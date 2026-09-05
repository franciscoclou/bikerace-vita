package com.facebook.widget;

import android.app.Activity;
import android.content.res.TypedArray;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import com.facebook.AppEventsLogger;
import com.facebook.FacebookException;
import com.facebook.LoggingBehavior;
import com.facebook.Request;
import com.facebook.Session;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.Logger;
import com.facebook.internal.Utility;
import com.facebook.model.GraphPlace;
import com.topfreegames.b.b;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class PlacePickerFragment extends PickerFragment<GraphPlace> {
    private static final String CATEGORY = "category";
    public static final int DEFAULT_RADIUS_IN_METERS = 1000;
    public static final int DEFAULT_RESULTS_LIMIT = 100;
    private static final String ID = "id";
    private static final String LOCATION = "location";
    public static final String LOCATION_BUNDLE_KEY = "com.facebook.widget.PlacePickerFragment.Location";
    private static final String NAME = "name";
    public static final String RADIUS_IN_METERS_BUNDLE_KEY = "com.facebook.widget.PlacePickerFragment.RadiusInMeters";
    public static final String RESULTS_LIMIT_BUNDLE_KEY = "com.facebook.widget.PlacePickerFragment.ResultsLimit";
    public static final String SEARCH_TEXT_BUNDLE_KEY = "com.facebook.widget.PlacePickerFragment.SearchText";
    public static final String SHOW_SEARCH_BOX_BUNDLE_KEY = "com.facebook.widget.PlacePickerFragment.ShowSearchBox";
    private static final String TAG = "PlacePickerFragment";
    private static final String WERE_HERE_COUNT = "were_here_count";
    private static final int searchTextTimerDelayInMilliseconds = 2000;
    private boolean hasSearchTextChangedSinceLastQuery;
    private Location location;
    private int radiusInMeters;
    private int resultsLimit;
    private EditText searchBox;
    private String searchText;
    private Timer searchTextTimer;
    private boolean showSearchBox;

    public PlacePickerFragment() {
        this(null);
    }

    public PlacePickerFragment(Bundle bundle) {
        super(GraphPlace.class, 2130903053, bundle);
        this.radiusInMeters = DEFAULT_RADIUS_IN_METERS;
        this.resultsLimit = 100;
        this.showSearchBox = true;
        setPlacePickerSettingsFromBundle(bundle);
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getRadiusInMeters() {
        return this.radiusInMeters;
    }

    public void setRadiusInMeters(int i) {
        this.radiusInMeters = i;
    }

    public int getResultsLimit() {
        return this.resultsLimit;
    }

    public void setResultsLimit(int i) {
        this.resultsLimit = i;
    }

    public String getSearchText() {
        return this.searchText;
    }

    public void setSearchText(String str) {
        if (TextUtils.isEmpty(str)) {
            str = null;
        }
        this.searchText = str;
        if (this.searchBox != null) {
            this.searchBox.setText(str);
        }
    }

    public void onSearchBoxTextChanged(String str, boolean z) {
        if (z || !Utility.stringsEqualOrEmpty(this.searchText, str)) {
            if (TextUtils.isEmpty(str)) {
                str = null;
            }
            this.searchText = str;
            this.hasSearchTextChangedSinceLastQuery = true;
            if (this.searchTextTimer == null) {
                this.searchTextTimer = createSearchTextTimer();
            }
        }
    }

    public GraphPlace getSelection() {
        List<GraphPlace> selectedGraphObjects = getSelectedGraphObjects();
        if (selectedGraphObjects == null || selectedGraphObjects.isEmpty()) {
            return null;
        }
        return selectedGraphObjects.iterator().next();
    }

    @Override // com.facebook.widget.PickerFragment
    public void setSettingsFromBundle(Bundle bundle) {
        super.setSettingsFromBundle(bundle);
        setPlacePickerSettingsFromBundle(bundle);
    }

    @Override // com.facebook.widget.PickerFragment, android.support.v4.app.Fragment
    public void onInflate(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        super.onInflate(activity, attributeSet, bundle);
        TypedArray typedArrayObtainStyledAttributes = activity.obtainStyledAttributes(attributeSet, b.com_facebook_place_picker_fragment);
        setRadiusInMeters(typedArrayObtainStyledAttributes.getInt(0, this.radiusInMeters));
        setResultsLimit(typedArrayObtainStyledAttributes.getInt(1, this.resultsLimit));
        if (typedArrayObtainStyledAttributes.hasValue(1)) {
            setSearchText(typedArrayObtainStyledAttributes.getString(2));
        }
        this.showSearchBox = typedArrayObtainStyledAttributes.getBoolean(3, this.showSearchBox);
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // com.facebook.widget.PickerFragment
    void setupViews(ViewGroup viewGroup) {
        SearchTextWatcher searchTextWatcher = null;
        if (this.showSearchBox) {
            ListView listView = (ListView) viewGroup.findViewById(2131296279);
            listView.addHeaderView(getActivity().getLayoutInflater().inflate(2130903050, (ViewGroup) listView, false), null, false);
            this.searchBox = (EditText) viewGroup.findViewById(2131296296);
            this.searchBox.addTextChangedListener(new SearchTextWatcher(this, searchTextWatcher));
            if (!TextUtils.isEmpty(this.searchText)) {
                this.searchBox.setText(this.searchText);
            }
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (this.searchBox != null) {
            ((InputMethodManager) getActivity().getSystemService("input_method")).showSoftInput(this.searchBox, 1);
        }
    }

    @Override // com.facebook.widget.PickerFragment, android.support.v4.app.Fragment
    public void onDetach() {
        super.onDetach();
        if (this.searchBox != null) {
            ((InputMethodManager) getActivity().getSystemService("input_method")).hideSoftInputFromWindow(this.searchBox.getWindowToken(), 0);
        }
    }

    @Override // com.facebook.widget.PickerFragment
    void saveSettingsToBundle(Bundle bundle) {
        super.saveSettingsToBundle(bundle);
        bundle.putInt(RADIUS_IN_METERS_BUNDLE_KEY, this.radiusInMeters);
        bundle.putInt(RESULTS_LIMIT_BUNDLE_KEY, this.resultsLimit);
        bundle.putString(SEARCH_TEXT_BUNDLE_KEY, this.searchText);
        bundle.putParcelable(LOCATION_BUNDLE_KEY, this.location);
        bundle.putBoolean(SHOW_SEARCH_BOX_BUNDLE_KEY, this.showSearchBox);
    }

    @Override // com.facebook.widget.PickerFragment
    void onLoadingData() {
        this.hasSearchTextChangedSinceLastQuery = false;
    }

    @Override // com.facebook.widget.PickerFragment
    Request getRequestForLoadData(Session session) {
        return createRequest(this.location, this.radiusInMeters, this.resultsLimit, this.searchText, this.extraFields, session);
    }

    @Override // com.facebook.widget.PickerFragment
    String getDefaultTitleText() {
        return getString(2131100136);
    }

    @Override // com.facebook.widget.PickerFragment
    void logAppEvents(boolean z) {
        AppEventsLogger appEventsLoggerNewLogger = AppEventsLogger.newLogger(getActivity(), getSession());
        Bundle bundle = new Bundle();
        bundle.putString(AnalyticsEvents.PARAMETER_DIALOG_OUTCOME, z ? AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_COMPLETED : AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN);
        bundle.putInt("num_places_picked", getSelection() != null ? 1 : 0);
        appEventsLoggerNewLogger.logSdkEvent(AnalyticsEvents.EVENT_PLACE_PICKER_USAGE, null, bundle);
    }

    @Override // com.facebook.widget.PickerFragment
    PickerFragment<GraphPlace>.PickerFragmentAdapter<GraphPlace> createAdapter() {
        PickerFragment<GraphPlace>.PickerFragmentAdapter<GraphPlace> pickerFragmentAdapter = new PickerFragment<GraphPlace>.PickerFragmentAdapter<GraphPlace>(this, getActivity()) { // from class: com.facebook.widget.PlacePickerFragment.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.facebook.widget.GraphObjectAdapter
            public CharSequence getSubTitleOfGraphObject(GraphPlace graphPlace) {
                String category = graphPlace.getCategory();
                Integer num = (Integer) graphPlace.getProperty(PlacePickerFragment.WERE_HERE_COUNT);
                if (category != null && num != null) {
                    return PlacePickerFragment.this.getString(2131100131, category, num);
                }
                if (category == null && num != null) {
                    return PlacePickerFragment.this.getString(2131100133, num);
                }
                if (category == null || num != null) {
                    return null;
                }
                return PlacePickerFragment.this.getString(2131100132, category);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.facebook.widget.GraphObjectAdapter
            public int getGraphObjectRowLayoutId(GraphPlace graphPlace) {
                return 2130903054;
            }

            @Override // com.facebook.widget.GraphObjectAdapter
            protected int getDefaultPicture() {
                return 2130837692;
            }
        };
        pickerFragmentAdapter.setShowCheckbox(false);
        pickerFragmentAdapter.setShowPicture(getShowPictures());
        return pickerFragmentAdapter;
    }

    @Override // com.facebook.widget.PickerFragment
    PickerFragment<GraphPlace>.LoadingStrategy createLoadingStrategy() {
        return new AsNeededLoadingStrategy(this, null);
    }

    @Override // com.facebook.widget.PickerFragment
    PickerFragment<GraphPlace>.SelectionStrategy createSelectionStrategy() {
        return new PickerFragment.SingleSelectionStrategy();
    }

    private Request createRequest(Location location, int i, int i2, String str, Set<String> set, Session session) {
        Request requestNewPlacesSearchRequest = Request.newPlacesSearchRequest(session, location, i, i2, str, null);
        HashSet hashSet = new HashSet(set);
        hashSet.addAll(Arrays.asList(ID, NAME, LOCATION, CATEGORY, WERE_HERE_COUNT));
        String pictureFieldSpecifier = this.adapter.getPictureFieldSpecifier();
        if (pictureFieldSpecifier != null) {
            hashSet.add(pictureFieldSpecifier);
        }
        Bundle parameters = requestNewPlacesSearchRequest.getParameters();
        parameters.putString("fields", TextUtils.join(",", hashSet));
        requestNewPlacesSearchRequest.setParameters(parameters);
        return requestNewPlacesSearchRequest;
    }

    private void setPlacePickerSettingsFromBundle(Bundle bundle) {
        if (bundle != null) {
            setRadiusInMeters(bundle.getInt(RADIUS_IN_METERS_BUNDLE_KEY, this.radiusInMeters));
            setResultsLimit(bundle.getInt(RESULTS_LIMIT_BUNDLE_KEY, this.resultsLimit));
            if (bundle.containsKey(SEARCH_TEXT_BUNDLE_KEY)) {
                setSearchText(bundle.getString(SEARCH_TEXT_BUNDLE_KEY));
            }
            if (bundle.containsKey(LOCATION_BUNDLE_KEY)) {
                setLocation((Location) bundle.getParcelable(LOCATION_BUNDLE_KEY));
            }
            this.showSearchBox = bundle.getBoolean(SHOW_SEARCH_BOX_BUNDLE_KEY, this.showSearchBox);
        }
    }

    private Timer createSearchTextTimer() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() { // from class: com.facebook.widget.PlacePickerFragment.2
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                PlacePickerFragment.this.onSearchTextTimerTriggered();
            }
        }, 0L, 2000L);
        return timer;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSearchTextTimerTriggered() {
        if (this.hasSearchTextChangedSinceLastQuery) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.facebook.widget.PlacePickerFragment.3
                /* JADX WARN: Multi-variable type inference failed */
                /* JADX WARN: Type inference failed for: r1v0 */
                /* JADX WARN: Type inference failed for: r1v10 */
                /* JADX WARN: Type inference failed for: r1v11 */
                /* JADX WARN: Type inference failed for: r1v12 */
                /* JADX WARN: Type inference failed for: r1v13 */
                /* JADX WARN: Type inference failed for: r1v14 */
                /* JADX WARN: Type inference failed for: r1v6, types: [com.facebook.FacebookException] */
                /* JADX WARN: Type inference failed for: r1v7 */
                /* JADX WARN: Type inference failed for: r1v8 */
                /* JADX WARN: Type inference failed for: r1v9 */
                /* JADX WARN: Type inference failed for: r2v4, types: [com.facebook.widget.PickerFragment$OnErrorListener] */
                /* JADX WARN: Type inference failed for: r5v1, types: [java.lang.Object[]] */
                @Override // java.lang.Runnable
                public void run() {
                    ?? r1 = 0;
                    r1 = 0;
                    r1 = 0;
                    r1 = 0;
                    r1 = 0;
                    r1 = 0;
                    try {
                        try {
                            PlacePickerFragment.this.loadData(true);
                            if (0 != 0) {
                                PickerFragment.OnErrorListener onErrorListener = PlacePickerFragment.this.getOnErrorListener();
                                if (onErrorListener != null) {
                                    onErrorListener.onError(PlacePickerFragment.this, null);
                                } else {
                                    Logger.log(LoggingBehavior.REQUESTS, PlacePickerFragment.TAG, "Error loading data : %s", null);
                                }
                            }
                        } catch (FacebookException e) {
                            if (e != null) {
                                PickerFragment.OnErrorListener onErrorListener2 = PlacePickerFragment.this.getOnErrorListener();
                                if (onErrorListener2 != null) {
                                    onErrorListener2.onError(PlacePickerFragment.this, e);
                                } else {
                                    Logger.log(LoggingBehavior.REQUESTS, PlacePickerFragment.TAG, "Error loading data : %s", e);
                                }
                            }
                        } catch (Exception e2) {
                            FacebookException facebookException = new FacebookException(e2);
                            if (facebookException != null) {
                                PickerFragment.OnErrorListener onErrorListener3 = PlacePickerFragment.this.getOnErrorListener();
                                if (onErrorListener3 != null) {
                                    PlacePickerFragment placePickerFragment = PlacePickerFragment.this;
                                    onErrorListener3.onError(placePickerFragment, facebookException);
                                    r1 = placePickerFragment;
                                } else {
                                    LoggingBehavior loggingBehavior = LoggingBehavior.REQUESTS;
                                    String str = PlacePickerFragment.TAG;
                                    Logger.log(loggingBehavior, PlacePickerFragment.TAG, "Error loading data : %s", facebookException);
                                    r1 = str;
                                }
                            }
                        }
                    } catch (Throwable th) {
                        if (r1 != 0) {
                            ?? onErrorListener4 = PlacePickerFragment.this.getOnErrorListener();
                            if (onErrorListener4 != 0) {
                                onErrorListener4.onError(PlacePickerFragment.this, r1);
                            } else {
                                Logger.log(LoggingBehavior.REQUESTS, PlacePickerFragment.TAG, "Error loading data : %s", (Object[]) new Object[]{r1});
                            }
                        }
                        throw th;
                    }
                }
            });
        } else {
            this.searchTextTimer.cancel();
            this.searchTextTimer = null;
        }
    }

    class AsNeededLoadingStrategy extends PickerFragment<GraphPlace>.LoadingStrategy {
        private AsNeededLoadingStrategy() {
            super();
        }

        /* synthetic */ AsNeededLoadingStrategy(PlacePickerFragment placePickerFragment, AsNeededLoadingStrategy asNeededLoadingStrategy) {
            this();
        }

        @Override // com.facebook.widget.PickerFragment.LoadingStrategy
        public void attach(GraphObjectAdapter<GraphPlace> graphObjectAdapter) {
            super.attach(graphObjectAdapter);
            this.adapter.setDataNeededListener(new GraphObjectAdapter.DataNeededListener() { // from class: com.facebook.widget.PlacePickerFragment.AsNeededLoadingStrategy.1
                @Override // com.facebook.widget.GraphObjectAdapter.DataNeededListener
                public void onDataNeeded() {
                    if (!AsNeededLoadingStrategy.this.loader.isLoading()) {
                        AsNeededLoadingStrategy.this.loader.followNextLink();
                    }
                }
            });
        }

        @Override // com.facebook.widget.PickerFragment.LoadingStrategy
        protected void onLoadFinished(GraphObjectPagingLoader<GraphPlace> graphObjectPagingLoader, SimpleGraphObjectCursor<GraphPlace> simpleGraphObjectCursor) {
            super.onLoadFinished(graphObjectPagingLoader, simpleGraphObjectCursor);
            if (simpleGraphObjectCursor != null && !graphObjectPagingLoader.isLoading()) {
                PlacePickerFragment.this.hideActivityCircle();
                if (simpleGraphObjectCursor.isFromCache()) {
                    graphObjectPagingLoader.refreshOriginalRequest(simpleGraphObjectCursor.areMoreObjectsAvailable() ? 2000 : 0);
                }
            }
        }
    }

    class SearchTextWatcher implements TextWatcher {
        private SearchTextWatcher() {
        }

        /* synthetic */ SearchTextWatcher(PlacePickerFragment placePickerFragment, SearchTextWatcher searchTextWatcher) {
            this();
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            PlacePickerFragment.this.onSearchBoxTextChanged(charSequence.toString(), false);
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }
    }
}
