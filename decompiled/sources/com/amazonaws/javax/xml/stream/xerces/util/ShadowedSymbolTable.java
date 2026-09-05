package com.amazonaws.javax.xml.stream.xerces.util;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class ShadowedSymbolTable extends SymbolTable {
    protected SymbolTable fSymbolTable;

    public ShadowedSymbolTable(SymbolTable symbolTable) {
        this.fSymbolTable = symbolTable;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.util.SymbolTable
    public String addSymbol(String str) {
        return this.fSymbolTable.containsSymbol(str) ? this.fSymbolTable.addSymbol(str) : super.addSymbol(str);
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.util.SymbolTable
    public String addSymbol(char[] cArr, int i, int i2) {
        return this.fSymbolTable.containsSymbol(cArr, i, i2) ? this.fSymbolTable.addSymbol(cArr, i, i2) : super.addSymbol(cArr, i, i2);
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.util.SymbolTable
    public int hash(String str) {
        return this.fSymbolTable.hash(str);
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.util.SymbolTable
    public int hash(char[] cArr, int i, int i2) {
        return this.fSymbolTable.hash(cArr, i, i2);
    }
}
