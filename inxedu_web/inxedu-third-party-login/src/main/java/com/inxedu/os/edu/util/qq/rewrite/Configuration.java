package com.inxedu.os.edu.util.qq.rewrite;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {
    private static Properties defaultProperty;
    private static boolean DALVIK;

    public Configuration() {
    }

    static void init() {
        defaultProperty = new Properties();
        defaultProperty.setProperty("qqConnect.debug", "false");
        defaultProperty.setProperty("qqConnect.clientURL", "https://graph.qq.com/user/get_user_info");
        defaultProperty.setProperty("qqConnect.http.useSSL", "false");
        defaultProperty.setProperty("qqConnect.http.connectionTimeout", "20000");
        defaultProperty.setProperty("qqConnect.http.readTimeout", "120000");
        defaultProperty.setProperty("qqConnect.http.retryCount", "3");
        defaultProperty.setProperty("qqConnect.http.retryIntervalSecs", "10");
        defaultProperty.setProperty("qqConnect.async.numThreads", "1");
        defaultProperty.setProperty("qqConnect.clientVersion", "2.0.0.0");

        try {
            Class.forName("dalvik.system.VMRuntime");
            defaultProperty.setProperty("qqConnect.dalvik", "true");
        } catch (ClassNotFoundException var2) {
            defaultProperty.setProperty("qqConnect.dalvik", "false");
        }

        DALVIK = getBoolean("qqConnect.dalvik");
        boolean var10000=true;


    }

    private static boolean loadProperties(Properties props, String path) {
        try {
            File ignore = new File(path);
            if(ignore.exists() && ignore.isFile()) {
                props.load(new FileInputStream(ignore));
                return true;
            }
        } catch (Exception var3) {
            ;
        }

        return false;
    }

    private static boolean loadProperties(Properties props, InputStream is) {
        try {
            props.load(is);
            return true;
        } catch (Exception var3) {
            return false;
        }
    }

    public static boolean isDalvik() {
        return DALVIK;
    }

    public static boolean useSSL() {
        return getBoolean("qqConnect.http.useSSL");
    }

    public static String getScheme() {
        return useSSL()?"https://":"http://";
    }

    public static String getCilentVersion() {
        return getProperty("qqConnect.clientVersion");
    }

    public static String getClientURL() {
        return getProperty("qqConnect.clientURL");
    }

    public static int getReadTimeout() {
        return getIntProperty("qqConnect.http.readTimeout");
    }

    public static int getRetryCount() {
        return getIntProperty("qqConnect.http.retryCount");
    }

    public static int getRetryIntervalSecs() {
        return getIntProperty("qqConnect.http.retryIntervalSecs");
    }

    public static String getUser() {
        return getProperty("qqConnect.user");
    }

    public static String getPassword() {
        return getProperty("qqConnect.password");
    }

    public static String getUserAgent() {
        return getProperty("qqConnect.http.userAgent");
    }

    public static String getOAuthConsumerKey() {
        return getProperty("qqConnect.oauth.consumerKey");
    }

    public static String getOAuthConsumerSecret() {
        return getProperty("qqConnect.oauth.consumerSecret");
    }

    public static boolean getBoolean(String name) {
        String value = getProperty(name);
        return Boolean.valueOf(value).booleanValue();
    }

    public static int getIntProperty(String name) {
        String value = getProperty(name);

        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException var3) {
            return -1;
        }
    }

    public static long getLongProperty(String name) {
        String value = getProperty(name);

        try {
            return Long.parseLong(value);
        } catch (NumberFormatException var3) {
            return -1L;
        }
    }

    public static String getProperty(String name) {
        return defaultProperty.getProperty(name);
    }

    public static int getNumberOfAsyncThreads() {
        return getIntProperty("qqConnect.async.numThreads");
    }

    public static boolean getDebug() {
        return getBoolean("qqConnect.debug");
    }

    static {
        init();
    }
}
