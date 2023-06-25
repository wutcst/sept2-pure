package cn.edu.whut.sept.zuul.controller;

import cn.edu.whut.sept.zuul.enums.EnumResult;
import cn.edu.whut.sept.zuul.pojo.*;
import cn.edu.whut.sept.zuul.pojo.items.Available;
import cn.edu.whut.sept.zuul.pojo.rooms.Event;
import cn.edu.whut.sept.zuul.service.PlayerService;
import cn.edu.whut.sept.zuul.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author AnthonyCJ
 * @version 1.0
 * @description PlayerController层，用于与前端进行逻辑交互
 * @date 2023/06/22 20:24
 */

@RestController
@RequestMapping("/player")
public class PlayerController {
    @Autowired
    @Qualifier("gameMap_0")
    private GameMap gameMap;

    @Autowired
    private PlayerService playerService;

    /**
     * 获取用户对应的player所在的房间的信息
     *
     * @param request 获取token值
     * @return 返回当前的房间对象
     */
    @GetMapping("/curRoomInfo")
    public ResultBean<Object> getRoomInfo(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Long userId = JwtUtil.getUserId(token);
        // 获取当前用户对应的player对象
        Player player = playerService.getPlayerByUserId(userId);
        Room room = playerService.getCurrentRoom(player);
        return new ResultBean<>(EnumResult.SUCCESS, room);
    }

    /**
     * 获取用户对应的player的信息
     *
     * @param request 获取token值
     * @return 返回当前的用户对象
     */
    @GetMapping("/playerInfo")
    public ResultBean<Object> getPlayerInfo(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Long userId = JwtUtil.getUserId(token);
        // 获取当前用户对应的player对象
        Player player = playerService.getPlayerByUserId(userId);
        return new ResultBean<>(EnumResult.SUCCESS, player);
    }

    /**
     * 用户对应的player进行移动
     *
     * @param direction 对应的方向
     * @param request   获取token值
     * @return 若给定direction存在下一个房间，则返回当前房间信息，否则返回异常信息
     */
    @GetMapping("/move")
    public ResultBean<Object> move(@RequestParam("direction") String direction, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Long userId = JwtUtil.getUserId(token);
        Player player = playerService.getPlayerByUserId(userId);
        Room room = playerService.move(player, direction);
        if (room == null) {
            return new ResultBean<>(EnumResult.USER_MOVE_FAIL);
        } else {
            return new ResultBean<>(EnumResult.SUCCESS, room);
        }
    }

    /**
     * 用户对应的player进行回退操作
     *
     * @param quantity 可选是否提供，回退的数量
     * @param request  获取token值
     * @return 若quantity超过最大层数，返回初始房间，否则返回回退到的房间信息
     */
    @GetMapping("/back")
    public ResultBean<Object> back(@RequestParam(value = "quantity", required = false) Integer quantity,
                                   HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Long userId = JwtUtil.getUserId(token);
        Player player = playerService.getPlayerByUserId(userId);
        Room room = playerService.back(player, quantity);
        return new ResultBean<>(EnumResult.SUCCESS, room);
    }

    /**
     * @param itemId  需要获取的itemId，即物品的编号
     * @param request 获取token值
     * @return 若删除成功
     */
    @GetMapping("/take")
    public ResultBean<Object> takeItem(@RequestParam("itemId") Integer itemId,
                                       HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Long userId = JwtUtil.getUserId(token);
        Player player = playerService.getPlayerByUserId(userId);
        if (playerService.takeItem(player, itemId)) {
            return new ResultBean<>(EnumResult.SUCCESS, player.getUserItems());
        } else {
            return new ResultBean<>(EnumResult.USER_TAKE_FAIL);
        }

    }

    /**
     * @param itemId  需要放下物品的itemId，即物品的编号
     * @param request 获取token值
     * @return 若删除成功返回用户的物品集合，否则提示
     */
    @GetMapping("/drop")
    public ResultBean<Object> dropItem(@RequestParam("itemId") Integer itemId,
                                       HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Long userId = JwtUtil.getUserId(token);
        Player player = playerService.getPlayerByUserId(userId);
        if (playerService.dropItem(player, itemId)) {
            return new ResultBean<>(EnumResult.SUCCESS, player.getUserItems());
        } else {
            return new ResultBean<>(EnumResult.USER_NO_HOLD_ITEM);
        }
    }

    /**
     * @param itemId  需要使用的物品的itemId，即物品的编号
     * @param request 获取token值
     * @return 若使用成功则删除物品，返回成功信息，否则提示失败
     */
    @GetMapping("/useItem")
    public ResultBean<Object> useItem(@RequestParam("itemId") Integer itemId,
                                      HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Long userId = JwtUtil.getUserId(token);
        Player player = playerService.getPlayerByUserId(userId);
        Item item = playerService.getItem(player, itemId);
        // 用户未持有该物品
        if (item == null) return new ResultBean<>(EnumResult.USER_NO_HOLD_ITEM);
        if (item instanceof Available) {
            // 如果实现了Available接口，说明可以使用该物品
            Available useItem = (Available) item;
            useItem.useItem(player);
            playerService.deleteItem(player, itemId, false);
            return new ResultBean<>(EnumResult.SUCCESS, useItem.curSuccessMsg());
        } else {
            return new ResultBean<>(EnumResult.USER_USE_ITEM_FAIL);
        }
    }

    /**
     * 若房间存在相关事件，可以调用该接口进行相应的事件
     *
     * @param request 获取token
     * @return 返回是否成功
     */
    @GetMapping("/doEvent")
    public ResultBean<Object> doEvent(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Long userId = JwtUtil.getUserId(token);
        Player player = playerService.getPlayerByUserId(userId);
        // 获取当前房间，判断是否存在设定的功能
        Room currentRoom = playerService.getCurrentRoom(player);
        if (currentRoom instanceof Event) {
            Event roomEvent = (Event) currentRoom;
            roomEvent.doEvent(gameMap, player);
            return new ResultBean<>(EnumResult.SUCCESS, roomEvent.curSuccessMsg());
        } else {
            return new ResultBean<>(EnumResult.ROOM_NO_EVENT);
        }
    }
}
