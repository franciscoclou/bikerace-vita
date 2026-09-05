package com.google.android.gms.analytics.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class Command implements Parcelable {
    public static final Parcelable.Creator<Command> CREATOR = new Parcelable.Creator<Command>() { // from class: com.google.android.gms.analytics.internal.Command.1
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Command createFromParcel(Parcel parcel) {
            return new Command(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Command[] newArray(int i) {
            return new Command[i];
        }
    };

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f733a;
    private String b;
    private String c;

    public Command(String str, String str2, String str3) {
        this.f733a = str;
        this.b = str2;
        this.c = str3;
    }

    public String a() {
        return this.f733a;
    }

    public String b() {
        return this.c;
    }

    public Command() {
    }

    Command(Parcel parcel) {
        a(parcel);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f733a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
    }

    private void a(Parcel parcel) {
        this.f733a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
    }
}
