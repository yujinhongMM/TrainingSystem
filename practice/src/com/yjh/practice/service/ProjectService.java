package com.yjh.practice.service;

import java.util.ArrayList;
/**
 * 
 * Description
 * @author YJH
 * @date 2018��6��4��  
 *
 */
public interface ProjectService {
	/**
	 * ����project��No,���+���
	 * @return
	 */
	public String getProjectNo();
	/**
	 * �����꼶������Grade
	 * @param n 1,2,3,4
	 * @return
	 */
	public int getStuGrade(int n);
	
	/**
	 * �жϷ����Ƿ������û�
	 * @param username
	 * @param p_no
	 * @return
	 */
	public boolean findProjectBelongToUserByPNo(String username,String p_no);
	
	/**
	 * ��ѡ��ʵѵ��ʼʱ��֮����ѡ��ʵѵ����ʱ��֮ǰ����true
	 * @return
	 */
	public boolean findPracticeIsUnderWay();
	
	/**
	 * �����ʵѵ��ʼʱ��֮�������ʵѵ����ʱ��֮ǰ����true
	 * @return
	 */
	public boolean findAddPracticeIsUnderWay();
	
	/**
	 * ���ش�ϵͳ��ʼ�����ڵ���ݵ�����
	 * @return
	 */
	public int[] findAllAddProjectYear();
	
	/**
	 * �õ�����רҵ
	 * @return
	 */
	public ArrayList<String> findAllProfessional();
}

