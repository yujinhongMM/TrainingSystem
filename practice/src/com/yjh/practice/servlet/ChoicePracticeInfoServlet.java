package com.yjh.practice.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yjh.practice.dao.impl.ProjectDaoImpl;
import com.yjh.practice.model.ProProSelStuView;
import com.yjh.practice.model.Project;
import com.yjh.practice.utils.PageUtils;


@WebServlet("/ChoicePracticeInfoServlet")
public class ChoicePracticeInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChoicePracticeInfoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * ��ҵ��ѯѧ��ѡ����ҵ�������
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String company_username = (String) request.getSession().getAttribute("account");
		String role = (String) request.getSession().getAttribute("role");
		if (company_username == null || !role.equals("1")) {
			//δͨ�������֤
			//��ת��404ҳ��,����ӡ������Ϣ
			String errorMessage = "��ǰ�û���Ȩ���ʣ�";
			request.getSession().setAttribute("ErrorMessage", errorMessage);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
		} else {
	
			String nowPage = request.getParameter("nowPage");
			if (nowPage == null)
				// δ�õ������ҳ����Ĭ��Ϊ1
				nowPage = 1 + "";
			ProjectDaoImpl projectDaoImpl = new ProjectDaoImpl();
			PageUtils pageUtils = null;
			if ((pageUtils = (PageUtils) request.getSession().getAttribute("choiceProjectInfoPageUtils")) == null) {
				pageUtils = new PageUtils(1, 0);
				pageUtils.setPageSize(10);
			} else {
				pageUtils.setPageNow(Integer.parseInt(nowPage));
			}
			
			
			
			ArrayList<ProProSelStuView> proProSelStuViews = null;

			//��ҵ�鿴ѧ��ѡ�񱾷������
			proProSelStuViews = projectDaoImpl.findAllStudentChoice(company_username, pageUtils);
			
			if(proProSelStuViews==null){
				request.getRequestDispatcher("/jsp/enterpriseManagementStudents.jsp").forward(request,
						response);
				return;
			}
			
			//��ѧ���Ƿ�����ȷ����ʵѵ�������б�ʶ
			HashMap< String, Boolean> stuHasProject=new HashMap<>();
			for(ProProSelStuView proProSelStuView:proProSelStuViews){
				if(projectDaoImpl.findStuProject(proProSelStuView.getStudent().getNo()).size()==0){
					//��ʾû���ѱ���ҵȷ����ѧ��ѡ��
					stuHasProject.put(proProSelStuView.getStudent().getNo(), true);
				}else{
					stuHasProject.put(proProSelStuView.getStudent().getNo(), false);
				}
			}
			request.setAttribute("stuHasProject", stuHasProject);
			
			// ��ѯ��ҵ���з�������ҳ��ͨ�������Ų�ѯѧ��ѡ����Ϣ
			ArrayList<Project> cUserAllProject = projectDaoImpl.findAllProject(company_username);
			request.getSession().setAttribute("cUserAllProject", cUserAllProject);

			request.getSession().setAttribute("choiceProjectInfoPageUtils", pageUtils);

			request.setAttribute("proProSelStuViews", proProSelStuViews);
			request.getRequestDispatcher("/jsp/enterpriseManagementStudents.jsp").forward(request,
					response);
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
