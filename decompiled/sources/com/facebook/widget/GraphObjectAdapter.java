package com.facebook.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SectionIndexer;
import android.widget.TextView;
import com.facebook.FacebookException;
import com.facebook.internal.ImageDownloader;
import com.facebook.internal.ImageRequest;
import com.facebook.internal.ImageResponse;
import com.facebook.model.GraphObject;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class GraphObjectAdapter<T extends GraphObject> extends BaseAdapter implements SectionIndexer {
    private static /* synthetic */ int[] $SWITCH_TABLE$com$facebook$widget$GraphObjectAdapter$SectionAndItem$Type = null;
    static final /* synthetic */ boolean $assertionsDisabled;
    private static final int ACTIVITY_CIRCLE_VIEW_TYPE = 2;
    private static final int DISPLAY_SECTIONS_THRESHOLD = 1;
    private static final int GRAPH_OBJECT_VIEW_TYPE = 1;
    private static final int HEADER_VIEW_TYPE = 0;
    private static final String ID = "id";
    private static final int MAX_PREFETCHED_PICTURES = 20;
    private static final String NAME = "name";
    private static final String PICTURE = "picture";
    private Context context;
    private GraphObjectCursor<T> cursor;
    private DataNeededListener dataNeededListener;
    private boolean displaySections;
    private Filter<T> filter;
    private String groupByField;
    private final LayoutInflater inflater;
    private OnErrorListener onErrorListener;
    private boolean showCheckbox;
    private boolean showPicture;
    private List<String> sortFields;
    private final Map<String, ImageRequest> pendingRequests = new HashMap();
    private List<String> sectionKeys = new ArrayList();
    private Map<String, ArrayList<T>> graphObjectsBySection = new HashMap();
    private Map<String, T> graphObjectsById = new HashMap();
    private Map<String, ImageResponse> prefetchedPictureCache = new HashMap();
    private ArrayList<String> prefetchedProfilePictureIds = new ArrayList<>();

    public interface DataNeededListener {
        void onDataNeeded();
    }

    interface Filter<T> {
        boolean includeItem(T t);
    }

    interface ItemPicture extends GraphObject {
        ItemPictureData getData();
    }

    interface ItemPictureData extends GraphObject {
        String getUrl();
    }

    public interface OnErrorListener {
        void onError(GraphObjectAdapter<?> graphObjectAdapter, FacebookException facebookException);
    }

    static /* synthetic */ int[] $SWITCH_TABLE$com$facebook$widget$GraphObjectAdapter$SectionAndItem$Type() {
        int[] iArr = $SWITCH_TABLE$com$facebook$widget$GraphObjectAdapter$SectionAndItem$Type;
        if (iArr == null) {
            iArr = new int[SectionAndItem.Type.valuesCustom().length];
            try {
                iArr[SectionAndItem.Type.ACTIVITY_CIRCLE.ordinal()] = 3;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[SectionAndItem.Type.GRAPH_OBJECT.ordinal()] = 1;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[SectionAndItem.Type.SECTION_HEADER.ordinal()] = 2;
            } catch (NoSuchFieldError e3) {
            }
            $SWITCH_TABLE$com$facebook$widget$GraphObjectAdapter$SectionAndItem$Type = iArr;
        }
        return iArr;
    }

    static {
        $assertionsDisabled = !GraphObjectAdapter.class.desiredAssertionStatus();
    }

    public class SectionAndItem<T extends GraphObject> {
        public T graphObject;
        public String sectionKey;

        public enum Type {
            GRAPH_OBJECT,
            SECTION_HEADER,
            ACTIVITY_CIRCLE;

            /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
            public static Type[] valuesCustom() {
                Type[] typeArrValuesCustom = values();
                int length = typeArrValuesCustom.length;
                Type[] typeArr = new Type[length];
                System.arraycopy(typeArrValuesCustom, 0, typeArr, 0, length);
                return typeArr;
            }
        }

        public SectionAndItem(String str, T t) {
            this.sectionKey = str;
            this.graphObject = t;
        }

        public Type getType() {
            if (this.sectionKey == null) {
                return Type.ACTIVITY_CIRCLE;
            }
            if (this.graphObject == null) {
                return Type.SECTION_HEADER;
            }
            return Type.GRAPH_OBJECT;
        }
    }

    public GraphObjectAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    public List<String> getSortFields() {
        return this.sortFields;
    }

    public void setSortFields(List<String> list) {
        this.sortFields = list;
    }

    public String getGroupByField() {
        return this.groupByField;
    }

    public void setGroupByField(String str) {
        this.groupByField = str;
    }

    public boolean getShowPicture() {
        return this.showPicture;
    }

    public void setShowPicture(boolean z) {
        this.showPicture = z;
    }

    public boolean getShowCheckbox() {
        return this.showCheckbox;
    }

    public void setShowCheckbox(boolean z) {
        this.showCheckbox = z;
    }

    public DataNeededListener getDataNeededListener() {
        return this.dataNeededListener;
    }

    public void setDataNeededListener(DataNeededListener dataNeededListener) {
        this.dataNeededListener = dataNeededListener;
    }

    public OnErrorListener getOnErrorListener() {
        return this.onErrorListener;
    }

    public void setOnErrorListener(OnErrorListener onErrorListener) {
        this.onErrorListener = onErrorListener;
    }

    public GraphObjectCursor<T> getCursor() {
        return this.cursor;
    }

    public boolean changeCursor(GraphObjectCursor<T> graphObjectCursor) {
        if (this.cursor == graphObjectCursor) {
            return false;
        }
        if (this.cursor != null) {
            this.cursor.close();
        }
        this.cursor = graphObjectCursor;
        rebuildAndNotify();
        return true;
    }

    public void rebuildAndNotify() {
        rebuildSections();
        notifyDataSetChanged();
    }

    public void prioritizeViewRange(int i, int i2, int i3) {
        if (i2 >= i && this.sectionKeys.size() != 0) {
            for (int i4 = i2; i4 >= 0; i4--) {
                SectionAndItem<T> sectionAndItem = getSectionAndItem(i4);
                if (sectionAndItem.graphObject != null) {
                    ImageRequest imageRequest = this.pendingRequests.get(getIdOfGraphObject(sectionAndItem.graphObject));
                    if (imageRequest != null) {
                        ImageDownloader.prioritizeRequest(imageRequest);
                    }
                }
            }
            int iMin = Math.min(i2 + i3, getCount() - 1);
            ArrayList<GraphObject> arrayList = new ArrayList();
            for (int iMax = Math.max(0, i - i3); iMax < i; iMax++) {
                SectionAndItem<T> sectionAndItem2 = getSectionAndItem(iMax);
                if (sectionAndItem2.graphObject != null) {
                    arrayList.add(sectionAndItem2.graphObject);
                }
            }
            for (int i5 = i2 + 1; i5 <= iMin; i5++) {
                SectionAndItem<T> sectionAndItem3 = getSectionAndItem(i5);
                if (sectionAndItem3.graphObject != null) {
                    arrayList.add(sectionAndItem3.graphObject);
                }
            }
            for (GraphObject graphObject : arrayList) {
                URI pictureUriOfGraphObject = getPictureUriOfGraphObject(graphObject);
                String idOfGraphObject = getIdOfGraphObject(graphObject);
                boolean zRemove = this.prefetchedProfilePictureIds.remove(idOfGraphObject);
                this.prefetchedProfilePictureIds.add(idOfGraphObject);
                if (!zRemove) {
                    downloadProfilePicture(idOfGraphObject, pictureUriOfGraphObject, null);
                }
            }
        }
    }

    protected String getSectionKeyOfGraphObject(T t) {
        String upperCase = null;
        if (this.groupByField != null && (upperCase = (String) t.getProperty(this.groupByField)) != null && upperCase.length() > 0) {
            upperCase = upperCase.substring(0, 1).toUpperCase();
        }
        return upperCase != null ? upperCase : "";
    }

    protected CharSequence getTitleOfGraphObject(T t) {
        return (String) t.getProperty(NAME);
    }

    protected CharSequence getSubTitleOfGraphObject(T t) {
        return null;
    }

    protected URI getPictureUriOfGraphObject(T t) {
        String url;
        ItemPictureData data;
        Object property = t.getProperty(PICTURE);
        if (property instanceof String) {
            url = (String) property;
        } else {
            url = (!(property instanceof JSONObject) || (data = ((ItemPicture) GraphObject.Factory.create((JSONObject) property).cast(ItemPicture.class)).getData()) == null) ? null : data.getUrl();
        }
        if (url != null) {
            try {
                return new URI(url);
            } catch (URISyntaxException e) {
            }
        }
        return null;
    }

    protected View getSectionHeaderView(String str, View view, ViewGroup viewGroup) {
        TextView textView = (TextView) view;
        TextView textView2 = textView == null ? (TextView) this.inflater.inflate(2130903049, (ViewGroup) null) : textView;
        textView2.setText(str);
        return textView2;
    }

    protected View getGraphObjectView(T t, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = createGraphObjectView(t);
        }
        populateGraphObjectView(view, t);
        return view;
    }

    private View getActivityCircleView(View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.inflater.inflate(2130903045, (ViewGroup) null);
        }
        ((ProgressBar) view.findViewById(2131296282)).setVisibility(0);
        return view;
    }

    protected int getGraphObjectRowLayoutId(T t) {
        return 2130903048;
    }

    protected int getDefaultPicture() {
        return 2130837693;
    }

    protected View createGraphObjectView(T t) {
        View viewInflate = this.inflater.inflate(getGraphObjectRowLayoutId(t), (ViewGroup) null);
        ViewStub viewStub = (ViewStub) viewInflate.findViewById(2131296287);
        if (viewStub != null) {
            if (!getShowCheckbox()) {
                viewStub.setVisibility(8);
            } else {
                updateCheckboxState((CheckBox) viewStub.inflate(), false);
            }
        }
        ViewStub viewStub2 = (ViewStub) viewInflate.findViewById(2131296285);
        if (!getShowPicture()) {
            viewStub2.setVisibility(8);
        } else {
            ((ImageView) viewStub2.inflate()).setVisibility(0);
        }
        return viewInflate;
    }

    protected void populateGraphObjectView(View view, T t) {
        URI pictureUriOfGraphObject;
        String idOfGraphObject = getIdOfGraphObject(t);
        view.setTag(idOfGraphObject);
        CharSequence titleOfGraphObject = getTitleOfGraphObject(t);
        TextView textView = (TextView) view.findViewById(2131296286);
        if (textView != null) {
            textView.setText(titleOfGraphObject, TextView.BufferType.SPANNABLE);
        }
        CharSequence subTitleOfGraphObject = getSubTitleOfGraphObject(t);
        TextView textView2 = (TextView) view.findViewById(2131296294);
        if (textView2 != null) {
            if (subTitleOfGraphObject != null) {
                textView2.setText(subTitleOfGraphObject, TextView.BufferType.SPANNABLE);
                textView2.setVisibility(0);
            } else {
                textView2.setVisibility(8);
            }
        }
        if (getShowCheckbox()) {
            updateCheckboxState((CheckBox) view.findViewById(2131296283), isGraphObjectSelected(idOfGraphObject));
        }
        if (getShowPicture() && (pictureUriOfGraphObject = getPictureUriOfGraphObject(t)) != null) {
            ImageView imageView = (ImageView) view.findViewById(2131296284);
            if (this.prefetchedPictureCache.containsKey(idOfGraphObject)) {
                ImageResponse imageResponse = this.prefetchedPictureCache.get(idOfGraphObject);
                imageView.setImageBitmap(imageResponse.getBitmap());
                imageView.setTag(imageResponse.getRequest().getImageUri());
                return;
            }
            downloadProfilePicture(idOfGraphObject, pictureUriOfGraphObject, imageView);
        }
    }

    String getIdOfGraphObject(T t) {
        if (t.asMap().containsKey(ID)) {
            Object property = t.getProperty(ID);
            if (property instanceof String) {
                return (String) property;
            }
        }
        throw new FacebookException("Received an object without an ID.");
    }

    boolean filterIncludesItem(T t) {
        return this.filter == null || this.filter.includeItem(t);
    }

    Filter<T> getFilter() {
        return this.filter;
    }

    void setFilter(Filter<T> filter) {
        this.filter = filter;
    }

    boolean isGraphObjectSelected(String str) {
        return false;
    }

    void updateCheckboxState(CheckBox checkBox, boolean z) {
    }

    String getPictureFieldSpecifier() {
        ImageView imageView = (ImageView) createGraphObjectView(null).findViewById(2131296284);
        if (imageView == null) {
            return null;
        }
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        return String.format("picture.height(%d).width(%d)", Integer.valueOf(layoutParams.height), Integer.valueOf(layoutParams.width));
    }

    private boolean shouldShowActivityCircleCell() {
        return (this.cursor == null || !this.cursor.areMoreObjectsAvailable() || this.dataNeededListener == null || isEmpty()) ? false : true;
    }

    private void rebuildSections() {
        int i;
        boolean z = false;
        this.sectionKeys = new ArrayList();
        this.graphObjectsBySection = new HashMap();
        this.graphObjectsById = new HashMap();
        this.displaySections = false;
        if (this.cursor != null && this.cursor.getCount() != 0) {
            this.cursor.moveToFirst();
            int i2 = 0;
            while (true) {
                GraphObject graphObject = this.cursor.getGraphObject();
                if (filterIncludesItem(graphObject)) {
                    i = i2 + 1;
                    String sectionKeyOfGraphObject = getSectionKeyOfGraphObject(graphObject);
                    if (!this.graphObjectsBySection.containsKey(sectionKeyOfGraphObject)) {
                        this.sectionKeys.add(sectionKeyOfGraphObject);
                        this.graphObjectsBySection.put(sectionKeyOfGraphObject, new ArrayList<>());
                    }
                    this.graphObjectsBySection.get(sectionKeyOfGraphObject).add(graphObject);
                    this.graphObjectsById.put(getIdOfGraphObject(graphObject), (T) graphObject);
                } else {
                    i = i2;
                }
                if (!this.cursor.moveToNext()) {
                    break;
                } else {
                    i2 = i;
                }
            }
            if (this.sortFields != null) {
                final Collator collator = Collator.getInstance();
                Iterator<ArrayList<T>> it = this.graphObjectsBySection.values().iterator();
                while (it.hasNext()) {
                    Collections.sort(it.next(), new Comparator<GraphObject>() { // from class: com.facebook.widget.GraphObjectAdapter.1
                        @Override // java.util.Comparator
                        public int compare(GraphObject graphObject2, GraphObject graphObject3) {
                            return GraphObjectAdapter.compareGraphObjects(graphObject2, graphObject3, GraphObjectAdapter.this.sortFields, collator);
                        }
                    });
                }
            }
            Collections.sort(this.sectionKeys, Collator.getInstance());
            if (this.sectionKeys.size() > 1 && i > 1) {
                z = true;
            }
            this.displaySections = z;
        }
    }

    SectionAndItem<T> getSectionAndItem(int i) {
        T t;
        String str = null;
        if (this.sectionKeys.size() == 0) {
            return null;
        }
        if (!this.displaySections) {
            String str2 = this.sectionKeys.get(0);
            ArrayList<T> arrayList = this.graphObjectsBySection.get(str2);
            if (i >= 0 && i < arrayList.size()) {
                t = this.graphObjectsBySection.get(str2).get(i);
                str = str2;
            } else {
                if ($assertionsDisabled || (this.dataNeededListener != null && this.cursor.areMoreObjectsAvailable())) {
                    return new SectionAndItem<>(null, null);
                }
                throw new AssertionError();
            }
        } else {
            for (String str3 : this.sectionKeys) {
                int i2 = i - 1;
                if (i == 0) {
                    t = null;
                    str = str3;
                } else {
                    ArrayList<T> arrayList2 = this.graphObjectsBySection.get(str3);
                    if (i2 < arrayList2.size()) {
                        t = arrayList2.get(i2);
                        str = str3;
                    } else {
                        i = i2 - arrayList2.size();
                    }
                }
            }
            t = null;
        }
        if (str != null) {
            return new SectionAndItem<>(str, t);
        }
        throw new IndexOutOfBoundsException("position");
    }

    int getPosition(String str, T t) {
        boolean z;
        Iterator<String> it = this.sectionKeys.iterator();
        int size = 0;
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            }
            String next = it.next();
            if (this.displaySections) {
                size++;
            }
            if (next.equals(str)) {
                z = true;
                break;
            }
            size = this.graphObjectsBySection.get(next).size() + size;
        }
        if (!z) {
            return -1;
        }
        if (t == null) {
            return size - (this.displaySections ? 1 : 0);
        }
        Iterator<T> it2 = this.graphObjectsBySection.get(str).iterator();
        while (it2.hasNext()) {
            if (!GraphObject.Factory.hasSameId(it2.next(), t)) {
                size++;
            } else {
                return size;
            }
        }
        return -1;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean isEmpty() {
        return this.sectionKeys.size() == 0;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        int i;
        if (this.sectionKeys.size() == 0) {
            return 0;
        }
        int size = this.displaySections ? this.sectionKeys.size() : 0;
        Iterator<ArrayList<T>> it = this.graphObjectsBySection.values().iterator();
        while (true) {
            i = size;
            if (!it.hasNext()) {
                break;
            }
            size = it.next().size() + i;
        }
        if (shouldShowActivityCircleCell()) {
            i++;
        }
        return i;
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean areAllItemsEnabled() {
        return this.displaySections;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return true;
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean isEnabled(int i) {
        return getSectionAndItem(i).getType() == SectionAndItem.Type.GRAPH_OBJECT;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        SectionAndItem<T> sectionAndItem = getSectionAndItem(i);
        if (sectionAndItem.getType() == SectionAndItem.Type.GRAPH_OBJECT) {
            return sectionAndItem.graphObject;
        }
        return null;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        String idOfGraphObject;
        SectionAndItem<T> sectionAndItem = getSectionAndItem(i);
        if (sectionAndItem == null || sectionAndItem.graphObject == null || (idOfGraphObject = getIdOfGraphObject(sectionAndItem.graphObject)) == null) {
            return 0L;
        }
        return Long.parseLong(idOfGraphObject);
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getViewTypeCount() {
        return 3;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getItemViewType(int i) {
        switch ($SWITCH_TABLE$com$facebook$widget$GraphObjectAdapter$SectionAndItem$Type()[getSectionAndItem(i).getType().ordinal()]) {
            case 1:
                return 1;
            case 2:
                return 0;
            case 3:
                return 2;
            default:
                throw new FacebookException("Unexpected type of section and item.");
        }
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        SectionAndItem<T> sectionAndItem = getSectionAndItem(i);
        switch ($SWITCH_TABLE$com$facebook$widget$GraphObjectAdapter$SectionAndItem$Type()[sectionAndItem.getType().ordinal()]) {
            case 1:
                return getGraphObjectView(sectionAndItem.graphObject, view, viewGroup);
            case 2:
                return getSectionHeaderView(sectionAndItem.sectionKey, view, viewGroup);
            case 3:
                if (!$assertionsDisabled && (!this.cursor.areMoreObjectsAvailable() || this.dataNeededListener == null)) {
                    throw new AssertionError();
                }
                this.dataNeededListener.onDataNeeded();
                return getActivityCircleView(view, viewGroup);
            default:
                throw new FacebookException("Unexpected type of section and item.");
        }
    }

    @Override // android.widget.SectionIndexer
    public Object[] getSections() {
        return this.displaySections ? this.sectionKeys.toArray() : new Object[0];
    }

    @Override // android.widget.SectionIndexer
    public int getPositionForSection(int i) {
        int iMax;
        if (!this.displaySections || (iMax = Math.max(0, Math.min(i, this.sectionKeys.size() - 1))) >= this.sectionKeys.size()) {
            return 0;
        }
        return getPosition(this.sectionKeys.get(iMax), null);
    }

    @Override // android.widget.SectionIndexer
    public int getSectionForPosition(int i) {
        SectionAndItem<T> sectionAndItem = getSectionAndItem(i);
        if (sectionAndItem == null || sectionAndItem.getType() == SectionAndItem.Type.ACTIVITY_CIRCLE) {
            return 0;
        }
        return Math.max(0, Math.min(this.sectionKeys.indexOf(sectionAndItem.sectionKey), this.sectionKeys.size() - 1));
    }

    public List<T> getGraphObjectsById(Collection<String> collection) {
        HashSet hashSet = new HashSet();
        hashSet.addAll(collection);
        ArrayList arrayList = new ArrayList(hashSet.size());
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            T t = this.graphObjectsById.get((String) it.next());
            if (t != null) {
                arrayList.add(t);
            }
        }
        return arrayList;
    }

    private void downloadProfilePicture(final String str, URI uri, final ImageView imageView) {
        if (uri != null) {
            boolean z = imageView == null;
            if (z || !uri.equals(imageView.getTag())) {
                if (!z) {
                    imageView.setTag(str);
                    imageView.setImageResource(getDefaultPicture());
                }
                ImageRequest imageRequestBuild = new ImageRequest.Builder(this.context.getApplicationContext(), uri).setCallerTag(this).setCallback(new ImageRequest.Callback() { // from class: com.facebook.widget.GraphObjectAdapter.2
                    @Override // com.facebook.internal.ImageRequest.Callback
                    public void onCompleted(ImageResponse imageResponse) {
                        GraphObjectAdapter.this.processImageResponse(imageResponse, str, imageView);
                    }
                }).build();
                this.pendingRequests.put(str, imageRequestBuild);
                ImageDownloader.downloadAsync(imageRequestBuild);
            }
        }
    }

    private void callOnErrorListener(Exception exc) {
        if (this.onErrorListener != null) {
            this.onErrorListener.onError(this, (FacebookException) (!(exc instanceof FacebookException) ? new FacebookException(exc) : exc));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processImageResponse(ImageResponse imageResponse, String str, ImageView imageView) {
        this.pendingRequests.remove(str);
        if (imageResponse.getError() != null) {
            callOnErrorListener(imageResponse.getError());
        }
        if (imageView == null) {
            if (imageResponse.getBitmap() != null) {
                if (this.prefetchedPictureCache.size() >= MAX_PREFETCHED_PICTURES) {
                    this.prefetchedPictureCache.remove(this.prefetchedProfilePictureIds.remove(0));
                }
                this.prefetchedPictureCache.put(str, imageResponse);
                return;
            }
            return;
        }
        if (str.equals(imageView.getTag())) {
            Exception error = imageResponse.getError();
            Bitmap bitmap = imageResponse.getBitmap();
            if (error == null && bitmap != null) {
                imageView.setImageBitmap(bitmap);
                imageView.setTag(imageResponse.getRequest().getImageUri());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int compareGraphObjects(GraphObject graphObject, GraphObject graphObject2, Collection<String> collection, Collator collator) {
        for (String str : collection) {
            String str2 = (String) graphObject.getProperty(str);
            String str3 = (String) graphObject2.getProperty(str);
            if (str2 != null && str3 != null) {
                int iCompare = collator.compare(str2, str3);
                if (iCompare != 0) {
                    return iCompare;
                }
            } else if (str2 != null || str3 != null) {
                return str2 == null ? -1 : 1;
            }
        }
        return 0;
    }
}
