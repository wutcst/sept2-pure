package cn.edu.whut.sept.zuul.pojo;

import cn.edu.whut.sept.zuul.util.JsonUtil;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author AnthonyCJ
 * @version 1.0
 * @description Room类，房间类，用于关联其他房间，作为地图GameMap类的结点
 * @date 2023/06/18 12:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    // Jackson注解，指定在序列化时使用ToStringSerializer来将id属性转换为字符串
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id; // 房间的唯一标识

    private String name; // 房间的名称

    private String description; // 房间的描述

    private boolean event; // 房间是否是一个事件房间

    //Jackson注解，指定在序列化时使用自定义的MapWithLong2StrSerializer来将directions属性中的键值对中的Long类型转换为字符串
    @JsonSerialize(using = JsonUtil.MapWithLong2StrSerializer.class)
    private Map<String, Long> directions; // 关联其它房间

    private List<Item> roomItems;   // 房间的物品列表

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.roomItems = new ArrayList<>();
        this.event = false;
    }

    public Room(String name, String description, boolean event) {
        this.name = name;
        this.description = description;
        this.roomItems = new ArrayList<>();
        this.event = event;
    }
}
