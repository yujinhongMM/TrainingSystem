package com.yjh.practice.service.impl;

import java.util.List;

import com.yjh.practice.dao.StudentDao;
import com.yjh.practice.dao.impl.StudentDaoImpl;
import com.yjh.practice.model.Student;
import com.yjh.practice.service.StudentService;

/**
 * 
 * Description ѧ����Ϣ�������ʵ��
 * @author YJH
 * @date 2018��6��2��  
 *
 */

public class StudentServiceImpl implements StudentService {

	private StudentDao studentDao = new StudentDaoImpl();
	
	/**
	 * ����ѧ����¼
	 * @param: student һ��ѧ��ʵ��
	 * @return: true ����ɹ���false ����ʧ��
	 */
	@Override
	public boolean insert(Student student) {
		try {			
			if (student != null ) 
				return this.studentDao.insert(student);
			} catch(Exception exception) {
				return false;
			}
		return false;
	}

	/**
	 * ��ѯ����ѧ����¼
	 * @return: ����ѧ��ʵ���б�
	 */
	@Override
	public List<Student> findAll() {
		try {
			return this.studentDao.findAll();
		} catch(Exception e) {
			return null ;
		}
	}

	/**
	 * ��ѯָ��ѧ��ѧ����¼
	 * @param id ѧ��
	 * @return �鵽ѧ��ʵ��
	 */
	@Override
	public Student findById(String id) {
		if(id!=null){
			try {
				return this.studentDao.findById(id);
			} catch(Exception e) {
				return null ;
			}
		}
		return null;
	}

	/**
	 * ��ѯ��ѡ/δѡѧ�����漰����ѯ��ѧ����+����ѡ���
	 * @param flag��1����ѯ��ѡѧ����2����ѯδѡѧ��
	 * @return �鵽ѧ��ʵ���б� 
	 */
	@Override
	public List<Student> findBySelected(int flag) {
		try {
			return this.studentDao.findBySelected(flag);
		} catch(Exception e) {
			return null ;
		}
	}

	/**
	 * ����Ա������ҵ���Ʋ�ѯѧ����Ϣ
	 * @param companyName ��ҵ����
	 * @return ���ҵ�ѧ��ʵ���б�
	 */
	@Override
	public List<Student> findByCompany(String companyName) {
		if(companyName!=null){
			try {
				return this.studentDao.findByCompany(companyName);
			} catch(Exception e) {
				return null ;
			}
		}
		return null;		
	}

	/**
	 * ��ѯָ���꼶��ѧ����¼
	 * @param grade �꼶
	 * @return �鵽ѧ��ʵ���б�
	 */
	@Override
	public List<Student> findByGrade(int grade) {
		try {
			return this.studentDao.findByGrade(grade);
		} catch(Exception e) {
			return null ;
		}		
	}

	/**
	 * ��ѯָ��רҵ��ѧ��
	 * @param major רҵ����
	 * @return �鵽ѧ��ʵ���б�
	 */
	@Override
	public List<Student> findByMajor(String major) {
		if(major!=null){
			try {
				return this.studentDao.findByMajor(major);
			} catch(Exception e) {
				return null ;
			}
		}
		return null;	
	}

	/**
	 * ����Ա����Ȳ�ѯѧ����¼����������������ȣ�
	 * @param year ���
	 * @return �鵽ѧ��ʵ���б�
	 */
	@Override
	public List<Student> findByYear(int year) {
		try {
			return this.studentDao.findByYear(year);
		} catch(Exception e) {
			return null ;
		}
	}



	/**
	 * ����ѧ����¼
	 * @param student һ��ѧ��ʵ��
	 * @return true:�ɹ���false��ʧ�� 
	 */
	@Override
	public boolean update(Student student) {
		if(student!=null){
			try {
				return this.studentDao.update(student);
			} catch(Exception e) {
				return false ;
			}
		}
		return false;
	}

	/**
	 * ɾ��ָ��ѧ��
     * @param id ѧ��
     * @return true ɾ���ɹ���false ɾ��ʧ��
	 */
	@Override
	public boolean delete(String id) {
		if(id!=null){
			try {
				return this.studentDao.delete(id);
			} catch(Exception e) {
				return false ;
			}
		}
		return false;
	}

	

}
