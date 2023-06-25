package cn.edu.whut.sept.zuul.service.impl;

import cn.edu.whut.sept.zuul.pojo.GameMap;
import cn.edu.whut.sept.zuul.pojo.Room;
import cn.edu.whut.sept.zuul.service.GameMapService;
import cn.edu.whut.sept.zuul.util.SnowFlakeGenerateIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author AnthonyCJ
 * @version 1.0
 * @description 地图服务接口实现类
 * @date 2023/06/25 13:38
 */
@Service
public class GameMapServiceImpl implements GameMapService {
    @Autowired
    private SnowFlakeGenerateIdWorker snowFlake;

    /**
     * 向迷宫中添加房间信息
     *
     * @param room 提供房间信息即可自动生成唯一Room id，不需要手动填写id
     * @return 返回是否添加成功，此外调用完成后
     */
    public boolean addRoom(GameMap gameMap, Room room) {
        room.setId(snowFlake.nextId());
        if (gameMap.getRooms().containsKey(room.getId())) {
            return false;
        } else {
            gameMap.getRooms().put(room.getId(), room);
            return true;
        }
    }

    /**
     * @param id Room对应的Id
     * @return 返回迷宫中该Id对应的房间号
     */
    public Room getRoomByIndex(GameMap gameMap, Long id) {
        return gameMap.getRooms().getOrDefault(id, null);
    }
}
