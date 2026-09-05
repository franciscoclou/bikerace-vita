package com.heyzap.internal;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: compiled from: Feedlette.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class i implements Comparable<i> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected int f758a;
    protected Bundle b;
    protected Context c;
    public String d = "";

    public i() {
        a();
    }

    private void a() {
        this.b = new Bundle();
    }

    protected LayoutInflater a(Context context) {
        return (LayoutInflater) context.getSystemService("layout_inflater");
    }

    public View a(View view, Context context, FeedView feedView) {
        this.c = context;
        if (view == null) {
            return a(context).inflate(this.f758a, (ViewGroup) null);
        }
        return view;
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(i iVar) {
        return 0;
    }
}
