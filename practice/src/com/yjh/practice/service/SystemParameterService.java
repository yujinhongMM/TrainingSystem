package com.yjh.practice.service;

import com.yjh.practice.model.SystemParameter;

public interface SystemParameterService {
	/**
	 * <p>Title: setSystemConfig</p>
	 * <p>Description: ����ϵͳ����</p>
	 * @param systemConfig ϵͳ����ʵ���������
	 * @return ���óɹ�����true������ʧ�ܷ���false
	 */
	boolean setOrUpdateSystemConfig(String account,String pwd,String code,
			String releaseProjectStartDate,String releaseProjectEndDate,
			String studentSelStartDate,String studentSelEndDate,String studentSelMaxnum,
			String userName);
	/**
	 * <p>Title: queryByAccount</p>
	 * <p>Description: �����û�����ѯ</p>
	 * @param accountName ϵͳ��������û���
	 * @return ϵͳ����ʵ���������
	 */
	SystemParameter queryByAccount(String accountName);
}
