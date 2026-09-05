package com.facebook;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphObjectList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class TestSession extends Session {
    static final /* synthetic */ boolean $assertionsDisabled;
    private static final String LOG_TAG = "FacebookSDK.TestSession";
    private static Map<String, TestAccount> appTestAccounts = null;
    private static final long serialVersionUID = 1;
    private static String testApplicationId;
    private static String testApplicationSecret;
    private final Mode mode;
    private final List<String> requestedPermissions;
    private final String sessionUniqueUserTag;
    private String testAccountId;
    private boolean wasAskedToExtendAccessToken;

    interface FqlResponse extends GraphObject {
        GraphObjectList<FqlResult> getData();
    }

    interface FqlResult extends GraphObject {
        GraphObjectList<GraphObject> getFqlResultSet();
    }

    enum Mode {
        PRIVATE,
        SHARED;

        /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
        public static Mode[] valuesCustom() {
            Mode[] modeArrValuesCustom = values();
            int length = modeArrValuesCustom.length;
            Mode[] modeArr = new Mode[length];
            System.arraycopy(modeArrValuesCustom, 0, modeArr, 0, length);
            return modeArr;
        }
    }

    interface TestAccount extends GraphObject {
        String getAccessToken();

        String getId();

        String getName();

        void setName(String str);
    }

    interface UserAccount extends GraphObject {
        String getName();

        String getUid();

        void setName(String str);
    }

    static {
        $assertionsDisabled = !TestSession.class.desiredAssertionStatus();
    }

    TestSession(Activity activity, List<String> list, TokenCachingStrategy tokenCachingStrategy, String str, Mode mode) {
        super(activity, testApplicationId, tokenCachingStrategy);
        Validate.notNull(list, "permissions");
        Validate.notNullOrEmpty(testApplicationId, "testApplicationId");
        Validate.notNullOrEmpty(testApplicationSecret, "testApplicationSecret");
        this.sessionUniqueUserTag = str;
        this.mode = mode;
        this.requestedPermissions = list;
    }

    public static TestSession createSessionWithPrivateUser(Activity activity, List<String> list) {
        return createTestSession(activity, list, Mode.PRIVATE, null);
    }

    public static TestSession createSessionWithSharedUser(Activity activity, List<String> list) {
        return createSessionWithSharedUser(activity, list, null);
    }

    public static TestSession createSessionWithSharedUser(Activity activity, List<String> list, String str) {
        return createTestSession(activity, list, Mode.SHARED, str);
    }

    public static synchronized String getTestApplicationId() {
        return testApplicationId;
    }

    public static synchronized void setTestApplicationId(String str) {
        if (testApplicationId != null && !testApplicationId.equals(str)) {
            throw new FacebookException("Can't have more than one test application ID");
        }
        testApplicationId = str;
    }

    public static synchronized String getTestApplicationSecret() {
        return testApplicationSecret;
    }

    public static synchronized void setTestApplicationSecret(String str) {
        if (testApplicationSecret != null && !testApplicationSecret.equals(str)) {
            throw new FacebookException("Can't have more than one test application secret");
        }
        testApplicationSecret = str;
    }

    public final String getTestUserId() {
        return this.testAccountId;
    }

    private static synchronized TestSession createTestSession(Activity activity, List<String> list, Mode mode, String str) {
        if (Utility.isNullOrEmpty(testApplicationId) || Utility.isNullOrEmpty(testApplicationSecret)) {
            throw new FacebookException("Must provide app ID and secret");
        }
        return new TestSession(activity, Utility.isNullOrEmpty(list) ? Arrays.asList("email", "publish_actions") : list, new TestTokenCachingStrategy(null), str, mode);
    }

    private static synchronized void retrieveTestAccountsForAppIfNeeded() {
        if (appTestAccounts == null) {
            appTestAccounts = new HashMap();
            String str = String.format("SELECT id,access_token FROM test_account WHERE app_id = %s", testApplicationId);
            Bundle bundle = new Bundle();
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("test_accounts", str);
                jSONObject.put("users", "SELECT uid,name FROM user WHERE uid IN (SELECT id FROM #test_accounts)");
                bundle.putString("q", jSONObject.toString());
                bundle.putString("access_token", getAppAccessToken());
                Response responseExecuteAndWait = new Request(null, "fql", bundle, null).executeAndWait();
                if (responseExecuteAndWait.getError() != null) {
                    throw responseExecuteAndWait.getError().getException();
                }
                GraphObjectList<FqlResult> data = ((FqlResponse) responseExecuteAndWait.getGraphObjectAs(FqlResponse.class)).getData();
                if (data == null || data.size() != 2) {
                    throw new FacebookException("Unexpected number of results from FQL query");
                }
                populateTestAccounts(data.get(0).getFqlResultSet().castToListOf(TestAccount.class), data.get(1).getFqlResultSet().castToListOf(UserAccount.class));
            } catch (JSONException e) {
                throw new FacebookException(e);
            }
        }
    }

    private static synchronized void populateTestAccounts(Collection<TestAccount> collection, Collection<UserAccount> collection2) {
        Iterator<TestAccount> it = collection.iterator();
        while (it.hasNext()) {
            storeTestAccount(it.next());
        }
        for (UserAccount userAccount : collection2) {
            TestAccount testAccount = appTestAccounts.get(userAccount.getUid());
            if (testAccount != null) {
                testAccount.setName(userAccount.getName());
            }
        }
    }

    private static synchronized void storeTestAccount(TestAccount testAccount) {
        appTestAccounts.put(testAccount.getId(), testAccount);
    }

    private static synchronized TestAccount findTestAccountMatchingIdentifier(String str) {
        TestAccount next;
        retrieveTestAccountsForAppIfNeeded();
        Iterator<TestAccount> it = appTestAccounts.values().iterator();
        while (it.hasNext()) {
            next = it.next();
            if (next.getName().contains(str)) {
            }
        }
        next = null;
        return next;
    }

    @Override // com.facebook.Session
    public final String toString() {
        return "{TestSession testUserId:" + this.testAccountId + " " + super.toString() + "}";
    }

    @Override // com.facebook.Session
    void authorize(Session.AuthorizationRequest authorizationRequest) {
        if (this.mode == Mode.PRIVATE) {
            createTestAccountAndFinishAuth();
        } else {
            findOrCreateSharedTestAccount();
        }
    }

    @Override // com.facebook.Session
    void postStateChange(SessionState sessionState, SessionState sessionState2, Exception exc) {
        String str = this.testAccountId;
        super.postStateChange(sessionState, sessionState2, exc);
        if (sessionState2.isClosed() && str != null && this.mode == Mode.PRIVATE) {
            deleteTestAccount(str, getAppAccessToken());
        }
    }

    boolean getWasAskedToExtendAccessToken() {
        return this.wasAskedToExtendAccessToken;
    }

    void forceExtendAccessToken(boolean z) {
        AccessToken tokenInfo = getTokenInfo();
        setTokenInfo(new AccessToken(tokenInfo.getToken(), new Date(), tokenInfo.getPermissions(), AccessTokenSource.TEST_USER, new Date(0L)));
        setLastAttemptedTokenExtendDate(new Date(0L));
    }

    @Override // com.facebook.Session
    boolean shouldExtendAccessToken() {
        boolean zShouldExtendAccessToken = super.shouldExtendAccessToken();
        this.wasAskedToExtendAccessToken = false;
        return zShouldExtendAccessToken;
    }

    @Override // com.facebook.Session
    void extendAccessToken() {
        this.wasAskedToExtendAccessToken = true;
        super.extendAccessToken();
    }

    void fakeTokenRefreshAttempt() {
        setCurrentTokenRefreshRequest(new Session.TokenRefreshRequest());
    }

    static final String getAppAccessToken() {
        return String.valueOf(testApplicationId) + "|" + testApplicationSecret;
    }

    private void findOrCreateSharedTestAccount() {
        TestAccount testAccountFindTestAccountMatchingIdentifier = findTestAccountMatchingIdentifier(getSharedTestAccountIdentifier());
        if (testAccountFindTestAccountMatchingIdentifier != null) {
            finishAuthWithTestAccount(testAccountFindTestAccountMatchingIdentifier);
        } else {
            createTestAccountAndFinishAuth();
        }
    }

    private void finishAuthWithTestAccount(TestAccount testAccount) {
        this.testAccountId = testAccount.getId();
        finishAuthOrReauth(AccessToken.createFromString(testAccount.getAccessToken(), this.requestedPermissions, AccessTokenSource.TEST_USER), null);
    }

    private TestAccount createTestAccountAndFinishAuth() {
        Bundle bundle = new Bundle();
        bundle.putString("installed", "true");
        bundle.putString("permissions", getPermissionsString());
        bundle.putString("access_token", getAppAccessToken());
        if (this.mode == Mode.SHARED) {
            bundle.putString("name", String.format("Shared %s Testuser", getSharedTestAccountIdentifier()));
        }
        Response responseExecuteAndWait = new Request(null, String.format("%s/accounts/test-users", testApplicationId), bundle, HttpMethod.POST).executeAndWait();
        FacebookRequestError error = responseExecuteAndWait.getError();
        TestAccount testAccount = (TestAccount) responseExecuteAndWait.getGraphObjectAs(TestAccount.class);
        if (error != null) {
            finishAuthOrReauth(null, error.getException());
            return null;
        }
        if (!$assertionsDisabled && testAccount == null) {
            throw new AssertionError();
        }
        if (this.mode == Mode.SHARED) {
            testAccount.setName(bundle.getString("name"));
            storeTestAccount(testAccount);
        }
        finishAuthWithTestAccount(testAccount);
        return testAccount;
    }

    private void deleteTestAccount(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString("access_token", str2);
        Response responseExecuteAndWait = new Request(null, str, bundle, HttpMethod.DELETE).executeAndWait();
        FacebookRequestError error = responseExecuteAndWait.getError();
        GraphObject graphObject = responseExecuteAndWait.getGraphObject();
        if (error != null) {
            Log.w(LOG_TAG, String.format("Could not delete test account %s: %s", str, error.getException().toString()));
        } else if (graphObject.getProperty(Response.NON_JSON_RESPONSE_PROPERTY) == false) {
            Log.w(LOG_TAG, String.format("Could not delete test account %s: unknown reason", str));
        }
    }

    private String getPermissionsString() {
        return TextUtils.join(",", this.requestedPermissions);
    }

    private String getSharedTestAccountIdentifier() {
        return validNameStringFromInteger((this.sessionUniqueUserTag != null ? ((long) this.sessionUniqueUserTag.hashCode()) & 4294967295L : 0L) ^ (((long) getPermissionsString().hashCode()) & 4294967295L));
    }

    private String validNameStringFromInteger(long j) {
        String string = Long.toString(j);
        StringBuilder sb = new StringBuilder("Perm");
        char[] charArray = string.toCharArray();
        int length = charArray.length;
        int i = 0;
        char c = 0;
        while (i < length) {
            char c2 = charArray[i];
            if (c2 == c) {
                c2 = (char) (c2 + '\n');
            }
            sb.append((char) ((c2 + 'a') - 48));
            i++;
            c = c2;
        }
        return sb.toString();
    }

    final class TestTokenCachingStrategy extends TokenCachingStrategy {
        private Bundle bundle;

        private TestTokenCachingStrategy() {
        }

        /* synthetic */ TestTokenCachingStrategy(TestTokenCachingStrategy testTokenCachingStrategy) {
            this();
        }

        @Override // com.facebook.TokenCachingStrategy
        public Bundle load() {
            return this.bundle;
        }

        @Override // com.facebook.TokenCachingStrategy
        public void save(Bundle bundle) {
            this.bundle = bundle;
        }

        @Override // com.facebook.TokenCachingStrategy
        public void clear() {
            this.bundle = null;
        }
    }
}
