package cn.edu.whut.sept.zuul.config;

import cn.edu.whut.sept.zuul.util.SnowFlakeGenerateIdWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author AnthonyCJ
 * @version 1.0
 * @description 雪花算法生成器
 * @date 2023/06/20 14:01
 */
@Configuration
public class SnowFlakeGenerateIdWorkConfig {
    @Bean
    public SnowFlakeGenerateIdWorker createWork(){
        return new SnowFlakeGenerateIdWorker(0L,0L);
    }
}
