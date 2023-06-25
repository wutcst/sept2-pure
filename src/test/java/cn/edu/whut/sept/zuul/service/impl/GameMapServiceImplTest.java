package cn.edu.whut.sept.zuul.service.impl;

import cn.edu.whut.sept.zuul.pojo.GameMap;
import cn.edu.whut.sept.zuul.pojo.Room;
import cn.edu.whut.sept.zuul.service.GameMapService;
import cn.edu.whut.sept.zuul.service.RoomService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author AnthonyCJ
 * @version 1.0
 * @description 测试地图服务功能
 * @date 2023/06/25 14:11
 */
@SpringBootTest
class GameMapServiceImplTest {
    @Autowired
    private GameMapService gameMapService;
    @Autowired
    private RoomService roomService;
    @Autowired
    @Qualifier("gameMap_0")
    private GameMap gameMap;


    @Test
    void listRooms() {
        for(Map.Entry<Long, Room> entry : gameMap.getRooms().entrySet()) {
            assertEquals(entry.getValue(), gameMapService.getRoomByIndex(gameMap, entry.getKey()));
            System.out.println(entry.toString());
        }
    }
}
