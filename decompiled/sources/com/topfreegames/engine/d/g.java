package com.topfreegames.engine.d;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;

/* JADX INFO: compiled from: MeshBuffer.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected FloatBuffer f1567a;
    protected FloatBuffer b;
    protected com.topfreegames.engine.b.h c;
    protected int d;

    public g(ArrayList<com.topfreegames.engine.b.i> arrayList, com.topfreegames.engine.b.h hVar) {
        this.f1567a = null;
        this.b = null;
        this.c = null;
        this.d = -1;
        this.c = hVar;
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(com.topfreegames.engine.a.b.b() * arrayList.size());
        byteBufferAllocateDirect.order(ByteOrder.nativeOrder());
        this.f1567a = byteBufferAllocateDirect.asFloatBuffer();
        ByteBuffer byteBufferAllocateDirect2 = ByteBuffer.allocateDirect(com.topfreegames.engine.a.b.b() * arrayList.size());
        byteBufferAllocateDirect2.order(ByteOrder.nativeOrder());
        this.b = byteBufferAllocateDirect2.asFloatBuffer();
        for (com.topfreegames.engine.b.i iVar : arrayList) {
            this.f1567a.put(iVar.f1560a.a());
            this.b.put(iVar.b.a());
        }
        this.f1567a.position(0);
        this.b.position(0);
        this.d = arrayList.size();
    }

    public FloatBuffer a() {
        return this.f1567a;
    }

    public FloatBuffer b() {
        return this.b;
    }

    public com.topfreegames.engine.b.h c() {
        return this.c;
    }

    public int d() {
        return this.d;
    }
}
