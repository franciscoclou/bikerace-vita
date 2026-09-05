package com.amazonaws.javax.xml.stream;

import java.util.HashMap;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class PropertyManager {
    public static final int CONTEXT_READER = 1;
    public static final int CONTEXT_WRITER = 2;
    public static final String STAX_ENTITIES = "com.amazonaws.javax.xml.stream.entities";
    public static final String STAX_NOTATIONS = "com.amazonaws.javax.xml.stream.notations";
    private static final String STRING_INTERNING = "http://xml.org/sax/features/string-interning";
    HashMap supportedProps = new HashMap();

    public PropertyManager(int i) {
        switch (i) {
            case 1:
                initConfigurableReaderProperties();
                break;
            case 2:
                initWriterProps();
                break;
        }
    }

    public PropertyManager(PropertyManager propertyManager) {
        this.supportedProps.putAll(propertyManager.getProperties());
    }

    private HashMap getProperties() {
        return this.supportedProps;
    }

    private void initConfigurableReaderProperties() {
        this.supportedProps.put(XMLInputFactory.IS_NAMESPACE_AWARE, Boolean.TRUE);
        this.supportedProps.put(XMLInputFactory.IS_VALIDATING, Boolean.FALSE);
        this.supportedProps.put(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, Boolean.TRUE);
        this.supportedProps.put(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, Boolean.TRUE);
        this.supportedProps.put(XMLInputFactory.IS_COALESCING, Boolean.FALSE);
        this.supportedProps.put(XMLInputFactory.SUPPORT_DTD, Boolean.TRUE);
        this.supportedProps.put(XMLInputFactory.REPORTER, null);
        this.supportedProps.put(XMLInputFactory.RESOLVER, null);
        this.supportedProps.put(XMLInputFactory.ALLOCATOR, null);
        this.supportedProps.put(STAX_NOTATIONS, null);
        this.supportedProps.put(STRING_INTERNING, Boolean.TRUE);
        this.supportedProps.put("http://apache.org/xml/features/allow-java-encodings", Boolean.TRUE);
        this.supportedProps.put(Constants.REUSE_INSTANCE, Boolean.FALSE);
        this.supportedProps.put("http://java.sun.com/xml/stream/properties/report-cdata-event", Boolean.FALSE);
        this.supportedProps.put("http://java.sun.com/xml/stream/properties/ignore-external-dtd", Boolean.FALSE);
        this.supportedProps.put("http://apache.org/xml/features/validation/warn-on-duplicate-attdef", Boolean.FALSE);
        this.supportedProps.put("http://apache.org/xml/features/warn-on-duplicate-entitydef", Boolean.FALSE);
        this.supportedProps.put("http://apache.org/xml/features/validation/warn-on-undeclared-elemdef", Boolean.FALSE);
        this.supportedProps.put("http://java.sun.com/xml/stream/properties/implementation-name", "sjsxp");
    }

    private void initWriterProps() {
        this.supportedProps.put(XMLOutputFactory.IS_REPAIRING_NAMESPACES, Boolean.FALSE);
        this.supportedProps.put(Constants.ESCAPE_CHARACTERS, Boolean.TRUE);
        this.supportedProps.put(Constants.REUSE_INSTANCE, Boolean.FALSE);
        this.supportedProps.put("http://java.sun.com/xml/stream/properties/implementation-name", "sjsxp");
        this.supportedProps.put("http://java.sun.com/xml/stream/properties/outputstream", null);
    }

    public boolean containsProperty(String str) {
        return this.supportedProps.containsKey(str);
    }

    public Object getProperty(String str) {
        return this.supportedProps.get(str);
    }

    public void setProperty(String str, Object obj) {
        String str2;
        if (str == XMLInputFactory.IS_NAMESPACE_AWARE || str.equals(XMLInputFactory.IS_NAMESPACE_AWARE)) {
            str2 = "http://apache.org/xml/features/namespaces";
        } else {
            if (str == XMLInputFactory.IS_VALIDATING || str.equals(XMLInputFactory.IS_VALIDATING)) {
                if ((obj instanceof Boolean) && ((Boolean) obj).booleanValue()) {
                    throw new IllegalArgumentException("true value of isValidating not supported");
                }
            } else if (str == STRING_INTERNING || str.equals(STRING_INTERNING)) {
                if ((obj instanceof Boolean) && !((Boolean) obj).booleanValue()) {
                    throw new IllegalArgumentException("false value of http://xml.org/sax/features/string-interningfeature is not supported");
                }
            } else if (str == XMLInputFactory.RESOLVER || str.equals(XMLInputFactory.RESOLVER)) {
                this.supportedProps.put("http://apache.org/xml/properties/internal/stax-entity-resolver", new StaxEntityResolverWrapper((XMLResolver) obj));
            }
            str2 = null;
        }
        this.supportedProps.put(str, obj);
        if (str2 != null) {
            this.supportedProps.put(str2, obj);
        }
    }

    public String toString() {
        return this.supportedProps.toString();
    }
}
