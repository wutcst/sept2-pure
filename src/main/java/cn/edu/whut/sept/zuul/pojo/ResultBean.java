package cn.edu.whut.sept.zuul.pojo;

import cn.edu.whut.sept.zuul.enums.EnumResult;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author AnthonyCJ
 * @version 1.0
 * @description Restful风格返回json的数据封装包，封装了状态码以及消息值等信息
 * @date 2023/06/18 12:56
 */
@JsonInclude(JsonInclude.Include.NON_NULL) // Jackson注解，用于指定在序列化过程中忽略值为null的属性
public class ResultBean<T> implements Serializable {
    @ApiModelProperty(value="返回的状态码", example = "500701")
    private int code;       // 状态码
    @ApiModelProperty(value="状态码对应的消息值", example = "token已失效")
    private String msg;     // 消息值
    @ApiModelProperty(value="返回数据", example = "返回的对应数据")
    private T data;         // 返回值

    public ResultBean() {
    }

    public ResultBean(EnumResult enumResult, T data){
        this.code=enumResult.getCode();
        this.msg=enumResult.getMsg();
        this.data=data;
    }
    public ResultBean(EnumResult enumResult){
        this.code=enumResult.getCode();
        this.msg=enumResult.getMsg();
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
