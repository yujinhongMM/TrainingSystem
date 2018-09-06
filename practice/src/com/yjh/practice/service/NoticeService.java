package com.yjh.practice.service;

import java.sql.Date;
import java.util.List;

import com.yjh.practice.model.NoticeAdmin;
import com.yjh.practice.model.NoticeCompany;



public interface NoticeService {
	/**
	 * <p>Title: updateCompanyNotice</p>
	 * <p>Description: �޸���ҵ������֪ͨ����</p>
	 * @param companyNotice ��ҵ֪ͨ����ʵ���������
	 * @return ���³ɹ�����true������ʧ�ܷ���false
	 */
	boolean updateCompanyNotice(NoticeCompany companyNotice);
	
	/**
	 * <p>Title: deleteCompanyNotice</p>
	 * <p>Description: ���ݹ���Idɾ����ҵ�����Ĺ���</p>
	 * @param companyNoticeId ��ҵ����Id
	 * @return ɾ���ɹ�����true��ɾ��ʧ�ܷ���false
	 */
	boolean deleteCompanyNotice(int companyNoticeId);
	
	/**
	 * <p>Title: provideAnnouncement</p>
	 * <p>Description:������ҵ���� </p>
	 * @param companyNotice ��ҵ֪ͨ����ʵ���������
	 */
	void provideAnnouncement(NoticeCompany companyNotice);
	
	/**
	 * <p>Title: reviewCompanyNotice</p>
	 * <p>Description: ����Ա�����ҵ����</p>
	 * @param companyNoticeId ��ҵ����Id
	 * @param companyNoticeDate ֪ͨ�����������
	 * @return ����������ڳɹ�����true��ɾ��ʧ�ܷ���false
	 */
	boolean reviewCompanyNotice(int companyNoticeId, Date companyAuditDate);
	/**
	 * <p>Title: queryNoticeByCompanyName</p>
	 * <p>Description: ���ݹ�˾���˺�����ѯ֪ͨ����</p>
	 * @param companyUserName ��˾���˺���
	 * @return ֪ͨ����
	 */
	List<NoticeCompany>  queryNoticeByCompanyName(String companyUserName,int pageNow,int pageSize);
	/**
	 * <p>Title: queryAllByName</p>
	 * <p>Description: ���ݹ�˾����ѯ���й�������</p>
	 * @param companyUserName ��˾��
	 * @return ���������
	 */
	int queryAllByName(String companyUserName);
	
	/**
	 * <p>Title: queryNoticeById</p>
	 * <p>Description: ����Id��ѯ֪ͨ</p>
	 * @param companyNoticeId ֪ͨId
	 * @return NoticeCompanyʵ����
	 */
	NoticeCompany queryNoticeById(int companyNoticeId);
	
	/**
	 * <p>Title: queryNoticeByAuditTime</p>
	 * <p>Description: ��ҳ��ѯû����˵�֪ͨ����</p>
	 * @param pageNow ��ǰҳ��
	 * @param pageSize ÿҳ����
	 * @return List����
	 */
	List<NoticeCompany> queryNoticeByAuditTime(int pageNow,int pageSize);
	
	/**
	 * <p>Title: countNoAuditTimeNotice</p>
	 * <p>Description: ͳ��û����˵�֪ͨ������</p>
	 * @return ����
	 */
	int countNoAuditTimeNotice();
	NoticeAdmin queryNoticeAdminById(int adminNoticeId);
	
	
	/**
	 * <p>Title: countNoAuditTimeNotice</p>
	 * <p>Description: ͳ��ѧԺ֪ͨ��������</p>
	 * @return ����
	 */
	int countAdminNotice();
	
	/**
	 * <p>Title: queryNoticeByAuditTime</p>
	 * <p>Description: ��ҳ��ѯѧԺ֪ͨ����</p>
	 * @param pageNow ��ǰҳ��
	 * @param pageSize ÿҳ����
	 * @return List����
	 */
	List<NoticeAdmin> queryAdminNotice(int pageNow,int pageSize);
	
	/**
	 * <p>Title: updateCompanyNotice</p>
	 * <p>Description: �޸���ҵ������֪ͨ����</p>
	 * @param companyNotice ��ҵ֪ͨ����ʵ���������
	 * @return ���³ɹ�����true������ʧ�ܷ���false
	 */
	boolean updateAdminNotic(NoticeAdmin noticeAdmin);
	
	/**
	 * <p>Title: deleteCompanyNotice</p>
	 * <p>Description: ���ݹ���Idɾ����ҵ�����Ĺ���</p>
	 * @param companyNoticeId ��ҵ����Id
	 * @return ɾ���ɹ�����true��ɾ��ʧ�ܷ���false
	 */
	boolean deleteAdminNotic(int adminNoticeId);
	
	/**
	 * <p>Title: provideAnnouncement</p>
	 * <p>Description:������ҵ���� </p>
	 * @param companyNotice ��ҵ֪ͨ����ʵ���������
	 */
	void provideAdminAnnouncement(NoticeAdmin noticeAdmin);
	
	/**
	 * <p>Title: queryAll</p>
	 * <p>Description: ��ѯ�����·�����ʮ����ҵ��Ϣ����</p>
	 * @return
	 */
	List<NoticeCompany> queryAllCompanyNoticeOrderByDate(int pageNow,int pageSize);
	
	/**
	 * <p>Title: queryAllAdminNoticeOrderByDate</p>
	 * <p>Description: ��ѯ�����·�����ʮ��ѧԺ֪ͨ����</p>
	 * @return
	 */
	List<NoticeAdmin> queryAllAdminNoticeOrderByDate(int pageNow,int pageSize);
	/**
	 * <p>Title: countAllAuditCompanyNotice</p>
	 * <p>Description: ͳ���Ѿ�����˵���ҵ֪ͨ����</p>
	 * @return
	 */
	int countAllAuditCompanyNotice();
}

