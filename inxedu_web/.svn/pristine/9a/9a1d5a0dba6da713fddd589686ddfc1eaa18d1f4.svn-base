package com.inxedu.os.edu.service.video;

import com.inxedu.os.edu.entity.video.Server;

import java.util.List;
import java.util.Map;

public interface AdminStreamingService {

    /**
     * 流媒体服务器列表
     */
    List<Server> getServerList();

    /**
     * 添加流媒体服务器
     */
    Long createServer(Server server);

    /**
     * 删除流媒体服务器
     */
    Long deleteServer(String serverId);

    /**
     * 获取频道实时信息
     * @param list
     */
    void getChanDetail(List<Map<String,String>> list);

}
