package cn.edu.whut.sept.zuul.service.impl;

import cn.edu.whut.sept.zuul.pojo.GameMap;
import cn.edu.whut.sept.zuul.pojo.Item;
import cn.edu.whut.sept.zuul.pojo.Player;
import cn.edu.whut.sept.zuul.pojo.Room;
import cn.edu.whut.sept.zuul.service.GameMapService;
import cn.edu.whut.sept.zuul.service.PlayerService;
import cn.edu.whut.sept.zuul.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author AnthonyCJ
 * @version 1.0
 * @description TODO
 * @date 2023/06/20 23:40
 */
@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private GameMapService gameMapService;
    @Autowired
    private RoomService roomService;
    @Autowired
    @Qualifier("gameMap_0")
    @Lazy
    private GameMap gameMap;

    /**
     * 该变量用于存放全局的user id和player的对应关系，通过userId查找其对应的Player
     */
    private final ConcurrentHashMap<Long, Player> playerList = new ConcurrentHashMap<>();

    /**
     * 获得UserId对应的Player
     *
     * @param userId 从token中获取的userId
     * @return 若当前内存中存在userId对应的Player(说明player对象已经被创建)直接返回；若不存在，则新建Player存储并返回
     */
    @Override
    public Player getPlayerByUserId(Long userId) {
        if (playerList.containsKey(userId)) {
            return playerList.get(userId);
        } else {
            // 创建新的Player对象
            Player player = new Player(userId);
            player.getPassedRooms().add(gameMap.getInitRoom().getId());
            playerList.put(userId, player);
            return player;
        }
    }

    /**
     * 回退给定层数，最多回退到初始房间
     *
     * @param quantity 返回的层数，如果为null则返回一层
     * @return 回退后的当前房间
     */
    @Override
    public Room back(Player player, Integer quantity) {
        List<Long> list = player.getPassedRooms();
        for(int i = 0; list.size() > 1 && i < quantity; i++) {
            list.remove(list.size() - 1);
        }
        return gameMapService.getRoomByIndex(gameMap, list.get(list.size() - 1));
    }

    /**
     * @param player 当前user对应的player
     * @param direc 当前user给出操作的方向
     * @return 返回移动到的房间，如果给出的方向有误则返回null
     */
    @Override
    public Room move(Player player, String direc) {
        Room curRoom = getCurrentRoom(player);
        Map<String, Long> roomMap = curRoom.getDirections();
        if(roomMap.containsKey(direc)) {
            // 可以移动
            Long nextRoomId = roomMap.get(direc);
            player.getPassedRooms().add(nextRoomId);
            return gameMapService.getRoomByIndex(gameMap, nextRoomId);
        } else {
            return null;
        }
    }

    @Override
    public Long getCurrentRoomId(Player player) {
        int lastIdx = player.getPassedRooms().size() - 1;
        return player.getPassedRooms().get(lastIdx);
    }

    @Override
    public Room getCurrentRoom(Player player) {
        Long id = getCurrentRoomId(player);
        return gameMapService.getRoomByIndex(gameMap, id);
    }

    public Item getItem(Player player, Integer itemId) {
        List<Item> userItems = player.getUserItems();
        for(Item item : userItems) {
            if(itemId.equals(item.getId())) return item;
        }
        return null;
    }

    @Override
    public boolean deleteItem(Player player, Integer itemId, boolean useItem) {
        // 如果useItem为true则不扣除用户当前载重，若为false则扣除
        List<Item> userItems = player.getUserItems();
        for(Item item : userItems) {
            if(itemId.equals(item.getId())) {
                if(!useItem) {
                    player.setCur_weight(player.getCur_weight() - item.getWeight());
                }
                player.getUserItems().remove(item);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean dropItem(Player player, Integer itemId) {
        Item item = getItem(player, itemId);
        if(item == null) return false;
        roomService.addItem(getCurrentRoom(player), item);
        player.getUserItems().remove(item);
        player.setCur_weight(player.getCur_weight() - item.getWeight());
        return true;
    }

    @Override
    public boolean takeItem(Player player, Integer itemId) {
        Room room = getCurrentRoom(player);
        Item item = roomService.getItem(room, itemId);
        if (item != null && player.getCur_weight() + item.getWeight() <= player.getBearing_capacity()) {
            roomService.reduceItem(getCurrentRoom(player), item);
            player.getUserItems().add(item);
            player.setCur_weight(player.getCur_weight() + item.getWeight());
            return true;
        }
        return false;
    }

}
