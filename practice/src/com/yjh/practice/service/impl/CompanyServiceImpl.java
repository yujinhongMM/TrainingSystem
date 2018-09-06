package com.yjh.practice.service.impl;

import java.util.List;

import com.yjh.practice.dao.CompanyDao;
import com.yjh.practice.dao.impl.CompanyDaoImpl;
import com.yjh.practice.model.Company;
import com.yjh.practice.service.CompanyService;
import com.yjh.practice.utils.MdPwdUtil;

/**
 * 
 * Description ��ҵ��Ϣ�������
 * @author YJH
 * @date 2018��6��5��  
 *
 */

public class CompanyServiceImpl implements CompanyService{
	private CompanyDao companyDao = new CompanyDaoImpl();
	/**
	 * ����˾ע���ҵ���߼�
	 */
	public boolean registerCompanyInfo(String username,String companyName,String mailbox,String password,String yzm,String yzm0) {
		try {
				if(yzm == null || !yzm0.equals(yzm.toUpperCase())){
					return false;
				}
				String mdPwd = MdPwdUtil.MD5Password(password);
				Company company = new Company();
				company.setUsername(username);
				company.setCompanyName(companyName);
				company.setMailbox(mailbox);
				company.setPassword(mdPwd);
				company.setContacts("");
				company.setPhone("");
				return this.companyDao.registerCompanyInfo(company);
			
		}catch(Exception e) {
			return false;
		}
			
	}

	/**
	 * 
	 * <p>Title: updateCompanyInfo</p>
	 * <p>Description: ������ҵ��Ϣ</p>
	 * @param company 
	 * @return boolean
	 */
	public boolean updateCompanyInfo(Company company) {
			try {
				if (company != null ) {
				return this.companyDao.updateCompanyInfo(company);
				}
				return false;
 			} catch(Exception exception) {
 				return false;
 			}
	}

	/**
	 * ִ�и����������
	 */
	public boolean updateCompanyPassword(String companyUserName, String newPassword) {
		try {
			//����û�����password����Ϊ�յĻ�����ô�͵���dao�㷽�������򷵻�false
			if (companyUserName != null && !"".equals(companyUserName)&&
					newPassword!= null && !"".equals(newPassword)) {
				return this.companyDao.updateCompanyPassword(companyUserName, newPassword);
			}
			return false;
		} catch(Exception e) {
			return false;
		}
	}

	/**
	 * <p>Title: queryViryFyCompanys</p>
	 * <p>Description: ��ѯ��ҵ</p>
	 * @param condition ��ѯ����
	 * @return ���ز�ѯ������ҵ�ļ���
	 */
	public List<Company> queryCompanys(String condition) {
		/**
		 * ���ȶ�condition�����жϣ������Ϊ�գ����ж���ֵ��ѡȡ��Ӧ�Ĳ�ѯ���������Ϊ�գ�ֱ�ӷ���null
		 */
		try {
			if (condition != null) {
				if ("δ���".equals(condition)) {
					return this.companyDao.queryNotVirefyCompanys();
				}
				if ("���".equals(condition)) {
					return this.companyDao.queryViryFyCompanys();
				}
				if ("all".equals(condition)) {
					return this.companyDao.queryAllCompanys();
				}
				return null ;
			}
			return null ;
		} catch(Exception e) {
			return null ;
		}
	}

	/**
	 * <p>Title: deleteCompany</p>
	 * <p>Description: ɾ����ҵ��Ϣ</p>
	 * @param companyUsername ��ҵע��ʱ���û���
	 * @return ����ɾ���Ľ������true/false
	 */
	public boolean deleteCompany(String companyUsername) {
		try {
			if (companyUsername != null && !"".equals(companyUsername)) {
				return this.companyDao.deleteCompany(companyUsername);
			}
			return false;
		} catch(Exception e) {
			return false ;
		}
	}

	/**
	 * <p>Title: checkCompany</p>
	 * <p>Description:�ýӿڷ�����Ҫ�������Ա�����ҵ��Ϣ</p>
	 * @param company Companyʵ�������ö���
	 * @return ����һ�������ı�־,���ͨ������true,��˲�ͨ������false
	 */
	
	public boolean checkCompany(Company company) {
		
		try {
			if (company != null) {
				if (company.getUsername() != null && !"".equals(company.getUsername())) {
					System.out.println("������Ϊ��");
					return this.companyDao.checkCompany(company);
				}
			}
			return false;
		} catch(Exception e) {
			return false ;
		}
	}

	/**
	 * �����û�����ѯ��˾
	 */
	public Company queryByUserName(String account) {
		try {
			if (account != null && !"".equals(account)) {
				return this.companyDao.queryByUserName(account);
			}
			return null;
		} catch(Exception e) {
			return null ;
		}
	}

	@Override
	public boolean backReview(Company company) {
		try {
			if (company != null) {
				if (company.getUsername() != null && !"".equals(company.getUsername())) {
					return this.companyDao.backReview(company);
				}
			}
			return false;
		} catch(Exception e) {
			return false ;
		}
	}



}
