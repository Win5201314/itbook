package com.inxedu.os.edu.dao.livegensee;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.entity.livegensee.LiveGensee;

import java.util.List;

/**
 * @author www.inxedu.com
 * @description 展视互动直播 LiveGenseeDao接口
 */
public interface LiveGenseeDao{
    /**
     * 添加展视互动直播
     */
    Long addLiveGensee(LiveGensee liveGensee);

    /**
     * 删除展视互动直播
     * @param id
     */
    void delLiveGenseeById(Long id);

    /**
     * 修改展视互动直播
     * @param liveGensee
     */
    void updateLiveGensee(LiveGensee liveGensee);

    /**
     * 通过id，查询展视互动直播
     * @param id
     * @return
     */
    LiveGensee getLiveGenseeById(Long id);

    /**
     * 分页查询展视互动直播列表
     * @param liveGensee 查询条件
     * @param page 分页条件
     * @return List<LiveGensee>
     */
    List<LiveGensee> queryLiveGenseeListPage(LiveGensee liveGensee, PageEntity page);

    /**
     * 条件查询展视互动直播列表
     * @param liveGensee 查询条件
     * @return List<LiveGensee>
     */
    List<LiveGensee> queryLiveGenseeList(LiveGensee liveGensee);
}


