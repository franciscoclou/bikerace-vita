package com.heyzap.internal;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import java.util.HashMap;

/* JADX INFO: compiled from: FeedAdapter.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class g extends ArrayAdapter<i> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private HashMap<Object, Integer> f756a;
    private FeedView b;

    private void b(i iVar) {
        if (!this.f756a.containsKey(iVar.getClass())) {
            this.f756a.put(iVar.getClass(), Integer.valueOf(this.f756a.size()));
        }
    }

    @Override // android.widget.ArrayAdapter
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public void add(i iVar) {
        b(iVar);
        super.add(iVar);
    }

    @Override // android.widget.ArrayAdapter
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public void insert(i iVar, int i) {
        b(iVar);
        super.insert(iVar, i);
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getViewTypeCount() {
        return Math.max(10, this.f756a.size());
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getItemViewType(int i) {
        return this.f756a.get(getItem(i).getClass()).intValue();
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        a(i);
        return ((i) super.getItem(i)).a(view, getContext(), this.b);
    }

    protected void a(int i) {
    }
}
