package com.amazon.inapp.purchasing;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class Receipt {
    private static final String TO_STRING_FORMAT = "(%s, sku: \"%s\", itemType: \"%s\", subscriptionPeriod: %s, purchaseToken: \"%s\")";
    private final boolean _isContentAvailable;
    private final Item.ItemType _itemType;
    private final String _purchaseToken;
    private final String _sku;
    private final SubscriptionPeriod _subscriptionPeriod;

    Receipt(String str, Item.ItemType itemType, boolean z, SubscriptionPeriod subscriptionPeriod, String str2) {
        Validator.validateNotNull(str, "sku");
        Validator.validateNotNull(itemType, "itemType");
        Validator.validateNotNull(str2, "purchaseToken");
        if (Item.ItemType.SUBSCRIPTION == itemType) {
            Validator.validateNotNull(subscriptionPeriod, "subscriptionPeriod");
        }
        this._sku = str;
        this._itemType = itemType;
        this._purchaseToken = str2;
        this._isContentAvailable = z;
        this._subscriptionPeriod = subscriptionPeriod;
    }

    public Item.ItemType getItemType() {
        return this._itemType;
    }

    public String getPurchaseToken() {
        return this._purchaseToken;
    }

    public String getSku() {
        return this._sku;
    }

    public SubscriptionPeriod getSubscriptionPeriod() {
        return this._subscriptionPeriod;
    }

    boolean isContentAvailable() {
        return this._isContentAvailable;
    }

    public String toString() {
        return String.format(TO_STRING_FORMAT, super.toString(), this._sku, this._itemType, this._subscriptionPeriod, this._purchaseToken);
    }
}
