package com.amazon.aws.tvmclient;

import android.content.SharedPreferences;
import com.amazonaws.a.c;
import com.amazonaws.g;
import com.amazonaws.services.dynamodb.a;
import com.amazonaws.services.sqs.b;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class AmazonClientManager {
    private final g clientConfig = new g().b(5);
    private c credentialsProvider;
    private a ddb;
    private b sqs;

    public AmazonClientManager(SharedPreferences sharedPreferences) {
        this.sqs = null;
        this.ddb = null;
        this.credentialsProvider = null;
        this.credentialsProvider = new AnonymousTVMCredentialsProvider(sharedPreferences);
        this.ddb = new com.topfreegames.d.a.b(this.credentialsProvider, this.clientConfig);
        this.sqs = new com.topfreegames.d.b.b(this.credentialsProvider);
    }

    public a ddb() {
        return this.ddb;
    }

    public b sqs() {
        return this.sqs;
    }

    public void clearToken() {
        ((AnonymousTVMCredentialsProvider) this.credentialsProvider).wipe();
    }
}
