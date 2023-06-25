package cn.edu.whut.sept.zuul.service;

import cn.edu.whut.sept.zuul.pojo.Item;
import cn.edu.whut.sept.zuul.pojo.Room;

/**
 * @author AnthonyCJ
 * @version 1.0
 * @description TODO
 * @date 2023/06/19 20:36
 */
public interface RoomService {
    public boolean addItem(Room room, Item item);

    public boolean reduceItem(Room room, Item item);

    public Item getItem(Room room, Integer itemId);
}
