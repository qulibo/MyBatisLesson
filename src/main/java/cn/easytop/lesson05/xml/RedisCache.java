package cn.easytop.lesson05.xml;

import java.io.IOException;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;

import redis.clients.jedis.Jedis;
import cn.easytop.lesson05.JavaRedis;

public class RedisCache implements Cache {
	/**
	 * 操作redis对象
	 */
	Jedis jedis=new Jedis("localhost",6379);
	
	/**
	 * 缓存的id
	 */
	private String cacheId;
	public RedisCache(String cacheId){
		this.cacheId=cacheId;
	}
	@Override
	public void clear() {
		//jedis.flushDB();
	}

	@Override
	public String getId() {
		return cacheId;
	}
	/**
	 * mybatis自动调用getObject检测是否缓存中存在
	 */
	@Override
	public Object getObject(Object key) {
		try {
			byte[] bt=jedis.get(JavaRedis.objectToByteArray(key));
			if(bt==null){
				return null;
			}
			return JavaRedis.byteArrayToObject(bt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		return new ReentrantReadWriteLock();
	}

	@Override
	public int getSize() {
		return 1;
	}
	/**
	 * mybatis读取数据时 将数据库中读取的数据 通过
	 *   putObject设置到缓存中
	 */
	@Override
	public void putObject(Object key, Object value) {
		//写入redis
		try {
			jedis.set(JavaRedis.objectToByteArray(key), JavaRedis.objectToByteArray(value));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * mybatis缓存策略 自动判断内存的大小 绝对是否删除某些过期 久远的数据
	 * @param key
	 * @return
	 */
	@Override
	public Object removeObject(Object key) {
		Object obj=getObject(key);
		try {
			jedis.del(JavaRedis.objectToByteArray(key));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return obj;
	}

}
