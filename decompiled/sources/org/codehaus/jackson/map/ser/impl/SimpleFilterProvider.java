package org.codehaus.jackson.map.ser.impl;

import java.util.HashMap;
import java.util.Map;
import org.codehaus.jackson.map.ser.BeanPropertyFilter;
import org.codehaus.jackson.map.ser.FilterProvider;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class SimpleFilterProvider extends FilterProvider {
    protected BeanPropertyFilter _defaultFilter;
    protected final Map<String, BeanPropertyFilter> _filtersById = new HashMap();

    public SimpleFilterProvider() {
    }

    public SimpleFilterProvider(Map<String, BeanPropertyFilter> map) {
    }

    public SimpleFilterProvider setDefaultFilter(BeanPropertyFilter beanPropertyFilter) {
        this._defaultFilter = beanPropertyFilter;
        return this;
    }

    public SimpleFilterProvider addFilter(String str, BeanPropertyFilter beanPropertyFilter) {
        this._filtersById.put(str, beanPropertyFilter);
        return this;
    }

    public BeanPropertyFilter removeFilter(String str) {
        return this._filtersById.remove(str);
    }

    @Override // org.codehaus.jackson.map.ser.FilterProvider
    public BeanPropertyFilter findFilter(Object obj) {
        BeanPropertyFilter beanPropertyFilter = this._filtersById.get(obj);
        return beanPropertyFilter == null ? this._defaultFilter : beanPropertyFilter;
    }
}
