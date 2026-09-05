package com.topfreegames.bikerace.activities;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.topfreegames.bikerace.views.MultiplayerRankingItemView;
import java.util.Comparator;

/* JADX INFO: compiled from: MultiplayerRankingActivity.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class z extends ArrayAdapter<com.topfreegames.bikerace.multiplayer.an> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ MultiplayerRankingActivity f1112a;
    private boolean b;
    private boolean c;
    private Comparator<com.topfreegames.bikerace.multiplayer.an> d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public z(MultiplayerRankingActivity multiplayerRankingActivity, Context context, int i) {
        super(context, i);
        this.f1112a = multiplayerRankingActivity;
        this.b = false;
        this.c = this.b ? false : true;
        this.d = new com.topfreegames.bikerace.multiplayer.ao();
    }

    public void a(boolean z) {
        this.b = !z;
        if (this.b != this.c) {
            this.c = this.b;
            if (!this.b) {
                notifyDataSetChanged();
            }
        }
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        final MultiplayerRankingItemView multiplayerRankingItemView = (MultiplayerRankingItemView) view;
        com.topfreegames.bikerace.multiplayer.an item = getItem(i);
        if (multiplayerRankingItemView == null) {
            multiplayerRankingItemView = new MultiplayerRankingItemView(getContext(), com.topfreegames.bikerace.views.q.DEFAULT);
            this.f1112a.a(multiplayerRankingItemView);
            multiplayerRankingItemView.setTag(new ab(null));
        }
        ab abVar = (ab) multiplayerRankingItemView.getTag();
        if (abVar.f1058a != item.b || abVar.b != getCount() || !abVar.c) {
            com.topfreegames.e.a.a aVarB = com.topfreegames.e.a.a.b();
            if (abVar.d != null) {
                aVarB.a((com.topfreegames.e.a.j) ((ab) multiplayerRankingItemView.getTag()).d);
            }
            int i2 = i + 1;
            multiplayerRankingItemView.a(item.f1309a, item.c, i2, i2 == getCount());
            abVar.f1058a = item.b;
            abVar.b = getCount();
            abVar.c = false;
            multiplayerRankingItemView.setTag(abVar);
            multiplayerRankingItemView.setAvatarImage(null);
            if (!this.b) {
                try {
                    if (!com.topfreegames.bikerace.multiplayer.c.b(item.b)) {
                        com.topfreegames.e.l lVarA = aVarB.a(item.b, true);
                        if (lVarA != null && lVarA.c() != null) {
                            multiplayerRankingItemView.setAvatarImage(lVarA.c());
                            abVar.c = true;
                        } else {
                            com.topfreegames.e.a.m mVar = new com.topfreegames.e.a.m() { // from class: com.topfreegames.bikerace.activities.z.1
                                @Override // com.topfreegames.e.a.m
                                public void a(final com.topfreegames.e.l lVar, boolean z) {
                                    MultiplayerRankingActivity multiplayerRankingActivity = z.this.f1112a;
                                    final MultiplayerRankingItemView multiplayerRankingItemView2 = multiplayerRankingItemView;
                                    multiplayerRankingActivity.runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.z.1.1
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            if (lVar != null) {
                                                multiplayerRankingItemView2.setAvatarImage(lVar.c());
                                                ab abVar2 = (ab) multiplayerRankingItemView2.getTag();
                                                abVar2.c = true;
                                                multiplayerRankingItemView2.setTag(abVar2);
                                            }
                                        }
                                    });
                                }
                            };
                            abVar.d = mVar;
                            aVarB.a(item.b, true, mVar, (Object) this.f1112a);
                        }
                    }
                } catch (Error e) {
                    if (com.topfreegames.bikerace.ap.d()) {
                        e.printStackTrace();
                    }
                    ((BikeRaceApplication) this.f1112a.getApplication()).d().a(getClass().getName(), "getView", e);
                    throw e;
                } catch (Exception e2) {
                    if (com.topfreegames.bikerace.ap.d()) {
                        e2.printStackTrace();
                    }
                    ((BikeRaceApplication) this.f1112a.getApplication()).d().a(getClass().getName(), "getView", e2);
                }
                multiplayerRankingItemView.setTag(abVar);
            }
        }
        return multiplayerRankingItemView;
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean isEnabled(int i) {
        return false;
    }

    @Override // android.widget.ArrayAdapter
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public void add(com.topfreegames.bikerace.multiplayer.an anVar) {
        super.add(anVar);
    }

    public void a() {
        super.sort(this.d);
    }
}
