package cn.easytop.lesson05.xml;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

public class TestHelloWorld {

	public SqlSessionFactory getSessionFactory() throws IOException{
		String resource = "cn/easytop/lesson05/xml/mybatis.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		return sqlSessionFactory;
	}
	public static SqlSession getSession() throws IOException{
		String resource = "cn/easytop/lesson05/xml/mybatis.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//工廠類
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session=sqlSessionFactory.openSession();
		return session;
	}
	/**
	 * 一级缓存 同一个session对象针对同一份数据的查询 产生的缓存 
	 *  第一次查询时 调用数据 获取数据后 
	 *  通过session设置到一级缓存中
	 *  第二次查询时 通过session一级缓存判断是否存在 相同主键的数据值 如果存在 直接返回引用 否则查询数据库
	 * @throws IOException
	 */
	@Test
	public void testXmlInterface() throws IOException{
		SqlSession session =getSession();
		StudentMapper fm=session.getMapper(StudentMapper.class);
		Student s=fm.queryStudentById("1");
		//从缓存中查询的
		Student s1=fm.queryStudentById("1");
		System.out.println(s==s1);
	}
	
	
	
	/**
	 * 二级缓存 同一个sessionFactory下的不同session 可以共享数据
	 * @throws IOException
	 */
	@Test
	public void testSecondInterface() throws IOException{
		SqlSessionFactory sessionFactory=getSessionFactory();
		SqlSession session =sessionFactory.openSession();
		SqlSession session1 =sessionFactory.openSession();
		
		
		
		StudentMapper fm=session.getMapper(StudentMapper.class);
		Student s=fm.queryStudentById("1");
		//从缓存中查询的
		session.close();
		
		StudentMapper fm1=session1.getMapper(StudentMapper.class);
		Student s1=fm1.queryStudentById("1");
		
		
	}
	
	
	
	
	
	
	
	
}
