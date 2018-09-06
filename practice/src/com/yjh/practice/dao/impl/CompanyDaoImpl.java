package com.yjh.practice.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.yjh.practice.dao.CompanyDao;
import com.yjh.practice.model.Company;
import com.yjh.practice.utils.DbUtils;

public class CompanyDaoImpl implements CompanyDao{
	/**
	 *	��ҵע��
	 */
	public boolean registerCompanyInfo(Company company) {
		//��ȡ���ݿ�����
		Connection connection = DbUtils.getConnection();
		String registSql = "insert into company(username,company_name,mailbox,password,contacts,phone) values(?,?,?,?,?,?)";
		PreparedStatement ps = null ;
		try {
			 connection.setAutoCommit(false);//�����ֶ��ύ����
			 ps = connection.prepareStatement(registSql);
			 ps.setString(1, company.getUsername());
			 ps.setString(2, company.getCompanyName());
			 ps.setString(3, company.getMailbox());
			 ps.setString(4, company.getPassword());
			 ps.setString(5, company.getContacts());
			 ps.setString(6, company.getPhone());
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
			return false ;
		} finally {
			//ÿ�β���֮�����ر�����
			DbUtils.closeConnection(connection, ps);
		}
	}

	/**
	 * ������ҵ��Ϣ
	 */
	public boolean updateCompanyInfo(Company company) {
		Connection connection = DbUtils.getConnection();
		//sqlƴ�Ӹ�����䣬��ֹsqlע��
		String updateSql = "update company set company_name = ?,"
				+ "contacts = ?,phone = ?,"
				+ "address = ?,profile = ?,audit_date = ? where username=?";
		PreparedStatement ps = null ;
		try {
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(updateSql);
			ps.setString(1, company.getCompanyName());
			ps.setString(2, company.getContacts());
			ps.setString(3, company.getPhone());
			ps.setString(4, company.getAddress());
			ps.setString(5, company.getProfile());
			ps.setDate(6, company.getAuditDate());
			ps.setString(7, company.getUsername());
			ps.executeUpdate();
			connection.commit();
			return true ;
		} catch(Exception e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			return false ;
		} finally {
			DbUtils.closeConnection(connection, ps);
		}
	}

	/**
	 * <p>Title: queryCompanyInfo</p>
	 * <p>Description: ��ѯ��ҵ��Ϣ</p>
	 * @param companyUserName �û���
	 * @return Companyʵ��
	 */
	public Company queryCompanyInfo(String companyUserName) {
		return null;
	}

	/**
	 * �����������
	 */
	public boolean updateCompanyPassword(String companyUserName, String newPassword) {
		Connection connection = DbUtils.getConnection();
		//sqlƴ�Ӹ�����䣬��ֹsqlע��
		String updateSql = "update company set password = ? where username = ?";
		PreparedStatement ps = null ;
		try {
			connection.setAutoCommit(false);
			//���PreparedStatement����
			ps = connection.prepareStatement(updateSql);
			//��̬���ò���
			ps.setString(1, newPassword);
			ps.setString(2, companyUserName);
			//ִ�����
			ps.execute();
			//�ύ������������ύ�������ᷢ��SQL���
			connection.commit();
		} catch(Exception e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			DbUtils.closeConnection(connection, ps);
		}
		return false;
	}

	/**
	 * <p>Title: checkCompany</p>
	 * <p>Description:�ýӿڷ�����Ҫ�������Ա�����ҵ��Ϣ</p>
	 * @param company Companyʵ�������ö���
	 * @return ����һ�������ı�־,���ͨ������true,��˲�ͨ������false
	 */
	public boolean checkCompany(Company company) {
		Connection connection = DbUtils.getConnection();
		//sqlƴ�Ӹ�����䣬��ֹsqlע��
		String updateSql = "update company set audit_date = ? where username = ?";
		PreparedStatement ps = null ;
		try {
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(updateSql);
			ps.setDate(1, company.getAuditDate());
			ps.setString(2, company.getUsername());
			ps.executeUpdate();
			connection.commit();
			return true ;
		} catch(Exception e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			return false ;
		} finally {
			DbUtils.closeConnection(connection, ps);
		}
	}

	/**
	 * ��ѯ�Ѿ���˵Ĺ�˾���ж�������������� �Ƿ�Ϊ��
	 */
	public List<Company> queryViryFyCompanys() {
		Connection connection = DbUtils.getConnection();
		//sqlƴ�Ӹ�����䣬��ֹsqlע��
		String querySql = "select * from company where audit_date IS NOT NULL";
		Statement statement = null ;
		ResultSet resultSet = null ;
		Company company = null ;
		List<Company> list = new ArrayList<Company>() ;
		try {
			//���Statement����
			statement = connection.createStatement();
			resultSet = statement.executeQuery(querySql);
			while (resultSet.next()) {
				company = new Company();
				company.setUsername(resultSet.getString("username"));
				company.setCompanyName(resultSet.getString("company_name"));
				company.setMailbox(resultSet.getString("mailbox"));
				company.setPassword(resultSet.getString("password"));
				company.setContacts(resultSet.getString("contacts"));
				company.setPhone(resultSet.getString("phone"));
				company.setAddress(resultSet.getString("address"));
				company.setProfile(resultSet.getString("profile"));
				company.setAuditDate((resultSet.getDate("audit_date")));
				list.add(company);
			}
			return list ;
		} catch(Exception e) {
			e.printStackTrace();
			return null ;
		} finally {
			DbUtils.closeConnection(connection, statement,resultSet);
		}
	}

