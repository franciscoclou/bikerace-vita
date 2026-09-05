package com.topfreegames.bikerace;

import android.content.Context;
import android.util.SparseArray;
import com.amazonaws.javax.xml.stream.XMLStreamConstants;
import com.topfreegames.bikerace.activities.BikeRaceApplication;
import com.topfreegames.engine.data.DataNode;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;

/* JADX INFO: compiled from: GameData.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class z {
    private static final int A;
    private static final int B;
    private static final int C;
    private static final int D;
    private static final int E;
    private static final int F;
    private static final int G;
    private static final int H;
    private static final int I;
    private static final int J;
    private static final int K;
    private static final int L;
    private static final int M;
    private static final int N;
    private static final int O;
    private static final int P;
    private static final int Q;
    private static final int R;
    private static final int S;
    private static final int T;
    private static final int U;
    private static final int V;
    private static final int W;
    private static final int X;
    private static final int Y;
    private static final int Z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ boolean f1485a;
    private static final int aA;
    private static final int aB;
    private static final int aC;
    private static final int aD;
    private static final int aE;
    private static final int aF;
    private static final int aG;
    private static final int aH;
    private static final int aI;
    private static final int aJ;
    private static final int aK;
    private static final int aL;
    private static final int aM;
    private static final int aN;
    private static final int aO;
    private static final int aP;
    private static int aQ;
    private static int aR;
    private static final Map<com.topfreegames.bikerace.worldcup.a, String> aS;
    private static final SparseArray<String> aT;
    private static ArrayList<Integer> aU;
    private static ArrayList<int[]> aV;
    private static boolean aW;
    private static boolean aX;
    private static boolean aY;
    private static boolean aZ;
    private static final int aa;
    private static final int ab;
    private static final int ac;
    private static final int ad;
    private static final int ae;
    private static final int af;
    private static final int ag;
    private static final int ah;
    private static final int ai;
    private static final int aj;
    private static final int ak;
    private static final int al;
    private static final int am;
    private static final int an;
    private static final int ao;
    private static final int ap;
    private static final int aq;
    private static final int ar;
    private static final int as;
    private static final int at;
    private static final int au;
    private static final int av;
    private static final int aw;
    private static final int ax;
    private static final int ay;
    private static final int az;
    private static HashSet<Integer> b;
    private static /* synthetic */ int[] bf;
    private static Random c;
    private static final int d;
    private static final int e;
    private static final int f;
    private static final int g;
    private static final int h;
    private static final int i;
    private static final int j;
    private static final int k;
    private static final int l;
    private static final int m;
    private static final int n;
    private static final int o;
    private static final int p;
    private static final int q;
    private static final int r;
    private static final int s;
    private static final int t;
    private static final int u;
    private static final int v;
    private static final int w;
    private static final int x;
    private static final int y;
    private static final int z;
    private Context ba;
    private boolean bb;
    private ab bc = new ab(this);
    private volatile DataNode bd = null;
    private volatile DataNode be = null;

    static {
        f1485a = !z.class.desiredAssertionStatus();
        b = new HashSet<>();
        c = new Random();
        d = N();
        e = N();
        f = N();
        g = N();
        h = N();
        i = N();
        j = N();
        k = N();
        l = N();
        m = N();
        n = N();
        o = N();
        p = N();
        q = N();
        r = N();
        s = N();
        t = N();
        u = N();
        v = N();
        w = N();
        x = N();
        y = N();
        z = N();
        A = N();
        B = N();
        C = N();
        D = N();
        E = N();
        F = N();
        G = N();
        H = N();
        I = N();
        J = N();
        K = N();
        L = N();
        M = N();
        N = N();
        O = N();
        P = N();
        Q = N();
        R = N();
        S = N();
        T = N();
        U = N();
        V = N();
        W = N();
        X = N();
        Y = N();
        Z = N();
        aa = N();
        ab = N();
        ac = N();
        ad = N();
        ae = N();
        af = N();
        ag = N();
        ah = N();
        ai = N();
        aj = N();
        ak = N();
        al = N();
        am = N();
        an = N();
        ao = N();
        ap = N();
        aq = N();
        ar = N();
        as = N();
        at = N();
        au = N();
        av = N();
        aw = N();
        ax = N();
        ay = N();
        az = N();
        aA = N();
        aB = N();
        aC = N();
        aD = N();
        aE = N();
        aF = N();
        aG = N();
        aH = N();
        aI = N();
        aJ = N();
        aK = N();
        aL = N();
        aM = N();
        aN = N();
        aO = N();
        aP = N();
        aQ = N();
        aR = aQ;
        aU = new ArrayList<>();
        aV = new ArrayList<>();
        HashMap map = new HashMap();
        map.put(Integer.valueOf(d), new byte[]{-103, 93, -54, -36, -14, 36, -4, -58, -53, 17, -8, 20, -6, -79, -4, 12, -73, 58, -3, 71, -37, -23, -19, 66, -8, 127, -16});
        map.put(Integer.valueOf(e), new byte[]{105, -30, -8, 2, 85, 12, 59, 59, 13, 8, -12, 10, 108, 12, 90, 71, -61, 108, 43, -118, 0, -106, 25, 26, 37, 30, 113});
        map.put(Integer.valueOf(f), new byte[]{-22, -23, 62, 40, -125, -125, -127, 82, -113, 33, -72, 54, -117, 110, -121, -2, -102, 88, -119, 62, -113, -119, -60, 111, -98, -6, -113, -66, -88});
        map.put(Integer.valueOf(g), new byte[]{-61, 114, -24, 53, -127, -88, -30, -90, 86, -111, -76, -94, -106, -96, 68, -90, -126, -19, 75, -73, 53, -82, -18, -77, -86, 82, -126});
        map.put(Integer.valueOf(h), new byte[]{-6, 26, -3, -59, -72, -65, -109, 1, -111, 65, -97, -78, -66, -28, -101, -67, -114, 30, -101, 101, -88, -39, -101, 14, -103, -15, -97});
        map.put(Integer.valueOf(i), new byte[]{63, 100, -56, 87, 65, 94, 108, 67, 79, 80, -70});
        map.put(Integer.valueOf(j), new byte[]{-112, -128, -83, -31, -2, 51, -34, 103, -1, -71, -47, 73, -12, 41, -29, 93, -4, 121, -1, -16, -13, -104, -5, 58, -11, -7, -12, -113, -59});
        map.put(Integer.valueOf(k), new byte[]{124, 22, 50, 125, 23, 65, 25, -34, 62, 118, 23, -105, 25, 18, 24, -81, 48, -27, 19, -20, 31, 94, 21});
        map.put(Integer.valueOf(l), new byte[]{107, -23, -36, -102, 41, 57, 2, -67, 14, -125, 7, 92, 14, -128, 8, 30, 31, -33, 14, 27, 15, 56, 14, -85, 56, -92, 0});
        map.put(Integer.valueOf(m), new byte[]{-110, -49, 121, 79, -48, -7, 107, -9, -110, -51, -45, -39, 36, -5, 18, -10, -112, -31, -5, 50, -48});
        map.put(Integer.valueOf(n), new byte[]{43, 53, 65, 12, 105, -56, 66, -2, 64, -89, 78, -113, 116, 31, 120, -68, 94, -90, 91, -75, 78, 88, 89});
        map.put(Integer.valueOf(o), new byte[]{-72, -128, 31, 47, -25, -63, -1, -36, -6, 13, -47, -89, -45, -17, -41, 41, -53, -11, -52, 27, -48, -2, -35});
        map.put(Integer.valueOf(p), new byte[]{99, -74, 101, -2, 33, -29, 10, -19, 8, -76, 6, -55, 60, -10, 36, -83, 10, -30, 17, -5, 15});
        map.put(Integer.valueOf(q), new byte[]{40, -12, 23, -10, 106, 74, 65, -25, 67, -36, 77, -66, 119, -34, 122, 38, 77, -8, 92, -114, 90, 73, 71});
        map.put(Integer.valueOf(r), new byte[]{114, -68, 106, -18, 17, 18, 0, 111, 48, 106, 27, 107, 25, -121, 23, 122, 45, 119, 16, -77, 19, -30, 6, 115, 27, -114, 17, 3, 29, -45, 51});
        map.put(Integer.valueOf(s), new byte[]{115, -125, 25, -31, 22, 59, 49, -61, 26, -113, 28, -77, 29, -17, 9, -30, 22, -86, 44, -110, 49, 58, 1, 92, 24});
        map.put(Integer.valueOf(t), new byte[]{91, -114, -96, 58, -64, 48, -126, 62, -101, 4, -41, 21, -13, 50, -61, 53, 122, 49, 122, 58, 50, -74, 25});
        map.put(Integer.valueOf(u), new byte[]{1, -49, -63, 119, 106, 114, 100, 24, 94, -80, 71, 33, 116, 97, 117, 75, 116, 68, 67, -97, 115, 87, 100, 24, 104});
        map.put(Integer.valueOf(w), new byte[]{-53, 82, -95, 125, -94, -56, -82, -110, -108, -41, -104, 86, -94, 99, -89, 118, -67, -47, -82, -45, -71, -87, -96, 83, -119});
        map.put(Integer.valueOf(x), new byte[]{-26, -122, -55, 99, -125, -22, -71, -111, -92, -15, -92, -73, -113, 91, -110, -41, -125, 29, -121, 81, -115});
        map.put(Integer.valueOf(y), new byte[]{27, 116, -30, 7, 89, -89, 114, -109, 112, -2, 126, 127, 68, 39, 92, -114, 116, -29, 119, 96, 48});
        map.put(Integer.valueOf(v), new byte[]{93, 54, 65, 50, 52, -4, 56, -4, 2, -6, 13, -118, 50, 100, 49, -124, 52, 59, 62, 114, 56, -127, 54, 50, 31});
        map.put(Integer.valueOf(z), new byte[]{-27, -82, 90, 15, -89, -66, -116, -70, -99, -80, -104, -119, -39, -111, -79, -105, 56, -124, -114, -22, -128, 102, 80});
        map.put(Integer.valueOf(A), new byte[]{97, -101, -5, -126, 35, -46, 8, -19, 10, 36, 4, 94, 62, 64, 59, 37, 14, -75, 12, 55, 3, -45, 8, 45, 4});
        map.put(Integer.valueOf(B), new byte[]{74, -80, -79, -46, 35, -31, 33, 55, 47, 108, 21, -11, 11, 66, 56, 78, 39, -24, 51, -59, 8});
        map.put(Integer.valueOf(C), new byte[]{125, -24, 119, 63, 22, 54, 63, 37, 53, -114, 28, 88, 17, -98, 17, 41, 18, 48, 10, 94, 24, 72, 24, 61, 19, -33, 24, 80, 34, -98, 20});
        map.put(Integer.valueOf(E), new byte[]{68, -108, -126, -91, 6, -49, 45, -90, 47, 97, 33, 106, 27, -36, 12, -60, 43, -59, 40, -90, 45, -48, 32, -2, 37, 103, 61});
        map.put(Integer.valueOf(D), new byte[]{-34, -69, 8, 35, -118, 117, -74, 70, -65, 2, -80, -91, -75, 49, -83, -114, -71, 9, -100, 86, -73, 125, -75, 27, -69, -40, -88, 122, -73, 23, -80, 125, -71, -40, -73, 7, -127});
        map.put(Integer.valueOf(F), new byte[]{-106, -34, 81, -124, -3, 23, -44, -65, -45, 24, -9, -46, -27, 3, -30, 60, -13, 40, -28, -11, -13, 76, -55, 44, -1});
        map.put(Integer.valueOf(G), new byte[]{-66, 103, -6, -124, -43, 106, -37, -43, -31, 60, -6, 42, -33, -128, -41, -19, -46, 107, -57, -101, -4, 38, -47, -77, -48, -113, -4, -31, -53, 18, -51, 39, -41});
        map.put(Integer.valueOf(H), new byte[]{-108, -28, -95, 119, -42, 57, -3, -110, -1, -11, -15, 19, -53, 36, -61, -2, -5, -75, -11, 51, -8, -82, -16, -46, -41, 59, -31, -55, -28, 121, -47, 113, -31, -125, -26});
        map.put(Integer.valueOf(I), new byte[]{-42, -99, -50, -125, -108, 9, -65, -118, -67, 55, -77, 107, -119, -7, -127, -30, -71, -13, -92, -88, -70, 67, -78, -125, -107, 3, -93, 106, -90, -18, -125, -79, -67});
        map.put(Integer.valueOf(J), new byte[]{-12, 116, 14, -55, -104, -116, -112, 42, -73, -125, -127, 8, -124, -112, -70, -37, -111, 108, -128, 13, -74, -74, -99, -96, -97, 70, -111, 67, -85, -92, -93, 32, -101, 84, -121, -109, -100, -56, -111, 20, -122, -106, -104, 45, -107, 109, -102, 26, -112, -65, -122});
        map.put(Integer.valueOf(K), new byte[]{-106, 62, 97, 112, -44, 110, -13, -5, -55, 82, -63, -42, -7, 41, -28, 40, -6, 82, -14, 97, -43, 107, -29, 70, -26, 14, -41, 96, -29, -103, -27, -38, -30, -40, -28, 30, -9, -58, -6, -115, -1, -22, -9, -89, -3, -44, -1});
        map.put(Integer.valueOf(L), new byte[]{-4, -12, 99, -9, -107, 58, -105, 127, -103, 26, -93, -48, -85, -68, -109, 101, -114, 126, -112, -124, -97, -11, -103, 8, -104, -18, -65, 10, -119, 11, -116, 102, -70, -44, -114, -110, -99, -9, -110, 81, -66});
        map.put(Integer.valueOf(M), new byte[]{72, 89, -127, -126, 45, -48, 23, 78, 31, -85, 39, 112, 58, -120, -57, 10, 47, 33, -121, 37, -63, 41, 0, 38, 13, 49, 36, 7, 44, 83, 11, -106, 61, -51, 56, 43, 15, -102, 45, -33, 58, -33, 35});
        map.put(Integer.valueOf(N), new byte[]{97, 64, 19, -4, 35, 90, 8, -69, 10, 108, 4, -32, 62, -90, 54, 99, 14, 56, 19, 105, 13, 28, 5, -17, 34, -31, 20, 83, 17, -70, 35, 32, 19, -31, 0, -8, 27, 15, 8, -105, 13});
        map.put(Integer.valueOf(O), new byte[]{69, -43, -86, -107, 7, 115, 44, 0, 46, -14, 32, 28, 26, 16, 18, -90, 42, 97, 55, 105, 41, 23, 33, -80, 6, 81, 48, 92, 53, 119, 22, 88, 53, -113, 36, 52, 44, 22, 43});
        map.put(Integer.valueOf(P), new byte[]{-103, -61, -56, -29, -37, -31, -16, -57, -14, -59, -4, -120, -58, -87, -50, 125, -10, 83, -21, -45, -9, 28, -3, -76, -38, -114, -20, -119, -23, 117, -45, -35, -8, -125, -23, 74, -8, -20, -11});
        map.put(Integer.valueOf(Q), new byte[]{1, -125, 45, 75, 67, 77, 104, 23, 106, 92, 100, -61, 94, -45, 86, 20, 110, 38, 115, 38, 109, -48, 101, -70, 66, 11, 116, -18, 113, 15, 64, 51, 115, -34, 102, 111, 100, 104, 111, -13, 117, 8, 104, 23, 111, 1, 96});
        map.put(Integer.valueOf(R), new byte[]{84, -71, -8, 65, 22, -123, 61, -3, 63, 101, 49, -40, 11, 69, 33, -47, 36, -75, 29, 58, 32, -105, 53, -59, 56, 120, 45, -61, 59, 8, 38, -100, 56, 58, 48, 65, 23, 69, 3});
        map.put(Integer.valueOf(S), new byte[]{-64, 31, 59, -126, -99, -87, -46, -85, 41, -91, -74, -97, -4, -105, -83, -80, 119, -126, 65, -91, 122, -84, -43, -89, -43, -87, 97, -75, -30, -83, -81, -120, -78, -56, -84, -48, -92, -107, -125, 44, -75, -87, -15});
        map.put(Integer.valueOf(T), new byte[]{-116, 107, -127, -50, -65, -27, 41, -25, 124, -23, 13, -45, -117, -37, 62, -29, -95, -2, -53, -32, 0, -24, 28, -49, -77, -7, 85, -4, 72, -63, -35, -23, -110, -12, 64, -27, 122, -17, -57, -29, 47});
        map.put(Integer.valueOf(U), new byte[]{86, -32, -53, -113, 51, 48, 32, -37, 51, 95, 58, -73, 9, 46, 26});
        map.put(Integer.valueOf(V), new byte[]{113, -14, 102, 61, 126, 20, -29, 7, 100, 20, -12, 29, -48, 61, -36, 30, -42, 18, -47, 26, 90, 20, -60, 21, 123});
        map.put(Integer.valueOf(W), new byte[]{-59, -14, 14, 53, -77, 111, -96, 54, -87, 82, -111, -119, 118, -92, 34, -73, 18, -74, -117, 63, -80, -119, -88, 29, -106, -96, -79, 13, -96});
        map.put(Integer.valueOf(X), new byte[]{111, -98, 81, 57, 0, 39, 29, 77, 3, -74, 11, 68, 48, -79, 56});
        map.put(Integer.valueOf(Y), new byte[]{-17, 40, -101, 119, -99, 91, -72, 117, -93, 71, -128, -60, -116, 5, -124, 31, -118, -22, -117, 105, -125, -76, -117, 65, -128});
        map.put(Integer.valueOf(Z), new byte[]{45, -84, -45, -1, 66, -111, 95, 106, 99, -44, 88, -75, 64, -125, 126, -91, 89, 70, 76, 87, 95, -122, 94, 82, 65, -107, 73, -50, 122});
        map.put(Integer.valueOf(aa), new byte[]{104, 84, -74, 28, 43, -115, 7, -110, 6, -59, 14, -127, 1, 126, 15});
        map.put(Integer.valueOf(ab), new byte[]{-118, 35, -122, 46, -1, 112, -7, 97, -29, -12, -23, 19, -57});
        map.put(Integer.valueOf(ac), new byte[]{-45, -61, -62, -41, -68, -19, -90, 117, -107, -77, -85, 37, -67, -108, -73, -84, -128});
        map.put(Integer.valueOf(ad), new byte[]{-84, -12, -126, 50, -4, 7, -39, -52, -33, 81, -60});
        map.put(Integer.valueOf(ae), new byte[]{-55, -63, 40, -63, -89, 119, -82, -48, -91, -103, -84, -55, -114, 70, -95, -88, -90, -8, -102, -37, -70, 91, -67, 12, -96});
        map.put(Integer.valueOf(af), new byte[]{41, -81, 57, -24, 101, -90, 72, -109, 71, -80, 78, 4, 92, 75, 72, 112, 78, 105, 76});
        map.put(Integer.valueOf(ag), new byte[]{70, 8, 57, -20, 39, -89, 21, 59, 50, -53, 53, 28, 50});
        map.put(Integer.valueOf(ah), new byte[]{-117, -43, -57, -50, -30, -26, -27, 66, -8, -117, -33, 36, -28, -117, -1, 111, -22, 18, -25, -3, -36});
        map.put(Integer.valueOf(ai), new byte[]{65, -75, -82, -93, 47, -8, 50, -127, 2, 90, 46, 8, 47, 30, 50, 56, 36, -81, 34, -48, 52, 16, 53, 84, 40, -78, 22, -41, 55, 123, 36, -57, 40});
        map.put(Integer.valueOf(aj), new byte[]{-15, -14, 47, 70, -110, -69, -90, 87, -104, -34, -97, 75, -126, 38, -78, -43, -98, -28, -97, -55, -126, 23, -124, 21, -123, -41, -104, 73, -121, -100, -108, 119, -77, -93, -108, 16, -126, 98, -123, 122, -108});
        map.put(Integer.valueOf(ak), new byte[]{90, 31, 90, 53, 13, -61, 51, -116, 52, -126, 41, 98, 25, 89, 53, 76, 52, 29, 41, -101, 63, 82, 57, 72, 47, 110, 46, -119, 51, -44, 44, 31, 63, -114, 22, 64, 59, 81, 41, -6, 46, 3, 22, 89, 51, -9, 60, 16, 63});
        map.put(Integer.valueOf(al), new byte[]{-61, 60, 23, -27, -90, -99, -83, 29, -114, -113, -112, -69, -89, -10, -112, -72, -109});
        map.put(Integer.valueOf(am), new byte[]{5, 102, -21, -112, 85, -128, 96, -99, 107, 20, 97, 32, 64, 104, 104, -61, 100, 112, 108, 57, 105});
        map.put(Integer.valueOf(an), new byte[]{-63, -89, 52, -66, -109, 121, -92, 94, -75, 70, -77, -27, -88, 72, -92, 12, -78});
        map.put(Integer.valueOf(ao), new byte[]{-66, -123, 114, 71, -14, -101, -33, -21, -33, -86, -39, -10, -37, -83, -3, -41, -42, 103, -33, -48, -48, 34, -39, 123, -37, 77, -38, 117, -39, -86, -53, 0, -48});
        map.put(Integer.valueOf(ap), new byte[]{-107, 8, -86, -2, -5, 91, -14, 74, -32, 59, -61, -39, 102, -27, 5, -32, 85, -27, -12, -70, -14, -24, -16, -97, -59, 6, -6, -95, -12});
        map.put(Integer.valueOf(aq), new byte[]{-86, -19, -93, -8, 121, -53, -128, -34, 38, -61, 88, -60, -102, -51, -81, -19, -35, -59, -72, -60, -32, -49, 6});
        map.put(Integer.valueOf(ar), new byte[]{64, -23, 7, 55, -73, 9, -11, -7, 19, -104, 40, 115, 47, 86, 37, -119, 50, 46, 83, 52, -70});
        map.put(Integer.valueOf(as), new byte[]{117, -31, -116, 112, 34, 22, 25, 77, 17, -65, 54, 15, 0, 19, 5, -63, 7, -106, 26});
        map.put(Integer.valueOf(at), new byte[]{-47, -54, -37, 19, -122, -99, -66, -63, -110, 9, -92, -38, -95, -33, -126, 94, -66, 77, -73, -126, -91, -38, -67, -69, -75, 89, -93});
        map.put(Integer.valueOf(au), new byte[]{-84, -110, 41, -26, -5, 109, -61, 6, -34, -86, -64, 106, -56, -51, -17, 67, -18, 66, -36, 50, -28, -23, -51, -96, -34, 71, -56, 94, -13, -110, -39});
        map.put(Integer.valueOf(av), new byte[]{-120, -95, 11, -6, 115, -28, -109, -20, -98, -53, 10, -3, 115, -8, 48, -64, 6, -23, 18, -6, -97, 94, -33, -95, -25, -41, -109, -35, -20, 58, -103});
        map.put(Integer.valueOf(aw), new byte[]{-109, 106, 32, -31, 97, -1, 95, -9, -104, -48, -20, -26, 8, -29, -95, -37, 104, -14, -58, -31, 12, 86, -60, 56, -4, -52, 50, -48, -9, 59, -105});
        map.put(Integer.valueOf(ax), new byte[]{-68, 34, 114, 118, -45, 31, -50, -24, -48, -100, -40, -8, -20, 63, -29, 102, -1, -113, -55, -19, -52, 53, -2, 79, -21});
        map.put(Integer.valueOf(ay), new byte[]{76, 95, 13, -98, 28, 102, 45, 125, 62, 100, 56, 18, -63});
        map.put(Integer.valueOf(aA), new byte[]{-120, 46, -121, 71, -40, 82, -23, 22, -6, -19, -4, -99, -70});
        map.put(Integer.valueOf(az), new byte[]{-29, 28, -15, 89, -77, 12, -126, -95, -111, 54, -105, 93, -48});
        map.put(Integer.valueOf(aB), new byte[]{5, -54, -114, 85, -3, 119, -90, 113, 5, 49, 100, -100, -7});
        map.put(Integer.valueOf(aC), new byte[]{-10, -39, -52, 0, -125, -123, -123, -79, -73});
        map.put(Integer.valueOf(aD), new byte[]{-18, -35, -109, -120, -84, -108, -100, 36, -89});
        map.put(Integer.valueOf(aE), new byte[]{-47, 61, 87, -55, -94, 42, -80, -126, -124});
        map.put(Integer.valueOf(aF), new byte[]{-79, 28, -49, 60, -9, 65, -61, -34, -48});
        map.put(Integer.valueOf(aG), new byte[]{28, 34, 83, -105, 121, 68, 110, -47, 91});
        map.put(Integer.valueOf(aH), new byte[]{43, 105, 57, 109, 97, 74, 74, -50, 91});
        map.put(Integer.valueOf(aI), new byte[]{47, -52, -24, 97, 106, 74, 69, 91, 103});
        map.put(Integer.valueOf(aJ), new byte[]{121, 46, -78, -122, 42, 24, 9, -89, -20});
        map.put(Integer.valueOf(aK), new byte[]{-53, -102, -27, 89, -114, -76, -91, -92, -84});
        map.put(Integer.valueOf(aL), new byte[]{60, 4, -53, 87, 78, 107, 91, -90, 125});
        map.put(Integer.valueOf(aM), new byte[]{-117, 63, -70, 61, -55, 43, -18, 30, -25});
        map.put(Integer.valueOf(aN), new byte[]{-125, 115, -103, -24, -54, 14, -9, -7, -30});
        map.put(Integer.valueOf(aO), new byte[]{-56, 63, -69, -123, 127, -83, 91, -80, -24});
        map.put(Integer.valueOf(aP), new byte[]{117, 111, 49, 8, 64, -88, 65, 47, 65, -107, 1, -64, 15, 113, 52, -27, 1, 30, 58, 4, 63, 80, 94, -23, 65, 10, 70, 52, 31, 43, 19, -65, 76, 110, 64, -11, 70});
        SparseArray<String> sparseArray = new SparseArray<>();
        for (Map.Entry entry : map.entrySet()) {
            sparseArray.put(((Integer) entry.getKey()).intValue(), b(a((byte[]) entry.getValue())));
        }
        aT = sparseArray;
        aY = false;
        HashMap map2 = new HashMap();
        map2.put(com.topfreegames.bikerace.worldcup.b.BACK, m(ay));
        map2.put(com.topfreegames.bikerace.worldcup.b.HELMET, m(aA));
        map2.put(com.topfreegames.bikerace.worldcup.b.FRONT, m(az));
        map2.put(com.topfreegames.bikerace.worldcup.b.SUIT, m(aB));
        aX = true;
        com.topfreegames.bikerace.worldcup.b[] bVarArr = {com.topfreegames.bikerace.worldcup.b.BACK, com.topfreegames.bikerace.worldcup.b.HELMET, com.topfreegames.bikerace.worldcup.b.FRONT, com.topfreegames.bikerace.worldcup.b.SUIT};
        if (!f1485a && map2.size() != bVarArr.length) {
            throw new AssertionError();
        }
        HashMap map3 = new HashMap();
        map3.put(c.WORLDCUP_AUSTRALIA, m(aC));
        map3.put(c.WORLDCUP_BRAZIL, m(aD));
        map3.put(c.WORLDCUP_USA, m(aE));
        map3.put(c.WORLDCUP_FRANCE, m(aF));
        map3.put(c.WORLDCUP_GERMANY, m(aG));
        map3.put(c.WORLDCUP_JAPAN, m(aH));
        map3.put(c.WORLDCUP_NETHERLANDS, m(aI));
        map3.put(c.WORLDCUP_SPAIN, m(aJ));
        map3.put(c.WORLDCUP_ENGLAND, m(aK));
        map3.put(c.WORLDCUP_ARGENTINA, m(aL));
        map3.put(c.WORLDCUP_BELGIUM, m(aM));
        map3.put(c.WORLDCUP_ITALY, m(aM));
        map3.put(c.WORLDCUP_MEXICO, m(aO));
        aW = true;
        c[] cVarArrA = com.topfreegames.bikerace.worldcup.l.a();
        if (!f1485a && map3.size() != cVarArrA.length) {
            throw new AssertionError();
        }
        aZ = false;
        HashMap map4 = new HashMap();
        for (c cVar : cVarArrA) {
            for (com.topfreegames.bikerace.worldcup.b bVar : bVarArr) {
                map4.put(new com.topfreegames.bikerace.worldcup.a(cVar, bVar), String.valueOf(m(ax)) + ((String) map3.get(cVar)) + ((String) map2.get(bVar)));
            }
        }
        aS = Collections.unmodifiableMap(map4);
        ah();
    }

    static /* synthetic */ int[] M() {
        int[] iArr = bf;
        if (iArr == null) {
            iArr = new int[c.valuesCustom().length];
            try {
                iArr[c.ACROBATIC.ordinal()] = 12;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[c.ARMY.ordinal()] = 17;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[c.BEAT.ordinal()] = 13;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[c.BRONZE.ordinal()] = 8;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[c.COP.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[c.EASTER.ordinal()] = 21;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[c.GHOST.ordinal()] = 4;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr[c.GIRL.ordinal()] = 11;
            } catch (NoSuchFieldError e9) {
            }
            try {
                iArr[c.GOLD.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                iArr[c.HALLOWEEN.ordinal()] = 18;
            } catch (NoSuchFieldError e11) {
            }
            try {
                iArr[c.KIDS.ordinal()] = 3;
            } catch (NoSuchFieldError e12) {
            }
            try {
                iArr[c.NINJA.ordinal()] = 5;
            } catch (NoSuchFieldError e13) {
            }
            try {
                iArr[c.NOT_USED_IOS_DAILY_WORLD_BIKE_INDEX_HOLDER.ordinal()] = 22;
            } catch (NoSuchFieldError e14) {
            }
            try {
                iArr[c.REGULAR.ordinal()] = 1;
            } catch (NoSuchFieldError e15) {
            }
            try {
                iArr[c.RETRO.ordinal()] = 7;
            } catch (NoSuchFieldError e16) {
            }
            try {
                iArr[c.SANTA.ordinal()] = 20;
            } catch (NoSuchFieldError e17) {
            }
            try {
                iArr[c.SILVER.ordinal()] = 9;
            } catch (NoSuchFieldError e18) {
            }
            try {
                iArr[c.SPAM.ordinal()] = 14;
            } catch (NoSuchFieldError e19) {
            }
            try {
                iArr[c.SUPER.ordinal()] = 2;
            } catch (NoSuchFieldError e20) {
            }
            try {
                iArr[c.THANKSGIVING.ordinal()] = 19;
            } catch (NoSuchFieldError e21) {
            }
            try {
                iArr[c.ULTRA.ordinal()] = 15;
            } catch (NoSuchFieldError e22) {
            }
            try {
                iArr[c.WORLDCUP_ARGENTINA.ordinal()] = 35;
            } catch (NoSuchFieldError e23) {
            }
            try {
                iArr[c.WORLDCUP_AUSTRALIA.ordinal()] = 25;
            } catch (NoSuchFieldError e24) {
            }
            try {
                iArr[c.WORLDCUP_BELGIUM.ordinal()] = 32;
            } catch (NoSuchFieldError e25) {
            }
            try {
                iArr[c.WORLDCUP_BRAZIL.ordinal()] = 29;
            } catch (NoSuchFieldError e26) {
            }
            try {
                iArr[c.WORLDCUP_ENGLAND.ordinal()] = 24;
            } catch (NoSuchFieldError e27) {
            }
            try {
                iArr[c.WORLDCUP_FRANCE.ordinal()] = 27;
            } catch (NoSuchFieldError e28) {
            }
            try {
                iArr[c.WORLDCUP_GERMANY.ordinal()] = 28;
            } catch (NoSuchFieldError e29) {
            }
            try {
                iArr[c.WORLDCUP_ITALY.ordinal()] = 34;
            } catch (NoSuchFieldError e30) {
            }
            try {
                iArr[c.WORLDCUP_JAPAN.ordinal()] = 31;
            } catch (NoSuchFieldError e31) {
            }
            try {
                iArr[c.WORLDCUP_MEXICO.ordinal()] = 33;
            } catch (NoSuchFieldError e32) {
            }
            try {
                iArr[c.WORLDCUP_NETHERLANDS.ordinal()] = 26;
            } catch (NoSuchFieldError e33) {
            }
            try {
                iArr[c.WORLDCUP_SPAIN.ordinal()] = 30;
            } catch (NoSuchFieldError e34) {
            }
            try {
                iArr[c.WORLDCUP_USA.ordinal()] = 23;
            } catch (NoSuchFieldError e35) {
            }
            try {
                iArr[c.ZOMBIE.ordinal()] = 16;
            } catch (NoSuchFieldError e36) {
            }
            bf = iArr;
        }
        return iArr;
    }

    private static int N() {
        int iNextInt = 0;
        boolean zAdd = false;
        while (!zAdd) {
            iNextInt = c.nextInt(205093380);
            zAdd = b.add(Integer.valueOf(iNextInt));
        }
        return iNextInt;
    }

    public z(Context context, boolean z2) {
        this.ba = null;
        this.bb = false;
        this.ba = context.getApplicationContext();
        this.bb = z2;
    }

    /* JADX WARN: Code duplicated, block: B:33:0x0089  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v22 */
    /* JADX WARN: Type inference failed for: r0v23 */
    /* JADX WARN: Type inference failed for: r0v24 */
    public synchronized boolean a(c cVar) {
        aa aaVarE;
        DataNode child;
        boolean z2 = true;
        if (ap.v()) {
            z2 = false;
            aaVarE = null;
        } else {
            aaVarE = e(cVar);
        }
        if (aaVarE != null) {
            try {
                child = aaVarE.b.getChild(m(i));
            } catch (NullPointerException e2) {
                b(aaVarE.c);
                if (aaVarE.b == null) {
                    aaVarE.b = h(aaVarE.c);
                }
                child = aaVarE.b.getChild(m(i));
            }
            try {
                if (Z()) {
                    child = z2;
                } else if (aaVarE.f824a == c.REGULAR.ordinal()) {
                    child = false;
                } else {
                    child = child.getChild(m(aaVarE.f824a)).getBoolean(m(k));
                }
            } catch (NullPointerException e3) {
                child.addChild(l(aaVarE.f824a));
                child = true;
            }
        } else {
            child = z2;
        }
        throw th;
        return child.booleanValue();
    }

    public synchronized boolean a(int i2, int i3) {
        DataNode child;
        Boolean boolValueOf;
        boolean zBooleanValue;
        synchronized (this) {
            if (ap.s()) {
                zBooleanValue = false;
            } else {
                try {
                    child = this.bd.getChild(String.valueOf(m(X)) + i2);
                } catch (NullPointerException e2) {
                    if (ap.d()) {
                        e2.printStackTrace();
                    }
                    A();
                    if (this.bd == null) {
                        R();
                    }
                    child = this.bd.getChild(String.valueOf(m(X)) + i2);
                }
                try {
                    boolValueOf = child.getChild(String.valueOf(m(U)) + i3).getBoolean(m(V));
                } catch (NullPointerException e3) {
                    boolValueOf = null;
                }
                if (boolValueOf == null) {
                    boolValueOf = Boolean.valueOf(i3 != 1);
                }
                zBooleanValue = boolValueOf.booleanValue();
            }
        }
        return zBooleanValue;
    }

    public synchronized boolean a() {
        boolean z2 = false;
        synchronized (this) {
            if (!ap.u() && p() < bn.a().f1162a) {
                z2 = true;
            }
        }
        return z2;
    }

    public synchronized boolean b() {
        DataNode child;
        Boolean bool;
        try {
            child = this.bd.getChild(m(aa));
        } catch (NullPointerException e2) {
            if (ap.d()) {
                e2.printStackTrace();
            }
            A();
            if (this.bd == null) {
                R();
            }
            child = this.bd.getChild(m(aa));
        }
        try {
            bool = child.getBoolean(m(ab));
        } catch (NullPointerException e3) {
            bool = true;
            c(bool.booleanValue());
            W();
        }
        return bool.booleanValue();
    }

    public synchronized boolean c() {
        DataNode child;
        Boolean bool;
        boolean zBooleanValue = false;
        synchronized (this) {
            if (!ap.l()) {
                try {
                    child = this.bd.getChild(m(i));
                } catch (NullPointerException e2) {
                    if (ap.d()) {
                        e2.printStackTrace();
                    }
                    A();
                    if (this.bd == null) {
                        R();
                    }
                    child = this.bd.getChild(m(i));
                }
                try {
                    bool = child.getBoolean(m(j));
                } catch (NullPointerException e3) {
                    child.putBoolean(m(j), false);
                    W();
                    bool = false;
                }
                zBooleanValue = bool.booleanValue();
            }
        }
        return zBooleanValue;
    }

    public synchronized boolean d() {
        DataNode child;
        Boolean bool;
        try {
            child = this.bd.getChild(m(aa));
        } catch (NullPointerException e2) {
            if (ap.d()) {
                e2.printStackTrace();
            }
            A();
            if (this.bd == null) {
                R();
            }
            child = this.bd.getChild(m(aa));
        }
        try {
            bool = child.getBoolean(m(ad));
        } catch (NullPointerException e3) {
            bool = null;
        }
        if (bool == null) {
            bool = true;
            d(bool.booleanValue());
        }
        return bool.booleanValue();
    }

    public synchronized c e() {
        DataNode child;
        DataNode child2;
        c cVar;
        try {
            child = this.bd.getChild(m(i));
        } catch (NullPointerException e2) {
            A();
            try {
                child = this.bd.getChild(m(i));
            } catch (NullPointerException e3) {
                R();
                child = this.bd.getChild(m(i));
            }
        }
        try {
            if (child.getChild(m(m)).getBoolean(m(l)).booleanValue()) {
                cVar = c.KIDS;
            } else {
                try {
                    if (child.getChild(m(o)).getBoolean(m(l)).booleanValue()) {
                        cVar = c.GHOST;
                    } else {
                        try {
                            if (child.getChild(m(n)).getBoolean(m(l)).booleanValue()) {
                                cVar = c.SUPER;
                            } else {
                                try {
                                    if (child.getChild(m(p)).getBoolean(m(l)).booleanValue()) {
                                        cVar = c.GIRL;
                                    } else {
                                        try {
                                            if (child.getChild(m(q)).getBoolean(m(l)).booleanValue()) {
                                                cVar = c.RETRO;
                                            } else {
                                                try {
                                                    if (child.getChild(m(r)).getBoolean(m(l)).booleanValue()) {
                                                        cVar = c.ACROBATIC;
                                                    } else {
                                                        try {
                                                            if (child.getChild(m(s)).getBoolean(m(l)).booleanValue()) {
                                                                cVar = c.BRONZE;
                                                            } else {
                                                                try {
                                                                    if (child.getChild(m(t)).getBoolean(m(l)).booleanValue()) {
                                                                        cVar = c.NINJA;
                                                                    } else {
                                                                        try {
                                                                            if (child.getChild(m(u)).getBoolean(m(l)).booleanValue()) {
                                                                                cVar = c.SPAM;
                                                                            } else {
                                                                                try {
                                                                                    if (child.getChild(m(v)).getBoolean(m(l)).booleanValue()) {
                                                                                        cVar = c.COP;
                                                                                    } else {
                                                                                        try {
                                                                                            if (child.getChild(m(w)).getBoolean(m(l)).booleanValue()) {
                                                                                                cVar = c.SILVER;
                                                                                            } else {
                                                                                                try {
                                                                                                    if (child.getChild(m(x)).getBoolean(m(l)).booleanValue()) {
                                                                                                        cVar = c.BEAT;
                                                                                                    } else {
                                                                                                        try {
                                                                                                            if (child.getChild(m(y)).getBoolean(m(l)).booleanValue()) {
                                                                                                                cVar = c.GOLD;
                                                                                                            } else {
                                                                                                                try {
                                                                                                                    child2 = this.be.getChild(m(i));
                                                                                                                } catch (NullPointerException e4) {
                                                                                                                    A();
                                                                                                                    try {
                                                                                                                        child2 = this.be.getChild(m(i));
                                                                                                                    } catch (NullPointerException e5) {
                                                                                                                        O();
                                                                                                                        child2 = this.be.getChild(m(i));
                                                                                                                    }
                                                                                                                }
                                                                                                                try {
                                                                                                                    if (child2.getChild(m(z)).getBoolean(m(l)).booleanValue()) {
                                                                                                                        cVar = c.ULTRA;
                                                                                                                    } else {
                                                                                                                        try {
                                                                                                                            if (child2.getChild(m(A)).getBoolean(m(l)).booleanValue()) {
                                                                                                                                cVar = c.ZOMBIE;
                                                                                                                            } else {
                                                                                                                                try {
                                                                                                                                    if (child2.getChild(m(B)).getBoolean(m(l)).booleanValue()) {
                                                                                                                                        cVar = c.ARMY;
                                                                                                                                    } else {
                                                                                                                                        try {
                                                                                                                                            if (child2.getChild(m(C)).getBoolean(m(l)).booleanValue()) {
                                                                                                                                                cVar = c.HALLOWEEN;
                                                                                                                                            } else {
                                                                                                                                                try {
                                                                                                                                                    if (child2.getChild(m(D)).getBoolean(m(l)).booleanValue()) {
                                                                                                                                                        cVar = c.THANKSGIVING;
                                                                                                                                                    } else {
                                                                                                                                                        try {
                                                                                                                                                            if (child2.getChild(m(E)).getBoolean(m(l)).booleanValue()) {
                                                                                                                                                                cVar = c.SANTA;
                                                                                                                                                            } else {
                                                                                                                                                                try {
                                                                                                                                                                    if (child2.getChild(m(F)).getBoolean(m(l)).booleanValue()) {
                                                                                                                                                                        cVar = c.EASTER;
                                                                                                                                                                    } else {
                                                                                                                                                                        try {
                                                                                                                                                                            if (child2.getChild(m(K)).getBoolean(m(l)).booleanValue()) {
                                                                                                                                                                                cVar = c.WORLDCUP_AUSTRALIA;
                                                                                                                                                                            } else {
                                                                                                                                                                                try {
                                                                                                                                                                                    if (child2.getChild(m(N)).getBoolean(m(l)).booleanValue()) {
                                                                                                                                                                                        cVar = c.WORLDCUP_BRAZIL;
                                                                                                                                                                                    } else {
                                                                                                                                                                                        try {
                                                                                                                                                                                            if (child2.getChild(m(H)).getBoolean(m(l)).booleanValue()) {
                                                                                                                                                                                                cVar = c.WORLDCUP_USA;
                                                                                                                                                                                            } else {
                                                                                                                                                                                                try {
                                                                                                                                                                                                    if (child2.getChild(m(L)).getBoolean(m(l)).booleanValue()) {
                                                                                                                                                                                                        cVar = c.WORLDCUP_FRANCE;
                                                                                                                                                                                                    } else {
                                                                                                                                                                                                        try {
                                                                                                                                                                                                            if (child2.getChild(m(M)).getBoolean(m(l)).booleanValue()) {
                                                                                                                                                                                                                cVar = c.WORLDCUP_GERMANY;
                                                                                                                                                                                                            } else {
                                                                                                                                                                                                                try {
                                                                                                                                                                                                                    if (child2.getChild(m(P)).getBoolean(m(l)).booleanValue()) {
                                                                                                                                                                                                                        cVar = c.WORLDCUP_JAPAN;
                                                                                                                                                                                                                    } else {
                                                                                                                                                                                                                        try {
                                                                                                                                                                                                                            if (child2.getChild(m(J)).getBoolean(m(l)).booleanValue()) {
                                                                                                                                                                                                                                cVar = c.WORLDCUP_NETHERLANDS;
                                                                                                                                                                                                                            } else {
                                                                                                                                                                                                                                try {
                                                                                                                                                                                                                                    if (child2.getChild(m(O)).getBoolean(m(l)).booleanValue()) {
                                                                                                                                                                                                                                        cVar = c.WORLDCUP_SPAIN;
                                                                                                                                                                                                                                    } else {
                                                                                                                                                                                                                                        try {
                                                                                                                                                                                                                                            if (child2.getChild(m(I)).getBoolean(m(l)).booleanValue()) {
                                                                                                                                                                                                                                                cVar = c.WORLDCUP_ENGLAND;
                                                                                                                                                                                                                                            } else {
                                                                                                                                                                                                                                                try {
                                                                                                                                                                                                                                                    if (child2.getChild(m(Q)).getBoolean(m(l)).booleanValue()) {
                                                                                                                                                                                                                                                        cVar = c.WORLDCUP_ARGENTINA;
                                                                                                                                                                                                                                                    } else {
                                                                                                                                                                                                                                                        try {
                                                                                                                                                                                                                                                            if (child2.getChild(m(R)).getBoolean(m(l)).booleanValue()) {
                                                                                                                                                                                                                                                                cVar = c.WORLDCUP_ITALY;
                                                                                                                                                                                                                                                            } else {
                                                                                                                                                                                                                                                                try {
                                                                                                                                                                                                                                                                    if (child2.getChild(m(S)).getBoolean(m(l)).booleanValue()) {
                                                                                                                                                                                                                                                                        cVar = c.WORLDCUP_BELGIUM;
                                                                                                                                                                                                                                                                    } else {
                                                                                                                                                                                                                                                                        try {
                                                                                                                                                                                                                                                                            if (child2.getChild(m(T)).getBoolean(m(l)).booleanValue()) {
                                                                                                                                                                                                                                                                                cVar = c.WORLDCUP_MEXICO;
                                                                                                                                                                                                                                                                            } else {
                                                                                                                                                                                                                                                                                try {
                                                                                                                                                                                                                                                                                    if (child2.getChild(m(G)).getBoolean(m(l)).booleanValue()) {
                                                                                                                                                                                                                                                                                        cVar = c.NOT_USED_IOS_DAILY_WORLD_BIKE_INDEX_HOLDER;
                                                                                                                                                                                                                                                                                    } else {
                                                                                                                                                                                                                                                                                        cVar = c.REGULAR;
                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                } catch (NullPointerException e6) {
                                                                                                                                                                                                                                                                                    child2.addChild(l(G));
                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                        } catch (NullPointerException e7) {
                                                                                                                                                                                                                                                                            child2.addChild(l(T));
                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                } catch (NullPointerException e8) {
                                                                                                                                                                                                                                                                    child2.addChild(l(S));
                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                        } catch (NullPointerException e9) {
                                                                                                                                                                                                                                                            child2.addChild(l(R));
                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                } catch (NullPointerException e10) {
                                                                                                                                                                                                                                                    child2.addChild(l(Q));
                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                        } catch (NullPointerException e11) {
                                                                                                                                                                                                                                            child2.addChild(l(I));
                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                } catch (NullPointerException e12) {
                                                                                                                                                                                                                                    child2.addChild(l(O));
                                                                                                                                                                                                                                }
                                                                                                                                                                                                                            }
                                                                                                                                                                                                                        } catch (NullPointerException e13) {
                                                                                                                                                                                                                            child2.addChild(l(J));
                                                                                                                                                                                                                        }
                                                                                                                                                                                                                    }
                                                                                                                                                                                                                } catch (NullPointerException e14) {
                                                                                                                                                                                                                    child2.addChild(l(P));
                                                                                                                                                                                                                }
                                                                                                                                                                                                            }
                                                                                                                                                                                                        } catch (NullPointerException e15) {
                                                                                                                                                                                                            child2.addChild(l(M));
                                                                                                                                                                                                        }
                                                                                                                                                                                                    }
                                                                                                                                                                                                } catch (NullPointerException e16) {
                                                                                                                                                                                                    child2.addChild(l(L));
                                                                                                                                                                                                }
                                                                                                                                                                                            }
                                                                                                                                                                                        } catch (NullPointerException e17) {
                                                                                                                                                                                            child2.addChild(l(H));
                                                                                                                                                                                        }
                                                                                                                                                                                    }
                                                                                                                                                                                } catch (NullPointerException e18) {
                                                                                                                                                                                    child2.addChild(l(N));
                                                                                                                                                                                }
                                                                                                                                                                            }
                                                                                                                                                                        } catch (NullPointerException e19) {
                                                                                                                                                                            child2.addChild(l(K));
                                                                                                                                                                        }
                                                                                                                                                                    }
                                                                                                                                                                } catch (NullPointerException e20) {
                                                                                                                                                                    child2.addChild(l(F));
                                                                                                                                                                }
                                                                                                                                                            }
                                                                                                                                                        } catch (NullPointerException e21) {
                                                                                                                                                            child2.addChild(l(E));
                                                                                                                                                        }
                                                                                                                                                    }
                                                                                                                                                } catch (NullPointerException e22) {
                                                                                                                                                    child2.addChild(l(D));
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                        } catch (NullPointerException e23) {
                                                                                                                                            child2.addChild(l(C));
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                } catch (NullPointerException e24) {
                                                                                                                                    child2.addChild(l(B));
                                                                                                                                }
                                                                                                                            }
                                                                                                                        } catch (NullPointerException e25) {
                                                                                                                            child2.addChild(l(A));
                                                                                                                        }
                                                                                                                    }
                                                                                                                } catch (NullPointerException e26) {
                                                                                                                    child2.addChild(l(z));
                                                                                                                }
                                                                                                            }
                                                                                                        } catch (NullPointerException e27) {
                                                                                                            child.addChild(l(y));
                                                                                                        }
                                                                                                    }
                                                                                                } catch (NullPointerException e28) {
                                                                                                    child.addChild(l(x));
                                                                                                }
                                                                                            }
                                                                                        } catch (NullPointerException e29) {
                                                                                            child.addChild(l(w));
                                                                                        }
                                                                                    }
                                                                                } catch (NullPointerException e30) {
                                                                                    child.addChild(l(v));
                                                                                }
                                                                            }
                                                                        } catch (NullPointerException e31) {
                                                                            child.addChild(l(u));
                                                                        }
                                                                    }
                                                                } catch (NullPointerException e32) {
                                                                    child.addChild(l(t));
                                                                }
                                                            }
                                                        } catch (NullPointerException e33) {
                                                            child.addChild(l(s));
                                                        }
                                                    }
                                                } catch (NullPointerException e34) {
                                                    child.addChild(l(r));
                                                }
                                            }
                                        } catch (NullPointerException e35) {
                                            child.addChild(l(q));
                                        }
                                    }
                                } catch (NullPointerException e36) {
                                    child.addChild(l(p));
                                }
                            }
                        } catch (NullPointerException e37) {
                            child.addChild(l(n));
                        }
                    }
                } catch (NullPointerException e38) {
                    child.addChild(l(o));
                }
            }
        } catch (NullPointerException e39) {
            child.addChild(l(m));
        }
        return cVar;
    }

    public synchronized boolean f() {
        DataNode child;
        Boolean boolValueOf;
        try {
            child = this.bd.getChild(m(aa));
        } catch (NullPointerException e2) {
            if (ap.d()) {
                e2.printStackTrace();
            }
            A();
            if (this.bd == null) {
                R();
            }
            child = this.bd.getChild(m(aa));
        }
        try {
            boolValueOf = child.getBoolean(m(ae));
        } catch (NullPointerException e3) {
            boolValueOf = null;
        }
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(this.bb);
            e(boolValueOf.booleanValue());
        }
        return boolValueOf.booleanValue();
    }

    public synchronized boolean g() {
        DataNode child;
        Boolean bool;
        try {
            child = this.bd.getChild(m(aa));
        } catch (NullPointerException e2) {
            if (ap.d()) {
                e2.printStackTrace();
            }
            A();
            if (this.bd == null) {
                R();
            }
            child = this.bd.getChild(m(aa));
        }
        Boolean bool2 = null;
        try {
            bool = child.getBoolean(m(ac));
        } catch (NullPointerException e3) {
            f(bool2.booleanValue());
            bool = null;
        }
        if (bool == null) {
            bool = true;
        }
        return bool.booleanValue();
    }

    public synchronized boolean a(int i2) {
        DataNode child;
        DataNode dataNode;
        Boolean bool;
        boolean zBooleanValue = false;
        synchronized (this) {
            try {
                try {
                    if (ap.s()) {
                        for (int i3 = 0; i3 < com.topfreegames.bikerace.h.y.b.length; i3++) {
                            if (com.topfreegames.bikerace.h.y.b[i3] != i2) {
                            }
                        }
                    }
                    child = this.bd.getChild(String.valueOf(m(X)) + i2);
                    dataNode = child;
                    bool = child.getBoolean(m(Y));
                } catch (NullPointerException e2) {
                    if (child == null) {
                        child = b(String.valueOf(m(X)) + i2);
                    }
                    this.bd.addChild(child);
                    dataNode = child;
                    bool = null;
                }
                child = this.bd.getChild(String.valueOf(m(X)) + i2);
            } catch (NullPointerException e3) {
                if (ap.d()) {
                    e3.printStackTrace();
                }
                A();
                if (this.bd == null) {
                    R();
                }
                child = this.bd.getChild(String.valueOf(m(X)) + i2);
                if (child == null) {
                    this.bd.addChild(b(String.valueOf(m(X)) + i2));
                }
            }
            if (bool == null) {
                if (i2 == 1) {
                    bool = false;
                    if (dataNode != null) {
                        dataNode.putBoolean(m(Y), false);
                    }
                } else {
                    bool = true;
                }
            }
            zBooleanValue = bool.booleanValue();
        }
        return zBooleanValue;
    }

    public com.topfreegames.bikerace.i.b h() {
        DataNode child;
        Integer numValueOf;
        DataNode dataNodeI;
        try {
            child = this.bd.getChild(m(aa));
        } catch (NullPointerException e2) {
            if (ap.d()) {
                e2.printStackTrace();
            }
            A();
            if (this.bd == null) {
                R();
            }
            child = this.bd.getChild(m(aa));
        }
        try {
            dataNodeI = child;
            numValueOf = child.getInteger(m(af));
        } catch (NullPointerException e3) {
            numValueOf = 0;
            dataNodeI = i(this.bb);
            this.bd.addChild(dataNodeI);
        }
        if (numValueOf == null) {
            numValueOf = Integer.valueOf(com.topfreegames.bikerace.i.b.ENGLISH.ordinal());
            dataNodeI.putInteger(m(af), numValueOf);
            l(false);
        }
        return com.topfreegames.bikerace.i.b.a(numValueOf.intValue());
    }

    public synchronized ab i() {
        return this.bc;
    }

    public synchronized int b(int i2, int i3) {
        DataNode child;
        Integer integer;
        try {
            child = this.bd.getChild(String.valueOf(m(X)) + i2);
        } catch (NullPointerException e2) {
            if (ap.d()) {
                e2.printStackTrace();
            }
            A();
            if (this.bd == null) {
                R();
            }
            child = this.bd.getChild(String.valueOf(m(X)) + i2);
        }
        try {
            integer = child.getChild(String.valueOf(m(U)) + i3).getInteger(m(W));
        } catch (NullPointerException e3) {
            integer = null;
        }
        if (integer == null) {
            integer = 0;
        }
        return integer.intValue();
    }

    public synchronized int j() {
        DataNode child;
        Integer integer;
        DataNode dataNodeT;
        try {
            child = this.bd.getChild(m(ag));
        } catch (NullPointerException e2) {
            if (ap.d()) {
                e2.printStackTrace();
            }
            A();
            if (this.bd == null) {
                R();
            }
            child = this.bd.getChild(m(ag));
        }
        try {
            dataNodeT = child;
            integer = child.getInteger(m(ai));
        } catch (NullPointerException e3) {
            integer = 0;
            dataNodeT = T();
            this.bd.addChild(dataNodeT);
        }
        if (integer == null) {
            integer = 0;
            dataNodeT.putInteger(m(ai), 0);
        }
        return integer.intValue();
    }

    public synchronized int k() {
        DataNode child;
        Integer integer;
        DataNode dataNodeT;
        try {
            child = this.bd.getChild(m(ag));
        } catch (NullPointerException e2) {
            if (ap.d()) {
                e2.printStackTrace();
            }
            A();
            if (this.bd == null) {
                R();
            }
            child = this.bd.getChild(m(ag));
        }
        try {
            dataNodeT = child;
            integer = child.getInteger(m(aj));
        } catch (NullPointerException e3) {
            integer = 0;
            dataNodeT = T();
            this.bd.addChild(dataNodeT);
        }
        if (integer == null) {
            integer = 0;
            dataNodeT.putInteger(m(aj), 0);
        }
        return integer.intValue();
    }

    public synchronized int l() {
        DataNode child;
        Integer integer;
        DataNode dataNodeT;
        try {
            child = this.bd.getChild(m(ag));
        } catch (NullPointerException e2) {
            if (ap.d()) {
                e2.printStackTrace();
            }
            A();
            if (this.bd == null) {
                R();
            }
            child = this.bd.getChild(m(ag));
        }
        try {
            dataNodeT = child;
            integer = child.getInteger(m(ak));
        } catch (NullPointerException e3) {
            integer = 0;
            dataNodeT = T();
            this.bd.addChild(dataNodeT);
        }
        if (integer == null) {
            integer = 0;
            dataNodeT.putInteger(m(ak), 0);
        }
        return integer.intValue();
    }

    public synchronized int m() {
        DataNode child;
        Integer integer;
        DataNode dataNodeT;
        try {
            child = this.bd.getChild(m(ag));
        } catch (NullPointerException e2) {
            if (ap.d()) {
                e2.printStackTrace();
            }
            A();
            if (this.bd == null) {
                R();
            }
            child = this.bd.getChild(m(ag));
        }
        try {
            dataNodeT = child;
            integer = child.getInteger(m(ah));
        } catch (NullPointerException e3) {
            integer = 0;
            dataNodeT = T();
            this.bd.addChild(dataNodeT);
        }
        if (integer == null) {
            integer = 0;
            dataNodeT.putInteger(m(ah), 0);
        }
        return integer.intValue();
    }

    public synchronized int n() {
        return j(false);
    }

    public synchronized int o() {
        return j(aa());
    }

    public synchronized int p() {
        int iB = 0;
        synchronized (this) {
            if (bg.a(bi.FAKE_NUM_STARS)) {
                iB = bg.b(bi.FAKE_NUM_STARS);
            } else {
                int[] iArr = com.topfreegames.bikerace.h.y.f1259a;
                int length = iArr.length;
                int i2 = 0;
                while (i2 < length) {
                    int iB2 = b(iArr[i2]) + iB;
                    i2++;
                    iB = iB2;
                }
            }
        }
        return iB;
    }

    public synchronized int b(int i2) {
        Integer integer;
        integer = null;
        try {
            integer = this.bd.getChild(String.valueOf(m(X)) + i2).getInteger(m(Z));
        } catch (NullPointerException e2) {
        }
        if (integer == null) {
            integer = 0;
        }
        return integer.intValue();
    }

    public synchronized int q() {
        int i2;
        i2 = 0;
        for (int i3 : com.topfreegames.bikerace.h.y.b) {
            for (int i4 = 0; i4 < 8; i4++) {
                if (b(i3, i4) <= 0) {
                    i2++;
                }
            }
        }
        return i2;
    }

    public synchronized int r() {
        int i2;
        i2 = 0;
        for (int i3 : com.topfreegames.bikerace.h.y.b) {
            for (int i4 = 0; i4 < 8; i4++) {
                if (b(i3, i4) < 3) {
                    i2++;
                }
            }
        }
        return i2;
    }

    public boolean s() {
        DataNode child;
        Boolean bool;
        DataNode dataNodeT;
        try {
            child = this.bd.getChild(m(ag));
        } catch (NullPointerException e2) {
            if (ap.d()) {
                e2.printStackTrace();
            }
            A();
            if (this.bd == null) {
                R();
            }
            child = this.bd.getChild(m(ag));
        }
        try {
            dataNodeT = child;
            bool = child.getBoolean(m(ao));
        } catch (NullPointerException e3) {
            bool = false;
            dataNodeT = T();
            this.bd.addChild(dataNodeT);
        }
        if (bool == null) {
            bool = false;
            dataNodeT.putBoolean(m(ao), bool);
            l(false);
        }
        return bool.booleanValue();
    }

    public boolean t() {
        DataNode child;
        Boolean bool;
        DataNode dataNodeT;
        try {
            child = this.bd.getChild(m(ag));
        } catch (NullPointerException e2) {
            if (ap.d()) {
                e2.printStackTrace();
            }
            A();
            if (this.bd == null) {
                R();
            }
            child = this.bd.getChild(m(ag));
        }
        try {
            dataNodeT = child;
            bool = child.getBoolean(m(ap));
        } catch (NullPointerException e3) {
            bool = false;
            dataNodeT = T();
            this.bd.addChild(dataNodeT);
        }
        if (bool == null) {
            bool = false;
            dataNodeT.putBoolean(m(ap), bool);
            l(false);
        }
        return bool.booleanValue();
    }

    public boolean u() {
        DataNode child;
        Boolean bool;
        DataNode dataNodeT;
        try {
            child = this.bd.getChild(m(ag));
        } catch (NullPointerException e2) {
            if (ap.d()) {
                e2.printStackTrace();
            }
            A();
            if (this.bd == null) {
                R();
            }
            child = this.bd.getChild(m(ag));
        }
        try {
            dataNodeT = child;
            bool = child.getBoolean(m(aq));
        } catch (NullPointerException e3) {
            bool = false;
            dataNodeT = T();
            this.bd.addChild(dataNodeT);
        }
        if (bool == null) {
            bool = false;
            dataNodeT.putBoolean(m(aq), bool);
            l(false);
        }
        return bool.booleanValue();
    }

    public int v() {
        DataNode child;
        Integer integer;
        DataNode dataNodeT;
        try {
            child = this.bd.getChild(m(ag));
        } catch (NullPointerException e2) {
            if (ap.d()) {
                e2.printStackTrace();
            }
            A();
            if (this.bd == null) {
                R();
            }
            child = this.bd.getChild(m(ag));
        }
        try {
            dataNodeT = child;
            integer = child.getInteger(m(ar));
        } catch (NullPointerException e3) {
            integer = -1;
            dataNodeT = T();
            this.bd.addChild(dataNodeT);
        }
        if (integer == null) {
            integer = -1;
            dataNodeT.putInteger(m(ar), integer);
            l(false);
        }
        return integer.intValue();
    }

    public void c(int i2) {
        DataNode child;
        try {
            child = this.bd.getChild(m(ag));
        } catch (NullPointerException e2) {
            if (ap.d()) {
                e2.printStackTrace();
            }
            A();
            if (this.bd == null) {
                R();
            }
            child = this.bd.getChild(m(ag));
        }
        try {
            child.putInteger(m(ar), Integer.valueOf(i2));
        } catch (NullPointerException e3) {
            this.bd.addChild(T());
        }
        l(false);
    }

    public void a(boolean z2) {
        DataNode child;
        try {
            child = this.bd.getChild(m(ag));
        } catch (NullPointerException e2) {
            if (ap.d()) {
                e2.printStackTrace();
            }
            A();
            if (this.bd == null) {
                R();
            }
            child = this.bd.getChild(m(ag));
        }
        try {
            child.putBoolean(m(aq), Boolean.valueOf(z2));
        } catch (NullPointerException e3) {
            this.bd.addChild(T());
        }
        l(false);
    }

    public synchronized void w() {
        DataNode child;
        V();
        int iJ = j() + 1;
        try {
            child = this.bd.getChild(m(ag));
        } catch (NullPointerException e2) {
            if (ap.d()) {
                e2.printStackTrace();
            }
            A();
            if (this.bd == null) {
                R();
            }
            child = this.bd.getChild(m(ag));
        }
        try {
            child.putInteger(m(ai), Integer.valueOf(iJ));
            if (iJ > k()) {
                child.putInteger(m(aj), Integer.valueOf(iJ));
            }
        } catch (NullPointerException e3) {
            DataNode dataNodeT = T();
            this.bd.addChild(dataNodeT);
            dataNodeT.putInteger(m(ai), Integer.valueOf(iJ));
        }
        l(false);
    }

    public synchronized void x() {
        DataNode child;
        int iJ = j() + 1;
        try {
            child = this.bd.getChild(m(ag));
        } catch (NullPointerException e2) {
            if (ap.d()) {
                e2.printStackTrace();
            }
            A();
            if (this.bd == null) {
                R();
            }
            child = this.bd.getChild(m(ag));
        }
        try {
            child.putInteger(m(ak), Integer.valueOf(iJ));
        } catch (NullPointerException e3) {
            DataNode dataNodeT = T();
            this.bd.addChild(dataNodeT);
            dataNodeT.putInteger(m(ak), Integer.valueOf(iJ));
        }
        l(false);
    }

    public synchronized void y() {
        k(false);
    }

    public synchronized void z() {
        k(aa());
    }

    public synchronized void A() {
        b(true);
        b(false);
        Y();
    }

    /* JADX WARN: Code duplicated, block: B:158:0x00d5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:160:0x0076 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:162:0x009a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:164:0x0095 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:167:0x0071 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:169:0x006c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:181:0x010a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:183:0x0105 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:185:0x00da A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:187:0x010f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:191:0x009f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:193:0x00df A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:28:0x0058 A[Catch: all -> 0x015e, TryCatch #39 {all -> 0x015e, blocks: (B:26:0x0052, B:28:0x0058, B:29:0x005b, B:75:0x00eb, B:77:0x00f1, B:78:0x00f4, B:60:0x00bb, B:62:0x00c1, B:63:0x00c4), top: B:166:0x0002 }] */
    /* JADX WARN: Code duplicated, block: B:62:0x00c1 A[Catch: all -> 0x015e, TryCatch #39 {all -> 0x015e, blocks: (B:26:0x0052, B:28:0x0058, B:29:0x005b, B:75:0x00eb, B:77:0x00f1, B:78:0x00f4, B:60:0x00bb, B:62:0x00c1, B:63:0x00c4), top: B:166:0x0002 }] */
    /* JADX WARN: Code duplicated, block: B:77:0x00f1 A[Catch: all -> 0x015e, TryCatch #39 {all -> 0x015e, blocks: (B:26:0x0052, B:28:0x0058, B:29:0x005b, B:75:0x00eb, B:77:0x00f1, B:78:0x00f4, B:60:0x00bb, B:62:0x00c1, B:63:0x00c4), top: B:166:0x0002 }] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 1, insn: 0x015c: MOVE (r2 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:133:0x015a */
    /* JADX WARN: Not initialized variable reg: 3, insn: 0x015a: MOVE (r4 I:??[OBJECT, ARRAY]) = (r3 I:??[OBJECT, ARRAY]), block:B:133:0x015a */
    /* JADX WARN: Type inference failed for: r1v19, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r1v23, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r1v29, types: [java.io.FileInputStream, java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v33 */
    /* JADX WARN: Type inference failed for: r1v34 */
    /* JADX WARN: Type inference failed for: r1v35 */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v16, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r2v17, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r2v18, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r2v19, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v20, types: [javax.crypto.CipherInputStream] */
    /* JADX WARN: Type inference failed for: r2v21, types: [javax.crypto.CipherInputStream] */
    /* JADX WARN: Type inference failed for: r2v22 */
    /* JADX WARN: Type inference failed for: r2v23 */
    /* JADX WARN: Type inference failed for: r2v24 */
    /* JADX WARN: Type inference failed for: r2v25 */
    /* JADX WARN: Type inference failed for: r2v26 */
    /* JADX WARN: Type inference failed for: r2v27 */
    /* JADX WARN: Type inference failed for: r2v28 */
    /* JADX WARN: Type inference failed for: r2v29 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v30 */
    /* JADX WARN: Type inference failed for: r2v31 */
    /* JADX WARN: Type inference failed for: r2v32 */
    /* JADX WARN: Type inference failed for: r2v33 */
    /* JADX WARN: Type inference failed for: r2v34 */
    /* JADX WARN: Type inference failed for: r2v35 */
    /* JADX WARN: Type inference failed for: r2v36 */
    /* JADX WARN: Type inference failed for: r2v37 */
    /* JADX WARN: Type inference failed for: r2v38 */
    /* JADX WARN: Type inference failed for: r2v39 */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v40 */
    /* JADX WARN: Type inference failed for: r2v41 */
    /* JADX WARN: Type inference failed for: r2v42 */
    /* JADX WARN: Type inference failed for: r2v43 */
    /* JADX WARN: Type inference failed for: r2v44 */
    /* JADX WARN: Type inference failed for: r2v45 */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v12, types: [javax.crypto.CipherInputStream] */
    /* JADX WARN: Type inference failed for: r3v13, types: [javax.crypto.CipherInputStream] */
    /* JADX WARN: Type inference failed for: r3v14, types: [javax.crypto.CipherInputStream] */
    /* JADX WARN: Type inference failed for: r3v15, types: [javax.crypto.CipherInputStream] */
    /* JADX WARN: Type inference failed for: r3v16, types: [java.io.ObjectInputStream] */
    /* JADX WARN: Type inference failed for: r3v18, types: [java.io.ObjectInputStream] */
    /* JADX WARN: Type inference failed for: r3v19 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v20 */
    /* JADX WARN: Type inference failed for: r3v21 */
    /* JADX WARN: Type inference failed for: r3v26 */
    /* JADX WARN: Type inference failed for: r3v27 */
    /* JADX WARN: Type inference failed for: r3v28 */
    /* JADX WARN: Type inference failed for: r3v29 */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v30 */
    /* JADX WARN: Type inference failed for: r3v31 */
    /* JADX WARN: Type inference failed for: r3v32 */
    /* JADX WARN: Type inference failed for: r3v33 */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r3v9 */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v10, types: [java.io.ObjectInputStream] */
    /* JADX WARN: Type inference failed for: r4v11, types: [java.io.ObjectInputStream] */
    /* JADX WARN: Type inference failed for: r4v12, types: [java.io.ObjectInputStream] */
    /* JADX WARN: Type inference failed for: r4v13 */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v15 */
    /* JADX WARN: Type inference failed for: r4v16 */
    /* JADX WARN: Type inference failed for: r4v17 */
    /* JADX WARN: Type inference failed for: r4v18 */
    /* JADX WARN: Type inference failed for: r4v19 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9, types: [java.io.ObjectInputStream] */
    public synchronized void b(boolean z2) {
        ?? r3;
        ?? r4;
        ClassNotFoundException classNotFoundException;
        ?? r5;
        ?? r6;
        IOException iOException;
        ?? r7;
        ?? r8;
        StreamCorruptedException streamCorruptedException;
        ?? r9;
        ?? r10;
        ?? OpenFileInput;
        ?? objectInputStream;
        ?? r2;
        ?? r11;
        ?? r12;
        Object obj;
        Object obj2;
        ?? r13 = 0;
        r13 = 0;
        r13 = 0;
        r13 = 0;
        r13 = 0;
        r13 = 0;
        r13 = 0;
        r13 = 0;
        r13 = 0;
        r13 = 0;
        r13 = 0;
        r13 = 0;
        r13 = 0;
        synchronized (this) {
            try {
                try {
                    try {
                        try {
                            try {
                                if (z2) {
                                    OpenFileInput = this.ba.openFileInput(m(e));
                                    Cipher cipherB = com.topfreegames.bikerace.d.a.b(this.ba);
                                    if (com.topfreegames.bikerace.d.a.a(this.ba) == null || cipherB == null) {
                                        ObjectInputStream objectInputStream2 = new ObjectInputStream(OpenFileInput);
                                        OpenFileInput = OpenFileInput;
                                        objectInputStream = objectInputStream2;
                                    } else {
                                        CipherInputStream cipherInputStream = new CipherInputStream(OpenFileInput, cipherB);
                                        try {
                                            r13 = cipherInputStream;
                                            objectInputStream = new ObjectInputStream(cipherInputStream);
                                            OpenFileInput = OpenFileInput;
                                        } catch (FileNotFoundException e2) {
                                            objectInputStream = 0;
                                            r13 = cipherInputStream;
                                            h(z2);
                                            if (objectInputStream != 0) {
                                                try {
                                                    objectInputStream.close();
                                                } catch (IOException e3) {
                                                }
                                            }
                                            if (r13 != 0) {
                                                try {
                                                    r13.close();
                                                } catch (IOException e4) {
                                                }
                                            }
                                            if (OpenFileInput != 0) {
                                                try {
                                                    OpenFileInput.close();
                                                } catch (IOException e5) {
                                                }
                                            }
                                        } catch (StreamCorruptedException e6) {
                                            r10 = 0;
                                            r12 = OpenFileInput;
                                            streamCorruptedException = e6;
                                            r9 = cipherInputStream;
                                            if (ap.d()) {
                                                streamCorruptedException.printStackTrace();
                                            }
                                            ((BikeRaceApplication) this.ba.getApplicationContext()).d().a(streamCorruptedException);
                                            if (r10 != 0) {
                                                try {
                                                    r10.close();
                                                } catch (IOException e7) {
                                                }
                                            }
                                            if (r9 != 0) {
                                                try {
                                                    r9.close();
                                                } catch (IOException e8) {
                                                }
                                            }
                                            if (r12 != 0) {
                                                try {
                                                    r12.close();
                                                } catch (IOException e9) {
                                                }
                                            }
                                        } catch (IOException e10) {
                                            r8 = 0;
                                            r11 = OpenFileInput;
                                            iOException = e10;
                                            r7 = cipherInputStream;
                                            if (ap.d()) {
                                                iOException.printStackTrace();
                                            }
                                            ((BikeRaceApplication) this.ba.getApplicationContext()).d().a(iOException);
                                            if (r8 != 0) {
                                                try {
                                                    r8.close();
                                                } catch (IOException e11) {
                                                }
                                            }
                                            if (r7 != 0) {
                                                try {
                                                    r7.close();
                                                } catch (IOException e12) {
                                                }
                                            }
                                            if (r11 != 0) {
                                                try {
                                                    r11.close();
                                                } catch (IOException e13) {
                                                }
                                            }
                                        } catch (ClassNotFoundException e14) {
                                            r6 = 0;
                                            r2 = OpenFileInput;
                                            classNotFoundException = e14;
                                            r5 = cipherInputStream;
                                            if (ap.d()) {
                                                classNotFoundException.printStackTrace();
                                            }
                                            ((BikeRaceApplication) this.ba.getApplicationContext()).d().a(classNotFoundException);
                                            if (r6 != 0) {
                                                try {
                                                    r6.close();
                                                } catch (IOException e15) {
                                                }
                                            }
                                            if (r5 != 0) {
                                                try {
                                                    r5.close();
                                                } catch (IOException e16) {
                                                }
                                            }
                                            if (r2 != 0) {
                                                try {
                                                    r2.close();
                                                } catch (IOException e17) {
                                                }
                                            }
                                        } catch (Throwable th) {
                                            th = th;
                                            r4 = 0;
                                            r13 = OpenFileInput;
                                            r3 = cipherInputStream;
                                            if (r4 != 0) {
                                                try {
                                                    r4.close();
                                                } catch (IOException e18) {
                                                }
                                            }
                                            if (r3 != 0) {
                                                try {
                                                    r3.close();
                                                } catch (IOException e19) {
                                                }
                                            }
                                            if (r13 == 0) {
                                                throw th;
                                            }
                                            try {
                                                r13.close();
                                                throw th;
                                            } catch (IOException e20) {
                                                throw th;
                                            }
                                        }
                                    }
                                } else {
                                    FileInputStream fileInputStreamOpenFileInput = this.ba.openFileInput(m(d));
                                    ObjectInputStream objectInputStream3 = new ObjectInputStream(fileInputStreamOpenFileInput);
                                    OpenFileInput = fileInputStreamOpenFileInput;
                                    objectInputStream = objectInputStream3;
                                }
                                try {
                                    if (z2) {
                                        this.be = (DataNode) objectInputStream.readObject();
                                    } else {
                                        this.bd = (DataNode) objectInputStream.readObject();
                                        U();
                                    }
                                    if (objectInputStream != 0) {
                                        try {
                                            objectInputStream.close();
                                        } catch (IOException e21) {
                                        }
                                    }
                                    if (r13 != 0) {
                                        try {
                                            r13.close();
                                        } catch (IOException e22) {
                                        }
                                    }
                                    if (OpenFileInput != 0) {
                                        try {
                                            OpenFileInput.close();
                                        } catch (IOException e23) {
                                        }
                                    }
                                } catch (FileNotFoundException e24) {
                                    h(z2);
                                    if (objectInputStream != 0) {
                                        objectInputStream.close();
                                    }
                                    if (r13 != 0) {
                                        r13.close();
                                    }
                                    if (OpenFileInput != 0) {
                                        OpenFileInput.close();
                                    }
                                } catch (StreamCorruptedException e25) {
                                    r10 = objectInputStream;
                                    r9 = r13;
                                    r12 = OpenFileInput;
                                    streamCorruptedException = e25;
                                    if (ap.d()) {
                                        streamCorruptedException.printStackTrace();
                                    }
                                    ((BikeRaceApplication) this.ba.getApplicationContext()).d().a(streamCorruptedException);
                                    if (r10 != 0) {
                                        r10.close();
                                    }
                                    if (r9 != 0) {
                                        r9.close();
                                    }
                                    if (r12 != 0) {
                                        r12.close();
                                    }
                                } catch (IOException e26) {
                                    r8 = objectInputStream;
                                    r7 = r13;
                                    r11 = OpenFileInput;
                                    iOException = e26;
                                    if (ap.d()) {
                                        iOException.printStackTrace();
                                    }
                                    ((BikeRaceApplication) this.ba.getApplicationContext()).d().a(iOException);
                                    if (r8 != 0) {
                                        r8.close();
                                    }
                                    if (r7 != 0) {
                                        r7.close();
                                    }
                                    if (r11 != 0) {
                                        r11.close();
                                    }
                                } catch (ClassNotFoundException e27) {
                                    r6 = objectInputStream;
                                    r5 = r13;
                                    r2 = OpenFileInput;
                                    classNotFoundException = e27;
                                    if (ap.d()) {
                                        classNotFoundException.printStackTrace();
                                    }
                                    ((BikeRaceApplication) this.ba.getApplicationContext()).d().a(classNotFoundException);
                                    if (r6 != 0) {
                                        r6.close();
                                    }
                                    if (r5 != 0) {
                                        r5.close();
                                    }
                                    if (r2 != 0) {
                                        r2.close();
                                    }
                                }
                            } catch (Throwable th2) {
                                throw th2;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            r4 = obj;
                            r3 = 0;
                            r13 = obj2;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                    }
                } catch (FileNotFoundException e28) {
                    objectInputStream = r13;
                } catch (StreamCorruptedException e29) {
                    r9 = r13;
                    r10 = r13;
                    r12 = OpenFileInput;
                    streamCorruptedException = e29;
                } catch (IOException e30) {
                    r7 = r13;
                    r8 = r13;
                    r11 = OpenFileInput;
                    iOException = e30;
                } catch (ClassNotFoundException e31) {
                    r5 = r13;
                    r6 = r13;
                    r2 = OpenFileInput;
                    classNotFoundException = e31;
                } catch (Throwable th5) {
                    th = th5;
                    r3 = r13;
                    r4 = r13;
                    r13 = OpenFileInput;
                }
            } catch (FileNotFoundException e32) {
                OpenFileInput = r13;
                objectInputStream = r13;
            } catch (StreamCorruptedException e33) {
                streamCorruptedException = e33;
                r9 = r13;
                r10 = r13;
                r12 = r13;
            } catch (IOException e34) {
                iOException = e34;
                r7 = r13;
                r8 = r13;
                r11 = r13;
            } catch (ClassNotFoundException e35) {
                classNotFoundException = e35;
                r5 = r13;
                r6 = r13;
                r2 = r13;
            } catch (Throwable th6) {
                th = th6;
                r3 = r13;
                r4 = r13;
            }
        }
    }

    public synchronized void b(c cVar) {
        a(cVar, aa());
    }

    public synchronized void d(int i2) {
        a(i2, ab());
    }

    public void B() {
        DataNode child;
        try {
            child = this.bd.getChild(m(ag));
        } catch (NullPointerException e2) {
            if (ap.d()) {
                e2.printStackTrace();
            }
            A();
            if (this.bd == null) {
                R();
            }
            child = this.bd.getChild(m(ag));
        }
        try {
            child.putBoolean(m(ao), Boolean.valueOf(aa()));
        } catch (NullPointerException e3) {
            this.bd.addChild(T());
        }
        l(false);
    }

    public void C() {
        DataNode child;
        try {
            child = this.bd.getChild(m(ag));
        } catch (NullPointerException e2) {
            if (ap.d()) {
                e2.printStackTrace();
            }
            A();
            if (this.bd == null) {
                R();
            }
            child = this.bd.getChild(m(ag));
        }
        try {
            child.putBoolean(m(ap), Boolean.valueOf(aa()));
        } catch (NullPointerException e3) {
            this.bd.addChild(T());
        }
        l(false);
    }

    public synchronized void D() {
        DataNode child;
        try {
            child = this.bd.getChild(m(ag));
        } catch (NullPointerException e2) {
            if (ap.d()) {
                e2.printStackTrace();
            }
            A();
            if (this.bd == null) {
                R();
            }
            child = this.bd.getChild(m(ag));
        }
        try {
            child.putInteger(m(ai), 0);
        } catch (NullPointerException e3) {
            DataNode dataNodeT = T();
            this.bd.addChild(dataNodeT);
            dataNodeT.putInteger(m(ai), 0);
        }
        l(false);
    }

    public synchronized void E() {
        DataNode child;
        try {
            child = this.bd.getChild(m(ag));
        } catch (NullPointerException e2) {
            if (ap.d()) {
                e2.printStackTrace();
            }
            A();
            if (this.bd == null) {
                R();
            }
            child = this.bd.getChild(m(ag));
        }
        try {
            child.putInteger(m(ak), 0);
        } catch (NullPointerException e3) {
            DataNode dataNodeT = T();
            this.bd.addChild(dataNodeT);
            dataNodeT.putInteger(m(ak), 0);
        }
        l(false);
    }

    public void a(com.topfreegames.bikerace.i.b bVar) {
        DataNode child;
        if (bVar != h()) {
            try {
                child = this.bd.getChild(m(aa));
            } catch (NullPointerException e2) {
                if (ap.d()) {
                    e2.printStackTrace();
                }
                A();
                if (this.bd == null) {
                    R();
                }
                child = this.bd.getChild(m(aa));
            }
            try {
                child.putInteger(m(af), Integer.valueOf(bVar.ordinal()));
            } catch (NullPointerException e3) {
                this.bd.addChild(i(this.bb));
            }
            l(false);
        }
    }

    public synchronized void a(int i2, int i3, int i4) {
        DataNode dataNode;
        int iIntValue;
        DataNode child = this.bd.getChild(String.valueOf(m(X)) + i2);
        DataNode child2 = null;
        try {
            child2 = child.getChild(String.valueOf(m(U)) + i3);
        } catch (NullPointerException e2) {
            child = b(String.valueOf(m(X)) + i2);
            this.bd.addChild(child);
        }
        try {
            dataNode = child2;
            iIntValue = child2.getInteger(m(W)).intValue();
        } catch (NullPointerException e3) {
            DataNode dataNodeA = a(String.valueOf(m(U)) + i3, aa());
            child.addChild(dataNodeA);
            dataNode = dataNodeA;
            iIntValue = 0;
        }
        if (i4 > iIntValue) {
            int iIntValue2 = (i4 - iIntValue) + child.getInteger(m(Z)).intValue();
            dataNode.putInteger(m(W), Integer.valueOf(i4));
            child.putInteger(m(Z), Integer.valueOf(iIntValue2));
        }
        l(false);
    }

    public synchronized void e(int i2) {
        DataNode child;
        try {
            child = this.bd.getChild(m(ag));
        } catch (NullPointerException e2) {
            if (ap.d()) {
                e2.printStackTrace();
            }
            A();
            if (this.bd == null) {
                R();
            }
            child = this.bd.getChild(m(ag));
        }
        try {
            child.putInteger(m(ah), Integer.valueOf(i2));
        } catch (NullPointerException e3) {
            DataNode dataNodeT = T();
            this.bd.addChild(dataNodeT);
            dataNodeT.putInteger(m(ah), Integer.valueOf(i2));
        }
        l(false);
    }

    public synchronized void c(boolean z2) {
        DataNode child;
        try {
            child = this.bd.getChild(m(aa));
        } catch (NullPointerException e2) {
            if (ap.d()) {
                e2.printStackTrace();
            }
            A();
            try {
                child = this.bd.getChild(m(aa));
            } catch (NullPointerException e3) {
                R();
                child = this.bd.getChild(m(aa));
            }
        }
        if (child == null) {
            child = i(this.bb);
            this.bd.addChild(child);
        }
        child.putBoolean(m(ab), Boolean.valueOf(z2));
        l(false);
    }

    public synchronized void d(boolean z2) {
        DataNode child;
        try {
            child = this.bd.getChild(m(aa));
        } catch (NullPointerException e2) {
            if (ap.d()) {
                e2.printStackTrace();
            }
            A();
            if (this.bd == null) {
                R();
            }
            child = this.bd.getChild(m(aa));
        }
        child.putBoolean(m(ad), Boolean.valueOf(z2));
        l(false);
    }

    public synchronized void c(c cVar) {
        DataNode child;
        DataNode child2;
        try {
            child = this.bd.getChild(m(i));
        } catch (NullPointerException e2) {
            A();
            if (this.bd == null) {
                R();
            }
            child = this.bd.getChild(m(i));
        }
        try {
            child2 = this.be.getChild(m(i));
        } catch (NullPointerException e3) {
            A();
            if (this.be == null) {
                O();
            }
            child2 = this.be.getChild(m(i));
        }
        try {
            child.getChild(m(m)).putBoolean(m(l), false);
        } catch (NullPointerException e4) {
            child.addChild(l(m));
        }
        try {
            child.getChild(m(o)).putBoolean(m(l), false);
        } catch (NullPointerException e5) {
            child.addChild(l(o));
        }
        try {
            child.getChild(m(n)).putBoolean(m(l), false);
        } catch (NullPointerException e6) {
            child.addChild(l(n));
        }
        try {
            child.getChild(m(p)).putBoolean(m(l), false);
        } catch (NullPointerException e7) {
            child.addChild(l(p));
        }
        try {
            child.getChild(m(q)).putBoolean(m(l), false);
        } catch (NullPointerException e8) {
            child.addChild(l(q));
        }
        try {
            child.getChild(m(r)).putBoolean(m(l), false);
        } catch (NullPointerException e9) {
            child.addChild(l(r));
        }
        try {
            child.getChild(m(s)).putBoolean(m(l), false);
        } catch (NullPointerException e10) {
            child.addChild(l(s));
        }
        try {
            child.getChild(m(t)).putBoolean(m(l), false);
        } catch (NullPointerException e11) {
            child.addChild(l(t));
        }
        try {
            child.getChild(m(u)).putBoolean(m(l), false);
        } catch (NullPointerException e12) {
            child.addChild(l(u));
        }
        try {
            child.getChild(m(v)).putBoolean(m(l), false);
        } catch (NullPointerException e13) {
            child.addChild(l(v));
        }
        try {
            child.getChild(m(w)).putBoolean(m(l), false);
        } catch (NullPointerException e14) {
            child.addChild(l(w));
        }
        try {
            child.getChild(m(x)).putBoolean(m(l), false);
        } catch (NullPointerException e15) {
            child.addChild(l(x));
        }
        try {
            child.getChild(m(y)).putBoolean(m(l), false);
        } catch (NullPointerException e16) {
            child.addChild(l(y));
        }
        try {
            child2.getChild(m(z)).putBoolean(m(l), false);
        } catch (NullPointerException e17) {
            child2.addChild(l(z));
        }
        try {
            child2.getChild(m(A)).putBoolean(m(l), false);
        } catch (NullPointerException e18) {
            child2.addChild(l(A));
        }
        try {
            child2.getChild(m(B)).putBoolean(m(l), false);
        } catch (NullPointerException e19) {
            child2.addChild(l(B));
        }
        try {
            child2.getChild(m(C)).putBoolean(m(l), false);
        } catch (NullPointerException e20) {
            child2.addChild(l(C));
        }
        try {
            child2.getChild(m(D)).putBoolean(m(l), false);
        } catch (NullPointerException e21) {
            child2.addChild(l(D));
        }
        try {
            child2.getChild(m(E)).putBoolean(m(l), false);
        } catch (NullPointerException e22) {
            child2.addChild(l(E));
        }
        try {
            child2.getChild(m(F)).putBoolean(m(l), false);
        } catch (NullPointerException e23) {
            child2.addChild(l(F));
        }
        try {
            child2.getChild(m(K)).putBoolean(m(l), false);
        } catch (NullPointerException e24) {
            child2.addChild(l(K));
        }
        try {
            child2.getChild(m(N)).putBoolean(m(l), false);
        } catch (NullPointerException e25) {
            child2.addChild(l(N));
        }
        try {
            child2.getChild(m(H)).putBoolean(m(l), false);
        } catch (NullPointerException e26) {
            child2.addChild(l(H));
        }
        try {
            child2.getChild(m(L)).putBoolean(m(l), false);
        } catch (NullPointerException e27) {
            child2.addChild(l(L));
        }
        try {
            child2.getChild(m(M)).putBoolean(m(l), false);
        } catch (NullPointerException e28) {
            child2.addChild(l(M));
        }
        try {
            child2.getChild(m(P)).putBoolean(m(l), false);
        } catch (NullPointerException e29) {
            child2.addChild(l(P));
        }
        try {
            child2.getChild(m(J)).putBoolean(m(l), false);
        } catch (NullPointerException e30) {
            child2.addChild(l(J));
        }
        try {
            child2.getChild(m(O)).putBoolean(m(l), false);
        } catch (NullPointerException e31) {
            child2.addChild(l(O));
        }
        try {
            child2.getChild(m(I)).putBoolean(m(l), false);
        } catch (NullPointerException e32) {
            child2.addChild(l(I));
        }
        try {
            child2.getChild(m(Q)).putBoolean(m(l), false);
        } catch (NullPointerException e33) {
            child2.addChild(l(Q));
        }
        try {
            child2.getChild(m(R)).putBoolean(m(l), false);
        } catch (NullPointerException e34) {
            child2.addChild(l(R));
        }
        try {
            child2.getChild(m(S)).putBoolean(m(l), false);
        } catch (NullPointerException e35) {
            child2.addChild(l(S));
        }
        try {
            child2.getChild(m(T)).putBoolean(m(l), false);
        } catch (NullPointerException e36) {
            child2.addChild(l(T));
        }
        try {
            child2.getChild(m(G)).putBoolean(m(l), false);
        } catch (NullPointerException e37) {
            child2.addChild(l(G));
        }
        switch (M()[cVar.ordinal()]) {
            case 2:
                child.getChild(m(n)).putBoolean(m(l), Boolean.valueOf(aa()));
                break;
            case 3:
                child.getChild(m(m)).putBoolean(m(l), Boolean.valueOf(aa()));
                break;
            case 4:
                child.getChild(m(o)).putBoolean(m(l), Boolean.valueOf(aa()));
                break;
            case 5:
                child.getChild(m(t)).putBoolean(m(l), Boolean.valueOf(aa()));
                break;
            case 6:
                child.getChild(m(v)).putBoolean(m(l), Boolean.valueOf(aa()));
                break;
            case 7:
                child.getChild(m(q)).putBoolean(m(l), Boolean.valueOf(aa()));
                break;
            case 8:
                child.getChild(m(s)).putBoolean(m(l), Boolean.valueOf(aa()));
                break;
            case 9:
                child.getChild(m(w)).putBoolean(m(l), Boolean.valueOf(aa()));
                break;
            case XMLStreamConstants.ATTRIBUTE /* 10 */:
                child.getChild(m(y)).putBoolean(m(l), Boolean.valueOf(aa()));
                break;
            case XMLStreamConstants.DTD /* 11 */:
                child.getChild(m(p)).putBoolean(m(l), Boolean.valueOf(aa()));
                break;
            case XMLStreamConstants.CDATA /* 12 */:
                child.getChild(m(r)).putBoolean(m(l), Boolean.valueOf(aa()));
                break;
            case XMLStreamConstants.NAMESPACE /* 13 */:
                child.getChild(m(x)).putBoolean(m(l), Boolean.valueOf(aa()));
                break;
            case XMLStreamConstants.NOTATION_DECLARATION /* 14 */:
                child.getChild(m(u)).putBoolean(m(l), Boolean.valueOf(aa()));
                break;
            case XMLStreamConstants.ENTITY_DECLARATION /* 15 */:
                child2.getChild(m(z)).putBoolean(m(l), Boolean.valueOf(aa()));
                break;
            case 16:
                child2.getChild(m(A)).putBoolean(m(l), Boolean.valueOf(aa()));
                break;
            case 17:
                child2.getChild(m(B)).putBoolean(m(l), Boolean.valueOf(aa()));
                break;
            case 18:
                child2.getChild(m(C)).putBoolean(m(l), Boolean.valueOf(aa()));
                break;
            case 19:
                child2.getChild(m(D)).putBoolean(m(l), Boolean.valueOf(aa()));
                break;
            case 20:
                child2.getChild(m(E)).putBoolean(m(l), Boolean.valueOf(aa()));
                break;
            case 21:
                child2.getChild(m(F)).putBoolean(m(l), Boolean.valueOf(aa()));
                break;
            case 22:
                child2.getChild(m(G)).putBoolean(m(l), Boolean.valueOf(aa()));
                break;
            case 23:
                child2.getChild(m(H)).putBoolean(m(l), Boolean.valueOf(aa()));
                break;
            case 24:
                child2.getChild(m(I)).putBoolean(m(l), Boolean.valueOf(aa()));
                break;
            case 25:
                child2.getChild(m(K)).putBoolean(m(l), Boolean.valueOf(aa()));
                break;
            case 26:
                child2.getChild(m(J)).putBoolean(m(l), Boolean.valueOf(aa()));
                break;
            case 27:
                child2.getChild(m(L)).putBoolean(m(l), Boolean.valueOf(aa()));
                break;
            case 28:
                child2.getChild(m(M)).putBoolean(m(l), Boolean.valueOf(aa()));
                break;
            case 29:
                child2.getChild(m(N)).putBoolean(m(l), Boolean.valueOf(aa()));
                break;
            case 30:
                child2.getChild(m(O)).putBoolean(m(l), Boolean.valueOf(aa()));
                break;
            case 31:
                child2.getChild(m(P)).putBoolean(m(l), Boolean.valueOf(aa()));
                break;
            case 32:
                child2.getChild(m(S)).putBoolean(m(l), Boolean.valueOf(aa()));
                break;
            case 33:
                child2.getChild(m(T)).putBoolean(m(l), Boolean.valueOf(aa()));
                break;
            case 34:
                child2.getChild(m(R)).putBoolean(m(l), Boolean.valueOf(aa()));
                break;
            case 35:
                child2.getChild(m(Q)).putBoolean(m(l), Boolean.valueOf(aa()));
                break;
        }
        W();
    }

    public synchronized void e(boolean z2) {
        DataNode child;
        try {
            child = this.bd.getChild(m(aa));
        } catch (NullPointerException e2) {
            if (ap.d()) {
                e2.printStackTrace();
            }
            A();
            if (this.bd == null) {
                R();
            }
            child = this.bd.getChild(m(aa));
        }
        child.putBoolean(m(ae), Boolean.valueOf(z2));
        l(false);
    }

    public synchronized void f(boolean z2) {
        DataNode child;
        try {
            child = this.bd.getChild(m(aa));
        } catch (NullPointerException e2) {
            if (ap.d()) {
                e2.printStackTrace();
            }
            A();
            if (this.bd == null) {
                R();
            }
            child = this.bd.getChild(m(aa));
        }
        if (child == null) {
            child = i(this.bb);
            this.bd.addChild(child);
        }
        child.putBoolean(m(ac), Boolean.valueOf(z2));
        l(false);
    }

    public synchronized void d(c cVar) {
        a(cVar, false);
        try {
            com.topfreegames.bikerace.k.a.a().a(cVar);
        } catch (Exception e2) {
            if (ap.d()) {
                e2.printStackTrace();
            }
        }
    }

    public synchronized void c(int i2, int i3) {
        DataNode child;
        try {
            child = this.bd.getChild(String.valueOf(m(X)) + i2);
        } catch (NullPointerException e2) {
            if (ap.d()) {
                e2.printStackTrace();
            }
            A();
            if (this.bd == null) {
                R();
            }
            child = this.bd.getChild(String.valueOf(m(X)) + i2);
        }
        try {
            child.getChild(String.valueOf(m(U)) + i3).putBoolean(m(V), false);
        } catch (NullPointerException e3) {
            if (ap.d()) {
                e3.printStackTrace();
            }
        }
        l(false);
    }

    public synchronized void F() {
        DataNode child;
        try {
            child = this.bd.getChild(m(i));
        } catch (NullPointerException e2) {
            if (ap.d()) {
                e2.printStackTrace();
            }
            A();
            if (this.bd == null) {
                R();
            }
            child = this.bd.getChild(m(i));
        }
        child.putBoolean(m(j), false);
        l(false);
    }

    public synchronized void f(int i2) {
        a(i2, false);
    }

    public synchronized void g(int i2) {
        a(m(av), Math.max(0, i2) + a(m(av)));
    }

    public synchronized void h(int i2) {
        a(m(aw), Math.max(0, i2) + a(m(aw)));
    }

    public synchronized void i(int i2) {
        a(m(au), Math.max(Math.max(0, i2) + a(m(au)), 0));
    }

    public synchronized void j(int i2) {
        a(m(at), Math.max(a(m(at)) + i2, 0));
    }

    private synchronized void a(String str, int i2) {
        DataNode child;
        try {
            child = this.be.getChild(m(as));
        } catch (NullPointerException e2) {
            if (ap.d()) {
                e2.printStackTrace();
            }
            A();
            if (this.be == null) {
                Q();
            }
            child = this.be.getChild(m(as));
        }
        try {
            child.putInteger(str, Integer.valueOf(i2));
        } catch (NullPointerException e3) {
            DataNode dataNodeQ = Q();
            this.be.addChild(dataNodeQ);
            dataNodeQ.putInteger(str, Integer.valueOf(i2));
        }
        l(aa());
    }

    private synchronized int a(String str) {
        DataNode child;
        Integer integer;
        DataNode dataNodeQ;
        try {
            child = this.be.getChild(m(as));
        } catch (NullPointerException e2) {
            if (ap.d()) {
                e2.printStackTrace();
            }
            A();
            if (this.be == null) {
                this.be = O();
            }
            child = this.be.getChild(m(as));
        }
        try {
            dataNodeQ = child;
            integer = child.getInteger(str);
        } catch (NullPointerException e3) {
            integer = 0;
            dataNodeQ = Q();
            this.be.addChild(dataNodeQ);
            l(aa());
        }
        if (integer == null) {
            integer = 0;
            dataNodeQ.putInteger(str, 0);
        }
        return integer.intValue();
    }

    public synchronized int G() {
        int iA;
        if (bg.a(bi.FAKE_NUM_GEMS)) {
            iA = bg.b(bi.FAKE_NUM_GEMS);
        } else {
            iA = (a(m(au)) + a(m(aw))) - a(m(av));
        }
        return iA;
    }

    public synchronized int H() {
        return bg.a(bi.FAKE_NUM_COINS) ? bg.b(bi.FAKE_NUM_COINS) : a(m(at));
    }

    public synchronized int a(com.topfreegames.bikerace.worldcup.a aVar) {
        DataNode child;
        int integer;
        try {
            child = this.be.getChild(m(as));
        } catch (NullPointerException e2) {
            if (ap.d()) {
                e2.printStackTrace();
            }
            A();
            if (this.be == null) {
                this.be = O();
            }
            child = this.be.getChild(m(as));
        }
        String str = aS.get(aVar);
        try {
            integer = child.getInteger(str);
        } catch (NullPointerException e3) {
            integer = 0;
            child = Q();
            this.be.addChild(child);
            l(aa());
        }
        if (integer == null) {
            integer = 0;
            child.putInteger(str, 0);
        }
        return integer.intValue();
    }

    public synchronized void a(HashMap<com.topfreegames.bikerace.worldcup.a, Integer> map) {
        for (Map.Entry<com.topfreegames.bikerace.worldcup.a, Integer> entry : map.entrySet()) {
            a(entry.getKey(), entry.getValue().intValue(), false);
        }
        l(aa());
    }

    private synchronized void a(com.topfreegames.bikerace.worldcup.a aVar, int i2, boolean z2) {
        DataNode child;
        try {
            child = this.be.getChild(m(as));
        } catch (NullPointerException e2) {
            if (ap.d()) {
                e2.printStackTrace();
            }
            A();
            if (this.be == null) {
                this.be = O();
            }
            child = this.be.getChild(m(as));
        }
        String str = aS.get(aVar);
        try {
            child.putInteger(str, Integer.valueOf(i2));
        } catch (NullPointerException e3) {
            DataNode dataNodeQ = Q();
            this.be.addChild(dataNodeQ);
            dataNodeQ.putInteger(str, Integer.valueOf(i2));
        }
        if (z2) {
            l(aa());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:127:0x01a4 A[Catch: all -> 0x00c8, NullPointerException -> 0x01b4, TRY_ENTER, TryCatch #25 {NullPointerException -> 0x01b4, blocks: (B:24:0x0053, B:26:0x005d, B:27:0x0063, B:29:0x0070, B:30:0x0076, B:128:0x01ac, B:127:0x01a4), top: B:181:0x0053, outer: #16 }] */
    /* JADX WARN: Code duplicated, block: B:128:0x01ac A[Catch: all -> 0x00c8, NullPointerException -> 0x01b4, TRY_LEAVE, TryCatch #25 {NullPointerException -> 0x01b4, blocks: (B:24:0x0053, B:26:0x005d, B:27:0x0063, B:29:0x0070, B:30:0x0076, B:128:0x01ac, B:127:0x01a4), top: B:181:0x0053, outer: #16 }] */
    /* JADX WARN: Code duplicated, block: B:158:0x01f9  */
    /* JADX WARN: Code duplicated, block: B:167:0x0170 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:171:0x004e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:173:0x0144 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:177:0x00ff A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:179:0x00b8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:181:0x0053 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:23:0x0051 A[DONT_INVERT, PHI: r0
      0x0051: PHI (r0v39 boolean) = 
      (r0v16 boolean)
      (r0v17 boolean)
      (r0v26 boolean)
      (r0v27 boolean)
      (r0v28 boolean)
      (r0v37 boolean)
      (r0v38 boolean)
      (r0v70 boolean)
      (r0v72 boolean)
      (r0v70 boolean)
     binds: [B:101:0x015f, B:92:0x0147, B:81:0x011a, B:72:0x0102, B:158:0x01f9, B:61:0x00d5, B:48:0x00bb, B:21:0x004c, B:126:0x01a1, B:22:0x004e] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:26:0x005d A[Catch: all -> 0x00c8, NullPointerException -> 0x01b4, TryCatch #25 {NullPointerException -> 0x01b4, blocks: (B:24:0x0053, B:26:0x005d, B:27:0x0063, B:29:0x0070, B:30:0x0076, B:128:0x01ac, B:127:0x01a4), top: B:181:0x0053, outer: #16 }] */
    /* JADX WARN: Code duplicated, block: B:29:0x0070 A[Catch: all -> 0x00c8, NullPointerException -> 0x01b4, TryCatch #25 {NullPointerException -> 0x01b4, blocks: (B:24:0x0053, B:26:0x005d, B:27:0x0063, B:29:0x0070, B:30:0x0076, B:128:0x01ac, B:127:0x01a4), top: B:181:0x0053, outer: #16 }] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v20 */
    /* JADX WARN: Type inference failed for: r2v21 */
    /* JADX WARN: Type inference failed for: r2v22 */
    public synchronized void g(boolean z2) {
        FileOutputStream fileOutputStreamOpenFileOutput;
        ObjectOutputStream objectOutputStream;
        ObjectOutputStream objectOutputStream2;
        NullPointerException nullPointerException;
        ObjectOutputStream objectOutputStream3;
        IOException iOException;
        ObjectOutputStream objectOutputStream4;
        FileNotFoundException fileNotFoundException;
        String strM;
        String strM2;
        ObjectOutputStream objectOutputStream5;
        boolean z3 = true;
        try {
            try {
                fileOutputStreamOpenFileOutput = this.ba.openFileOutput(z2 ? m(g) : m(f), 0);
                try {
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStreamOpenFileOutput);
                    Cipher cipherA = com.topfreegames.bikerace.d.a.a(this.ba);
                    Cipher cipherB = com.topfreegames.bikerace.d.a.b(this.ba);
                    try {
                        if (!z2 || cipherA == 0 || cipherB == null || ai()) {
                            ObjectOutputStream objectOutputStream6 = new ObjectOutputStream(bufferedOutputStream);
                            objectOutputStream6.writeObject(this.bd);
                            objectOutputStream5 = objectOutputStream6;
                            cipherA = objectOutputStream6;
                        } else {
                            ObjectOutputStream objectOutputStream7 = new ObjectOutputStream(new CipherOutputStream(bufferedOutputStream, cipherA));
                            objectOutputStream7.writeObject(this.be);
                            objectOutputStream5 = objectOutputStream7;
                            cipherA = objectOutputStream7;
                        }
                        try {
                            objectOutputStream5.flush();
                            if (fileOutputStreamOpenFileOutput != null) {
                                try {
                                    fileOutputStreamOpenFileOutput.getFD().sync();
                                } catch (IOException e2) {
                                    if (ap.d()) {
                                        e2.printStackTrace();
                                    }
                                    z3 = false;
                                }
                                if (objectOutputStream5 != null) {
                                    try {
                                        objectOutputStream5.close();
                                    } catch (IOException e3) {
                                        if (ap.d()) {
                                            e3.printStackTrace();
                                        }
                                        z3 = false;
                                    }
                                    if (z3) {
                                        try {
                                            File filesDir = this.ba.getFilesDir();
                                            if (z2) {
                                                strM = m(g);
                                            } else {
                                                strM = m(f);
                                            }
                                            File file = new File(filesDir, strM);
                                            File filesDir2 = this.ba.getFilesDir();
                                            if (z2) {
                                                strM2 = m(e);
                                            } else {
                                                strM2 = m(d);
                                            }
                                            file.renameTo(new File(filesDir2, strM2));
                                        } catch (NullPointerException e4) {
                                            ((BikeRaceApplication) this.ba.getApplicationContext()).d().b(e4);
                                        }
                                    }
                                } else if (z3) {
                                    File filesDir3 = this.ba.getFilesDir();
                                    if (z2) {
                                        strM = m(g);
                                    } else {
                                        strM = m(f);
                                    }
                                    File file2 = new File(filesDir3, strM);
                                    File filesDir4 = this.ba.getFilesDir();
                                    if (z2) {
                                        strM2 = m(e);
                                    } else {
                                        strM2 = m(d);
                                    }
                                    file2.renameTo(new File(filesDir4, strM2));
                                }
                            } else if (objectOutputStream5 != null) {
                                objectOutputStream5.close();
                                if (z3) {
                                    File filesDir5 = this.ba.getFilesDir();
                                    if (z2) {
                                        strM = m(g);
                                    } else {
                                        strM = m(f);
                                    }
                                    File file3 = new File(filesDir5, strM);
                                    File filesDir6 = this.ba.getFilesDir();
                                    if (z2) {
                                        strM2 = m(e);
                                    } else {
                                        strM2 = m(d);
                                    }
                                    file3.renameTo(new File(filesDir6, strM2));
                                }
                            } else if (z3) {
                                File filesDir7 = this.ba.getFilesDir();
                                if (z2) {
                                    strM = m(g);
                                } else {
                                    strM = m(f);
                                }
                                File file4 = new File(filesDir7, strM);
                                File filesDir8 = this.ba.getFilesDir();
                                if (z2) {
                                    strM2 = m(e);
                                } else {
                                    strM2 = m(d);
                                }
                                file4.renameTo(new File(filesDir8, strM2));
                            }
                        } catch (FileNotFoundException e5) {
                            objectOutputStream4 = objectOutputStream5;
                            fileNotFoundException = e5;
                            if (ap.d()) {
                                fileNotFoundException.printStackTrace();
                            }
                            ((BikeRaceApplication) this.ba.getApplicationContext()).d().b(fileNotFoundException);
                            if (fileOutputStreamOpenFileOutput != null) {
                                try {
                                    fileOutputStreamOpenFileOutput.getFD().sync();
                                } catch (IOException e6) {
                                    if (ap.d()) {
                                        e6.printStackTrace();
                                    }
                                }
                                if (objectOutputStream4 != 0) {
                                    try {
                                        objectOutputStream4.close();
                                        z3 = false;
                                    } catch (IOException e7) {
                                        if (ap.d()) {
                                            e7.printStackTrace();
                                        }
                                        z3 = false;
                                    }
                                } else {
                                    z3 = false;
                                }
                            } else if (objectOutputStream4 != 0) {
                                objectOutputStream4.close();
                                z3 = false;
                            } else {
                                z3 = false;
                            }
                            throw th;
                        } catch (IOException e8) {
                            objectOutputStream3 = objectOutputStream5;
                            iOException = e8;
                            if (ap.d()) {
                                iOException.printStackTrace();
                            }
                            ((BikeRaceApplication) this.ba.getApplicationContext()).d().b(iOException);
                            if (fileOutputStreamOpenFileOutput != null) {
                                try {
                                    fileOutputStreamOpenFileOutput.getFD().sync();
                                } catch (IOException e9) {
                                    if (ap.d()) {
                                        e9.printStackTrace();
                                    }
                                }
                                if (objectOutputStream3 != 0) {
                                    try {
                                        objectOutputStream3.close();
                                        z3 = false;
                                    } catch (IOException e10) {
                                        if (ap.d()) {
                                            e10.printStackTrace();
                                        }
                                        z3 = false;
                                    }
                                } else {
                                    z3 = false;
                                }
                            } else if (objectOutputStream3 != 0) {
                                objectOutputStream3.close();
                                z3 = false;
                            } else {
                                z3 = false;
                            }
                            throw th;
                        } catch (NullPointerException e11) {
                            objectOutputStream2 = objectOutputStream5;
                            nullPointerException = e11;
                            if (ap.d()) {
                                nullPointerException.printStackTrace();
                            }
                            ((BikeRaceApplication) this.ba.getApplicationContext()).d().b(nullPointerException);
                            if (fileOutputStreamOpenFileOutput != null) {
                                try {
                                    fileOutputStreamOpenFileOutput.getFD().sync();
                                } catch (IOException e12) {
                                    if (ap.d()) {
                                        e12.printStackTrace();
                                    }
                                }
                                if (objectOutputStream2 != 0) {
                                    try {
                                        objectOutputStream2.close();
                                        z3 = false;
                                    } catch (IOException e13) {
                                        if (ap.d()) {
                                            e13.printStackTrace();
                                        }
                                        z3 = false;
                                    }
                                } else {
                                    z3 = false;
                                }
                            } else if (objectOutputStream2 != 0) {
                                objectOutputStream2.close();
                                z3 = false;
                            } else {
                                z3 = false;
                            }
                            throw th;
                        } catch (Throwable th) {
                            th = th;
                            objectOutputStream = objectOutputStream5;
                            if (fileOutputStreamOpenFileOutput == null) {
                                if (objectOutputStream != null) {
                                    objectOutputStream.close();
                                }
                                throw th;
                            }
                            try {
                                fileOutputStreamOpenFileOutput.getFD().sync();
                            } catch (IOException e14) {
                                if (ap.d()) {
                                    e14.printStackTrace();
                                }
                            }
                            if (objectOutputStream != null) {
                                try {
                                    objectOutputStream.close();
                                } catch (IOException e15) {
                                    if (ap.d()) {
                                        e15.printStackTrace();
                                    }
                                }
                            }
                            throw th;
                        }
                    } catch (FileNotFoundException e16) {
                        fileNotFoundException = e16;
                        objectOutputStream4 = cipherA;
                    } catch (IOException e17) {
                        iOException = e17;
                        objectOutputStream3 = cipherA;
                    } catch (NullPointerException e18) {
                        nullPointerException = e18;
                        objectOutputStream2 = cipherA;
                    }
                } catch (FileNotFoundException e19) {
                    objectOutputStream4 = 0;
                    fileNotFoundException = e19;
                } catch (IOException e20) {
                    objectOutputStream3 = 0;
                    iOException = e20;
                } catch (NullPointerException e21) {
                    objectOutputStream2 = 0;
                    nullPointerException = e21;
                } catch (Throwable th2) {
                    th = th2;
                    objectOutputStream = null;
                }
            } catch (FileNotFoundException e22) {
                objectOutputStream4 = 0;
                fileOutputStreamOpenFileOutput = null;
                fileNotFoundException = e22;
            } catch (IOException e23) {
                objectOutputStream3 = 0;
                fileOutputStreamOpenFileOutput = null;
                iOException = e23;
            } catch (NullPointerException e24) {
                objectOutputStream2 = 0;
                fileOutputStreamOpenFileOutput = null;
                nullPointerException = e24;
            } catch (Throwable th3) {
                th = th3;
                objectOutputStream = null;
                fileOutputStreamOpenFileOutput = null;
            }
        } catch (Throwable th4) {
            th = th4;
        }
        throw th;
    }

    private synchronized void a(c cVar, boolean z2) {
        DataNode child;
        aa aaVarE = e(cVar);
        if (aaVarE != null) {
            try {
                child = aaVarE.b.getChild(m(i));
            } catch (NullPointerException e2) {
                b(aaVarE.c);
                if (aaVarE.b == null) {
                    aaVarE.b = h(aaVarE.c);
                }
                child = aaVarE.b.getChild(m(i));
            }
            try {
                child.getChild(m(aaVarE.f824a)).putBoolean(m(k), Boolean.valueOf(z2));
            } catch (NullPointerException e3) {
                child.addChild(l(aaVarE.f824a));
            }
            W();
        }
    }

    private synchronized void a(int i2, boolean z2) {
        try {
            this.bd.getChild(String.valueOf(m(X)) + i2);
        } catch (NullPointerException e2) {
            if (ap.d()) {
                e2.printStackTrace();
            }
            A();
            if (this.bd == null) {
                R();
            }
            if (this.bd.getChild(String.valueOf(m(X)) + i2) == null) {
                this.bd.addChild(b(String.valueOf(m(X)) + i2));
            }
        }
        try {
            this.bd.getChild(String.valueOf(m(X)) + i2).putBoolean(m(Y), Boolean.valueOf(z2));
        } catch (NullPointerException e3) {
            DataNode dataNodeB = b(String.valueOf(m(X)) + i2);
            this.bd.addChild(dataNodeB);
            dataNodeB.putBoolean(m(Y), Boolean.valueOf(z2));
        }
        l(false);
    }

    private static DataNode l(int i2) {
        DataNode dataNode = new DataNode(m(i2));
        dataNode.putBoolean(m(k), Boolean.valueOf(ab()));
        dataNode.putBoolean(m(l), Boolean.valueOf(ac()));
        return dataNode;
    }

    private synchronized DataNode h(boolean z2) {
        try {
        } catch (Throwable th) {
            throw th;
        }
        return z2 ? O() : R();
    }

    private synchronized DataNode O() {
        this.be = new DataNode(m(h));
        this.be.addChild(P());
        this.be.addChild(Q());
        l(aa());
        return this.be;
    }

    private synchronized DataNode P() {
        DataNode dataNode;
        dataNode = new DataNode(m(i));
        dataNode.addChild(l(z));
        dataNode.addChild(l(A));
        dataNode.addChild(l(B));
        dataNode.addChild(l(C));
        dataNode.addChild(l(D));
        dataNode.addChild(l(E));
        dataNode.addChild(l(F));
        dataNode.addChild(l(K));
        dataNode.addChild(l(N));
        dataNode.addChild(l(H));
        dataNode.addChild(l(L));
        dataNode.addChild(l(M));
        dataNode.addChild(l(P));
        dataNode.addChild(l(J));
        dataNode.addChild(l(O));
        dataNode.addChild(l(I));
        dataNode.addChild(l(Q));
        dataNode.addChild(l(S));
        dataNode.addChild(l(R));
        dataNode.addChild(l(T));
        dataNode.addChild(l(G));
        return dataNode;
    }

    private synchronized DataNode Q() {
        DataNode dataNode;
        dataNode = new DataNode(m(as));
        dataNode.putInteger(m(at), Integer.valueOf(ae()));
        dataNode.putInteger(m(au), Integer.valueOf(af()));
        dataNode.putInteger(m(aw), Integer.valueOf(af()));
        dataNode.putInteger(m(av), Integer.valueOf(ae()));
        return dataNode;
    }

    private static DataNode a(String str, boolean z2) {
        DataNode dataNode = new DataNode(str);
        dataNode.putBoolean(m(V), Boolean.valueOf(ap.q() ? false : !z2));
        dataNode.putInteger(m(W), Integer.valueOf(ae()));
        return dataNode;
    }

    private static DataNode i(boolean z2) {
        DataNode dataNode = new DataNode(m(aa));
        dataNode.putBoolean(m(ab), Boolean.valueOf(ab()));
        dataNode.putBoolean(m(ac), Boolean.valueOf(aa()));
        dataNode.putBoolean(m(ad), Boolean.valueOf(aa()));
        dataNode.putBoolean(m(ae), Boolean.valueOf(z2));
        dataNode.putInteger(m(af), Integer.valueOf(com.topfreegames.bikerace.i.b.ENGLISH.ordinal()));
        return dataNode;
    }

    private synchronized DataNode R() {
        this.bd = new DataNode(m(h));
        this.bd.addChild(S());
        this.bd.addChild(i(this.bb));
        this.bd.addChild(T());
        for (int i2 : com.topfreegames.bikerace.h.y.b) {
            this.bd.addChild(b(String.valueOf(m(X)) + i2));
        }
        this.bd.addChild(b(String.valueOf(m(X)) + 999));
        f(1);
        c(1, 1);
        c(c.REGULAR);
        W();
        return this.bd;
    }

    private static DataNode S() {
        DataNode dataNode = new DataNode(m(i));
        dataNode.addChild(l(m));
        dataNode.addChild(l(n));
        dataNode.addChild(l(o));
        dataNode.addChild(l(p));
        dataNode.addChild(l(q));
        dataNode.addChild(l(r));
        dataNode.addChild(l(s));
        dataNode.addChild(l(t));
        dataNode.addChild(l(v));
        dataNode.addChild(l(u));
        dataNode.addChild(l(w));
        dataNode.addChild(l(x));
        dataNode.addChild(l(y));
        dataNode.putBoolean(m(j), Boolean.valueOf(aa()));
        return dataNode;
    }

    private static DataNode T() {
        DataNode dataNode = new DataNode(m(ag));
        dataNode.putInteger(m(ah), Integer.valueOf(af()));
        dataNode.putInteger(m(ai), Integer.valueOf(ae()));
        dataNode.putInteger(m(ak), Integer.valueOf(ae()));
        dataNode.putInteger(m(aj), Integer.valueOf(ae()));
        dataNode.putInteger(m(al), Integer.valueOf(af()));
        dataNode.putInteger(m(am), Integer.valueOf(af()));
        dataNode.putInteger(m(an), Integer.valueOf(ae()));
        dataNode.putBoolean(m(ao), Boolean.valueOf(ac()));
        dataNode.putBoolean(m(ap), Boolean.valueOf(ad()));
        dataNode.putBoolean(m(aq), Boolean.valueOf(ad()));
        dataNode.putInteger(m(ar), Integer.valueOf(ag()));
        return dataNode;
    }

    private static DataNode b(String str) {
        DataNode dataNode = new DataNode(str);
        dataNode.putBoolean(m(Y), Boolean.valueOf(!ap.q()));
        dataNode.putInteger(m(Z), Integer.valueOf(ae()));
        int[] iArr = com.topfreegames.bikerace.h.y.b;
        int length = iArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = iArr[i2];
            dataNode.addChild(a(String.valueOf(m(U)) + i3, i3 == 1));
        }
        return dataNode;
    }

    private synchronized void U() {
        if (this.bd.getChild(String.valueOf(m(X)) + 7) == null) {
            this.bd.addChild(b(String.valueOf(m(X)) + 7));
        }
        if (this.bd.getChild(String.valueOf(m(X)) + 8) == null) {
            this.bd.addChild(b(String.valueOf(m(X)) + 8));
        }
        if (this.bd.getChild(String.valueOf(m(X)) + 9) == null) {
            this.bd.addChild(b(String.valueOf(m(X)) + 9));
        }
        if (this.bd.getChild(String.valueOf(m(X)) + 10) == null) {
            this.bd.addChild(b(String.valueOf(m(X)) + 10));
        }
        if (this.bd.getChild(String.valueOf(m(X)) + 11) == null) {
            this.bd.addChild(b(String.valueOf(m(X)) + 11));
        }
        if (this.bd.getChild(String.valueOf(m(X)) + 13) == null) {
            this.bd.addChild(b(String.valueOf(m(X)) + 13));
        }
        if (this.bd.getChild(String.valueOf(m(X)) + 14) == null) {
            this.bd.addChild(b(String.valueOf(m(X)) + 14));
        }
        if (this.bd.getChild(String.valueOf(m(X)) + 15) == null) {
            this.bd.addChild(b(String.valueOf(m(X)) + 15));
        }
        if (this.bd.getChild(String.valueOf(m(X)) + 999) == null) {
            this.bd.addChild(b(String.valueOf(m(X)) + 999));
        }
    }

    private synchronized int j(boolean z2) {
        DataNode child;
        int integer;
        try {
            child = this.bd.getChild(m(ag));
        } catch (NullPointerException e2) {
            if (ap.d()) {
                e2.printStackTrace();
            }
            A();
            if (this.bd == null) {
                R();
            }
            child = this.bd.getChild(m(ag));
        }
        String strM = m(am);
        if (z2) {
            strM = m(al);
        }
        try {
            integer = child.getInteger(strM);
        } catch (NullPointerException e3) {
            integer = 0;
            child = T();
            this.bd.addChild(child);
            W();
        }
        if (integer == null) {
            integer = 0;
            child.putInteger(strM, 0);
        }
        return integer.intValue();
    }

    private synchronized void V() {
        e(m() + 1);
    }

    private synchronized void k(boolean z2) {
        DataNode child;
        int iJ = j(z2) + 1;
        try {
            child = this.bd.getChild(m(ag));
        } catch (NullPointerException e2) {
            if (ap.d()) {
                e2.printStackTrace();
            }
            A();
            if (this.bd == null) {
                R();
            }
            child = this.bd.getChild(m(ag));
        }
        String strM = m(am);
        if (z2) {
            strM = m(al);
        }
        try {
            child.putInteger(strM, Integer.valueOf(iJ));
        } catch (NullPointerException e3) {
            DataNode dataNodeT = T();
            this.bd.addChild(dataNodeT);
            dataNodeT.putInteger(strM, Integer.valueOf(iJ));
        }
        l(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void W() {
        new Thread(new Runnable() { // from class: com.topfreegames.bikerace.z.1
            @Override // java.lang.Runnable
            public void run() {
                z.this.g(false);
                z.this.g(true);
            }
        }).start();
    }

    private synchronized void l(final boolean z2) {
        new Thread(new Runnable() { // from class: com.topfreegames.bikerace.z.2
            @Override // java.lang.Runnable
            public void run() {
                z.this.g(z2);
            }
        }).start();
    }

    private aa e(c cVar) {
        aa aaVar = new aa(null);
        switch (M()[cVar.ordinal()]) {
            case 2:
                aaVar.f824a = n;
                aaVar.b = this.bd;
                return aaVar;
            case 3:
                aaVar.f824a = m;
                aaVar.b = this.bd;
                return aaVar;
            case 4:
                aaVar.f824a = o;
                aaVar.b = this.bd;
                return aaVar;
            case 5:
                aaVar.f824a = t;
                aaVar.b = this.bd;
                return aaVar;
            case 6:
                aaVar.f824a = v;
                aaVar.b = this.bd;
                return aaVar;
            case 7:
                aaVar.f824a = q;
                aaVar.b = this.bd;
                return aaVar;
            case 8:
                aaVar.f824a = s;
                aaVar.b = this.bd;
                return aaVar;
            case 9:
                aaVar.f824a = w;
                aaVar.b = this.bd;
                return aaVar;
            case XMLStreamConstants.ATTRIBUTE /* 10 */:
                aaVar.f824a = y;
                aaVar.b = this.bd;
                return aaVar;
            case XMLStreamConstants.DTD /* 11 */:
                aaVar.f824a = p;
                aaVar.b = this.bd;
                return aaVar;
            case XMLStreamConstants.CDATA /* 12 */:
                aaVar.f824a = r;
                aaVar.b = this.bd;
                return aaVar;
            case XMLStreamConstants.NAMESPACE /* 13 */:
                aaVar.f824a = x;
                aaVar.b = this.bd;
                return aaVar;
            case XMLStreamConstants.NOTATION_DECLARATION /* 14 */:
                aaVar.f824a = u;
                aaVar.b = this.bd;
                return aaVar;
            case XMLStreamConstants.ENTITY_DECLARATION /* 15 */:
                aaVar.f824a = z;
                aaVar.b = this.be;
                aaVar.c = true;
                return aaVar;
            case 16:
                aaVar.f824a = A;
                aaVar.b = this.be;
                aaVar.c = true;
                return aaVar;
            case 17:
                aaVar.f824a = B;
                aaVar.b = this.be;
                aaVar.c = true;
                return aaVar;
            case 18:
                aaVar.f824a = C;
                aaVar.b = this.be;
                aaVar.c = true;
                return aaVar;
            case 19:
                aaVar.f824a = D;
                aaVar.b = this.be;
                aaVar.c = true;
                return aaVar;
            case 20:
                aaVar.f824a = E;
                aaVar.b = this.be;
                aaVar.c = true;
                return aaVar;
            case 21:
                aaVar.f824a = F;
                aaVar.b = this.be;
                aaVar.c = true;
                return aaVar;
            case 22:
                aaVar.f824a = G;
                aaVar.b = this.be;
                aaVar.c = true;
                return aaVar;
            case 23:
                aaVar.f824a = H;
                aaVar.b = this.be;
                aaVar.c = true;
                return aaVar;
            case 24:
                aaVar.f824a = I;
                aaVar.b = this.be;
                aaVar.c = true;
                return aaVar;
            case 25:
                aaVar.f824a = K;
                aaVar.b = this.be;
                aaVar.c = true;
                return aaVar;
            case 26:
                aaVar.f824a = J;
                aaVar.b = this.be;
                aaVar.c = true;
                return aaVar;
            case 27:
                aaVar.f824a = L;
                aaVar.b = this.be;
                aaVar.c = true;
                return aaVar;
            case 28:
                aaVar.f824a = M;
                aaVar.b = this.be;
                aaVar.c = true;
                return aaVar;
            case 29:
                aaVar.f824a = N;
                aaVar.b = this.be;
                aaVar.c = true;
                return aaVar;
            case 30:
                aaVar.f824a = O;
                aaVar.b = this.be;
                aaVar.c = true;
                return aaVar;
            case 31:
                aaVar.f824a = P;
                aaVar.b = this.be;
                aaVar.c = true;
                return aaVar;
            case 32:
                aaVar.f824a = S;
                aaVar.b = this.be;
                aaVar.c = true;
                return aaVar;
            case 33:
                aaVar.f824a = T;
                aaVar.b = this.be;
                aaVar.c = true;
                return aaVar;
            case 34:
                aaVar.f824a = R;
                aaVar.b = this.be;
                aaVar.c = true;
                return aaVar;
            case 35:
                aaVar.f824a = Q;
                aaVar.b = this.be;
                aaVar.c = true;
                return aaVar;
            default:
                return null;
        }
    }

    private static void X() {
        aQ <<= 1;
        aR <<= 2;
    }

    private static void Y() {
        DataNode dataNodeL = l(B);
        if (!((!dataNodeL.getBoolean(m(l)).booleanValue()) & dataNodeL.getBoolean(m(k)).booleanValue())) {
            X();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String m(int i2) {
        return aT.get(i2);
    }

    private static byte[] a(byte[] bArr) {
        aU.clear();
        for (int i2 = 1; i2 < bArr.length; i2++) {
            aU.add(Integer.valueOf((bArr[i2] + 128) ^ (bArr[i2 / bArr.length] + 128)));
            bArr[i2] = (byte) ((bArr[i2] << bArr[i2 - 1]) & 3);
        }
        int iIntValue = aU.get(0).intValue();
        int iIntValue2 = aU.get(1).intValue();
        aU.remove(0);
        aU.remove(0);
        aV.clear();
        for (int i3 = 0; i3 < aU.size() - 1; i3++) {
            aV.add(new int[]{i3, ((iIntValue * iIntValue2) + i3) % aU.size()});
        }
        int size = aV.size() - 1;
        while (true) {
            int i4 = size;
            if (i4 > -1) {
                d(aV.get(i4)[0], aV.get(i4)[1]);
                size = i4 - 1;
            } else {
                return bArr;
            }
        }
    }

    private static String b(byte[] bArr) {
        for (int i2 = 0; i2 < aU.size(); i2++) {
            aU.remove(i2);
        }
        return new String(c(bArr));
    }

    private static void d(int i2, int i3) {
        int iIntValue = aU.get(i2).intValue();
        aU.set(i2, aU.get(i3));
        aU.set(i3, Integer.valueOf(iIntValue));
    }

    private static byte[] c(byte[] bArr) {
        byte[] bArr2 = new byte[aU.size()];
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 < aU.size()) {
                bArr2[i3] = (byte) aU.get(i3).intValue();
                i2 = i3 + 1;
            } else {
                return bArr2;
            }
        }
    }

    private static boolean Z() {
        return Math.abs(aQ ^ aR) > 0;
    }

    private static boolean aa() {
        return aW;
    }

    private static boolean ab() {
        return aX;
    }

    private static boolean ac() {
        return aY;
    }

    private static boolean ad() {
        return aZ;
    }

    private static int ae() {
        return 0;
    }

    private static int af() {
        return 0;
    }

    private static int ag() {
        return -1;
    }

    private static void ah() {
        int[] iArr = {o, p, q, r, s, t, u, v, w, x, y, z, A, B, C, D, E, F, G};
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < iArr.length - 1; i2++) {
            arrayList.add(Integer.valueOf(iArr[i2 + 1] - iArr[i2]));
        }
        if (Collections.max(arrayList) == Collections.min(arrayList)) {
            X();
        }
    }

    private static boolean ai() {
        return Math.abs(aQ ^ aR) > 0;
    }
}
