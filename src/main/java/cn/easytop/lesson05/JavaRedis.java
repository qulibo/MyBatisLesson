package cn.easytop.lesson05;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import redis.clients.jedis.Jedis;

public class JavaRedis {

	/**
	 * 1=Student对象
	 * 写入数到redis时
	 *   jedis.set(主键 ,objectToByteArray(Student对象))
	 * 序列化
	 * @param obj
	 * @return
	 * @throws IOException 
	 */
	public static byte[] objectToByteArray(Object obj) throws IOException{
		ByteOutputStream boss=new ByteOutputStream();
		ObjectOutputStream oos=new ObjectOutputStream(boss);
		oos.writeObject(obj);
		return boss.getBytes();
	}
	/**
	 * byte[] bt=get("主键")
	 * Student s=byteArrayToObject(bt);
	 * 反序列化字节数组为 对象
	 * @param bt
	 * @return
	 * @throws IOException
	 */
	public static Object byteArrayToObject(byte[] bt) throws Exception{
		ByteInputStream bis=new ByteInputStream(bt,bt.length);
		ObjectInputStream ois=new ObjectInputStream(bis);
		return ois.readObject();
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Jedis jedis=new Jedis("localhost",6379);
		//键值对
		jedis.set("myname", "jiaozi");
		
		System.out.println(jedis.get("myname"));
		
		
	}

}
