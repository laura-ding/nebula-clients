#!/usr/bin/env python
# --coding:utf-8--

# Copyright (c) 2020 vesoft inc. All rights reserved.
#
# This source code is licensed under Apache 2.0 License,
# attached with Common Clause Condition 1.0, found in the LICENSES directory.

import sys
import os


current_dir = os.path.dirname(os.path.abspath(__file__))
root_dir = os.path.join(current_dir, '..')
sys.path.insert(0, root_dir)

from nebula2.common.ttypes import HostAddr
from nebula2.gclient.net import Connection
from nebula2.meta import ttypes
from nebula2.mclient import MetaCache


class TestMetaCache(object):
    @classmethod
    def setup_class(self):
        try:
            conn = Connection()
            conn.open('127.0.0.1', 3699, 1000)
            session_id = conn.authenticate('root', 'nebula')
            assert session_id != 0
            resp = conn.execute(session_id,
                                'CREATE SPACE IF NOT EXISTS test_meta_cache1(REPLICA_FACTOR=3);'
                                'USE test_meta_cache1;'
                                'CREATE TAG IF NOT EXISTS tag11(name string);'
                                'CREATE EDGE IF NOT EXISTS edge11(name string);'
                                'CREATE SPACE IF NOT EXISTS test_meta_cache2;'
                                'USE test_meta_cache2;'
                                'CREATE TAG IF NOT EXISTS tag22(name string);'
                                'CREATE EDGE IF NOT EXISTS edge22(name string);')
            assert resp.error_code == 0
            conn.close()
            self.meta_cache = MetaCache('127.0.0.1', 45500, 50000)
        except Exception as x:
            import traceback
            print(traceback.format_exc())
            assert False

    def test_get_space_id(self):
        space_id1 = self.meta_cache.get_space_id('test_meta_cache1')
        space_id2 = self.meta_cache.get_space_id('test_meta_cache2')
        assert 0 < space_id1 < space_id2

    def test_get_tag_id(self):
        tag_id1 = self.meta_cache.get_tag_id('test_meta_cache1', 'tag11')
        tag_id2 = self.meta_cache.get_tag_id('test_meta_cache2', 'tag22')
        assert 0 < tag_id1 < tag_id2

    def test_get_edge_type(self):
        edge_id1 = self.meta_cache.get_edge_type('test_meta_cache1', 'edge11')
        edge_id2 = self.meta_cache.get_edge_type('test_meta_cache2', 'edge22')
        assert 0 < edge_id1 < edge_id2

    def test_get_tag_schema(self):
        tag_schema1 = self.meta_cache.get_tag_schema('test_meta_cache1', 'tag11')
        tag_schema2 = self.meta_cache.get_tag_schema('test_meta_cache2', 'tag22')
        assert tag_schema1.columns[0].name.decode('utf-8') == 'name'
        assert tag_schema1.columns[0].type.type == ttypes.PropertyType.STRING
        assert tag_schema1.columns[0].type.type_length == 0
        assert tag_schema2.columns[0].name.decode('utf-8') == 'name'
        assert tag_schema2.columns[0].type.type == ttypes.PropertyType.STRING
        assert tag_schema2.columns[0].type.type_length == 0

    def test_get_edge_schema(self):
        edge_schema1 = self.meta_cache.get_edge_schema('test_meta_cache1', 'edge11')
        edge_schema2 = self.meta_cache.get_edge_schema('test_meta_cache2', 'edge22')
        assert edge_schema1.columns[0].name.decode('utf-8') == 'name'
        assert edge_schema1.columns[0].type.type == ttypes.PropertyType.STRING
        assert edge_schema1.columns[0].type.type_length == 0
        assert edge_schema2.columns[0].name.decode('utf-8') == 'name'
        assert edge_schema2.columns[0].type.type == ttypes.PropertyType.STRING
        assert edge_schema2.columns[0].type.type_length == 0

    def test_get_part_addresses(self):
        addresses = self.meta_cache.get_part_addresses('test_meta_cache1', 1)
        assert len(addresses) == 3
        hosts = [addr.host for addr in addresses]
        expected_hosts = ['storaged0', 'storaged1', 'storaged2']
        hosts = sorted(hosts)
        expected_hosts = sorted(expected_hosts)
        assert hosts == expected_hosts

        ports = [addr.port for addr in addresses]
        expected_hosts = [44500, 44500, 44500]
        assert ports == expected_hosts

