package com.amazon.aws.tvmclient;

import android.content.SharedPreferences;
import com.amazonaws.a.b;
import com.amazonaws.a.i;
import com.topfreegames.c.a;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class AmazonSharedPreferencesWrapper {
    private static final String AWS_ACCESS_KEY = "AWS_ACCESS_KEY";
    private static final String AWS_DEVICE_KEY = "AWS_DEVICE_KEY";
    private static final String AWS_DEVICE_UID = "AWS_DEVICE_UID";
    private static final String AWS_EXPIRATION_DATE = "AWS_EXPIRATION_DATE";
    private static final String AWS_SECRET_KEY = "AWS_SECRET_KEY";
    private static final String AWS_SECURITY_TOKEN = "AWS_SECURITY_TOKEN";

    public static void wipe(SharedPreferences sharedPreferences) {
        storeValueInSharedPreferences(sharedPreferences, AWS_DEVICE_UID, null);
        storeValueInSharedPreferences(sharedPreferences, AWS_DEVICE_KEY, null);
        storeValueInSharedPreferences(sharedPreferences, AWS_ACCESS_KEY, null);
        storeValueInSharedPreferences(sharedPreferences, AWS_SECRET_KEY, null);
        storeValueInSharedPreferences(sharedPreferences, AWS_SECURITY_TOKEN, null);
        storeValueInSharedPreferences(sharedPreferences, AWS_EXPIRATION_DATE, null);
    }

    public static void registerDeviceId(SharedPreferences sharedPreferences, String str, String str2) {
        storeValueInSharedPreferences(sharedPreferences, AWS_DEVICE_UID, str);
        storeValueInSharedPreferences(sharedPreferences, AWS_DEVICE_KEY, str2);
    }

    public static String getUidForDevice(SharedPreferences sharedPreferences) {
        return getValueFromSharedPreferences(sharedPreferences, AWS_DEVICE_UID);
    }

    public static String getKeyForDevice(SharedPreferences sharedPreferences) {
        return getValueFromSharedPreferences(sharedPreferences, AWS_DEVICE_KEY);
    }

    public static b getCredentialsFromSharedPreferences(SharedPreferences sharedPreferences) {
        return new i(getValueFromSharedPreferences(sharedPreferences, AWS_ACCESS_KEY), getValueFromSharedPreferences(sharedPreferences, AWS_SECRET_KEY), getValueFromSharedPreferences(sharedPreferences, AWS_SECURITY_TOKEN));
    }

    public static boolean areCredentialsExpired(SharedPreferences sharedPreferences) {
        String valueFromSharedPreferences = getValueFromSharedPreferences(sharedPreferences, AWS_EXPIRATION_DATE);
        return valueFromSharedPreferences == null || Long.parseLong(valueFromSharedPreferences) < a.a().getTime() + 90000;
    }

    public static void storeCredentialsInSharedPreferences(SharedPreferences sharedPreferences, String str, String str2, String str3, String str4) {
        storeValueInSharedPreferences(sharedPreferences, AWS_ACCESS_KEY, str);
        storeValueInSharedPreferences(sharedPreferences, AWS_SECRET_KEY, str2);
        storeValueInSharedPreferences(sharedPreferences, AWS_SECURITY_TOKEN, str3);
        storeValueInSharedPreferences(sharedPreferences, AWS_EXPIRATION_DATE, str4);
    }

    protected static void storeValueInSharedPreferences(SharedPreferences sharedPreferences, String str, String str2) {
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putString(str, str2);
        editorEdit.commit();
    }

    protected static String getValueFromSharedPreferences(SharedPreferences sharedPreferences, String str) {
        return sharedPreferences.getString(str, null);
    }
}
