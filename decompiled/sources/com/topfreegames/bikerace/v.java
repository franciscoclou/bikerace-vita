package com.topfreegames.bikerace;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.SparseBooleanArray;
import com.topfreegames.bikerace.activities.BikeRaceApplication;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

/* JADX INFO: compiled from: GameAudio.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class v {
    private static /* synthetic */ int[] n;
    private static /* synthetic */ int[] o;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private z f1354a;
    private Context l;
    private HashMap<w, x> b = new HashMap<>();
    private HashMap<w, Integer> c = new HashMap<>();
    private HashMap<w, Integer> d = new HashMap<>();
    private HashMap<w, Float> e = new HashMap<>();
    private SparseBooleanArray f = new SparseBooleanArray();
    private MediaPlayer g = null;
    private SoundPool h = null;
    private y i = y.STOPPED;
    private float j = 0.0f;
    private float k = 0.0f;
    private Random m = new Random();

    static /* synthetic */ int[] p() {
        int[] iArr = n;
        if (iArr == null) {
            iArr = new int[b.valuesCustom().length];
            try {
                iArr[b.ACCELERATING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[b.BRAKING.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[b.CRASHED.ordinal()] = 4;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[b.IDLE.ordinal()] = 3;
            } catch (NoSuchFieldError e4) {
            }
            n = iArr;
        }
        return iArr;
    }

    static /* synthetic */ int[] q() {
        int[] iArr = o;
        if (iArr == null) {
            iArr = new int[y.valuesCustom().length];
            try {
                iArr[y.ACCELERATING.ordinal()] = 3;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[y.DEACCELERATING.ordinal()] = 5;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[y.FAST.ordinal()] = 4;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[y.SLOW.ordinal()] = 2;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[y.STOPPED.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            o = iArr;
        }
        return iArr;
    }

    public v(Context context, z zVar) {
        this.f1354a = null;
        this.l = null;
        this.f1354a = zVar;
        this.l = context.getApplicationContext();
        c(context);
    }

    public synchronized void a() {
        b();
        h();
    }

    public synchronized void b() {
        if (this.g != null) {
            try {
                if (this.g.isPlaying()) {
                    this.g.pause();
                }
            } catch (IllegalStateException e) {
            }
        } else {
            ((BikeRaceApplication) this.l).d().a(getClass().getName(), "Null menu music");
        }
    }

    public synchronized void a(a aVar, boolean z, float f) {
        if (aVar != null) {
            if (this.f1354a.g()) {
                if (this.e == null) {
                    throw new IllegalStateException("Sounds were not previosly loaded!");
                }
                b bVarF = aVar.f();
                try {
                    if (bVarF != b.CRASHED) {
                        this.j += f;
                        switch (p()[bVarF.ordinal()]) {
                            case 1:
                                float fC = aVar.c.b.c();
                                if (this.i == y.STOPPED) {
                                    this.i = y.SLOW;
                                    this.j = 0.0f;
                                } else if (this.i != y.SLOW) {
                                    if (this.i == y.ACCELERATING) {
                                        Float f2 = this.e.get(w.BIKE_ENGINE_MEDIUM_HI);
                                        if (f2 == null) {
                                            ((BikeRaceApplication) this.l.getApplicationContext()).d().a(getClass().getName(), "Null duration of medium_hi");
                                        } else if (this.j >= f2.floatValue() * 0.95f) {
                                            this.i = y.FAST;
                                            this.j = 0.0f;
                                        }
                                    } else if (this.i != y.FAST) {
                                        if (this.i == y.DEACCELERATING) {
                                            Float f3 = this.e.get(w.BIKE_ENGINE_HI_MEDIUM);
                                            if (f3 == null) {
                                                ((BikeRaceApplication) this.l.getApplicationContext()).d().a(getClass().getName(), "Null duration of hi_medium");
                                            } else if (this.j >= f3.floatValue() * 0.95f) {
                                                this.i = y.SLOW;
                                            }
                                        }
                                    } else if (fC < 2.8f && z) {
                                        this.i = y.DEACCELERATING;
                                        this.j = 0.0f;
                                    }
                                } else if (fC >= 4.5f || !z) {
                                    this.i = y.ACCELERATING;
                                    this.j = 0.0f;
                                }
                                break;
                            default:
                                this.i = y.STOPPED;
                                break;
                        }
                    }
                    switch (q()[this.i.ordinal()]) {
                        case 1:
                            Integer num = this.d.get(w.BIKE_ENGINE_LOW);
                            if (num != null && num.intValue() > 0) {
                                this.h.resume(num.intValue());
                            } else {
                                a(w.BIKE_ENGINE_LOW, true, 0.8f);
                            }
                            c(w.BIKE_ENGINE_MEDIUM_HI);
                            c(w.BIKE_ENGINE_HI_MEDIUM);
                            c(w.BIKE_ENGINE_MEDIUM);
                            c(w.BIKE_ENGINE_HI);
                            break;
                        case 2:
                            Integer num2 = this.d.get(w.BIKE_ENGINE_MEDIUM);
                            if (num2 != null && num2.intValue() > 0) {
                                this.h.resume(num2.intValue());
                            } else {
                                a(w.BIKE_ENGINE_MEDIUM, true);
                            }
                            c(w.BIKE_ENGINE_MEDIUM_HI);
                            c(w.BIKE_ENGINE_HI_MEDIUM);
                            c(w.BIKE_ENGINE_LOW);
                            c(w.BIKE_ENGINE_HI);
                            break;
                        case 3:
                            Integer num3 = this.d.get(w.BIKE_ENGINE_MEDIUM_HI);
                            if (num3 != null && num3.intValue() > 0) {
                                this.h.resume(num3.intValue());
                            } else {
                                a(w.BIKE_ENGINE_MEDIUM_HI, false);
                            }
                            c(w.BIKE_ENGINE_HI_MEDIUM);
                            c(w.BIKE_ENGINE_LOW);
                            c(w.BIKE_ENGINE_MEDIUM);
                            c(w.BIKE_ENGINE_HI);
                            break;
                        case 4:
                            Integer num4 = this.d.get(w.BIKE_ENGINE_HI);
                            if (num4 != null && num4.intValue() > 0) {
                                this.h.resume(num4.intValue());
                            } else {
                                a(w.BIKE_ENGINE_HI, true);
                            }
                            c(w.BIKE_ENGINE_MEDIUM_HI);
                            c(w.BIKE_ENGINE_HI_MEDIUM);
                            c(w.BIKE_ENGINE_LOW);
                            c(w.BIKE_ENGINE_MEDIUM);
                            break;
                        case 5:
                            Integer num5 = this.d.get(w.BIKE_ENGINE_HI_MEDIUM);
                            if (num5 != null && num5.intValue() > 0) {
                                this.h.resume(num5.intValue());
                            } else {
                                a(w.BIKE_ENGINE_HI_MEDIUM, false);
                            }
                            c(w.BIKE_ENGINE_MEDIUM_HI);
                            c(w.BIKE_ENGINE_LOW);
                            c(w.BIKE_ENGINE_MEDIUM);
                            c(w.BIKE_ENGINE_HI);
                            break;
                    }
                } catch (Error e) {
                    if (ap.d()) {
                        e.printStackTrace();
                    }
                    ((BikeRaceApplication) this.l).d().a(getClass().getName(), "playEngineSoundFx ", e);
                    throw e;
                } catch (Exception e2) {
                    if (ap.d()) {
                        e2.printStackTrace();
                    }
                    ((BikeRaceApplication) this.l).d().a(getClass().getName(), "playEngineSoundFx ", e2);
                }
            }
        }
    }

    public synchronized void c() {
        try {
            try {
                if (this.f1354a.g()) {
                    b(w.BIKE_ENGINE_HI);
                    b(w.BIKE_ENGINE_MEDIUM);
                    b(w.BIKE_ENGINE_MEDIUM_HI);
                    b(w.BIKE_ENGINE_HI_MEDIUM);
                    Integer num = this.d.get(w.BIKE_ENGINE_LOW);
                    if (num != null && num.intValue() > 0) {
                        this.h.resume(num.intValue());
                    } else {
                        a(w.BIKE_ENGINE_LOW, true, 1.0f);
                    }
                }
            } catch (Error e) {
                if (ap.d()) {
                    e.printStackTrace();
                }
                ((BikeRaceApplication) this.l).d().a(getClass().getName(), "playEngineSoundFx ", e);
                throw e;
            }
        } catch (Exception e2) {
            if (ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) this.l).d().a(getClass().getName(), "playEngineSoundFx ", e2);
        }
    }

    public synchronized void d() {
        a(w.EXPLOSION, false);
    }

    public synchronized void a(float f, float f2) {
        if (f >= 300.0f && f2 < 0.6f) {
            Float f3 = this.e.get(w.FALL_IMPACT);
            float fCurrentTimeMillis = System.currentTimeMillis() / 1000.0f;
            if (f3 != null) {
                if (fCurrentTimeMillis > f3.floatValue() + this.k) {
                    this.k = fCurrentTimeMillis;
                    a(w.FALL_IMPACT, false);
                }
            } else {
                this.k = fCurrentTimeMillis;
                a(w.FALL_IMPACT, false);
            }
        }
    }

    public synchronized void e() {
        try {
            if (a(w.MENU) && !this.g.isPlaying()) {
                this.g.start();
            }
        } catch (Exception e) {
            try {
                c(this.l);
                this.g.start();
            } catch (Exception e2) {
                if (ap.d()) {
                    e2.printStackTrace();
                }
                ((BikeRaceApplication) this.l).d().a(getClass().getName(), "playMenuMusic", e2);
            }
        }
    }

    public synchronized void f() {
        a(w.WIN, false);
    }

    public synchronized void g() {
        switch (this.m.nextInt(4)) {
            case 0:
                a(w.HALLOWEEN_BELL, false);
                break;
            case 1:
                a(w.HALLOWEEN_RAVEN, false);
                break;
            case 2:
                a(w.HALLOWEEN_SCREAM, false);
                break;
            case 3:
                a(w.HALLOWEEN_WITCH, false);
                break;
        }
    }

    public synchronized void a(Context context) {
        if (this.h == null) {
            k();
            d(context);
        }
    }

    public synchronized void h() {
        Iterator<w> it = this.d.keySet().iterator();
        while (it.hasNext()) {
            c(it.next());
        }
    }

    public synchronized void i() {
        k();
        j();
    }

    public synchronized void j() {
        if (this.g != null) {
            try {
                this.g.reset();
            } catch (Exception e) {
            }
            this.g.release();
        }
    }

    public synchronized void k() {
        if (this.h != null) {
            this.h.release();
            this.h = null;
        }
        if (this.d != null) {
            this.d.clear();
        }
        if (this.f != null) {
            this.f.clear();
        }
    }

    private synchronized boolean a(w wVar) {
        boolean zG;
        x xVar = this.b.get(wVar);
        if (xVar == x.MUSIC) {
            zG = this.f1354a.b();
        } else if (xVar == x.SOUND_FX) {
            zG = this.f1354a.g();
        } else {
            zG = false;
        }
        return zG;
    }

    private synchronized void c(Context context) {
        try {
            if (com.topfreegames.bikerace.n.a.a().a("kABTEST_musica_menu") == 0) {
                this.g = MediaPlayer.create(context, 2131034117);
            } else {
                this.g = MediaPlayer.create(context, 2131034118);
            }
            this.g.setLooping(true);
            this.b.put(w.MENU, x.MUSIC);
        } catch (Exception e) {
            if (ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) this.l).d().a(getClass().getName(), "loadMusics", e);
        }
    }

    private synchronized void a(Context context, w wVar, int i, x xVar) {
        Error error;
        Exception exc;
        MediaPlayer mediaPlayerCreate;
        MediaPlayer mediaPlayer = null;
        synchronized (this) {
            try {
                try {
                    try {
                        if (this.e.get(wVar) == null) {
                            try {
                                mediaPlayerCreate = MediaPlayer.create(context, i);
                                try {
                                    mediaPlayerCreate.setDisplay(null);
                                } catch (Error e) {
                                    mediaPlayer = mediaPlayerCreate;
                                    error = e;
                                    if (ap.d()) {
                                        error.printStackTrace();
                                    }
                                    ((BikeRaceApplication) this.l).d().a(getClass().getName(), "loadSingleAudio: " + wVar.ordinal() + " ", error);
                                    if (mediaPlayer != null) {
                                        mediaPlayer.reset();
                                        mediaPlayer.release();
                                        throw error;
                                    }
                                    throw error;
                                }
                            } catch (Exception e2) {
                                mediaPlayerCreate = null;
                            }
                            if (mediaPlayerCreate == null) {
                                try {
                                    Thread.sleep(250L);
                                    mediaPlayerCreate = MediaPlayer.create(context, i);
                                    mediaPlayerCreate.setDisplay(null);
                                } catch (Exception e3) {
                                    mediaPlayerCreate = null;
                                }
                            }
                            if (mediaPlayerCreate != null) {
                                this.e.put(wVar, Float.valueOf(mediaPlayerCreate.getDuration() / 1000.0f));
                                mediaPlayerCreate.reset();
                                mediaPlayerCreate.release();
                                mediaPlayerCreate = null;
                            } else {
                                ((BikeRaceApplication) this.l).d().a(getClass().getName(), "Null audio media (" + wVar.toString() + ")");
                            }
                        } else {
                            mediaPlayerCreate = null;
                        }
                        this.b.put(wVar, xVar);
                        this.d.put(wVar, -1);
                        int iLoad = this.h.load(context, i, 1);
                        this.f.put(iLoad, false);
                        this.c.put(wVar, Integer.valueOf(iLoad));
                    } catch (Exception e4) {
                        mediaPlayer = mediaPlayerCreate;
                        exc = e4;
                        if (ap.d()) {
                            exc.printStackTrace();
                        }
                        ((BikeRaceApplication) this.l).d().a(getClass().getName(), "loadSingleAudio: " + wVar.ordinal() + " ", exc);
                        if (mediaPlayer != null) {
                            mediaPlayer.reset();
                            mediaPlayer.release();
                        }
                    }
                } catch (Exception e5) {
                    exc = e5;
                }
            } catch (Error e6) {
                error = e6;
            }
        }
    }

    private synchronized void d(Context context) {
        if (context != null) {
            if (this.f1354a.g()) {
                this.h = new SoundPool(4, 3, 0);
                this.h.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() { // from class: com.topfreegames.bikerace.v.1
                    @Override // android.media.SoundPool.OnLoadCompleteListener
                    public void onLoadComplete(SoundPool soundPool, int i, int i2) {
                        v.this.f.put(i, i2 == 0);
                    }
                });
                a(context, w.BIKE_ENGINE_LOW, 2131034124, x.SOUND_FX);
                a(context, w.BIKE_ENGINE_MEDIUM, 2131034125, x.SOUND_FX);
                a(context, w.BIKE_ENGINE_MEDIUM_HI, 2131034126, x.SOUND_FX);
                a(context, w.BIKE_ENGINE_HI, 2131034122, x.SOUND_FX);
                a(context, w.BIKE_ENGINE_HI_MEDIUM, 2131034123, x.SOUND_FX);
                a(context, w.EXPLOSION, 2131034115, x.SOUND_FX);
                a(context, w.FALL_IMPACT, 2131034119, x.SOUND_FX);
                a(context, w.WIN, 2131034130, x.SOUND_FX);
                a(context, w.HALLOWEEN_BELL, 2131034128, x.SOUND_FX);
                a(context, w.HALLOWEEN_RAVEN, 2131034113, x.SOUND_FX);
                a(context, w.HALLOWEEN_SCREAM, 2131034116, x.SOUND_FX);
                a(context, w.HALLOWEEN_WITCH, 2131034114, x.SOUND_FX);
            }
        }
    }

    public synchronized void b(Context context) {
        if (context != null) {
            if (this.f1354a.g()) {
                k();
                this.h = new SoundPool(4, 3, 0);
                this.h.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() { // from class: com.topfreegames.bikerace.v.2
                    @Override // android.media.SoundPool.OnLoadCompleteListener
                    public void onLoadComplete(SoundPool soundPool, int i, int i2) {
                        v.this.f.put(i, i2 == 0);
                    }
                });
                a(context, w.WORLDCUP_SLOT_BUTTON, 2131034121, x.SOUND_FX);
                a(context, w.WORLDCUP_SLOT_RUNNING, 2131034129, x.SOUND_FX);
                a(context, w.WORLDCUP_SLOT_STOPPING, 2131034120, x.SOUND_FX);
                a(context, w.WORLDCUP_SLOT_COLLECT_PART, 2131034112, x.SOUND_FX);
                a(context, w.WORLDCUP_SLOT_COLLECT_MONEY, 2131034127, x.SOUND_FX);
            }
        }
    }

    public synchronized void l() {
        a(w.WORLDCUP_SLOT_BUTTON, false);
        a(w.WORLDCUP_SLOT_RUNNING, true);
    }

    public synchronized void m() {
        c(w.WORLDCUP_SLOT_BUTTON);
        c(w.WORLDCUP_SLOT_RUNNING);
        a(w.WORLDCUP_SLOT_STOPPING, false);
    }

    public synchronized void n() {
        a(w.WORLDCUP_SLOT_COLLECT_PART, false);
    }

    public synchronized void o() {
        a(w.WORLDCUP_SLOT_COLLECT_MONEY, false);
    }

    private synchronized void b(w wVar) {
        Integer num;
        try {
            if (a(wVar) && (num = this.d.get(wVar)) != null && num.intValue() > 0) {
                this.h.pause(num.intValue());
            }
        } catch (Error e) {
            if (ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) this.l).d().a(getClass().getName(), "pause: " + wVar.ordinal() + " ", e);
            throw e;
        } catch (Exception e2) {
            if (ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) this.l).d().a(getClass().getName(), "pause: " + wVar.ordinal() + " ", e2);
        }
    }

    private synchronized void a(w wVar, boolean z) {
        a(wVar, z, 0.99f);
    }

    private synchronized void a(w wVar, boolean z, float f) {
        try {
            if (a(wVar) && this.h != null) {
                int iIntValue = this.c.get(wVar).intValue();
                Boolean boolValueOf = Boolean.valueOf(this.f.get(iIntValue));
                if (boolValueOf != null && boolValueOf.booleanValue()) {
                    int iPlay = this.h.play(iIntValue, f, f, 1, z ? -1 : 0, 1.0f);
                    this.d.put(wVar, Integer.valueOf(iPlay));
                    if (z) {
                        this.h.setPriority(iPlay, 10);
                    }
                }
            }
        } catch (Error e) {
            if (ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) this.l).d().a(getClass().getName(), "play: " + wVar.ordinal() + " ", e);
            throw e;
        } catch (Exception e2) {
            if (ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) this.l).d().a(getClass().getName(), "play: " + wVar.ordinal() + " ", e2);
        }
    }

    private synchronized void c(w wVar) {
        Integer num;
        try {
            try {
                if (a(wVar) && (num = this.d.get(wVar)) != null && num.intValue() > 0) {
                    this.h.stop(num.intValue());
                    this.d.put(wVar, -1);
                }
            } catch (Exception e) {
                if (ap.d()) {
                    e.printStackTrace();
                }
                ((BikeRaceApplication) this.l).d().a(getClass().getName(), "stop: " + wVar.ordinal() + " ", e);
            }
        } catch (Error e2) {
            if (ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) this.l).d().a(getClass().getName(), "stop: " + wVar.ordinal() + " ", e2);
            throw e2;
        }
    }
}
