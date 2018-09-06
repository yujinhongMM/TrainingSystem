package com.yjh.practice.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yjh.practice.dao.impl.CompanyDaoImpl;
import com.yjh.practice.dao.impl.ProjectDaoImpl;
import com.yjh.practice.dao.impl.StudentDaoImpl;
import com.yjh.practice.model.Company;
import com.yjh.practice.model.Project;
import com.yjh.practice.model.ProjectSelect;
import com.yjh.practice.model.Student;
import com.yjh.practice.service.impl.ProjectServiceImpl;



@WebServlet("/StudentSelectPracticeServlet")
public class StudentSelectPracticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentSelectPracticeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * ѧ����ѯ��ѡ����
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String stu_no = (String) request.getSession().getAttribute("account");
		String role = (String) request.getSession().getAttribute("role");

		if (role.equals("2")) {
			//ѧ��ѡ�񷽰��Ƿ���
			ProjectServiceImpl projectServiceImpl = new ProjectServiceImpl();
			request.setAttribute("PracticeIsUnderWay", projectServiceImpl.findPracticeIsUnderWay());
			
			StudentDaoImpl studentDaoImpl = new StudentDaoImpl();
			Student student = studentDaoImpl.findById(stu_no);

			String nowPage = request.getParameter("nowPage");
			if (nowPage == null)
				nowPage = 1 + "";
			ProjectDaoImpl projectDaoImpl = new ProjectDaoImpl();

			ArrayList<Project> projects = projectDaoImpl.findAllProject(student.getGrade());

			// ��ѯѧ����ѡ����
			ArrayList<Project> chosenProject = projectDaoImpl.findAllChosenProject(student.getNo());
			if (chosenProject == null) {
				// ��ѯѧ����ѡ����ʧ�ܣ��޷�����
				//��ת��404ҳ��,����ӡ������Ϣ
				String errorMessage = "�������ݿ�����쳣���޷���ѯѧ����ѡ������";
				request.getSession().setAttribute("ErrorMessage", errorMessage);
				response.sendRedirect(request.getContextPath() + "/404.jsp");
			} else {
				//ͨ�������ű���ѧ���Ƿ�ѡ��÷���  1-��ѡ  0-δѡ
				HashMap<String, Integer> choiceState = new HashMap<>();
				//ͨ�������ű��淽��������ҵ����
				HashMap<String, Company> companyInfo = new HashMap<>();
				CompanyDaoImpl companyDaoImpl=new CompanyDaoImpl();
				for (int i = 0; i < projects.size(); i++) {
					
					Company company=companyDaoImpl.queryByUserName(projects.get(0).getCompanyUsername());
					companyInfo.put(projects.get(i).getNo(), company);
					choiceState.put(projects.get(i).getNo(), 0);
					for (int j = 0; j < chosenProject.size(); j++) {
						if (projects.get(i).getNo().equals(chosenProject.get(j).getNo())) {
							choiceState.put(projects.get(i).getNo(), 1);
							break;
						} else {
							choiceState.put(projects.get(i).getNo(), 0);
						}
					}
				}
				ArrayList<ProjectSelect> projectSelects=projectDaoImpl.findStuProject(stu_no);
				if(projectSelects.size()>0){
					request.setAttribute("stuProjectNo", projectSelects.get(0).getId().getProjectNo().toString());
				}else{
					request.setAttribute("stuProjectNo", "0");
				}
			
				request.setAttribute("selectProjects", projects);
				request.setAttribute("choiceState", choiceState);
				request.setAttribute("companyInfo", companyInfo);
				
				request.getRequestDispatcher("/jsp/studentSelection.jsp").forward(request, response);
			}
		} else {
			// ��ɫ��ƥ��
			//��ת��404ҳ��,����ӡ������Ϣ
			String errorMessage = "�û�Ȩ�޲��㣡";
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
