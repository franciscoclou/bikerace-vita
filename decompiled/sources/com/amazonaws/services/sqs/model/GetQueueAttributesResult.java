package com.amazonaws.services.sqs.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class GetQueueAttributesResult {
    private Map<String, String> attributes;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetQueueAttributesResult)) {
            return false;
        }
        GetQueueAttributesResult getQueueAttributesResult = (GetQueueAttributesResult) obj;
        if ((getQueueAttributesResult.getAttributes() == null) ^ (getAttributes() == null)) {
            return false;
        }
        return getQueueAttributesResult.getAttributes() == null || getQueueAttributesResult.getAttributes().equals(getAttributes());
    }

    public Map<String, String> getAttributes() {
        if (this.attributes == null) {
            this.attributes = new HashMap();
        }
        return this.attributes;
    }

    public int hashCode() {
        return (getAttributes() == null ? 0 : getAttributes().hashCode()) + 31;
    }

    public void setAttributes(Map<String, String> map) {
        this.attributes = map;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (this.attributes != null) {
            sb.append("Attributes: " + this.attributes + ", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public GetQueueAttributesResult withAttributes(Map<String, String> map) {
        setAttributes(map);
        return this;
    }
}
