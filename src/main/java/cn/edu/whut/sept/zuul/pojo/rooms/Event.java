package cn.edu.whut.sept.zuul.pojo.rooms;

import cn.edu.whut.sept.zuul.pojo.GameMap;
import cn.edu.whut.sept.zuul.pojo.Player;

/**
 * @author AnthonyCJ
 * @version 1.0
 * @description 事件接口，房间可以通过该接口绑定事件信息
 * @date 2023/06/18 14:05
 */
public interface Event {
    public boolean doEvent(GameMap gameMap, Player player);

    public String curSuccessMsg();
}
