package com.topfreegames.bikerace.activities;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import java.util.Comparator;

/* JADX INFO: compiled from: MultiplayerMainActivity.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class t extends ArrayAdapter<com.topfreegames.bikerace.multiplayer.l> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ MultiplayerMainActivity f1104a;
    private boolean b;
    private boolean c;
    private Comparator<com.topfreegames.bikerace.multiplayer.l> d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public t(MultiplayerMainActivity multiplayerMainActivity, Context context, int i) {
        super(context, i);
        this.f1104a = multiplayerMainActivity;
        this.b = false;
        this.c = this.b ? false : true;
        this.d = new com.topfreegames.bikerace.multiplayer.n();
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
        final com.topfreegames.bikerace.views.n nVar = (com.topfreegames.bikerace.views.n) view;
        com.topfreegames.bikerace.multiplayer.l item = getItem(i);
        if (nVar == null) {
            nVar = new com.topfreegames.bikerace.views.n(getContext(), this.f1104a.j, this.f1104a.k, this.f1104a.l);
            this.f1104a.a(nVar);
            nVar.setTag(new w(null));
        }
        w wVar = (w) nVar.getTag();
        if (wVar.f1109a != item.f() || wVar.b != getCount() || !wVar.c) {
            com.topfreegames.e.a.a aVarB = com.topfreegames.e.a.a.b();
            if (wVar.d != null) {
                aVarB.a((com.topfreegames.e.a.j) ((w) nVar.getTag()).d);
            }
            com.topfreegames.bikerace.views.p pVar = com.topfreegames.bikerace.views.p.MIDDLE;
            int count = getCount();
            if (count == 1) {
                pVar = com.topfreegames.bikerace.views.p.SINGLE;
            } else if (i == 0) {
                pVar = com.topfreegames.bikerace.views.p.TOP;
            } else if (i + 1 == count) {
                pVar = com.topfreegames.bikerace.views.p.BOTTOM;
            }
            nVar.a(item, pVar, this.f1104a.s);
            wVar.f1109a = item.f();
            wVar.b = getCount();
            wVar.c = false;
            nVar.setTag(wVar);
            nVar.setAvatarImage(null);
            if (!this.b) {
                try {
                    if (!com.topfreegames.bikerace.multiplayer.c.b(item.f())) {
                        com.topfreegames.e.l lVarA = aVarB.a(item.f(), true);
                        if (lVarA != null && lVarA.c() != null) {
                            nVar.setAvatarImage(lVarA.c());
                            wVar.c = true;
                        } else {
                            com.topfreegames.e.a.m mVar = new com.topfreegames.e.a.m() { // from class: com.topfreegames.bikerace.activities.t.1
                                @Override // com.topfreegames.e.a.m
                                public void a(final com.topfreegames.e.l lVar, boolean z) {
                                    MultiplayerMainActivity multiplayerMainActivity = t.this.f1104a;
                                    final com.topfreegames.bikerace.views.n nVar2 = nVar;
                                    multiplayerMainActivity.runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.t.1.1
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            if (lVar != null) {
                                                nVar2.setAvatarImage(lVar.c());
                                                w wVar2 = (w) nVar2.getTag();
                                                wVar2.c = true;
                                                nVar2.setTag(wVar2);
                                            }
                                        }
                                    });
                                }
                            };
                            wVar.d = mVar;
                            aVarB.a(item.f(), true, mVar, (Object) this.f1104a);
                        }
                    }
                } catch (Error e) {
                    if (com.topfreegames.bikerace.ap.d()) {
                        e.printStackTrace();
                    }
                    ((BikeRaceApplication) this.f1104a.getApplication()).d().a(getClass().getName(), "getView", e);
                    throw e;
                } catch (Exception e2) {
                    if (com.topfreegames.bikerace.ap.d()) {
                        e2.printStackTrace();
                    }
                    ((BikeRaceApplication) this.f1104a.getApplication()).d().a(getClass().getName(), "getView", e2);
                }
                nVar.setTag(wVar);
            }
        }
        return nVar;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return true;
    }

    public com.topfreegames.bikerace.multiplayer.l a(String str) {
        for (int count = getCount() - 1; count >= 0; count--) {
            com.topfreegames.bikerace.multiplayer.l item = getItem(count);
            if (item.b().equals(str)) {
                return item;
            }
        }
        return null;
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean isEnabled(int i) {
        return false;
    }

    public void a() {
        super.sort(this.d);
    }

    @Override // android.widget.ArrayAdapter
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public void add(com.topfreegames.bikerace.multiplayer.l lVar) {
        com.topfreegames.bikerace.multiplayer.l lVarA = a(lVar.b());
        if (lVarA == null) {
            super.add(lVar);
        } else {
            remove(lVarA);
            super.add(lVar);
        }
    }

    public void b(String str) {
        com.topfreegames.bikerace.multiplayer.l lVarA;
        if (str != null && (lVarA = a(str)) != null) {
            super.remove(lVarA);
        }
    }
}
