package com.amazonaws.javax.xml.stream.util;

import java.lang.ref.SoftReference;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ThreadLocalBufferAllocator {
    private static ThreadLocal tlba = new ThreadLocal();

    public static BufferAllocator getBufferAllocator() {
        SoftReference softReference = (SoftReference) tlba.get();
        if (softReference == null || softReference.get() == null) {
            softReference = new SoftReference(new BufferAllocator());
            tlba.set(softReference);
        }
        return (BufferAllocator) softReference.get();
    }
}
