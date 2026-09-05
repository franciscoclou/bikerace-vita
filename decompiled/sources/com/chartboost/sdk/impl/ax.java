package com.chartboost.sdk.impl;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ax extends aw {
    final al b;

    public al b() {
        return this.b;
    }

    @Override // com.chartboost.sdk.impl.aw
    public boolean equals(Object obj) {
        if (!(obj instanceof ax)) {
            return false;
        }
        ax axVar = (ax) obj;
        return this.f428a.equals(axVar.f428a) && this.b.equals(axVar.b);
    }

    @Override // com.chartboost.sdk.impl.aw
    public int hashCode() {
        return this.f428a.hashCode() ^ this.b.hashCode();
    }
}
