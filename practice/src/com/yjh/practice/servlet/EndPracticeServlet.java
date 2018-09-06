package com.yjh.practice.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yjh.practice.dao.impl.ProjectDaoImpl;
import com.yjh.practice.model.Project;

/**
 * 
 * Description
 * @author YJH
 * @date 2018��6��5��  
 *
 */

@WebServlet("/EndPracticeServlet")
public class EndPracticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EndPracticeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String role = (String) request.getSession().getAttribute("role");
		if (role.equals("9")) {
			ProjectDaoImpl projectDaoImpl = new ProjectDaoImpl();
			ArrayList<Project> projects = projectDaoImpl.findAllStartedProject();
			String[] p_no = new String[projects.size()];
			for (int i = 0; i < projects.size(); i++)
				p_no[i] = projects.get(i).getNo();
			projectDaoImpl.endProjects(p_no);
			request.getRequestDispatcher("").forward(request, response);
		}else{
			//���ǹ���Ա����Ȩ���ʣ�����404ҳ��
			//��ת��404ҳ��,����ӡ������Ϣ
			String errorMessage = "��ǰ�û���Ȩ���ʣ�";
			request.getSession().setAttribute("ErrorMessage", errorMessage);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
