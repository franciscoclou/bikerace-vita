package com.applovin.adview;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class AppLovinConfirmationActivity extends Activity {
    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        Intent intent = getIntent();
        builder.setTitle(intent.getStringExtra("dialog_title"));
        builder.setMessage(intent.getStringExtra("dialog_body"));
        builder.setPositiveButton(intent.getStringExtra("dialog_button_text"), new d(this));
        builder.show();
    }
}
