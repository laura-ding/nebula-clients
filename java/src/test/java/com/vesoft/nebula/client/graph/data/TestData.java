package com.vesoft.nebula.client.graph.data;

import com.vesoft.nebula.DataSet;
import com.vesoft.nebula.Date;
import com.vesoft.nebula.DateTime;
import com.vesoft.nebula.Edge;
import com.vesoft.nebula.NullType;
import com.vesoft.nebula.Path;
import com.vesoft.nebula.Row;
import com.vesoft.nebula.Step;
import com.vesoft.nebula.Tag;
import com.vesoft.nebula.Time;
import com.vesoft.nebula.Value;
import com.vesoft.nebula.Vertex;
import com.vesoft.nebula.graph.ErrorCode;
import com.vesoft.nebula.graph.ExecutionResponse;
import com.vesoft.nebula.graph.PlanDescription;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.Assert;
import org.junit.Test;


public class TestData {
    public Vertex getVertex(String vid) {
        Vertex vertex = new Vertex();
        List<Tag> tags = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Map<byte[], Value> props = new HashMap<>();
            for (int j = 0; j < 5; j++) {
                Value value = new Value();
                value.setIVal(j);
                props.put(String.format("prop%d", j).getBytes(), value);
            }
            Tag tag = new Tag(String.format("tag%d", i).getBytes(), props);
            tags.add(tag);
        }
        return new Vertex(vid.getBytes(), tags);
    }

    public Edge getEdge(String srcId, String dstId) {
        Edge edge = new Edge();
        edge.setSrc(srcId.getBytes());
        edge.setDst(dstId.getBytes());
        Map<byte[], Value> props = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            Value value = new Value();
            value.setIVal(i);
            props.put(String.format("prop%d", i).getBytes(), value);
        }
        return new Edge(srcId.getBytes(),
                dstId.getBytes(),
                1,
                "classmate".getBytes(),
                100,
                props);
    }

    public Path getPath(String startId, int stepsNum) {
        List<Step> steps = new ArrayList<>();
        for (int i = 0; i < stepsNum; i++) {
            Map<byte[], Value> props = new HashMap<>();
            for (int j = 0; j < 5; j++) {
                Value value = new Value();
                value.setIVal(j);
                props.put(String.format("prop%d", j).getBytes(), value);
            }
            int type = 1;
            if (i % 2 != 0) {
                type = -1;
            }
            Vertex dstId = getVertex(String.format("vertex%d", i));
            steps.add(new Step(getVertex(String.format("vertex%d", i)),
                    type,
                    String.format("classmate").getBytes(), 100, props));
        }
        return new Path(getVertex(startId), steps);
    }

    public DataSet getDateset() {
        final ArrayList<Value> list = new ArrayList<>();
        list.add(new Value(Value.IVAL, new Long(1)));
        list.add(new Value(Value.IVAL, new Long(2)));
        final HashSet<Value> set = new HashSet<>();
        set.add(new Value(Value.IVAL, new Long(1)));
        set.add(new Value(Value.IVAL, new Long(2)));
        final HashMap<String, Value> map = new HashMap();
        map.put("key1", new Value(Value.IVAL, new Long(1)));
        map.put("key2", new Value(Value.IVAL, new Long(2)));
        final Row row = new Row(Arrays.asList(
                new Value(),
                new Value(Value.NVAL, NullType.OUT_OF_RANGE),
                new Value(Value.BVAL, false),
                new Value(Value.IVAL, new Long(1)),
                new Value(Value.FVAL, 10.01),
                new Value(Value.SVAL, "value1".getBytes()),
                new Value(Value.LVAL, list),
                new Value(Value.UVAL, set),
                new Value(Value.MVAL, map),
                new Value(Value.TVAL, new Time((byte)10, (byte)30, (byte)0, 100)),
                new Value(Value.DVAL, new Date((short)2020, (byte)10, (byte)10)),
                new Value(Value.DTVAL,
                    new DateTime((short)2020, (byte)10,
                        (byte)10, (byte)10, (byte)30, (byte)0, 100)),
                new Value(Value.VVAL, getVertex("Tom")),
                new Value(Value.EVAL, getEdge("Tom", "Lily")),
                new Value(Value.PVAL, getPath("Tom", 3))));
        final List<byte[]> columnNames = Arrays.asList(
            "col0_empty".getBytes(),
            "col1_null".getBytes(),
            "col2_bool".getBytes(),
            "col3_int".getBytes(),
            "col4_double".getBytes(),
            "col5_string".getBytes(),
            "col6_list".getBytes(),
            "col7_set".getBytes(),
            "col8_map".getBytes(),
            "col9_time".getBytes(),
            "col10_date".getBytes(),
            "col11_datetime".getBytes(),
            "col12_vertex".getBytes(),
            "col13_edge".getBytes(),
            "col14_path".getBytes());
        return new DataSet(columnNames, Arrays.asList(row));
    }

    @Test
    public void testNode() {
        try {
            Node node = new Node(getVertex(new String("Tom")));
            assert Objects.equals(node.getId(), "Tom");
            assert node.hasLabel("tag1");
            List<String> names = Arrays.asList("prop0", "prop1", "prop2", "prop3", "prop4");
            assert Objects.equals(
                    node.keys("tag0").stream().sorted().collect(Collectors.toList()),
                    names.stream().sorted().collect(Collectors.toList()));
            List<Value> propValues = Arrays.asList(new Value(Value.IVAL, 0L),
                                                   new Value(Value.IVAL, 1L),
                                                   new Value(Value.IVAL, 2L),
                                                   new Value(Value.IVAL, 3L),
                                                   new Value(Value.IVAL, 4L));

            // TODO: Check the List<Value>
        } catch (Exception e) {
            e.printStackTrace();
            assert (false);
        }
    }

    @Test
    public void testRelationShip() {
        try {
            Edge edge = getEdge(new String("Tom"), new String("Lily"));
            Relationship relationShip = new Relationship(edge);
            assert Objects.equals(relationShip.srcId(), "Tom");
            assert Objects.equals(relationShip.dstId(), "Lily");
            assert Objects.equals(relationShip.edgeName(), "classmate");
            assert relationShip.ranking() == 100;

            // check keys
            List<String> names = Arrays.asList("prop0", "prop1", "prop2", "prop3", "prop4");
            assert Objects.equals(
                    relationShip.keys().stream().sorted().collect(Collectors.toList()),
                    names.stream().sorted().collect(Collectors.toList()));

            // check get values
            List<ValueWrapper> values = relationShip.values();
            assert values.get(0).isLong();
            ArrayList<Long> longVals  = new ArrayList<>();
            for (ValueWrapper val : values) {
                assert val.isLong();
                longVals.add(val.asLong());
            }
            List<Long> expectVals = Arrays.asList(0L, 1L, 2L, 3L, 4L);
            assert Objects.equals(expectVals,
                longVals.stream().sorted().collect(Collectors.toList()));

            // check properties
            HashMap<String, ValueWrapper> properties = relationShip.properties();
            assert properties.keySet().contains("prop0");
            assert properties.get("prop0").isLong();
            Assert.assertEquals(properties.get("prop0").asLong(), 0L);
            assert properties.keySet().contains("prop1");
            assert properties.get("prop1").isLong();
            Assert.assertEquals(properties.get("prop1").asLong(), 1L);
            assert properties.keySet().contains("prop2");
            assert properties.get("prop2").isLong();
            Assert.assertEquals(properties.get("prop2").asLong(), 2L);
            assert properties.keySet().contains("prop3");
            assert properties.get("prop3").isLong();
            Assert.assertEquals(properties.get("prop3").asLong(), 3L);
            assert properties.keySet().contains("prop4");
            assert properties.get("prop4").isLong();
            Assert.assertEquals(properties.get("prop4").asLong(), 4L);
            assert properties.keySet().contains("prop4");
        } catch (Exception e) {
            e.printStackTrace();
            assert (false);
        }
    }

    @Test
    public void testPathWarpper() {
        try {
            Path path = getPath("Tom", 5);
            PathWrapper pathWrapper = new PathWrapper(path);
            assert pathWrapper.length() == 5;
            Node node = new Node(getVertex("Tom"));
            assert pathWrapper.containNode(node);
            Relationship relationShip = new Relationship(getEdge("Tom", "vertex0"));
            assert pathWrapper.containRelationShip(relationShip);
            List<Node> nodes = new ArrayList<>();
            nodes.add(node);
            for (int i = 0; i < 5; i++) {
                nodes.add(new Node(getVertex(String.format("vertex%d", i))));
            }

            List<Relationship> relationships = new ArrayList<>();
            relationships.add(new Relationship(getEdge("Tom", "vertex0")));
            for (int i = 0; i < 4; i++) {
                if (i % 2 == 0) {
                    relationships.add(new Relationship(getEdge(String.format("vertex%d", i + 1),
                                                               String.format("vertex%d", i))));
                } else {
                    relationships.add(
                            new Relationship(getEdge(String.format("vertex%d", i),
                                             String.format("vertex%d", i + 1))));
                }
            }

            assert Objects.equals(nodes, pathWrapper.getNodes());
            assert Objects.equals(relationships, pathWrapper.getRelationships());
        } catch (Exception e) {
            e.printStackTrace();
            assert (false);
        }
    }

    @Test
    public void testResult() {
        try {
            ExecutionResponse resp =  new ExecutionResponse();
            resp.error_code = ErrorCode.SUCCEEDED;
            resp.error_msg = "test".getBytes();
            resp.comment = "test_comment".getBytes();
            resp.latency_in_us = 1000;
            resp.plan_desc = new PlanDescription();
            resp.space_name = "test_space".getBytes();
            resp.data = getDateset();
            ResultSet resultSet = new ResultSet(resp);
            assert resultSet.isSucceeded();
            assert resultSet.getErrorCode() == ErrorCode.SUCCEEDED;
            List<String> expectColNames = Arrays.asList(
                "col0_empty", "col1_null", "col2_bool", "col3_int", "col4_double", "col5_string",
                "col6_list", "col7_set", "col8_map", "col9_time", "col10_date",
                "col11_datetime", "col12_vertex", "col13_edge", "col14_path");
            assert Objects.equals(resultSet.keys(), expectColNames);
            assert resultSet.getRows().size() == 1;
            List<ValueWrapper> rowValues = resultSet.rowValues(0);
            assert rowValues.size() == 15;
            assert rowValues.get(0).isEmpty();

            assert rowValues.get(1).isNull();
            assert rowValues.get(1).asNull().nullType == ValueWrapper.NullType.OUT_OF_RANGE;

            assert rowValues.get(2).isBoolean();
            assert rowValues.get(2).asBoolean() == false;

            assert rowValues.get(3).isLong();
            assert rowValues.get(3).asLong() == 1;

            assert rowValues.get(4).isDouble();
            assert Double.compare(rowValues.get(4).asDouble(), 10.01) == 0;

            assert rowValues.get(5).isString();
            assert Objects.equals("value1", rowValues.get(5).asString());

            Assert.assertArrayEquals(
                rowValues.get(6).asList().stream().map(ValueWrapper::asLong).toArray(),
                Arrays.asList((long)1, (long)2).toArray());

            assert rowValues.get(7).isSet();
            Set<Long> set = rowValues.get(7).asSet().stream().map(ValueWrapper::asLong)
                .collect(Collectors.toSet());
            assert set.size() == 2;
            assert set.contains((long) 1);
            assert set.contains((long) 2);

            assert rowValues.get(8).isMap();
            HashMap<String, ValueWrapper> map = rowValues.get(8).asMap();
            assert map.keySet().size() == 2;
            Assert.assertArrayEquals(map.keySet().toArray(),
                Arrays.asList("key1", "key2").toArray());
            Assert.assertArrayEquals(map.values().stream().map(ValueWrapper::asLong).toArray(),
                Arrays.asList((long)1, (long)2).toArray());

            assert rowValues.get(9).isTime();
            assert rowValues.get(9).asTime() instanceof Time;

            assert rowValues.get(10).isDate();
            assert rowValues.get(10).asDate() instanceof Date;

            assert rowValues.get(11).isDateTime();
            assert rowValues.get(11).asDateTime() instanceof DateTime;

            assert rowValues.get(12).isVertex();
            assert Objects.equals(rowValues.get(12).asNode(),
                new Node(getVertex("Tom")));

            assert rowValues.get(13).isEdge();
            assert Objects.equals(rowValues.get(13).asRelationship(),
                new Relationship(getEdge("Tom", "Lily")));

            assert rowValues.get(14).isPath();
            assert Objects.equals(rowValues.get(14).asPath(),
                new PathWrapper(getPath("Tom", 3)));
        } catch (Exception e) {
            e.printStackTrace();
            assert (false);
        }
    }
}
