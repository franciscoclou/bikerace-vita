package com.facebook.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.a.a;
import android.support.v4.app.Fragment;
import android.support.v4.app.o;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.animation.AlphaAnimation;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.FacebookException;
import com.facebook.Request;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.internal.SessionTracker;
import com.facebook.model.GraphObject;
import com.topfreegames.b.b;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class PickerFragment<T extends GraphObject> extends Fragment {
    private static final String ACTIVITY_CIRCLE_SHOW_KEY = "com.facebook.android.PickerFragment.ActivityCircleShown";
    public static final String DONE_BUTTON_TEXT_BUNDLE_KEY = "com.facebook.widget.PickerFragment.DoneButtonText";
    public static final String EXTRA_FIELDS_BUNDLE_KEY = "com.facebook.widget.PickerFragment.ExtraFields";
    private static final int PROFILE_PICTURE_PREFETCH_BUFFER = 5;
    private static final String SELECTION_BUNDLE_KEY = "com.facebook.android.PickerFragment.Selection";
    public static final String SHOW_PICTURES_BUNDLE_KEY = "com.facebook.widget.PickerFragment.ShowPictures";
    public static final String SHOW_TITLE_BAR_BUNDLE_KEY = "com.facebook.widget.PickerFragment.ShowTitleBar";
    public static final String TITLE_TEXT_BUNDLE_KEY = "com.facebook.widget.PickerFragment.TitleText";
    private ProgressBar activityCircle;
    GraphObjectAdapter<T> adapter;
    private boolean appEventsLogged;
    private Button doneButton;
    private Drawable doneButtonBackground;
    private String doneButtonText;
    private GraphObjectFilter<T> filter;
    private final Class<T> graphObjectClass;
    private final int layout;
    private ListView listView;
    private PickerFragment<T>.LoadingStrategy loadingStrategy;
    private OnDataChangedListener onDataChangedListener;
    private OnDoneButtonClickedListener onDoneButtonClickedListener;
    private OnErrorListener onErrorListener;
    private OnSelectionChangedListener onSelectionChangedListener;
    private PickerFragment<T>.SelectionStrategy selectionStrategy;
    private SessionTracker sessionTracker;
    private Drawable titleBarBackground;
    private String titleText;
    private TextView titleTextView;
    private boolean showPictures = true;
    private boolean showTitleBar = true;
    HashSet<String> extraFields = new HashSet<>();
    private AbsListView.OnScrollListener onScrollListener = new AbsListView.OnScrollListener() { // from class: com.facebook.widget.PickerFragment.1
        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i) {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            PickerFragment.this.reprioritizeDownloads();
        }
    };

    public interface GraphObjectFilter<T> {
        boolean includeItem(T t);
    }

    public interface OnDataChangedListener {
        void onDataChanged(PickerFragment<?> pickerFragment);
    }

    public interface OnDoneButtonClickedListener {
        void onDoneButtonClicked(PickerFragment<?> pickerFragment);
    }

    public interface OnErrorListener {
        void onError(PickerFragment<?> pickerFragment, FacebookException facebookException);
    }

    public interface OnSelectionChangedListener {
        void onSelectionChanged(PickerFragment<?> pickerFragment);
    }

    abstract PickerFragment<T>.PickerFragmentAdapter<T> createAdapter();

    abstract PickerFragment<T>.LoadingStrategy createLoadingStrategy();

    abstract PickerFragment<T>.SelectionStrategy createSelectionStrategy();

    abstract Request getRequestForLoadData(Session session);

    PickerFragment(Class<T> cls, int i, Bundle bundle) {
        this.graphObjectClass = cls;
        this.layout = i;
        setPickerFragmentSettingsFromBundle(bundle);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.adapter = createAdapter();
        this.adapter.setFilter(new GraphObjectAdapter.Filter<T>() { // from class: com.facebook.widget.PickerFragment.2
            @Override // com.facebook.widget.GraphObjectAdapter.Filter
            public boolean includeItem(T t) {
                return PickerFragment.this.filterIncludesItem(t);
            }
        });
    }

    @Override // android.support.v4.app.Fragment
    public void onInflate(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        super.onInflate(activity, attributeSet, bundle);
        TypedArray typedArrayObtainStyledAttributes = activity.obtainStyledAttributes(attributeSet, b.com_facebook_picker_fragment);
        setShowPictures(typedArrayObtainStyledAttributes.getBoolean(0, this.showPictures));
        String string = typedArrayObtainStyledAttributes.getString(1);
        if (string != null) {
            setExtraFields(Arrays.asList(string.split(",")));
        }
        this.showTitleBar = typedArrayObtainStyledAttributes.getBoolean(2, this.showTitleBar);
        this.titleText = typedArrayObtainStyledAttributes.getString(3);
        this.doneButtonText = typedArrayObtainStyledAttributes.getString(4);
        this.titleBarBackground = typedArrayObtainStyledAttributes.getDrawable(5);
        this.doneButtonBackground = typedArrayObtainStyledAttributes.getDrawable(6);
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ViewGroup viewGroup2 = (ViewGroup) layoutInflater.inflate(this.layout, viewGroup, false);
        this.listView = (ListView) viewGroup2.findViewById(2131296279);
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.facebook.widget.PickerFragment.3
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                PickerFragment.this.onListItemClick((ListView) adapterView, view, i);
            }
        });
        this.listView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.facebook.widget.PickerFragment.4
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                return false;
            }
        });
        this.listView.setOnScrollListener(this.onScrollListener);
        this.activityCircle = (ProgressBar) viewGroup2.findViewById(2131296280);
        setupViews(viewGroup2);
        this.listView.setAdapter((ListAdapter) this.adapter);
        return viewGroup2;
    }

    @Override // android.support.v4.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.sessionTracker = new SessionTracker(getActivity(), new Session.StatusCallback() { // from class: com.facebook.widget.PickerFragment.5
            @Override // com.facebook.Session.StatusCallback
            public void call(Session session, SessionState sessionState, Exception exc) {
                if (session.isOpened()) {
                    return;
                }
                PickerFragment.this.clearResults();
            }
        });
        setSettingsFromBundle(bundle);
        this.loadingStrategy = createLoadingStrategy();
        this.loadingStrategy.attach(this.adapter);
        this.selectionStrategy = createSelectionStrategy();
        this.selectionStrategy.readSelectionFromBundle(bundle, SELECTION_BUNDLE_KEY);
        if (this.showTitleBar) {
            inflateTitleBar((ViewGroup) getView());
        }
        if (this.activityCircle != null && bundle != null) {
            if (bundle.getBoolean(ACTIVITY_CIRCLE_SHOW_KEY, false)) {
                displayActivityCircle();
            } else {
                hideActivityCircle();
            }
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.listView.setOnScrollListener(null);
        this.listView.setAdapter((ListAdapter) null);
        this.loadingStrategy.detach();
        this.sessionTracker.stopTracking();
    }

    @Override // android.support.v4.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        saveSettingsToBundle(bundle);
        this.selectionStrategy.saveSelectionToBundle(bundle, SELECTION_BUNDLE_KEY);
        if (this.activityCircle != null) {
            bundle.putBoolean(ACTIVITY_CIRCLE_SHOW_KEY, this.activityCircle.getVisibility() == 0);
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onStop() {
        if (!this.appEventsLogged) {
            logAppEvents(false);
        }
        super.onStop();
    }

    @Override // android.support.v4.app.Fragment
    public void setArguments(Bundle bundle) {
        super.setArguments(bundle);
        setSettingsFromBundle(bundle);
    }

    public OnDataChangedListener getOnDataChangedListener() {
        return this.onDataChangedListener;
    }

    public void setOnDataChangedListener(OnDataChangedListener onDataChangedListener) {
        this.onDataChangedListener = onDataChangedListener;
    }

    public OnSelectionChangedListener getOnSelectionChangedListener() {
        return this.onSelectionChangedListener;
    }

    public void setOnSelectionChangedListener(OnSelectionChangedListener onSelectionChangedListener) {
        this.onSelectionChangedListener = onSelectionChangedListener;
    }

    public OnDoneButtonClickedListener getOnDoneButtonClickedListener() {
        return this.onDoneButtonClickedListener;
    }

    public void setOnDoneButtonClickedListener(OnDoneButtonClickedListener onDoneButtonClickedListener) {
        this.onDoneButtonClickedListener = onDoneButtonClickedListener;
    }

    public OnErrorListener getOnErrorListener() {
        return this.onErrorListener;
    }

    public void setOnErrorListener(OnErrorListener onErrorListener) {
        this.onErrorListener = onErrorListener;
    }

    public GraphObjectFilter<T> getFilter() {
        return this.filter;
    }

    public void setFilter(GraphObjectFilter<T> graphObjectFilter) {
        this.filter = graphObjectFilter;
    }

    public Session getSession() {
        return this.sessionTracker.getSession();
    }

    public void setSession(Session session) {
        this.sessionTracker.setSession(session);
    }

    public boolean getShowPictures() {
        return this.showPictures;
    }

    public void setShowPictures(boolean z) {
        this.showPictures = z;
    }

    public Set<String> getExtraFields() {
        return new HashSet(this.extraFields);
    }

    public void setExtraFields(Collection<String> collection) {
        this.extraFields = new HashSet<>();
        if (collection != null) {
            this.extraFields.addAll(collection);
        }
    }

    public void setShowTitleBar(boolean z) {
        this.showTitleBar = z;
    }

    public boolean getShowTitleBar() {
        return this.showTitleBar;
    }

    public void setTitleText(String str) {
        this.titleText = str;
    }

    public String getTitleText() {
        if (this.titleText == null) {
            this.titleText = getDefaultTitleText();
        }
        return this.titleText;
    }

    public void setDoneButtonText(String str) {
        this.doneButtonText = str;
    }

    public String getDoneButtonText() {
        if (this.doneButtonText == null) {
            this.doneButtonText = getDefaultDoneButtonText();
        }
        return this.doneButtonText;
    }

    public void loadData(boolean z) {
        if (z || !this.loadingStrategy.isDataPresentOrLoading()) {
            loadDataSkippingRoundTripIfCached();
        }
    }

    public void setSettingsFromBundle(Bundle bundle) {
        setPickerFragmentSettingsFromBundle(bundle);
    }

    void setupViews(ViewGroup viewGroup) {
    }

    boolean filterIncludesItem(T t) {
        if (this.filter != null) {
            return this.filter.includeItem(t);
        }
        return true;
    }

    List<T> getSelectedGraphObjects() {
        return this.adapter.getGraphObjectsById(this.selectionStrategy.getSelectedIds());
    }

    void saveSettingsToBundle(Bundle bundle) {
        bundle.putBoolean(SHOW_PICTURES_BUNDLE_KEY, this.showPictures);
        if (!this.extraFields.isEmpty()) {
            bundle.putString(EXTRA_FIELDS_BUNDLE_KEY, TextUtils.join(",", this.extraFields));
        }
        bundle.putBoolean(SHOW_TITLE_BAR_BUNDLE_KEY, this.showTitleBar);
        bundle.putString(TITLE_TEXT_BUNDLE_KEY, this.titleText);
        bundle.putString(DONE_BUTTON_TEXT_BUNDLE_KEY, this.doneButtonText);
    }

    void onLoadingData() {
    }

    String getDefaultTitleText() {
        return null;
    }

    String getDefaultDoneButtonText() {
        return getString(2131100134);
    }

    void displayActivityCircle() {
        if (this.activityCircle != null) {
            layoutActivityCircle();
            this.activityCircle.setVisibility(0);
        }
    }

    void layoutActivityCircle() {
        setAlpha(this.activityCircle, !this.adapter.isEmpty() ? 0.25f : 1.0f);
    }

    void hideActivityCircle() {
        if (this.activityCircle != null) {
            this.activityCircle.clearAnimation();
            this.activityCircle.setVisibility(4);
        }
    }

    void setSelectionStrategy(PickerFragment<T>.SelectionStrategy selectionStrategy) {
        if (selectionStrategy != this.selectionStrategy) {
            this.selectionStrategy = selectionStrategy;
            if (this.adapter != null) {
                this.adapter.notifyDataSetChanged();
            }
        }
    }

    void logAppEvents(boolean z) {
    }

    private static void setAlpha(View view, float f) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(f, f);
        alphaAnimation.setDuration(0L);
        alphaAnimation.setFillAfter(true);
        view.startAnimation(alphaAnimation);
    }

    private void setPickerFragmentSettingsFromBundle(Bundle bundle) {
        if (bundle != null) {
            this.showPictures = bundle.getBoolean(SHOW_PICTURES_BUNDLE_KEY, this.showPictures);
            String string = bundle.getString(EXTRA_FIELDS_BUNDLE_KEY);
            if (string != null) {
                setExtraFields(Arrays.asList(string.split(",")));
            }
            this.showTitleBar = bundle.getBoolean(SHOW_TITLE_BAR_BUNDLE_KEY, this.showTitleBar);
            String string2 = bundle.getString(TITLE_TEXT_BUNDLE_KEY);
            if (string2 != null) {
                this.titleText = string2;
                if (this.titleTextView != null) {
                    this.titleTextView.setText(this.titleText);
                }
            }
            String string3 = bundle.getString(DONE_BUTTON_TEXT_BUNDLE_KEY);
            if (string3 != null) {
                this.doneButtonText = string3;
                if (this.doneButton != null) {
                    this.doneButton.setText(this.doneButtonText);
                }
            }
        }
    }

    private void inflateTitleBar(ViewGroup viewGroup) {
        ViewStub viewStub = (ViewStub) viewGroup.findViewById(2131296292);
        if (viewStub != null) {
            View viewInflate = viewStub.inflate();
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams.addRule(3, 2131296293);
            this.listView.setLayoutParams(layoutParams);
            if (this.titleBarBackground != null) {
                viewInflate.setBackgroundDrawable(this.titleBarBackground);
            }
            this.doneButton = (Button) viewGroup.findViewById(2131296290);
            if (this.doneButton != null) {
                this.doneButton.setOnClickListener(new View.OnClickListener() { // from class: com.facebook.widget.PickerFragment.6
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        PickerFragment.this.logAppEvents(true);
                        PickerFragment.this.appEventsLogged = true;
                        if (PickerFragment.this.onDoneButtonClickedListener != null) {
                            PickerFragment.this.onDoneButtonClickedListener.onDoneButtonClicked(PickerFragment.this);
                        }
                    }
                });
                if (getDoneButtonText() != null) {
                    this.doneButton.setText(getDoneButtonText());
                }
                if (this.doneButtonBackground != null) {
                    this.doneButton.setBackgroundDrawable(this.doneButtonBackground);
                }
            }
            this.titleTextView = (TextView) viewGroup.findViewById(2131296286);
            if (this.titleTextView != null && getTitleText() != null) {
                this.titleTextView.setText(getTitleText());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onListItemClick(ListView listView, View view, int i) {
        this.selectionStrategy.toggleSelection(this.adapter.getIdOfGraphObject((GraphObject) listView.getItemAtPosition(i)));
        this.adapter.notifyDataSetChanged();
        if (this.onSelectionChangedListener != null) {
            this.onSelectionChangedListener.onSelectionChanged(this);
        }
    }

    private void loadDataSkippingRoundTripIfCached() {
        clearResults();
        Request requestForLoadData = getRequestForLoadData(getSession());
        if (requestForLoadData != null) {
            onLoadingData();
            this.loadingStrategy.startLoading(requestForLoadData);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearResults() {
        if (this.adapter != null) {
            boolean z = !this.selectionStrategy.isEmpty();
            boolean z2 = this.adapter.isEmpty() ? false : true;
            this.loadingStrategy.clearResults();
            this.selectionStrategy.clear();
            this.adapter.notifyDataSetChanged();
            if (z2 && this.onDataChangedListener != null) {
                this.onDataChangedListener.onDataChanged(this);
            }
            if (z && this.onSelectionChangedListener != null) {
                this.onSelectionChangedListener.onSelectionChanged(this);
            }
        }
    }

    void updateAdapter(SimpleGraphObjectCursor<T> simpleGraphObjectCursor) {
        int position;
        if (this.adapter != null) {
            View childAt = this.listView.getChildAt(1);
            int firstVisiblePosition = this.listView.getFirstVisiblePosition();
            if (firstVisiblePosition > 0) {
                firstVisiblePosition++;
            }
            GraphObjectAdapter.SectionAndItem<T> sectionAndItem = this.adapter.getSectionAndItem(firstVisiblePosition);
            int top = (childAt == null || sectionAndItem.getType() == GraphObjectAdapter.SectionAndItem.Type.ACTIVITY_CIRCLE) ? 0 : childAt.getTop();
            boolean zChangeCursor = this.adapter.changeCursor(simpleGraphObjectCursor);
            if (childAt != null && sectionAndItem != null && (position = this.adapter.getPosition(sectionAndItem.sectionKey, sectionAndItem.graphObject)) != -1) {
                this.listView.setSelectionFromTop(position, top);
            }
            if (zChangeCursor && this.onDataChangedListener != null) {
                this.onDataChangedListener.onDataChanged(this);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reprioritizeDownloads() {
        int lastVisiblePosition = this.listView.getLastVisiblePosition();
        if (lastVisiblePosition >= 0) {
            this.adapter.prioritizeViewRange(this.listView.getFirstVisiblePosition(), lastVisiblePosition, 5);
        }
    }

    abstract class LoadingStrategy {
        protected static final int CACHED_RESULT_REFRESH_DELAY = 2000;
        protected GraphObjectAdapter<T> adapter;
        protected GraphObjectPagingLoader<T> loader;

        LoadingStrategy() {
        }

        public void attach(GraphObjectAdapter<T> graphObjectAdapter) {
            this.loader = (GraphObjectPagingLoader) PickerFragment.this.getLoaderManager().a(0, null, new o<SimpleGraphObjectCursor<T>>() { // from class: com.facebook.widget.PickerFragment.LoadingStrategy.1
                @Override // android.support.v4.app.o
                public a<SimpleGraphObjectCursor<T>> onCreateLoader(int i, Bundle bundle) {
                    return LoadingStrategy.this.onCreateLoader();
                }

                @Override // android.support.v4.app.o
                public void onLoadFinished(a<SimpleGraphObjectCursor<T>> aVar, SimpleGraphObjectCursor<T> simpleGraphObjectCursor) {
                    if (aVar != LoadingStrategy.this.loader) {
                        throw new FacebookException("Received callback for unknown loader.");
                    }
                    LoadingStrategy.this.onLoadFinished((GraphObjectPagingLoader) aVar, simpleGraphObjectCursor);
                }

                @Override // android.support.v4.app.o
                public void onLoaderReset(a<SimpleGraphObjectCursor<T>> aVar) {
                    if (aVar != LoadingStrategy.this.loader) {
                        throw new FacebookException("Received callback for unknown loader.");
                    }
                    LoadingStrategy.this.onLoadReset((GraphObjectPagingLoader) aVar);
                }
            });
            this.loader.setOnErrorListener(new GraphObjectPagingLoader.OnErrorListener() { // from class: com.facebook.widget.PickerFragment.LoadingStrategy.2
                @Override // com.facebook.widget.GraphObjectPagingLoader.OnErrorListener
                public void onError(FacebookException facebookException, GraphObjectPagingLoader<?> graphObjectPagingLoader) {
                    PickerFragment.this.hideActivityCircle();
                    if (PickerFragment.this.onErrorListener != null) {
                        PickerFragment.this.onErrorListener.onError(PickerFragment.this, facebookException);
                    }
                }
            });
            this.adapter = graphObjectAdapter;
            this.adapter.changeCursor(this.loader.getCursor());
            this.adapter.setOnErrorListener(new GraphObjectAdapter.OnErrorListener() { // from class: com.facebook.widget.PickerFragment.LoadingStrategy.3
                @Override // com.facebook.widget.GraphObjectAdapter.OnErrorListener
                public void onError(GraphObjectAdapter<?> graphObjectAdapter2, FacebookException facebookException) {
                    if (PickerFragment.this.onErrorListener != null) {
                        PickerFragment.this.onErrorListener.onError(PickerFragment.this, facebookException);
                    }
                }
            });
        }

        public void detach() {
            this.adapter.setDataNeededListener(null);
            this.adapter.setOnErrorListener(null);
            this.loader.setOnErrorListener(null);
            this.loader = null;
            this.adapter = null;
        }

        public void clearResults() {
            if (this.loader != null) {
                this.loader.clearResults();
            }
        }

        public void startLoading(Request request) {
            if (this.loader != null) {
                this.loader.startLoading(request, true);
                onStartLoading(this.loader, request);
            }
        }

        public boolean isDataPresentOrLoading() {
            return !this.adapter.isEmpty() || this.loader.isLoading();
        }

        protected GraphObjectPagingLoader<T> onCreateLoader() {
            return new GraphObjectPagingLoader<>(PickerFragment.this.getActivity(), PickerFragment.this.graphObjectClass);
        }

        protected void onStartLoading(GraphObjectPagingLoader<T> graphObjectPagingLoader, Request request) {
            PickerFragment.this.displayActivityCircle();
        }

        protected void onLoadReset(GraphObjectPagingLoader<T> graphObjectPagingLoader) {
            this.adapter.changeCursor(null);
        }

        protected void onLoadFinished(GraphObjectPagingLoader<T> graphObjectPagingLoader, SimpleGraphObjectCursor<T> simpleGraphObjectCursor) {
            PickerFragment.this.updateAdapter(simpleGraphObjectCursor);
        }
    }

    abstract class SelectionStrategy {
        abstract void clear();

        abstract Collection<String> getSelectedIds();

        abstract boolean isEmpty();

        abstract boolean isSelected(String str);

        abstract void readSelectionFromBundle(Bundle bundle, String str);

        abstract void saveSelectionToBundle(Bundle bundle, String str);

        abstract boolean shouldShowCheckBoxIfUnselected();

        abstract void toggleSelection(String str);

        SelectionStrategy() {
        }
    }

    class SingleSelectionStrategy extends PickerFragment<T>.SelectionStrategy {
        private String selectedId;

        SingleSelectionStrategy() {
            super();
        }

        @Override // com.facebook.widget.PickerFragment.SelectionStrategy
        public Collection<String> getSelectedIds() {
            return Arrays.asList(this.selectedId);
        }

        @Override // com.facebook.widget.PickerFragment.SelectionStrategy
        boolean isSelected(String str) {
            return (this.selectedId == null || str == null || !this.selectedId.equals(str)) ? false : true;
        }

        @Override // com.facebook.widget.PickerFragment.SelectionStrategy
        void toggleSelection(String str) {
            if (this.selectedId != null && this.selectedId.equals(str)) {
                this.selectedId = null;
            } else {
                this.selectedId = str;
            }
        }

        @Override // com.facebook.widget.PickerFragment.SelectionStrategy
        void saveSelectionToBundle(Bundle bundle, String str) {
            if (!TextUtils.isEmpty(this.selectedId)) {
                bundle.putString(str, this.selectedId);
            }
        }

        @Override // com.facebook.widget.PickerFragment.SelectionStrategy
        void readSelectionFromBundle(Bundle bundle, String str) {
            if (bundle != null) {
                this.selectedId = bundle.getString(str);
            }
        }

        @Override // com.facebook.widget.PickerFragment.SelectionStrategy
        public void clear() {
            this.selectedId = null;
        }

        @Override // com.facebook.widget.PickerFragment.SelectionStrategy
        boolean isEmpty() {
            return this.selectedId == null;
        }

        @Override // com.facebook.widget.PickerFragment.SelectionStrategy
        boolean shouldShowCheckBoxIfUnselected() {
            return false;
        }
    }

    class MultiSelectionStrategy extends PickerFragment<T>.SelectionStrategy {
        private Set<String> selectedIds;

        MultiSelectionStrategy() {
            super();
            this.selectedIds = new HashSet();
        }

        @Override // com.facebook.widget.PickerFragment.SelectionStrategy
        public Collection<String> getSelectedIds() {
            return this.selectedIds;
        }

        @Override // com.facebook.widget.PickerFragment.SelectionStrategy
        boolean isSelected(String str) {
            return str != null && this.selectedIds.contains(str);
        }

        @Override // com.facebook.widget.PickerFragment.SelectionStrategy
        void toggleSelection(String str) {
            if (str != null) {
                if (this.selectedIds.contains(str)) {
                    this.selectedIds.remove(str);
                } else {
                    this.selectedIds.add(str);
                }
            }
        }

        @Override // com.facebook.widget.PickerFragment.SelectionStrategy
        void saveSelectionToBundle(Bundle bundle, String str) {
            if (!this.selectedIds.isEmpty()) {
                bundle.putString(str, TextUtils.join(",", this.selectedIds));
            }
        }

        @Override // com.facebook.widget.PickerFragment.SelectionStrategy
        void readSelectionFromBundle(Bundle bundle, String str) {
            String string;
            if (bundle != null && (string = bundle.getString(str)) != null) {
                String[] strArrSplit = TextUtils.split(string, ",");
                this.selectedIds.clear();
                Collections.addAll(this.selectedIds, strArrSplit);
            }
        }

        @Override // com.facebook.widget.PickerFragment.SelectionStrategy
        public void clear() {
            this.selectedIds.clear();
        }

        @Override // com.facebook.widget.PickerFragment.SelectionStrategy
        boolean isEmpty() {
            return this.selectedIds.isEmpty();
        }

        @Override // com.facebook.widget.PickerFragment.SelectionStrategy
        boolean shouldShowCheckBoxIfUnselected() {
            return true;
        }
    }

    abstract class PickerFragmentAdapter<U extends GraphObject> extends GraphObjectAdapter<T> {
        public PickerFragmentAdapter(Context context) {
            super(context);
        }

        @Override // com.facebook.widget.GraphObjectAdapter
        boolean isGraphObjectSelected(String str) {
            return PickerFragment.this.selectionStrategy.isSelected(str);
        }

        @Override // com.facebook.widget.GraphObjectAdapter
        void updateCheckboxState(CheckBox checkBox, boolean z) {
            checkBox.setChecked(z);
            checkBox.setVisibility((z || PickerFragment.this.selectionStrategy.shouldShowCheckBoxIfUnselected()) ? 0 : 8);
        }
    }
}
