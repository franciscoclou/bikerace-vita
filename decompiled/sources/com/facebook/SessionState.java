package com.facebook;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public enum SessionState {
    CREATED(Category.CREATED_CATEGORY),
    CREATED_TOKEN_LOADED(Category.CREATED_CATEGORY),
    OPENING(Category.CREATED_CATEGORY),
    OPENED(Category.OPENED_CATEGORY),
    OPENED_TOKEN_UPDATED(Category.OPENED_CATEGORY),
    CLOSED_LOGIN_FAILED(Category.CLOSED_CATEGORY),
    CLOSED(Category.CLOSED_CATEGORY);

    private final Category category;

    enum Category {
        CREATED_CATEGORY,
        OPENED_CATEGORY,
        CLOSED_CATEGORY;

        /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
        public static Category[] valuesCustom() {
            Category[] categoryArrValuesCustom = values();
            int length = categoryArrValuesCustom.length;
            Category[] categoryArr = new Category[length];
            System.arraycopy(categoryArrValuesCustom, 0, categoryArr, 0, length);
            return categoryArr;
        }
    }

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static SessionState[] valuesCustom() {
        SessionState[] sessionStateArrValuesCustom = values();
        int length = sessionStateArrValuesCustom.length;
        SessionState[] sessionStateArr = new SessionState[length];
        System.arraycopy(sessionStateArrValuesCustom, 0, sessionStateArr, 0, length);
        return sessionStateArr;
    }

    SessionState(Category category) {
        this.category = category;
    }

    public boolean isOpened() {
        return this.category == Category.OPENED_CATEGORY;
    }

    public boolean isClosed() {
        return this.category == Category.CLOSED_CATEGORY;
    }
}
