package cn.hwolf.module.cache;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.Tuple;

import java.util.List;
import java.util.Map;

/**
 * @author hwolf
 * @email h.wolf@qq.com
 * @date 2018/3/15.
 */
public class JedisTemplate {

    public void delBigHash(String host, int port, String password, String bigHashKey) {
        // redis 客户端
        Jedis jedis = new Jedis(host, port);
        // 登录
        if (password != null && "".equals(password)) {
            jedis.auth(password);
        }
        // 一次性获取数据数目：100条
        ScanParams scanParams = new ScanParams().count(100);
        // 游标
        String cursor = "0";
        do {
            // 获得扫描结果集
            ScanResult<Map.Entry<String, String>> scanResult = jedis.hscan(bigHashKey, cursor, scanParams);
            // 直接获得结果
            List<Map.Entry<String, String>> entryList = scanResult.getResult();
            if (entryList != null && !entryList.isEmpty()) {
                // 删除指定key
                for (Map.Entry<String, String> entry : entryList) {
                    jedis.hdel(bigHashKey, entry.getKey());
                }
            }
            // 获取游标
            cursor = scanResult.getStringCursor();
        } while (!"0".equals(cursor));
        jedis.del(bigHashKey);
    }

    public void delBigList(String host, int port, String password, String bigListKey) {
        // redis 客户端
        Jedis jedis = new Jedis(host, port);
        // 登录
        if (password != null && "".equals(password)) {
            jedis.auth(password);
        }
        long llen = jedis.llen(bigListKey);
        int counter = 0;
        int left = 100;
        while (counter < left) {
            // 每次从左侧截掉100个
            jedis.ltrim(bigListKey, left, counter);
            counter += left;
        }
        // 最终删除key
        jedis.del(bigListKey);
    }

    public void delBigSet(String host, int port, String password, String bigSetKey) {
        // redis 客户端
        Jedis jedis = new Jedis(host, port);
        // 登录
        if (password != null && "".equals(password)) {
            jedis.auth(password);
        }
        // 一次性获取数据数目：100条
        ScanParams scanParams = new ScanParams().count(100);
        // 游标
        String cursor = "0";
        do {
            // 获得扫描结果集
            ScanResult<String> scanResult = jedis.sscan(bigSetKey, cursor, scanParams);
            // 直接获得结果
            List<String> members = scanResult.getResult();
            if (members != null && !members.isEmpty()) {
                // 删除指定key
                for (String member : members) {
                    jedis.srem(bigSetKey, member);
                }
            }
            // 获取游标
            cursor = scanResult.getStringCursor();
        } while (!"0".equals(cursor));

        jedis.del(bigSetKey);
    }

    public void delBigZset(String host, int port, String password, String bigZsetKey) {
        Jedis jedis = new Jedis(host, port);
        if (password != null && "".equals(password)) {
            jedis.auth(password);
        }
        ScanParams scanParams = new ScanParams().count(100);
        String cursor = "0";
        do {
            ScanResult<Tuple> scanResult = jedis.zscan(bigZsetKey, cursor, scanParams);
            List<Tuple> tupleList = scanResult.getResult();
            if (tupleList != null && !tupleList.isEmpty()) {
                for (Tuple tuple : tupleList) {
                    jedis.zrem(bigZsetKey, tuple.getElement());
                }
            }
            cursor = scanResult.getStringCursor();
        } while (!"0".equals(cursor));

        jedis.del(bigZsetKey);

    }
}
