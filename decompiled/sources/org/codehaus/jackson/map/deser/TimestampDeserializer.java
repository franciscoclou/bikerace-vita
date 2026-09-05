package org.codehaus.jackson.map.deser;

import java.sql.Timestamp;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationContext;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class TimestampDeserializer extends StdScalarDeserializer<Timestamp> {
    public TimestampDeserializer() {
        super(Timestamp.class);
    }

    @Override // org.codehaus.jackson.map.JsonDeserializer
    public Timestamp deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        return new Timestamp(_parseDate(jsonParser, deserializationContext).getTime());
    }
}
