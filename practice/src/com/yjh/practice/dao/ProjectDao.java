package com.yjh.practice.dao;

import java.util.ArrayList;

import com.yjh.practice.model.ProProSelStuView;
import com.yjh.practice.model.Project;
import com.yjh.practice.model.ProjectSelect;
import com.yjh.practice.utils.PageUtils;

/**
 * 
 * Description ����dao�ӿ�
 * @author YJH
 * @date 2018��6��4��  
 *
 */


public interface ProjectDao {

	/**
	 * ���ʵѵ����
	 * @param p ��������
	 * @return
	 */
	public boolean addProject(Project p);
	
	/**
	 * �޸ķ�������ͨ����˷��������޸�
	 * @param p
	 * @return
	 */
	public boolean updateProject(Project p);
	
	/**
	 * ���ݽ�ɫ��ѯ���з���,��ҳ
	 * @param role ��ɫ    1-��ҵ 9-����Ա
	 * @param company_username �������ҵ��ѯ���贫��company_username
	 * @param pageUtils ����ĸö���Ӧ����pageNow��pageSize
	 * @return 
	 */
	public ArrayList<Project> findAllProject(int role,String company_username,PageUtils pageUtils);
	
	/**
	 * ɾ����������ͨ������޷�ɾ��
	 * @param p_no ������
	 * @return
	 */
	public boolean deleteProject(String p_no);
	
	/**
	 * �������ʵѵ����
	 * @param p_no
	 * @param  check true��ʾ���ͨ����false��ʾ����
	 * @return
	 */
	public boolean checkProject(String p_no,boolean check);
	
	/**
	 * �����ܽ�
	 * @param p_no ������
	 * @param content �ܽ�����
	 * @return
	 */
	public boolean summaryProject(String p_no,String content);
	
	/**
	 * ʵѵ��������������
	 * @param p_nos
	 */
	public boolean endProjects(String p_nos[]);
	
	/**
	 * ѧ����ѯ���������з���
	 * @param grade �꼶
	 * @return
	 */
	public ArrayList<Project> findAllProject(int grade);
	
	/**
	 * ѧ��ѡ���ѡ������������ϵͳԤ������
	 * @param company_name  ��ҵ����
	 * @param p_no  ������
	 * @param stu_no  ѧ��ѧ��
	 * @param reason  ѡ������
	 * @return
	 */
	public boolean chooseProject(String company_name,String p_no,String stu_no,String reason);
	
	/**
	 * ѧ����ѡ����
	 * @param p_no
	 * @param stu_no  ѧ��ѧ��
	 * @return
	 */
	public boolean unChooseProject(String p_no,String stu_no);
	
	/**
	 * ��ҵ��ѯѧ��ѡ����ҵ�������
	 * @param c_name ��ҵ�û���
	 * @param pageUtils ��ҳ������
	 * @return 
	 */
	public ArrayList<ProProSelStuView> findAllStudentChoice(String c_name,PageUtils pageUtils);
	
	/**
	 * ��ҵ��ѯѧ��ѡ����ҵĳ�������
	 * @param p_no ��ҵ������
	 * @param pageUtils ��ҳ������
	 * @return 
	 */
	public ArrayList<ProProSelStuView> findAllStudentChoiceByPNo(String p_no,PageUtils pageUtils);
	
	/**
	 * ��ҵѡ��ѧ��   ѧ������ȷ���������ܱ�ѡ��
	 * @param stu_no  ѧ��ѧ��
	 * @param p_no   ������
	 * @return 
	 */
	public boolean chooseStudent(String stu_no,String p_no);
	
	/**
	 * ��ҵ��ѡѧ��
	 * @param stu_nos ѧ��ѧ��
	 * @param p_no  ������
	 * @return 
	 */
	public boolean unChooseStudent(String stu_nos[],String p_no);
	
	/**
	 * ��ҵ��ѯѡ������
	 * @param type 1-��ȷ�� 2-û��ȷ��  3-������
	 * @param p_no ��������ѯʱ���ò��������������ѯʱ��null
	 * @return 
	 */
	public ArrayList<ProjectSelect> findReason(int type,String p_no);
	
