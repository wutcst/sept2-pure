package cn.edu.whut.sept.zuul.service;

import cn.edu.whut.sept.zuul.pojo.Item;
import cn.edu.whut.sept.zuul.pojo.Player;
import cn.edu.whut.sept.zuul.pojo.Room;

/**
 * @author AnthonyCJ
 * @version 1.0
 * @description PlayerService接口
 * @date 2023/06/19 20:35
 */
public interface PlayerService {

    public Player getPlayerByUserId(Long userId);

    public Room back(Player player, Integer quantity);

    public Room move(Player player, String direc);

    public Long getCurrentRoomId(Player player);

    public Room getCurrentRoom(Player player);

    public boolean dropItem(Player player, Integer itemId);

    public boolean takeItem(Player player, Integer itemId);

    public Item getItem(Player player, Integer itemId);

    public boolean deleteItem(Player player, Integer itemId, boolean useItem);
}
