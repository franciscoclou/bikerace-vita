package com.amazonaws.services.sqs.model;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public enum QueueAttributeName {
    Policy("Policy"),
    VisibilityTimeout("VisibilityTimeout"),
    MaximumMessageSize("MaximumMessageSize"),
    MessageRetentionPeriod("MessageRetentionPeriod"),
    ApproximateNumberOfMessages("ApproximateNumberOfMessages"),
    ApproximateNumberOfMessagesNotVisible("ApproximateNumberOfMessagesNotVisible"),
    CreatedTimestamp("CreatedTimestamp"),
    LastModifiedTimestamp("LastModifiedTimestamp"),
    QueueArn("QueueArn"),
    ApproximateNumberOfMessagesDelayed("ApproximateNumberOfMessagesDelayed"),
    DelaySeconds("DelaySeconds");

    private String value;

    QueueAttributeName(String str) {
        this.value = str;
    }

    public static QueueAttributeName fromValue(String str) {
        if (str == null || "".equals(str)) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        if ("Policy".equals(str)) {
            return Policy;
        }
        if ("VisibilityTimeout".equals(str)) {
            return VisibilityTimeout;
        }
        if ("MaximumMessageSize".equals(str)) {
            return MaximumMessageSize;
        }
        if ("MessageRetentionPeriod".equals(str)) {
            return MessageRetentionPeriod;
        }
        if ("ApproximateNumberOfMessages".equals(str)) {
            return ApproximateNumberOfMessages;
        }
        if ("ApproximateNumberOfMessagesNotVisible".equals(str)) {
            return ApproximateNumberOfMessagesNotVisible;
        }
        if ("CreatedTimestamp".equals(str)) {
            return CreatedTimestamp;
        }
        if ("LastModifiedTimestamp".equals(str)) {
            return LastModifiedTimestamp;
        }
        if ("QueueArn".equals(str)) {
            return QueueArn;
        }
        if ("ApproximateNumberOfMessagesDelayed".equals(str)) {
            return ApproximateNumberOfMessagesDelayed;
        }
        if ("DelaySeconds".equals(str)) {
            return DelaySeconds;
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }
}