	/**
	 * ��ҵ¼��ɼ�����������¼��
	 * @param stu_nos ѧ��ѧ��  ��ɼ�һһ��Ӧ
	 * @param scores �ɼ�
	 * @param p_no  ������
	 * @return 
	 */
	public boolean inputScore(String stu_nos[],String scores[],String p_no);
	
	/**
	 * �������Ų�ѯѧ���ɼ�
	 * @param p_no
	 * @return
	 */
	public ArrayList<ProjectSelect> findScore(String p_no);
	
	/**
	 * ͳ�Ʒ�������
	 * @return
	 */
	public int countProject();
	
	/**
	 * ͳ�Ƶ�����ҵ�ķ�������
	 * @return
	 */
	public int countCompanyProject(String company_username);
	
	/**
	 * ͨ��no��ѯ������Ϣ
	 * @param no
	 * @return
	 */
	public Project findProjectByNo(String no);
	
	/**
	 * �õ�ĳ��ķ��������ֵ
	 * @return
	 */
	public int findMaxProjectNo(int year);
	
	/**
	 * ��ѯ���������з���
	 * @return
	 */
	public ArrayList<Project> findAllStartedProject();
	
	/**
	 * ��ѯѧ����ѡ����
	 * @param stu_no
	 * @return
	 */
	public ArrayList<Project> findAllChosenProject(String stu_no);
	
	/**
	 * ������ҵ�û�����ͳ��ѧ��ѡ�������
	 * @param c_name
	 * @return
	 */
	public int countAllStudentChoice(String c_name);
	
	/**
	 * ������ҵ�����ţ�ͳ��ѧ��ѡ�������
	 * @param p_no
	 * @param type: 1-�ѱ���ҵѡ��  2-δ����ҵѡ��  3-ȫ��
	 * @return
	 */
	public int countAllStudentChoiceByPNoAndType(String p_no,String type);
	
	
	/**
	 * ���ݽ�ɫ��ѯ���з���,��ҳ
	 * @param role ��ɫ    1-��ҵ 9-����Ա
	 * @param company_username �������ҵ��ѯ���贫��company_username
	 * @param pageUtils ����ĸö���Ӧ����pageNow��pageSize
	 * @param checkState ���״̬
	 * @param year  �����������
	 * @return 
	 */
	public ArrayList<Project> findAllProject(int role,String company_username,PageUtils pageUtils,boolean checkState,String year);
	
	/**
	 * ͳ��ĳ�������/δ��˷���������������Ա��ѯʱ��company_username�����ֵ
	 * @param year ����������
	 * @param checkState �������״̬
	 * @return
	 */
	public int countProject(String year,boolean checkState,String company_username);
	
	/**
	 * ��ѯ��ҵ���з���
	 * @param company_username 
	 * @return 
	 */
	public ArrayList<Project> findAllProject(String company_username);
	
	/**
	 * ��ѯѧ���ɼ�����ҳ������pageUtilsΪ��ʱ��ʾ�Ƿ�ҳ��ѯ
	 * @param p_no
	 * @param pageUtils
	 * @return
	 */
	public ArrayList<ProProSelStuView> findStuScoreByPNo(String p_no, PageUtils pageUtils);
	
	/**
	 * ͨ��������ͳ����ѡ�÷����ұ���ҵѡ���ѧ��������
	 * @param no
	 * @return
	 */
	public int countAllSelStuByNo(String no);
	
	/**
	 * ����ѧ����ȷ����ʵѵ����
	 * @param stu_no
	 * @return
	 */
	public ArrayList<ProjectSelect> findStuProject(String stu_no);
	
	/**
	 * ��ҵ��ѯѧ��ѡ����ҵĳ�������
	 * @param p_no ��ҵ������
	 * @param type 1-��ѡ��2-δѡ��ѧ��
	 * @param pageUtils ��ҳ������
	 * @return 
	 */
	public ArrayList<ProProSelStuView> findAllStudentChoiceByPNoAndType(String p_no,String type,PageUtils pageUtils);

	/**
	 * �õ�����רҵ
	 * @return
	 */
	public ArrayList<String> findAllProfessional();
}

