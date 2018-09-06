package com.yjh.practice.servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yjh.practice.dao.impl.CompanyDaoImpl;
import com.yjh.practice.dao.impl.ProjectDaoImpl;
import com.yjh.practice.model.Company;
import com.yjh.practice.model.Project;
import com.yjh.practice.service.impl.ProjectServiceImpl;



@WebServlet("/UpdatePracticeServlet")
public class UpdatePracticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdatePracticeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * �õ���������ת���޸ķ�������
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String no = request.getParameter("no");
		String company_username = (String) request.getSession().getAttribute("account");
		String role = (String) request.getSession().getAttribute("role");
		ProjectServiceImpl projectServiceImpl = new ProjectServiceImpl();
		ProjectDaoImpl projectDaoImpl = new ProjectDaoImpl();
		if ((role.equals("1") && projectServiceImpl.findProjectBelongToUserByPNo(company_username, no))||role.equals("9")) {
			Project project = projectDaoImpl.findProjectByNo(no);
			//������꼶
		    int year=Calendar.getInstance().get(Calendar.YEAR);
			int flag=Calendar.getInstance().get(Calendar.MONTH)>8?1:0;
			int gradeFlag=flag+year-project.getGrade();
			String[] majors=project.getMajor().split(" ");
			HashMap<String, Integer> majorInfo=new HashMap<>();
			for(String marjor:majors){
				majorInfo.put(marjor, 1);
			}
			//��ѯ����������ҵ
			CompanyDaoImpl companyDaoImpl = new CompanyDaoImpl();
			Company company=companyDaoImpl.queryByUserName(project.getCompanyUsername());
			
			request.setAttribute("company", company);
			
			request.setAttribute("majorInfo", majorInfo);
			request.setAttribute("gradeFlag", gradeFlag);
			request.setAttribute("updateProjectInfo", project);
			request.setAttribute("updateProjectNo", no);
			request.getRequestDispatcher("/jsp/updatePractice.jsp").forward(request, response);
		} else {
			//��ת��404ҳ��,����ӡ������Ϣ
			String errorMessage = "�û�Ȩ�޲��㣡";
			request.getSession().setAttribute("ErrorMessage", errorMessage);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
		}
	}

	/**
	 * �޸ķ���������Ϣ
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String no = request.getParameter("no");
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
		ProjectServiceImpl projectServiceImpl = new ProjectServiceImpl();
		grade = projectServiceImpl.getStuGrade(grade);
		String company_username = (String) request.getSession().getAttribute("account");
		String role = (String) request.getSession().getAttribute("role");
		if ((role.equals("1") && projectServiceImpl.findProjectBelongToUserByPNo(company_username, no))||role.equals("9")) {

			// ���ݱ������½�project����
			Project project = new Project();
			project.setNo(no);
			project.setName(name);
			project.setMajor(major);
			
			//��ѯ����������ҵ
//			CompanyDaoImpl companyDaoImpl = new CompanyDaoImpl();
//			Company company=companyDaoImpl.queryByUserName();
//			
//			project.setCompanyUsername(company.getUsername());
			project.setIntroduction(introduction);
			project.setStudentsNum(students_num);
			project.setCategory(category);
			project.setGrade(grade);
			project.setCompanyTeacher(company_teacher);
			project.setCompanyTeacherTitle(company_teacher_title);
			

			ProjectDaoImpl projectDaoImpl = new ProjectDaoImpl();
			if (projectDaoImpl.updateProject(project))
				request.getRequestDispatcher("SelectPracticeServlet").forward(request, response);
			else{
				//��ת��404ҳ��,����ӡ������Ϣ
				String errorMessage = "�������ݿ�����쳣,����ʧ�ܣ�";
				request.getSession().setAttribute("ErrorMessage", errorMessage);
				response.sendRedirect(request.getContextPath() + "/404.jsp");
			}
			
		} else {
			//��ת��404ҳ��,����ӡ������Ϣ
			String errorMessage = "�û�Ȩ�޲��㣡";
			request.getSession().setAttribute("ErrorMessage", errorMessage);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
		}
	}

}
