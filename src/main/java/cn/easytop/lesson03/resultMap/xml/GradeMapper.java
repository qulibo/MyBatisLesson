package cn.easytop.lesson03.resultMap.xml;

import java.util.List;

public interface GradeMapper {
	
	/*
	 * ��ѯ���еİ༶
	 */
	public List queryAllGrade();
	/**
	 * ͨ��id��ѯ���еİ༶
	 * @param gid
	 * @return
	 */
	public Grade queryGrade(String gid);
	
}
