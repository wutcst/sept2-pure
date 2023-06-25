package cn.edu.whut.sept.zuul.enums;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author AnthonyCJ
 * @version 1.0
 * @description Restful风格返回json的数据封装包，封装了状态码以及消息值等信息
 * @date 2023/06/18 13:12
 */
public enum EnumResult {
    SUCCESS(200200, "请求成功"),
    INTERNAL_SERVER_ERROR(500500, "服务器内部错误"),
    USER_REPEAT(500101, "用户名重复或未提供数据"),
    USER_LOGIN_ERROR(500102, "用户登录失败"),
    USER_MOVE_FAIL(500201, "用户移动失败"),
    USER_TAKE_FAIL(500301, "不存在该物品或用户拿起该物品超重"),
    USER_NO_HOLD_ITEM(500302, "用户未持有该物品"),
    USER_USE_ITEM_FAIL(500303, "该物品不能被使用"),
    ROOM_NO_EVENT(500401, "房间不含有可用事件"),
    TEST_FAIL(500601, "您没有执行相关功能的权限"),
    TOKEN_ERROR(500701, "token已失效"),
    TOKEN_MISS(500702, "token不存在");
    private int code;
    private String msg;

    EnumResult() {
    }

    EnumResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "EnumResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
