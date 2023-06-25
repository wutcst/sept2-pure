package cn.edu.whut.sept.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author AnthonyCJ
 * @version 1.0
 * @description SpringBoot启动类
 * @date 2023/06/17 10:07
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableWebMvc
public class ZuulSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulSpringbootApplication.class, args);
    }

}
