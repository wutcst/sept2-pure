package cn.edu.whut.sept.zuul.service;

import cn.edu.whut.sept.zuul.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author AnthonyCJ
 * @version 1.0
 * @description UserService接口
 * @date 2023/06/19 20:37
 */
public interface UserService extends IService<User> {
    public User userLogin(String userName, String userPassword);
}
