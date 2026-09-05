package com.amazon.inapp.purchasing;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import com.amazon.android.framework.context.ContextManager;
import com.amazon.android.framework.prompt.PromptContent;
import com.amazon.android.framework.prompt.SimplePrompt;
import com.amazon.android.framework.resource.Resource;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class FailurePrompt extends SimplePrompt {
    private static final String LINK = "http://www.amazon.com/gp/mas/get-appstore/android/ref=mas_mx_mba_iap_dl";
    private static final String PROMPT_TITLE1 = "Amazon Appstore required";
    private static final String PROMPT_TITLE2 = "Amazon Appstore Update Required";
    private static final long SHUTDOWN_EXP_TIME = 31536000;
    private static final String TAG = "FailurePrompt";

    @Resource
    private ContextManager contextMgr;
    private PromptContent pContent;

    FailurePrompt(PromptContent promptContent) {
        super(promptContent);
        this.pContent = promptContent;
    }

    protected void doAction() {
        if (Logger.isTraceOn()) {
            Logger.trace(TAG, "doAction");
        }
        if (PROMPT_TITLE1.equalsIgnoreCase(this.pContent.getTitle()) || PROMPT_TITLE2.equalsIgnoreCase(this.pContent.getTitle())) {
            try {
                Activity visible = this.contextMgr.getVisible();
                if (visible == null) {
                    visible = this.contextMgr.getRoot();
                }
                visible.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(LINK)));
            } catch (Exception e) {
                if (Logger.isTraceOn()) {
                    Logger.trace(TAG, "Exception in PurchaseItemCommandTask.OnSuccess: " + e);
                }
            }
        }
    }

    protected long getExpirationDurationInSeconds() {
        return SHUTDOWN_EXP_TIME;
    }

    public String toString() {
        return TAG;
    }
}