	/**
	 * ��ѯδ��˵Ĺ�˾���ж�������������� �Ƿ�Ϊ��
	 */
	public List<Company> queryNotVirefyCompanys() {
		Connection connection = DbUtils.getConnection();
		//sqlƴ�Ӹ�����䣬��ֹsqlע��
		String querySql = "select * from company where audit_date IS  NULL";
		Statement statement = null ;
		ResultSet resultSet = null ;
		Company company = null ;
		List<Company> list = new ArrayList<Company>() ;;
		try {
			//���PreparedStatement����
			statement = connection.createStatement();
			resultSet = statement.executeQuery(querySql);
			while (resultSet.next()) {
				company = new Company();
				company.setUsername(resultSet.getString("username"));
				company.setCompanyName(resultSet.getString("company_name"));
				company.setMailbox(resultSet.getString("mailbox"));
				company.setPassword(resultSet.getString("password"));
				company.setContacts(resultSet.getString("contacts"));
				company.setPhone(resultSet.getString("phone"));
				company.setAddress(resultSet.getString("address"));
				company.setProfile(resultSet.getString("profile"));
				company.setAuditDate((resultSet.getDate("audit_date")));
				list.add(company);
			}
			return list ;
		} catch(Exception e) {
			e.printStackTrace();
			return null ;
		} finally {
			DbUtils.closeConnection(connection, statement,resultSet);
		}
	}

	/**
	 * ��ѯ���й�˾
	 */
	public List<Company> queryAllCompanys() {
		Connection connection = DbUtils.getConnection();
		//sqlƴ�Ӹ�����䣬��ֹsqlע��
		String querySql = "select * from company ";
		Statement statement = null ;
		ResultSet resultSet = null ;
		Company company = null ;
		List<Company> list = new ArrayList<Company>() ;
		try {
			//���PreparedStatement����
			statement = connection.createStatement();
			resultSet = statement.executeQuery(querySql);
			while (resultSet.next()) {
				company = new Company();
				company.setUsername(resultSet.getString("username"));
				company.setCompanyName(resultSet.getString("company_name"));
				company.setMailbox(resultSet.getString("mailbox"));
				company.setPassword(resultSet.getString("password"));
				company.setContacts(resultSet.getString("contacts"));
				company.setPhone(resultSet.getString("phone"));
				company.setAddress(resultSet.getString("address"));
				company.setProfile(resultSet.getString("profile"));
				company.setAuditDate((resultSet.getDate("audit_date")));
				list.add(company);
			}
			return list ;
		} catch(Exception e) {
			e.printStackTrace();
			return null ;
		} finally {
			DbUtils.closeConnection(connection, statement,resultSet);
		}
	}

	/**
	 * ɾ����˾
	 */
	public boolean deleteCompany(String companyUsername) {
		Connection connection = DbUtils.getConnection();
		//sqlƴ�Ӹ�����䣬��ֹsqlע��
		String updateSql = "delete from company where username = ?";
		PreparedStatement ps = null ;
		try {
			connection.setAutoCommit(false);
			//���PreparedStatement����
			ps = connection.prepareStatement(updateSql);
			//��̬���ò���
			ps.setString(1, companyUsername);
			//ִ�����
			ps.execute();
			//�ύ������������ύ�������ᷢ��SQL���
			connection.commit();
			return true ;
		} catch(Exception e) {
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
			DbUtils.closeConnection(connection, ps);
		}
	}

	public Company queryByUserName(String account) {
		Connection connection = DbUtils.getConnection();
		//sqlƴ�Ӹ�����䣬��ֹsqlע��
		String querySql = "select * from company where username = ?";
		ResultSet resultSet = null ;
		PreparedStatement ps = null ;
		Company company = null ;
		try {
			//���PreparedStatement����
			ps = connection.prepareStatement(querySql);
			ps.setString(1, account);
			resultSet = ps.executeQuery();
			while (resultSet.next()) {
				company = new Company();
				company.setUsername(resultSet.getString("username"));
				company.setCompanyName(resultSet.getString("company_name"));
				company.setMailbox(resultSet.getString("mailbox"));
				company.setPassword(resultSet.getString("password"));
				company.setContacts(resultSet.getString("contacts"));
				company.setPhone(resultSet.getString("phone"));
				company.setAddress(resultSet.getString("address"));
				company.setProfile(resultSet.getString("profile"));
				company.setAuditDate((resultSet.getDate("audit_date")));
			}
			return company ;
		} catch(Exception e) {
			e.printStackTrace();
			return null ;
		} finally {
			DbUtils.closeConnection(connection, ps,resultSet);
		}
	}

	/**
	 * ���󣬰���������ÿ�
	 */
	public boolean backReview(Company company) {
		Connection connection = DbUtils.getConnection();
		//sqlƴ�Ӹ�����䣬��ֹsqlע��
		String updateSql = "update company set audit_date = ? where username = ?";
		PreparedStatement ps = null ;
		try {
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(updateSql);
			ps.setDate(1, company.getAuditDate());
			ps.setString(2, company.getUsername());
			ps.executeUpdate();
			connection.commit();
			return true ;
		} catch(Exception e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			return false ;
		} finally {
			DbUtils.closeConnection(connection, ps);
		}
	}
}

