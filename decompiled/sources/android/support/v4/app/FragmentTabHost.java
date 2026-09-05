package android.support.v4.app;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.TabHost;
import java.util.ArrayList;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class FragmentTabHost extends TabHost implements TabHost.OnTabChangeListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ArrayList<l> f10a;
    private Context b;
    private i c;
    private int d;
    private TabHost.OnTabChangeListener e;
    private l f;
    private boolean g;

    class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: android.support.v4.app.FragmentTabHost.SavedState.1
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        String f11a;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.f11a = parcel.readString();
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.f11a);
        }

        public String toString() {
            return "FragmentTabHost.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " curTab=" + this.f11a + "}";
        }
    }

    @Override // android.widget.TabHost
    @Deprecated
    public void setup() {
        throw new IllegalStateException("Must call setup() that takes a Context and FragmentManager");
    }

    @Override // android.widget.TabHost
    public void setOnTabChangedListener(TabHost.OnTabChangeListener onTabChangeListener) {
        this.e = onTabChangeListener;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        String currentTabTag = getCurrentTabTag();
        m mVarA = null;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.f10a.size()) {
                break;
            }
            l lVar = this.f10a.get(i2);
            lVar.d = this.c.a(lVar.f20a);
            if (lVar.d != null && !lVar.d.isDetached()) {
                if (lVar.f20a.equals(currentTabTag)) {
                    this.f = lVar;
                } else {
                    if (mVarA == null) {
                        mVarA = this.c.a();
                    }
                    mVarA.a(lVar.d);
                }
            }
            i = i2 + 1;
        }
        this.g = true;
        m mVarA2 = a(currentTabTag, mVarA);
        if (mVarA2 != null) {
            mVarA2.a();
            this.c.b();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.g = false;
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f11a = getCurrentTabTag();
        return savedState;
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setCurrentTabByTag(savedState.f11a);
    }

    @Override // android.widget.TabHost.OnTabChangeListener
    public void onTabChanged(String str) {
        m mVarA;
        if (this.g && (mVarA = a(str, null)) != null) {
            mVarA.a();
        }
        if (this.e != null) {
            this.e.onTabChanged(str);
        }
    }

    private m a(String str, m mVar) {
        l lVar = null;
        int i = 0;
        while (i < this.f10a.size()) {
            l lVar2 = this.f10a.get(i);
            if (!lVar2.f20a.equals(str)) {
                lVar2 = lVar;
            }
            i++;
            lVar = lVar2;
        }
        if (lVar == null) {
            throw new IllegalStateException("No tab known for tag " + str);
        }
        if (this.f != lVar) {
            if (mVar == null) {
                mVar = this.c.a();
            }
            if (this.f != null && this.f.d != null) {
                mVar.a(this.f.d);
            }
            if (lVar != null) {
                if (lVar.d == null) {
                    lVar.d = Fragment.instantiate(this.b, lVar.b.getName(), lVar.c);
                    mVar.a(this.d, lVar.d, lVar.f20a);
                } else {
                    mVar.b(lVar.d);
                }
            }
            this.f = lVar;
        }
        return mVar;
    }
}
