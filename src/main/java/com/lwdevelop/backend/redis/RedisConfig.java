package com.lwdevelop.backend.redis;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

@Configuration
public class RedisConfig {

    private static final String url = "redis://default:7zn3XT0HIykqPOgGQKRwYqZajQ9QqbFR@redis-16063.c9.us-east-1-4.ec2.cloud.redislabs.com:16063";
    
    public JedisPool getPool() {
        try {
            URI redisUri = new URI(url);
            JedisPool pool = new JedisPool(new JedisPoolConfig(),
                    redisUri.getHost(),
                    redisUri.getPort(),
                    Protocol.DEFAULT_TIMEOUT,
                    redisUri.getUserInfo().split(":", 2)[1]);
                    return pool;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Jedis jedis = getPool().getResource();

    public void setRedisData(String key,String value){
        jedis.set(key, value);
    }

    public void delRedisData(String key){
        jedis.del(key);
    }

}
