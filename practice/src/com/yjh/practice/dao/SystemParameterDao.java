package com.yjh.practice.dao;

import com.yjh.practice.model.SystemParameter;

/**
 * 
 * Description
 * @author YJH
 * @date 2018��6��4��  
 *
 */

public interface SystemParameterDao {
	/**
	 * <p>Title: setSystemConfig</p>
	 * <p>Description: ����ϵͳ����</p>
	 * @param systemConfig ϵͳ����ʵ���������
	 * @return �����ɹ�����true������ʧ�ܷ���false
	 */
	boolean setSystemConfig(SystemParameter systemConfig);
	
	/**
	 * <p>Title: updateSystemConfig</p>
	 * <p>Description: ����ϵͳ������</p>
	 * @param systemConfig ϵͳ����ʵ���������
	 * @return ���óɹ�����true������ʧ�ܷ���false
	 */
	boolean updateSystemConfig(SystemParameter systemConfig,String username);
	
	/**
	 * <p>Title: updateSystemConfig</p>
	 * <p>Description: ����ϵͳ������</p>
	 * @param systemConfig ϵͳ����ʵ���������
	 * @return ���óɹ�����true������ʧ�ܷ���false
	 */
	boolean updateSystemConfigNoPwd(SystemParameter systemConfig,String username);
	
	/**
	 * <p>Title: queryByAccount</p>
	 * <p>Description: �����û�����ѯ</p>
	 * @param accountName ϵͳ��������û���
	 * @return ϵͳ����ʵ���������
	 */
	SystemParameter queryByAccount(String accountName);
}

