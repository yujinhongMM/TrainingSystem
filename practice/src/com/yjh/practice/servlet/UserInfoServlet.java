package com.yjh.practice.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yjh.practice.dao.impl.CompanyDaoImpl;
import com.yjh.practice.dao.impl.StudentDaoImpl;



/**
 * 
 * Description �û���¼�����Ϣ
 * @author YJH
 * @date 2018��6��3��  
 *
 */
@WebServlet("/UserInfoServlet")
public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		// ���session���û��Ľ�ɫ
		String role =(String)session.getAttribute("role");
		// ���session���û����˻�
		String account =(String) session.getAttribute("account");
		if(role.equals("1")){
			CompanyDaoImpl company=new CompanyDaoImpl();
			session.setAttribute("company", company.queryByUserName(account));
			
			request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
		}else if(role.equals("2")){
			StudentDaoImpl student=new StudentDaoImpl();
			// ��ѧ����Ϣ����student
			session.setAttribute("student", student.findById(account));
			//response.sendRedirect("StudentSelectPracticeServlet");
			request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
		}else if(role.equals("9")){
			
			request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
		}
	}

}
