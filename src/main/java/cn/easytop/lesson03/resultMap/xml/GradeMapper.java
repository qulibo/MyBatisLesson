package cn.easytop.lesson03.resultMap.xml;

import java.util.List;

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
	public Grade queryGrade(String gid);
	
}
