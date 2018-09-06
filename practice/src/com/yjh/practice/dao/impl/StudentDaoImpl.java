package com.yjh.practice.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yjh.practice.dao.StudentDao;
import com.yjh.practice.model.Student;
import com.yjh.practice.utils.DbUtils;





public class StudentDaoImpl implements StudentDao {
	/**
	 * ����һ��ѧ����¼
	 * @param: student һ��ѧ��ʵ��
	 * @return: true ����ɹ���false ����ʧ��
	 */
	@Override
	public boolean insert(Student student) {
		Connection conn = DbUtils.getConnection();
		PreparedStatement pstmt = null;
		String sql="insert into student values(?,?,?,?,?,?,?,?,?,?,?,?)";
		try{
			//�ر��Զ��ύ
			conn.setAutoCommit(false);
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, student.getNo());
			pstmt.setString(2, student.getName());
			pstmt.setInt(3, student.getGrade());
			pstmt.setString(4, student.getLevel());
			pstmt.setString(5, student.getProfessional());
			pstmt.setString(6, student.getGender());
			pstmt.setString(7, student.getClass_());
			pstmt.setString(8, student.getPassword());
			pstmt.setString(9, student.getMailbox());
			pstmt.setString(10, student.getSubjectBackground());
			pstmt.setString(11, student.getLearningExperience());
			pstmt.setString(12, student.getResearchDirection());
			//ִ��sql���
			pstmt.executeUpdate();
			//�����ύ
			conn.commit();
					
		} catch(SQLException e){
			e.printStackTrace();
			return false;
		} finally{
			DbUtils.closeConnection(conn, pstmt, null);
		}		
		return true;
	}

	/**
	 * ��ѯ����ѧ����¼
	 * @return: ����ѧ��ʵ���б�
	 */
	@Override
	public List<Student> findAll() {
		Connection conn=DbUtils.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Student> list=new ArrayList<Student>();
		String sql="select * from student";
		try{
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Student student=new Student();
				student.setNo(rs.getString("No"));
				student.setName(rs.getString("name"));
				student.setGrade(rs.getInt("grade"));
				student.setLevel(rs.getString("level"));
				student.setProfessional(rs.getString("professional"));
				student.setGender(rs.getString("gender"));
				student.setClass_(rs.getString("class"));
				student.setPassword(rs.getString("password"));
				student.setMailbox(rs.getString("mailbox"));
				student.setSubjectBackground(rs.getString("subject_background"));
				student.setLearningExperience(rs.getString("learning_experience"));
				student.setResearchDirection(rs.getString("research_direction"));
				list.add(student);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DbUtils.closeConnection(conn, pstmt, rs);
		}		
		return list;
	}

	/**
	 * ��ѯָ��ѧ��ѧ����¼
	 * @param id ѧ��
	 * @return �鵽ѧ��ʵ��
	 */
	@Override
	public Student findById(String id) {
		Connection conn=DbUtils.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Student student=new Student();
		String sql="select * from student where No=?";
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()){
				student.setNo(rs.getString("No"));
				student.setName(rs.getString("name"));
				student.setGrade(rs.getInt("grade"));
				student.setLevel(rs.getString("level"));
				student.setProfessional(rs.getString("professional"));
				student.setGender(rs.getString("gender"));
				student.setClass_(rs.getString("class"));
				student.setPassword(rs.getString("password"));
				student.setMailbox(rs.getString("mailbox"));
				student.setSubjectBackground(rs.getString("subject_background"));
				student.setLearningExperience(rs.getString("learning_experience"));
				student.setResearchDirection(rs.getString("research_direction"));
			}else{
				student = null;
			}
		}catch(SQLException e){
			e.printStackTrace();
			student = null;
		}finally{
			DbUtils.closeConnection(conn, pstmt, rs);
		}		
		return student;
	}

	/**
	 * ��ѯ��ѡ/δѡѧ�����漰����ѯ��ѧ����+����ѡ���
	 * @param flag��1����ѯ��ѡѧ����2����ѯδѡѧ��
	 * @return �鵽ѧ��ʵ���б� 
	 */
	@Override
	public List<Student> findBySelected(int flag) {
		Connection conn=DbUtils.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Student> list=new ArrayList<Student>();		
		String sql=null;
		if(flag==1)
			sql="select * from student  "
					+ "where No in (select studentNo from project_select )";
		else if(flag==2)
			sql="select * from student  "
					+ "where No not in (select studentNo from project_select )";
		else{
			DbUtils.closeConnection(conn, pstmt, rs);
			return null;
		}
		try{
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Student student=new Student();
				student.setNo(rs.getString("No"));
				student.setName(rs.getString("name"));
				student.setGrade(rs.getInt("grade"));
				student.setLevel(rs.getString("level"));
				student.setProfessional(rs.getString("professional"));
				student.setGender(rs.getString("gender"));
				student.setClass_(rs.getString("class"));
				student.setPassword(rs.getString("password"));
				student.setMailbox(rs.getString("mailbox"));
				student.setSubjectBackground(rs.getString("subject_background"));
				student.setLearningExperience(rs.getString("learning_experience"));
				student.setResearchDirection(rs.getString("research_direction"));
				list.add(student);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DbUtils.closeConnection(conn, pstmt, rs);
		}		
		return list;
	}

	/**
	 * ����Ա������ҵ���Ʋ�ѯѧ����Ϣ
	 * @param companyName ��ҵ����
	 * @return ���ҵ�ѧ��ʵ���б�
	 */
	@Override
	public List<Student> findByCompany(String companyName) {
		Connection conn=DbUtils.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Student> list=new ArrayList<Student>();		
		String sql="select * from student  "
				+ "where no in (select studentNo from project_select "
				+ "where company_name=?)";
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, companyName);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Student student=new Student();
				student.setNo(rs.getString("No"));
				student.setName(rs.getString("name"));
				student.setGrade(rs.getInt("grade"));
				student.setLevel(rs.getString("level"));
				student.setProfessional(rs.getString("professional"));
				student.setGender(rs.getString("gender"));
				student.setClass_(rs.getString("class"));
				student.setPassword(rs.getString("password"));
				student.setMailbox(rs.getString("mailbox"));
				student.setSubjectBackground(rs.getString("subject_background"));
				student.setLearningExperience(rs.getString("learning_experience"));
				student.setResearchDirection(rs.getString("research_direction"));
				list.add(student);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			new DbUtils();
			DbUtils.closeConnection(conn, pstmt, rs);
		}		
		return list;		
	}

	/**
	 * ��ѯָ���꼶��ѧ����¼
	 * @param grade �꼶
	 * @return �鵽ѧ��ʵ���б�
	 */
	@Override
	public List<Student> findByGrade(int grade) {
		Connection conn=DbUtils.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Student> list=new ArrayList<Student>();		
		String sql="select * from student where grade=?";
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, grade);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Student student=new Student();
				student.setNo(rs.getString("No"));
				student.setName(rs.getString("name"));
				student.setGrade(rs.getInt("grade"));
				student.setLevel(rs.getString("level"));
				student.setProfessional(rs.getString("professional"));
				student.setGender(rs.getString("gender"));
				student.setClass_(rs.getString("class"));
				student.setPassword(rs.getString("password"));
				student.setMailbox(rs.getString("mailbox"));
				student.setSubjectBackground(rs.getString("subject_background"));
				student.setLearningExperience(rs.getString("learning_experience"));
				student.setResearchDirection(rs.getString("research_direction"));
				list.add(student);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DbUtils.closeConnection(conn, pstmt, rs);
		}		
		return list;		
	}

	/**
	 * ��ѯָ��רҵ��ѧ��
	 * @param major רҵ����
	 * @return �鵽ѧ��ʵ���б�
	 */
	@Override
	public List<Student> findByMajor(String major) {
		Connection conn=DbUtils.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Student> list=new ArrayList<Student>();		
		String sql="select * from student where professional=?";
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, major);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Student student=new Student();
				student.setNo(rs.getString("No"));
				student.setName(rs.getString("name"));
				student.setGrade(rs.getInt("grade"));
				student.setLevel(rs.getString("level"));
				student.setProfessional(rs.getString("professional"));
				student.setGender(rs.getString("gender"));
				student.setClass_(rs.getString("class"));
				student.setPassword(rs.getString("password"));
				student.setMailbox(rs.getString("mailbox"));
				student.setSubjectBackground(rs.getString("subject_background"));
				student.setLearningExperience(rs.getString("learning_experience"));
				student.setResearchDirection(rs.getString("research_direction"));
				list.add(student);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DbUtils.closeConnection(conn, pstmt, rs);
		}		
		return list;	
	}

	/**
	 * ����Ա����Ȳ�ѯѧ����¼����������������ȣ�
	 * @param year ���
	 * @return �鵽ѧ��ʵ���б�
	 */
	@Override
	public List<Student> findByYear(int year) {
		Connection conn=DbUtils.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Student> list=new ArrayList<Student>();		
		String sql="select * from student where No in"
				+ "(SELECT studentNo from project_select where projectNo like ?)";
		try{
			String strYear=""+year+"%";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, strYear);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Student student=new Student();
				student.setNo(rs.getString("No"));
				student.setName(rs.getString("name"));
				student.setGrade(rs.getInt("grade"));
				student.setLevel(rs.getString("level"));
				student.setProfessional(rs.getString("professional"));
				student.setGender(rs.getString("gender"));
				student.setClass_(rs.getString("class"));
				student.setPassword(rs.getString("password"));
				student.setMailbox(rs.getString("mailbox"));
				student.setSubjectBackground(rs.getString("subject_background"));
				student.setLearningExperience(rs.getString("learning_experience"));
				student.setResearchDirection(rs.getString("research_direction"));
				list.add(student);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DbUtils.closeConnection(conn, pstmt, rs);
		}		
		return list;

	}

	@Override
	public List<Student> findByProcdure(String procdure) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean findByProcdure(String procdure, Object[] params) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * ����ѧ����¼
	 * @param student һ��ѧ��ʵ��
	 * @return true:�ɹ���false��ʧ�� 
	 */
	@Override
	public boolean update(Student student) {
		Connection conn = DbUtils.getConnection();
		PreparedStatement pstmt = null;
		String sql="update student set name=?,grade=?,level=?,professional=?,"
				+ "gender=?,class=?,password=?,mailbox=?,subject_background=?,"
				+ "learning_experience=?,research_direction=? where No=?";
		try{
			//�ر��Զ��ύ
			conn.setAutoCommit(false);
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, student.getName());
			pstmt.setInt(2, student.getGrade());
			pstmt.setString(3, student.getLevel());
			pstmt.setString(4, student.getProfessional());
			pstmt.setString(5, student.getGender());
			pstmt.setString(6, student.getClass_());
			pstmt.setString(7, student.getPassword());
			pstmt.setString(8, student.getMailbox());
			pstmt.setString(9, student.getSubjectBackground());
			pstmt.setString(10, student.getLearningExperience());
			pstmt.setString(11, student.getResearchDirection());
			pstmt.setString(12, student.getNo());
			//ִ��sql���
			pstmt.executeUpdate();
			//�����ύ
			conn.commit();
					
		} catch(SQLException e){
			e.printStackTrace();
			return false;
		} finally{
			DbUtils.closeConnection(conn, pstmt, null);
		}		
		return true;
	}

	/**
	 * ����ѧ��������
	 * 
	 * ��ʱûд
	 */
	public String updateEmail(String role, String account, String mbemail) {
		
		return "";
	}
	/**
	 * ɾ��ָ��ѧ��
     * @param id ѧ��
     * @return true ɾ���ɹ���false ɾ��ʧ��
	 */
	@Override
	public boolean delete(String id) {
		Connection conn = DbUtils.getConnection();
		PreparedStatement pstmt = null;
		String sql="delete from student where No=?";
		try{
			//�ر��Զ��ύ
			conn.setAutoCommit(false);
			pstmt=conn.prepareStatement(sql);			
			pstmt.setString(1, id);
			//ִ��sql���
			pstmt.executeUpdate();
			//�����ύ
			conn.commit();
					
		} catch(SQLException e){
			e.printStackTrace();
			return false;
		} finally{
			DbUtils.closeConnection(conn, pstmt, null);
		}		
		return true;
	}

	@Override
	public boolean importStudent(List<Student> list) {
		Connection conn = DbUtils.getConnection();
		PreparedStatement pstmt = null;
		String sql="insert into student values(?,?,?,?,?,?,?,?,?,?,?,?)";
		try{
			//�ر��Զ��ύ
			conn.setAutoCommit(false);
			pstmt=conn.prepareStatement(sql);
			for(Student student : list) {
				pstmt.setString(1, student.getNo());
				pstmt.setString(2, student.getName());
				pstmt.setInt(3, student.getGrade());
				pstmt.setString(4, student.getLevel());
				pstmt.setString(5, student.getProfessional());
				pstmt.setString(6, student.getGender());
				pstmt.setString(7, student.getClass_());
				pstmt.setString(8, student.getPassword());
				pstmt.setString(9, student.getMailbox());
				pstmt.setString(10, student.getSubjectBackground());
				pstmt.setString(11, student.getLearningExperience());
				pstmt.setString(12, student.getResearchDirection());
				pstmt.addBatch();
			}
			pstmt.executeBatch();
			//�����ύ
			conn.commit();
			System.out.println("�ɹ�");
					
		} catch(SQLException e){
			e.printStackTrace();
			return false;
		} finally{
			DbUtils.closeConnection(conn, pstmt, null);
		}		
		return true;
	}

	@Override
	public boolean exportStudent(String fileName) {
		// TODO Auto-generated method stub
		return false;
	}

}
