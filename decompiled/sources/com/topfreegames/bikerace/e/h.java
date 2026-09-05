package com.topfreegames.bikerace.e;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/* JADX INFO: compiled from: EasterEggLocationDialog.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class h extends ArrayAdapter<i> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ g f1193a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(g gVar, Context context, int i, i[] iVarArr) {
        super(context, i);
        this.f1193a = gVar;
        for (i iVar : iVarArr) {
            add(iVar);
        }
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        com.topfreegames.bikerace.views.d dVar = (com.topfreegames.bikerace.views.d) view;
        i item = getItem(i);
        if (dVar == null) {
            dVar = new com.topfreegames.bikerace.views.d(getContext());
            this.f1193a.a(getContext(), dVar);
            dVar.setTag(new j(null));
        }
        j jVar = (j) dVar.getTag();
        if (jVar.f1195a != item.f1194a) {
            dVar.a(item.b, item.c, i);
            jVar.f1195a = item.f1194a;
        }
        return dVar;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return true;
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean isEnabled(int i) {
        return false;
    }
}
