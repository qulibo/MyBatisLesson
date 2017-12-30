package cn.easytop.lesson03.resultMap.anno;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.easytop.lesson03.resultMap.xml.Grade;

public interface GradeMapper {
	
	/*
	 * 查询所有的班级
	 */
	public List queryAllGrade();
	/**
	 * 通过id查询所有的班级
	 * @param gid
	 * @return
	 */
	@Results(
			{
			@Result(property="gname1",column="gname"),
			@Result(property="studentList",javaType=ArrayList.class, column="gid",many=@Many(select="cn.easytop.lesson03.resultMap.anno.StudentMapper.queryStudentByGid"))
			}
	)
	@Select("select * from grade where gid=#{0}")
	public Grade queryGrade(String gid);
	
	
	@Results(
			@Result(property="gname1",column="gname")
	)
	@Select("select * from grade where gid=#{0}")
    public Grade queryGradeByGradeId(String gid);
	
}
