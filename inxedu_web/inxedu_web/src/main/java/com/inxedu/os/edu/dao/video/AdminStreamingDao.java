package com.inxedu.os.edu.dao.video;

import com.inxedu.os.edu.entity.video.Server;

import java.util.List;
import java.util.Map;

public interface AdminStreamingDao {

    /**
     * 流媒体服务器列表
     */
    List<Server> getServerList();

    /**
     * 添加流媒体服务器列表
     */
    Long createServer(Server server);

    /**
     * 删除流媒体服务器列表
     */
    Long deleteServer(String serverId);

    /**
     * 获取频道实时信息
     * @param list
     */
    void getChanDetail(List<Map<String,String>> list);

}
