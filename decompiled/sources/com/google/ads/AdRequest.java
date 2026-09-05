package com.google.ads;

import android.content.Context;
import android.location.Location;
import android.text.TextUtils;
import com.google.ads.doubleclick.DfpExtras;
import com.google.ads.mediation.NetworkExtras;
import com.google.ads.mediation.admob.AdMobAdapterExtras;
import com.google.ads.util.AdUtil;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class AdRequest {
    public static final String LOGTAG = "Ads";
    public static final String TEST_EMULATOR;
    public static final String VERSION = "6.4.1";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final SimpleDateFormat f611a = new SimpleDateFormat("yyyyMMdd");
    private static Method b;
    private static Method c;
    private Gender d = null;
    private Date e = null;
    private Set<String> f = null;
    private Map<String, Object> g = null;
    private final Map<Class<?>, NetworkExtras> h = new HashMap();
    private Location i = null;
    private boolean j = false;
    private boolean k = false;
    private Set<String> l = null;

    public enum Gender {
        UNKNOWN,
        MALE,
        FEMALE
    }

    static {
        b = null;
        c = null;
        try {
            for (Method method : Class.forName("com.google.analytics.tracking.android.AdMobInfo").getMethods()) {
                if (method.getName().equals("getInstance") && method.getParameterTypes().length == 0) {
                    b = method;
                } else if (method.getName().equals("getJoinIds") && method.getParameterTypes().length == 0) {
                    c = method;
                }
            }
            if (b == null || c == null) {
                b = null;
                c = null;
                com.google.ads.util.b.e("No Google Analytics: Library Incompatible.");
            }
        } catch (ClassNotFoundException e) {
            com.google.ads.util.b.a("No Google Analytics: Library Not Found.");
        } catch (Throwable th) {
            com.google.ads.util.b.a("No Google Analytics: Error Loading Library");
        }
        TEST_EMULATOR = AdUtil.b("emulator");
    }

    public enum ErrorCode {
        INVALID_REQUEST("Invalid Ad request."),
        NO_FILL("Ad request successful, but no ad returned due to lack of ad inventory."),
        NETWORK_ERROR("A network error occurred."),
        INTERNAL_ERROR("There was an internal error.");


        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final String f612a;

        ErrorCode(String str) {
            this.f612a = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.f612a;
        }
    }

    public AdRequest setGender(Gender gender) {
        this.d = gender;
        return this;
    }

    public Gender getGender() {
        return this.d;
    }

    @Deprecated
    public AdRequest setBirthday(String str) {
        if (str == "" || str == null) {
            this.e = null;
        } else {
            try {
                this.e = f611a.parse(str);
            } catch (ParseException e) {
                com.google.ads.util.b.e("Birthday format invalid.  Expected 'YYYYMMDD' where 'YYYY' is a 4 digit year, 'MM' is a two digit month, and 'DD' is a two digit day.  Birthday value ignored");
                this.e = null;
            }
        }
        return this;
    }

    public AdRequest setBirthday(Date date) {
        if (date == null) {
            this.e = null;
        } else {
            this.e = new Date(date.getTime());
        }
        return this;
    }

    public AdRequest setBirthday(Calendar calendar) {
        if (calendar == null) {
            this.e = null;
        } else {
            setBirthday(calendar.getTime());
        }
        return this;
    }

    public Date getBirthday() {
        return this.e;
    }

    public AdRequest clearBirthday() {
        this.e = null;
        return this;
    }

    @Deprecated
    public AdRequest setPlusOneOptOut(boolean z) {
        a().setPlusOneOptOut(z);
        return this;
    }

    @Deprecated
    public boolean getPlusOneOptOut() {
        return a().getPlusOneOptOut();
    }

    public AdRequest setKeywords(Set<String> set) {
        this.f = set;
        return this;
    }

    public AdRequest addKeyword(String str) {
        if (this.f == null) {
            this.f = new HashSet();
        }
        this.f.add(str);
        return this;
    }

    public AdRequest addKeywords(Set<String> set) {
        if (this.f == null) {
            this.f = new HashSet();
        }
        this.f.addAll(set);
        return this;
    }

    public Set<String> getKeywords() {
        if (this.f == null) {
            return null;
        }
        return Collections.unmodifiableSet(this.f);
    }

    private synchronized AdMobAdapterExtras a() {
        if (getNetworkExtras(AdMobAdapterExtras.class) == null) {
            setNetworkExtras(new AdMobAdapterExtras());
        }
        return (AdMobAdapterExtras) getNetworkExtras(AdMobAdapterExtras.class);
    }

    @Deprecated
    public AdRequest setExtras(Map<String, Object> map) {
        a().setExtras(map);
        return this;
    }

    @Deprecated
    public AdRequest addExtra(String str, Object obj) {
        AdMobAdapterExtras adMobAdapterExtrasA = a();
        if (adMobAdapterExtrasA.getExtras() == null) {
            adMobAdapterExtrasA.setExtras(new HashMap());
        }
        adMobAdapterExtrasA.getExtras().put(str, obj);
        return this;
    }

    public AdRequest setNetworkExtras(NetworkExtras networkExtras) {
        if (networkExtras != null) {
            this.h.put(networkExtras.getClass(), networkExtras);
        }
        return this;
    }

    public AdRequest removeNetworkExtras(Class<?> cls) {
        this.h.remove(cls);
        return this;
    }

    public <T> T getNetworkExtras(Class<T> cls) {
        return (T) this.h.get(cls);
    }

    public AdRequest setMediationExtras(Map<String, Object> map) {
        this.g = map;
        return this;
    }

    public AdRequest addMediationExtra(String str, Object obj) {
        if (this.g == null) {
            this.g = new HashMap();
        }
        this.g.put(str, obj);
        return this;
    }

    public AdRequest setLocation(Location location) {
        this.i = location;
        return this;
    }

    public Location getLocation() {
        return this.i;
    }

    @Deprecated
    public AdRequest setTesting(boolean z) {
        this.j = z;
        return this;
    }

    public Map<String, Object> getRequestMap(Context context) {
        String str;
        HashMap map = new HashMap();
        if (this.f != null) {
            map.put("kw", this.f);
        }
        if (this.d != null) {
            map.put("cust_gender", Integer.valueOf(this.d.ordinal()));
        }
        if (this.e != null) {
            map.put("cust_age", f611a.format(this.e));
        }
        if (this.i != null) {
            map.put("uule", AdUtil.a(this.i));
        }
        if (this.j) {
            map.put("testing", 1);
        }
        if (isTestDevice(context)) {
            map.put("adtest", "on");
        } else if (!this.k) {
            if (AdUtil.c()) {
                str = "AdRequest.TEST_EMULATOR";
            } else {
                str = "\"" + AdUtil.a(context) + "\"";
            }
            com.google.ads.util.b.c("To get test ads on this device, call adRequest.addTestDevice(" + str + ");");
            this.k = true;
        }
        AdMobAdapterExtras adMobAdapterExtras = (AdMobAdapterExtras) getNetworkExtras(AdMobAdapterExtras.class);
        DfpExtras dfpExtras = (DfpExtras) getNetworkExtras(DfpExtras.class);
        if (dfpExtras != null && dfpExtras.getExtras() != null && !dfpExtras.getExtras().isEmpty()) {
            map.put("extras", dfpExtras.getExtras());
        } else if (adMobAdapterExtras != null && adMobAdapterExtras.getExtras() != null && !adMobAdapterExtras.getExtras().isEmpty()) {
            map.put("extras", adMobAdapterExtras.getExtras());
        }
        if (dfpExtras != null) {
            String publisherProvidedId = dfpExtras.getPublisherProvidedId();
            if (!TextUtils.isEmpty(publisherProvidedId)) {
                map.put("ppid", publisherProvidedId);
            }
        }
        if (this.g != null) {
            map.put("mediation_extras", this.g);
        }
        try {
            if (b != null) {
                Map map2 = (Map) c.invoke(b.invoke(null, new Object[0]), new Object[0]);
                if (map2 != null && map2.size() > 0) {
                    map.put("analytics_join_id", map2);
                }
            }
        } catch (Throwable th) {
            com.google.ads.util.b.c("Internal Analytics Error:", th);
        }
        return map;
    }

    public AdRequest addTestDevice(String str) {
        if (this.l == null) {
            this.l = new HashSet();
        }
        this.l.add(str);
        return this;
    }

    public AdRequest setTestDevices(Set<String> set) {
        this.l = set;
        return this;
    }

    public boolean isTestDevice(Context context) {
        String strA;
        return (this.l == null || (strA = AdUtil.a(context)) == null || !this.l.contains(strA)) ? false : true;
    }
}
