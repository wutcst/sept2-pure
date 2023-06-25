package cn.edu.whut.sept.zuul.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author AnthonyCJ
 * @version 1.0
 * @description 物品类，Item类
 * @date 2023/06/18 12:50
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Item {
    private Integer id;     // 物品id
    private String name;    // 物品名称
    private int weight;     // 物品重量
    private boolean available;  // 能否使用（标识）
}
