package cn.edu.whut.sept.zuul.config;

import cn.edu.whut.sept.zuul.pojo.GameMap;
import cn.edu.whut.sept.zuul.pojo.Item;
import cn.edu.whut.sept.zuul.pojo.Room;
import cn.edu.whut.sept.zuul.pojo.items.MagicCookie;
import cn.edu.whut.sept.zuul.pojo.rooms.TransferRoom;
import cn.edu.whut.sept.zuul.service.GameMapService;
import cn.edu.whut.sept.zuul.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author AnthonyCJ
 * @version 1.0
 * @description TODO
 * @date 2023/06/25 13:42
 */
@Configuration
@Component
public class GameMapConfig {
    @Autowired
    private GameMapService gameMapService;

    //    public void setMazeService(MazeService mazeService) {
//        this.mazeService = mazeService;
//    }
    @Autowired
    private RoomService roomService;


    @Bean(name = "gameMap_0")
    public GameMap createMaze(){
        GameMap gameMap = new GameMap();
        Room room1 = new Room("神秘国度", "欢迎您来到神秘国度！此处是您神秘国度之旅的起点——中土。");
        Room room2 = new Room("沙漠绿洲", "位于卡兰多大陆西侧的噶尔喀什荒漠是方圆一百六十万平方公里的无人区，传说荒漠深处有一眼神奇的月牙泉，孕育了荒漠中的天堂——绿洲。");
        Room room3 = new Room("文化之都", "莱恩城位于中土的东侧，相较于卡兰多大陆的其他地方，这里有历史悠久的独特文化与文明大陆的自由之风。");
        Room room4 = new Room("林海雪原", "卡兰多大陆极北之地，数百年来一直由都铎家族统治，成功抵御了数次蛮族的侵略，是中土以北最坚固的防线。");
        Room room5 = new Room("上古遗址", "破碎之地，满目疮痍，随处可见残垣断壁。传闻这里的文明曾盛极一时，后来发生了一场惊天大战，一切湮没于历史的尘埃。");
        Room room6 = new Room("东方明珠", "素来享有“水之城”美誉的东方明珠，位于卡兰多大陆东南沿海的一处小岛上，四面环海，风景独秀，是神秘国度的经济、金融中心。");
        // 设置了随机传送点房间
        Room room7 = new TransferRoom();
        gameMap.setInitRoom(room1);

        roomService.addItem(room1, new MagicCookie());
        roomService.addItem(room2, new MagicCookie());
        roomService.addItem(room3, new MagicCookie());
        roomService.addItem(room4, new MagicCookie());
        roomService.addItem(room5, new MagicCookie());
        roomService.addItem(room6, new MagicCookie());
        roomService.addItem(room7, new MagicCookie());
        roomService.addItem(room2, new Item(1, "荒漠萤石", 2, false));
        roomService.addItem(room3, new Item(2, "《卡兰多大陆简史》", 30, false));
        roomService.addItem(room4, new Item(3, "掉落的松塔", 5, false));
        roomService.addItem(room5, new Item(4, "玄铁重剑", 99, false));
        roomService.addItem(room6, new Item(5, "破损的魔法手册", 6, false));
        roomService.addItem(room7, new Item(6, "一串珍珠项链", 2, false));
        roomService.addItem(room1, new Item(7, "皮质马鞍", 15, false));
        roomService.addItem(room2, new Item(8, "翡翠绿枝", 1, false));
        roomService.addItem(room3, new Item(9, "一把生锈的钥匙", 3, false));
        roomService.addItem(room4, new Item(10, "牦牛的断角", 4, false));
        roomService.addItem(room1, new Item(11, "一段结实的绳索", 2, false));
        roomService.addItem(room5, new Item(12, "精钢盾牌", 20, false));

        gameMapService.addRoom(gameMap,room1);
        gameMapService.addRoom(gameMap,room2);
        gameMapService.addRoom(gameMap,room3);
        gameMapService.addRoom(gameMap,room4);
        gameMapService.addRoom(gameMap,room5);
        gameMapService.addRoom(gameMap,room6);
        gameMapService.addRoom(gameMap,room7);
        Map<String, Long> map1 = new HashMap<>();
        Map<String, Long> map2 = new HashMap<>();
        Map<String, Long> map3 = new HashMap<>();
        Map<String, Long> map4 = new HashMap<>();
        Map<String, Long> map5 = new HashMap<>();
        Map<String, Long> map6 = new HashMap<>();
        Map<String, Long> map7 = new HashMap<>();
        map1.put("向西", room2.getId());
        map1.put("向东", room3.getId());
        map1.put("向北", room4.getId());
        map1.put("向南", room5.getId());

        map2.put("向东", room1.getId());

        map3.put("向西", room1.getId());
        map3.put("向南", room6.getId());

        map4.put("向南", room1.getId());

        map5.put("向北", room1.getId());
        map5.put("向东", room6.getId());
        map5.put("向南", room7.getId());

        map6.put("向西", room5.getId());
        map6.put("向北", room3.getId());


        room1.setDirections(map1);
        room2.setDirections(map2);
        room3.setDirections(map3);
        room4.setDirections(map4);
        room5.setDirections(map5);
        room6.setDirections(map6);
        room7.setDirections(map7);
        return gameMap;
    }
}
