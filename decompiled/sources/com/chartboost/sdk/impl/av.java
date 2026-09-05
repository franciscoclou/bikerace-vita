package com.chartboost.sdk.impl;

import java.io.Serializable;
import java.util.Arrays;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class av implements Serializable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final byte f427a;
    final byte[] b;

    public byte a() {
        return this.f427a;
    }

    public byte[] b() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof av)) {
            return false;
        }
        av avVar = (av) obj;
        return this.f427a == avVar.f427a && Arrays.equals(this.b, avVar.b);
    }

    public int hashCode() {
        return (this.b != null ? Arrays.hashCode(this.b) : 0) + (this.f427a * 31);
    }
}
