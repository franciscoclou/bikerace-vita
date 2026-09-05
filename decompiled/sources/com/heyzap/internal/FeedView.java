package com.heyzap.internal;

import android.R;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.widget.ListView;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class FeedView extends ListView {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f749a;
    private boolean b;
    private Bundle c;

    public void setOnClickExtras(Bundle bundle) {
        this.c = bundle;
    }

    public void setWhiteFeedlettes(boolean z) {
        this.b = z;
    }

    public FeedView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f749a = true;
        this.c = null;
        a();
    }

    /* JADX INFO: renamed from: com.heyzap.internal.FeedView$1, reason: invalid class name */
    class AnonymousClass1 extends g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ FeedView f750a;

        @Override // com.heyzap.internal.g
        public void a(int i) {
            this.f750a.a(i);
        }
    }

    protected void a(int i) {
    }

    private void a() {
        setItemsCanFocus(true);
        setScrollingCacheEnabled(true);
        setVerticalFadingEdgeEnabled(false);
        setOnItemClickListener(new h(this, null));
        setDividerHeight(0);
        setSelector(R.color.transparent);
    }
}
