package com.topfreegames.bikerace.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.topfreegames.bikerace.ap;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class LevelItemView extends RelativeLayout {
    private static /* synthetic */ int[] n;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected View f1363a;
    protected View b;
    protected ImageView c;
    protected ImageView d;
    protected ImageView e;
    protected ThreeStarsView f;
    protected ImageView g;
    protected View h;
    protected View i;
    protected int j;
    protected j k;
    protected i l;
    protected h m;

    static /* synthetic */ int[] a() {
        int[] iArr = n;
        if (iArr == null) {
            iArr = new int[j.valuesCustom().length];
            try {
                iArr[j.EMPTY.ordinal()] = 4;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[j.LOCKED.ordinal()] = 3;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[j.UNLOCKED_DELETE.ordinal()] = 2;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[j.UNLOCKED_NO_DELETE.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            n = iArr;
        }
        return iArr;
    }

    public LevelItemView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet);
    }

    public LevelItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1363a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = 0;
        this.k = j.UNLOCKED_NO_DELETE;
        this.l = null;
        this.m = null;
        a(attributeSet);
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(2130903070, this);
        this.f1363a = findViewById(2131296410);
        this.b = findViewById(2131296411);
        this.c = (ImageView) findViewById(2131296414);
        this.d = (ImageView) findViewById(2131296413);
        this.e = (ImageView) findViewById(2131296412);
        this.g = (ImageView) findViewById(2131296416);
        this.f = (ThreeStarsView) findViewById(2131296415);
        this.h = findViewById(2131296417);
        this.i = findViewById(2131296418);
        setNumber(this.j);
    }

    public int getLevelID() {
        return this.j;
    }

    public void setLevelID(int i) {
        this.j = i;
        setNumber(this.j);
    }

    public void setState(j jVar) {
        switch (a()[jVar.ordinal()]) {
            case 1:
                this.f1363a.setVisibility(0);
                this.h.setVisibility(8);
                this.g.setVisibility(8);
                this.b.setVisibility(0);
                this.b.setOnClickListener(a(this.l));
                this.i.setVisibility(8);
                this.i.setOnClickListener(null);
                break;
            case 2:
                this.f1363a.setVisibility(0);
                this.h.setVisibility(8);
                this.g.setVisibility(8);
                this.b.setVisibility(0);
                this.b.setOnClickListener(null);
                this.i.setVisibility(0);
                this.i.setOnClickListener(a(this.m));
                break;
            case 3:
                this.f1363a.setVisibility(0);
                this.h.setVisibility(8);
                this.g.setVisibility(0);
                this.b.setVisibility(0);
                this.b.setOnClickListener(null);
                this.i.setVisibility(8);
                this.i.setOnClickListener(null);
                break;
            case 4:
                this.f1363a.setVisibility(8);
                this.h.setVisibility(0);
                this.g.setVisibility(8);
                this.b.setVisibility(8);
                this.b.setOnClickListener(null);
                this.i.setVisibility(8);
                this.i.setOnClickListener(null);
                break;
        }
    }

    public void setNumFillStars(int i) {
        this.f.a(i);
    }

    public void setListener(i iVar) {
        this.l = iVar;
        setState(this.k);
    }

    public void setDeleteListener(h hVar) {
        this.m = hVar;
        setState(this.k);
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        this.f1363a.setVisibility(i);
        setState(this.k);
    }

    private void a(AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, com.topfreegames.b.b.LevelItemView);
        this.j = typedArrayObtainStyledAttributes.getInt(0, 0);
        this.k = typedArrayObtainStyledAttributes.getBoolean(1, false) ? j.LOCKED : j.UNLOCKED_NO_DELETE;
        typedArrayObtainStyledAttributes.recycle();
    }

    private static int a(int i) {
        switch (i) {
            case 0:
                return 2130837890;
            case 1:
                return 2130837891;
            case 2:
                return 2130837892;
            case 3:
                return 2130837893;
            case 4:
                return 2130837894;
            case 5:
                return 2130837895;
            case 6:
                return 2130837896;
            case 7:
                return 2130837897;
            case 8:
                return 2130837898;
            case 9:
                return 2130837899;
            default:
                if (ap.d()) {
                    System.err.println("Invalid level number at LevelItemView");
                }
                return 0;
        }
    }

    private void setNumber(int i) {
        int i2 = i / 100;
        int i3 = (i % 100) / 10;
        int i4 = i % 10;
        this.e.setVisibility(i2 == 0 ? 8 : 0);
        if (this.e.getVisibility() == 0) {
            this.e.setImageResource(a(i2));
        }
        this.d.setVisibility((i2 == 0 && i3 == 0) ? 8 : 0);
        if (this.d.getVisibility() == 0) {
            this.d.setImageResource(a(i3));
        }
        this.c.setVisibility(0);
        this.c.setImageResource(a(i4));
    }

    private View.OnClickListener a(final i iVar) {
        if (iVar != null) {
            return new View.OnClickListener() { // from class: com.topfreegames.bikerace.views.LevelItemView.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (iVar != null) {
                        iVar.a(LevelItemView.this.j);
                    }
                }
            };
        }
        return null;
    }

    private View.OnClickListener a(final h hVar) {
        if (hVar != null) {
            return new View.OnClickListener() { // from class: com.topfreegames.bikerace.views.LevelItemView.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (hVar != null) {
                        hVar.a(LevelItemView.this.j);
                    }
                }
            };
        }
        return null;
    }
}
