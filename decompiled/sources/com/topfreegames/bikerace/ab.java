package com.topfreegames.bikerace;

import com.topfreegames.engine.data.DataNode;

/* JADX INFO: compiled from: GameData.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ab {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ z f825a;

    public ab(z zVar) {
        this.f825a = zVar;
    }

    public void a(int i, int i2, int i3) {
        a(i);
        DataNode child = this.f825a.bd.getChild(String.valueOf(z.m(z.X)) + i);
        if (child != null) {
            DataNode child2 = child.getChild(String.valueOf(z.m(z.U)) + i2);
            if (child2 != null) {
                child.putInteger(z.m(z.Z), Integer.valueOf(child.getInteger(z.m(z.Z)).intValue() - child2.getInteger(z.m(z.W)).intValue()));
                child.removeChild(child2.getKey());
                for (int i4 = i2 + 1; i4 < i3 + 1; i4++) {
                    try {
                        child.changeChildKey(String.valueOf(z.m(z.U)) + i4, String.valueOf(z.m(z.U)) + (i4 - 1));
                    } catch (Exception e) {
                    }
                }
            }
            this.f825a.W();
        }
    }

    private void a(int i) {
        if (i != 999) {
            throw new IllegalArgumentException("Invalid world for LevelEditor");
        }
    }
}
