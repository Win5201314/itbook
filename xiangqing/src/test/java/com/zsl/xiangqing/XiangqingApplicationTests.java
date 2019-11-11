package com.zsl.xiangqing;

import com.zsl.xiangqing.entity.Users;
import com.zsl.xiangqing.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class XiangqingApplicationTests {

    @Autowired
    private IUserService iUserService;

    @Test
    public void contextLoads() {
        //Users users = iUserService.selectUserByPhoneNumber("13480901446");
        //System.out.println("================" + users.getUsername());
        boolean flag = iUserService.updatePasswordByUsername("13480901446", "486544555rdg");
        System.out.println("===============================" + flag);
    }

}
