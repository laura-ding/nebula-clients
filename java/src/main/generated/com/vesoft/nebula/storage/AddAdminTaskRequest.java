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
public class AddAdminTaskRequest implements TBase, java.io.Serializable, Cloneable, Comparable<AddAdminTaskRequest> {
  private static final TStruct STRUCT_DESC = new TStruct("AddAdminTaskRequest");
  private static final TField CMD_FIELD_DESC = new TField("cmd", TType.I32, (short)1);
  private static final TField JOB_ID_FIELD_DESC = new TField("job_id", TType.I32, (short)2);
  private static final TField TASK_ID_FIELD_DESC = new TField("task_id", TType.I32, (short)3);
  private static final TField PARA_FIELD_DESC = new TField("para", TType.STRUCT, (short)4);
  private static final TField CONCURRENCY_FIELD_DESC = new TField("concurrency", TType.I32, (short)5);

  /**
   * 
   * @see com.vesoft.nebula.meta.AdminCmd
   */
  public int cmd;
  public int job_id;
  public int task_id;
  public TaskPara para;
  public int concurrency;
  public static final int CMD = 1;
  public static final int JOB_ID = 2;
  public static final int TASK_ID = 3;
  public static final int PARA = 4;
  public static final int CONCURRENCY = 5;
  public static boolean DEFAULT_PRETTY_PRINT = true;

  // isset id assignments
  private static final int __CMD_ISSET_ID = 0;
  private static final int __JOB_ID_ISSET_ID = 1;
  private static final int __TASK_ID_ISSET_ID = 2;
  private static final int __CONCURRENCY_ISSET_ID = 3;
  private BitSet __isset_bit_vector = new BitSet(4);

  public static final Map<Integer, FieldMetaData> metaDataMap;
  static {
    Map<Integer, FieldMetaData> tmpMetaDataMap = new HashMap<Integer, FieldMetaData>();
    tmpMetaDataMap.put(CMD, new FieldMetaData("cmd", TFieldRequirementType.DEFAULT, 
        new FieldValueMetaData(TType.I32)));
    tmpMetaDataMap.put(JOB_ID, new FieldMetaData("job_id", TFieldRequirementType.DEFAULT, 
        new FieldValueMetaData(TType.I32)));
    tmpMetaDataMap.put(TASK_ID, new FieldMetaData("task_id", TFieldRequirementType.DEFAULT, 
        new FieldValueMetaData(TType.I32)));
    tmpMetaDataMap.put(PARA, new FieldMetaData("para", TFieldRequirementType.DEFAULT, 
        new StructMetaData(TType.STRUCT, TaskPara.class)));
    tmpMetaDataMap.put(CONCURRENCY, new FieldMetaData("concurrency", TFieldRequirementType.OPTIONAL, 
        new FieldValueMetaData(TType.I32)));
    metaDataMap = Collections.unmodifiableMap(tmpMetaDataMap);
  }

  static {
    FieldMetaData.addStructMetaDataMap(AddAdminTaskRequest.class, metaDataMap);
  }

  public AddAdminTaskRequest() {
  }

  public AddAdminTaskRequest(
    int cmd,
    int job_id,
    int task_id,
    TaskPara para)
  {
    this();
    this.cmd = cmd;
    setCmdIsSet(true);
    this.job_id = job_id;
    setJob_idIsSet(true);
    this.task_id = task_id;
    setTask_idIsSet(true);
    this.para = para;
  }

