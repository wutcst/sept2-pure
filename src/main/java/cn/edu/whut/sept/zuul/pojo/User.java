package cn.edu.whut.sept.zuul.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author AnthonyCJ
 * @version 1.0
 * @description 用户实体类
 * @date 2023/06/17 12:23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")  // MyBatis-Plus注解，用于指定数据库表的名称为"user"。
public class User {
    @JsonSerialize(using = ToStringSerializer.class) //Jackson注解，指定在序列化时使用ToStringSerializer来将userId属性转换为字符串
    @TableId(type = IdType.AUTO)    // MyBatis-Plus注解，指定userId属性作为数据库表的主键，并且采用自动增长的方式生成
    @ApiModelProperty(value = "用户Id", hidden = true, example = "19") // Swagger注解，用于提供关于userId属性的额外信息，包括描述、隐藏、示例值等
    private Long userId;

    @ApiModelProperty(value = "登录用户名", example = "admin1")
    private String userName;

    @ApiModelProperty(value = "登录用户密码", example = "p@ssword")
    private String userPassword;
}
