package com.amazon.inapp.purchasing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class ItemDataResponse {
    private static final String TO_STRING_FORMAT = "(%s, requestId: \"%s\", itemDataRequestStatus: \"%s\", unavailableSkus: %s, itemData: %s)";
    private final Map<String, Item> _itemData;
    private final ItemDataRequestStatus _itemDataRequestStatus;
    private final String _requestId;
    private final Set<String> _unavailableSkus;

    public enum ItemDataRequestStatus {
        SUCCESSFUL,
        FAILED,
        SUCCESSFUL_WITH_UNAVAILABLE_SKUS
    }

    ItemDataResponse(String str, Set<String> set, ItemDataRequestStatus itemDataRequestStatus, Map<String, Item> map) {
        Validator.validateNotNull(str, "requestId");
        Validator.validateNotNull(itemDataRequestStatus, "itemDataRequestStatus");
        if (ItemDataRequestStatus.SUCCESSFUL_WITH_UNAVAILABLE_SKUS == itemDataRequestStatus) {
            Validator.validateNotNull(set, "unavailableSkus");
            Validator.validateNotEmpty(set, "unavailableSkus");
        }
        if (ItemDataRequestStatus.SUCCESSFUL == itemDataRequestStatus) {
            Validator.validateNotNull(map, "itemData");
            Validator.validateNotEmpty(map.entrySet(), "itemData");
        }
        this._requestId = str;
        this._itemDataRequestStatus = itemDataRequestStatus;
        this._unavailableSkus = set == null ? new HashSet<>() : set;
        this._itemData = map == null ? new HashMap<>() : map;
    }

    public Map<String, Item> getItemData() {
        return this._itemData;
    }

    public ItemDataRequestStatus getItemDataRequestStatus() {
        return this._itemDataRequestStatus;
    }

    public String getRequestId() {
        return this._requestId;
    }

    public Set<String> getUnavailableSkus() {
        return this._unavailableSkus;
    }

    public String toString() {
        return String.format(TO_STRING_FORMAT, super.toString(), this._requestId, this._itemDataRequestStatus, this._unavailableSkus, this._itemData);
    }
}
