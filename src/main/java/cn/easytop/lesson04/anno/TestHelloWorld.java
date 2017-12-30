package cn.easytop.lesson04.anno;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import cn.easytop.lesson04.xml.Student;

public class TestHelloWorld {

	public static SqlSession getSession() throws IOException{
		String resource = "cn/easytop/lesson04/anno/mybatis.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//工廠類
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session=sqlSessionFactory.openSession();
		return session;
	}
	
	@Test
	public void testXmlInterface() throws IOException{
		SqlSession session =getSession();
		StudentMapper fm=session.getMapper(StudentMapper.class);
		Student s=new Student();
		//s.setSname("张");
		s.setAddress("深圳");
		List<Student> list=fm.queryStudent(s);
		for(Student g:list){
			System.out.println(g.getSname());
		}
	}
	
	@Test
	public void testChooseInterface() throws IOException{
		SqlSession session =getSession();
		StudentMapper fm=session.getMapper(StudentMapper.class);
		Integer sex=0;
		List<Student> list=fm.queryBySex(sex);
		for(Student g:list){
			System.out.println(g.getSname());
		}
	}

	
	@Test
	public void testUpdateInterface() throws IOException{
		SqlSession session =getSession();
		StudentMapper fm=session.getMapper(StudentMapper.class);
		Student student=new Student();
		student.setSid("1");
		student.setSname("张三2号");
	    fm.updateStudent(student);
	    session.commit();
	}
	
	@Test
	public void testForEachInterface() throws IOException{
		SqlSession session =getSession();
		StudentMapper fm=session.getMapper(StudentMapper.class);
		List list=new ArrayList();
		list.add("0");
		list.add("2");
		list.add("3");
		List<Student> slist=fm.queryStudentbyAnyGrade(list);
		for(Student g:slist){
			System.out.println(g.getSname());
		}
	    session.commit();
	}
	
	
}
