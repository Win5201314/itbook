package com.inxedu.os.edu.service.impl.video;

import com.inxedu.os.edu.dao.video.AdminStreamingDao;
import com.inxedu.os.edu.entity.video.Server;
import com.inxedu.os.edu.service.video.AdminStreamingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("AdminStreamingService")
public class AdminStreamingServiceImpl implements AdminStreamingService {
    @Autowired
    private AdminStreamingDao adminStreamingDao;

    @Override
    public List<Server> getServerList() {
        return adminStreamingDao.getServerList();
    }

    @Override
    public Long createServer(Server server){
        return adminStreamingDao.createServer(server);
    }

    @Override
    public Long deleteServer(String serverId){
        return adminStreamingDao.deleteServer(serverId);
    }

    @Override
    public void getChanDetail(List<Map<String,String>> list){
         adminStreamingDao.getChanDetail(list);
    }

}
