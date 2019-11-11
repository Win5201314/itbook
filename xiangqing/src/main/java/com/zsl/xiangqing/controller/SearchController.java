package com.zsl.xiangqing.controller;

import com.zsl.xiangqing.common.ServerResponse;
import com.zsl.xiangqing.common.core.controller.BaseController;
import com.zsl.xiangqing.common.page.TableDataInfo;
import com.zsl.xiangqing.entity.Users;
import com.zsl.xiangqing.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "搜索符合条件的伴侣控制器")
@RestController
@RequestMapping("/search")
public class SearchController extends BaseController {

    private IUserService userService;

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    /**
     * 简单模拟一个
     */
    @ApiOperation(value = "根据条件搜索自己想看的对象")
    @PostMapping("/searchMate")
    public ServerResponse searchMate(@ApiParam(name = "height", value = "身高", required = true) Float height,
                                     @ApiParam(name = "pageNum", value = "当前记录起始索引,前端要自己记住再传递过来", required = true) Integer pageNum,
                                     @ApiParam(name = "pageSize", value = "每页显示记录数,默认10条", required = false, defaultValue = "10") Integer pageSize)
            throws Exception{
        //设置分页信息
        startPage();
        //查询数据
        List<Users> list = userService.selectUsersByHeight(height);
        //包装查询的数据
        TableDataInfo tableDataInfo = getDataTable(list);
        return ServerResponse.createBySuccess(tableDataInfo);
    }
}
