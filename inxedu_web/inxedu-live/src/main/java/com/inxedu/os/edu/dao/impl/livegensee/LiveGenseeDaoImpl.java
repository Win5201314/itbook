package com.inxedu.os.edu.dao.impl.livegensee;

import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.dao.livegensee.LiveGenseeDao;
import com.inxedu.os.edu.entity.livegensee.LiveGensee;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @author www.inxedu.com
 * @description 展视互动直播 LiveGenseeDao接口实现
 */
@Repository("liveGenseeDao")
public class LiveGenseeDaoImpl extends GenericDaoImpl implements LiveGenseeDao{
    /**
     * 添加展视互动直播
     */
    public Long addLiveGensee(LiveGensee liveGensee){
        this.insert("LiveGenseeMapper.addLiveGensee", liveGensee);
        return liveGensee.getId();
    }

    /**
     * 删除展视互动直播
     * @param id
     */
    public void delLiveGenseeById(Long id){
        this.update("LiveGenseeMapper.delLiveGenseeById", id);
    }

    /**
     * 修改展视互动直播
     * @param liveGensee
     */
    public void updateLiveGensee(LiveGensee liveGensee){
        this.update("LiveGenseeMapper.updateLiveGensee", liveGensee);
    }

    /**
     * 通过id，查询展视互动直播
     * @param id
     * @return
     */
    public LiveGensee getLiveGenseeById(Long id){
        return this.selectOne("LiveGenseeMapper.getLiveGenseeById", id);
    }

    /**
     * 分页查询展视互动直播列表
     * @param liveGensee 查询条件
     * @param page 分页条件
     * @return List<LiveGensee>
     */
    public List<LiveGensee> queryLiveGenseeListPage(LiveGensee liveGensee,PageEntity page){
        return this.queryForListPage("LiveGenseeMapper.queryLiveGenseeListPage", liveGensee, page);
    }

    /**
     * 条件查询展视互动直播列表
     * @param liveGensee 查询条件
     * @return List<LiveGensee>
     */
    public List<LiveGensee> queryLiveGenseeList(LiveGensee liveGensee){
        return this.selectList("LiveGenseeMapper.queryLiveGenseeList", liveGensee);
    }
}
