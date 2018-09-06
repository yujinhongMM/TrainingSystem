package com.yjh.practice.service;

import java.util.List;

import com.yjh.practice.model.Student;
/**
 * 
 * Description ѧ����Ϣ��������ӿ� 
 * @author YJH
 * @date 2018��6��2��  
 *
 */


public interface StudentService {
	/**
	 * ����һ��ѧ����¼
	 * @param: student һ��ѧ��ʵ��
	 * @return: true ����ɹ���false ����ʧ��
	 */
	public boolean insert(Student student);
	
	/**
	 * ��ѯ����ѧ����¼
	 * @return: ����ѧ��ʵ���б�
	 */
	public List<Student> findAll();
	
	/**
	 * ��ѯָ��ѧ��ѧ����¼
	 * @param id ѧ��
	 * @return �鵽ѧ��ʵ��
	 */
	public Student findById(String id);
	
	/**
	 * ��ѯ��ѡ/δѡѧ�����漰����ѯ��ѧ����+����ѡ���
	 * @param flag��1����ѯ��ѡѧ����2����ѯδѡѧ��
	 * @return �鵽ѧ��ʵ���б�
	 */
	public List<Student> findBySelected(int flag);
	
	/**
	 * ����Ա������ҵ���Ʋ�ѯѧ����Ϣ
	 * @param companyName ��ҵ����
	 * @return ���ҵ�ѧ��ʵ���б�
	 */
	public List<Student> findByCompany(String companyName);
	
	/**
	 * ��ѯָ���꼶��ѧ����¼
	 * @param grade �꼶
	 * @return �鵽ѧ��ʵ���б�
	 */
	public List<Student> findByGrade(int grade);
	
	/**
	 * ��ѯָ��רҵ��ѧ��
	 * @param major רҵ����
	 * @return �鵽ѧ��ʵ���б�
	 */
	public List<Student> findByMajor(String major);
	
	/**
	 * ����Ա����Ȳ�ѯѧ����¼����������������ȣ�
	 * @param year ���
	 * @return �鵽ѧ��ʵ���б�
	 */
	public List<Student> findByYear(int year);
	
	/**
	 * ����ѧ����¼
	 * @param student һ��ѧ��ʵ��
	 * @return true:�ɹ���false��ʧ��
	 */
	public boolean update(Student student);
	
    /**
     * ɾ��ָ��ѧ��
     * @param id ѧ��
     * @return true ɾ���ɹ���false ɾ��ʧ��
     */
	public boolean delete(String id);
	
	
}
