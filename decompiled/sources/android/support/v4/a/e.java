package android.support.v4.a;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import com.amazonaws.javax.xml.stream.xerces.util.XMLChar;

/* JADX INFO: compiled from: LocalBroadcastManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final IntentFilter f4a;
    final BroadcastReceiver b;
    boolean c;

    e(IntentFilter intentFilter, BroadcastReceiver broadcastReceiver) {
        this.f4a = intentFilter;
        this.b = broadcastReceiver;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(XMLChar.MASK_NCNAME);
        sb.append("Receiver{");
        sb.append(this.b);
        sb.append(" filter=");
        sb.append(this.f4a);
        sb.append("}");
        return sb.toString();
    }
}
