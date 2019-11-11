package com.zsl.xiangqing.controller;

import com.zsl.xiangqing.common.ServerResponse;
import com.zsl.xiangqing.common.core.controller.BaseController;
import com.zsl.xiangqing.entity.Users;
import com.zsl.xiangqing.properties.RedisProperties;
import com.zsl.xiangqing.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Api(value = "测试作用", tags = "测试作用")
@RestController
public class TestController extends BaseController {

    private RedisProperties redisProperties;
    @Autowired
    public void setRedisProperties(RedisProperties redisProperties) {
        this.redisProperties = redisProperties;
    }

    @Autowired
    private IUserService userService;

    //http://localhost:8080/test
    @ApiOperation(value = "测试作用", notes = "测试作用")
    @RequestMapping("/test")
    public ServerResponse<String> test() {
        /*Users user = userService.selectUserByLoginName("13480901446");
        if (user != null) {
            return ServerResponse.createBySuccessMessage("注册成功" + user);
        } else {
            //插入数据库失败
            return ServerResponse.createByErrorMessage("注册失败");
        }*/
        return ServerResponse.createBySuccessMessage("启动成功!-----------" + redisProperties);
    }

    @RequiresPermissions({"select"}) //没有的话 AuthorizationException
    @PostMapping("/select")
    public Map<String, Object> selectPermission() {
        System.out.println("select");
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("msg", "当前角色有查看的权力");
        return map;
    }

    @RequiresPermissions({"insert"}) //没有的话 AuthorizationException
    @PostMapping("/insert")
    public Map<String, Object> insertPermission() {
        System.out.println("insert");
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("msg", "当前角色有增加的权力");
        return map;
    }

    @RequiresPermissions({"update"}) //没有的话 AuthorizationException
    @PostMapping("/update")
    public Map<String, Object> updatePermission() {
        System.out.println("update");
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("msg", "当前角色有更新的权力");
        return map;
    }

    @RequiresPermissions({"delete"}) //没有的话 AuthorizationException
    @PostMapping("/delete")
    public Map<String, Object> deletePermission() {
        System.out.println("delete");
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("msg", "当前角色有删除的权力");
        return map;
    }

    @RequiresRoles({"vip"}) //没有的话 AuthorizationException
    @PostMapping("/vip")
    public Map<String, Object> vipRole() {
        System.out.println("vip");
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("msg", "当前用户具有 vip 角色");
        return map;
    }

    @RequiresRoles({"ip"}) //没有的话 AuthorizationException
    @PostMapping("/ip")
    public Map<String, Object> ipRole() {
        System.out.println("ip");
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("msg", "当前用户具有 ip 角色");
        return map;
    }

    @RequiresRoles({"p"}) //没有的话 AuthorizationException
    @PostMapping("/p")
    public Map<String, Object> pRole() {
        System.out.println("vip");
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("msg", "当前用户具有 p 角色");
        return map;
    }

}
