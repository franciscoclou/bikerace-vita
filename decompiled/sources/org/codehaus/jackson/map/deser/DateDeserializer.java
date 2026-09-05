package org.codehaus.jackson.map.deser;

import java.util.Date;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationContext;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class DateDeserializer extends StdScalarDeserializer<Date> {
    public DateDeserializer() {
        super(Date.class);
    }

    @Override // org.codehaus.jackson.map.JsonDeserializer
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        return _parseDate(jsonParser, deserializationContext);
    }
}
