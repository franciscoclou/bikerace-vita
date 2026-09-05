package com.topfreegames.bikerace.worldcup.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class SlotListView extends ListView {
    public SlotListView(Context context) {
        super(context);
    }

    public SlotListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SlotListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return true;
    }
}
