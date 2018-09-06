package com.yjh.practice.dao;

import java.util.List;

import com.yjh.practice.model.Company;
/**
 * 
 * Description ��ҵ��Ϣ��������ӿ�
 * @author YJH
 * @date 2018��6��3��  
 *
 */
public interface CompanyDao {
	/**
	 * <p>Title: registerCompanyInfo</p>
	 * <p>Description: �ýӿڷ�����Ҫ����˾ע��</p>
	 * @param company Companyʵ����Ķ�������
	 * @return ����ע��ɹ����ı�־���ɹ�����true��ʧ�ܷ���false
	 */
	boolean registerCompanyInfo(Company company);
	
	/**
	 * <p>Title: updateCompanyInfo</p>
	 * <p>Description:�ýӿڷ�����Ҫ������ҵ�޸�ע��ҳ����Ϣ </p>
	 * @param company Companyʵ����Ķ�������
	 * @return �����޸ĳɹ����ı�־���ɹ�����true��ʧ�ܷ���false
	 */
	boolean updateCompanyInfo(Company company);
	
	/**
	 * <p>Title: queryCompanyInfo</p>
	 * <p>Description: �ýӿڷ�����Ҫ������ҵ��ѯ��Ϣ </p>
	 * @param companyName ��ҵע����û���
	 * @return ���ݴ����û���������Companyʵ����
	 */
	Company queryCompanyInfo(String companyUserName);
	
	/**
	 * <p>Title: updatePassword</p>
	 * <p>Description: �ýӿڷ�����Ҫ�����޸�����</p>
	 * @param companyName ��ҵע����û���
	 * @param newPassword ��ҵ��¼����
	 * @return �����޸ĳɹ����ı�־���ɹ�����true��ʧ�ܷ���false
	 */
	boolean updateCompanyPassword(String companyUserName,String newPassword);
	
	/**
	 * <p>Title: queryCompanys</p>
	 * <p>Description: �ýӿڷ�����Ҫ�����������,��ѯ�Ѿ���˵���ҵ</p>
	 * @param condition ��ѯ����
	 * @return ����List<Company>����
	 */
	List<Company> queryViryFyCompanys();
	
	/**
	 * 
	 * <p>Title: queryNotVirefyCompanys</p>
	 * <p>Description: �ýӿڷ�����Ҫ�����������,��ѯδ��˵���ҵ</p>
	 * @return ����List<Company>����
	 */
	List<Company> queryNotVirefyCompanys();
	
	/**
	 * 
	 * <p>Title: queryAllCompanys</p>
	 * <p>Description:�ýӿڷ�����Ҫ�����������,��ѯ������ҵ </p>
	 * @return ����List<Company>����
	 */
	List<Company> queryAllCompanys();
	
	/**
	 * <p>Title: deleteCompany</p>
	 * <p>Description: ɾ����ҵ��Ϣ</p>
	 * @param companyUsername ��ҵע��ʱ���û���
	 * @return ����ɾ���Ľ������true/false
	 */
	boolean deleteCompany(String companyUsername);
	/**
	 * <p>Title: checkCompany</p>
	 * <p>Description:�ýӿڷ�����Ҫ�������Ա�����ҵ��Ϣ</p>
	 * @param company Companyʵ�������ö���
	 * @return ����һ�������ı�־,���ͨ������true,��˲�ͨ������false
	 */
	boolean checkCompany(Company company);
	
	/**
	 * <p>Title: queryByUserName</p>
	 * <p>Description: ����ע����û�����ѯ��ҵ</p>
	 * @param account ע���û���
	 * @return ��˾
	 */
	Company queryByUserName(String account);
	/**
	 * <p>Title: backReview</p>
	 * <p>Description: ����</p>
	 * @param companyUsername �û���
	 * @return ��������ɹ����ı�־
	 */
	boolean backReview(Company company);
	
}
