package com.yjh.practice.service.impl;

import java.sql.Date;

import com.yjh.practice.dao.SystemParameterDao;
import com.yjh.practice.dao.impl.SystemParameterDaoImpl;
import com.yjh.practice.model.SystemParameter;
import com.yjh.practice.service.SystemParameterService;
import com.yjh.practice.utils.DateUtil;
import com.yjh.practice.utils.MdPwdUtil;



public class SystemParameterServiceImpl implements SystemParameterService {
	private SystemParameterDao systemParameterDao = new SystemParameterDaoImpl();
	/**
	 * ����ϵͳ����
	 */
	public boolean setOrUpdateSystemConfig(String account,String pwd,String code,
			String releaseProjectStartDate,String releaseProjectEndDate,
			String studentSelStartDate,String studentSelEndDate,String studentSelMaxnum,
			String userName) {
		//����Ĳ�������Ϊ��
		if (code == null || "".equals(code) || studentSelMaxnum == null ||"".equals(studentSelMaxnum)) {
			System.out.println("�пյĲ���");
			return false;
		} else {
			Integer studentSelMaxnum1 = Integer.parseInt(studentSelMaxnum);
			if (studentSelMaxnum1 == 0) {
				System.out.println("�д�");
				return false;
			}
			else {
				System.out.println("û��");
				Date releaseProjectStartDate1 = null;
				Date releaseProjectEndDate1 = null;
				Date studentSelStartDate1 = null;
				Date studentSelEndDate1 = null;
				if (releaseProjectStartDate != null && !"".equals(releaseProjectStartDate)) {
					System.out.println("û��1");
					releaseProjectStartDate1 = DateUtil.splitStringToDate(releaseProjectStartDate);
				}
				if (releaseProjectEndDate != null && !"".equals(releaseProjectEndDate)) {
					System.out.println("1"+releaseProjectEndDate+"1");
					System.out.println("û��2");
					releaseProjectEndDate1 = DateUtil.splitStringToDate(releaseProjectEndDate);
				}
				if (studentSelStartDate != null && !"".equals(studentSelStartDate)) {
					System.out.println("û��3");
					studentSelStartDate1 = DateUtil.splitStringToDate(studentSelStartDate);
				}
				if (studentSelEndDate != null && !"".equals(studentSelEndDate)) {
					System.out.println("û��4");
					studentSelEndDate1 = DateUtil.splitStringToDate(studentSelEndDate);
				}
				System.out.println("���û��");
				SystemParameter systemParameter = new SystemParameter();
				if ("518855".equals(pwd)) {
					pwd = "";
				}
				boolean flag = false;
				if (pwd == null || "".equals(pwd)) {//����Ϊ�գ��Ͳ���������
					System.out.println("û������");
					systemParameter.setInvitationCode(code);
					systemParameter.setReleaseProjectStartDate(releaseProjectStartDate1);
					systemParameter.setReleaseProjectEndDate(releaseProjectEndDate1);
					systemParameter.setStudentSelStartDate(studentSelStartDate1);
					systemParameter.setStudentSelEndDate(studentSelEndDate1);
					systemParameter.setStudentSelMaxnum(studentSelMaxnum1);
				}
				else {
					//����һϵ�еĲ���
					System.out.println("������");
					systemParameter.setAdminPassword(MdPwdUtil.MD5Password(pwd));
					systemParameter.setInvitationCode(code);
					systemParameter.setReleaseProjectStartDate(releaseProjectStartDate1);
					systemParameter.setReleaseProjectEndDate(releaseProjectEndDate1);
					systemParameter.setStudentSelStartDate(studentSelStartDate1);
					systemParameter.setStudentSelEndDate(studentSelEndDate1);
					systemParameter.setStudentSelMaxnum(studentSelMaxnum1);
					flag = true;
				}
				System.out.println("���Ը���");
				if (userName != null && !"".equals(userName)) {
					try {
						if (flag) {
							return this.systemParameterDao.updateSystemConfig(systemParameter, userName);
						}
						else {
							return this.systemParameterDao.updateSystemConfigNoPwd(systemParameter, userName);
						}
					}catch(Exception e) {
						return false;
				}
				}
				else {
					return false;
				}
			}
			
		}
	}
	
	public SystemParameter queryByAccount(String accountName) {
		//������û�����Ϊ��
		if (accountName != null && !"".equals(accountName)) {
			try {
				SystemParameter systemParameter = this.systemParameterDao.queryByAccount(accountName);
				return systemParameter;
			} catch(Exception e) {
				return null ;
			}
		}else {
			throw new NullPointerException("�������Ϊ��");
		}
	}

}
