package cn.edu.whut.sept.zuul.service.impl;

import cn.edu.whut.sept.zuul.mapper.UserMapper;
import cn.edu.whut.sept.zuul.pojo.User;
import cn.edu.whut.sept.zuul.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author AnthonyCJ
 * @version 1.0
 * @description UserService接口的实现类
 * @date 2023/06/19 23:51
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户登录.
     *
     * @param userName 登录的用户名
     * @param userPassword 登录的用户密码
     * @return 若登录成功，则返回用户类，否则返回null
     */
    @Override
    public User userLogin(String userName, String userPassword) {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("user_name", userName));
        if(user != null && userPassword != null && !"".equals(userPassword)) {
            if(userPassword.equals(user.getUserPassword())) {
                return user;
            } else {
                return null;
            }
        }
        return null;
    }
}
