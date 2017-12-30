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
		//���S�
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session=sqlSessionFactory.openSession();
		return session;
	}
	/**
	 * һ������ ͬһ��session�������ͬһ�����ݵĲ�ѯ �����Ļ��� 
	 *  ��һ�β�ѯʱ �������� ��ȡ���ݺ� 
	 *  ͨ��session���õ�һ��������
	 *  �ڶ��β�ѯʱ ͨ��sessionһ�������ж��Ƿ���� ��ͬ����������ֵ ������� ֱ�ӷ������� �����ѯ���ݿ�
	 * @throws IOException
	 */
	@Test
	public void testXmlInterface() throws IOException{
		SqlSession session =getSession();
		StudentMapper fm=session.getMapper(StudentMapper.class);
		Student s=fm.queryStudentById("1");
		//�ӻ����в�ѯ��
		Student s1=fm.queryStudentById("1");
		System.out.println(s==s1);
	}
	
	
	
	/**
	 * �������� ͬһ��sessionFactory�µĲ�ͬsession ���Թ�������
	 * @throws IOException
	 */
	@Test
	public void testSecondInterface() throws IOException{
		SqlSessionFactory sessionFactory=getSessionFactory();
		SqlSession session =sessionFactory.openSession();
		SqlSession session1 =sessionFactory.openSession();
		
		
		
		StudentMapper fm=session.getMapper(StudentMapper.class);
		Student s=fm.queryStudentById("1");
		//�ӻ����в�ѯ��
		session.close();
		
		StudentMapper fm1=session1.getMapper(StudentMapper.class);
		Student s1=fm1.queryStudentById("1");
		
		
	}
	
	
	
	
	
	
	
	
}
