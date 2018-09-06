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



@WebServlet("/AddPracticeServlet")
public class AddPracticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddPracticeServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//����Ա�Ƿ�����ҵ��ӷ���
		ProjectServiceImpl projectServiceImpl = new ProjectServiceImpl();
		if(!projectServiceImpl.findAddPracticeIsUnderWay()){
			request.getRequestDispatcher("SelectPracticeServlet?selectProjectType=1").forward(request, response);
			return;
		}
		
		request.setCharacterEncoding("utf-8");
		// ��ҵ�û����ӵ�¼ʱ������session���account��ȡ
		String company_username = (String) request.getSession().getAttribute("account");
		String role = (String) request.getSession().getAttribute("role");
		if (company_username == null||!role.equals("1")) {
			//�û���ݲ���
			//��ת��404ҳ��,����ӡ������Ϣ
			String errorMessage = "�û�Ȩ�޲��㣡";
			request.getSession().setAttribute("ErrorMessage", errorMessage);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
		} else {
			String majors[] = request.getParameterValues("major");
			String name = request.getParameter("name");
			String introduction = request.getParameter("introduction");
			int students_num = Integer.parseInt(request.getParameter("students_num"));
			String category = request.getParameter("category");
			int grade = Integer.parseInt(request.getParameter("grade"));
			String company_teacher = request.getParameter("company_teacher");
			String company_teacher_title = request.getParameter("company_teacher_title");
			// �Եõ���majors����������Ӵ���
			String major = "";
			for (int i = 0; i < majors.length; i++)
				major += majors[i] + " ";
			grade = projectServiceImpl.getStuGrade(grade);

			// ���ݱ������½�project����
			Project project = new Project();
			project.setName(name);
			project.setMajor(major);
			project.setCompanyUsername(company_username);
			project.setIntroduction(introduction);
			project.setStudentsNum(students_num);
			project.setCategory(category);
			project.setGrade(grade);
			project.setCompanyTeacher(company_teacher);
			project.setCompanyTeacherTitle(company_teacher_title);

			ProjectDaoImpl projectDaoImpl = new ProjectDaoImpl();
			if (projectDaoImpl.addProject(project)){
				//ʵѵ������ӳɹ�
				request.getRequestDispatcher("SelectPracticeServlet?selectProjectType=1").forward(request, response);
			}else{
				//ʵѵ�������ʧ��
				//��ת��404ҳ��,����ӡ������Ϣ
				String errorMessage = "���ʵѵ����ʧ�ܣ������Ƿ����������쳣��";
				request.getSession().setAttribute("ErrorMessage", errorMessage);
				response.sendRedirect(request.getContextPath() + "/404.jsp");
			}
		}

	}

}
