package com.zsl.xiangqing.shiro;

import com.zsl.xiangqing.util.YmlPropertiesUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @description：SessionDao自定义实现
 * 将redis中存储的session时间单位改成毫秒
 * https://www.cnblogs.com/UncleWang001/articles/9779245.html
 */
@SuppressWarnings("all")
public class RedisSessionDao extends AbstractSessionDAO {

    private final String PREFIX="shiro_redis_session:";
    private static Logger logger = LoggerFactory.getLogger(RedisSessionDao.class);
    private RedisTemplate redisTpl;

    /**
     * 存储在redis里面的key
     * @param sessionId
     * @return
     */
    private String sessionKey(Serializable sessionId) {
        return PREFIX + sessionId.toString();
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        if (session == null || session.getId() == null){
            logger.error("redis update session error:session or session id is null");
            return;
        }

        try {
            redisTpl.opsForValue().set(sessionKey(session.getId()), session, (Long) YmlPropertiesUtils.getYml("spring.session.timeout"), TimeUnit.HOURS);
        } catch (Exception e) {
            // TODO: handle exception
            logger.error(e.getMessage(), e);
            throw new UnknownSessionException(e);
        }
    }

    @Override
    public void delete(Session session) {
        // TODO Auto-generated method stub
        if (session == null || session.getId() == null){
            logger.error("redis delete session error:session or session id is null");
            return;
        }
        try {
            redisTpl.delete(sessionKey(session.getId()));
        } catch (Exception e) {
            // TODO: handle exception
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public Collection<Session> getActiveSessions() {
        // TODO Auto-generated method stub
        return (Collection<Session>) redisTpl.execute(new RedisCallback<Collection<Session>>() {
            @Override
            public Collection<Session> doInRedis(RedisConnection connection) throws DataAccessException {
                // TODO Auto-generated method stub
                Set<Session> sessions = new HashSet<>();
                Set keys = redisTpl.keys(PREFIX + "*");

                for(Object key : keys){
                    Session session = (Session) redisTpl.opsForValue().get(key);
                    sessions.add(session);
                }

                return sessions;
            }
        });
    }

    @Override
    protected Serializable doCreate(Session session) {
        // TODO Auto-generated method stub
        if (session == null){
            logger.error("redis create session error:session  is null");
            return null;
        }
        // TODO Auto-generated method stub
        Serializable sessionId = generateSessionId(session);
        //绑定Session和sessionId
        assignSessionId(session, sessionId);
        redisTpl.opsForValue().set(sessionKey(session.getId()), session, session.getTimeout() / 1000, TimeUnit.SECONDS);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        if (sessionId == null){
            logger.error("redis read session error:sessionId is null");
            return null;
        }
        // TODO Auto-generated method stub
        Session session = null;
        try {
            session = (Session) redisTpl.opsForValue().get(PREFIX + sessionId);
        } catch (Exception e) {
            // TODO: handle exception
            logger.error(e.getMessage(), e);
        }
        return session;
    }

    public void setRedisTpl(RedisTemplate redisTpl) {
        this.redisTpl = redisTpl;
    }
}
