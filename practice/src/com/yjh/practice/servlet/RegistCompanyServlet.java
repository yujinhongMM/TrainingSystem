package com.yjh.practice.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yjh.practice.service.CompanyService;
import com.yjh.practice.service.impl.CompanyServiceImpl;



@WebServlet("/RegistCompanyServlet")
public class RegistCompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegistCompanyServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//POST����
		request.setCharacterEncoding("UTF-8");
		String qyname = request.getParameter("qyname");
		String qyusername = request.getParameter("qyusername");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String yzm = request.getParameter("yzm");
		
		// ҳ���õ��ɺ�̨��������֤��
		String vchidden =(String) request.getSession().getAttribute("vchidden");
		
		
		System.out.println("����Servlet");
		try {
			CompanyService companyService = new CompanyServiceImpl();
			if (companyService.registerCompanyInfo(qyusername, qyname, email, password, yzm,vchidden)) {
				response.sendRedirect("/practice/jsp/login.jsp");
				return;
			}else {
				//��ת��404ҳ��,����ӡ������Ϣ
				String errorMessage = "�������ݿ�����쳣��";
				request.getSession().setAttribute("ErrorMessage", errorMessage);
				response.sendRedirect(request.getContextPath() + "/404.jsp");
				return ;
			}
		} catch(Exception e) {
			//��ת��404ҳ��,����ӡ������Ϣ
			String errorMessage = "�������ݿ�����쳣��";
			request.getSession().setAttribute("ErrorMessage", errorMessage);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
			return ;
		}
	}

}
