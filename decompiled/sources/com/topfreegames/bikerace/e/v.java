package com.topfreegames.bikerace.e;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.topfreegames.bikerace.activities.BikeRaceApplication;
import com.topfreegames.bikerace.ap;

/* JADX INFO: compiled from: GiftsDialog.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class v extends ArrayAdapter<com.topfreegames.bikerace.f.a> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ s f1215a;
    private boolean b;
    private boolean c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public v(s sVar, Context context, int i, com.topfreegames.bikerace.f.a[] aVarArr) {
        super(context, i);
        this.f1215a = sVar;
        this.b = false;
        this.c = !this.b;
        for (com.topfreegames.bikerace.f.a aVar : aVarArr) {
            add(aVar);
        }
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
        final com.topfreegames.bikerace.views.e eVar = (com.topfreegames.bikerace.views.e) view;
        com.topfreegames.bikerace.f.a item = getItem(i);
        if (eVar == null) {
            eVar = new com.topfreegames.bikerace.views.e(getContext(), this.f1215a.f1209a, this.f1215a.b);
            this.f1215a.a(getContext(), eVar);
            eVar.setTag(new aa(null));
        }
        aa aaVar = (aa) eVar.getTag();
        if (aaVar.f1171a != item.a() || aaVar.b != getCount() || !aaVar.c || aaVar.d != item.e()) {
            com.topfreegames.e.a.a aVarB = com.topfreegames.e.a.a.b();
            if (aaVar.e != null) {
                aVarB.a((com.topfreegames.e.a.j) ((aa) eVar.getTag()).e);
            }
            com.topfreegames.bikerace.views.g gVar = com.topfreegames.bikerace.views.g.MIDDLE;
            int count = getCount();
            if (count == 1) {
                gVar = com.topfreegames.bikerace.views.g.SINGLE;
            } else if (i == 0) {
                gVar = com.topfreegames.bikerace.views.g.TOP;
            } else if (i + 1 == count) {
                gVar = com.topfreegames.bikerace.views.g.BOTTOM;
            }
            eVar.a(item, gVar, this.f1215a.k);
            aaVar.f1171a = item.a();
            aaVar.b = getCount();
            aaVar.c = false;
            aaVar.d = item.e();
            eVar.setTag(aaVar);
            eVar.setAvatarImage(null);
            if (!this.b) {
                try {
                    com.topfreegames.e.l lVarA = aVarB.a(item.c(), true);
                    if (lVarA != null && lVarA.c() != null) {
                        eVar.setAvatarImage(lVarA.c());
                        aaVar.c = true;
                    } else {
                        com.topfreegames.e.a.m mVar = new com.topfreegames.e.a.m() { // from class: com.topfreegames.bikerace.e.v.1
                            @Override // com.topfreegames.e.a.m
                            public void a(final com.topfreegames.e.l lVar, boolean z) {
                                Activity activity = (Activity) v.this.getContext();
                                final com.topfreegames.bikerace.views.e eVar2 = eVar;
                                activity.runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.e.v.1.1
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        if (lVar != null) {
                                            eVar2.setAvatarImage(lVar.c());
                                            aa aaVar2 = (aa) eVar2.getTag();
                                            aaVar2.c = true;
                                            eVar2.setTag(aaVar2);
                                        }
                                    }
                                });
                            }
                        };
                        aaVar.e = mVar;
                        Activity activity = (Activity) this.f1215a.d.get();
                        if (activity != null) {
                            aVarB.a(item.c(), true, mVar, (Object) activity);
                        }
                    }
                } catch (Error e) {
                    if (ap.d()) {
                        e.printStackTrace();
                    }
                    ((BikeRaceApplication) getContext().getApplicationContext()).d().a(getClass().getName(), "getView", e);
                    throw e;
                } catch (Exception e2) {
                    if (ap.d()) {
                        e2.printStackTrace();
                    }
                    ((BikeRaceApplication) getContext().getApplicationContext()).d().a(getClass().getName(), "getView", e2);
                }
                eVar.setTag(aaVar);
            }
        }
        return eVar;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return true;
    }

    public com.topfreegames.bikerace.f.a a(String str) {
        for (int count = getCount() - 1; count >= 0; count--) {
            com.topfreegames.bikerace.f.a item = getItem(count);
            if (item.a().equals(str)) {
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

    @Override // android.widget.ArrayAdapter
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public void add(com.topfreegames.bikerace.f.a aVar) {
        com.topfreegames.bikerace.f.a aVarA = a(aVar.a());
        if (aVarA == null) {
            super.add(aVar);
        } else {
            remove(aVarA);
            super.add(aVar);
        }
    }

    @Override // android.widget.ArrayAdapter
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public void remove(com.topfreegames.bikerace.f.a aVar) {
        if (aVar != null) {
            super.remove(aVar);
        }
    }
}
