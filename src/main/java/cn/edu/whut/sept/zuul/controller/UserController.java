package cn.edu.whut.sept.zuul.controller;

import cn.edu.whut.sept.zuul.enums.EnumResult;
import cn.edu.whut.sept.zuul.pojo.ResultBean;
import cn.edu.whut.sept.zuul.pojo.User;
import cn.edu.whut.sept.zuul.service.UserService;
import cn.edu.whut.sept.zuul.util.JwtUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author AnthonyCJ
 * @version 1.0
 * @description UserController，实现用户登录，注册等相关功能
 * @date 2023/06/22 21:27
 */
@Api(tags = {"用户操作"})
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册用户User.
     *
     * @param user 需要插入数据库的用户信息，使用body传递json数据
     * @return 若重复用户名或用户信息为空报错
     */
    @ApiOperation(value = "用户注册", notes = "提供用户名以及密码进行注册")
    @ApiImplicitParam(name = "user", value = "用户注册信息Json数据", required = true, dataType = "User")
    @PostMapping("/register")
    public ResultBean<Object> registerUser(@RequestBody User user) {
        if(user == null || userService.getOne(new QueryWrapper<User>().
                eq("user_name", user.getUserName())) != null) {
            // 说米国没有提供user或者查询到了重复的用户名，报错
            return new ResultBean<>(EnumResult.USER_REPEAT);
        } else {
            // 说明请求成功
            if(userService.save(user)) {
                return new ResultBean<>(EnumResult.SUCCESS);
            } else {
                return new ResultBean<>(EnumResult.INTERNAL_SERVER_ERROR);
            }
        }
    }

    /**
     * 用户登录.
     *
     * @param user 需要登录的用户
     * @return 若登录成功则颁发token允许之后的鉴权，否则返回错误信息
     */
    @ApiOperation(value = "用户登录", notes = "提供用户名以及密码进行登录")
    @ApiImplicitParam(name = "user", value = "用户登录信息Json数据", required = true, dataType = "User")
    @PostMapping("/login")
    public ResultBean<Object> tryLogin(@RequestBody User user) {
        User tmpUser = userService.userLogin(user.getUserName(), user.getUserPassword());
        if(tmpUser != null) {
            // 说明登录成功，生成一个token
            String token = JwtUtil.sign(tmpUser.getUserId(), tmpUser.getUserName());
            return new ResultBean<>(EnumResult.SUCCESS, token);
        } else {
            // 登录失败
            return new ResultBean<>(EnumResult.USER_LOGIN_ERROR);
        }
    }
}
