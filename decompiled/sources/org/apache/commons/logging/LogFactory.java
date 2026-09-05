package org.apache.commons.logging;

import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class LogFactory {
    public static final String DIAGNOSTICS_DEST_PROPERTY = "org.apache.commons.logging.diagnostics.dest";
    public static final String FACTORY_DEFAULT = "org.apache.commons.logging.impl.LogFactoryImpl";
    public static final String FACTORY_PROPERTIES = "commons-logging.properties";
    public static final String FACTORY_PROPERTY = "org.apache.commons.logging.LogFactory";
    public static final String HASHTABLE_IMPLEMENTATION_PROPERTY = "org.apache.commons.logging.LogFactory.HashtableImpl";
    public static final String PRIORITY_KEY = "priority";
    protected static final String SERVICE_ID = "META-INF/services/org.apache.commons.logging.LogFactory";
    public static final String TCCL_KEY = "use_tccl";
    private static final String WEAK_HASHTABLE_CLASSNAME = "org.apache.commons.logging.impl.WeakHashtable";
    static /* synthetic */ Class class$java$lang$Thread;
    static /* synthetic */ Class class$org$apache$commons$logging$LogFactory;
    private static String diagnosticPrefix;
    protected static Hashtable factories;
    private static ClassLoader thisClassLoader;
    private static PrintStream diagnosticsStream = null;
    protected static LogFactory nullClassLoaderFactory = null;

    public abstract Object getAttribute(String str);

    public abstract String[] getAttributeNames();

    public abstract Log getInstance(Class cls);

    public abstract Log getInstance(String str);

    public abstract void release();

    public abstract void removeAttribute(String str);

    public abstract void setAttribute(String str, Object obj);

    static {
        Class clsClass$;
        Class clsClass$2;
        factories = null;
        if (class$org$apache$commons$logging$LogFactory == null) {
            clsClass$ = class$(FACTORY_PROPERTY);
            class$org$apache$commons$logging$LogFactory = clsClass$;
        } else {
            clsClass$ = class$org$apache$commons$logging$LogFactory;
        }
        thisClassLoader = getClassLoader(clsClass$);
        initDiagnostics();
        if (class$org$apache$commons$logging$LogFactory == null) {
            clsClass$2 = class$(FACTORY_PROPERTY);
            class$org$apache$commons$logging$LogFactory = clsClass$2;
        } else {
            clsClass$2 = class$org$apache$commons$logging$LogFactory;
        }
        logClassLoaderEnvironment(clsClass$2);
        factories = createFactoryStore();
        if (isDiagnosticsEnabled()) {
            logDiagnostic("BOOTSTRAP COMPLETED");
        }
    }

    protected LogFactory() {
    }

    private static final Hashtable createFactoryStore() {
        String systemProperty;
        Hashtable hashtable;
        try {
            systemProperty = getSystemProperty(HASHTABLE_IMPLEMENTATION_PROPERTY, null);
        } catch (SecurityException e) {
            systemProperty = null;
        }
        String str = systemProperty == null ? WEAK_HASHTABLE_CLASSNAME : systemProperty;
        try {
            hashtable = (Hashtable) Class.forName(str).newInstance();
        } catch (Throwable th) {
            if (WEAK_HASHTABLE_CLASSNAME.equals(str)) {
                hashtable = null;
            } else if (isDiagnosticsEnabled()) {
                logDiagnostic("[ERROR] LogFactory: Load of custom hashtable failed");
                hashtable = null;
            } else {
                System.err.println("[ERROR] LogFactory: Load of custom hashtable failed");
                hashtable = null;
            }
        }
        if (hashtable == null) {
            return new Hashtable();
        }
        return hashtable;
    }

    private static String trim(String str) {
        if (str == null) {
            return null;
        }
        return str.trim();
    }

    public static LogFactory getFactory() {
        LogFactory logFactoryNewFactory;
        BufferedReader bufferedReader;
        String property;
        ClassLoader contextClassLoaderInternal = getContextClassLoaderInternal();
        if (contextClassLoaderInternal == null && isDiagnosticsEnabled()) {
            logDiagnostic("Context classloader is null.");
        }
        LogFactory cachedFactory = getCachedFactory(contextClassLoaderInternal);
        if (cachedFactory == null) {
            if (isDiagnosticsEnabled()) {
                logDiagnostic(new StringBuffer().append("[LOOKUP] LogFactory implementation requested for the first time for context classloader ").append(objectId(contextClassLoaderInternal)).toString());
                logHierarchy("[LOOKUP] ", contextClassLoaderInternal);
            }
            Properties configurationFile = getConfigurationFile(contextClassLoaderInternal, FACTORY_PROPERTIES);
            ClassLoader classLoader = (configurationFile == null || (property = configurationFile.getProperty(TCCL_KEY)) == null || Boolean.valueOf(property).booleanValue()) ? contextClassLoaderInternal : thisClassLoader;
            if (isDiagnosticsEnabled()) {
                logDiagnostic("[LOOKUP] Looking for system property [org.apache.commons.logging.LogFactory] to define the LogFactory subclass to use...");
            }
            try {
                String systemProperty = getSystemProperty(FACTORY_PROPERTY, null);
                if (systemProperty != null) {
                    if (isDiagnosticsEnabled()) {
                        logDiagnostic(new StringBuffer().append("[LOOKUP] Creating an instance of LogFactory class '").append(systemProperty).append("' as specified by system property ").append(FACTORY_PROPERTY).toString());
                    }
                    cachedFactory = newFactory(systemProperty, classLoader, contextClassLoaderInternal);
                } else if (isDiagnosticsEnabled()) {
                    logDiagnostic("[LOOKUP] No system property [org.apache.commons.logging.LogFactory] defined.");
                }
            } catch (SecurityException e) {
                if (isDiagnosticsEnabled()) {
                    logDiagnostic(new StringBuffer().append("[LOOKUP] A security exception occurred while trying to create an instance of the custom factory class: [").append(trim(e.getMessage())).append("]. Trying alternative implementations...").toString());
                }
            } catch (RuntimeException e2) {
                if (isDiagnosticsEnabled()) {
                    logDiagnostic(new StringBuffer().append("[LOOKUP] An exception occurred while trying to create an instance of the custom factory class: [").append(trim(e2.getMessage())).append("] as specified by a system property.").toString());
                }
                throw e2;
            }
            if (cachedFactory == null) {
                if (isDiagnosticsEnabled()) {
                    logDiagnostic("[LOOKUP] Looking for a resource file of name [META-INF/services/org.apache.commons.logging.LogFactory] to define the LogFactory subclass to use...");
                }
                try {
                    InputStream resourceAsStream = getResourceAsStream(contextClassLoaderInternal, SERVICE_ID);
                    if (resourceAsStream != null) {
                        try {
                            bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream, XMLStreamWriterImpl.UTF_8));
                        } catch (UnsupportedEncodingException e3) {
                            bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream));
                        }
                        String line = bufferedReader.readLine();
                        bufferedReader.close();
                        if (line != null && !"".equals(line)) {
                            if (isDiagnosticsEnabled()) {
                                logDiagnostic(new StringBuffer().append("[LOOKUP]  Creating an instance of LogFactory class ").append(line).append(" as specified by file '").append(SERVICE_ID).append("' which was present in the path of the context").append(" classloader.").toString());
                            }
                            cachedFactory = newFactory(line, classLoader, contextClassLoaderInternal);
                        }
                    } else if (isDiagnosticsEnabled()) {
                        logDiagnostic("[LOOKUP] No resource file with name 'META-INF/services/org.apache.commons.logging.LogFactory' found.");
                    }
                } catch (Exception e4) {
                    if (isDiagnosticsEnabled()) {
                        logDiagnostic(new StringBuffer().append("[LOOKUP] A security exception occurred while trying to create an instance of the custom factory class: [").append(trim(e4.getMessage())).append("]. Trying alternative implementations...").toString());
                    }
                }
            }
            if (cachedFactory == null) {
                if (configurationFile != null) {
                    if (isDiagnosticsEnabled()) {
                        logDiagnostic("[LOOKUP] Looking in properties file for entry with key 'org.apache.commons.logging.LogFactory' to define the LogFactory subclass to use...");
                    }
                    String property2 = configurationFile.getProperty(FACTORY_PROPERTY);
                    if (property2 != null) {
                        if (isDiagnosticsEnabled()) {
                            logDiagnostic(new StringBuffer().append("[LOOKUP] Properties file specifies LogFactory subclass '").append(property2).append("'").toString());
                        }
                        logFactoryNewFactory = newFactory(property2, classLoader, contextClassLoaderInternal);
                    } else {
                        if (isDiagnosticsEnabled()) {
                            logDiagnostic("[LOOKUP] Properties file has no entry specifying LogFactory subclass.");
                        }
                        logFactoryNewFactory = cachedFactory;
                    }
                    cachedFactory = logFactoryNewFactory;
                } else if (isDiagnosticsEnabled()) {
                    logDiagnostic("[LOOKUP] No properties file available to determine LogFactory subclass from..");
                }
            }
            if (cachedFactory == null) {
                if (isDiagnosticsEnabled()) {
                    logDiagnostic("[LOOKUP] Loading the default LogFactory implementation 'org.apache.commons.logging.impl.LogFactoryImpl' via the same classloader that loaded this LogFactory class (ie not looking in the context classloader).");
                }
                cachedFactory = newFactory(FACTORY_DEFAULT, thisClassLoader, contextClassLoaderInternal);
            }
            if (cachedFactory != null) {
                cacheFactory(contextClassLoaderInternal, cachedFactory);
                if (configurationFile != null) {
                    Enumeration<?> enumerationPropertyNames = configurationFile.propertyNames();
                    while (enumerationPropertyNames.hasMoreElements()) {
                        String str = (String) enumerationPropertyNames.nextElement();
                        cachedFactory.setAttribute(str, configurationFile.getProperty(str));
                    }
                }
            }
        }
        return cachedFactory;
    }

    public static Log getLog(Class cls) {
        return getFactory().getInstance(cls);
    }

    public static Log getLog(String str) {
        return getFactory().getInstance(str);
    }

    public static void release(ClassLoader classLoader) {
        if (isDiagnosticsEnabled()) {
            logDiagnostic(new StringBuffer().append("Releasing factory for classloader ").append(objectId(classLoader)).toString());
        }
        synchronized (factories) {
            try {
                if (classLoader == null) {
                    if (nullClassLoaderFactory != null) {
                        nullClassLoaderFactory.release();
                        nullClassLoaderFactory = null;
                    }
                } else {
                    LogFactory logFactory = (LogFactory) factories.get(classLoader);
                    if (logFactory != null) {
                        logFactory.release();
                        factories.remove(classLoader);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static void releaseAll() {
        if (isDiagnosticsEnabled()) {
            logDiagnostic("Releasing factory for all classloaders.");
        }
        synchronized (factories) {
            Enumeration enumerationElements = factories.elements();
            while (enumerationElements.hasMoreElements()) {
                ((LogFactory) enumerationElements.nextElement()).release();
            }
            factories.clear();
            if (nullClassLoaderFactory != null) {
                nullClassLoaderFactory.release();
                nullClassLoaderFactory = null;
            }
        }
    }

    protected static ClassLoader getClassLoader(Class cls) {
        try {
            return cls.getClassLoader();
        } catch (SecurityException e) {
            if (isDiagnosticsEnabled()) {
                logDiagnostic(new StringBuffer().append("Unable to get classloader for class '").append(cls).append("' due to security restrictions - ").append(e.getMessage()).toString());
            }
            throw e;
        }
    }

    protected static ClassLoader getContextClassLoader() {
        return directGetContextClassLoader();
    }

    private static ClassLoader getContextClassLoaderInternal() {
        return (ClassLoader) AccessController.doPrivileged(new PrivilegedAction() { // from class: org.apache.commons.logging.LogFactory.1
            @Override // java.security.PrivilegedAction
            public Object run() {
                return LogFactory.directGetContextClassLoader();
            }
        });
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    protected static ClassLoader directGetContextClassLoader() {
        Class clsClass$;
        Class cls;
        try {
            if (class$java$lang$Thread == null) {
                Class clsClass$2 = class$("java.lang.Thread");
                class$java$lang$Thread = clsClass$2;
                cls = clsClass$2;
            } else {
                cls = class$java$lang$Thread;
            }
            try {
                return (ClassLoader) cls.getMethod("getContextClassLoader", (Class[]) null).invoke(Thread.currentThread(), (Object[]) null);
            } catch (IllegalAccessException e) {
                throw new LogConfigurationException("Unexpected IllegalAccessException", e);
            } catch (InvocationTargetException e2) {
                if (e2.getTargetException() instanceof SecurityException) {
                    return null;
                }
                throw new LogConfigurationException("Unexpected InvocationTargetException", e2.getTargetException());
            }
        } catch (NoSuchMethodException e3) {
            if (class$org$apache$commons$logging$LogFactory == null) {
                clsClass$ = class$(FACTORY_PROPERTY);
                class$org$apache$commons$logging$LogFactory = clsClass$;
            } else {
                clsClass$ = class$org$apache$commons$logging$LogFactory;
            }
            return getClassLoader(clsClass$);
        }
    }

    private static LogFactory getCachedFactory(ClassLoader classLoader) {
        if (classLoader == null) {
            return nullClassLoaderFactory;
        }
        return (LogFactory) factories.get(classLoader);
    }

    private static void cacheFactory(ClassLoader classLoader, LogFactory logFactory) {
        if (logFactory != null) {
            if (classLoader == null) {
                nullClassLoaderFactory = logFactory;
            } else {
                factories.put(classLoader, logFactory);
            }
        }
    }

    protected static LogFactory newFactory(final String str, final ClassLoader classLoader, ClassLoader classLoader2) {
        Object objDoPrivileged = AccessController.doPrivileged((PrivilegedAction<Object>) new PrivilegedAction() { // from class: org.apache.commons.logging.LogFactory.2
            @Override // java.security.PrivilegedAction
            public Object run() {
                return LogFactory.createFactory(str, classLoader);
            }
        });
        if (objDoPrivileged instanceof LogConfigurationException) {
            LogConfigurationException logConfigurationException = (LogConfigurationException) objDoPrivileged;
            if (isDiagnosticsEnabled()) {
                logDiagnostic(new StringBuffer().append("An error occurred while loading the factory class:").append(logConfigurationException.getMessage()).toString());
                throw logConfigurationException;
            }
            throw logConfigurationException;
        }
        if (isDiagnosticsEnabled()) {
            logDiagnostic(new StringBuffer().append("Created object ").append(objectId(objDoPrivileged)).append(" to manage classloader ").append(objectId(classLoader2)).toString());
        }
        return (LogFactory) objDoPrivileged;
    }

    protected static LogFactory newFactory(String str, ClassLoader classLoader) {
        return newFactory(str, classLoader, null);
    }

    protected static Object createFactory(String str, ClassLoader classLoader) {
        Class<?> cls;
        Exception exc;
        Class clsClass$;
        Class clsClass$2;
        String string;
        Class clsClass$3;
        Class clsClass$4;
        Class<?> clsLoadClass = null;
        try {
            if (classLoader != null) {
                try {
                    try {
                        try {
                            clsLoadClass = classLoader.loadClass(str);
                            if (class$org$apache$commons$logging$LogFactory == null) {
                                clsClass$3 = class$(FACTORY_PROPERTY);
                                class$org$apache$commons$logging$LogFactory = clsClass$3;
                            } else {
                                clsClass$3 = class$org$apache$commons$logging$LogFactory;
                            }
                            if (clsClass$3.isAssignableFrom(clsLoadClass)) {
                                if (isDiagnosticsEnabled()) {
                                    logDiagnostic(new StringBuffer().append("Loaded class ").append(clsLoadClass.getName()).append(" from classloader ").append(objectId(classLoader)).toString());
                                }
                            } else if (isDiagnosticsEnabled()) {
                                StringBuffer stringBufferAppend = new StringBuffer().append("Factory class ").append(clsLoadClass.getName()).append(" loaded from classloader ").append(objectId(clsLoadClass.getClassLoader())).append(" does not extend '");
                                if (class$org$apache$commons$logging$LogFactory == null) {
                                    clsClass$4 = class$(FACTORY_PROPERTY);
                                    class$org$apache$commons$logging$LogFactory = clsClass$4;
                                } else {
                                    clsClass$4 = class$org$apache$commons$logging$LogFactory;
                                }
                                logDiagnostic(stringBufferAppend.append(clsClass$4.getName()).append("' as loaded by this classloader.").toString());
                                logHierarchy("[BAD CL TREE] ", classLoader);
                            }
                            return (LogFactory) clsLoadClass.newInstance();
                        } catch (Exception e) {
                            cls = null;
                            exc = e;
                            if (isDiagnosticsEnabled()) {
                                logDiagnostic("Unable to create LogFactory instance.");
                            }
                            if (cls != null) {
                                if (class$org$apache$commons$logging$LogFactory == null) {
                                    clsClass$ = class$(FACTORY_PROPERTY);
                                    class$org$apache$commons$logging$LogFactory = clsClass$;
                                } else {
                                    clsClass$ = class$org$apache$commons$logging$LogFactory;
                                }
                                if (!clsClass$.isAssignableFrom(cls)) {
                                    return new LogConfigurationException("The chosen LogFactory implementation does not extend LogFactory. Please check your configuration.", exc);
                                }
                            }
                            return new LogConfigurationException(exc);
                        }
                    } catch (NoClassDefFoundError e2) {
                        if (classLoader == thisClassLoader) {
                            if (isDiagnosticsEnabled()) {
                                logDiagnostic(new StringBuffer().append("Class '").append(str).append("' cannot be loaded").append(" via classloader ").append(objectId(classLoader)).append(" - it depends on some other class that cannot").append(" be found.").toString());
                            }
                            throw e2;
                        }
                    }
                } catch (ClassCastException e3) {
                    if (classLoader == thisClassLoader) {
                        boolean zImplementsLogFactory = implementsLogFactory(null);
                        StringBuffer stringBufferAppend2 = new StringBuffer().append("The application has specified that a custom LogFactory implementation should be used but Class '").append(str).append("' cannot be converted to '");
                        if (class$org$apache$commons$logging$LogFactory == null) {
                            clsClass$2 = class$(FACTORY_PROPERTY);
                            class$org$apache$commons$logging$LogFactory = clsClass$2;
                        } else {
                            clsClass$2 = class$org$apache$commons$logging$LogFactory;
                        }
                        String string2 = stringBufferAppend2.append(clsClass$2.getName()).append("'. ").toString();
                        if (zImplementsLogFactory) {
                            string = new StringBuffer().append(string2).append("The conflict is caused by the presence of multiple LogFactory classes in incompatible classloaders. ").append("Background can be found in http://commons.apache.org/logging/tech.html. ").append("If you have not explicitly specified a custom LogFactory then it is likely that ").append("the container has set one without your knowledge. ").append("In this case, consider using the commons-logging-adapters.jar file or ").append("specifying the standard LogFactory from the command line. ").toString();
                        } else {
                            string = new StringBuffer().append(string2).append("Please check the custom implementation. ").toString();
                        }
                        String string3 = new StringBuffer().append(string).append("Help can be found @http://commons.apache.org/logging/troubleshooting.html.").toString();
                        if (isDiagnosticsEnabled()) {
                            logDiagnostic(string3);
                        }
                        throw new ClassCastException(string3);
                    }
                } catch (ClassNotFoundException e4) {
                    if (classLoader == thisClassLoader) {
                        if (isDiagnosticsEnabled()) {
                            logDiagnostic(new StringBuffer().append("Unable to locate any class called '").append(str).append("' via classloader ").append(objectId(classLoader)).toString());
                        }
                        throw e4;
                    }
                }
            }
            if (isDiagnosticsEnabled()) {
                logDiagnostic(new StringBuffer().append("Unable to load factory class via classloader ").append(objectId(classLoader)).append(" - trying the classloader associated with this LogFactory.").toString());
            }
            return (LogFactory) Class.forName(str).newInstance();
        } catch (Exception e5) {
            cls = clsLoadClass;
            exc = e5;
        }
    }

    private static boolean implementsLogFactory(Class cls) {
        boolean zIsAssignableFrom = false;
        if (cls != null) {
            try {
                ClassLoader classLoader = cls.getClassLoader();
                if (classLoader == null) {
                    logDiagnostic("[CUSTOM LOG FACTORY] was loaded by the boot classloader");
                } else {
                    logHierarchy("[CUSTOM LOG FACTORY] ", classLoader);
                    zIsAssignableFrom = Class.forName(FACTORY_PROPERTY, false, classLoader).isAssignableFrom(cls);
                    if (zIsAssignableFrom) {
                        logDiagnostic(new StringBuffer().append("[CUSTOM LOG FACTORY] ").append(cls.getName()).append(" implements LogFactory but was loaded by an incompatible classloader.").toString());
                    } else {
                        logDiagnostic(new StringBuffer().append("[CUSTOM LOG FACTORY] ").append(cls.getName()).append(" does not implement LogFactory.").toString());
                    }
                }
            } catch (ClassNotFoundException e) {
                logDiagnostic("[CUSTOM LOG FACTORY] LogFactory class cannot be loaded by classloader which loaded the custom LogFactory implementation. Is the custom factory in the right classloader?");
            } catch (LinkageError e2) {
                logDiagnostic(new StringBuffer().append("[CUSTOM LOG FACTORY] LinkageError thrown whilst trying to determine whether the compatibility was caused by a classloader conflict: ").append(e2.getMessage()).toString());
            } catch (SecurityException e3) {
                logDiagnostic(new StringBuffer().append("[CUSTOM LOG FACTORY] SecurityException thrown whilst trying to determine whether the compatibility was caused by a classloader conflict: ").append(e3.getMessage()).toString());
            }
        }
        return zIsAssignableFrom;
    }

    private static InputStream getResourceAsStream(final ClassLoader classLoader, final String str) {
        return (InputStream) AccessController.doPrivileged(new PrivilegedAction() { // from class: org.apache.commons.logging.LogFactory.3
            @Override // java.security.PrivilegedAction
            public Object run() {
                return classLoader != null ? classLoader.getResourceAsStream(str) : ClassLoader.getSystemResourceAsStream(str);
            }
        });
    }

    private static Enumeration getResources(final ClassLoader classLoader, final String str) {
        return (Enumeration) AccessController.doPrivileged(new PrivilegedAction() { // from class: org.apache.commons.logging.LogFactory.4
            @Override // java.security.PrivilegedAction
            public Object run() {
                Enumeration<URL> systemResources = null;
                try {
                    if (classLoader != null) {
                        systemResources = classLoader.getResources(str);
                    } else {
                        systemResources = ClassLoader.getSystemResources(str);
                    }
                } catch (IOException e) {
                    if (LogFactory.isDiagnosticsEnabled()) {
                        LogFactory.logDiagnostic(new StringBuffer().append("Exception while trying to find configuration file ").append(str).append(":").append(e.getMessage()).toString());
                    }
                } catch (NoSuchMethodError e2) {
                }
                return systemResources;
            }
        });
    }

    private static Properties getProperties(final URL url) {
        return (Properties) AccessController.doPrivileged(new PrivilegedAction() { // from class: org.apache.commons.logging.LogFactory.5
            @Override // java.security.PrivilegedAction
            public Object run() {
                try {
                    InputStream inputStreamOpenStream = url.openStream();
                    if (inputStreamOpenStream != null) {
                        Properties properties = new Properties();
                        properties.load(inputStreamOpenStream);
                        inputStreamOpenStream.close();
                        return properties;
                    }
                } catch (IOException e) {
                    if (LogFactory.isDiagnosticsEnabled()) {
                        LogFactory.logDiagnostic(new StringBuffer().append("Unable to read URL ").append(url).toString());
                    }
                }
                return null;
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:39:0x0118 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:40:0x011a  */
    /* JADX WARN: Code duplicated, block: B:41:0x0138  */
    /* JADX WARN: Code duplicated, block: B:52:? A[RETURN, SYNTHETIC] */
    private static final Properties getConfigurationFile(ClassLoader classLoader, String str) {
        Properties properties;
        double d;
        Properties properties2 = null;
        double d2 = 0.0d;
        URL url = null;
        try {
            Enumeration resources = getResources(classLoader, str);
            if (resources == null) {
                return null;
            }
            while (resources.hasMoreElements()) {
                URL url2 = (URL) resources.nextElement();
                Properties properties3 = getProperties(url2);
                if (properties3 == null) {
                    url2 = url;
                    properties = properties2;
                    d = d2;
                } else if (properties2 == null) {
                    try {
                        String property = properties3.getProperty(PRIORITY_KEY);
                        d = 0.0d;
                        if (property != null) {
                            d = Double.parseDouble(property);
                        }
                        if (isDiagnosticsEnabled()) {
                            logDiagnostic(new StringBuffer().append("[LOOKUP] Properties file found at '").append(url2).append("'").append(" with priority ").append(d).toString());
                        }
                        properties = properties3;
                    } catch (SecurityException e) {
                        url = url2;
                        properties2 = properties3;
                    }
                } else {
                    String property2 = properties3.getProperty(PRIORITY_KEY);
                    double d3 = 0.0d;
                    if (property2 != null) {
                        d3 = Double.parseDouble(property2);
                    }
                    if (d3 > d2) {
                        if (isDiagnosticsEnabled()) {
                            logDiagnostic(new StringBuffer().append("[LOOKUP] Properties file at '").append(url2).append("'").append(" with priority ").append(d3).append(" overrides file at '").append(url).append("'").append(" with priority ").append(d2).toString());
                        }
                        d = d3;
                        properties = properties3;
                    } else {
                        if (isDiagnosticsEnabled()) {
                            logDiagnostic(new StringBuffer().append("[LOOKUP] Properties file at '").append(url2).append("'").append(" with priority ").append(d3).append(" does not override file at '").append(url).append("'").append(" with priority ").append(d2).toString());
                        }
                        url2 = url;
                        properties = properties2;
                        d = d2;
                    }
                }
                d2 = d;
                properties2 = properties;
                url = url2;
            }
            if (isDiagnosticsEnabled()) {
                if (properties2 == null) {
                    logDiagnostic(new StringBuffer().append("[LOOKUP] No properties file of name '").append(str).append("' found.").toString());
                    return properties2;
                }
                logDiagnostic(new StringBuffer().append("[LOOKUP] Properties file of name '").append(str).append("' found at '").append(url).append('\"').toString());
                return properties2;
            }
            return properties2;
        } catch (SecurityException e2) {
        }
        if (isDiagnosticsEnabled()) {
            logDiagnostic("SecurityException thrown while trying to find/read config files.");
        }
        if (isDiagnosticsEnabled()) {
            if (properties2 == null) {
                logDiagnostic(new StringBuffer().append("[LOOKUP] No properties file of name '").append(str).append("' found.").toString());
                return properties2;
            }
            logDiagnostic(new StringBuffer().append("[LOOKUP] Properties file of name '").append(str).append("' found at '").append(url).append('\"').toString());
            return properties2;
        }
        return properties2;
    }

    private static String getSystemProperty(final String str, final String str2) {
        return (String) AccessController.doPrivileged(new PrivilegedAction() { // from class: org.apache.commons.logging.LogFactory.6
            @Override // java.security.PrivilegedAction
            public Object run() {
                return System.getProperty(str, str2);
            }
        });
    }

    private static void initDiagnostics() {
        String strObjectId;
        try {
            String systemProperty = getSystemProperty(DIAGNOSTICS_DEST_PROPERTY, null);
            if (systemProperty != null) {
                if (systemProperty.equals("STDOUT")) {
                    diagnosticsStream = System.out;
                } else if (systemProperty.equals("STDERR")) {
                    diagnosticsStream = System.err;
                } else {
                    try {
                        diagnosticsStream = new PrintStream(new FileOutputStream(systemProperty, true));
                    } catch (IOException e) {
                        return;
                    }
                }
                try {
                    ClassLoader classLoader = thisClassLoader;
                    if (thisClassLoader == null) {
                        strObjectId = "BOOTLOADER";
                    } else {
                        strObjectId = objectId(classLoader);
                    }
                } catch (SecurityException e2) {
                    strObjectId = "UNKNOWN";
                }
                diagnosticPrefix = new StringBuffer().append("[LogFactory from ").append(strObjectId).append("] ").toString();
            }
        } catch (SecurityException e3) {
        }
    }

    protected static boolean isDiagnosticsEnabled() {
        return diagnosticsStream != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void logDiagnostic(String str) {
        if (diagnosticsStream != null) {
            diagnosticsStream.print(diagnosticPrefix);
            diagnosticsStream.println(str);
            diagnosticsStream.flush();
        }
    }

    protected static final void logRawDiagnostic(String str) {
        if (diagnosticsStream != null) {
            diagnosticsStream.println(str);
            diagnosticsStream.flush();
        }
    }

    private static void logClassLoaderEnvironment(Class cls) {
        if (isDiagnosticsEnabled()) {
            try {
                logDiagnostic(new StringBuffer().append("[ENV] Extension directories (java.ext.dir): ").append(System.getProperty("java.ext.dir")).toString());
                logDiagnostic(new StringBuffer().append("[ENV] Application classpath (java.class.path): ").append(System.getProperty("java.class.path")).toString());
            } catch (SecurityException e) {
                logDiagnostic("[ENV] Security setting prevent interrogation of system classpaths.");
            }
            String name = cls.getName();
            try {
                ClassLoader classLoader = getClassLoader(cls);
                logDiagnostic(new StringBuffer().append("[ENV] Class ").append(name).append(" was loaded via classloader ").append(objectId(classLoader)).toString());
                logHierarchy(new StringBuffer().append("[ENV] Ancestry of classloader which loaded ").append(name).append(" is ").toString(), classLoader);
            } catch (SecurityException e2) {
                logDiagnostic(new StringBuffer().append("[ENV] Security forbids determining the classloader for ").append(name).toString());
            }
        }
    }

    private static void logHierarchy(String str, ClassLoader classLoader) {
        if (isDiagnosticsEnabled()) {
            if (classLoader != null) {
                logDiagnostic(new StringBuffer().append(str).append(objectId(classLoader)).append(" == '").append(classLoader.toString()).append("'").toString());
            }
            try {
                ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
                if (classLoader != null) {
                    StringBuffer stringBuffer = new StringBuffer(new StringBuffer().append(str).append("ClassLoader tree:").toString());
                    do {
                        stringBuffer.append(objectId(classLoader));
                        if (classLoader == systemClassLoader) {
                            stringBuffer.append(" (SYSTEM) ");
                        }
                        try {
                            classLoader = classLoader.getParent();
                            stringBuffer.append(" --> ");
                        } catch (SecurityException e) {
                            stringBuffer.append(" --> SECRET");
                        }
                    } while (classLoader != null);
                    stringBuffer.append("BOOT");
                    logDiagnostic(stringBuffer.toString());
                }
            } catch (SecurityException e2) {
                logDiagnostic(new StringBuffer().append(str).append("Security forbids determining the system classloader.").toString());
            }
        }
    }

    public static String objectId(Object obj) {
        return obj == null ? "null" : new StringBuffer().append(obj.getClass().getName()).append("@").append(System.identityHashCode(obj)).toString();
    }
}
