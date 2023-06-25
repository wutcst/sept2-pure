package cn.edu.whut.sept.zuul.pojo.items;

import cn.edu.whut.sept.zuul.pojo.Item;
import cn.edu.whut.sept.zuul.pojo.Player;
import lombok.Data;

/**
 * @author AnthonyCJ
 * @version 1.0
 * @description TODO
 * @date 2023/06/25 13:00
 */
@Data
public class MagicCookie extends Item implements Available {

    public MagicCookie() {
        super(0, "魔法饼干，+10载重", 1, true);
    }

    @Override
    public boolean useItem(Player player) {
        player.setBearing_capacity(player.getBearing_capacity() + 10);
        return true;
    }

    @Override
    public String curSuccessMsg() {
        return "好神奇的饼干！我感觉身体轻盈了很多……难道是力量增加了……\n最大载重 +10 ~";
    }
}
