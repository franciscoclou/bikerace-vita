package com.topfreegames.bikerace.l;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/* JADX INFO: compiled from: GameSessionJsonSerde.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a {
    public static byte[] a(com.topfreegames.bikerace.b.a aVar) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(aVar);
        objectOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    public static com.topfreegames.bikerace.b.a a(byte[] bArr) {
        return (com.topfreegames.bikerace.b.a) new ObjectInputStream(new ByteArrayInputStream(bArr)).readObject();
    }
}
