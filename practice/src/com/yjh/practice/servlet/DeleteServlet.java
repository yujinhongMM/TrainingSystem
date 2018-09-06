package com.yjh.practice.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yjh.practice.service.CompanyService;
import com.yjh.practice.service.impl.CompanyServiceImpl;
import com.yjh.practice.utils.ValidateUtils;

/**
 * 
 * Description
 * @author YJH
 * @date 2018��6��5��  
 *
 */

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public DeleteServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//�õ������userName����
		String userName = request.getParameter("userName");
		//У��
		if (ValidateUtils.validate(userName) || userName == null || "".equals(userName)) {
				System.out.println("�й��˲���");
				//��ת��404ҳ��,����ӡ������Ϣ
				String errorMessage = "����ʱ�����Ƿ��ַ����ܾ����ʣ�";
				request.getSession().setAttribute("ErrorMessage", errorMessage);
				response.sendRedirect(request.getContextPath() + "/404.jsp");
				return ;
		}
		else {
			CompanyService companyService = new CompanyServiceImpl();
			try {
				
					if (companyService.deleteCompany(userName)) {
						request.getRequestDispatcher("ShowAdminCompanyServlet").forward(request, response);
					}
					else {
						//��ת��404ҳ��,����ӡ������Ϣ
						String errorMessage = "�������ݿ�����쳣��";
						request.getSession().setAttribute("ErrorMessage", errorMessage);
						response.sendRedirect(request.getContextPath() + "/404.jsp");
						return ;
					}
			}catch(Exception e) {
				//��ת��404ҳ��,����ӡ������Ϣ
				String errorMessage = "�������ݿ�����쳣��";
				request.getSession().setAttribute("ErrorMessage", errorMessage);
				response.sendRedirect(request.getContextPath() + "/404.jsp");
				return ;
			}
		}
		
	}

}
