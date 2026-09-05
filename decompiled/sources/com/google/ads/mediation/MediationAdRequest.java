package com.google.ads.mediation;

import android.content.Context;
import android.location.Location;
import com.google.ads.AdRequest;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class MediationAdRequest {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private AdRequest f699a;
    private boolean b;
    private boolean c;

    public MediationAdRequest(AdRequest adRequest, Context context, boolean z) {
        this.f699a = adRequest;
        this.c = z;
        if (context == null) {
            this.b = true;
        } else {
            this.b = adRequest.isTestDevice(context);
        }
    }

    public AdRequest.Gender getGender() {
        return this.f699a.getGender();
    }

    public Date getBirthday() {
        return this.f699a.getBirthday();
    }

    public Integer getAgeInYears() {
        if (this.f699a.getBirthday() == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar.setTime(this.f699a.getBirthday());
        Integer numValueOf = Integer.valueOf(calendar2.get(1) - calendar.get(1));
        if (calendar2.get(6) < calendar.get(6)) {
            return Integer.valueOf(numValueOf.intValue() - 1);
        }
        return numValueOf;
    }

    public Set<String> getKeywords() {
        if (this.f699a.getKeywords() == null) {
            return null;
        }
        return Collections.unmodifiableSet(this.f699a.getKeywords());
    }

    public Location getLocation() {
        if (this.f699a.getLocation() == null || !this.c) {
            return null;
        }
        return new Location(this.f699a.getLocation());
    }

    public boolean isTesting() {
        return this.b;
    }
}
