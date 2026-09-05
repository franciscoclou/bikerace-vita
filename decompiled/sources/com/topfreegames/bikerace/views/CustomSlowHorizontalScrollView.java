package com.topfreegames.bikerace.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;
import com.topfreegames.bikerace.ap;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class CustomSlowHorizontalScrollView extends HorizontalScrollView {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private c f1358a;

    public CustomSlowHorizontalScrollView(Context context) {
        super(context);
        a();
    }

    public CustomSlowHorizontalScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    public CustomSlowHorizontalScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        try {
            Field declaredField = getClass().getSuperclass().getDeclaredField("mMaximumVelocity");
            declaredField.setAccessible(true);
            declaredField.setInt(this, (int) (declaredField.getInt(this) / 4.0f));
        } catch (IllegalAccessException e) {
            if (ap.d()) {
                e.printStackTrace();
            }
        } catch (IllegalArgumentException e2) {
            if (ap.d()) {
                e2.printStackTrace();
            }
        } catch (NoSuchFieldException e3) {
            if (ap.d()) {
                e3.printStackTrace();
            }
        }
    }

    public void setScrollViewListener(c cVar) {
        this.f1358a = cVar;
    }

    @Override // android.view.View
    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        if (this.f1358a != null) {
            this.f1358a.a(this, i, i2, i3, i4);
        }
    }
}
