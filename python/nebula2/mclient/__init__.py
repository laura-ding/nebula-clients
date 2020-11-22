#!/usr/bin/env python
# --coding:utf-8--

# Copyright (c) 2020 vesoft inc. All rights reserved.
#
# This source code is licensed under Apache 2.0 License,
# attached with Common Clause Condition 1.0, found in the LICENSES directory.

import logging
import socket
import threading

from _thread import RLock
from nebula2.Exception import InValidHostname
from nebula2.meta.ttypes import (
    ListTagsReq,
    ListEdgesReq,
    ListSpacesReq,
    GetPartsAllocReq,
    ErrorCode
)

from nebula2.meta import (
    ttypes,
    MetaService
)

from thrift.transport import TSocket, TTransport
from thrift.protocol import TBinaryProtocol


class MetaClient(object):
    def __init__(self, host, port, timeout):
        try:
            try:
                ip = socket.gethostbyname(host)
            except Exception:
                raise InValidHostname(str(host))
            s = TSocket.TSocket(ip, port)
            if timeout > 0:
                s.setTimeout(timeout)
            transport = TTransport.TBufferedTransport(s)
            protocol = TBinaryProtocol.TBinaryProtocol(transport)
            transport.open()
            self._connection = MetaService.Client(protocol)
        except Exception:
            raise

    def list_tags(self, space_id):
        req = ListTagsReq()
        req.space_id = space_id
        resp = self._connection.listTags(req)
        if resp.code != ttypes.ErrorCode.SUCCEEDED:
            raise RuntimeError("List tags from space id:{} failed, error code: {}"
                               .format(space_id, ttypes.ErrorCode._VALUES_TO_NAMES(resp.code)))
        return resp.tags

    def list_edges(self, space_id):
        req = ListEdgesReq()
        req.space_id = space_id
        resp = self._connection.listEdges(req)
        if resp.code != ttypes.ErrorCode.SUCCEEDED:
            raise RuntimeError("List edges from space id:{} failed, error code: {}"
                               .format(space_id, ttypes.ErrorCode._VALUES_TO_NAMES(resp.code)))
        return resp.edges

    def list_spaces(self):
        req = ListSpacesReq()
        resp = self._connection.listSpaces(req)
        if resp.code != ttypes.ErrorCode.SUCCEEDED:
            raise RuntimeError("List spaces failed, error code: {}"
                               .format(ttypes.ErrorCode._VALUES_TO_NAMES(resp.code)))
        return resp.spaces

    def get_parts_alloc(self, space_id):
        req = GetPartsAllocReq()
        req.space_id = space_id
        resp = self._connection.getPartsAlloc(req)
        if resp.code != ttypes.ErrorCode.SUCCEEDED:
            raise RuntimeError("List parts from space id:{} failed, error code: {}"
                               .format(space_id, ttypes.ErrorCode._VALUES_TO_NAMES(resp.code)))
        return resp.parts

    def close(self):
        try:
            self._connection._iprot.trans.close()
        except Exception:
            raise


class MetaCache(object):
    class SpaceCache(object):
        space_id = 0
        space_name = ""
        tag_items = {}
        edge_items = {}
        part_addrs = {}

    def __init__(self, meta_host, meta_port, timeout, decode_type='utf-8'):
        self._decode_type = decode_type
        self._lock = RLock()
        self._meta_client = MetaClient(meta_host, meta_port, timeout)
        self._space_caches = {}
        # create a thread to update meta data from metad
        self._period_update_meta_data()

    def _period_update_meta_data(self):
        self._load_all()
        timer = threading.Timer(10, self._period_update_meta_data)
        timer.setDaemon(True)
        timer.start()

    def _load_all(self):
        try:
            spaces = self._meta_client.list_spaces()
            space_caches = {}
            for space in spaces:
                space_id = space.id.get_space_id()
                space_cache = MetaCache.SpaceCache()
                space_cache.space_id = space_id
                space_cache.space_name = space.name
                tags = self._meta_client.list_tags(space_id)
                edges = self._meta_client.list_edges(space_id)
                parts = self._meta_client.get_parts_alloc(space_id)
                for tag in tags:
                    space_cache.tag_items[tag.tag_name.decode(self._decode_type)] = tag
                for edge in edges:
                    space_cache.edge_items[edge.edge_name.decode(self._decode_type)] = edge
                space_cache.part_addrs = parts
                space_caches[space.name.decode(self._decode_type)] = space_cache

            with self._lock:
                self._space_caches = space_caches
        except Exception as x:
            logging.error('Update meta data failed: {}'.format(x))
            import traceback
            logging.error(traceback.format_exc())

    def get_tag_id(self, space_name, tag_name):
        '''
        get_tag_id
        :param space_name:
        :param tag_name:
        :return:
        '''
        with self._lock:
            tag_items = self._get_tag_item(space_name, tag_name)
            return tag_items.tag_id

    def get_edge_type(self, space_name, edge_name):
        '''
        get_edge_type
        :param space_name:
        :param edge_name:
        :return:
        '''
        with self._lock:
            if space_name not in self._space_caches.keys():
                raise RuntimeError("Space name:{} is not found".format(space_name))
            space_cache = self._space_caches[space_name]
            if edge_name not in space_cache.edge_items.keys():
                raise RuntimeError("Edge name:{} is not found".format(edge_name))
            edge_item = self._get_edge_item(space_name, edge_name)
            return edge_item.edge_type

    def get_space_id(self, space_name):
        '''
        get_space_id
        :param space_name:
        :return:
        '''
        with self._lock:
            if space_name not in self._space_caches.keys():
                raise RuntimeError("{} is not found".format(space_name))
            return self._space_caches[space_name].space_id

    def get_tag_schema(self, space_name, tag_name):
        '''
        get_tag_schema
        :param space_name:
        :param tag_name:
        :return:
        '''
        with self._lock:
            tag_item = self._get_tag_item(space_name, tag_name)
            return tag_item.schema

    def get_edge_schema(self, space_name, edge_name):
        '''
        get_edge_schema
        :param space_name:
        :param edge_name:
        :return:
        '''
        with self._lock:
            edge_item = self._get_edge_item(space_name, edge_name)
            return edge_item.schema

    def get_part_addresses(self, space_name, part_id):
        with self._lock:
            if space_name not in self._space_caches.keys():
                raise RuntimeError("Space name:{} is not found".format(space_name))
            space_cache = self._space_caches[space_name]
            if part_id not in space_cache.part_addrs.keys():
                raise RuntimeError("Part id:{} is not found".format(part_id))
            return space_cache.part_addrs[part_id]

    def _get_tag_item(self, space_name, tag_name):
        if space_name not in self._space_caches.keys():
            raise RuntimeError("Space name:{} is not found".format(space_name))
        space_cache = self._space_caches[space_name]
        if tag_name not in space_cache.tag_items.keys():
            raise RuntimeError("Tag name:{} is not found".format(tag_name))
        return space_cache.tag_items[tag_name]

    def _get_edge_item(self, space_name, edge_name):
        if space_name not in self._space_caches.keys():
            raise RuntimeError("Space name:{} is not found".format(space_name))
        space_cache = self._space_caches[space_name]
        if edge_name not in space_cache.edge_items.keys():
            raise RuntimeError("Edge name:{} is not found".format(edge_name))
        return space_cache.edge_items[edge_name]

