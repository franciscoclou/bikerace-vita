package com.topfreegames.bikerace.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class MultiplayerRankingHeaderView extends RelativeLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ImageView f1371a;
    private ImageView b;
    private TextView c;
    private TextView d;

    public MultiplayerRankingHeaderView(Context context, AttributeSet attributeSet, int i) {
        this(context, (View.OnClickListener) null, (View.OnClickListener) null);
    }

    public MultiplayerRankingHeaderView(Context context, final View.OnClickListener onClickListener, final View.OnClickListener onClickListener2) {
        super(context);
        this.f1371a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(2130903081, this);
        this.f1371a = (ImageView) findViewById(2131296522);
        this.b = (ImageView) findViewById(2131296523);
        this.c = (TextView) findViewById(2131296524);
        this.d = (TextView) findViewById(2131296525);
        this.c.setOnClickListener(new View.OnClickListener() { // from class: com.topfreegames.bikerace.views.MultiplayerRankingHeaderView.1
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                if (MultiplayerRankingHeaderView.this.f1371a.getVisibility() != 0) {
                    ImageView imageView = MultiplayerRankingHeaderView.this.f1371a;
                    final View.OnClickListener onClickListener3 = onClickListener;
                    imageView.post(new Runnable() { // from class: com.topfreegames.bikerace.views.MultiplayerRankingHeaderView.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MultiplayerRankingHeaderView.this.b.setVisibility(8);
                            MultiplayerRankingHeaderView.this.f1371a.setVisibility(0);
                            if (onClickListener3 != null) {
                                onClickListener3.onClick(view);
                            }
                        }
                    });
                }
            }
        });
        this.d.setOnClickListener(new View.OnClickListener() { // from class: com.topfreegames.bikerace.views.MultiplayerRankingHeaderView.2
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                if (MultiplayerRankingHeaderView.this.b.getVisibility() != 0) {
                    ImageView imageView = MultiplayerRankingHeaderView.this.b;
                    final View.OnClickListener onClickListener3 = onClickListener2;
                    imageView.post(new Runnable() { // from class: com.topfreegames.bikerace.views.MultiplayerRankingHeaderView.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MultiplayerRankingHeaderView.this.f1371a.setVisibility(8);
                            MultiplayerRankingHeaderView.this.b.setVisibility(0);
                            if (onClickListener3 != null) {
                                onClickListener3.onClick(view);
                            }
                        }
                    });
                }
            }
        });
    }
}
