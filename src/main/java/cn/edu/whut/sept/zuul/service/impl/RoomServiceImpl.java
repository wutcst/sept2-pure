package cn.edu.whut.sept.zuul.service.impl;

import cn.edu.whut.sept.zuul.pojo.Item;
import cn.edu.whut.sept.zuul.pojo.Room;
import cn.edu.whut.sept.zuul.service.RoomService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AnthonyCJ
 * @version 1.0
 * @description RoomService实现类，实现相关功能
 * @date 2023/06/25 13:50
 */
@Service
public class RoomServiceImpl implements RoomService {

    @Override
    public Item getItem(Room room, Integer itemId) {
        List<Item> lists = room.getRoomItems();
        for (Item item : lists) {
            if (itemId.equals(item.getId())) return item;
        }
        return null;
    }

    @Override
    public boolean addItem(Room room, Item item) {
        if (room.getRoomItems() == null) {
            room.setRoomItems(new ArrayList<Item>());
        }
        room.getRoomItems().add(item);
        return true;
    }

    @Override
    public boolean reduceItem(Room room, Item item) {
        List<Item> items = room.getRoomItems();
        return items.remove(item);
    }
}
