package com.amazon.inapp.purchasing;

import java.util.Collection;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class Validator {
    Validator() {
    }

    static void validateNotEmpty(Collection<? extends Object> collection, String str) {
        if (collection.isEmpty()) {
            throw new IllegalArgumentException(str + " must not be empty");
        }
    }

    static void validateNotNull(Object obj, String str) {
        if (obj == null) {
            throw new IllegalArgumentException(str + " must not be null");
        }
    }
}
