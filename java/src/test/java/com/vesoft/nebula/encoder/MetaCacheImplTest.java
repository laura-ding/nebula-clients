/* Copyright (c) 2020 vesoft inc. All rights reserved.
 *
 * This source code is licensed under Apache 2.0 License,
 * attached with Common Clause Condition 1.0, found in the LICENSES directory.
 */

package com.vesoft.nebula.encoder;

import com.vesoft.nebula.HostAddr;
import com.vesoft.nebula.meta.ColumnDef;
import com.vesoft.nebula.meta.ColumnTypeDef;
import com.vesoft.nebula.meta.EdgeItem;
import com.vesoft.nebula.meta.IdName;
import com.vesoft.nebula.meta.PropertyType;
import com.vesoft.nebula.meta.Schema;
import com.vesoft.nebula.meta.SpaceDesc;
import com.vesoft.nebula.meta.SpaceItem;
import com.vesoft.nebula.meta.TagItem;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MetaCacheImplTest implements MetaCache {
    private SpaceItem spaceItem = new SpaceItem();
    private Map<String, TagItem> tagItems = new HashMap<>();
    private Map<String, EdgeItem> edgeItems = new HashMap<>();

    private Schema genNoDefaultVal() {
        List<ColumnDef> columns = new ArrayList<>();
        ColumnDef columnDef = new ColumnDef(("Col01").getBytes(),
            new ColumnTypeDef(PropertyType.BOOL));
        columns.add(columnDef);

        columnDef = new ColumnDef(("Col02").getBytes(),
            new ColumnTypeDef(PropertyType.INT8));
        columns.add(columnDef);

        columnDef = new ColumnDef(("Col03").getBytes(),
            new ColumnTypeDef(PropertyType.INT16));
        columns.add(columnDef);

        columnDef = new ColumnDef(("Col04").getBytes(),
            new ColumnTypeDef(PropertyType.INT32));
        columns.add(columnDef);

        columnDef = new ColumnDef(("Col05").getBytes(),
            new ColumnTypeDef(PropertyType.INT64));
        columns.add(columnDef);

        columnDef = new ColumnDef(("Col06").getBytes(),
            new ColumnTypeDef(PropertyType.FLOAT));
        columns.add(columnDef);

        columnDef = new ColumnDef(("Col07").getBytes(),
            new ColumnTypeDef(PropertyType.DOUBLE));
        columns.add(columnDef);
        columnDef = new ColumnDef(("Col08").getBytes(),
            new ColumnTypeDef(PropertyType.STRING));
        columns.add(columnDef);
        columnDef = new ColumnDef(("Col09").getBytes(),
            new ColumnTypeDef(PropertyType.FIXED_STRING, (short)12));
        columns.add(columnDef);
        columnDef = new ColumnDef(("Col10").getBytes(),
            new ColumnTypeDef(PropertyType.TIMESTAMP));
        columns.add(columnDef);
        columnDef = new ColumnDef(("Col11").getBytes(),
            new ColumnTypeDef(PropertyType.DATE));
        columns.add(columnDef);
        columnDef = new ColumnDef(("Col12").getBytes(),
            new ColumnTypeDef(PropertyType.TIME));
        columns.add(columnDef);
        columnDef = new ColumnDef(("Col13").getBytes(),
            new ColumnTypeDef(PropertyType.DATETIME));
        columns.add(columnDef);
        columnDef = new ColumnDef(("Col14").getBytes(),
            new ColumnTypeDef(PropertyType.INT64));
        columnDef.setNullable(true);
        columns.add(columnDef);
        columnDef = new ColumnDef(("Col15").getBytes(),
            new ColumnTypeDef(PropertyType.INT32));
        columnDef.setNullable(true);
        columns.add(columnDef);
        return new Schema(columns, null);
    }

    private Schema genWithDefaultVal() {
        List<ColumnDef> columns = new ArrayList<>();
        ColumnDef columnDef1 = new ColumnDef(("Col01").getBytes(),
            new ColumnTypeDef(PropertyType.BOOL));
        columnDef1.setDefault_value("".getBytes());
        columns.add(columnDef1);

        ColumnDef columnDef2 = new ColumnDef(("Col02").getBytes(),
            new ColumnTypeDef(PropertyType.INT64));
        columnDef2.setDefault_value("".getBytes());
        columns.add(columnDef2);

        ColumnDef columnDef3 = new ColumnDef(("Col03").getBytes(),
            new ColumnTypeDef(PropertyType.STRING));
        columnDef3.setDefault_value("".getBytes());
        columns.add(columnDef3);

        ColumnDef columnDef4 = new ColumnDef(("Col04").getBytes(),
            new ColumnTypeDef(PropertyType.FIXED_STRING));
        columnDef4.setDefault_value("".getBytes());
        columns.add(columnDef4);
        return new Schema(columns, null);
    }

    public Schema genEmptyString() {
        List<ColumnDef> columns = new ArrayList<>();
        ColumnDef columnDef = new ColumnDef(("Col01").getBytes(),
            new ColumnTypeDef(PropertyType.STRING));
        columns.add(columnDef);
        return new Schema(columns, null);
    }

    public MetaCacheImplTest() {
        spaceItem.space_id = 1;
        SpaceDesc spaceDesc = new SpaceDesc("test_space".getBytes(),
                                3,
                                1,
                                            "utf-8".getBytes(),
                                            "utf-8".getBytes(),
                                            new ColumnTypeDef(
                                                PropertyType.FIXED_STRING, (short)20));

        this.spaceItem = spaceItem;
        this.spaceItem.properties = spaceDesc;
        TagItem tagItem1 = new TagItem();
        tagItem1.tag_name = "tag_no_default".getBytes();
        tagItem1.version = 12;
        tagItem1.tag_id = 2;
        tagItem1.schema = genNoDefaultVal();
        this.tagItems.put(new String(tagItem1.tag_name), tagItem1);

        TagItem tagItem2 = new TagItem();
        tagItem2.tag_name = "tag_with_default".getBytes();
        tagItem2.version = 7;
        tagItem2.schema = genWithDefaultVal();
        this.tagItems.put(new String(tagItem2.tag_name), tagItem2);

        TagItem tagItem3 = new TagItem();
        tagItem3.tag_name = "tag_with_empty_string".getBytes();
        tagItem3.version = 0;
        tagItem3.schema = genEmptyString();
        this.tagItems.put(new String(tagItem3.tag_name), tagItem3);

        EdgeItem edgeItem1 = new EdgeItem();
        edgeItem1.edge_name = "edge_no_default".getBytes();
        edgeItem1.schema = genNoDefaultVal();
        edgeItem1.version = 12;
        this.edgeItems.put(new String(edgeItem1.edge_name), edgeItem1);

        EdgeItem edgeItem2 = new EdgeItem();
        edgeItem2.edge_name = "edge_with_default".getBytes();
        edgeItem2.version = 7;
        edgeItem2.schema = genWithDefaultVal();
        this.edgeItems.put(new String(edgeItem2.edge_name), edgeItem2);

        EdgeItem edgeItem3 = new EdgeItem();
        edgeItem3.edge_name = "edge_with_empty_string".getBytes();
        edgeItem3.version = 0;
        edgeItem3.schema = genEmptyString();
        this.edgeItems.put(new String(edgeItem3.edge_name), edgeItem3);
    }

    @Override
    public SpaceItem getSpace(String spaceName) {
        return spaceItem;
    }

    @Override
    public List<IdName> listSpaces() {
        return null;
    }

    @Override
    public TagItem getTag(String tagName) {
        if (!tagItems.containsKey(tagName)) {
            return null;
        }
        return tagItems.get(tagName);
    }

    @Override
    public List<TagItem> listTags() {
        return new ArrayList<TagItem>(tagItems.values());
    }

    @Override
    public EdgeItem getEdge(String edgeName) {
        if (!edgeItems.containsKey(edgeName)) {
            return null;
        }
        return edgeItems.get(edgeName);
    }

    @Override
    public List<EdgeItem> listEdges() {
        return new ArrayList<EdgeItem>(edgeItems.values());
    }

    @Override
    public Map<Integer, List<HostAddr>> getPartsAlloc(String spaceName) {
        return null;
    }
}
