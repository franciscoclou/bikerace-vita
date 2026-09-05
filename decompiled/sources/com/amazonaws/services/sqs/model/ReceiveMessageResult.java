package com.amazonaws.services.sqs.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ReceiveMessageResult {
    private List<Message> messages;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ReceiveMessageResult)) {
            return false;
        }
        ReceiveMessageResult receiveMessageResult = (ReceiveMessageResult) obj;
        if ((receiveMessageResult.getMessages() == null) ^ (getMessages() == null)) {
            return false;
        }
        return receiveMessageResult.getMessages() == null || receiveMessageResult.getMessages().equals(getMessages());
    }

    public List<Message> getMessages() {
        if (this.messages == null) {
            this.messages = new ArrayList();
        }
        return this.messages;
    }

    public int hashCode() {
        return (getMessages() == null ? 0 : getMessages().hashCode()) + 31;
    }

    public void setMessages(Collection<Message> collection) {
        if (collection == null) {
            this.messages = null;
            return;
        }
        ArrayList arrayList = new ArrayList(collection.size());
        arrayList.addAll(collection);
        this.messages = arrayList;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (this.messages != null) {
            sb.append("Messages: " + this.messages + ", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public ReceiveMessageResult withMessages(Collection<Message> collection) {
        if (collection == null) {
            this.messages = null;
        } else {
            ArrayList arrayList = new ArrayList(collection.size());
            arrayList.addAll(collection);
            this.messages = arrayList;
        }
        return this;
    }

    public ReceiveMessageResult withMessages(Message... messageArr) {
        if (getMessages() == null) {
            setMessages(new ArrayList(messageArr.length));
        }
        for (Message message : messageArr) {
            getMessages().add(message);
        }
        return this;
    }
}