  public AddAdminTaskRequest(
    int cmd,
    int job_id,
    int task_id,
    TaskPara para,
    int concurrency)
  {
    this();
    this.cmd = cmd;
    setCmdIsSet(true);
    this.job_id = job_id;
    setJob_idIsSet(true);
    this.task_id = task_id;
    setTask_idIsSet(true);
    this.para = para;
    this.concurrency = concurrency;
    setConcurrencyIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public AddAdminTaskRequest(AddAdminTaskRequest other) {
    __isset_bit_vector.clear();
    __isset_bit_vector.or(other.__isset_bit_vector);
    this.cmd = TBaseHelper.deepCopy(other.cmd);
    this.job_id = TBaseHelper.deepCopy(other.job_id);
    this.task_id = TBaseHelper.deepCopy(other.task_id);
    if (other.isSetPara()) {
      this.para = TBaseHelper.deepCopy(other.para);
    }
    this.concurrency = TBaseHelper.deepCopy(other.concurrency);
  }

  public AddAdminTaskRequest deepCopy() {
    return new AddAdminTaskRequest(this);
  }

  @Deprecated
  public AddAdminTaskRequest clone() {
    return new AddAdminTaskRequest(this);
  }

  /**
   * 
   * @see com.vesoft.nebula.meta.AdminCmd
   */
  public int  getCmd() {
    return this.cmd;
  }

  /**
   * 
   * @see com.vesoft.nebula.meta.AdminCmd
   */
  public AddAdminTaskRequest setCmd(int cmd) {
    this.cmd = cmd;
    setCmdIsSet(true);
    return this;
  }

  public void unsetCmd() {
    __isset_bit_vector.clear(__CMD_ISSET_ID);
  }

  // Returns true if field cmd is set (has been assigned a value) and false otherwise
  public boolean isSetCmd() {
    return __isset_bit_vector.get(__CMD_ISSET_ID);
  }

  public void setCmdIsSet(boolean value) {
    __isset_bit_vector.set(__CMD_ISSET_ID, value);
  }

  public int  getJob_id() {
    return this.job_id;
  }

  public AddAdminTaskRequest setJob_id(int job_id) {
    this.job_id = job_id;
    setJob_idIsSet(true);
    return this;
  }

  public void unsetJob_id() {
    __isset_bit_vector.clear(__JOB_ID_ISSET_ID);
  }

  // Returns true if field job_id is set (has been assigned a value) and false otherwise
  public boolean isSetJob_id() {
    return __isset_bit_vector.get(__JOB_ID_ISSET_ID);
  }

  public void setJob_idIsSet(boolean value) {
    __isset_bit_vector.set(__JOB_ID_ISSET_ID, value);
  }

  public int  getTask_id() {
    return this.task_id;
  }

  public AddAdminTaskRequest setTask_id(int task_id) {
    this.task_id = task_id;
    setTask_idIsSet(true);
    return this;
  }

  public void unsetTask_id() {
    __isset_bit_vector.clear(__TASK_ID_ISSET_ID);
  }

  // Returns true if field task_id is set (has been assigned a value) and false otherwise
  public boolean isSetTask_id() {
    return __isset_bit_vector.get(__TASK_ID_ISSET_ID);
  }

  public void setTask_idIsSet(boolean value) {
    __isset_bit_vector.set(__TASK_ID_ISSET_ID, value);
  }

  public TaskPara  getPara() {
    return this.para;
  }

  public AddAdminTaskRequest setPara(TaskPara para) {
    this.para = para;
    return this;
  }

  public void unsetPara() {
    this.para = null;
  }

  // Returns true if field para is set (has been assigned a value) and false otherwise
  public boolean isSetPara() {
    return this.para != null;
  }

  public void setParaIsSet(boolean value) {
    if (!value) {
      this.para = null;
    }
  }

  public int  getConcurrency() {
    return this.concurrency;
  }

  public AddAdminTaskRequest setConcurrency(int concurrency) {
    this.concurrency = concurrency;
    setConcurrencyIsSet(true);
    return this;
  }

  public void unsetConcurrency() {
    __isset_bit_vector.clear(__CONCURRENCY_ISSET_ID);
  }

  // Returns true if field concurrency is set (has been assigned a value) and false otherwise
  public boolean isSetConcurrency() {
    return __isset_bit_vector.get(__CONCURRENCY_ISSET_ID);
  }

  public void setConcurrencyIsSet(boolean value) {
    __isset_bit_vector.set(__CONCURRENCY_ISSET_ID, value);
  }

  public void setFieldValue(int fieldID, Object value) {
    switch (fieldID) {
    case CMD:
      if (value == null) {
        unsetCmd();
      } else {
        setCmd((Integer)value);
      }
      break;

    case JOB_ID:
      if (value == null) {
        unsetJob_id();
      } else {
        setJob_id((Integer)value);
      }
      break;

    case TASK_ID:
      if (value == null) {
        unsetTask_id();
      } else {
        setTask_id((Integer)value);
      }
      break;

    case PARA:
      if (value == null) {
        unsetPara();
      } else {
        setPara((TaskPara)value);
      }
      break;

    case CONCURRENCY:
      if (value == null) {
        unsetConcurrency();
      } else {
        setConcurrency((Integer)value);
      }
      break;

    default:
      throw new IllegalArgumentException("Field " + fieldID + " doesn't exist!");
    }
  }

  public Object getFieldValue(int fieldID) {
    switch (fieldID) {
    case CMD:
      return getCmd();

    case JOB_ID:
      return new Integer(getJob_id());

    case TASK_ID:
      return new Integer(getTask_id());

    case PARA:
      return getPara();

    case CONCURRENCY:
      return new Integer(getConcurrency());

    default:
      throw new IllegalArgumentException("Field " + fieldID + " doesn't exist!");
    }
  }

  // Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise
  public boolean isSet(int fieldID) {
    switch (fieldID) {
    case CMD:
      return isSetCmd();
    case JOB_ID:
      return isSetJob_id();
    case TASK_ID:
      return isSetTask_id();
    case PARA:
      return isSetPara();
    case CONCURRENCY:
      return isSetConcurrency();
    default:
      throw new IllegalArgumentException("Field " + fieldID + " doesn't exist!");
    }
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof AddAdminTaskRequest)
      return this.equals((AddAdminTaskRequest)that);
    return false;
  }

