package com.chartboost.sdk.Libraries;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.Signature;
import android.os.Looper;
import android.view.Display;
import android.view.WindowManager;
import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import com.chartboost.sdk.Chartboost;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.security.auth.x500.X500Principal;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static String f381a = null;
    private static final X500Principal b = new X500Principal("CN=Android Debug,O=Android,C=US");

    public static SharedPreferences a() {
        Chartboost chartboostSharedChartboost = Chartboost.sharedChartboost();
        if (chartboostSharedChartboost.getContext() == null) {
            throw new IllegalStateException("The context must be set through the Chartboost method onCreate() before modifying or accessing preferences.");
        }
        return chartboostSharedChartboost.getContext().getSharedPreferences("cbPrefs", 0);
    }

    public static String b() {
        if (c()) {
            return null;
        }
        if (f381a != null) {
            return f381a;
        }
        f381a = c.a();
        return f381a;
    }

    public static boolean c() {
        return a().getBoolean("cbIdentityTrackingDisabled", false);
    }

    public static boolean d() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static boolean a(Context context) {
        boolean zEquals;
        try {
            Signature[] signatureArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures;
            int i = 0;
            boolean z = false;
            while (true) {
                try {
                    if (i < signatureArr.length) {
                        zEquals = ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(signatureArr[i].toByteArray()))).getSubjectX500Principal().equals(b);
                        if (zEquals) {
                            break;
                        }
                        i++;
                        z = zEquals;
                    } else {
                        zEquals = z;
                        break;
                    }
                } catch (Exception e) {
                    zEquals = z;
                }
            }
        } catch (Exception e2) {
            zEquals = false;
        }
        return zEquals | ((context.getApplicationInfo().flags & 2) != 0);
    }

    public static String a(Map<String, Object> map) {
        String strEncode;
        if (map == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        if (!map.keySet().isEmpty()) {
            sb.append("?");
        }
        for (String str : map.keySet()) {
            if (sb.length() > 1) {
                sb.append("&");
            }
            String string = map.get(str).toString();
            if (str == null) {
                strEncode = "";
            } else {
                try {
                    strEncode = URLEncoder.encode(str, XMLStreamWriterImpl.UTF_8);
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException("This method requires UTF-8 encoding support", e);
                }
            }
            sb.append(strEncode);
            sb.append("=");
            sb.append(string != null ? URLEncoder.encode(string, XMLStreamWriterImpl.UTF_8) : "");
        }
        return sb.toString();
    }

    public static List<?> a(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                Object objA = jSONArray.get(i);
                if (objA instanceof JSONObject) {
                    objA = a((JSONObject) objA);
                } else if (objA instanceof JSONArray) {
                    objA = a((JSONArray) objA);
                } else if (objA.equals(JSONObject.NULL)) {
                    objA = null;
                }
                arrayList.add(objA);
            } catch (Exception e) {
            }
        }
        return arrayList;
    }

    public static Map<String, Object> a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        HashMap map = new HashMap();
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            try {
                String next = itKeys.next();
                Object objA = jSONObject.get(next);
                if (objA instanceof JSONObject) {
                    objA = a((JSONObject) objA);
                } else if (objA instanceof JSONArray) {
                    objA = a((JSONArray) objA);
                } else if (objA.equals(JSONObject.NULL)) {
                    objA = null;
                }
                map.put(next, objA);
            } catch (Exception e) {
            }
        }
        return map;
    }

    public static JSONArray a(List<?> list) {
        if (list == null) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            try {
                Object objA = list.get(i);
                if (objA instanceof Map) {
                    objA = b((Map<?, ?>) objA);
                } else if (objA instanceof List) {
                    objA = a((List<?>) objA);
                } else if (objA == null) {
                    objA = JSONObject.NULL;
                }
                jSONArray.put(objA);
            } catch (Exception e) {
            }
        }
        return jSONArray;
    }

    public static JSONObject b(Map<?, ?> map) {
        if (map == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            String string = entry.getKey().toString();
            Object value = entry.getValue();
            try {
                if (value instanceof Map) {
                    value = b((Map<?, ?>) value);
                } else if (value instanceof List) {
                    value = a((List<?>) value);
                } else if (value == null) {
                    value = JSONObject.NULL;
                }
                jSONObject.put(string, value);
            } catch (Exception e) {
            }
        }
        return jSONObject;
    }

    public static float b(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    public static int a(int i, Context context) {
        return Math.round(i * b(context));
    }

    public static float b(int i, Context context) {
        return i * b(context);
    }

    public static CBOrientation c(Context context) {
        char c;
        boolean z;
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        int i = context.getResources().getConfiguration().orientation;
        int rotation = defaultDisplay.getRotation();
        if (defaultDisplay.getWidth() == defaultDisplay.getHeight()) {
            c = 3;
        } else {
            c = defaultDisplay.getWidth() < defaultDisplay.getHeight() ? (char) 1 : (char) 2;
        }
        if (c == 1) {
            z = true;
        } else if (c == 2) {
            z = false;
        } else {
            z = (c == 3 && i != 1 && i == 2) ? false : true;
        }
        if (rotation != 0 && rotation != 2) {
            z = !z;
        }
        if (z) {
            switch (rotation) {
                case 1:
                    return CBOrientation.LANDSCAPE_LEFT;
                case 2:
                    return CBOrientation.PORTRAIT_REVERSE;
                case 3:
                    return CBOrientation.LANDSCAPE_RIGHT;
                default:
                    return CBOrientation.PORTRAIT;
            }
        }
        switch (rotation) {
            case 1:
                return CBOrientation.PORTRAIT_LEFT;
            case 2:
                return CBOrientation.LANDSCAPE_REVERSE;
            case 3:
                return CBOrientation.PORTRAIT_RIGHT;
            default:
                return CBOrientation.LANDSCAPE;
        }
    }

    public static JSONObject a(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof JSONObject) {
            return (JSONObject) obj;
        }
        if (obj instanceof Map) {
            if (!e.a(new e.g[0]).a(obj)) {
                throw new IllegalArgumentException("The given Map must have all String keys in order to be converted to JSON.");
            }
            return new JSONObject((Map) obj);
        }
        throw new IllegalArgumentException("The given argument must be either a JSONObject or a Map that can be converted to a JSONObject.");
    }

    public static void a(HttpResponse httpResponse) {
        if (httpResponse != null) {
            try {
                if (httpResponse.getEntity() != null) {
                    a(httpResponse.getEntity());
                }
            } catch (Exception e) {
            }
        }
    }

    public static void a(HttpEntity httpEntity) {
        try {
            httpEntity.consumeContent();
        } catch (Exception e) {
        }
    }
}
