package com.inxedu.os.common.cache;

import com.inxedu.os.common.util.PropertiesReader;
import net.spy.memcached.AddrUtil;
import net.spy.memcached.DefaultConnectionFactory;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.OperationFactory;
import net.spy.memcached.internal.BulkFuture;
import net.spy.memcached.internal.GetFuture;
import net.spy.memcached.internal.OperationFuture;
import net.spy.memcached.transcoders.SerializingTranscoder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by yzl16 on 2016/7/12.
 */
public class MemCacheServiceImpl implements MemCacheService{
    public static final String CACHE_PROP_FILE = "project";
    public static final String ENCODING = "UTF-8";
    //0 不使用memcache 1使用memcache
    public static String isUse = "1";
    private static Log log = LogFactory.getLog(MemCacheServiceImpl.class);
    private static ConcurrentHashMap<String, MemCacheService> flyweights = new ConcurrentHashMap();
    private MemcachedClient mc1 = null;
    private MemcachedClient mc2 = null;
    public static final int DEFAULT_MEMCACHED_TIMEOUT = 1;
    private int opTimeout = 1;
    public static final int DEFAULT_MEMCACHED_TIMEOUT_BATCH = 3;
    private int opTimeoutBulk = 3;
    public static final int DEFAULT_READBUF_SIZE = 16384;
    private int readBufSize = 16384;
    public static final int DEFAULT_OPQ_SIZE = 16384;
    private int opQueueLens = 3;
    public static final int DEFAULT_MEMCACHED_EXP_HOURS = 24;
    private int expHour = 24;
    public static final int DEFAULT_MEMCACHED_RETRY = 3;
    private int retry = 3;

    public static MemCacheService getInstance(String prop_file) {
        if(!flyweights.containsKey(prop_file)) {
            synchronized(prop_file) {
                flyweights.put(prop_file, new MemCacheServiceImpl(prop_file));
            }
        }

        return flyweights.get(prop_file);
    }

    public MemCacheServiceImpl() {
        String prop_file = "project";
        if(!flyweights.containsKey(prop_file)) {
            synchronized(prop_file) {
                flyweights.put(prop_file, new MemCacheServiceImpl(prop_file));
            }
        }

    }

    public static MemCacheService getInstance() {
        return "1".equalsIgnoreCase(isUse)?getInstance("project"):null;
    }

    private MemCacheServiceImpl(String prop_file) {
        String server1 = PropertiesReader.getValue(prop_file, "server1");
        String server2 = PropertiesReader.getValue(prop_file, "server1");
        try {
            this.opTimeout = 3;
            this.opTimeoutBulk = 3;
            this.retry = 1;
            this.readBufSize = 16384;
            this.opQueueLens = 16384;
            this.expHour = 24;
        } catch (Exception var7) {
            log.error("loading properties fail, use default config!");
        }

        try {
            this.mc1 = new MemcachedClient(new DefaultConnectionFactory() {
                public long getOperationTimeout() {
                    return (long)(MemCacheServiceImpl.this.opTimeout * 1000);
                }

                public int getReadBufSize() {
                    return MemCacheServiceImpl.this.readBufSize;
                }

                public OperationFactory getOperationFactory() {
                    return super.getOperationFactory();
                }

                public int getOpQueueLen() {
                    return MemCacheServiceImpl.this.opQueueLens;
                }

                public boolean isDaemon() {
                    return true;
                }
            }, AddrUtil.getAddresses(server1));
            this.mc2 = new MemcachedClient(new DefaultConnectionFactory() {
                public long getOperationTimeout() {
                    return (long)(MemCacheServiceImpl.this.opTimeout * 1000);
                }

                public int getReadBufSize() {
                    return MemCacheServiceImpl.this.readBufSize;
                }

                public OperationFactory getOperationFactory() {
                    return super.getOperationFactory();
                }

                public int getOpQueueLen() {
                    return this.opQueueLen;
                }

                public boolean isDaemon() {
                    return true;
                }
            }, AddrUtil.getAddresses(server2));
        } catch (IOException var6) {
            log.error("DefaultConnectionFactory memcache error:", var6);
        }

        SerializingTranscoder x1 = (SerializingTranscoder)this.mc1.getTranscoder();
        x1.setCharset("UTF-8");
        SerializingTranscoder x2 = (SerializingTranscoder)this.mc2.getTranscoder();
        x2.setCharset("UTF-8");
    }