  public boolean equals(AddAdminTaskRequest that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_cmd = true;
    boolean that_present_cmd = true;
    if (this_present_cmd || that_present_cmd) {
      if (!(this_present_cmd && that_present_cmd))
        return false;
      if (!TBaseHelper.equalsNobinary(this.cmd, that.cmd))
        return false;
    }

    boolean this_present_job_id = true;
    boolean that_present_job_id = true;
    if (this_present_job_id || that_present_job_id) {
      if (!(this_present_job_id && that_present_job_id))
        return false;
      if (!TBaseHelper.equalsNobinary(this.job_id, that.job_id))
        return false;
    }

    boolean this_present_task_id = true;
    boolean that_present_task_id = true;
    if (this_present_task_id || that_present_task_id) {
      if (!(this_present_task_id && that_present_task_id))
        return false;
      if (!TBaseHelper.equalsNobinary(this.task_id, that.task_id))
        return false;
    }

    boolean this_present_para = true && this.isSetPara();
    boolean that_present_para = true && that.isSetPara();
    if (this_present_para || that_present_para) {
      if (!(this_present_para && that_present_para))
        return false;
      if (!TBaseHelper.equalsNobinary(this.para, that.para))
        return false;
    }

    boolean this_present_concurrency = true && this.isSetConcurrency();
    boolean that_present_concurrency = true && that.isSetConcurrency();
    if (this_present_concurrency || that_present_concurrency) {
      if (!(this_present_concurrency && that_present_concurrency))
        return false;
      if (!TBaseHelper.equalsNobinary(this.concurrency, that.concurrency))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    HashCodeBuilder builder = new HashCodeBuilder();

    boolean present_cmd = true;
    builder.append(present_cmd);
    if (present_cmd)
      builder.append(cmd);

    boolean present_job_id = true;
    builder.append(present_job_id);
    if (present_job_id)
      builder.append(job_id);

    boolean present_task_id = true;
    builder.append(present_task_id);
    if (present_task_id)
      builder.append(task_id);

    boolean present_para = true && (isSetPara());
    builder.append(present_para);
    if (present_para)
      builder.append(para);

    boolean present_concurrency = true && (isSetConcurrency());
    builder.append(present_concurrency);
    if (present_concurrency)
      builder.append(concurrency);

    return builder.toHashCode();
  }

  @Override
  public int compareTo(AddAdminTaskRequest other) {
    if (other == null) {
      // See java.lang.Comparable docs
      throw new NullPointerException();
    }

    if (other == this) {
      return 0;
    }
    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetCmd()).compareTo(other.isSetCmd());
    if (lastComparison != 0) {
      return lastComparison;
    }
    lastComparison = TBaseHelper.compareTo(cmd, other.cmd);
    if (lastComparison != 0) {
      return lastComparison;
    }
    lastComparison = Boolean.valueOf(isSetJob_id()).compareTo(other.isSetJob_id());
    if (lastComparison != 0) {
      return lastComparison;
    }
    lastComparison = TBaseHelper.compareTo(job_id, other.job_id);
    if (lastComparison != 0) {
      return lastComparison;
    }
    lastComparison = Boolean.valueOf(isSetTask_id()).compareTo(other.isSetTask_id());
    if (lastComparison != 0) {
      return lastComparison;
    }
    lastComparison = TBaseHelper.compareTo(task_id, other.task_id);
    if (lastComparison != 0) {
      return lastComparison;
    }
    lastComparison = Boolean.valueOf(isSetPara()).compareTo(other.isSetPara());
    if (lastComparison != 0) {
      return lastComparison;
    }
    lastComparison = TBaseHelper.compareTo(para, other.para);
    if (lastComparison != 0) {
      return lastComparison;
    }
    lastComparison = Boolean.valueOf(isSetConcurrency()).compareTo(other.isSetConcurrency());
    if (lastComparison != 0) {
      return lastComparison;
    }
    lastComparison = TBaseHelper.compareTo(concurrency, other.concurrency);
    if (lastComparison != 0) {
      return lastComparison;
    }
    return 0;
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
        case CMD:
          if (field.type == TType.I32) {
            this.cmd = iprot.readI32();
            setCmdIsSet(true);
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case JOB_ID:
          if (field.type == TType.I32) {
            this.job_id = iprot.readI32();
            setJob_idIsSet(true);
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case TASK_ID:
          if (field.type == TType.I32) {
            this.task_id = iprot.readI32();
            setTask_idIsSet(true);
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case PARA:
          if (field.type == TType.STRUCT) {
            this.para = new TaskPara();
            this.para.read(iprot);
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case CONCURRENCY:
          if (field.type == TType.I32) {
            this.concurrency = iprot.readI32();
            setConcurrencyIsSet(true);
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
    oprot.writeFieldBegin(CMD_FIELD_DESC);
    oprot.writeI32(this.cmd);
    oprot.writeFieldEnd();
    oprot.writeFieldBegin(JOB_ID_FIELD_DESC);
    oprot.writeI32(this.job_id);
    oprot.writeFieldEnd();
    oprot.writeFieldBegin(TASK_ID_FIELD_DESC);
    oprot.writeI32(this.task_id);
    oprot.writeFieldEnd();
    if (this.para != null) {
      oprot.writeFieldBegin(PARA_FIELD_DESC);
      this.para.write(oprot);
      oprot.writeFieldEnd();
    }
    if (isSetConcurrency()) {
      oprot.writeFieldBegin(CONCURRENCY_FIELD_DESC);
      oprot.writeI32(this.concurrency);
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
    StringBuilder sb = new StringBuilder("AddAdminTaskRequest");
    sb.append(space);
    sb.append("(");
    sb.append(newLine);
    boolean first = true;

    sb.append(indentStr);
    sb.append("cmd");
    sb.append(space);
    sb.append(":").append(space);
    String cmd_name = com.vesoft.nebula.meta.AdminCmd.VALUES_TO_NAMES.get(this. getCmd());
    if (cmd_name != null) {
      sb.append(cmd_name);
      sb.append(" (");
    }
    sb.append(this. getCmd());
    if (cmd_name != null) {
      sb.append(")");
    }
    first = false;
    if (!first) sb.append("," + newLine);
    sb.append(indentStr);
    sb.append("job_id");
    sb.append(space);
    sb.append(":").append(space);
    sb.append(TBaseHelper.toString(this. getJob_id(), indent + 1, prettyPrint));
    first = false;
    if (!first) sb.append("," + newLine);
    sb.append(indentStr);
    sb.append("task_id");
    sb.append(space);
    sb.append(":").append(space);
    sb.append(TBaseHelper.toString(this. getTask_id(), indent + 1, prettyPrint));
    first = false;
    if (!first) sb.append("," + newLine);
    sb.append(indentStr);
    sb.append("para");
    sb.append(space);
    sb.append(":").append(space);
    if (this. getPara() == null) {
      sb.append("null");
    } else {
      sb.append(TBaseHelper.toString(this. getPara(), indent + 1, prettyPrint));
    }
    first = false;
    if (isSetConcurrency())
    {
      if (!first) sb.append("," + newLine);
      sb.append(indentStr);
      sb.append("concurrency");
      sb.append(space);
      sb.append(":").append(space);
      sb.append(TBaseHelper.toString(this. getConcurrency(), indent + 1, prettyPrint));
      first = false;
    }
    sb.append(newLine + TBaseHelper.reduceIndent(indentStr));
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws TException {
    // check for required fields
    // check that fields of type enum have valid values
    if (isSetCmd() && !com.vesoft.nebula.meta.AdminCmd.VALID_VALUES.contains(cmd)){
      throw new TProtocolException("The field 'cmd' has been assigned the invalid value " + cmd);
    }
  }

}

