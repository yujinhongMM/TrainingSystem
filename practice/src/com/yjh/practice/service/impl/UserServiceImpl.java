package com.yjh.practice.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yjh.practice.service.UserService;
import com.yjh.practice.utils.DbUtils;
import com.yjh.practice.utils.MdPwdUtil;

/**
 * 
 * Description �û�ҵ���߼����������û���¼�ȹ�����صĽӿ�ʵ��
 * @author YJH
 * @date 2018��6��2��  
 *
 */



public class UserServiceImpl implements UserService{
	
	@Override
	//�û���¼ʱ����ҳ��ѡ���ɫ��Ȼ��������Ҫ�Ĳ����������֤���session�е�һ�£��������һ����֤
	//���role=1������ҵ�����role=2����ѧ�������role=9����ϵͳ������
	public boolean login(String account, String password, String Verification_Code,String role,String vchidden) {
		Connection con = (Connection) DbUtils.getConnection();
		String sql = "";
		ResultSet rs = null;
		PreparedStatement ps = null;
		//String account_type = "";
		System.out.println(Verification_Code + " "+ vchidden.toUpperCase());
		//�����֤�벻��ȷ��û�еõ���֤�룬����false
		if(Verification_Code == null || !vchidden.equals(Verification_Code.toUpperCase())){
			return false;
		}
		
		//����û���ɫû��ѡ�У���ֱ�ӷ���false
		if(role == null){
			return false;
		}else{
			//���ݲ�ͬ�Ľ�ɫ�����ɲ�ͬ��sql���
			switch(role){
			case "1": 
				//"��ҵ";
				sql = "select * from company where username=? and password = ?"; 
				break;
			case "2": 
				//"ѧ��";
				sql = "select * from student where No=? and password = ?"; 
				break;
			case "9": 
				//"����Ա";
				sql = "select * from system_parameter where admin_username=? and admin_password = ?";
			}
		}
		try {
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, account);
			ps.setString(2, MdPwdUtil.MD5Password(password));
			System.out.println(ps.toString());
			rs = ps.executeQuery();
//			System.out.println(ps.toString());
			if(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbUtils.closeConnection(con, ps, rs);
		}
		return false;
	}

	
}
