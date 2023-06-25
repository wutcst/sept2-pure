package cn.edu.whut.sept.zuul.service.impl;

import cn.edu.whut.sept.zuul.pojo.GameMap;
import cn.edu.whut.sept.zuul.pojo.Player;
import cn.edu.whut.sept.zuul.pojo.Room;
import cn.edu.whut.sept.zuul.service.GameMapService;
import cn.edu.whut.sept.zuul.service.PlayerService;
import cn.edu.whut.sept.zuul.service.RoomService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author AnthonyCJ
 * @version 1.0
 * @description TODO
 * @date 2023/06/22 14:15
 */
@SpringBootTest
class PlayerServiceImplTest {
    @Autowired
    private GameMapService gameMapService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private PlayerService playerService;
    @Autowired
    @Qualifier("gameMap_0")
    private GameMap gameMap;

    @Test
    void dropItem() {
        Player player = new Player(1L);
        List<Long> passed = player.getPassedRooms();
        passed.add(gameMap.getInitRoom().getId());
        player.setPassedRooms(passed);
        playerService.dropItem(player, 1);

        for (Map.Entry<Long, Room> entry : gameMap.getRooms().entrySet()) {
            assertEquals(entry.getValue(), gameMapService.getRoomByIndex(gameMap, entry.getKey()));
            System.out.println(entry.toString());
        }
    }

    @Test
    void takeItem() {
        Player player = new Player(1L);
        List<Long> passed = player.getPassedRooms();
        passed.add(gameMap.getInitRoom().getId());
        player.setPassedRooms(passed);
        playerService.takeItem(player, 1);

        for (Map.Entry<Long, Room> entry : gameMap.getRooms().entrySet()) {
            assertEquals(entry.getValue(), gameMapService.getRoomByIndex(gameMap, entry.getKey()));
            System.out.println(entry.toString());
        }
    }
}
