package cn.edu.whut.sept.zuul.service;

import cn.edu.whut.sept.zuul.pojo.GameMap;
import cn.edu.whut.sept.zuul.pojo.Room;

/**
 * @author AnthonyCJ
 * @version 1.0
 * @description 地图类接口
 * @date 2023/06/25 13:34
 */
public interface GameMapService {

    public boolean addRoom(GameMap gameMap, Room room);

    public Room getRoomByIndex(GameMap gameMap,Long id);
}
