/**
 * Autogenerated by Thrift Compiler (0.13.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package fdu.daslab.thrift.master;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.13.0)", date = "2020-10-23")
public class TaskService {

  public interface Iface {

    public void submitPlan(String planName, String planDagPath) throws org.apache.thrift.TException;

  }

  public interface AsyncIface {

    public void submitPlan(String planName, String planDagPath, org.apache.thrift.async.AsyncMethodCallback<Void> resultHandler) throws org.apache.thrift.TException;

  }

  public static class Client extends org.apache.thrift.TServiceClient implements Iface {
    public static class Factory implements org.apache.thrift.TServiceClientFactory<Client> {
      public Factory() {}
      public Client getClient(org.apache.thrift.protocol.TProtocol prot) {
        return new Client(prot);
      }
      public Client getClient(org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TProtocol oprot) {
        return new Client(iprot, oprot);
      }
    }

    public Client(org.apache.thrift.protocol.TProtocol prot)
    {
      super(prot, prot);
    }

    public Client(org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TProtocol oprot) {
      super(iprot, oprot);
    }

    public void submitPlan(String planName, String planDagPath) throws org.apache.thrift.TException
    {
      send_submitPlan(planName, planDagPath);
      recv_submitPlan();
    }

    public void send_submitPlan(String planName, String planDagPath) throws org.apache.thrift.TException
    {
      submitPlan_args args = new submitPlan_args();
      args.setPlanName(planName);
      args.setPlanDagPath(planDagPath);
      sendBase("submitPlan", args);
    }

    public void recv_submitPlan() throws org.apache.thrift.TException
    {
      submitPlan_result result = new submitPlan_result();
      receiveBase(result, "submitPlan");
      return;
    }

  }
  public static class AsyncClient extends org.apache.thrift.async.TAsyncClient implements AsyncIface {
    public static class Factory implements org.apache.thrift.async.TAsyncClientFactory<AsyncClient> {
      private org.apache.thrift.async.TAsyncClientManager clientManager;
      private org.apache.thrift.protocol.TProtocolFactory protocolFactory;
      public Factory(org.apache.thrift.async.TAsyncClientManager clientManager, org.apache.thrift.protocol.TProtocolFactory protocolFactory) {
        this.clientManager = clientManager;
        this.protocolFactory = protocolFactory;
      }
      public AsyncClient getAsyncClient(org.apache.thrift.transport.TNonblockingTransport transport) {
        return new AsyncClient(protocolFactory, clientManager, transport);
      }
    }

    public AsyncClient(org.apache.thrift.protocol.TProtocolFactory protocolFactory, org.apache.thrift.async.TAsyncClientManager clientManager, org.apache.thrift.transport.TNonblockingTransport transport) {
      super(protocolFactory, clientManager, transport);
    }

    public void submitPlan(String planName, String planDagPath, org.apache.thrift.async.AsyncMethodCallback<Void> resultHandler) throws org.apache.thrift.TException {
      checkReady();
      submitPlan_call method_call = new submitPlan_call(planName, planDagPath, resultHandler, this, ___protocolFactory, ___transport);
      this.___currentMethod = method_call;
      ___manager.call(method_call);
    }

    public static class submitPlan_call extends org.apache.thrift.async.TAsyncMethodCall<Void> {
      private String planName;
      private String planDagPath;
      public submitPlan_call(String planName, String planDagPath, org.apache.thrift.async.AsyncMethodCallback<Void> resultHandler, org.apache.thrift.async.TAsyncClient client, org.apache.thrift.protocol.TProtocolFactory protocolFactory, org.apache.thrift.transport.TNonblockingTransport transport) throws org.apache.thrift.TException {
        super(client, protocolFactory, transport, resultHandler, false);
        this.planName = planName;
        this.planDagPath = planDagPath;
      }

      public void write_args(org.apache.thrift.protocol.TProtocol prot) throws org.apache.thrift.TException {
        prot.writeMessageBegin(new org.apache.thrift.protocol.TMessage("submitPlan", org.apache.thrift.protocol.TMessageType.CALL, 0));
        submitPlan_args args = new submitPlan_args();
        args.setPlanName(planName);
        args.setPlanDagPath(planDagPath);
        args.write(prot);
        prot.writeMessageEnd();
      }

      public Void getResult() throws org.apache.thrift.TException {
        if (getState() != State.RESPONSE_READ) {
          throw new IllegalStateException("Method call not finished!");
        }
        org.apache.thrift.transport.TMemoryInputTransport memoryTransport = new org.apache.thrift.transport.TMemoryInputTransport(getFrameBuffer().array());
        org.apache.thrift.protocol.TProtocol prot = client.getProtocolFactory().getProtocol(memoryTransport);
        return null;
      }
    }

  }

  public static class Processor<I extends Iface> extends org.apache.thrift.TBaseProcessor<I> implements org.apache.thrift.TProcessor {
    private static final org.slf4j.Logger _LOGGER = org.slf4j.LoggerFactory.getLogger(Processor.class.getName());
    public Processor(I iface) {
      super(iface, getProcessMap(new java.util.HashMap<String, org.apache.thrift.ProcessFunction<I, ? extends org.apache.thrift.TBase>>()));
    }

    protected Processor(I iface, java.util.Map<String, org.apache.thrift.ProcessFunction<I, ? extends org.apache.thrift.TBase>> processMap) {
      super(iface, getProcessMap(processMap));
    }

    private static <I extends Iface> java.util.Map<String,  org.apache.thrift.ProcessFunction<I, ? extends org.apache.thrift.TBase>> getProcessMap(java.util.Map<String, org.apache.thrift.ProcessFunction<I, ? extends  org.apache.thrift.TBase>> processMap) {
      processMap.put("submitPlan", new submitPlan());
      return processMap;
    }

    public static class submitPlan<I extends Iface> extends org.apache.thrift.ProcessFunction<I, submitPlan_args> {
      public submitPlan() {
        super("submitPlan");
      }

      public submitPlan_args getEmptyArgsInstance() {
        return new submitPlan_args();
      }

      protected boolean isOneway() {
        return false;
      }

      @Override
      protected boolean rethrowUnhandledExceptions() {
        return false;
      }

      public submitPlan_result getResult(I iface, submitPlan_args args) throws org.apache.thrift.TException {
        submitPlan_result result = new submitPlan_result();
        iface.submitPlan(args.planName, args.planDagPath);
        return result;
      }
    }

  }

  public static class AsyncProcessor<I extends AsyncIface> extends org.apache.thrift.TBaseAsyncProcessor<I> {
    private static final org.slf4j.Logger _LOGGER = org.slf4j.LoggerFactory.getLogger(AsyncProcessor.class.getName());
    public AsyncProcessor(I iface) {
      super(iface, getProcessMap(new java.util.HashMap<String, org.apache.thrift.AsyncProcessFunction<I, ? extends org.apache.thrift.TBase, ?>>()));
    }

    protected AsyncProcessor(I iface, java.util.Map<String,  org.apache.thrift.AsyncProcessFunction<I, ? extends  org.apache.thrift.TBase, ?>> processMap) {
      super(iface, getProcessMap(processMap));
    }

    private static <I extends AsyncIface> java.util.Map<String,  org.apache.thrift.AsyncProcessFunction<I, ? extends  org.apache.thrift.TBase,?>> getProcessMap(java.util.Map<String,  org.apache.thrift.AsyncProcessFunction<I, ? extends  org.apache.thrift.TBase, ?>> processMap) {
      processMap.put("submitPlan", new submitPlan());
      return processMap;
    }

    public static class submitPlan<I extends AsyncIface> extends org.apache.thrift.AsyncProcessFunction<I, submitPlan_args, Void> {
      public submitPlan() {
        super("submitPlan");
      }

      public submitPlan_args getEmptyArgsInstance() {
        return new submitPlan_args();
      }

      public org.apache.thrift.async.AsyncMethodCallback<Void> getResultHandler(final org.apache.thrift.server.AbstractNonblockingServer.AsyncFrameBuffer fb, final int seqid) {
        final org.apache.thrift.AsyncProcessFunction fcall = this;
        return new org.apache.thrift.async.AsyncMethodCallback<Void>() { 
          public void onComplete(Void o) {
            submitPlan_result result = new submitPlan_result();
            try {
              fcall.sendResponse(fb, result, org.apache.thrift.protocol.TMessageType.REPLY,seqid);
            } catch (org.apache.thrift.transport.TTransportException e) {
              _LOGGER.error("TTransportException writing to internal frame buffer", e);
              fb.close();
            } catch (Exception e) {
              _LOGGER.error("Exception writing to internal frame buffer", e);
              onError(e);
            }
          }
          public void onError(Exception e) {
            byte msgType = org.apache.thrift.protocol.TMessageType.REPLY;
            org.apache.thrift.TSerializable msg;
            submitPlan_result result = new submitPlan_result();
            if (e instanceof org.apache.thrift.transport.TTransportException) {
              _LOGGER.error("TTransportException inside handler", e);
              fb.close();
              return;
            } else if (e instanceof org.apache.thrift.TApplicationException) {
              _LOGGER.error("TApplicationException inside handler", e);
              msgType = org.apache.thrift.protocol.TMessageType.EXCEPTION;
              msg = (org.apache.thrift.TApplicationException)e;
            } else {
              _LOGGER.error("Exception inside handler", e);
              msgType = org.apache.thrift.protocol.TMessageType.EXCEPTION;
              msg = new org.apache.thrift.TApplicationException(org.apache.thrift.TApplicationException.INTERNAL_ERROR, e.getMessage());
            }
            try {
              fcall.sendResponse(fb,msg,msgType,seqid);
            } catch (Exception ex) {
              _LOGGER.error("Exception writing to internal frame buffer", ex);
              fb.close();
            }
          }
        };
      }

      protected boolean isOneway() {
        return false;
      }

      public void start(I iface, submitPlan_args args, org.apache.thrift.async.AsyncMethodCallback<Void> resultHandler) throws org.apache.thrift.TException {
        iface.submitPlan(args.planName, args.planDagPath,resultHandler);
      }
    }

  }

  public static class submitPlan_args implements org.apache.thrift.TBase<submitPlan_args, submitPlan_args._Fields>, java.io.Serializable, Cloneable, Comparable<submitPlan_args>   {
    private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("submitPlan_args");

    private static final org.apache.thrift.protocol.TField PLAN_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("planName", org.apache.thrift.protocol.TType.STRING, (short)1);
    private static final org.apache.thrift.protocol.TField PLAN_DAG_PATH_FIELD_DESC = new org.apache.thrift.protocol.TField("planDagPath", org.apache.thrift.protocol.TType.STRING, (short)2);

    private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new submitPlan_argsStandardSchemeFactory();
    private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new submitPlan_argsTupleSchemeFactory();

    public @org.apache.thrift.annotation.Nullable String planName; // required
    public @org.apache.thrift.annotation.Nullable String planDagPath; // required

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements org.apache.thrift.TFieldIdEnum {
      PLAN_NAME((short)1, "planName"),
      PLAN_DAG_PATH((short)2, "planDagPath");

      private static final java.util.Map<String, _Fields> byName = new java.util.HashMap<String, _Fields>();

      static {
        for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      @org.apache.thrift.annotation.Nullable
      public static _Fields findByThriftId(int fieldId) {
        switch(fieldId) {
          case 1: // PLAN_NAME
            return PLAN_NAME;
          case 2: // PLAN_DAG_PATH
            return PLAN_DAG_PATH;
          default:
            return null;
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      @org.apache.thrift.annotation.Nullable
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments
    public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
    static {
      java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
      tmpMap.put(_Fields.PLAN_NAME, new org.apache.thrift.meta_data.FieldMetaData("planName", org.apache.thrift.TFieldRequirementType.DEFAULT, 
          new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
      tmpMap.put(_Fields.PLAN_DAG_PATH, new org.apache.thrift.meta_data.FieldMetaData("planDagPath", org.apache.thrift.TFieldRequirementType.DEFAULT, 
          new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
      metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
      org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(submitPlan_args.class, metaDataMap);
    }

    public submitPlan_args() {
    }

    public submitPlan_args(
      String planName,
      String planDagPath)
    {
      this();
      this.planName = planName;
      this.planDagPath = planDagPath;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public submitPlan_args(submitPlan_args other) {
      if (other.isSetPlanName()) {
        this.planName = other.planName;
      }
      if (other.isSetPlanDagPath()) {
        this.planDagPath = other.planDagPath;
      }
    }

    public submitPlan_args deepCopy() {
      return new submitPlan_args(this);
    }

    @Override
    public void clear() {
      this.planName = null;
      this.planDagPath = null;
    }

    @org.apache.thrift.annotation.Nullable
    public String getPlanName() {
      return this.planName;
    }

    public submitPlan_args setPlanName(@org.apache.thrift.annotation.Nullable String planName) {
      this.planName = planName;
      return this;
    }

    public void unsetPlanName() {
      this.planName = null;
    }

    /** Returns true if field planName is set (has been assigned a value) and false otherwise */
    public boolean isSetPlanName() {
      return this.planName != null;
    }

    public void setPlanNameIsSet(boolean value) {
      if (!value) {
        this.planName = null;
      }
    }

    @org.apache.thrift.annotation.Nullable
    public String getPlanDagPath() {
      return this.planDagPath;
    }

    public submitPlan_args setPlanDagPath(@org.apache.thrift.annotation.Nullable String planDagPath) {
      this.planDagPath = planDagPath;
      return this;
    }

    public void unsetPlanDagPath() {
      this.planDagPath = null;
    }

    /** Returns true if field planDagPath is set (has been assigned a value) and false otherwise */
    public boolean isSetPlanDagPath() {
      return this.planDagPath != null;
    }

    public void setPlanDagPathIsSet(boolean value) {
      if (!value) {
        this.planDagPath = null;
      }
    }

    public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable Object value) {
      switch (field) {
      case PLAN_NAME:
        if (value == null) {
          unsetPlanName();
        } else {
          setPlanName((String)value);
        }
        break;

      case PLAN_DAG_PATH:
        if (value == null) {
          unsetPlanDagPath();
        } else {
          setPlanDagPath((String)value);
        }
        break;

      }
    }

    @org.apache.thrift.annotation.Nullable
    public Object getFieldValue(_Fields field) {
      switch (field) {
      case PLAN_NAME:
        return getPlanName();

      case PLAN_DAG_PATH:
        return getPlanDagPath();

      }
      throw new IllegalStateException();
    }

    /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      if (field == null) {
        throw new IllegalArgumentException();
      }

      switch (field) {
      case PLAN_NAME:
        return isSetPlanName();
      case PLAN_DAG_PATH:
        return isSetPlanDagPath();
      }
      throw new IllegalStateException();
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof submitPlan_args)
        return this.equals((submitPlan_args)that);
      return false;
    }

    public boolean equals(submitPlan_args that) {
      if (that == null)
        return false;
      if (this == that)
        return true;

      boolean this_present_planName = true && this.isSetPlanName();
      boolean that_present_planName = true && that.isSetPlanName();
      if (this_present_planName || that_present_planName) {
        if (!(this_present_planName && that_present_planName))
          return false;
        if (!this.planName.equals(that.planName))
          return false;
      }

      boolean this_present_planDagPath = true && this.isSetPlanDagPath();
      boolean that_present_planDagPath = true && that.isSetPlanDagPath();
      if (this_present_planDagPath || that_present_planDagPath) {
        if (!(this_present_planDagPath && that_present_planDagPath))
          return false;
        if (!this.planDagPath.equals(that.planDagPath))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      int hashCode = 1;

      hashCode = hashCode * 8191 + ((isSetPlanName()) ? 131071 : 524287);
      if (isSetPlanName())
        hashCode = hashCode * 8191 + planName.hashCode();

      hashCode = hashCode * 8191 + ((isSetPlanDagPath()) ? 131071 : 524287);
      if (isSetPlanDagPath())
        hashCode = hashCode * 8191 + planDagPath.hashCode();

      return hashCode;
    }

    @Override
    public int compareTo(submitPlan_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;

      lastComparison = Boolean.valueOf(isSetPlanName()).compareTo(other.isSetPlanName());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetPlanName()) {
        lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.planName, other.planName);
        if (lastComparison != 0) {
          return lastComparison;
        }
      }
      lastComparison = Boolean.valueOf(isSetPlanDagPath()).compareTo(other.isSetPlanDagPath());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetPlanDagPath()) {
        lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.planDagPath, other.planDagPath);
        if (lastComparison != 0) {
          return lastComparison;
        }
      }
      return 0;
    }

    @org.apache.thrift.annotation.Nullable
    public _Fields fieldForId(int fieldId) {
      return _Fields.findByThriftId(fieldId);
    }

    public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
      scheme(iprot).read(iprot, this);
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
      scheme(oprot).write(oprot, this);
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("submitPlan_args(");
      boolean first = true;

      sb.append("planName:");
      if (this.planName == null) {
        sb.append("null");
      } else {
        sb.append(this.planName);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("planDagPath:");
      if (this.planDagPath == null) {
        sb.append("null");
      } else {
        sb.append(this.planDagPath);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws org.apache.thrift.TException {
      // check for required fields
      // check for sub-struct validity
    }

    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
      try {
        write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
      } catch (org.apache.thrift.TException te) {
        throw new java.io.IOException(te);
      }
    }

    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
      try {
        read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
      } catch (org.apache.thrift.TException te) {
        throw new java.io.IOException(te);
      }
    }

    private static class submitPlan_argsStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
      public submitPlan_argsStandardScheme getScheme() {
        return new submitPlan_argsStandardScheme();
      }
    }

    private static class submitPlan_argsStandardScheme extends org.apache.thrift.scheme.StandardScheme<submitPlan_args> {

      public void read(org.apache.thrift.protocol.TProtocol iprot, submitPlan_args struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TField schemeField;
        iprot.readStructBegin();
        while (true)
        {
          schemeField = iprot.readFieldBegin();
          if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
            break;
          }
          switch (schemeField.id) {
            case 1: // PLAN_NAME
              if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
                struct.planName = iprot.readString();
                struct.setPlanNameIsSet(true);
              } else { 
                org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
              }
              break;
            case 2: // PLAN_DAG_PATH
              if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
                struct.planDagPath = iprot.readString();
                struct.setPlanDagPathIsSet(true);
              } else { 
                org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
              }
              break;
            default:
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
          }
          iprot.readFieldEnd();
        }
        iprot.readStructEnd();

        // check for required fields of primitive type, which can't be checked in the validate method
        struct.validate();
      }

      public void write(org.apache.thrift.protocol.TProtocol oprot, submitPlan_args struct) throws org.apache.thrift.TException {
        struct.validate();

        oprot.writeStructBegin(STRUCT_DESC);
        if (struct.planName != null) {
          oprot.writeFieldBegin(PLAN_NAME_FIELD_DESC);
          oprot.writeString(struct.planName);
          oprot.writeFieldEnd();
        }
        if (struct.planDagPath != null) {
          oprot.writeFieldBegin(PLAN_DAG_PATH_FIELD_DESC);
          oprot.writeString(struct.planDagPath);
          oprot.writeFieldEnd();
        }
        oprot.writeFieldStop();
        oprot.writeStructEnd();
      }

    }

    private static class submitPlan_argsTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
      public submitPlan_argsTupleScheme getScheme() {
        return new submitPlan_argsTupleScheme();
      }
    }

    private static class submitPlan_argsTupleScheme extends org.apache.thrift.scheme.TupleScheme<submitPlan_args> {

      @Override
      public void write(org.apache.thrift.protocol.TProtocol prot, submitPlan_args struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
        java.util.BitSet optionals = new java.util.BitSet();
        if (struct.isSetPlanName()) {
          optionals.set(0);
        }
        if (struct.isSetPlanDagPath()) {
          optionals.set(1);
        }
        oprot.writeBitSet(optionals, 2);
        if (struct.isSetPlanName()) {
          oprot.writeString(struct.planName);
        }
        if (struct.isSetPlanDagPath()) {
          oprot.writeString(struct.planDagPath);
        }
      }

      @Override
      public void read(org.apache.thrift.protocol.TProtocol prot, submitPlan_args struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
        java.util.BitSet incoming = iprot.readBitSet(2);
        if (incoming.get(0)) {
          struct.planName = iprot.readString();
          struct.setPlanNameIsSet(true);
        }
        if (incoming.get(1)) {
          struct.planDagPath = iprot.readString();
          struct.setPlanDagPathIsSet(true);
        }
      }
    }

    private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
      return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
    }
  }

  public static class submitPlan_result implements org.apache.thrift.TBase<submitPlan_result, submitPlan_result._Fields>, java.io.Serializable, Cloneable, Comparable<submitPlan_result>   {
    private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("submitPlan_result");


    private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new submitPlan_resultStandardSchemeFactory();
    private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new submitPlan_resultTupleSchemeFactory();


    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements org.apache.thrift.TFieldIdEnum {
;

      private static final java.util.Map<String, _Fields> byName = new java.util.HashMap<String, _Fields>();

      static {
        for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      @org.apache.thrift.annotation.Nullable
      public static _Fields findByThriftId(int fieldId) {
        switch(fieldId) {
          default:
            return null;
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      @org.apache.thrift.annotation.Nullable
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }
    public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
    static {
      java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
      metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
      org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(submitPlan_result.class, metaDataMap);
    }

    public submitPlan_result() {
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public submitPlan_result(submitPlan_result other) {
    }

    public submitPlan_result deepCopy() {
      return new submitPlan_result(this);
    }

    @Override
    public void clear() {
    }

    public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable Object value) {
      switch (field) {
      }
    }

    @org.apache.thrift.annotation.Nullable
    public Object getFieldValue(_Fields field) {
      switch (field) {
      }
      throw new IllegalStateException();
    }

    /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      if (field == null) {
        throw new IllegalArgumentException();
      }

      switch (field) {
      }
      throw new IllegalStateException();
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof submitPlan_result)
        return this.equals((submitPlan_result)that);
      return false;
    }

    public boolean equals(submitPlan_result that) {
      if (that == null)
        return false;
      if (this == that)
        return true;

      return true;
    }

    @Override
    public int hashCode() {
      int hashCode = 1;

      return hashCode;
    }

    @Override
    public int compareTo(submitPlan_result other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;

      return 0;
    }

    @org.apache.thrift.annotation.Nullable
    public _Fields fieldForId(int fieldId) {
      return _Fields.findByThriftId(fieldId);
    }

    public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
      scheme(iprot).read(iprot, this);
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
      scheme(oprot).write(oprot, this);
      }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("submitPlan_result(");
      boolean first = true;

      sb.append(")");
      return sb.toString();
    }

    public void validate() throws org.apache.thrift.TException {
      // check for required fields
      // check for sub-struct validity
    }

    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
      try {
        write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
      } catch (org.apache.thrift.TException te) {
        throw new java.io.IOException(te);
      }
    }

    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
      try {
        read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
      } catch (org.apache.thrift.TException te) {
        throw new java.io.IOException(te);
      }
    }

    private static class submitPlan_resultStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
      public submitPlan_resultStandardScheme getScheme() {
        return new submitPlan_resultStandardScheme();
      }
    }

    private static class submitPlan_resultStandardScheme extends org.apache.thrift.scheme.StandardScheme<submitPlan_result> {

      public void read(org.apache.thrift.protocol.TProtocol iprot, submitPlan_result struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TField schemeField;
        iprot.readStructBegin();
        while (true)
        {
          schemeField = iprot.readFieldBegin();
          if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
            break;
          }
          switch (schemeField.id) {
            default:
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
          }
          iprot.readFieldEnd();
        }
        iprot.readStructEnd();

        // check for required fields of primitive type, which can't be checked in the validate method
        struct.validate();
      }

      public void write(org.apache.thrift.protocol.TProtocol oprot, submitPlan_result struct) throws org.apache.thrift.TException {
        struct.validate();

        oprot.writeStructBegin(STRUCT_DESC);
        oprot.writeFieldStop();
        oprot.writeStructEnd();
      }

    }

    private static class submitPlan_resultTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
      public submitPlan_resultTupleScheme getScheme() {
        return new submitPlan_resultTupleScheme();
      }
    }

    private static class submitPlan_resultTupleScheme extends org.apache.thrift.scheme.TupleScheme<submitPlan_result> {

      @Override
      public void write(org.apache.thrift.protocol.TProtocol prot, submitPlan_result struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      }

      @Override
      public void read(org.apache.thrift.protocol.TProtocol prot, submitPlan_result struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      }
    }

    private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
      return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
    }
  }

}
