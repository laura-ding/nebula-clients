/* Copyright (c) 2020 vesoft inc. All rights reserved.
 *
 * This source code is licensed under Apache 2.0 License,
 * attached with Common Clause Condition 1.0, found in the LICENSES directory.
 */

package com.vesoft.nebula.client.graph.data;

import com.vesoft.nebula.Tag;
import com.vesoft.nebula.Value;
import com.vesoft.nebula.Vertex;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Node {
    private Map<String, List<String>> tagPropName = new HashMap<>();
    private Map<String, List<Value>> tagPropValue = new HashMap<>();
    private String vid;

    public Node(Vertex vertex) {
        vid = new String(vertex.vid);
        for (Tag tag : vertex.tags) {
            List<String> names = new ArrayList<>();
            List<Value> values = new ArrayList<>();
            for (byte[] key : tag.props.keySet()) {
                names.add(new String(key));
                values.add(tag.props.get(key));
            }
            tagPropName.put(new String(tag.name), names);
            tagPropValue.put(new String(tag.name), values);
        }
    }

    public String getId() {
        return vid;
    }

    public List<String> tags() {
        return tagPropName.keySet().stream().collect(Collectors.toList());
    }

    public boolean hasTag(String tagName) {
        return tagPropName.containsKey(tagName);
    }

    public List<Value> propValues(String tagName) {
        if (tagPropValue.containsKey(tagName)) {
            return tagPropValue.get(tagName);
        }
        throw new IllegalArgumentException(tagName + " is not found");
    }

    public List<String> propNames(String tag) {
        if (tagPropName.containsKey(tag)) {
            return tagPropName.get(tag);
        }
        throw new IllegalArgumentException(tag + " is not found");
    }

    public Value getValue(String tag, String propName) {
        if (!tagPropName.containsKey(tag)) {
            throw new IllegalArgumentException(tag + " is not found");
        }
        int index = tagPropName.get(tag).indexOf(propName);
        if (index < 0) {
            throw new IllegalArgumentException(propName + " is not found");
        }
        return tagPropValue.get(tag).get(index);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Node node = (Node) o;
        return Objects.equals(vid, node.vid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagPropName, tagPropValue, vid);
    }

    @Override
    public String toString() {
        return this.getClass().toString()
                + "{, }tagPropName=" + tagPropName
                + ", tagPropValue=" + tagPropValue
                + ", vid='" + vid + '\''
                + '}';
    }
}
