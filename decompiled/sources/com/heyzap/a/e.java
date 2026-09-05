package com.heyzap.a;

import android.content.Context;
import android.content.pm.PackageInfo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

/* JADX INFO: compiled from: HeyzapCookies.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static String f741a = null;

    protected static synchronized String a(Context context) {
        String str;
        f741a = c(context);
        if (f741a != null) {
            str = f741a;
        } else {
            try {
                f741a = b(context);
                if (f741a == null) {
                    f741a = "";
                }
                b(context, f741a);
                str = f741a;
            } catch (Exception e) {
                str = f741a;
            }
        }
        return str;
    }

    private static String b(Context context) {
        String strA;
        for (PackageInfo packageInfo : context.getPackageManager().getInstalledPackages(0)) {
            if (packageInfo != null && (strA = a(context, packageInfo)) != null) {
                return strA;
            }
        }
        return null;
    }

    static void a(Context context, String str) {
        for (PackageInfo packageInfo : context.getPackageManager().getInstalledPackages(0)) {
            if (packageInfo != null && a(context, packageInfo) != null) {
                a(context, packageInfo, str);
            }
        }
    }

    private static String c(Context context) {
        return a(new File(context.getFilesDir(), "Usr.hz"));
    }

    private static String a(Context context, PackageInfo packageInfo) {
        return a(new File(c(context, packageInfo), "Usr.hz"));
    }

    private static String a(File file) throws Throwable {
        InputStreamReader inputStreamReader;
        FileInputStream fileInputStream;
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2;
        InputStreamReader inputStreamReader2;
        FileInputStream fileInputStream2;
        String line;
        BufferedReader bufferedReader3 = null;
        try {
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    inputStreamReader = new InputStreamReader(fileInputStream);
                    try {
                        bufferedReader = new BufferedReader(inputStreamReader);
                        try {
                            line = bufferedReader.readLine();
                            if (line == null) {
                                line = "";
                            }
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (Exception e) {
                                }
                            }
                            if (inputStreamReader != null) {
                                try {
                                    inputStreamReader.close();
                                } catch (Exception e2) {
                                }
                            }
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (Exception e3) {
                                }
                            }
                        } catch (FileNotFoundException e4) {
                            bufferedReader2 = bufferedReader;
                            inputStreamReader2 = inputStreamReader;
                            fileInputStream2 = fileInputStream;
                            if (bufferedReader2 != null) {
                                try {
                                    bufferedReader2.close();
                                } catch (Exception e5) {
                                }
                            }
                            if (inputStreamReader2 != null) {
                                try {
                                    inputStreamReader2.close();
                                } catch (Exception e6) {
                                }
                            }
                            if (fileInputStream2 != null) {
                                try {
                                    fileInputStream2.close();
                                } catch (Exception e7) {
                                }
                            }
                            return null;
                        } catch (Exception e8) {
                            e = e8;
                            e.printStackTrace();
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (Exception e9) {
                                }
                            }
                            if (inputStreamReader != null) {
                                try {
                                    inputStreamReader.close();
                                } catch (Exception e10) {
                                }
                            }
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (Exception e11) {
                                }
                            }
                            line = null;
                        }
                    } catch (FileNotFoundException e12) {
                        bufferedReader2 = null;
                        inputStreamReader2 = inputStreamReader;
                        fileInputStream2 = fileInputStream;
                    } catch (Exception e13) {
                        e = e13;
                        bufferedReader = null;
                    } catch (Throwable th) {
                        th = th;
                        if (bufferedReader3 != null) {
                            try {
                                bufferedReader3.close();
                            } catch (Exception e14) {
                            }
                        }
                        if (inputStreamReader != null) {
                            try {
                                inputStreamReader.close();
                            } catch (Exception e15) {
                            }
                        }
                        if (fileInputStream == null) {
                            throw th;
                        }
                        try {
                            fileInputStream.close();
                            throw th;
                        } catch (Exception e16) {
                            throw th;
                        }
                    }
                } catch (FileNotFoundException e17) {
                    bufferedReader2 = null;
                    inputStreamReader2 = null;
                    fileInputStream2 = fileInputStream;
                } catch (Exception e18) {
                    e = e18;
                    bufferedReader = null;
                    inputStreamReader = null;
                } catch (Throwable th2) {
                    th = th2;
                    inputStreamReader = null;
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedReader3 = bufferedReader;
            }
        } catch (FileNotFoundException e19) {
            bufferedReader2 = null;
            inputStreamReader2 = null;
            fileInputStream2 = null;
        } catch (Exception e20) {
            e = e20;
            bufferedReader = null;
            inputStreamReader = null;
            fileInputStream = null;
        } catch (Throwable th4) {
            th = th4;
            inputStreamReader = null;
            fileInputStream = null;
        }
        return line;
    }

    private static void a(Context context, PackageInfo packageInfo, String str) {
        Context contextB = b(context, packageInfo);
        if (contextB != null) {
            b(contextB, str);
        }
    }

    private static void b(Context context, String str) {
        if (str != null) {
            FileOutputStream fileOutputStreamOpenFileOutput = null;
            try {
                fileOutputStreamOpenFileOutput = context.openFileOutput("Usr.hz", 3);
                fileOutputStreamOpenFileOutput.write(str.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (fileOutputStreamOpenFileOutput != null) {
                    try {
                        fileOutputStreamOpenFileOutput.close();
                    } catch (Exception e2) {
                    }
                }
            }
        }
    }

    private static Context b(Context context, PackageInfo packageInfo) {
        try {
            return context.createPackageContext(packageInfo.packageName, 4);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String c(Context context, PackageInfo packageInfo) {
        return packageInfo.applicationInfo.dataDir + "/files";
    }
}
