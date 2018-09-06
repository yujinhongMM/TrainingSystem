package com.yjh.practice.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yjh.practice.service.impl.UserServiceImpl;
/**
 * 
 * Description ��¼
 * @author YJH
 * @date 2018��6��2��  
 *
 */

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		// ��ȡҳ�洫��ĸ���ֵ
		HttpSession session = request.getSession();
		// ҳ���õ��û�������˺���Ϣ
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String Verification_Code = request.getParameter("verificationCode");
		//System.out.println(Verification_Code);
		String role = request.getParameter("role");
		//System.out.println(role);
		//System.out.println(account);
		// ҳ���õ��ɺ�̨��������֤��
		String vchidden =(String) request.getSession().getAttribute("vchidden");
		//System.out.println(vchidden);
		UserServiceImpl usi = new UserServiceImpl();
		if (usi.login(account, password, Verification_Code, role, vchidden)) {
			// ��role���뵽session��
			session.setAttribute("role", role);
			// ���û������뵽session��
			session.setAttribute("account", account);
			// �����¼�ɹ�����ת����Ӧҳ��
			response.sendRedirect("UserInfoServlet");
		} else {
			// �����¼���ɹ�����ת��loginҳ��,����ӡ������Ϣ
			 request.setAttribute("msg", "��¼ʧ�����������룡");  
	         request.getRequestDispatcher("/jsp/login.jsp").forward(request, response); 
		}

	}

}
