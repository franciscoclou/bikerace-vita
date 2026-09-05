package com.amazonaws.f.a;

import java.io.IOException;
import java.io.Writer;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class f {
    protected Writer b;
    private boolean c = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected char f121a = 'i';
    private c[] d = new c[20];
    private int e = 0;

    public f(Writer writer) {
        this.b = writer;
    }

    private f a(char c, char c2) throws b {
        if (this.f121a != c) {
            throw new b(c == 'a' ? "Misplaced endArray." : "Misplaced endObject.");
        }
        a(c);
        try {
            this.b.write(c2);
            this.c = true;
            return this;
        } catch (IOException e) {
            throw new b(e);
        }
    }

    private void a(char c) throws b {
        char c2 = 'a';
        if (this.e <= 0) {
            throw new b("Nesting error.");
        }
        if ((this.d[this.e + (-1)] == null ? 'a' : 'k') != c) {
            throw new b("Nesting error.");
        }
        this.e--;
        if (this.e == 0) {
            c2 = 'd';
        } else if (this.d[this.e - 1] != null) {
            c2 = 'k';
        }
        this.f121a = c2;
    }

    private void a(c cVar) throws b {
        if (this.e >= 20) {
            throw new b("Nesting too deep.");
        }
        this.d[this.e] = cVar;
        this.f121a = cVar == null ? 'a' : 'k';
        this.e++;
    }

    private f b(String str) throws b {
        if (str == null) {
            throw new b("Null pointer");
        }
        if (this.f121a != 'o' && this.f121a != 'a') {
            throw new b("Value out of sequence.");
        }
        try {
            if (this.c && this.f121a == 'a') {
                this.b.write(44);
            }
            this.b.write(str);
            if (this.f121a == 'o') {
                this.f121a = 'k';
            }
            this.c = true;
            return this;
        } catch (IOException e) {
            throw new b(e);
        }
    }

    public f a() throws b {
        if (this.f121a != 'i' && this.f121a != 'o' && this.f121a != 'a') {
            throw new b("Misplaced array.");
        }
        a((c) null);
        b("[");
        this.c = false;
        return this;
    }

    public f a(Object obj) {
        return b(c.b(obj));
    }

    public f a(String str) throws b {
        if (str == null) {
            throw new b("Null key.");
        }
        if (this.f121a != 'k') {
            throw new b("Misplaced key.");
        }
        try {
            this.d[this.e - 1].b(str, Boolean.TRUE);
            if (this.c) {
                this.b.write(44);
            }
            this.b.write(c.e(str));
            this.b.write(58);
            this.c = false;
            this.f121a = 'o';
            return this;
        } catch (IOException e) {
            throw new b(e);
        }
    }

    public f a(ByteBuffer byteBuffer) {
        byte[] bArr = new byte[byteBuffer.capacity()];
        byteBuffer.get(bArr, 0, bArr.length);
        return a((Object) com.amazonaws.f.c.a(bArr));
    }

    public f b() {
        return a('a', ']');
    }

    public f c() {
        return a('k', '}');
    }

    public f d() throws b {
        if (this.f121a == 'i') {
            this.f121a = 'o';
        }
        if (this.f121a != 'o' && this.f121a != 'a') {
            throw new b("Misplaced object.");
        }
        b("{");
        a(new c());
        this.c = false;
        return this;
    }
}
