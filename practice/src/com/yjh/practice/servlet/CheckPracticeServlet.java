package com.yjh.practice.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yjh.practice.dao.impl.ProjectDaoImpl;
import com.yjh.practice.utils.PageUtils;





@WebServlet("/CheckPracticeServlet")
public class CheckPracticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckPracticeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * ������󷽰�
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String no = request.getParameter("no");
		// 1��2���ֱ��ʾ��ˡ�����
		String type = request.getParameter("type");
		boolean type_boolean = false;
		String role = (String) request.getSession().getAttribute("role");
		if ((type.equals("1") || type.equals("2")) && role.equals("9")) {
			if (Integer.parseInt(type) == 1)
				type_boolean = true;
			else if (Integer.parseInt(type) == 2)
				type_boolean = false;
			ProjectDaoImpl projectDaoImpl = new ProjectDaoImpl();
			// �������ʵѵ���������ز���������ɹ��������ͬһ����
			boolean b = projectDaoImpl.checkProject(no, type_boolean);
			PageUtils pageUtils=(PageUtils) request.getSession().getAttribute("selectProjectPageUtils");
			
			request.getRequestDispatcher("/SelectPracticeServlet?nowPage="+pageUtils.getPageNow()).forward(request, response);
		} else {
			// �û�������Ч
			//��ת��404ҳ��,����ӡ������Ϣ
			String errorMessage = "��ǰ�û���Ȩ���ʣ�����ʷ�ʽ�Ƿ���";
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

