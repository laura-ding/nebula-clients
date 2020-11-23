#!/usr/bin/env python
# --coding:utf-8--

# Copyright (c) 2020 vesoft inc. All rights reserved.
#
# This source code is licensed under Apache 2.0 License,
# attached with Common Clause Condition 1.0, found in the LICENSES directory.

from nebula2.common.ttypes import Value


def Value__hash__(self):
    '''
    reimplement the hash function for Value
    :param self:
    :return:
    '''
    return self.value.__hash__()
Value.__hash__ = Value__hash__


