/**
 * Autogenerated by Thrift
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.vesoft.nebula.storage;

import org.apache.commons.lang.builder.HashCodeBuilder;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import java.util.BitSet;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.facebook.thrift.*;
import com.facebook.thrift.async.*;
import com.facebook.thrift.meta_data.*;
import com.facebook.thrift.server.*;
import com.facebook.thrift.transport.*;
import com.facebook.thrift.protocol.*;

@SuppressWarnings({ "unused", "serial" })
public class GetNeighborsRequest implements TBase, java.io.Serializable, Cloneable {
  private static final TStruct STRUCT_DESC = new TStruct("GetNeighborsRequest");
  private static final TField SPACE_ID_FIELD_DESC = new TField("space_id", TType.I32, (short)1);
  private static final TField COLUMN_NAMES_FIELD_DESC = new TField("column_names", TType.LIST, (short)2);
  private static final TField PARTS_FIELD_DESC = new TField("parts", TType.MAP, (short)3);
  private static final TField TRAVERSE_SPEC_FIELD_DESC = new TField("traverse_spec", TType.STRUCT, (short)4);

  public int space_id;
  public List<byte[]> column_names;
  public Map<Integer,List<com.vesoft.nebula.Row>> parts;
  public TraverseSpec traverse_spec;
  public static final int SPACE_ID = 1;
  public static final int COLUMN_NAMES = 2;
  public static final int PARTS = 3;
  public static final int TRAVERSE_SPEC = 4;
  public static boolean DEFAULT_PRETTY_PRINT = true;

  // isset id assignments
  private static final int __SPACE_ID_ISSET_ID = 0;
  private BitSet __isset_bit_vector = new BitSet(1);

  public static final Map<Integer, FieldMetaData> metaDataMap;
  static {
    Map<Integer, FieldMetaData> tmpMetaDataMap = new HashMap<Integer, FieldMetaData>();
    tmpMetaDataMap.put(SPACE_ID, new FieldMetaData("space_id", TFieldRequirementType.DEFAULT, 
        new FieldValueMetaData(TType.I32)));
    tmpMetaDataMap.put(COLUMN_NAMES, new FieldMetaData("column_names", TFieldRequirementType.DEFAULT, 
        new ListMetaData(TType.LIST, 
            new FieldValueMetaData(TType.STRING))));
    tmpMetaDataMap.put(PARTS, new FieldMetaData("parts", TFieldRequirementType.DEFAULT, 
        new MapMetaData(TType.MAP, 
            new FieldValueMetaData(TType.I32), 
            new ListMetaData(TType.LIST, 
                new StructMetaData(TType.STRUCT, com.vesoft.nebula.Row.class)))));
    tmpMetaDataMap.put(TRAVERSE_SPEC, new FieldMetaData("traverse_spec", TFieldRequirementType.DEFAULT, 
        new StructMetaData(TType.STRUCT, TraverseSpec.class)));
    metaDataMap = Collections.unmodifiableMap(tmpMetaDataMap);
  }

  static {
    FieldMetaData.addStructMetaDataMap(GetNeighborsRequest.class, metaDataMap);
  }

  public GetNeighborsRequest() {
  }

  public GetNeighborsRequest(
    int space_id,
    List<byte[]> column_names,
    Map<Integer,List<com.vesoft.nebula.Row>> parts,
    TraverseSpec traverse_spec)
  {
    this();
    this.space_id = space_id;
    setSpace_idIsSet(true);
    this.column_names = column_names;
    this.parts = parts;
    this.traverse_spec = traverse_spec;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public GetNeighborsRequest(GetNeighborsRequest other) {
    __isset_bit_vector.clear();
    __isset_bit_vector.or(other.__isset_bit_vector);
    this.space_id = TBaseHelper.deepCopy(other.space_id);
    if (other.isSetColumn_names()) {
      this.column_names = TBaseHelper.deepCopy(other.column_names);
    }
    if (other.isSetParts()) {
      this.parts = TBaseHelper.deepCopy(other.parts);
    }
    if (other.isSetTraverse_spec()) {
      this.traverse_spec = TBaseHelper.deepCopy(other.traverse_spec);
    }
  }

  public GetNeighborsRequest deepCopy() {
    return new GetNeighborsRequest(this);
  }

  @Deprecated
  public GetNeighborsRequest clone() {
    return new GetNeighborsRequest(this);
  }

  public int  getSpace_id() {
    return this.space_id;
  }

  public GetNeighborsRequest setSpace_id(int space_id) {
    this.space_id = space_id;
    setSpace_idIsSet(true);
    return this;
  }

  public void unsetSpace_id() {
    __isset_bit_vector.clear(__SPACE_ID_ISSET_ID);
  }

  // Returns true if field space_id is set (has been assigned a value) and false otherwise
  public boolean isSetSpace_id() {
    return __isset_bit_vector.get(__SPACE_ID_ISSET_ID);
  }

  public void setSpace_idIsSet(boolean value) {
    __isset_bit_vector.set(__SPACE_ID_ISSET_ID, value);
  }

  public List<byte[]>  getColumn_names() {
    return this.column_names;
  }

  public GetNeighborsRequest setColumn_names(List<byte[]> column_names) {
    this.column_names = column_names;
    return this;
  }

  public void unsetColumn_names() {
    this.column_names = null;
  }

  // Returns true if field column_names is set (has been assigned a value) and false otherwise
  public boolean isSetColumn_names() {
    return this.column_names != null;
  }

  public void setColumn_namesIsSet(boolean value) {
    if (!value) {
      this.column_names = null;
    }
  }

  public Map<Integer,List<com.vesoft.nebula.Row>>  getParts() {
    return this.parts;
  }

  public GetNeighborsRequest setParts(Map<Integer,List<com.vesoft.nebula.Row>> parts) {
    this.parts = parts;
    return this;
  }

  public void unsetParts() {
    this.parts = null;
  }

  // Returns true if field parts is set (has been assigned a value) and false otherwise
  public boolean isSetParts() {
    return this.parts != null;
  }

  public void setPartsIsSet(boolean value) {
    if (!value) {
      this.parts = null;
    }
  }

  public TraverseSpec  getTraverse_spec() {
    return this.traverse_spec;
  }

  public GetNeighborsRequest setTraverse_spec(TraverseSpec traverse_spec) {
    this.traverse_spec = traverse_spec;
    return this;
  }

  public void unsetTraverse_spec() {
    this.traverse_spec = null;
  }

  // Returns true if field traverse_spec is set (has been assigned a value) and false otherwise
  public boolean isSetTraverse_spec() {
    return this.traverse_spec != null;
  }

  public void setTraverse_specIsSet(boolean value) {
    if (!value) {
      this.traverse_spec = null;
    }
  }

  @SuppressWarnings("unchecked")
  public void setFieldValue(int fieldID, Object value) {
    switch (fieldID) {
    case SPACE_ID:
      if (value == null) {
        unsetSpace_id();
      } else {
        setSpace_id((Integer)value);
      }
      break;

    case COLUMN_NAMES:
      if (value == null) {
        unsetColumn_names();
      } else {
        setColumn_names((List<byte[]>)value);
      }
      break;

    case PARTS:
      if (value == null) {
        unsetParts();
      } else {
        setParts((Map<Integer,List<com.vesoft.nebula.Row>>)value);
      }
      break;

    case TRAVERSE_SPEC:
      if (value == null) {
        unsetTraverse_spec();
      } else {
        setTraverse_spec((TraverseSpec)value);
      }
      break;

    default:
      throw new IllegalArgumentException("Field " + fieldID + " doesn't exist!");
    }
  }

  public Object getFieldValue(int fieldID) {
    switch (fieldID) {
    case SPACE_ID:
      return new Integer(getSpace_id());

    case COLUMN_NAMES:
      return getColumn_names();

    case PARTS:
      return getParts();

    case TRAVERSE_SPEC:
      return getTraverse_spec();

    default:
      throw new IllegalArgumentException("Field " + fieldID + " doesn't exist!");
    }
  }

  // Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise
  public boolean isSet(int fieldID) {
    switch (fieldID) {
    case SPACE_ID:
      return isSetSpace_id();
    case COLUMN_NAMES:
      return isSetColumn_names();
    case PARTS:
      return isSetParts();
    case TRAVERSE_SPEC:
      return isSetTraverse_spec();
    default:
      throw new IllegalArgumentException("Field " + fieldID + " doesn't exist!");
    }
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof GetNeighborsRequest)
      return this.equals((GetNeighborsRequest)that);
    return false;
  }

  public boolean equals(GetNeighborsRequest that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_space_id = true;
    boolean that_present_space_id = true;
    if (this_present_space_id || that_present_space_id) {
      if (!(this_present_space_id && that_present_space_id))
        return false;
      if (!TBaseHelper.equalsNobinary(this.space_id, that.space_id))
        return false;
    }

    boolean this_present_column_names = true && this.isSetColumn_names();
    boolean that_present_column_names = true && that.isSetColumn_names();
    if (this_present_column_names || that_present_column_names) {
      if (!(this_present_column_names && that_present_column_names))
        return false;
      if (!TBaseHelper.equalsSlow(this.column_names, that.column_names))
        return false;
    }

    boolean this_present_parts = true && this.isSetParts();
    boolean that_present_parts = true && that.isSetParts();
    if (this_present_parts || that_present_parts) {
      if (!(this_present_parts && that_present_parts))
        return false;
      if (!TBaseHelper.equalsNobinary(this.parts, that.parts))
        return false;
    }

    boolean this_present_traverse_spec = true && this.isSetTraverse_spec();
    boolean that_present_traverse_spec = true && that.isSetTraverse_spec();
    if (this_present_traverse_spec || that_present_traverse_spec) {
      if (!(this_present_traverse_spec && that_present_traverse_spec))
        return false;
      if (!TBaseHelper.equalsNobinary(this.traverse_spec, that.traverse_spec))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    HashCodeBuilder builder = new HashCodeBuilder();

    boolean present_space_id = true;
    builder.append(present_space_id);
    if (present_space_id)
      builder.append(space_id);

    boolean present_column_names = true && (isSetColumn_names());
    builder.append(present_column_names);
    if (present_column_names)
      builder.append(column_names);

    boolean present_parts = true && (isSetParts());
    builder.append(present_parts);
    if (present_parts)
      builder.append(parts);

    boolean present_traverse_spec = true && (isSetTraverse_spec());
    builder.append(present_traverse_spec);
    if (present_traverse_spec)
      builder.append(traverse_spec);

    return builder.toHashCode();
  }

  public void read(TProtocol iprot) throws TException {
    TField field;
    iprot.readStructBegin(metaDataMap);
    while (true)
    {
      field = iprot.readFieldBegin();
      if (field.type == TType.STOP) { 
        break;
      }
      switch (field.id)
      {
        case SPACE_ID:
          if (field.type == TType.I32) {
            this.space_id = iprot.readI32();
            setSpace_idIsSet(true);
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case COLUMN_NAMES:
          if (field.type == TType.LIST) {
            {
              TList _list36 = iprot.readListBegin();
              this.column_names = new ArrayList<byte[]>(Math.max(0, _list36.size));
              for (int _i37 = 0; 
                   (_list36.size < 0) ? iprot.peekList() : (_i37 < _list36.size); 
                   ++_i37)
              {
                byte[] _elem38;
                _elem38 = iprot.readBinary();
                this.column_names.add(_elem38);
              }
              iprot.readListEnd();
            }
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case PARTS:
          if (field.type == TType.MAP) {
            {
              TMap _map39 = iprot.readMapBegin();
              this.parts = new HashMap<Integer,List<com.vesoft.nebula.Row>>(Math.max(0, 2*_map39.size));
              for (int _i40 = 0; 
                   (_map39.size < 0) ? iprot.peekMap() : (_i40 < _map39.size); 
                   ++_i40)
              {
                int _key41;
                List<com.vesoft.nebula.Row> _val42;
                _key41 = iprot.readI32();
                {
                  TList _list43 = iprot.readListBegin();
                  _val42 = new ArrayList<com.vesoft.nebula.Row>(Math.max(0, _list43.size));
                  for (int _i44 = 0; 
                       (_list43.size < 0) ? iprot.peekList() : (_i44 < _list43.size); 
                       ++_i44)
                  {
                    com.vesoft.nebula.Row _elem45;
                    _elem45 = new com.vesoft.nebula.Row();
                    _elem45.read(iprot);
                    _val42.add(_elem45);
                  }
                  iprot.readListEnd();
                }
                this.parts.put(_key41, _val42);
              }
              iprot.readMapEnd();
            }
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case TRAVERSE_SPEC:
          if (field.type == TType.STRUCT) {
            this.traverse_spec = new TraverseSpec();
            this.traverse_spec.read(iprot);
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        default:
          TProtocolUtil.skip(iprot, field.type);
          break;
      }
      iprot.readFieldEnd();
    }
    iprot.readStructEnd();


    // check for required fields of primitive type, which can't be checked in the validate method
    validate();
  }

  public void write(TProtocol oprot) throws TException {
    validate();

    oprot.writeStructBegin(STRUCT_DESC);
    oprot.writeFieldBegin(SPACE_ID_FIELD_DESC);
    oprot.writeI32(this.space_id);
    oprot.writeFieldEnd();
    if (this.column_names != null) {
      oprot.writeFieldBegin(COLUMN_NAMES_FIELD_DESC);
      {
        oprot.writeListBegin(new TList(TType.STRING, this.column_names.size()));
        for (byte[] _iter46 : this.column_names)        {
          oprot.writeBinary(_iter46);
        }
        oprot.writeListEnd();
      }
      oprot.writeFieldEnd();
    }
    if (this.parts != null) {
      oprot.writeFieldBegin(PARTS_FIELD_DESC);
      {
        oprot.writeMapBegin(new TMap(TType.I32, TType.LIST, this.parts.size()));
        for (Map.Entry<Integer, List<com.vesoft.nebula.Row>> _iter47 : this.parts.entrySet())        {
          oprot.writeI32(_iter47.getKey());
          {
            oprot.writeListBegin(new TList(TType.STRUCT, _iter47.getValue().size()));
            for (com.vesoft.nebula.Row _iter48 : _iter47.getValue())            {
              _iter48.write(oprot);
            }
            oprot.writeListEnd();
          }
        }
        oprot.writeMapEnd();
      }
      oprot.writeFieldEnd();
    }
    if (this.traverse_spec != null) {
      oprot.writeFieldBegin(TRAVERSE_SPEC_FIELD_DESC);
      this.traverse_spec.write(oprot);
      oprot.writeFieldEnd();
    }
    oprot.writeFieldStop();
    oprot.writeStructEnd();
  }

  @Override
  public String toString() {
    return toString(DEFAULT_PRETTY_PRINT);
  }

  @Override
  public String toString(boolean prettyPrint) {
    return toString(1, prettyPrint);
  }

  @Override
  public String toString(int indent, boolean prettyPrint) {
    String indentStr = prettyPrint ? TBaseHelper.getIndentedString(indent) : "";
    String newLine = prettyPrint ? "\n" : "";
String space = prettyPrint ? " " : "";
    StringBuilder sb = new StringBuilder("GetNeighborsRequest");
    sb.append(space);
    sb.append("(");
    sb.append(newLine);
    boolean first = true;

    sb.append(indentStr);
    sb.append("space_id");
    sb.append(space);
    sb.append(":").append(space);
    sb.append(TBaseHelper.toString(this. getSpace_id(), indent + 1, prettyPrint));
    first = false;
    if (!first) sb.append("," + newLine);
    sb.append(indentStr);
    sb.append("column_names");
    sb.append(space);
    sb.append(":").append(space);
    if (this. getColumn_names() == null) {
      sb.append("null");
    } else {
      sb.append(TBaseHelper.toString(this. getColumn_names(), indent + 1, prettyPrint));
    }
    first = false;
    if (!first) sb.append("," + newLine);
    sb.append(indentStr);
    sb.append("parts");
    sb.append(space);
    sb.append(":").append(space);
    if (this. getParts() == null) {
      sb.append("null");
    } else {
      sb.append(TBaseHelper.toString(this. getParts(), indent + 1, prettyPrint));
    }
    first = false;
    if (!first) sb.append("," + newLine);
    sb.append(indentStr);
    sb.append("traverse_spec");
    sb.append(space);
    sb.append(":").append(space);
    if (this. getTraverse_spec() == null) {
      sb.append("null");
    } else {
      sb.append(TBaseHelper.toString(this. getTraverse_spec(), indent + 1, prettyPrint));
    }
    first = false;
    sb.append(newLine + TBaseHelper.reduceIndent(indentStr));
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws TException {
    // check for required fields
    // check that fields of type enum have valid values
  }

}

