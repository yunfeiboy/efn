package com.nowbio.database.efn;



import com.nowbio.database.efn.common.service.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author yun
 * @since 2021/5/10 9:49
 */
@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisService redisService;
    @Test
    void contextLoads() {
        redisService.set("yunfei", "yunfeiredis");
        System.out.println(redisService.get("yunfei"));
    }

}
