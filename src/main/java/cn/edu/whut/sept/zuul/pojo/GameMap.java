package cn.edu.whut.sept.zuul.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
/**
 * @author AnthonyCJ
 * @version 1.0
 * @description 定义了一个迷宫（Maze）类，其中包含了一个用于存储房间的映射对象（rooms）
 *  *              和一个表示初始房间的引用（initRoom）
 * @date 2023/06/18 12:51
 */
@Data // Lombok注解，用于自动生成类的getter、setter、toString等方法
@NoArgsConstructor // Lombok注解，用于生成无参的构造函数
@AllArgsConstructor // Lombok注解，用于生成包含所有参数的构造函数
public class GameMap {
    // 与maze中包含的所有的room id进行映射，key为room的id，value为room的引用
    private Map<Long, Room> rooms = new HashMap<>();    // 存储地图中的所有房间
    private Room initRoom;  // 迷宫的初始房间
}
