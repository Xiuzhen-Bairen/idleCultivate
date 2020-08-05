import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisPoolTest {
    public static void main(String[] args) {
        JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set("k2", "siyi2");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JedisPoolUtil.release(jedisPool, jedis);
        }

    }
}
