package cn.qinguu.content.jedis;

/**
 * @program: JedisClient
 * @Description redis客户端
 * @Author cy
 * @Date 2019/4/99:57
 * @Version 1.0
 **/
public interface JedisClient {
    //设置值
    String set(String key, String value);
    //根据key获取值
    String get(String key);
    //根据key判断值是否存在
    Boolean exists(String key);
    //设置key的过期时间
    Long expire(String key, int seconds);
    //查询key的过期时间
    Long ttl(String key);
    //key对应的值自增1
    Long incr(String key);
    //设置hash值
    Long hset(String key, String field, String value);
    //获取hash值
    String hget(String key, String field);
    //根据key和field删除值
    Long hdel(String key, String... field);
}
