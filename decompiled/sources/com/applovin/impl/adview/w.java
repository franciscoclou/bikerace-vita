package com.applovin.impl.adview;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class w {
    static com.applovin.a.f a(AttributeSet attributeSet) {
        String attributeValue;
        if (attributeSet == null || (attributeValue = attributeSet.getAttributeValue("http://schemas.applovin.com/android/1.0", "size")) == null) {
            return null;
        }
        return com.applovin.a.f.a(attributeValue);
    }

    static String a(AttributeSet attributeSet, Context context) {
        String attributeValue = attributeSet != null ? attributeSet.getAttributeValue("http://schemas.applovin.com/android/1.0", "placement") : null;
        return (attributeValue == null && (context instanceof Activity)) ? context.getClass().getName() : attributeValue;
    }

    static boolean b(AttributeSet attributeSet) {
        if (attributeSet == null) {
            return false;
        }
        return attributeSet.getAttributeBooleanValue("http://schemas.applovin.com/android/1.0", "loadAdOnCreate", false);
    }
}
