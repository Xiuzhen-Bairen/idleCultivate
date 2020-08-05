import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

//Double Chcek Lock
public class JedisPoolUtil {

    private static volatile JedisPool jedisPool = null;

    // 构造方法私有化
    private JedisPoolUtil() {
    }

    // 通过一个方法返回池的实例
    public static JedisPool getJedisPoolInstance() {

        if (null == jedisPool) {
            // 锁定对象
            synchronized (JedisPoolUtil.class) {
                if (null == jedisPool) {

                    JedisPoolConfig poolConfig = new JedisPoolConfig();
                    // poolConfig.setMaxTotal(1000);
                    poolConfig.setMaxTotal(200);
                    poolConfig.setMaxIdle(50);
                    poolConfig.setMaxWaitMillis(5000);
//                    poolConfig.setTestOnBorrow(false);
                    poolConfig.setTestOnBorrow(true);
                    poolConfig.setTestOnReturn(false);

                    jedisPool = new JedisPool(poolConfig, "localhost", 6379);
                }
            }
        }

        return jedisPool;

    }

    public static void release(JedisPool jedispool,Jedis jedis) {
        if(null!=jedis) {
            //jedisPool.returnResource(jedis) ---> jedis.close();
            //升级版的jedis用close来替代returnResource？
            //jedispool.getResource();
            jedis.close();
        }
    }
}
