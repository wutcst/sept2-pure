package cn.edu.whut.sept.zuul.mapper;

import cn.edu.whut.sept.zuul.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author AnthonyCJ
 * @version 1.0
 * @description 用户类Mapper层
 * @date 2023/06/19 13:17
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
