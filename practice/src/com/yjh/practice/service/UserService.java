package com.yjh.practice.service;


/**
 * 
 * Description �û�ҵ���߼����������û���¼�ȹ�����صĽӿ� 
 * @author YJH
 * @date 2018��6��1��  
 *
 */
public interface UserService {
	//�û���¼ʱ����ҳ��ѡ���ɫ��Ȼ��������Ҫ�Ĳ����������֤���session�е�һ�£��������һ����֤
	//���role=1������ҵ�����role=2����ѧ�������role=9����ϵͳ������
	public boolean login(String account, String password, String Verification_Code, String role,String vchidden);
}
