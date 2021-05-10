package com.nowbio.database.efn.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * redis 配置
 * @author yun
 * @since 2021/5/10 11:46
 */
@EnableCaching
@Configuration
public class RedisConfig extends BaseRedisConfig {

}
