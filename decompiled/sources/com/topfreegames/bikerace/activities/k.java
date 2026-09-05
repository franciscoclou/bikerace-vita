package com.topfreegames.bikerace.activities;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.topfreegames.bikerace.views.MultiplayerFriendItemView;

/* JADX INFO: compiled from: FacebookUsersListActivity.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class k extends ArrayAdapter<com.topfreegames.e.l> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ FacebookUsersListActivity f1093a;
    private boolean b;
    private boolean c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k(FacebookUsersListActivity facebookUsersListActivity, Context context, int i) {
        super(context, i);
        this.f1093a = facebookUsersListActivity;
        this.b = false;
        this.c = this.b ? false : true;
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
        final MultiplayerFriendItemView multiplayerFriendItemView;
        MultiplayerFriendItemView multiplayerFriendItemView2 = (MultiplayerFriendItemView) view;
        com.topfreegames.e.l item = getItem(i);
        if (multiplayerFriendItemView2 == null) {
            multiplayerFriendItemView = new MultiplayerFriendItemView(getContext(), this.f1093a.f, this.f1093a.g);
            this.f1093a.a(multiplayerFriendItemView);
            multiplayerFriendItemView.setTag(new m(null));
        } else {
            multiplayerFriendItemView = multiplayerFriendItemView2;
        }
        m mVar = (m) multiplayerFriendItemView.getTag();
        if (mVar.f1097a != item.a() || mVar.b != getCount() || !mVar.c) {
            com.topfreegames.e.a.a aVarB = com.topfreegames.e.a.a.b();
            if (mVar.d != null) {
                aVarB.a((com.topfreegames.e.a.j) ((m) multiplayerFriendItemView.getTag()).d);
            }
            com.topfreegames.bikerace.views.m mVar2 = com.topfreegames.bikerace.views.m.MIDDLE;
            int count = getCount();
            if (count == 1) {
                mVar2 = com.topfreegames.bikerace.views.m.SINGLE;
            } else if (i == 0) {
                mVar2 = com.topfreegames.bikerace.views.m.TOP;
            } else if (i + 1 == count) {
                mVar2 = com.topfreegames.bikerace.views.m.BOTTOM;
            }
            multiplayerFriendItemView.a(item.a(), item.b(), item.a(), this.f1093a.j == com.topfreegames.e.e.FRIENDS_HAVE_APP, mVar2);
            mVar.f1097a = item.a();
            mVar.b = getCount();
            mVar.c = false;
            multiplayerFriendItemView.setTag(mVar);
            multiplayerFriendItemView.setAvatarImage(null);
            if (!this.b) {
                try {
                    if (!com.topfreegames.bikerace.multiplayer.c.b(item.a())) {
                        com.topfreegames.e.l lVarA = aVarB.a(item.a(), true);
                        if (lVarA != null && lVarA.c() != null) {
                            multiplayerFriendItemView.setAvatarImage(lVarA.c());
                            mVar.c = true;
                        } else {
                            com.topfreegames.e.a.m mVar3 = new com.topfreegames.e.a.m() { // from class: com.topfreegames.bikerace.activities.k.1
                                @Override // com.topfreegames.e.a.m
                                public void a(final com.topfreegames.e.l lVar, boolean z) {
                                    FacebookUsersListActivity facebookUsersListActivity = k.this.f1093a;
                                    final MultiplayerFriendItemView multiplayerFriendItemView3 = multiplayerFriendItemView;
                                    facebookUsersListActivity.runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.k.1.1
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            if (lVar != null) {
                                                multiplayerFriendItemView3.setAvatarImage(lVar.c());
                                                m mVar4 = (m) multiplayerFriendItemView3.getTag();
                                                mVar4.c = true;
                                                multiplayerFriendItemView3.setTag(mVar4);
                                            }
                                        }
                                    });
                                }
                            };
                            mVar.d = mVar3;
                            aVarB.a(item.a(), true, mVar3, (Object) this.f1093a);
                        }
                    }
                } catch (Error e) {
                    if (com.topfreegames.bikerace.ap.d()) {
                        e.printStackTrace();
                    }
                    ((BikeRaceApplication) this.f1093a.getApplication()).d().a(getClass().getName(), "getView", e);
                    throw e;
                } catch (Exception e2) {
                    if (com.topfreegames.bikerace.ap.d()) {
                        e2.printStackTrace();
                    }
                    ((BikeRaceApplication) this.f1093a.getApplication()).d().a(getClass().getName(), "getView", e2);
                }
                multiplayerFriendItemView.setTag(mVar);
            }
        }
        return multiplayerFriendItemView;
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
        super.sort(new com.topfreegames.e.m());
    }

    private com.topfreegames.e.l a(String str) {
        for (int count = getCount() - 1; count >= 0; count--) {
            com.topfreegames.e.l item = getItem(count);
            if (item.a().equals(str)) {
                return item;
            }
        }
        return null;
    }

    @Override // android.widget.ArrayAdapter
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public void add(com.topfreegames.e.l lVar) {
        if (a(lVar.a()) == null) {
            super.add(lVar);
        }
    }
}
