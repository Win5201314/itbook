package com.inxedu.os.edu.dao.impl.video;

import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.edu.dao.video.AdminStreamingDao;
import com.inxedu.os.edu.entity.video.Server;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("AdminStreamingDao")
public class AdminStreamingDaoImpl extends GenericDaoImpl implements AdminStreamingDao {

    /**
     * 流媒体服务器列表
     * @return
     */
    @Override
    public List<Server> getServerList() {
        return this.selectList("ChanMapper.getServerList",null);
    }

    /**
     * 添加流媒体服务器
     * @return
     */
    @Override
    public Long createServer(Server server) {
        return this.insert("ChanMapper.createServer", server);
    }

    /**
     * 删除流媒体服务器
     * @return
     */
    @Override
    public Long deleteServer(String serverId) {
        return this.delete("ChanMapper.deleteServer", serverId);
    }

    /**
     * 获取频道实时信息
     * @param list
     */
    @Override
    public void getChanDetail(List<Map<String,String>> list){
        this.insert("ChanMapper.getChanDetail", list);
    }
}
