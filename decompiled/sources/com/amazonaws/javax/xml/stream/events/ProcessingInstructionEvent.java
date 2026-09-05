package com.amazonaws.javax.xml.stream.events;

import com.amazonaws.javax.xml.stream.Location;
import java.io.IOException;
import java.io.Writer;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ProcessingInstructionEvent extends DummyEvent implements ProcessingInstruction {
    private String fContent;
    private String fName;

    public ProcessingInstructionEvent() {
        init();
    }

    public ProcessingInstructionEvent(String str, String str2) {
        this(str, str2, null);
    }

    public ProcessingInstructionEvent(String str, String str2, Location location) {
        init();
        this.fName = str;
        this.fContent = str2;
        setLocation(location);
    }

    protected void init() {
        setEventType(3);
    }

    @Override // com.amazonaws.javax.xml.stream.events.ProcessingInstruction
    public String getTarget() {
        return this.fName;
    }

    public void setTarget(String str) {
        this.fName = str;
    }

    public void setData(String str) {
        this.fContent = str;
    }

    @Override // com.amazonaws.javax.xml.stream.events.ProcessingInstruction
    public String getData() {
        return this.fContent;
    }

    public String toString() {
        if (this.fContent != null && this.fName != null) {
            return new StringBuffer().append("<?").append(this.fName).append(" ").append(this.fContent).append("?>").toString();
        }
        if (this.fName != null) {
            return new StringBuffer().append("<?").append(this.fName).append("?>").toString();
        }
        if (this.fContent != null) {
            return new StringBuffer().append("<?").append(this.fContent).append("?>").toString();
        }
        return "<??>";
    }

    @Override // com.amazonaws.javax.xml.stream.events.DummyEvent
    protected void writeAsEncodedUnicodeEx(Writer writer) throws IOException {
        writer.write(toString());
    }
}
