package org.codehaus.jackson.impl;

import java.io.Reader;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.io.IOContext;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class ReaderBasedNumericParser extends ReaderBasedParserBase {
    public ReaderBasedNumericParser(IOContext iOContext, int i, Reader reader) {
        super(iOContext, i, reader);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InitCodeVariables
        jadx.core.utils.exceptions.JadxRuntimeException: Several immutable types in one variable: [int, char], vars: [r14v0 ??, r14v1 ??, r14v2 ??]
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVarType(InitCodeVariables.java:107)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:83)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:74)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:57)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:45)
        	at jadx.core.dex.visitors.InitCodeVariables.visit(InitCodeVariables.java:29)
        */
    protected final org.codehaus.jackson.JsonToken parseNumberText(
    /*  JADX ERROR: JadxRuntimeException in pass: InitCodeVariables
        jadx.core.utils.exceptions.JadxRuntimeException: Several immutable types in one variable: [int, char], vars: [r14v0 ??, r14v1 ??, r14v2 ??]
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVarType(InitCodeVariables.java:107)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:83)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:74)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:57)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:45)
        */
    /*  JADX ERROR: Method generation error
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r14v0 ??
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
        	at jadx.core.codegen.MethodGen.addMethodArguments(MethodGen.java:215)
        	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:150)
        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:415)
        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:345)
        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:299)
        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
        */

    private final JsonToken parseNumberText2(boolean z) {
        int i;
        char nextChar;
        boolean z2;
        int i2;
        int i3;
        char c;
        char[] cArr;
        int i4;
        char c2;
        char[] cArrFinishCurrentSegment;
        int i5;
        boolean z3;
        char nextChar2;
        int i6;
        char nextChar3;
        char c3;
        int i7;
        int i8;
        boolean z4;
        boolean z5;
        int i9;
        int i10 = 0;
        char[] cArrEmptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        if (z) {
            cArrEmptyAndGetCurrentSegment[0] = '-';
            i = 1;
        } else {
            i = 0;
        }
        if (this._inputPtr < this._inputEnd) {
            char[] cArr2 = this._inputBuffer;
            int i11 = this._inputPtr;
            this._inputPtr = i11 + 1;
            nextChar = cArr2[i11];
        } else {
            nextChar = getNextChar("No digit following minus sign");
        }
        if (nextChar == '0') {
            nextChar = _verifyNoLeadingZeroes();
        }
        int i12 = 0;
        char c4 = nextChar;
        char[] cArrFinishCurrentSegment2 = cArrEmptyAndGetCurrentSegment;
        char c5 = c4;
        while (true) {
            if (c5 >= '0' && c5 <= '9') {
                i12++;
                if (i >= cArrFinishCurrentSegment2.length) {
                    cArrFinishCurrentSegment2 = this._textBuffer.finishCurrentSegment();
                    i = 0;
                }
                int i13 = i + 1;
                cArrFinishCurrentSegment2[i] = c5;
                if (this._inputPtr >= this._inputEnd && !loadMore()) {
                    z2 = true;
                    c = 0;
                    i2 = i12;
                    cArr = cArrFinishCurrentSegment2;
                    i3 = i13;
                    break;
                }
                char[] cArr3 = this._inputBuffer;
                int i14 = this._inputPtr;
                this._inputPtr = i14 + 1;
                c5 = cArr3[i14];
                i = i13;
            } else {
                z2 = false;
                i2 = i12;
                i3 = i;
                c = c5;
                cArr = cArrFinishCurrentSegment2;
                break;
            }
        }
        if (i2 == 0) {
            reportInvalidNumber("Missing integer part (next char " + _getCharDesc(c) + ")");
        }
        if (c == '.') {
            int i15 = i3 + 1;
            cArr[i3] = c;
            char[] cArrFinishCurrentSegment3 = cArr;
            int i16 = i15;
            char c6 = c;
            int i17 = 0;
            while (true) {
                if (this._inputPtr >= this._inputEnd && !loadMore()) {
                    c2 = c6;
                    z5 = true;
                    break;
                }
                char[] cArr4 = this._inputBuffer;
                int i18 = this._inputPtr;
                this._inputPtr = i18 + 1;
                c6 = cArr4[i18];
                if (c6 < '0') {
                    c2 = c6;
                    z5 = z2;
                    break;
                }
                if (c6 > '9') {
                    c2 = c6;
                    z5 = z2;
                    break;
                }
                i17++;
                if (i16 >= cArrFinishCurrentSegment3.length) {
                    cArrFinishCurrentSegment3 = this._textBuffer.finishCurrentSegment();
                    i9 = 0;
                } else {
                    i9 = i16;
                }
                i16 = i9 + 1;
                cArrFinishCurrentSegment3[i9] = c6;
            }
            if (i17 == 0) {
                reportUnexpectedNumberChar(c2, "Decimal point not followed by a digit");
            }
            i4 = i17;
            i5 = i16;
            boolean z6 = z5;
            cArrFinishCurrentSegment = cArrFinishCurrentSegment3;
            z3 = z6;
        } else {
            i4 = 0;
            c2 = c;
            cArrFinishCurrentSegment = cArr;
            i5 = i3;
            z3 = z2;
        }
        if (c2 == 'e' || c2 == 'E') {
            if (i5 >= cArrFinishCurrentSegment.length) {
                cArrFinishCurrentSegment = this._textBuffer.finishCurrentSegment();
                i5 = 0;
            }
            int i19 = i5 + 1;
            cArrFinishCurrentSegment[i5] = c2;
            if (this._inputPtr < this._inputEnd) {
                char[] cArr5 = this._inputBuffer;
                int i20 = this._inputPtr;
                this._inputPtr = i20 + 1;
                nextChar2 = cArr5[i20];
            } else {
                nextChar2 = getNextChar("expected a digit for number exponent");
            }
            if (nextChar2 == '-' || nextChar2 == '+') {
                if (i19 >= cArrFinishCurrentSegment.length) {
                    cArrFinishCurrentSegment = this._textBuffer.finishCurrentSegment();
                    i6 = 0;
                } else {
                    i6 = i19;
                }
                int i21 = i6 + 1;
                cArrFinishCurrentSegment[i6] = nextChar2;
                if (this._inputPtr < this._inputEnd) {
                    char[] cArr6 = this._inputBuffer;
                    int i22 = this._inputPtr;
                    this._inputPtr = i22 + 1;
                    nextChar3 = cArr6[i22];
                } else {
                    nextChar3 = getNextChar("expected a digit for number exponent");
                }
                c3 = nextChar3;
                i7 = i21;
            } else {
                c3 = nextChar2;
                i7 = i19;
            }
            while (true) {
                if (c3 <= '9' && c3 >= '0') {
                    i10++;
                    if (i7 >= cArrFinishCurrentSegment.length) {
                        cArrFinishCurrentSegment = this._textBuffer.finishCurrentSegment();
                        i7 = 0;
                    }
                    int i23 = i7 + 1;
                    cArrFinishCurrentSegment[i7] = c3;
                    if (this._inputPtr >= this._inputEnd && !loadMore()) {
                        i10 = i10;
                        z4 = true;
                        i8 = i23;
                        break;
                    }
                    char[] cArr7 = this._inputBuffer;
                    int i24 = this._inputPtr;
                    this._inputPtr = i24 + 1;
                    c3 = cArr7[i24];
                    i7 = i23;
                } else {
                    i10 = i10;
                    i8 = i7;
                    z4 = z3;
                    break;
                }
            }
            if (i10 == 0) {
                reportUnexpectedNumberChar(c3, "Exponent indicator not followed by a digit");
            }
        } else {
            i8 = i5;
            z4 = z3;
        }
        if (!z4) {
            this._inputPtr--;
        }
        this._textBuffer.setCurrentLength(i8);
        return reset(z, i2, i4, i10);
    }

    private final char _verifyNoLeadingZeroes() {
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            return '0';
        }
        char c = this._inputBuffer[this._inputPtr];
        if (c < '0' || c > '9') {
            return '0';
        }
        if (!isEnabled(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS)) {
            reportInvalidNumber("Leading zeroes not allowed");
        }
        this._inputPtr++;
        if (c == '0') {
            do {
                if (this._inputPtr < this._inputEnd || loadMore()) {
                    c = this._inputBuffer[this._inputPtr];
                    if (c < '0' || c > '9') {
                        return '0';
                    }
                    this._inputPtr++;
                } else {
                    return c;
                }
            } while (c == '0');
            return c;
        }
        return c;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InitCodeVariables
        jadx.core.utils.exceptions.JadxRuntimeException: Several immutable types in one variable: [int, char], vars: [r9v0 ??, r9v1 ??, r9v2 ??]
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVarType(InitCodeVariables.java:107)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:83)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:74)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:57)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:45)
        	at jadx.core.dex.visitors.InitCodeVariables.visit(InitCodeVariables.java:29)
        */
    protected org.codehaus.jackson.JsonToken _handleInvalidNumberStart(
    /*  JADX ERROR: JadxRuntimeException in pass: InitCodeVariables
        jadx.core.utils.exceptions.JadxRuntimeException: Several immutable types in one variable: [int, char], vars: [r9v0 ??, r9v1 ??, r9v2 ??]
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVarType(InitCodeVariables.java:107)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:83)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:74)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:57)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:45)
        */
    /*  JADX ERROR: Method generation error
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r9v0 ??
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
        	at jadx.core.codegen.MethodGen.addMethodArguments(MethodGen.java:215)
        	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:150)
        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:415)
        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:345)
        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:299)
        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
        */
}
