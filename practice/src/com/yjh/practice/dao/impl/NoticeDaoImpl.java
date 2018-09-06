package com.yjh.practice.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.yjh.practice.dao.NoticeDao;
import com.yjh.practice.model.NoticeAdmin;
import com.yjh.practice.model.NoticeCompany;
import com.yjh.practice.utils.DbUtils;

public class NoticeDaoImpl implements NoticeDao {

	/**
	 * ���²���
	 */
	public boolean updateCompanyNotice(NoticeCompany companyNotice) {
		//��ȡ���ݿ�����
		Connection connection = DbUtils.getConnection();
		String registSql = "update notice_company set "
				+ "release_date = ?,audit_date = ?,content = ?,title = ? where ID = ?";
		PreparedStatement ps = null ;
		try {
			 connection.setAutoCommit(false);//�����ֶ��ύ����
			 ps = connection.prepareStatement(registSql);
			 ps.setDate(1, companyNotice.getReleaseDate());
			 ps.setDate(2, companyNotice.getAuditDate());
			 ps.setString(3,companyNotice.getContent());
			 ps.setString(4, companyNotice.getTitle());
			 ps.setInt(5, companyNotice.getId());
			 ps.execute();
			 connection.commit();//�ύ����
			 return true ;
		} catch (Exception e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			return false;
		} finally {
			//ÿ�β���֮�����ر�����
			DbUtils.closeConnection(connection, ps);
		}
	}

