package cn.easytop.lesson02.xml;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.easytop.lesson02.Food;

public interface FoodMapper {
	/**
	 * �������� ��ѯ���еĲ�Ʒ��Ϣ
	 * @param foodName
	 * @return
	 */
	public List queryFood(String foodName,String price);
	/*
	 * 
	 */
	public List queryFoodByFoodName(@Param("foodName")String foodName);
	
	/**
	 * ����idɾ����Ʒ
	 * @param foodId
	 */
	public void deleteFood(String foodId);
	/**
	 * ����food
	 * @param food
	 */
	public void saveFood(Food food);
	
	
	
	
}
