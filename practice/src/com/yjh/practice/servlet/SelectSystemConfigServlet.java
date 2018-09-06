package com.yjh.practice.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yjh.practice.model.SystemParameter;
import com.yjh.practice.service.SystemParameterService;
import com.yjh.practice.service.impl.SystemParameterServiceImpl;



/**
 * Servlet implementation class SelectSystemConfigServlet
 */
@WebServlet("/SelectSystemConfigServlet")
public class SelectSystemConfigServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SelectSystemConfigServlet() {
        super();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("����鿴ϵͳ���õ�Servlet");
    	HttpSession session = request.getSession();
		String user = (String) session.getAttribute("account");
		try {
			//�����ݿ��л�ȡSystemParameter���󲢴��session
			SystemParameterService systemParameterService = new SystemParameterServiceImpl();
			SystemParameter systemParameter = systemParameterService.queryByAccount(user);
			session.setAttribute("sys", systemParameter);
			request.getRequestDispatcher("/jsp/system-parameter.jsp").forward(request, response);
		}catch(Exception e) {
			System.out.println("�д�");
			//��ת��404ҳ��,����ӡ������Ϣ
			String errorMessage = "�������ݿ�����쳣��";
			request.getSession().setAttribute("ErrorMessage", errorMessage);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
			return;
		}
	}
}
