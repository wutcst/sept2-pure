package cn.edu.whut.sept.zuul.pojo.rooms;

import cn.edu.whut.sept.zuul.pojo.GameMap;
import cn.edu.whut.sept.zuul.pojo.Player;
import cn.edu.whut.sept.zuul.pojo.Room;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author AnthonyCJ
 * @version 1.0
 * @description 有传送功能的房间
 * @date 2023/06/18 13:08
 */
public class TransferRoom extends Room implements Event {

    public TransferRoom() {
        super("风暴之地",
                "风暴之地位于上古遗址的南侧，南临神秘三角洲，常年风暴肆虐，传闻误入该境者无人生还。",
                true);
    }

    /**
     * 进行随机传送.
     *
     * @param gameMap gameMap对象
     * @param player player对象
     * @return true / false
     */
    @Override
    public boolean doEvent(GameMap gameMap, Player player) {
        if(gameMap == null || player == null) return false;
        Map<Long, Room> rooms = gameMap.getRooms();
        List<Long> keys = rooms.keySet().stream()
                .filter(x -> !x.equals(this.getId()))
                .collect(Collectors.toList());
        Random random = new Random();
        Long randomKey = keys.get(random.nextInt(keys.size()));
        player.getPassedRooms().add(randomKey);
        // 获得随机房间，不要传送到自己的房间
        return true;
    }

    @Override
    public String curSuccessMsg() {
        return "已跳转至新的房间！";
    }
}
