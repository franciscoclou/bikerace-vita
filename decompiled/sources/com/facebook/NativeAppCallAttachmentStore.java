package com.facebook;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class NativeAppCallAttachmentStore implements NativeAppCallContentProvider.AttachmentDataSource {
    static final String ATTACHMENTS_DIR_NAME = "com.facebook.NativeAppCallAttachmentStore.files";
    private static final String TAG = NativeAppCallAttachmentStore.class.getName();
    private static File attachmentsDirectory;

    interface ProcessAttachment<T> {
        void processAttachment(T t, File file);
    }

    public void addAttachmentsForCall(Context context, UUID uuid, Map<String, Bitmap> map) {
        Validate.notNull(context, "context");
        Validate.notNull(uuid, "callId");
        Validate.containsNoNulls(map.values(), "imageAttachments");
        Validate.containsNoNullOrEmpty(map.keySet(), "imageAttachments");
        addAttachments(context, uuid, map, new ProcessAttachment<Bitmap>() { // from class: com.facebook.NativeAppCallAttachmentStore.1
            @Override // com.facebook.NativeAppCallAttachmentStore.ProcessAttachment
            public void processAttachment(Bitmap bitmap, File file) {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                try {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                } finally {
                    Utility.closeQuietly(fileOutputStream);
                }
            }
        });
    }

    public void addAttachmentFilesForCall(Context context, UUID uuid, Map<String, File> map) {
        Validate.notNull(context, "context");
        Validate.notNull(uuid, "callId");
        Validate.containsNoNulls(map.values(), "imageAttachmentFiles");
        Validate.containsNoNullOrEmpty(map.keySet(), "imageAttachmentFiles");
        addAttachments(context, uuid, map, new ProcessAttachment<File>() { // from class: com.facebook.NativeAppCallAttachmentStore.2
            @Override // com.facebook.NativeAppCallAttachmentStore.ProcessAttachment
            public void processAttachment(File file, File file2) throws Throwable {
                FileInputStream fileInputStream;
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                try {
                    fileInputStream = new FileInputStream(file);
                    try {
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int i = fileInputStream.read(bArr);
                            if (i > 0) {
                                fileOutputStream.write(bArr, 0, i);
                            } else {
                                Utility.closeQuietly(fileOutputStream);
                                Utility.closeQuietly(fileInputStream);
                                return;
                            }
                        }
                    } catch (Throwable th) {
                        th = th;
                        Utility.closeQuietly(fileOutputStream);
                        Utility.closeQuietly(fileInputStream);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    fileInputStream = null;
                }
            }
        });
    }

    private <T> void addAttachments(Context context, UUID uuid, Map<String, T> map, ProcessAttachment<T> processAttachment) {
        if (map.size() != 0) {
            if (attachmentsDirectory == null) {
                cleanupAllAttachments(context);
            }
            ensureAttachmentsDirectoryExists(context);
            ArrayList arrayList = new ArrayList();
            try {
                for (Map.Entry<String, T> entry : map.entrySet()) {
                    String key = entry.getKey();
                    T value = entry.getValue();
                    File attachmentFile = getAttachmentFile(uuid, key, true);
                    arrayList.add(attachmentFile);
                    processAttachment.processAttachment(value, attachmentFile);
                }
            } catch (IOException e) {
                Log.e(TAG, "Got unexpected exception:" + e);
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    try {
                        ((File) it.next()).delete();
                    } catch (Exception e2) {
                    }
                }
                throw new FacebookException(e);
            }
        }
    }

    public void cleanupAttachmentsForCall(Context context, UUID uuid) {
        Utility.deleteDirectory(getAttachmentsDirectoryForCall(uuid, false));
    }

    @Override // com.facebook.NativeAppCallContentProvider.AttachmentDataSource
    public File openAttachment(UUID uuid, String str) throws FileNotFoundException {
        if (Utility.isNullOrEmpty(str) || uuid == null) {
            throw new FileNotFoundException();
        }
        try {
            return getAttachmentFile(uuid, str, false);
        } catch (IOException e) {
            throw new FileNotFoundException();
        }
    }

    static synchronized File getAttachmentsDirectory(Context context) {
        if (attachmentsDirectory == null) {
            attachmentsDirectory = new File(context.getCacheDir(), ATTACHMENTS_DIR_NAME);
        }
        return attachmentsDirectory;
    }

    File ensureAttachmentsDirectoryExists(Context context) {
        File attachmentsDirectory2 = getAttachmentsDirectory(context);
        attachmentsDirectory2.mkdirs();
        return attachmentsDirectory2;
    }

    File getAttachmentsDirectoryForCall(UUID uuid, boolean z) {
        if (attachmentsDirectory == null) {
            return null;
        }
        File file = new File(attachmentsDirectory, uuid.toString());
        if (z && !file.exists()) {
            file.mkdirs();
            return file;
        }
        return file;
    }

    File getAttachmentFile(UUID uuid, String str, boolean z) {
        File attachmentsDirectoryForCall = getAttachmentsDirectoryForCall(uuid, z);
        if (attachmentsDirectoryForCall == null) {
            return null;
        }
        try {
            return new File(attachmentsDirectoryForCall, URLEncoder.encode(str, XMLStreamWriterImpl.UTF_8));
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    void cleanupAllAttachments(Context context) {
        Utility.deleteDirectory(getAttachmentsDirectory(context));
    }
}
