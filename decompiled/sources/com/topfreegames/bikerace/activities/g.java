package com.topfreegames.bikerace.activities;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.topfreegames.bikerace.views.UserLevelItemView;

/* JADX INFO: compiled from: CustomLevelsActivity.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class g extends ArrayAdapter<com.topfreegames.bikerace.views.v> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ CustomLevelsActivity f1085a;
    private com.topfreegames.bikerace.views.w b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(CustomLevelsActivity customLevelsActivity, Context context, int i) {
        super(context, i);
        this.f1085a = customLevelsActivity;
        this.b = new com.topfreegames.bikerace.views.w();
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        final UserLevelItemView userLevelItemView = (UserLevelItemView) view;
        com.topfreegames.bikerace.views.v item = getItem(i);
        if (userLevelItemView == null) {
            userLevelItemView = new UserLevelItemView(getContext());
            this.f1085a.a(userLevelItemView);
            userLevelItemView.setTag(new i(null));
        }
        int count = getCount();
        i iVar = (i) userLevelItemView.getTag();
        if (iVar.f1091a != item.a() || iVar.b != count || !iVar.c) {
            com.topfreegames.e.a.a aVarB = com.topfreegames.e.a.a.b();
            if (iVar.e != null) {
                aVarB.a((com.topfreegames.e.a.j) ((i) userLevelItemView.getTag()).e);
            }
            if (iVar.d) {
                this.f1085a.u.a(iVar.f1091a);
            }
            com.topfreegames.bikerace.views.u uVar = com.topfreegames.bikerace.views.u.MIDDLE;
            if (count == 1) {
                uVar = com.topfreegames.bikerace.views.u.SINGLE;
            } else if (i == 0) {
                uVar = com.topfreegames.bikerace.views.u.FIRST;
            } else if (i + 1 == count) {
                uVar = com.topfreegames.bikerace.views.u.LAST;
            }
            userLevelItemView.a(item, uVar);
            iVar.f1091a = item.a();
            iVar.b = count;
            iVar.c = false;
            iVar.d = true;
            userLevelItemView.setTag(iVar);
            try {
                this.f1085a.u.a(item.a(), new com.topfreegames.bikerace.h.a.p() { // from class: com.topfreegames.bikerace.activities.g.1
                    @Override // com.topfreegames.bikerace.h.a.p
                    public void a(final Bitmap bitmap) {
                        CustomLevelsActivity customLevelsActivity = g.this.f1085a;
                        final UserLevelItemView userLevelItemView2 = userLevelItemView;
                        customLevelsActivity.runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.g.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                userLevelItemView2.b(bitmap);
                                i iVar2 = (i) userLevelItemView2.getTag();
                                iVar2.c = true;
                                iVar2.d = false;
                                userLevelItemView2.setTag(iVar2);
                            }
                        });
                    }
                });
            } catch (Error e) {
                if (com.topfreegames.bikerace.ap.d()) {
                    e.printStackTrace();
                }
                ((BikeRaceApplication) this.f1085a.getApplication()).d().a(getClass().getName(), "getView", e);
                throw e;
            } catch (Exception e2) {
                if (com.topfreegames.bikerace.ap.d()) {
                    e2.printStackTrace();
                }
                ((BikeRaceApplication) this.f1085a.getApplication()).d().a(getClass().getName(), "getView", e2);
            }
            if (item.c()) {
                try {
                    com.topfreegames.bikerace.f.a aVarD = item.d();
                    if (!com.topfreegames.bikerace.multiplayer.c.b(aVarD.c())) {
                        com.topfreegames.e.l lVarA = aVarB.a(aVarD.c(), true);
                        if (lVarA != null && lVarA.c() != null) {
                            userLevelItemView.a(lVarA.c());
                            iVar.c = true;
                        } else {
                            com.topfreegames.e.a.m mVar = new com.topfreegames.e.a.m() { // from class: com.topfreegames.bikerace.activities.g.2
                                @Override // com.topfreegames.e.a.m
                                public void a(final com.topfreegames.e.l lVar, boolean z) {
                                    CustomLevelsActivity customLevelsActivity = g.this.f1085a;
                                    final UserLevelItemView userLevelItemView2 = userLevelItemView;
                                    customLevelsActivity.runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.g.2.1
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            if (lVar != null) {
                                                userLevelItemView2.a(lVar.c());
                                                i iVar2 = (i) userLevelItemView2.getTag();
                                                iVar2.c = true;
                                                userLevelItemView2.setTag(iVar2);
                                            }
                                        }
                                    });
                                }
                            };
                            iVar.e = mVar;
                            aVarB.a(aVarD.c(), true, mVar, (Object) this.f1085a);
                        }
                    }
                } catch (Error e3) {
                    if (com.topfreegames.bikerace.ap.d()) {
                        e3.printStackTrace();
                    }
                    ((BikeRaceApplication) this.f1085a.getApplication()).d().a(getClass().getName(), "getView", e3);
                    throw e3;
                } catch (Exception e4) {
                    if (com.topfreegames.bikerace.ap.d()) {
                        e4.printStackTrace();
                    }
                    ((BikeRaceApplication) this.f1085a.getApplication()).d().a(getClass().getName(), "getView", e4);
                }
            }
        }
        return userLevelItemView;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return true;
    }

    public com.topfreegames.bikerace.views.v a(String str) {
        if (str != null) {
            for (int count = getCount() - 1; count >= 0; count--) {
                com.topfreegames.bikerace.views.v item = getItem(count);
                if (str.equals(item.a())) {
                    return item;
                }
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
    public void add(com.topfreegames.bikerace.views.v vVar) {
        boolean z;
        int count = getCount();
        int i = 0;
        while (true) {
            if (i >= count) {
                z = true;
                break;
            }
            com.topfreegames.bikerace.views.v item = getItem(i);
            if (item == vVar || item.equals(vVar)) {
                z = false;
                break;
            }
            i++;
        }
        if (z) {
            super.add(vVar);
        }
    }

    public void b(String str) {
        com.topfreegames.bikerace.views.v vVarA;
        if (str != null && (vVarA = a(str)) != null) {
            super.remove(vVarA);
        }
    }

    public void a() {
        super.sort(this.b);
    }
}
