package com.amazon.aws.tvmclient;

import android.content.SharedPreferences;
import android.util.Log;
import com.amazonaws.a.b;
import com.amazonaws.a.c;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class AnonymousTVMCredentialsProvider implements c {
    private static final String LOG_TAG = "AnonymousTVMCredentialsProvider";
    private b credentials;
    private SharedPreferences sharedPreferences;

    public AnonymousTVMCredentialsProvider(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public synchronized boolean hasCredentials() {
        return PropertyLoader.getInstance().hasCredentials();
    }

    public synchronized Response validateCredentials() {
        Response responseAnonymousRegister;
        responseAnonymousRegister = Response.SUCCESSFUL;
        if (areCredentialsExpired()) {
            Log.i(LOG_TAG, "Credentials were expired.");
            clearCredentials();
            AmazonTVMClient amazonTVMClient = new AmazonTVMClient(this.sharedPreferences, PropertyLoader.getInstance().getTokenVendingMachineURL(), PropertyLoader.getInstance().useSSL());
            responseAnonymousRegister = amazonTVMClient.anonymousRegister();
            if (responseAnonymousRegister.requestWasSuccessful()) {
                Log.i(LOG_TAG, "Retreiving credentials from TVM.");
                responseAnonymousRegister = amazonTVMClient.getToken();
            } else {
                Log.e(LOG_TAG, "Failed to register device on TVM.");
            }
        }
        if (responseAnonymousRegister.requestWasSuccessful()) {
            Log.i(LOG_TAG, "Retreiving credentials from SharedPreferences.");
            this.credentials = AmazonSharedPreferencesWrapper.getCredentialsFromSharedPreferences(this.sharedPreferences);
        } else {
            Log.e(LOG_TAG, "Failed to get token from TVM.");
        }
        return responseAnonymousRegister;
    }

    public synchronized boolean areCredentialsExpired() {
        return AmazonSharedPreferencesWrapper.areCredentialsExpired(this.sharedPreferences);
    }

    public void clearCredentials() {
        this.credentials = null;
    }

    @Override // com.amazonaws.a.c
    public b getCredentials() {
        validateCredentials();
        return this.credentials;
    }

    public synchronized void wipe() {
        AmazonSharedPreferencesWrapper.wipe(this.sharedPreferences);
    }

    public synchronized void refresh() {
        validateCredentials();
    }
}
