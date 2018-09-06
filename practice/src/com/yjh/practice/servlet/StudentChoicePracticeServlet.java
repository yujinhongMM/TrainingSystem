package com.yjh.practice.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yjh.practice.dao.impl.ProjectDaoImpl;
import com.yjh.practice.model.Project;
import com.yjh.practice.service.impl.ProjectServiceImpl;


/**
 * Servlet implementation class StudentChoicePracticeServlet
 */
@WebServlet("/StudentChoicePracticeServlet")
public class StudentChoicePracticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentChoicePracticeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * ѧ����ѡʵѵ����
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �ж��Ƿ��ڿ�ѡʱ����
		ProjectServiceImpl projectServiceImpl = new ProjectServiceImpl();
		if (!projectServiceImpl.findPracticeIsUnderWay()) {
			request.getRequestDispatcher("StudentSelectPracticeServlet").forward(request, response);
			return;
		}

		String p_no = request.getParameter("no");

		String stu_no = (String) request.getSession().getAttribute("account");
		String role = (String) request.getSession().getAttribute("role");

		if (role.equals("2")) {
			ProjectDaoImpl projectDaoImpl = new ProjectDaoImpl();
			Project project = projectDaoImpl.findProjectByNo(p_no);
			if (project == null) {
				//��ת��404ҳ��,����ӡ������Ϣ
				String errorMessage = "�������ݿ�����쳣����ѡ����δ�ҵ���";
				request.getSession().setAttribute("ErrorMessage", errorMessage);
				response.sendRedirect(request.getContextPath() + "/404.jsp");
			} else {
				boolean b = projectDaoImpl.unChooseProject(p_no, stu_no);
				if (b) {
					request.getRequestDispatcher("StudentSelectPracticeServlet").forward(request, response);
				} else{
					//��ת��404ҳ��,����ӡ������Ϣ
					String errorMessage = "��ѡ����ʧ�ܣ�";
					request.getSession().setAttribute("ErrorMessage", errorMessage);
					response.sendRedirect(request.getContextPath() + "/404.jsp");
				}
			}
		} else {
			//��ת��404ҳ��,����ӡ������Ϣ
			String errorMessage = "�û�Ȩ�޲��㣡";
			request.getSession().setAttribute("ErrorMessage", errorMessage);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
		}

	}

	/**
	 * ѡ��ʵѵ����
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �ж��Ƿ��ڿ�ѡʱ����
		ProjectServiceImpl projectServiceImpl = new ProjectServiceImpl();
		if (!projectServiceImpl.findPracticeIsUnderWay()) {
			request.getRequestDispatcher("StudentSelectPracticeServlet").forward(request, response);
			return;
		}

		System.out.println("----ѡ��ʵѵ����");
		request.setCharacterEncoding("utf-8");
		String p_no = request.getParameter("no");
		String reason = request.getParameter("reason");
		String stu_no = (String) request.getSession().getAttribute("account");
		String role = (String) request.getSession().getAttribute("role");
		if (role.equals("2")) {
			ProjectDaoImpl projectDaoImpl = new ProjectDaoImpl();
			Project project = projectDaoImpl.findProjectByNo(p_no);
			if (project == null) {
				//��ת��404ҳ��,����ӡ������Ϣ
				String errorMessage = "�������ݿ�����쳣����ѡ����δ�ҵ���";
				request.getSession().setAttribute("ErrorMessage", errorMessage);
				response.sendRedirect(request.getContextPath() + "/404.jsp");
			} else {
				boolean b = projectDaoImpl.chooseProject(project.getCompanyUsername(), p_no, stu_no, reason);
				if (b) {
					request.getRequestDispatcher("StudentSelectPracticeServlet").forward(request, response);
				} else{
					//��ת��404ҳ��,����ӡ������Ϣ
					String errorMessage = "�������ݿ�����쳣,ѡ�񷽰�ʧ�ܣ�";
					request.getSession().setAttribute("ErrorMessage", errorMessage);
					response.sendRedirect(request.getContextPath() + "/404.jsp");
				}
			}
		} else {
			//��ת��404ҳ��,����ӡ������Ϣ
			String errorMessage = "�û�Ȩ�޲��㣡";
			request.getSession().setAttribute("ErrorMessage", errorMessage);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
		}
	}

}
