package org.apache.commons.logging;

import java.lang.reflect.Constructor;
import java.util.Hashtable;
import org.apache.commons.logging.impl.LogFactoryImpl;
import org.apache.commons.logging.impl.NoOpLog;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class LogSource {
    protected static boolean jdk14IsAvailable;
    protected static boolean log4jIsAvailable;
    protected static Hashtable logs = new Hashtable();
    protected static Constructor logImplctor = null;

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x0046 -> B:52:0x001a). Please report as a decompilation issue!!! */
    static {
        String property = null;
        log4jIsAvailable = false;
        jdk14IsAvailable = false;
        try {
            if (Class.forName("org.apache.log4j.Logger") != null) {
                log4jIsAvailable = true;
            } else {
                log4jIsAvailable = false;
            }
        } catch (Throwable th) {
            log4jIsAvailable = false;
        }
        try {
            if (Class.forName("java.util.logging.Logger") != null && Class.forName("org.apache.commons.logging.impl.Jdk14Logger") != null) {
                jdk14IsAvailable = true;
            } else {
                jdk14IsAvailable = false;
            }
        } catch (Throwable th2) {
            jdk14IsAvailable = false;
        }
        try {
            property = System.getProperty("org.apache.commons.logging.log");
            if (property == null) {
                property = System.getProperty(LogFactoryImpl.LOG_PROPERTY);
            }
        } catch (Throwable th3) {
        }
        if (property != null) {
            try {
                setLogImplementation(property);
                return;
            } catch (Throwable th4) {
                try {
                    setLogImplementation("org.apache.commons.logging.impl.NoOpLog");
                    return;
                } catch (Throwable th5) {
                    return;
                }
            }
        }
        try {
            if (log4jIsAvailable) {
                setLogImplementation("org.apache.commons.logging.impl.Log4JLogger");
            } else if (jdk14IsAvailable) {
                setLogImplementation("org.apache.commons.logging.impl.Jdk14Logger");
            } else {
                setLogImplementation("org.apache.commons.logging.impl.NoOpLog");
            }
        } catch (Throwable th6) {
            try {
                setLogImplementation("org.apache.commons.logging.impl.NoOpLog");
            } catch (Throwable th7) {
            }
        }
    }

    private LogSource() {
    }

    public static void setLogImplementation(String str) {
        try {
            logImplctor = Class.forName(str).getConstructor("".getClass());
        } catch (Throwable th) {
            logImplctor = null;
        }
    }

    public static void setLogImplementation(Class cls) {
        logImplctor = cls.getConstructor("".getClass());
    }

    public static Log getInstance(String str) {
        Log log = (Log) logs.get(str);
        if (log == null) {
            Log logMakeNewLogInstance = makeNewLogInstance(str);
            logs.put(str, logMakeNewLogInstance);
            return logMakeNewLogInstance;
        }
        return log;
    }

    public static Log getInstance(Class cls) {
        return getInstance(cls.getName());
    }

    public static Log makeNewLogInstance(String str) {
        Log log;
        try {
            log = (Log) logImplctor.newInstance(str);
        } catch (Throwable th) {
            log = null;
        }
        if (log == null) {
            return new NoOpLog(str);
        }
        return log;
    }

    public static String[] getLogNames() {
        return (String[]) logs.keySet().toArray(new String[logs.size()]);
    }
}