    public Object get(String key) {
        Object result = null;

        try {
            for(int ex = 0; ex < this.retry; ++ex) {
                result = this._get(key);
                if(result != null) {
                    break;
                }
            }

            if(result == null) {
            }
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return result;
    }

    private Object _get(String key) {
        Object myObj = null;

        try {
            GetFuture e = this.mc1.asyncGet(key);

            try {
                myObj = e.get((long)this.opTimeout, TimeUnit.SECONDS);
            } catch (TimeoutException var9) {
                var9.printStackTrace();
                e.cancel(false);
            } catch (InterruptedException var10) {
                var10.printStackTrace();
                e.cancel(false);
            } catch (ExecutionException var11) {
                var11.printStackTrace();
                e.cancel(false);
            }

            if(myObj == null) {
                GetFuture f2 = this.mc2.asyncGet(key);

                try {
                    myObj = f2.get((long)this.opTimeout, TimeUnit.SECONDS);
                } catch (TimeoutException var6) {
                    var6.printStackTrace();
                    f2.cancel(false);
                } catch (InterruptedException var7) {
                    var7.printStackTrace();
                    f2.cancel(false);
                } catch (ExecutionException var8) {
                    var8.printStackTrace();
                    f2.cancel(false);
                }
            }
        } catch (Exception var12) {
            var12.printStackTrace();
        }

        if(myObj != null) {
        }

        return myObj;
    }

    public Map<String, Object> getBulk(Set<String> keys) {
        log.info("[ACCESS]begin to get info from cache in bulk...");
        Map ret = null;

        try {
            BulkFuture i$ = this.mc1.asyncGetBulk(keys);

            try {
                ret = (Map)i$.get((long)this.opTimeoutBulk, TimeUnit.SECONDS);
            } catch (TimeoutException var9) {
                log.info("[FAIL]time out when getting objects from cache server1...");
                i$.cancel(false);
            } catch (InterruptedException var10) {
                log.info("[FAIL]thread been interrupted while waiting when getting object from cache server1...");
                i$.cancel(false);
            } catch (ExecutionException var11) {
                log.info("[FAIL]exception when getting object from cache server1...");
                i$.cancel(false);
            }

            if(ret == null) {
                BulkFuture key = this.mc2.asyncGetBulk(keys);

                try {
                    ret = (Map)key.get((long)this.opTimeoutBulk, TimeUnit.SECONDS);
                } catch (TimeoutException var6) {
                    log.info("[FAIL]time out when getting objects from cache server2...");
                    key.cancel(false);
                } catch (InterruptedException var7) {
                    log.info("[FAIL]thread been interrupted while waiting when getting object from cache server2...");
                    key.cancel(false);
                } catch (ExecutionException var8) {
                    log.info("[FAIL]exception when getting object from cache server2...");
                    key.cancel(false);
                }
            }
        } catch (Exception var12) {
            log.error("[ERROR]other exception when getting objects from fengchao cache...", var12);
        }

        if(ret != null) {
            Iterator i$1 = keys.iterator();

            while(i$1.hasNext()) {
                String key1 = (String)i$1.next();
                if(ret.get(key1) != null) {
                    log.info("[GET]SHOOTED\tKey=" + key1 + "\tValue=" + ret.get(key1).toString());
                }
            }
        }

        return ret;
    }

    public boolean set(String key, Object value) {
        boolean result = false;

        for(int i = 0; i < this.retry; ++i) {
            result = this._set(key, value);
            if(result) {
                break;
            }

            log.info("set info into cache failed begin to retry " + i);
        }

        if(!result) {
            log.error("[FAIL] completely failed when setting info into cache after " + this.retry + " times");
        }

        return result;
    }

    private boolean _set(String key, Object value) {
        boolean ret = false;
        OperationFuture f = this.mc1.set(key, this.expHour * 60 * 60, value);
        OperationFuture f2 = this.mc2.set(key, this.expHour * 60 * 60, value);

        try {
            boolean e = ((Boolean)f.get((long)this.opTimeout, TimeUnit.SECONDS)).booleanValue();
            boolean fs2 = ((Boolean)f2.get((long)this.opTimeout, TimeUnit.SECONDS)).booleanValue();
            ret = e || fs2;
            if(!e) {
                log.info("[FAIL]CACHE SET FAIL:server1 set failed: Key=" + key + "\tValue=" + value.toString());
            } else if(!fs2) {
                log.info("[FAIL]CACHE SET FAIL:server2 set failed: Key=" + key + "\tValue=" + value.toString());
            }
        } catch (TimeoutException var8) {
            log.info("[FAIL]time out when getting objects from cache server2...");
            f.cancel(false);
            f2.cancel(false);
        } catch (InterruptedException var9) {
            log.error("[ERROR]exception when setting fengchao cache - thread been interrupted...", var9);
            f.cancel(false);
            f2.cancel(false);
        } catch (ExecutionException var10) {
            log.error("[ERROR]exception when setting fengchao cache - exception when getting status...", var10);
            f.cancel(false);
            f2.cancel(false);
        } catch (Exception var11) {
            log.error("[ERROR]exception when setting fengchao cache - other exceptions...", var11);
            f.cancel(false);
            f2.cancel(false);
        }

        if(value != null) {
            log.info("MemCacheServiceImpl.set,key=" + key + ",value=" + value.getClass());
        } else {
            log.info("MemCacheServiceImpl.set,key=" + key + ",value=null");
        }

        return ret;
    }

    public boolean remove(String key) {
        boolean ret = false;
        OperationFuture f = this.mc1.delete(key);
        OperationFuture f2 = this.mc2.delete(key);

        try {
            ret = ((Boolean)f.get((long)this.opTimeout, TimeUnit.SECONDS)).booleanValue() && ((Boolean)f2.get((long)this.opTimeout, TimeUnit.SECONDS)).booleanValue();
        } catch (TimeoutException var6) {
            log.info("[FAIL]time out when getting objects from cache server2...");
            f.cancel(false);
            f2.cancel(false);
        } catch (InterruptedException var7) {
            log.error("[ERROR]exception when deleting fengchao cache - thread been interrupted...", var7);
            f.cancel(false);
            f2.cancel(false);
            ret = false;
        } catch (ExecutionException var8) {
            log.error("[ERROR]exception when deleting fengchao cache - exception when getting status...", var8);
            f.cancel(false);
            f2.cancel(false);
            ret = false;
        } catch (Exception var9) {
            log.error("[ERROR]exception when deleting fengchao cache - other exceptions...", var9);
            f.cancel(false);
            f2.cancel(false);
            ret = false;
        }

        log.info("[REMOVE]" + ret + "\tKey=" + key);
        return ret;
    }

    public boolean set(String key, Object value, int exp) {
        if(value == null) {
            return false;
        } else {
            boolean ret = false;
            OperationFuture f = this.mc1.set(key, exp, value);
            OperationFuture f2 = this.mc2.set(key, exp, value);

            try {
                boolean e = ((Boolean)f.get((long)this.opTimeout, TimeUnit.SECONDS)).booleanValue();
                boolean fs2 = ((Boolean)f2.get((long)this.opTimeout, TimeUnit.SECONDS)).booleanValue();
                ret = e || fs2;
                if(!e) {
                    log.info("[FAIL]CACHE SET FAIL:server1 set failed: Key=" + key + ",Value=" + value.toString());
                } else if(!fs2) {
                    log.info("[FAIL]CACHE SET FAIL:server2 set failed: Key=" + key + ",Value=" + value.toString());
                }
            } catch (Exception var9) {
                if(!"LOGIN_IP".equalsIgnoreCase(key)) {
                    log.info("MemCacheServiceImpl.set,key=" + key + ",value=" + value + ",Exception");
                }

                var9.printStackTrace();
                f.cancel(false);
                f2.cancel(false);
            }

            log.info("MemCacheServiceImpl.set,key=" + key + ",value=" + value.getClass());
            return ret;
        }
    }
}
