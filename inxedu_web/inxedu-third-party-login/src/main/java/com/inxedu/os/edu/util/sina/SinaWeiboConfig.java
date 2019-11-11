package com.inxedu.os.edu.util.sina;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author www.inxedu.com
 */
public class SinaWeiboConfig {
    private static Properties props = new Properties();

    public SinaWeiboConfig() {
    }

    public static String getValue(String key) {
        return props.getProperty(key);
    }

    public static void updateProperties(String key, String value) {
        props.setProperty(key, value);
    }

    static {
        try {
            props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("sinaweiboconfig.properties"));
        } catch (FileNotFoundException var1) {
            var1.printStackTrace();
        } catch (IOException var2) {
            var2.printStackTrace();
        }

    }
}
