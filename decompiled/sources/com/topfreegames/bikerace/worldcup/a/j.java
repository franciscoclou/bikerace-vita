package com.topfreegames.bikerace.worldcup.a;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.topfreegames.bikerace.worldcup.views.WorldCupSlotItemView;

/* JADX INFO: compiled from: WorldCupShopSlotMode.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class j extends ArrayAdapter<com.topfreegames.bikerace.worldcup.a> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final com.topfreegames.bikerace.worldcup.a[] f1446a;
    final /* synthetic */ h b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(h hVar, Context context, int i, com.topfreegames.bikerace.worldcup.a[] aVarArr) {
        super(context, i, aVarArr);
        this.b = hVar;
        this.f1446a = aVarArr;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public com.topfreegames.bikerace.worldcup.a getItem(int i) {
        return this.f1446a[i % this.f1446a.length];
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        final WorldCupSlotItemView worldCupSlotItemView = (WorldCupSlotItemView) view;
        if (worldCupSlotItemView == null) {
            worldCupSlotItemView = new WorldCupSlotItemView(this.b.f1432a);
        }
        if (this.b.q <= 0) {
            this.b.b.post(new Runnable() { // from class: com.topfreegames.bikerace.worldcup.a.j.1
                @Override // java.lang.Runnable
                public void run() {
                    j.this.b.q = worldCupSlotItemView.getHeight();
                }
            });
        }
        com.topfreegames.bikerace.worldcup.a item = getItem(i);
        if (worldCupSlotItemView.getTag() != item) {
            worldCupSlotItemView.setup(item);
            worldCupSlotItemView.setTag(item);
        }
        return worldCupSlotItemView;
    }

    public int a(com.topfreegames.bikerace.worldcup.a aVar) {
        for (int i = 0; i < this.f1446a.length; i++) {
            if (this.f1446a[i].equals(aVar)) {
                return i;
            }
        }
        return -1;
    }
}
