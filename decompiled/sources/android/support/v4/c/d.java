package android.support.v4.c;

import android.util.Log;
import com.amazonaws.javax.xml.stream.xerces.util.XMLChar;
import java.io.Writer;

/* JADX INFO: compiled from: LogWriter.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class d extends Writer {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f28a;
    private StringBuilder b = new StringBuilder(XMLChar.MASK_NCNAME);

    public d(String str) {
        this.f28a = str;
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        a();
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() {
        a();
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            char c = cArr[i + i3];
            if (c == '\n') {
                a();
            } else {
                this.b.append(c);
            }
        }
    }

    private void a() {
        if (this.b.length() > 0) {
            Log.d(this.f28a, this.b.toString());
            this.b.delete(0, this.b.length());
        }
    }
}
