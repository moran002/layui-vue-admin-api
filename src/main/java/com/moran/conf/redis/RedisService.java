package com.moran.conf.redis;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author : moran
 * 过期时间: 不指定时间时过期时间为-1 永不过期, 传入过期时间则时间单位为秒
 * 基础类型值加入泛型返回值, 用户需明确知道返回类型
 */
@Component
@Slf4j
public final class RedisService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 指定缓存失效时间
     * @param time 时间单位为分钟
     * @author moran
     **/
    public void expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            log.error("<!---- 指定缓存失效时间失败:{} ----!>", e.getMessage());
        }
    }

    /**
     * 根据key获取过期时间
     * @author moran
     * @return 当返回值为-2时代表当前key不存在, -1 代表永不过期, 其他: x分钟后失效
     **/
    public long getExpire(String key) {
        Long expire = redisTemplate.getExpire(key, TimeUnit.SECONDS);
        return ObjectUtils.isEmpty(expire)? -2: expire;
    }

    /**
     * 判断key是否存在
     * @author moran
     **/
    public boolean hasKey(String key) {
        try {
            Boolean aBoolean = redisTemplate.hasKey(key);
            return aBoolean != null && aBoolean;
        }catch (Exception e) {
            log.error("<!---- 判断key是否存在:{} ----!>", e.getMessage());
            return false;
        }
    }

    /**
     * 移除缓存
     * @param key 可传入单个或多个key
     * @author moran
     **/
    @SuppressWarnings("unchecked")
    public void delete(String... key) {
        if (!ObjectUtils.isEmpty(key)) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            }else {
                redisTemplate.delete((Collection<String>) CollectionUtils.arrayToList(key));
            }
        }
    }

    /**
     * 模糊移除缓存, 通配右侧例: 移除带有token_的全部可以 只需要传入token_即可
     * @author moran
     **/
    public void  deleteByLikeKey(String key) {
        if (key== null || "".equals(key.trim())) {
            return;
        }
        Set<String> keys = redisTemplate.keys(key + "*");
        if (keys != null) {
            redisTemplate.delete(keys);
        }
    }
    /**
     * 清空缓存
     * @author moran
     **/
    public void deleteAll() {
        Set<String> keys = redisTemplate.keys("*");
        if (keys != null) {
            redisTemplate.delete(keys);
        }
    }

    /**
     * 获取值
     * @author moran
     * 返回泛型, 可选择返回Object 也可选择指定类型<>String,int,double等</> 需明确知道具体类型
     **/
    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        return key == null? null : (T) redisTemplate.opsForValue().get(key);
    }

    /**
     * 获取redis中模糊的key值集合
     * @author :moran
     **/
    public Set<String> getKeys(String key) {
        return redisTemplate.keys(key.concat("*"));
    }

    /**
     * 设置键值
     * @author moran
     **/
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        }catch (Exception e) {
            log.error("<!---- 设置键值:{} ----!>", e.getMessage());
            return false;
        }
    }

    /**
     * 设置键值并指定过期时间, 当指定时间小于等于0时调用设置键值方法
     * @author moran
     *
     **/
    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            }else {
                return set(key, value);
            }
            return true;
        }catch (Exception e) {
            log.error("<!---- 设置键值并指定过期时间:{} ----!>", e.getMessage());
            return false;
        }
    }

    /**
     * 键值递增
     * @author :moran
     **/
    public long increment(String key) {
        Long increment = redisTemplate.opsForValue().increment(key);
        if (increment == null) {
            throw new RuntimeException("不存在递增指令");
        }
        return increment;
    }

    /**
     * 指定值增长数
     * @author moran
     **/
    public long increment(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增指令必须大于0");
        }
        Long count = redisTemplate.opsForValue().increment(key, delta);
        return count!= null? count:0;
    }

    /**
     * 键值递减
     * @author :moran
     **/
    public long deIncrement(String key) {
        Long decrement = redisTemplate.opsForValue().decrement(key);
        if (decrement == null) {
            throw new RuntimeException("递减指令不存在");
        }
        return decrement;
    }

    /**
     * 指定递减数
     * @author :moran
     **/
    public long deIncrement(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减指令必须大于0");
        }
        Long decrement = redisTemplate.opsForValue().decrement(key, delta);
        if (decrement == null) {
            throw new RuntimeException("递减指令不存在");
        }
        return decrement;
    }
}