	/**
	 * ɾ������
	 */
	public boolean deleteCompanyNotice(int companyNoticeId) {
		//��ȡ���ݿ�����
				Connection connection = DbUtils.getConnection();
				String registSql = "delete from notice_company   where ID = ?";
				PreparedStatement ps = null ;
				try {
					 connection.setAutoCommit(false);//�����ֶ��ύ����
					 ps = connection.prepareStatement(registSql);
					 ps.setInt(1, companyNoticeId);
					 ps.execute();
					 connection.commit();//�ύ����
					 return true ;
				} catch (Exception e) {
					e.printStackTrace();
					if (connection != null) {
						try {
							connection.rollback();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					return false;
				} finally {
					//ÿ�β���֮�����ر�����
					DbUtils.closeConnection(connection, ps);
				}
	}

	/**
	 * ����֪ͨ
	 */
	public void provideAnnouncement(NoticeCompany companyNotice) {
				//��ȡ���ݿ�����
				Connection connection = DbUtils.getConnection();
				String registSql = "insert into notice_company(company_username,release_date,"
						+ "title,content)"
						+ " values(?,?,?,?)";
				PreparedStatement ps = null ;
				try {
					 connection.setAutoCommit(false);//�����ֶ��ύ����
					 ps = connection.prepareStatement(registSql);
					 
					 ps.setString(1, companyNotice.getCompanyUsername());
					 ps.setDate(2, companyNotice.getReleaseDate());
					 ps.setString(3, companyNotice.getTitle());
					 ps.setString(4,companyNotice.getContent());
					 ps.execute();
					 connection.commit();//�ύ����
				} catch (Exception e) {
					e.printStackTrace();
					if (connection != null) {
						try {
							connection.rollback();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				} finally {
					//ÿ�β���֮�����ر�����
					DbUtils.closeConnection(connection, ps);
				}
	}

	/**
	 * ����Ա���֪ͨ
	 */
	public boolean reviewCompanyNotice(int companyNoticeId, Date companyAuditDate) {
				//��ȡ���ݿ�����
				Connection connection = DbUtils.getConnection();
				String registSql = "update notice_company set audit_date = ? where ID = ?";
				PreparedStatement ps = null ;
				try {
					 connection.setAutoCommit(false);//�����ֶ��ύ����
					 ps = connection.prepareStatement(registSql);
					 ps.setDate(1, companyAuditDate);
					 ps.setInt(2, companyNoticeId);
					 ps.execute();
					 connection.commit();//�ύ����
					 return true ;
				} catch (Exception e) {
					e.printStackTrace();
					if (connection != null) {
						try {
							connection.rollback();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					return false;
				} finally {
					//ÿ�β���֮�����ر�����
					DbUtils.closeConnection(connection, ps);
				}
	}

	public List<NoticeCompany> queryNoticeByCompanyName(String companyUserName, int pageNow , int pageSize) {
		//��ȡ���ݿ�����
		Connection connection = DbUtils.getConnection();
		String registSql = "select * from (select * from notice_company where company_username = ?)as a limit ?,?";
		PreparedStatement ps = null ;
		ResultSet resultSet = null ;
		List<NoticeCompany> list = new ArrayList<NoticeCompany>();
		NoticeCompany noticeCompany = null ;
		try {
			 ps = connection.prepareStatement(registSql);
			 ps.setString(1, companyUserName);
			 ps.setInt(2, (pageNow-1)*pageSize);
			 ps.setInt(3, pageSize);
			 //ִ�в�ѯ���
			 resultSet = ps.executeQuery();
			 //ֻҪresultSetָ�����һ��Ԫ�������ݣ���ô��һֱִ�в�ѯ�븳ֵ����
			 while (resultSet.next()) {
				 noticeCompany = new NoticeCompany();
				 noticeCompany.setCompanyUsername(resultSet.getString("company_username"));
				 noticeCompany.setId(resultSet.getInt("ID"));
				 noticeCompany.setReleaseDate(resultSet.getDate("release_date"));
				 noticeCompany.setAuditDate(resultSet.getDate("audit_date"));
				 noticeCompany.setTitle(resultSet.getString("title"));
				 noticeCompany.setContent(resultSet.getString("content"));
				 //��ӽ�List��
				 list.add(noticeCompany);
			}
			 return list ;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			//ÿ�β���֮�����ر�����
			DbUtils.closeConnection(connection, ps,resultSet);
		}
	}

	/**
	 * ���ݹ�˾���֣���ѯ�����е�֪ͨ����
	 */
	public int queryAllByName(String companyUserName) {
				//��ȡ���ݿ�����
				Connection connection = DbUtils.getConnection();
				String registSql = "select count(*) from notice_company where company_username = ?";
				PreparedStatement ps = null ;
				ResultSet resultSet = null ;
				int count = 0;
				try {
					 ps = connection.prepareStatement(registSql);
					 ps.setString(1, companyUserName);
					 //ִ�в�ѯ���
					 resultSet = ps.executeQuery();
					 //ֻҪresultSetָ�����һ��Ԫ�������ݣ���ô��һֱִ�в�ѯ�븳ֵ����
					if (resultSet.next()) {
						count = resultSet.getInt(1);
					}
					 return count;
				} catch (Exception e) {
					e.printStackTrace();
					return 0;
				} finally {
					//ÿ�β���֮�����ر�����
					DbUtils.closeConnection(connection, ps,resultSet);
				}
	}

	/**
	 * ����֪ͨ�����Id��ѯ֪ͨ����
	 */
	public NoticeCompany queryNoticeById(int companyNoticeId) {
		//��ȡ���ݿ�����
		Connection connection = DbUtils.getConnection();
		String registSql = "select * from notice_company  where ID = ?";
		NoticeCompany noticeCompany = null;
		PreparedStatement ps = null ;
		ResultSet resultSet = null ;
		try {
			 //��̬������ֵ
			 ps = connection.prepareStatement(registSql);
			 ps.setInt(1, companyNoticeId);
			 resultSet = ps.executeQuery();
			 while (resultSet.next()) {
				 noticeCompany = new NoticeCompany();
				 noticeCompany.setCompanyUsername(resultSet.getString("company_username"));
				 noticeCompany.setId(resultSet.getInt("ID"));
				 noticeCompany.setReleaseDate(resultSet.getDate("release_date"));
				 noticeCompany.setAuditDate(resultSet.getDate("audit_date"));
				 noticeCompany.setTitle(resultSet.getString("title"));
				 noticeCompany.setContent(resultSet.getString("content"));
			}
			 return noticeCompany ;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			//ÿ�β���֮�����ر�����
			DbUtils.closeConnection(connection, ps);
		}
	}

	/**
	 * ��ҳ��ѯ������δ��˵�֪ͨ����
	 */
	public List<NoticeCompany> queryNoticeByAuditTime(int pageNow, int pageSize) {
		//��ȡ���ݿ�����
				Connection connection = DbUtils.getConnection();
				String registSql = "select * from (select * from notice_company where audit_date IS NULL)as a limit ?,?";
				PreparedStatement ps = null ;
				ResultSet resultSet = null ;
				List<NoticeCompany> list = new ArrayList<NoticeCompany>();
				NoticeCompany noticeCompany = null ;
				try {
					 ps = connection.prepareStatement(registSql);
					 ps.setInt(1, (pageNow-1)*pageSize);
					 ps.setInt(2, pageSize);
					 //ִ�в�ѯ���
					 resultSet = ps.executeQuery();
					 //ֻҪresultSetָ�����һ��Ԫ�������ݣ���ô��һֱִ�в�ѯ�븳ֵ����
					 while (resultSet.next()) {
						 noticeCompany = new NoticeCompany();
						 noticeCompany.setCompanyUsername(resultSet.getString("company_username"));
						 noticeCompany.setId(resultSet.getInt("ID"));
						 noticeCompany.setReleaseDate(resultSet.getDate("release_date"));
						 noticeCompany.setAuditDate(resultSet.getDate("audit_date"));
						 noticeCompany.setTitle(resultSet.getString("title"));
						 noticeCompany.setContent(resultSet.getString("content"));
						 //��ӽ�List��
						 list.add(noticeCompany);
					}
					 return list ;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				} finally {
					//ÿ�β���֮�����ر�����
					DbUtils.closeConnection(connection, ps,resultSet);
				}
	}

	/**
	 * ͳ������δ��˵�֪ͨ���������
	 */
	public int countNoAuditTimeNotice() {
		//��ȡ���ݿ�����
		Connection connection = DbUtils.getConnection();
		String registSql = "select count(*) from notice_company where audit_date IS NULL";
		Statement statement = null ;
		ResultSet resultSet = null ;
		int count = 0;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(registSql);
			 //ֻҪresultSetָ�����һ��Ԫ�������ݣ���ô��һֱִ�в�ѯ�븳ֵ����
			if (resultSet.next()) {
				count = resultSet.getInt(1);
			}
			 return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			//ÿ�β���֮�����ر�����
			DbUtils.closeConnection(connection, statement,resultSet);
		}
	}

	@Override
	public NoticeAdmin queryNoticeAdminById(int adminNoticeId) {
		//��ȡ���ݿ�����
				Connection connection = DbUtils.getConnection();
				String registSql = "select * from notice_admin  where ID = ?";
				NoticeAdmin noticeAdmin = null;
				PreparedStatement ps = null ;
				ResultSet resultSet = null ;
				try {
					 //��̬������ֵ
					 ps = connection.prepareStatement(registSql);
					 ps.setInt(1, adminNoticeId);
					 resultSet = ps.executeQuery();
					 while (resultSet.next()) {
						 noticeAdmin = new NoticeAdmin();
						 noticeAdmin.setId(resultSet.getInt("ID"));
						 noticeAdmin.setReleaseDate(resultSet.getDate("release_date"));
						 noticeAdmin.setTitle(resultSet.getString("title"));
						 noticeAdmin.setContent(resultSet.getString("content"));
					}
					 return noticeAdmin ;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				} finally {
					//ÿ�β���֮�����ر�����
					DbUtils.closeConnection(connection, ps);
				}
	}

	@Override
	public int countAdminNotice() {
		//��ȡ���ݿ�����
				Connection connection = DbUtils.getConnection();
				String registSql = "select count(*) from notice_admin";
				Statement statement = null ;
				ResultSet resultSet = null ;
				int count = 0;
				try {
					statement = connection.createStatement();
					resultSet = statement.executeQuery(registSql);
					 //ֻҪresultSetָ�����һ��Ԫ�������ݣ���ô��һֱִ�в�ѯ�븳ֵ����
					if (resultSet.next()) {
						count = resultSet.getInt(1);
					}
					 return count;
				} catch (Exception e) {
					e.printStackTrace();
					return 0;
				} finally {
					//ÿ�β���֮�����ر�����
					DbUtils.closeConnection(connection, statement,resultSet);
				}
	}

	@Override
	public List<NoticeAdmin> queryAdminNotice(int pageNow, int pageSize) {
		//��ȡ���ݿ�����
		Connection connection = DbUtils.getConnection();
		String registSql = "select * from notice_admin limit ?,?";
		PreparedStatement ps = null ;
		ResultSet resultSet = null ;
		List<NoticeAdmin> list = new ArrayList<NoticeAdmin>();
		NoticeAdmin noticeAdmin = null ;
		try {
			 ps = connection.prepareStatement(registSql);
			 ps.setInt(1, (pageNow-1)*pageSize);
			 ps.setInt(2, pageSize);
			 //ִ�в�ѯ���
			 resultSet = ps.executeQuery();
			 //ֻҪresultSetָ�����һ��Ԫ�������ݣ���ô��һֱִ�в�ѯ�븳ֵ����
			 while (resultSet.next()) {
				 noticeAdmin = new NoticeAdmin();
				 noticeAdmin.setId(resultSet.getInt("ID"));
				 noticeAdmin.setReleaseDate(resultSet.getDate("release_date"));
				 noticeAdmin.setTitle(resultSet.getString("title"));
				 noticeAdmin.setContent(resultSet.getString("content"));
				 //��ӽ�List��
				 list.add(noticeAdmin);
			}
			 return list ;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			//ÿ�β���֮�����ر�����
			DbUtils.closeConnection(connection, ps,resultSet);
		}
	}

	@Override
	public boolean updateAdminNotic(NoticeAdmin noticeAdmin) {
		//��ȡ���ݿ�����
				Connection connection = DbUtils.getConnection();
				String registSql = "update notice_admin set content = ? ,title = ?where ID = ?";
				PreparedStatement ps = null ;
				try {
					 connection.setAutoCommit(false);//�����ֶ��ύ����
					 ps = connection.prepareStatement(registSql);
					 ps.setString(1,noticeAdmin.getContent());
					 ps.setString(2,noticeAdmin.getTitle());
					 ps.setInt(3, noticeAdmin.getId());
					 ps.execute();
					 connection.commit();//�ύ����
					 return true ;
				} catch (Exception e) {
					e.printStackTrace();
					if (connection != null) {
						try {
							connection.rollback();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					return false;
				} finally {
					//ÿ�β���֮�����ر�����
					DbUtils.closeConnection(connection, ps);
				}
	}

	@Override
	public boolean deleteAdminNotic(int adminNoticeId) {
		//��ȡ���ݿ�����
		Connection connection = DbUtils.getConnection();
		String registSql = "delete from notice_admin  where ID = ?";
		PreparedStatement ps = null ;
		try {
			 connection.setAutoCommit(false);//�����ֶ��ύ����
			 ps = connection.prepareStatement(registSql);
			 ps.setInt(1, adminNoticeId);
			 ps.execute();
			 connection.commit();//�ύ����
			 return true ;
		} catch (Exception e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			return false;
		} finally {
			//ÿ�β���֮�����ر�����
			DbUtils.closeConnection(connection, ps);
		}
	}

	@Override
	public void provideAdminAnnouncement(NoticeAdmin noticeAdmin) {
		Connection connection = DbUtils.getConnection();
		String registSql = "insert into notice_admin(release_date,"
				+ "title,content)"
				+ " values(?,?,?)";
		PreparedStatement ps = null ;
		try {
			 connection.setAutoCommit(false);//�����ֶ��ύ����
			 ps = connection.prepareStatement(registSql);
			 ps.setDate(1, noticeAdmin.getReleaseDate());
			 ps.setString(2, noticeAdmin.getTitle());
			 ps.setString(3,noticeAdmin.getContent());
			 ps.execute();
			 connection.commit();//�ύ����
		} catch (Exception e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			//ÿ�β���֮�����ر�����
			DbUtils.closeConnection(connection, ps);
		}
	}

	@Override
	public List<NoticeCompany> queryAllCompanyNoticeOrderByDate(int pageNow, int pageSize) {
		Connection connection = DbUtils.getConnection();
		String registSql = "select * from notice_company where audit_date IS NOT NULL order by release_date DESC limit ?,?";
		PreparedStatement ps = null ;
		ResultSet resultSet = null ;
		List<NoticeCompany> list = new ArrayList<NoticeCompany>();
		NoticeCompany noticeCompany = null ;
		try {
			 ps = connection.prepareStatement(registSql);
			 ps.setInt(1, (pageNow-1)*pageSize);
			 ps.setInt(2, pageSize);
			 //ִ�в�ѯ���
			 resultSet = ps.executeQuery();
			 //ֻҪresultSetָ�����һ��Ԫ�������ݣ���ô��һֱִ�в�ѯ�븳ֵ����
			 while (resultSet.next()) {
				 noticeCompany = new NoticeCompany();
				 noticeCompany.setCompanyUsername(resultSet.getString("company_username"));
				 noticeCompany.setId(resultSet.getInt("ID"));
				 noticeCompany.setReleaseDate(resultSet.getDate("release_date"));
				 noticeCompany.setAuditDate(resultSet.getDate("audit_date"));
				 noticeCompany.setTitle(resultSet.getString("title"));
				 noticeCompany.setContent(resultSet.getString("content"));
				 //��ӽ�List��
				 list.add(noticeCompany);
			}
			 return list ;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			//ÿ�β���֮�����ر�����
			DbUtils.closeConnection(connection, ps,resultSet);
		}
	}

	@Override
	public List<NoticeAdmin> queryAllAdminNoticeOrderByDate(int pageNow, int pageSize) {
		//��ȡ���ݿ�����
				Connection connection = DbUtils.getConnection();
				String registSql = "select * from notice_admin order by release_date DESC limit ?,?";
				PreparedStatement ps = null ;
				ResultSet resultSet = null ;
				List<NoticeAdmin> list = new ArrayList<NoticeAdmin>();
				NoticeAdmin noticeAdmin = null ;
				try {
					 ps = connection.prepareStatement(registSql);
					 ps.setInt(1, (pageNow-1)*pageSize);
					 ps.setInt(2, pageSize);
					 //ִ�в�ѯ���
					 resultSet = ps.executeQuery();
					 //ֻҪresultSetָ�����һ��Ԫ�������ݣ���ô��һֱִ�в�ѯ�븳ֵ����
					 while (resultSet.next()) {
						 noticeAdmin = new NoticeAdmin();
						 noticeAdmin.setId(resultSet.getInt("ID"));
						 noticeAdmin.setReleaseDate(resultSet.getDate("release_date"));
						 noticeAdmin.setTitle(resultSet.getString("title"));
						 noticeAdmin.setContent(resultSet.getString("content"));
						 //��ӽ�List��
						 list.add(noticeAdmin);
					}
					 return list ;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				} finally {
					//ÿ�β���֮�����ر�����
					DbUtils.closeConnection(connection, ps,resultSet);
				}
	}

	@Override
	public int countAllAuditCompanyNotice() {
		Connection connection = DbUtils.getConnection();
		String registSql = "select count(*) from notice_company where audit_date IS NOT NULL";
		Statement statement = null ;
		ResultSet resultSet = null ;
		int count = 0;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(registSql);
			 //ֻҪresultSetָ�����һ��Ԫ�������ݣ���ô��һֱִ�в�ѯ�븳ֵ����
			if (resultSet.next()) {
				count = resultSet.getInt(1);
			}
			 return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			//ÿ�β���֮�����ر�����
			DbUtils.closeConnection(connection, statement,resultSet);
		}
	}

}
