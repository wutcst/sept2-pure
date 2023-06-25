package cn.edu.whut.sept.zuul.interceptor;

import cn.edu.whut.sept.zuul.enums.EnumResult;
import cn.edu.whut.sept.zuul.pojo.ResultBean;
import cn.edu.whut.sept.zuul.util.JsonUtil;
import cn.edu.whut.sept.zuul.util.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author AnthonyCJ
 * @version 1.0
 * @description 该类实现了HandlerInterceptor接口，用于拦截请求并进行处理。
 *  *              校验用户登录权限 拦截请求，在请求处理之前验证传递的身份验证令牌（通过"Authorization"头部），
 *  *              如果验证成功则允许访问资源，否则返回一个包含Token错误信息的JSON响应。
 * @date 2023/06/19 13:24
 */
@Component
public class CheckInterceptor implements HandlerInterceptor {

    // 下面的方法是对HandlerInterceptor接口中的方法进行重写。
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从请求的HTTP头部获取名为"Authorization"的值，通常用于传递身份验证令牌或凭证。
        String token = request.getHeader("Authorization");
        if (JwtUtil.verify(token)) {
            // 说明验证成功，允许访问资源
            return true;
        } else {
            // 设置响应的字符编码为UTF-8
            response.setCharacterEncoding("utf-8");
            // 设置响应的内容类型为JSON格式，并指定字符编码为UTF-8
            response.setContentType("application/json; charset=utf-8");
            // 获取响应的输出流，并将一个包含Token错误信息的ResultBean对象转换为JSON字符串并写入输出流中
            response.getWriter().write(JsonUtil.objToJson(new ResultBean<>(EnumResult.TOKEN_ERROR)));
            return false;
        }
    }
}
