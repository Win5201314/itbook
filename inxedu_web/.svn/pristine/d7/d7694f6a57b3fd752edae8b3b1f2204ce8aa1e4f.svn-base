
package com.inxedu.os.common.cache;

/**
 * 缓存工具类
 * @author www.inxedu.com
 */
public class CacheUtil {
    private static MemCache memCache = MemCache.getInstance();
    public CacheUtil() {
    }
    public static Object get(String key) {
        try {
            return memCache.get(key);
        } catch (Exception var2) {
            var2.printStackTrace();
        }

        return null;
    }

    public static void set(String key, Object value) {
        try {
            memCache.set(key,value);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public static boolean remove(String key) {
        try {
            memCache.remove(key);
        } catch (Exception var2) {
            var2.printStackTrace();
        }

        return false;
    }

    public static boolean removeAll() {
        try {

        } catch (Exception var1) {
            var1.printStackTrace();
        }

        return false;
    }

    public static void set(String key, Object value, int exp) {
        try {
            memCache.set(key,value,exp);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }

}
