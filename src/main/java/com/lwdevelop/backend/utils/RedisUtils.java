package com.lwdevelop.backend.utils;

import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

@Component
public class RedisUtils {

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

    public String getFriend(String key) {
        return jedis.get(key);
    }

    public void addFriend(String key, String id, String username) {
        if (getFriend(key)==null) {
            String str = id+":"+username;
            jedis.set(key, str);
        } else {
            String data = jedis.get(key);
            jedis.del(key);
            String str = data+","+id+":"+username;
            jedis.set(key, str);
        }
    }
/*     public void addFriend(String key, String id, String username) {
        if (getFriend(key)==null) {
            String jsonStr = "{\"" + id + "\":\"" + username + "\"}";
            jedis.set(key, jsonStr);
        } else {
            String data = jedis.get(key);
            jedis.del(key);
            String jsonStr = data.substring(0, data.length() - 1) + ",\"" + id + "\":\"" + username + "\"}";
            jedis.set(key, jsonStr);
        }
    } */

    public void delFriend(String key) {
        jedis.del(key);
    }

}
