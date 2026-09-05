package com.topfreegames.bikerace.activities;

import android.content.res.Resources;
import android.view.View;
import com.amazonaws.javax.xml.stream.XMLStreamConstants;

/* JADX INFO: compiled from: ShopActivity.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class ao implements View.OnClickListener {
    private static /* synthetic */ int[] c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ ShopActivity f1070a;
    private com.topfreegames.bikerace.c b;

    static /* synthetic */ int[] a() {
        int[] iArr = c;
        if (iArr == null) {
            iArr = new int[com.topfreegames.bikerace.c.valuesCustom().length];
            try {
                iArr[com.topfreegames.bikerace.c.ACROBATIC.ordinal()] = 12;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.ARMY.ordinal()] = 17;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.BEAT.ordinal()] = 13;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.BRONZE.ordinal()] = 8;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.COP.ordinal()] = 6;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.EASTER.ordinal()] = 21;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.GHOST.ordinal()] = 4;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.GIRL.ordinal()] = 11;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.GOLD.ordinal()] = 10;
            } catch (NoSuchFieldError e9) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.HALLOWEEN.ordinal()] = 18;
            } catch (NoSuchFieldError e10) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.KIDS.ordinal()] = 3;
            } catch (NoSuchFieldError e11) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.NINJA.ordinal()] = 5;
            } catch (NoSuchFieldError e12) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.NOT_USED_IOS_DAILY_WORLD_BIKE_INDEX_HOLDER.ordinal()] = 22;
            } catch (NoSuchFieldError e13) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.REGULAR.ordinal()] = 1;
            } catch (NoSuchFieldError e14) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.RETRO.ordinal()] = 7;
            } catch (NoSuchFieldError e15) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.SANTA.ordinal()] = 20;
            } catch (NoSuchFieldError e16) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.SILVER.ordinal()] = 9;
            } catch (NoSuchFieldError e17) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.SPAM.ordinal()] = 14;
            } catch (NoSuchFieldError e18) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.SUPER.ordinal()] = 2;
            } catch (NoSuchFieldError e19) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.THANKSGIVING.ordinal()] = 19;
            } catch (NoSuchFieldError e20) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.ULTRA.ordinal()] = 15;
            } catch (NoSuchFieldError e21) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_ARGENTINA.ordinal()] = 35;
            } catch (NoSuchFieldError e22) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_AUSTRALIA.ordinal()] = 25;
            } catch (NoSuchFieldError e23) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_BELGIUM.ordinal()] = 32;
            } catch (NoSuchFieldError e24) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_BRAZIL.ordinal()] = 29;
            } catch (NoSuchFieldError e25) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_ENGLAND.ordinal()] = 24;
            } catch (NoSuchFieldError e26) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_FRANCE.ordinal()] = 27;
            } catch (NoSuchFieldError e27) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_GERMANY.ordinal()] = 28;
            } catch (NoSuchFieldError e28) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_ITALY.ordinal()] = 34;
            } catch (NoSuchFieldError e29) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_JAPAN.ordinal()] = 31;
            } catch (NoSuchFieldError e30) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_MEXICO.ordinal()] = 33;
            } catch (NoSuchFieldError e31) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_NETHERLANDS.ordinal()] = 26;
            } catch (NoSuchFieldError e32) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_SPAIN.ordinal()] = 30;
            } catch (NoSuchFieldError e33) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_USA.ordinal()] = 23;
            } catch (NoSuchFieldError e34) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.ZOMBIE.ordinal()] = 16;
            } catch (NoSuchFieldError e35) {
            }
            c = iArr;
        }
        return iArr;
    }

    public ao(ShopActivity shopActivity, com.topfreegames.bikerace.c cVar) {
        this.f1070a = shopActivity;
        this.b = com.topfreegames.bikerace.c.REGULAR;
        this.b = cVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        String string;
        if (this.f1070a.c != null && !this.f1070a.c.b()) {
            this.f1070a.a(aj.BILLING_UNAVAILABLE.ordinal());
            return;
        }
        BikeRaceApplication bikeRaceApplication = (BikeRaceApplication) this.f1070a.getApplication();
        if (bikeRaceApplication.a().a(this.b)) {
            Resources resources = this.f1070a.getResources();
            switch (a()[this.b.ordinal()]) {
                case 2:
                    string = resources.getString(2131099769);
                    break;
                case 3:
                    string = resources.getString(2131099766);
                    break;
                case 4:
                    string = resources.getString(2131099772);
                    break;
                case 5:
                    string = resources.getString(2131099793);
                    break;
                case 6:
                    string = resources.getString(2131099797);
                    break;
                case 7:
                    string = resources.getString(2131099781);
                    break;
                case 8:
                    string = resources.getString(2131099785);
                    break;
                case 9:
                    string = resources.getString(2131099789);
                    break;
                case XMLStreamConstants.ATTRIBUTE /* 10 */:
                    string = resources.getString(2131099791);
                    break;
                case XMLStreamConstants.DTD /* 11 */:
                    string = resources.getString(2131099779);
                    break;
                case XMLStreamConstants.CDATA /* 12 */:
                    string = resources.getString(2131099783);
                    break;
                case XMLStreamConstants.NAMESPACE /* 13 */:
                    string = resources.getString(2131099799);
                    break;
                case XMLStreamConstants.NOTATION_DECLARATION /* 14 */:
                    string = resources.getString(2131099795);
                    break;
                case XMLStreamConstants.ENTITY_DECLARATION /* 15 */:
                    string = resources.getString(2131099801);
                    break;
                case 16:
                    string = resources.getString(2131099804);
                    break;
                case 17:
                    string = resources.getString(2131099787);
                    break;
                case 18:
                    string = resources.getString(2131099806);
                    break;
                case 19:
                    string = resources.getString(2131099808);
                    break;
                case 20:
                    string = resources.getString(2131099810);
                    break;
                case 21:
                    string = resources.getString(2131099812);
                    break;
                default:
                    return;
            }
            this.f1070a.c.a(string);
            bikeRaceApplication.d().a(this.b);
        }
    }
}
