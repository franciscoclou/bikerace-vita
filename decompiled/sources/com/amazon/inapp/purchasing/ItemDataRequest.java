package com.amazon.inapp.purchasing;

import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class ItemDataRequest extends Request {
    private final Set<String> _skus;

    ItemDataRequest(Set<String> set) {
        Validator.validateNotNull(set, "skus");
        Validator.validateNotEmpty(set, "skus");
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            if (it.next().trim().length() == 0) {
                throw new IllegalArgumentException("Empty SKU values are not allowed");
            }
        }
        if (set.size() > 100) {
            throw new IllegalArgumentException(set.size() + " SKUs were provided, but no more than 100 SKUs are allowed");
        }
        this._skus = set;
    }

    @Override // com.amazon.inapp.purchasing.Request
    Runnable getRunnable() {
        return new Runnable() { // from class: com.amazon.inapp.purchasing.ItemDataRequest.1
            @Override // java.lang.Runnable
            public void run() {
                ImplementationFactory.getRequestHandler().sendItemDataRequest(ItemDataRequest.this._skus, ItemDataRequest.this.getRequestId());
            }
        };
    }
}
