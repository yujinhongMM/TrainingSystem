package com.yjh.practice.service;

import java.util.List;

import com.yjh.practice.model.Company;



/**
 * 
 * Description ��ҵ��Ϣ�������
 * @author YJH
 * @date 2018��6��5��  
 *
 */
public interface CompanyService {
	/**
	 * <p>Title: registerCompanyInfo</p>
	 * <p>Description: �ýӿڷ�����Ҫ����˾ע��</p>
	 * @param company Companyʵ����Ķ�������
	 * @return ����ע��ɹ����ı�־���ɹ�����true��ʧ�ܷ���false
	 */
	boolean registerCompanyInfo(String username,String companyName,String mailbox,String password,
			String yzm,String yam0);
	
	/**
	 * <p>Title: updateCompanyInfo</p>
	 * <p>Description:�ýӿڷ�����Ҫ������ҵ�޸�ע��ҳ����Ϣ </p>
	 * @param company Companyʵ����Ķ�������
	 * @return �����޸ĳɹ����ı�־���ɹ�����true��ʧ�ܷ���false
	 */
	boolean updateCompanyInfo(Company company);
	
	
	/**
	 * <p>Title: updatePassword</p>
	 * <p>Description: �ýӿڷ�����Ҫ�����޸�����</p>
	 * @param companyName ��ҵע����û���
	 * @param newPassword ��ҵ��¼����
	 * @return �����޸ĳɹ����ı�־���ɹ�����true��ʧ�ܷ���false
	 */
	boolean updateCompanyPassword(String companyUserName,String newPassword);
	
	/**
	 * <p>Title: queryViryFyCompanys</p>
	 * <p>Description: ��ѯ��ҵ</p>
	 * @param condition ��ѯ����
	 * @return ���ز�ѯ������ҵ�ļ���
	 */
	public List<Company> queryCompanys(String condition);
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

