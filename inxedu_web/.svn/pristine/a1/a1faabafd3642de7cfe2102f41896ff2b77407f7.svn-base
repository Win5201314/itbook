//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.inxedu.os.common.cache;


import java.util.Map;
import java.util.Set;

/**
 * @author www.inxedu.com
 */
public class MemCache {
    private static MemCacheService memCacheService = null;

    private static MemCache memCache = new MemCache();

    public static MemCache getInstance() {
        return memCache;
    }

    public MemCache() {
        memCacheService = MemCacheServiceImpl.getInstance();
    }

    public Object get(String key) {
        try {
            if(memCacheService != null) {
                return memCacheService.get(key);
            }
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return null;
    }

    public boolean set(String key, Object value) {
        try {
            if(memCacheService != null) {
                return memCacheService.set(key, value);
            }
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return false;
    }

    public Map<String, Object> getBulk(Set<String> keys) {
        try {
            if(memCacheService != null) {
                return memCacheService.getBulk(keys);
            }
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return null;
    }

    public boolean remove(String key) {
        try {
            if(memCacheService != null) {
                return memCacheService.remove(key);
            }
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return false;
    }

    public boolean set(String key, Object value, int exp) {
        try {
            if(memCacheService != null) {
                return memCacheService.set(key, value, exp);
            }
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return false;
    }
}
