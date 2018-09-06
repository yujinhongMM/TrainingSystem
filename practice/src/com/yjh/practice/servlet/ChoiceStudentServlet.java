package com.yjh.practice.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yjh.practice.dao.impl.ProjectDaoImpl;
import com.yjh.practice.service.impl.ProjectServiceImpl;
import com.yjh.practice.utils.PageUtils;


@WebServlet("/ChoiceStudentServlet")
public class ChoiceStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChoiceStudentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * �Ե���ѧ������ѡ����ѡ
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("type");
		String stu_no = request.getParameter("stu_no");
		String p_no = request.getParameter("p_no");
		String company_username = (String) request.getSession().getAttribute("account");
		String role = (String) request.getSession().getAttribute("role");
		ProjectServiceImpl projectServiceImpl=new ProjectServiceImpl();
		System.out.println("ѡ����ѡ     "+role+"    "+company_username +"   "+projectServiceImpl.findProjectBelongToUserByPNo(company_username, p_no));
		if(role.equals("1")&&projectServiceImpl.findProjectBelongToUserByPNo(company_username, p_no)){
			//��ɫΪ��ҵ���Ը÷���ӵ��Ȩ��
			ProjectDaoImpl projectDaoImpl = new ProjectDaoImpl();
			
			PageUtils pageUtils = null;
			if ((pageUtils = (PageUtils) request.getSession().getAttribute("choiceProjectInfoPageUtils")) == null) {
				pageUtils = new PageUtils(1, 0);
				pageUtils.setPageSize(10);
			}
			if (type.equals("1")) {
				// ѡ��ѧ��
				boolean b = projectDaoImpl.chooseStudent(stu_no, p_no);
				//����֮ǰҳ��
				request.getRequestDispatcher("ChoicePracticeInfoServlet?nowPage="+pageUtils.getPageNow()).forward(request, response);
			} else if (type.equals("2")) {
				// ��ѡѧ��
				boolean b=projectDaoImpl.unChooseStudent(new String[]{stu_no}, p_no);
				//����֮ǰҳ��
				request.getRequestDispatcher("ChoicePracticeInfoServlet?nowPage="+pageUtils.getPageNow()).forward(request, response);
			} else {
				// ������Ч
				//��ת��404ҳ��,����ӡ������Ϣ
				String errorMessage = "����ʱ����ϵͳָ�������쳣��";
				request.getSession().setAttribute("ErrorMessage", errorMessage);
				response.sendRedirect(request.getContextPath() + "/404.jsp");
			}
		}else{
			//��ɫ��ݲ�ƥ��
			//��ת��404ҳ��,����ӡ������Ϣ
			String errorMessage = "��ǰ�û���Ȩ���ʣ�";
			request.getSession().setAttribute("ErrorMessage", errorMessage);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
		}
		
	}

	/**
	 * ����ѡ��ѧ��
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

