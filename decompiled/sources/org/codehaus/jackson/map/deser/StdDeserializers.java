package org.codehaus.jackson.map.deser;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.JavaType;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class StdDeserializers {
    final HashMap<JavaType, JsonDeserializer<Object>> _deserializers = new HashMap<>();

    private StdDeserializers() {
        add(new UntypedObjectDeserializer());
        StdDeserializer.StringDeserializer stringDeserializer = new StdDeserializer.StringDeserializer();
        add(stringDeserializer, String.class);
        add(stringDeserializer, CharSequence.class);
        add(new StdDeserializer.ClassDeserializer());
        add(new StdDeserializer.BooleanDeserializer(Boolean.class, null));
        add(new StdDeserializer.ByteDeserializer(Byte.class, null));
        add(new StdDeserializer.ShortDeserializer(Short.class, null));
        add(new StdDeserializer.CharacterDeserializer(Character.class, null));
        add(new StdDeserializer.IntegerDeserializer(Integer.class, null));
        add(new StdDeserializer.LongDeserializer(Long.class, null));
        add(new StdDeserializer.FloatDeserializer(Float.class, null));
        add(new StdDeserializer.DoubleDeserializer(Double.class, null));
        add(new StdDeserializer.BooleanDeserializer(Boolean.TYPE, Boolean.FALSE));
        add(new StdDeserializer.ByteDeserializer(Byte.TYPE, (byte) 0));
        add(new StdDeserializer.ShortDeserializer(Short.TYPE, (short) 0));
        add(new StdDeserializer.CharacterDeserializer(Character.TYPE, (char) 0));
        add(new StdDeserializer.IntegerDeserializer(Integer.TYPE, 0));
        add(new StdDeserializer.LongDeserializer(Long.TYPE, 0L));
        add(new StdDeserializer.FloatDeserializer(Float.TYPE, Float.valueOf(0.0f)));
        add(new StdDeserializer.DoubleDeserializer(Double.TYPE, Double.valueOf(0.0d)));
        add(new StdDeserializer.NumberDeserializer());
        add(new StdDeserializer.BigDecimalDeserializer());
        add(new StdDeserializer.BigIntegerDeserializer());
        add(new DateDeserializer());
        add(new StdDeserializer.SqlDateDeserializer());
        add(new TimestampDeserializer());
        add(new StdDeserializer.CalendarDeserializer());
        add(new StdDeserializer.CalendarDeserializer(GregorianCalendar.class), GregorianCalendar.class);
        Iterator<FromStringDeserializer<?>> it = FromStringDeserializer.all().iterator();
        while (it.hasNext()) {
            add(it.next());
        }
        add(new StdDeserializer.StackTraceElementDeserializer());
        add(new StdDeserializer.TokenBufferDeserializer());
        add(new StdDeserializer.AtomicBooleanDeserializer());
    }

    public static HashMap<JavaType, JsonDeserializer<Object>> constructAll() {
        return new StdDeserializers()._deserializers;
    }

    private void add(StdDeserializer<?> stdDeserializer) {
        add(stdDeserializer, stdDeserializer.getValueClass());
    }

    private void add(StdDeserializer<?> stdDeserializer, Class<?> cls) {
        this._deserializers.put(TypeFactory.defaultInstance().constructType(cls), stdDeserializer);
    }
}
