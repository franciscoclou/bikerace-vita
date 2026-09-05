package com.topfreegames.bikerace.a;

import java.util.LinkedList;
import java.util.Queue;

/* JADX INFO: compiled from: AchievementPresenter.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class e implements j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private i f817a = null;
    private Queue<a> b = new LinkedList();
    private boolean c = false;

    e() {
    }

    void a(i iVar) {
        if (iVar == null) {
            throw new IllegalArgumentException("Delegate cannot be null!");
        }
        this.f817a = iVar;
    }

    void b(i iVar) {
        if (iVar == null) {
            throw new IllegalArgumentException("Delegate cannot be null!");
        }
        this.f817a = null;
        this.c = false;
    }

    void a(final a aVar) {
        if (aVar == null) {
            throw new IllegalArgumentException("Achievement cannot be null!");
        }
        synchronized (this.b) {
            if (this.c) {
                this.b.add(aVar);
            } else {
                this.c = true;
                new Thread(new Runnable() { // from class: com.topfreegames.bikerace.a.e.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (e.this.f817a != null) {
                            e.this.f817a.a(aVar, e.this);
                        }
                    }
                }).start();
            }
        }
    }

    @Override // com.topfreegames.bikerace.a.j
    public void b(a aVar) {
        synchronized (this.b) {
            if (this.b.peek() != null) {
                new Thread(new Runnable() { // from class: com.topfreegames.bikerace.a.e.2
                    @Override // java.lang.Runnable
                    public void run() {
                        if (e.this.f817a != null) {
                            e.this.f817a.a((a) e.this.b.remove(), e.this);
                        }
                    }
                }).start();
            } else {
                this.c = false;
            }
        }
    }
}
