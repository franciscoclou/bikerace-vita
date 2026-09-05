package com.amazonaws.javax.xml.stream.xerces.util;

import com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLErrorHandler;
import com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLParseException;
import java.io.PrintWriter;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class DefaultErrorHandler implements XMLErrorHandler {
    protected PrintWriter fOut;

    public DefaultErrorHandler() {
        this(new PrintWriter(System.err));
    }

    public DefaultErrorHandler(PrintWriter printWriter) {
        this.fOut = printWriter;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLErrorHandler
    public void warning(String str, String str2, XMLParseException xMLParseException) {
        printError("Warning", xMLParseException);
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLErrorHandler
    public void error(String str, String str2, XMLParseException xMLParseException) {
        printError("Error", xMLParseException);
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLErrorHandler
    public void fatalError(String str, String str2, XMLParseException xMLParseException) {
        printError("Fatal Error", xMLParseException);
        throw xMLParseException;
    }

    private void printError(String str, XMLParseException xMLParseException) {
        this.fOut.print("[");
        this.fOut.print(str);
        this.fOut.print("] ");
        String expandedSystemId = xMLParseException.getExpandedSystemId();
        if (expandedSystemId != null) {
            int iLastIndexOf = expandedSystemId.lastIndexOf(47);
            if (iLastIndexOf != -1) {
                expandedSystemId = expandedSystemId.substring(iLastIndexOf + 1);
            }
            this.fOut.print(expandedSystemId);
        }
        this.fOut.print(':');
        this.fOut.print(xMLParseException.getLineNumber());
        this.fOut.print(':');
        this.fOut.print(xMLParseException.getColumnNumber());
        this.fOut.print(": ");
        this.fOut.print(xMLParseException.getMessage());
        this.fOut.println();
        this.fOut.flush();
    }
}
