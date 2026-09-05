package com.facebook;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.a.c;
import android.util.Log;
import com.facebook.internal.NativeProtocol;
import com.facebook.widget.FacebookDialog;
import java.util.UUID;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class UiLifecycleHelper {
    private static final String ACTIVITY_NULL_MESSAGE = "activity cannot be null";
    private static final String DIALOG_CALL_BUNDLE_SAVE_KEY = "com.facebook.UiLifecycleHelper.pendingFacebookDialogCallKey";
    private final Activity activity;
    private AppEventsLogger appEventsLogger;
    private final c broadcastManager;
    private final Session.StatusCallback callback;
    private FacebookDialog.PendingCall pendingFacebookDialogCall;
    private final BroadcastReceiver receiver;

    public UiLifecycleHelper(Activity activity, Session.StatusCallback statusCallback) {
        if (activity == null) {
            throw new IllegalArgumentException(ACTIVITY_NULL_MESSAGE);
        }
        this.activity = activity;
        this.callback = statusCallback;
        this.receiver = new ActiveSessionBroadcastReceiver(this, null);
        this.broadcastManager = c.a(activity);
    }

    public void onCreate(Bundle bundle) {
        Session activeSession = Session.getActiveSession();
        if (activeSession == null) {
            if (bundle != null) {
                activeSession = Session.restoreSession(this.activity, null, this.callback, bundle);
            }
            if (activeSession == null) {
                activeSession = new Session(this.activity);
            }
            Session.setActiveSession(activeSession);
        }
        if (bundle != null) {
            this.pendingFacebookDialogCall = (FacebookDialog.PendingCall) bundle.getParcelable(DIALOG_CALL_BUNDLE_SAVE_KEY);
        }
    }

    public void onResume() {
        Session activeSession = Session.getActiveSession();
        if (activeSession != null) {
            if (this.callback != null) {
                activeSession.addCallback(this.callback);
            }
            if (SessionState.CREATED_TOKEN_LOADED.equals(activeSession.getState())) {
                activeSession.openForRead(null);
            }
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Session.ACTION_ACTIVE_SESSION_SET);
        intentFilter.addAction(Session.ACTION_ACTIVE_SESSION_UNSET);
        this.broadcastManager.a(this.receiver, intentFilter);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        onActivityResult(i, i2, intent, null);
    }

    public void onActivityResult(int i, int i2, Intent intent, FacebookDialog.Callback callback) {
        Session activeSession = Session.getActiveSession();
        if (activeSession != null) {
            activeSession.onActivityResult(this.activity, i, i2, intent);
        }
        handleFacebookDialogActivityResult(i, i2, intent, callback);
    }

    public void onSaveInstanceState(Bundle bundle) {
        Session.saveSession(Session.getActiveSession(), bundle);
        bundle.putParcelable(DIALOG_CALL_BUNDLE_SAVE_KEY, this.pendingFacebookDialogCall);
    }

    public void onPause() {
        Session activeSession;
        this.broadcastManager.a(this.receiver);
        if (this.callback != null && (activeSession = Session.getActiveSession()) != null) {
            activeSession.removeCallback(this.callback);
        }
    }

    public void onStop() {
        AppEventsLogger.onContextStop();
    }

    public void onDestroy() {
    }

    public void trackPendingDialogCall(FacebookDialog.PendingCall pendingCall) {
        if (this.pendingFacebookDialogCall != null) {
            Log.i("Facebook", "Tracking new app call while one is still pending; canceling pending call.");
            cancelPendingAppCall(null);
        }
        this.pendingFacebookDialogCall = pendingCall;
    }

    public AppEventsLogger getAppEventsLogger() {
        Session activeSession = Session.getActiveSession();
        if (activeSession == null) {
            return null;
        }
        if (this.appEventsLogger == null || !this.appEventsLogger.isValidForSession(activeSession)) {
            if (this.appEventsLogger != null) {
                AppEventsLogger.onContextStop();
            }
            this.appEventsLogger = AppEventsLogger.newLogger(this.activity, activeSession);
        }
        return this.appEventsLogger;
    }

    class ActiveSessionBroadcastReceiver extends BroadcastReceiver {
        private ActiveSessionBroadcastReceiver() {
        }

        /* synthetic */ ActiveSessionBroadcastReceiver(UiLifecycleHelper uiLifecycleHelper, ActiveSessionBroadcastReceiver activeSessionBroadcastReceiver) {
            this();
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Session activeSession;
            if (Session.ACTION_ACTIVE_SESSION_SET.equals(intent.getAction())) {
                Session activeSession2 = Session.getActiveSession();
                if (activeSession2 != null && UiLifecycleHelper.this.callback != null) {
                    activeSession2.addCallback(UiLifecycleHelper.this.callback);
                    return;
                }
                return;
            }
            if (Session.ACTION_ACTIVE_SESSION_UNSET.equals(intent.getAction()) && (activeSession = Session.getActiveSession()) != null && UiLifecycleHelper.this.callback != null) {
                activeSession.removeCallback(UiLifecycleHelper.this.callback);
            }
        }
    }

    private boolean handleFacebookDialogActivityResult(int i, int i2, Intent intent, FacebookDialog.Callback callback) {
        UUID uuidFromString;
        if (this.pendingFacebookDialogCall == null || this.pendingFacebookDialogCall.getRequestCode() != i) {
            return false;
        }
        if (intent == null) {
            cancelPendingAppCall(callback);
            return true;
        }
        String stringExtra = intent.getStringExtra(NativeProtocol.EXTRA_PROTOCOL_CALL_ID);
        if (stringExtra != null) {
            try {
                uuidFromString = UUID.fromString(stringExtra);
            } catch (IllegalArgumentException e) {
                uuidFromString = null;
            }
        } else {
            uuidFromString = null;
        }
        if (uuidFromString != null && this.pendingFacebookDialogCall.getCallId().equals(uuidFromString)) {
            FacebookDialog.handleActivityResult(this.activity, this.pendingFacebookDialogCall, i, intent, callback);
        } else {
            cancelPendingAppCall(callback);
        }
        this.pendingFacebookDialogCall = null;
        return true;
    }

    private void cancelPendingAppCall(FacebookDialog.Callback callback) {
        if (callback != null) {
            Intent requestIntent = this.pendingFacebookDialogCall.getRequestIntent();
            Intent intent = new Intent();
            intent.putExtra(NativeProtocol.EXTRA_PROTOCOL_CALL_ID, requestIntent.getStringExtra(NativeProtocol.EXTRA_PROTOCOL_CALL_ID));
            intent.putExtra(NativeProtocol.EXTRA_PROTOCOL_ACTION, requestIntent.getStringExtra(NativeProtocol.EXTRA_PROTOCOL_ACTION));
            intent.putExtra(NativeProtocol.EXTRA_PROTOCOL_VERSION, requestIntent.getIntExtra(NativeProtocol.EXTRA_PROTOCOL_VERSION, 0));
            intent.putExtra(NativeProtocol.STATUS_ERROR_TYPE, NativeProtocol.ERROR_UNKNOWN_ERROR);
            FacebookDialog.handleActivityResult(this.activity, this.pendingFacebookDialogCall, this.pendingFacebookDialogCall.getRequestCode(), intent, callback);
        }
        this.pendingFacebookDialogCall = null;
    }
}
