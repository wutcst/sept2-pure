package cn.edu.whut.sept.zuul.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AnthonyCJ
 * @version 1.0
 * @description Player类，对应游戏中控制的人物
 * @date 2023/06/18 12:54
 */
@Data // Lombok注解，用于自动生成类的getter、setter、equals、hashCode和toString方法
public class Player {
    // Jackson注解，指定在序列化时使用ToStringSerializer来将playerId属性转换为字符串
    @JsonSerialize(using = ToStringSerializer.class)
    private Long playerId;
    private List<Item> userItems;
    @JsonIgnore
    private List<Long> passedRooms;
    private int bearing_capacity;
    private int cur_weight;

    public Player(Long userId) {
        playerId = userId;
        userItems = new ArrayList<>();
        passedRooms = new ArrayList<>();
        bearing_capacity = 45;
        cur_weight = 0;
    }
}
