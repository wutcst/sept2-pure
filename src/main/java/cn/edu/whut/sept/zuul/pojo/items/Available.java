package cn.edu.whut.sept.zuul.pojo.items;

import cn.edu.whut.sept.zuul.pojo.Player;

/**
 * @author AnthonyCJ
 * @version 1.0
 * @description 实现该接口的物品可以被使用
 * @date 2023/06/25 13:01
 */
public interface Available {
    public boolean useItem(Player player);

    public String curSuccessMsg();
}
