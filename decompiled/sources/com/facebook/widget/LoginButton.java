package com.facebook.widget;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.facebook.AppEventsLogger;
import com.facebook.FacebookException;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionDefaultAudience;
import com.facebook.SessionLoginBehavior;
import com.facebook.SessionState;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.SessionAuthorizationType;
import com.facebook.internal.SessionTracker;
import com.facebook.internal.Utility;
import com.facebook.model.GraphUser;
import com.topfreegames.b.b;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class LoginButton extends Button {
    private static final String TAG = LoginButton.class.getName();
    private String applicationId;
    private boolean confirmLogout;
    private boolean fetchUserInfo;
    private String loginLogoutEventName;
    private String loginText;
    private String logoutText;
    private Fragment parentFragment;
    private LoginButtonProperties properties;
    private SessionTracker sessionTracker;
    private GraphUser user;
    private UserInfoChangedCallback userInfoChangedCallback;
    private Session userInfoSession;

    public interface OnErrorListener {
        void onError(FacebookException facebookException);
    }

    public interface UserInfoChangedCallback {
        void onUserInfoFetched(GraphUser graphUser);
    }

    class LoginButtonProperties {
        private OnErrorListener onErrorListener;
        private Session.StatusCallback sessionStatusCallback;
        private SessionDefaultAudience defaultAudience = SessionDefaultAudience.FRIENDS;
        private List<String> permissions = Collections.emptyList();
        private SessionAuthorizationType authorizationType = null;
        private SessionLoginBehavior loginBehavior = SessionLoginBehavior.SSO_WITH_FALLBACK;

        LoginButtonProperties() {
        }

        public void setOnErrorListener(OnErrorListener onErrorListener) {
            this.onErrorListener = onErrorListener;
        }

        public OnErrorListener getOnErrorListener() {
            return this.onErrorListener;
        }

        public void setDefaultAudience(SessionDefaultAudience sessionDefaultAudience) {
            this.defaultAudience = sessionDefaultAudience;
        }

        public SessionDefaultAudience getDefaultAudience() {
            return this.defaultAudience;
        }

        public void setReadPermissions(List<String> list, Session session) {
            if (SessionAuthorizationType.PUBLISH.equals(this.authorizationType)) {
                throw new UnsupportedOperationException("Cannot call setReadPermissions after setPublishPermissions has been called.");
            }
            if (validatePermissions(list, SessionAuthorizationType.READ, session)) {
                this.permissions = list;
                this.authorizationType = SessionAuthorizationType.READ;
            }
        }

        public void setPublishPermissions(List<String> list, Session session) {
            if (SessionAuthorizationType.READ.equals(this.authorizationType)) {
                throw new UnsupportedOperationException("Cannot call setPublishPermissions after setReadPermissions has been called.");
            }
            if (validatePermissions(list, SessionAuthorizationType.PUBLISH, session)) {
                this.permissions = list;
                this.authorizationType = SessionAuthorizationType.PUBLISH;
            }
        }

        private boolean validatePermissions(List<String> list, SessionAuthorizationType sessionAuthorizationType, Session session) {
            if (SessionAuthorizationType.PUBLISH.equals(sessionAuthorizationType) && Utility.isNullOrEmpty(list)) {
                throw new IllegalArgumentException("Permissions for publish actions cannot be null or empty.");
            }
            if (session == null || !session.isOpened() || Utility.isSubset(list, session.getPermissions())) {
                return true;
            }
            Log.e(LoginButton.TAG, "Cannot set additional permissions when session is already open.");
            return false;
        }

        List<String> getPermissions() {
            return this.permissions;
        }

        public void clearPermissions() {
            this.permissions = null;
            this.authorizationType = null;
        }

        public void setLoginBehavior(SessionLoginBehavior sessionLoginBehavior) {
            this.loginBehavior = sessionLoginBehavior;
        }

        public SessionLoginBehavior getLoginBehavior() {
            return this.loginBehavior;
        }

        public void setSessionStatusCallback(Session.StatusCallback statusCallback) {
            this.sessionStatusCallback = statusCallback;
        }

        public Session.StatusCallback getSessionStatusCallback() {
            return this.sessionStatusCallback;
        }
    }

    public LoginButton(Context context) {
        super(context);
        this.applicationId = null;
        this.user = null;
        this.userInfoSession = null;
        this.properties = new LoginButtonProperties();
        this.loginLogoutEventName = AnalyticsEvents.EVENT_LOGIN_VIEW_USAGE;
        initializeActiveSessionWithCachedToken(context);
        finishInit();
    }

    public LoginButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.applicationId = null;
        this.user = null;
        this.userInfoSession = null;
        this.properties = new LoginButtonProperties();
        this.loginLogoutEventName = AnalyticsEvents.EVENT_LOGIN_VIEW_USAGE;
        if (attributeSet.getStyleAttribute() == 0) {
            setGravity(17);
            setTextColor(getResources().getColor(2131427347));
            setTextSize(0, getResources().getDimension(2131362523));
            setTypeface(Typeface.DEFAULT_BOLD);
            if (isInEditMode()) {
                setBackgroundColor(getResources().getColor(2131427343));
                this.loginText = "Log in with Facebook";
            } else {
                setBackgroundResource(2130837662);
                setCompoundDrawablesWithIntrinsicBounds(2130837674, 0, 0, 0);
                setCompoundDrawablePadding(getResources().getDimensionPixelSize(2131362522));
                setPadding(getResources().getDimensionPixelSize(2131362518), getResources().getDimensionPixelSize(2131362520), getResources().getDimensionPixelSize(2131362519), getResources().getDimensionPixelSize(2131362521));
            }
        }
        parseAttributes(attributeSet);
        if (!isInEditMode()) {
            initializeActiveSessionWithCachedToken(context);
        }
    }

    public LoginButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.applicationId = null;
        this.user = null;
        this.userInfoSession = null;
        this.properties = new LoginButtonProperties();
        this.loginLogoutEventName = AnalyticsEvents.EVENT_LOGIN_VIEW_USAGE;
        parseAttributes(attributeSet);
        initializeActiveSessionWithCachedToken(context);
    }

    public void setOnErrorListener(OnErrorListener onErrorListener) {
        this.properties.setOnErrorListener(onErrorListener);
    }

    public OnErrorListener getOnErrorListener() {
        return this.properties.getOnErrorListener();
    }

    public void setDefaultAudience(SessionDefaultAudience sessionDefaultAudience) {
        this.properties.setDefaultAudience(sessionDefaultAudience);
    }

    public SessionDefaultAudience getDefaultAudience() {
        return this.properties.getDefaultAudience();
    }

    public void setReadPermissions(List<String> list) {
        this.properties.setReadPermissions(list, this.sessionTracker.getSession());
    }

    public void setReadPermissions(String... strArr) {
        this.properties.setReadPermissions(Arrays.asList(strArr), this.sessionTracker.getSession());
    }

    public void setPublishPermissions(List<String> list) {
        this.properties.setPublishPermissions(list, this.sessionTracker.getSession());
    }

    public void setPublishPermissions(String... strArr) {
        this.properties.setPublishPermissions(Arrays.asList(strArr), this.sessionTracker.getSession());
    }

    public void clearPermissions() {
        this.properties.clearPermissions();
    }

    public void setLoginBehavior(SessionLoginBehavior sessionLoginBehavior) {
        this.properties.setLoginBehavior(sessionLoginBehavior);
    }

    public SessionLoginBehavior getLoginBehavior() {
        return this.properties.getLoginBehavior();
    }

    public void setApplicationId(String str) {
        this.applicationId = str;
    }

    public UserInfoChangedCallback getUserInfoChangedCallback() {
        return this.userInfoChangedCallback;
    }

    public void setUserInfoChangedCallback(UserInfoChangedCallback userInfoChangedCallback) {
        this.userInfoChangedCallback = userInfoChangedCallback;
    }

    public void setSessionStatusCallback(Session.StatusCallback statusCallback) {
        this.properties.setSessionStatusCallback(statusCallback);
    }

    public Session.StatusCallback getSessionStatusCallback() {
        return this.properties.getSessionStatusCallback();
    }

    public boolean onActivityResult(int i, int i2, Intent intent) {
        Session session = this.sessionTracker.getSession();
        if (session != null) {
            return session.onActivityResult((Activity) getContext(), i, i2, intent);
        }
        return false;
    }

    public void setSession(Session session) {
        this.sessionTracker.setSession(session);
        fetchUserInfo();
        setButtonText();
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        finishInit();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void finishInit() {
        Object[] objArr = 0;
        setOnClickListener(new LoginClickListener(this, null));
        setButtonText();
        if (!isInEditMode()) {
            this.sessionTracker = new SessionTracker(getContext(), new LoginButtonCallback(this, objArr == true ? 1 : 0), null, false);
            fetchUserInfo();
        }
    }

    public void setFragment(Fragment fragment) {
        this.parentFragment = fragment;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.sessionTracker != null && !this.sessionTracker.isTracking()) {
            this.sessionTracker.startTracking();
            fetchUserInfo();
            setButtonText();
        }
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.sessionTracker != null) {
            this.sessionTracker.stopTracking();
        }
    }

    List<String> getPermissions() {
        return this.properties.getPermissions();
    }

    void setProperties(LoginButtonProperties loginButtonProperties) {
        this.properties = loginButtonProperties;
    }

    void setLoginLogoutEventName(String str) {
        this.loginLogoutEventName = str;
    }

    private void parseAttributes(AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, b.com_facebook_login_view);
        this.confirmLogout = typedArrayObtainStyledAttributes.getBoolean(0, true);
        this.fetchUserInfo = typedArrayObtainStyledAttributes.getBoolean(1, true);
        this.loginText = typedArrayObtainStyledAttributes.getString(2);
        this.logoutText = typedArrayObtainStyledAttributes.getString(3);
        typedArrayObtainStyledAttributes.recycle();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setButtonText() {
        if (this.sessionTracker != null && this.sessionTracker.getOpenSession() != null) {
            setText(this.logoutText != null ? this.logoutText : getResources().getString(2131100121));
        } else {
            setText(this.loginText != null ? this.loginText : getResources().getString(2131100122));
        }
    }

    private boolean initializeActiveSessionWithCachedToken(Context context) {
        if (context == null) {
            return false;
        }
        Session activeSession = Session.getActiveSession();
        if (activeSession != null) {
            return activeSession.isOpened();
        }
        return (Utility.getMetadataApplicationId(context) == null || Session.openActiveSessionFromCache(context) == null) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fetchUserInfo() {
        if (this.fetchUserInfo) {
            final Session openSession = this.sessionTracker.getOpenSession();
            if (openSession != null) {
                if (openSession != this.userInfoSession) {
                    Request.executeBatchAsync(Request.newMeRequest(openSession, new Request.GraphUserCallback() { // from class: com.facebook.widget.LoginButton.1
                        @Override // com.facebook.Request.GraphUserCallback
                        public void onCompleted(GraphUser graphUser, Response response) {
                            if (openSession == LoginButton.this.sessionTracker.getOpenSession()) {
                                LoginButton.this.user = graphUser;
                                if (LoginButton.this.userInfoChangedCallback != null) {
                                    LoginButton.this.userInfoChangedCallback.onUserInfoFetched(LoginButton.this.user);
                                }
                            }
                            if (response.getError() != null) {
                                LoginButton.this.handleError(response.getError().getException());
                            }
                        }
                    }));
                    this.userInfoSession = openSession;
                    return;
                }
                return;
            }
            this.user = null;
            if (this.userInfoChangedCallback != null) {
                this.userInfoChangedCallback.onUserInfoFetched(this.user);
            }
        }
    }

    class LoginClickListener implements View.OnClickListener {
        private LoginClickListener() {
        }

        /* synthetic */ LoginClickListener(LoginButton loginButton, LoginClickListener loginClickListener) {
            this();
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Session.OpenRequest openRequest;
            String string;
            Context context = LoginButton.this.getContext();
            final Session openSession = LoginButton.this.sessionTracker.getOpenSession();
            if (openSession == null) {
                Session session = LoginButton.this.sessionTracker.getSession();
                if (session == null || session.getState().isClosed()) {
                    LoginButton.this.sessionTracker.setSession(null);
                    session = new Session.Builder(context).setApplicationId(LoginButton.this.applicationId).build();
                    Session.setActiveSession(session);
                }
                if (!session.isOpened()) {
                    if (LoginButton.this.parentFragment != null) {
                        openRequest = new Session.OpenRequest(LoginButton.this.parentFragment);
                    } else {
                        openRequest = context instanceof Activity ? new Session.OpenRequest((Activity) context) : null;
                    }
                    if (openRequest != null) {
                        openRequest.setDefaultAudience(LoginButton.this.properties.defaultAudience);
                        openRequest.setPermissions(LoginButton.this.properties.permissions);
                        openRequest.setLoginBehavior(LoginButton.this.properties.loginBehavior);
                        if (SessionAuthorizationType.PUBLISH.equals(LoginButton.this.properties.authorizationType)) {
                            session.openForPublish(openRequest);
                        } else {
                            session.openForRead(openRequest);
                        }
                    }
                }
            } else if (LoginButton.this.confirmLogout) {
                String string2 = LoginButton.this.getResources().getString(2131100125);
                String string3 = LoginButton.this.getResources().getString(2131100126);
                if (LoginButton.this.user != null && LoginButton.this.user.getName() != null) {
                    string = String.format(LoginButton.this.getResources().getString(2131100123), LoginButton.this.user.getName());
                } else {
                    string = LoginButton.this.getResources().getString(2131100124);
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage(string).setCancelable(true).setPositiveButton(string2, new DialogInterface.OnClickListener() { // from class: com.facebook.widget.LoginButton.LoginClickListener.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        openSession.closeAndClearTokenInformation();
                    }
                }).setNegativeButton(string3, (DialogInterface.OnClickListener) null);
                builder.create().show();
            } else {
                openSession.closeAndClearTokenInformation();
            }
            AppEventsLogger appEventsLoggerNewLogger = AppEventsLogger.newLogger(LoginButton.this.getContext());
            Bundle bundle = new Bundle();
            bundle.putInt("logging_in", openSession != null ? 0 : 1);
            appEventsLoggerNewLogger.logSdkEvent(LoginButton.this.loginLogoutEventName, null, bundle);
        }
    }

    class LoginButtonCallback implements Session.StatusCallback {
        private LoginButtonCallback() {
        }

        /* synthetic */ LoginButtonCallback(LoginButton loginButton, LoginButtonCallback loginButtonCallback) {
            this();
        }

        @Override // com.facebook.Session.StatusCallback
        public void call(Session session, SessionState sessionState, Exception exc) {
            LoginButton.this.fetchUserInfo();
            LoginButton.this.setButtonText();
            if (LoginButton.this.properties.sessionStatusCallback != null) {
                LoginButton.this.properties.sessionStatusCallback.call(session, sessionState, exc);
            } else if (exc != null) {
                LoginButton.this.handleError(exc);
            }
        }
    }

    void handleError(Exception exc) {
        if (this.properties.onErrorListener != null) {
            if (exc instanceof FacebookException) {
                this.properties.onErrorListener.onError((FacebookException) exc);
            } else {
                this.properties.onErrorListener.onError(new FacebookException(exc));
            }
        }
    }
}
