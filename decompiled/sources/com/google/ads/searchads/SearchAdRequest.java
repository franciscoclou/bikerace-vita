package com.google.ads.searchads;

import android.content.Context;
import android.graphics.Color;
import com.google.ads.AdRequest;
import com.google.ads.mediation.admob.AdMobAdapterExtras;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class SearchAdRequest extends AdRequest {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f710a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private String h;
    private int i;
    private int j;
    private BorderType k;
    private int l;
    private String m;

    public enum BorderType {
        NONE("none"),
        DASHED("dashed"),
        DOTTED("dotted"),
        SOLID("solid");


        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f711a;

        BorderType(String str) {
            this.f711a = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.f711a;
        }
    }

    public void setQuery(String str) {
        this.f710a = str;
    }

    public void setBackgroundColor(int i) {
        if (Color.alpha(i) == 255) {
            this.b = i;
            this.c = 0;
            this.d = 0;
        }
    }

    public void setBackgroundGradient(int i, int i2) {
        if (Color.alpha(i) == 255 && Color.alpha(i2) == 255) {
            this.b = Color.argb(0, 0, 0, 0);
            this.c = i;
            this.d = i2;
        }
    }

    public void setHeaderTextColor(int i) {
        this.e = i;
    }

    public void setDescriptionTextColor(int i) {
        this.f = i;
    }

    public void setAnchorTextColor(int i) {
        this.g = i;
    }

    public void setFontFace(String str) {
        this.h = str;
    }

    public void setHeaderTextSize(int i) {
        this.i = i;
    }

    public void setBorderColor(int i) {
        this.j = i;
    }

    public void setBorderType(BorderType borderType) {
        this.k = borderType;
    }

    public void setBorderThickness(int i) {
        this.l = i;
    }

    public void setCustomChannels(String str) {
        this.m = str;
    }

    @Override // com.google.ads.AdRequest
    public Map<String, Object> getRequestMap(Context context) {
        AdMobAdapterExtras adMobAdapterExtras = (AdMobAdapterExtras) getNetworkExtras(AdMobAdapterExtras.class);
        if (adMobAdapterExtras == null) {
            adMobAdapterExtras = new AdMobAdapterExtras();
            setNetworkExtras(adMobAdapterExtras);
        }
        if (this.f710a != null) {
            adMobAdapterExtras.getExtras().put("q", this.f710a);
        }
        if (Color.alpha(this.b) != 0) {
            adMobAdapterExtras.getExtras().put("bgcolor", a(this.b));
        }
        if (Color.alpha(this.c) == 255 && Color.alpha(this.d) == 255) {
            adMobAdapterExtras.getExtras().put("gradientfrom", a(this.c));
            adMobAdapterExtras.getExtras().put("gradientto", a(this.d));
        }
        if (Color.alpha(this.e) != 0) {
            adMobAdapterExtras.getExtras().put("hcolor", a(this.e));
        }
        if (Color.alpha(this.f) != 0) {
            adMobAdapterExtras.getExtras().put("dcolor", a(this.f));
        }
        if (Color.alpha(this.g) != 0) {
            adMobAdapterExtras.getExtras().put("acolor", a(this.g));
        }
        if (this.h != null) {
            adMobAdapterExtras.getExtras().put("font", this.h);
        }
        adMobAdapterExtras.getExtras().put("headersize", Integer.toString(this.i));
        if (Color.alpha(this.j) != 0) {
            adMobAdapterExtras.getExtras().put("bcolor", a(this.j));
        }
        if (this.k != null) {
            adMobAdapterExtras.getExtras().put("btype", this.k.toString());
        }
        adMobAdapterExtras.getExtras().put("bthick", Integer.toString(this.l));
        if (this.m != null) {
            adMobAdapterExtras.getExtras().put("channel", this.m);
        }
        return super.getRequestMap(context);
    }

    private String a(int i) {
        return String.format(Locale.US, "#%06x", Integer.valueOf(16777215 & i));
    }
}
