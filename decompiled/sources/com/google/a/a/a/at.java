package com.google.a.a.a;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

/* JADX INFO: compiled from: StandardExceptionParser.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class at implements q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final TreeSet<String> f585a = new TreeSet<>();

    public at(Context context, Collection<String> collection) {
        a(context, collection);
    }

    public void a(Context context, Collection<String> collection) {
        this.f585a.clear();
        HashSet<String> hashSet = new HashSet();
        if (collection != null) {
            hashSet.addAll(collection);
        }
        if (context != null) {
            try {
                String packageName = context.getApplicationContext().getPackageName();
                this.f585a.add(packageName);
                ActivityInfo[] activityInfoArr = context.getApplicationContext().getPackageManager().getPackageInfo(packageName, 15).activities;
                if (activityInfoArr != null) {
                    for (ActivityInfo activityInfo : activityInfoArr) {
                        hashSet.add(activityInfo.packageName);
                    }
                }
            } catch (PackageManager.NameNotFoundException e) {
                ah.b("No package found");
            }
        }
        for (String str : hashSet) {
            boolean z = true;
            for (String str2 : this.f585a) {
                if (!str.startsWith(str2)) {
                    if (!str2.startsWith(str)) {
                        break;
                    }
                    this.f585a.remove(str2);
                    break;
                }
                z = false;
            }
            if (z) {
                this.f585a.add(str);
            }
        }
    }

    protected Throwable a(Throwable th) {
        while (th.getCause() != null) {
            th = th.getCause();
        }
        return th;
    }

    protected StackTraceElement b(Throwable th) {
        StackTraceElement[] stackTrace = th.getStackTrace();
        if (stackTrace == null || stackTrace.length == 0) {
            return null;
        }
        for (StackTraceElement stackTraceElement : stackTrace) {
            String className = stackTraceElement.getClassName();
            Iterator<String> it = this.f585a.iterator();
            while (it.hasNext()) {
                if (className.startsWith(it.next())) {
                    return stackTraceElement;
                }
            }
        }
        return stackTrace[0];
    }

    protected String a(Throwable th, StackTraceElement stackTraceElement, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(th.getClass().getSimpleName());
        if (stackTraceElement != null) {
            String[] strArrSplit = stackTraceElement.getClassName().split("\\.");
            String str2 = "unknown";
            if (strArrSplit != null && strArrSplit.length > 0) {
                str2 = strArrSplit[strArrSplit.length - 1];
            }
            sb.append(String.format(" (@%s:%s:%s)", str2, stackTraceElement.getMethodName(), Integer.valueOf(stackTraceElement.getLineNumber())));
        }
        if (str != null) {
            sb.append(String.format(" {%s}", str));
        }
        return sb.toString();
    }

    @Override // com.google.a.a.a.q
    public String a(String str, Throwable th) {
        return a(a(th), b(a(th)), str);
    }
}
