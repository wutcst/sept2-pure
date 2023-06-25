package cn.edu.whut.sept.zuul.service.impl;

import cn.edu.whut.sept.zuul.pojo.User;
import cn.edu.whut.sept.zuul.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author AnthonyCJ
 * @version 1.0
 * @description TODO
 * @date 2023/06/25 14:19
 */
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    @Order(1)
    void saveOrUpdate() {
        User user = new User(null, "test1", "123456");
        userService.remove(new QueryWrapper<User>().eq("user_name", "test1"));
        // 测试新增功能是否正常
        boolean success1 = userService.saveOrUpdate(user);
        System.out.println(user);
        assertTrue(success1);
        // 测试修改功能是否正常
        user.setUserPassword("12345678");
        boolean success2 = userService.saveOrUpdate(user);
        User updateUser = userService.getById(user.getUserId());
        assertEquals("12345678", user.getUserPassword());
    }


    @Test
    @Order(2)
    void list() {
        List<User> lists = userService.list();
        for(User temp : lists) {
            System.out.println(temp);
        }
    }

    @Test
    @Order(3)
    void userLogin() {
        // 三个登录失败
        User loginUser = new User(null, "test1", "123456");
        User temp = userService.userLogin(loginUser.getUserName(), loginUser.getUserPassword());
        assertNull(temp);
        loginUser = new User(null, "", "123456");
        temp = userService.userLogin(loginUser.getUserName(), loginUser.getUserPassword());
        assertNull(temp);
        loginUser = new User(null, "test1", "");
        temp = userService.userLogin(loginUser.getUserName(), loginUser.getUserPassword());
        assertNull(temp);
        // 登录成功
        loginUser = new User(null, "test1", "12345678");
        temp = userService.userLogin(loginUser.getUserName(), loginUser.getUserPassword());
        assertNotNull(temp);
    }
}
