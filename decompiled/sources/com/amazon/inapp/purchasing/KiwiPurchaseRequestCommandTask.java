package com.amazon.inapp.purchasing;

import android.app.Activity;
import android.content.Intent;
import com.amazon.android.framework.context.ContextManager;
import com.amazon.android.framework.resource.Resource;
import com.amazon.android.framework.task.Task;
import com.amazon.android.framework.task.TaskManager;
import com.amazon.android.framework.task.pipeline.TaskPipelineId;
import com.amazon.venezia.command.SuccessResult;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class KiwiPurchaseRequestCommandTask extends KiwiBaseCommandTask {
    private static final String COMMAND_NAME = "purchase_item";
    private static final String COMMAND_VERSION = "1.0";
    private static final String TAG = "KiwiPurchaseRequestCommandTask";
    private final String _sku;

    @Resource
    private ContextManager contextMgr;

    @Resource
    private TaskManager taskManager;

    KiwiPurchaseRequestCommandTask(String str, String str2) {
        super(COMMAND_NAME, "1.0", str2);
        this._sku = str;
        addCommandData("sku", this._sku);
    }

    protected void onSuccess(SuccessResult successResult) {
        if (Logger.isTraceOn()) {
            Logger.trace(TAG, "onSuccess");
        }
        Map data = successResult.getData();
        if (Logger.isTraceOn()) {
            Logger.trace(TAG, "data: " + data);
        }
        if (!data.containsKey("purchaseItemIntent")) {
            if (Logger.isTraceOn()) {
                Logger.trace(TAG, "did not find intent");
            }
        } else {
            if (Logger.isTraceOn()) {
                Logger.trace(TAG, "found intent");
            }
            final Intent intent = (Intent) data.remove("purchaseItemIntent");
            this.taskManager.enqueueAtFront(TaskPipelineId.FOREGROUND, new Task() { // from class: com.amazon.inapp.purchasing.KiwiPurchaseRequestCommandTask.1
                public void execute() {
                    try {
                        Activity visible = KiwiPurchaseRequestCommandTask.this.contextMgr.getVisible();
                        if (visible == null) {
                            visible = KiwiPurchaseRequestCommandTask.this.contextMgr.getRoot();
                        }
                        if (Logger.isTraceOn()) {
                            Logger.trace(KiwiPurchaseRequestCommandTask.TAG, "About to fire intent with activity " + visible);
                        }
                        visible.startActivity(intent);
                    } catch (Exception e) {
                        if (Logger.isTraceOn()) {
                            Logger.trace(KiwiPurchaseRequestCommandTask.TAG, "Exception when attempting to fire intent: " + e);
                        }
                    }
                }
            });
        }
    }

    @Override // com.amazon.inapp.purchasing.KiwiBaseCommandTask
    protected void sendFailedResponse() {
        postRunnableToMainLooper(new Runnable() { // from class: com.amazon.inapp.purchasing.KiwiPurchaseRequestCommandTask.2
            @Override // java.lang.Runnable
            public void run() {
                PurchasingObserver purchasingObserver = PurchasingManager.getPurchasingObserver();
                PurchaseResponse purchaseResponse = new PurchaseResponse(KiwiPurchaseRequestCommandTask.this.getRequestId(), null, null, PurchaseResponse.PurchaseRequestStatus.FAILED);
                if (purchasingObserver != null) {
                    if (Logger.isTraceOn()) {
                        Logger.trace(KiwiPurchaseRequestCommandTask.TAG, "Invoking onPurchaseResponse with " + purchaseResponse);
                    }
                    purchasingObserver.onPurchaseResponse(purchaseResponse);
                }
            }
        });
    }
}
