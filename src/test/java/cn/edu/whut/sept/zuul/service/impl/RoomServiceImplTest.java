package cn.edu.whut.sept.zuul.service.impl;

import cn.edu.whut.sept.zuul.pojo.GameMap;
import cn.edu.whut.sept.zuul.pojo.Item;
import cn.edu.whut.sept.zuul.pojo.Room;
import cn.edu.whut.sept.zuul.service.GameMapService;
import cn.edu.whut.sept.zuul.service.RoomService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author AnthonyCJ
 * @version 1.0
 * @description TODO
 * @date 2023/06/21 14:17
 */
@SpringBootTest
class RoomServiceImplTest {
    @Autowired
    private GameMapService gameMapService;
    @Autowired
    private RoomService roomService;
    @Autowired
    @Qualifier("gameMap_0")
    private GameMap gameMap;

    @Test
    void addItem() {
        Random r = new Random();
        for (Map.Entry<Long, Room> entry : gameMap.getRooms().entrySet()) {
            if (r.nextBoolean()) {
                roomService.addItem(gameMapService.getRoomByIndex(gameMap, entry.getKey()), new Item(1,"cookies", 1, false));
            }
        }
        boolean added = false;
        for (Map.Entry<Long, Room> entry : gameMap.getRooms().entrySet()) {
            List<Item> itemList = gameMapService.getRoomByIndex(gameMap, entry.getKey()).getRoomItems();
            if (itemList != null) {
                for (Item i : itemList) {
                    if (Objects.equals(i.getName(), "cookies")) {
                        added = true;
                        break;
                    }
                }
            }
        }
        assertTrue(added);
    }

    @Test
    void deleteItem() {
        for(Map.Entry<Long, Room> entry : gameMap.getRooms().entrySet()) {
            assertEquals(entry.getValue(), gameMapService.getRoomByIndex(gameMap, entry.getKey()));
            System.out.println(entry.toString());
        }

        boolean deleted = true;
        for (Map.Entry<Long, Room> entry : gameMap.getRooms().entrySet()) {
            List<Item> items = entry.getValue().getRoomItems();
            if (items.size() != 0) {
                for (Item i : items) {
                    if (Objects.equals(i.getName(), "gas")) {
                        roomService.reduceItem(entry.getValue(), i);
                        break;
                    }
                }
                for (Item i : items) {
                    if (Objects.equals(i.getName(), "gas")) {
                        deleted = false;
                        break;
                    }
                }
                break;
            }
        }
        assertTrue(deleted);

        for(Map.Entry<Long, Room> entry : gameMap.getRooms().entrySet()) {
            assertEquals(entry.getValue(), gameMapService.getRoomByIndex(gameMap, entry.getKey()));
            System.out.println(entry.toString());
        }
    }
}
