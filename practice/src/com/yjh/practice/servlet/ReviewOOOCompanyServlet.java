package com.yjh.practice.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yjh.practice.model.Company;
import com.yjh.practice.service.CompanyService;
import com.yjh.practice.service.impl.CompanyServiceImpl;
import com.yjh.practice.utils.ValidateUtils;



/**
 * Servlet implementation class ReviewOOOCompanyServlet
 */
@WebServlet("/ReviewOOOCompanyServlet")
public class ReviewOOOCompanyServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
       
    
    public ReviewOOOCompanyServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//�õ������userName����
		String userName = request.getParameter("userName");
		System.out.println(userName+"123");
		//У��
		if (ValidateUtils.validate(userName) || userName == null) {
			System.out.println("�й��˲���");
			//��ת��404ҳ��,����ӡ������Ϣ
			String errorMessage = "����ʱ�����Ƿ��ַ����ܾ����ʣ�";
			request.getSession().setAttribute("ErrorMessage", errorMessage);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
			return ;
		}
		Company company = null ;
		CompanyService companyService = new CompanyServiceImpl();
		try {
			company = companyService.queryByUserName(userName);
			//�����û���ȡ����Company����Ϊ��
			if (company == null) {
				System.out.println("����Ϊ��");
				//��ת��404ҳ��,����ӡ������Ϣ
				String errorMessage = "�������ݿ�����쳣���޷���ȡָ�����ݣ�";
				request.getSession().setAttribute("ErrorMessage", errorMessage);
				response.sendRedirect(request.getContextPath() + "/404.jsp");
				return;
			}
			else {
				//���������ڲ�Ϊ�գ���ô��ζ������
				if (company.getAuditDate() != null) {
					company.setAuditDate(null);
					if (companyService.backReview(company)) {
						request.getRequestDispatcher("ShowAdminCompanyServlet").forward(request, response);
					}else {
						//��ת��404ҳ��,����ӡ������Ϣ
						String errorMessage = "�������ݿ�����쳣���޷��������";
						request.getSession().setAttribute("ErrorMessage", errorMessage);
						response.sendRedirect(request.getContextPath() + "/404.jsp");
						return ;
					}
				}
				else {
					System.out.println(company.getCompanyName());
					//����ת��
					java.util.Date date = new java.util.Date();
					java.sql.Date sqlDate=new java.sql.Date(date.getTime());
					company.setAuditDate(sqlDate);
					//�����˳ɹ�
					if (companyService.checkCompany(company)) {
						request.getRequestDispatcher("ShowAdminCompanyServlet").forward(request, response);
					}
					else {
						//��ת��404ҳ��,����ӡ������Ϣ
						String errorMessage = "�������ݿ�����쳣�����ʧ�ܣ�";
						request.getSession().setAttribute("ErrorMessage", errorMessage);
						response.sendRedirect(request.getContextPath() + "/404.jsp");
						return ;
					}
				}
			}
		}catch(Exception e) {
			response.sendRedirect("http://202.115.82.8:8080/404.jsp");
			//request.getRequestDispatcher("/404.html").forward(request, response);
		}
	}

}
