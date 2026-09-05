package com.amazon.aws.tvmclient;

import com.topfreegames.bikerace.ap;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class AmazonConfig {
    private static final String AWS_CREDENTIALS_PROPERTIES_DEBUG = "AwsCredentials_debug.properties";
    private static final String AWS_CREDENTIALS_PROPERTIES_PROD = "AwsCredentials.properties";

    static boolean isDebug() {
        return ap.d();
    }

    static String getAwsCredentialsPropertiesResource() {
        return isDebug() ? AWS_CREDENTIALS_PROPERTIES_DEBUG : AWS_CREDENTIALS_PROPERTIES_PROD;
    }
}
