package com.inxedu.os.edu.service.impl.livegensee;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.dao.livegensee.LiveGenseeDao;
import com.inxedu.os.edu.entity.livegensee.LiveGensee;
import com.inxedu.os.edu.service.livegensee.LiveGenseeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author www.inxedu.com
 * @description 展视互动直播 LiveGenseeService接口实现
 */
@Service("liveGenseeService")
public class LiveGenseeServiceImpl implements LiveGenseeService {
    @Autowired
    private LiveGenseeDao liveGenseeDao;

    /**
     * 添加展视互动直播
     */
    public Long addLiveGensee(LiveGensee liveGensee){
        return liveGenseeDao.addLiveGensee(liveGensee);
    }

    /**
     * 删除展视互动直播
     * @param id
     */
    public void delLiveGenseeById(Long id){
        liveGenseeDao.delLiveGenseeById(id);
    }

    /**
     * 修改展视互动直播
     * @param liveGensee
     */
    public void updateLiveGensee(LiveGensee liveGensee){
        liveGenseeDao.updateLiveGensee(liveGensee);
    }

    /**
     * 通过id，查询展视互动直播
     * @param id
     * @return
     */
    public LiveGensee getLiveGenseeById(Long id){
        return liveGenseeDao.getLiveGenseeById(id);
    }

    /**
     * 分页查询展视互动直播列表
     * @param liveGensee 查询条件
     * @param page 分页条件
     * @return List<LiveGensee>
     */
    public List<LiveGensee> queryLiveGenseeListPage(LiveGensee liveGensee,PageEntity page){
        return liveGenseeDao.queryLiveGenseeListPage(liveGensee, page);
    }

    /**
     * 条件查询展视互动直播列表
     * @param liveGensee 查询条件
     * @return List<LiveGensee>
     */
    public List<LiveGensee> queryLiveGenseeList(LiveGensee liveGensee){
        return liveGenseeDao.queryLiveGenseeList(liveGensee);
    }
}
