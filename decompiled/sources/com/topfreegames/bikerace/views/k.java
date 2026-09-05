package com.topfreegames.bikerace.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import com.topfreegames.bikerace.activities.BikeRaceApplication;
import com.topfreegames.bikerace.ap;
import com.topfreegames.bikerace.h.y;
import com.topfreegames.bikerace.z;
import java.util.ArrayList;

/* JADX INFO: compiled from: LevelTableView.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class k extends RelativeLayout {
    protected static final int[] g = {2131296420, 2131296421, 2131296422, 2131296423, 2131296424, 2131296425, 2131296426, 2131296427};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected View f1405a;
    protected View b;
    protected View c;
    protected int d;
    protected boolean e;
    protected boolean f;
    protected LevelItemView[] h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k(Context context, i iVar, h hVar, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        super(context);
        this.f1405a = null;
        this.b = null;
        this.c = null;
        this.d = 0;
        this.e = false;
        this.f = false;
        this.h = new LevelItemView[g.length];
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(2130903071, this);
        for (int i = 0; i < g.length; i++) {
            this.h[i] = (LevelItemView) findViewById(g[i]);
            this.h[i].setListener(iVar);
            this.h[i].setDeleteListener(hVar);
        }
        this.b = findViewById(2131296419);
        this.b.setOnClickListener(onClickListener);
        this.c = findViewById(2131296428);
        this.c.setOnClickListener(onClickListener2);
    }

    private void a(z zVar, int i, int i2, int i3, boolean z) {
        try {
            LevelItemView levelItemView = this.h[i2];
            levelItemView.setLevelID(i2 + 1 + (g.length * i3));
            if (levelItemView.getLevelID() <= y.b(i)) {
                levelItemView.setNumFillStars(zVar.b(i, levelItemView.getLevelID()));
                if (z) {
                    levelItemView.setState(j.UNLOCKED_DELETE);
                } else if (zVar.a(i, levelItemView.getLevelID())) {
                    levelItemView.setState(j.LOCKED);
                } else {
                    levelItemView.setState(j.UNLOCKED_NO_DELETE);
                }
            } else {
                levelItemView.setState(j.EMPTY);
            }
        } catch (Error e) {
            if (ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) getContext().getApplicationContext()).d().a(getClass().getName(), "updateLevelItemView", e);
            throw e;
        } catch (Exception e2) {
            if (ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) getContext().getApplicationContext()).d().a(getClass().getName(), "updateLevelItemView", e2);
        }
    }

    public void a(z zVar, int i, int i2, boolean z, boolean z2, boolean z3) {
        for (int i3 = 0; i3 < g.length; i3++) {
            a(zVar, i, i3, i2, z3);
        }
        this.b.setVisibility(z ? 4 : 0);
        this.c.setVisibility(z2 ? 4 : 0);
    }

    public ArrayList<View> getInternalViews() {
        ArrayList<View> arrayList = new ArrayList<>();
        for (LevelItemView levelItemView : this.h) {
            arrayList.add(levelItemView);
        }
        return arrayList;
    }
}
