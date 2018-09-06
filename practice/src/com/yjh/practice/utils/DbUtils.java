package com.yjh.practice.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 * 
 * Description: �������ݿ���� 
 * @author YJH
 * @date 2018��6��1��  
 *
 */
public class DbUtils {
	
	private static Connection connection ;
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://127.0.0.1:3306/practice";
	private static String username = "root" ;
	private static String password = "y84762514";
	public static Connection getConnection() {
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (connection != null) {
			return connection;
		}
		return null; 
	}
	
	/**
	 * 
	 * <p>Title: closeConnection</p>
	 * <p>Description: ���ݿ�رղ���</p>
	 * @param connection Connection����
	 * @param statement Statement����
	 * @param resultSet ResultSet����
	 */
	
	public static void closeConnection(Connection connection, Statement statement,ResultSet resultSet){
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (statement != null) {
					try {
						statement.close();
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (connection != null) {
							try {
								connection.close();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * 
	 * <p>Title: closeConnection</p>
	 * <p>Description: ���ݿ�رղ���</p>
	 * @param connection Connection����
	 * @param statement PreparedStatement����
	 * @param resultSet ResultSet����
	 */
	public static void closeConnection(Connection connection, PreparedStatement statement,ResultSet resultSet){
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (statement != null) {
					try {
						statement.close();
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (connection != null) {
							try {
								connection.close();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * 
	 * <p>Title: closeConnection</p>
	 * <p>Description: ���ݿ�رղ���</p>
	 * @param connection Connection����
	 * @param statement PreparedStatement����
	 */
	public static void closeConnection(Connection connection, PreparedStatement statement){
				if (statement != null) {
					try {
						statement.close();
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (connection != null) {
							try {
								connection.close();
							} catch (Exception e) {
								e.printStackTrace();
					}
				}
			}
		}
	}
}
