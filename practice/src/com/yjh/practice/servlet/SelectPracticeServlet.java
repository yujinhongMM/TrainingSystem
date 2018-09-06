package com.yjh.practice.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yjh.practice.dao.CompanyDao;
import com.yjh.practice.dao.impl.CompanyDaoImpl;
import com.yjh.practice.dao.impl.ProjectDaoImpl;
import com.yjh.practice.model.Company;
import com.yjh.practice.model.Project;
import com.yjh.practice.service.impl.ProjectServiceImpl;
import com.yjh.practice.utils.PageUtils;


@WebServlet("/SelectPracticeServlet")
public class SelectPracticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SelectPracticeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * ͨ��session��ĵ�¼��������(��ҵ������Ա)��ȡ��Ӧ������Ϣ(��ҳ��ѯ)
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String company_username = (String) request.getSession().getAttribute("account");//�˺�
		String role = (String) request.getSession().getAttribute("role");//��ɫ
		// role=9+"";
		if (role == null) {
			System.out.println("role Ϊ��");
			//��ת��404ҳ��,����ӡ������Ϣ
			String errorMessage = "��ǰ�û�Ȩ�޲���,role Ϊ�գ�";
			request.getSession().setAttribute("ErrorMessage", errorMessage);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
		} else if (role.equals("1") || role.equals("9")) {
			String nowPage = request.getParameter("nowPage");//��ȡ��ǰҳ��
			if (nowPage == null)
				nowPage = 1 + "";
			ProjectDaoImpl projectDaoImpl = new ProjectDaoImpl();
			PageUtils pageUtils = null;
			if ((pageUtils = (PageUtils) request.getSession().getAttribute("selectProjectPageUtils")) == null) {
				pageUtils = new PageUtils(1, 0);
				pageUtils.setPageSize(10);
			} else {
				pageUtils.setPageNow(Integer.parseInt(nowPage));
			}
			ArrayList<Project> projects = null;
			
			projects = projectDaoImpl.findAllProject(Integer.parseInt(role), company_username, pageUtils);
			

			// ͨ�������ű��淽��������ҵ����
			HashMap<String, Company> companyInfo = new HashMap<>();
			CompanyDao companyDaoImpl = new CompanyDaoImpl();
			if (projects != null) {
				for (int i = 0; i < projects.size(); i++) {
					Company company = companyDaoImpl.queryByUserName(projects.get(i).getCompanyUsername());
					companyInfo.put(projects.get(i).getNo(), company);
				}
			}
			
			//����Ա�Ƿ�����ҵ��ӷ���
			ProjectServiceImpl projectServiceImpl = new ProjectServiceImpl();
			request.setAttribute("AddPracticeIsUnderWay", projectServiceImpl.findAddPracticeIsUnderWay());
			
			request.setAttribute("companyInfo", companyInfo);
			request.getSession().setAttribute("selectProjectPageUtils", pageUtils);

			request.setAttribute("selectProjects", projects);
			request.setAttribute("selectProjectsRole", role);
			request.getRequestDispatcher("/jsp/programManagement.jsp").forward(request, response);
		} else {
			// ѧ���޷�����
			//��ת��404ҳ��,����ӡ������Ϣ
			String errorMessage = "��ǰ�û�Ȩ�޲��㣡";
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

