package com.yjh.practice.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.yjh.practice.service.NoticeService;
import com.yjh.practice.service.impl.NoticeServiceImpl;
import com.yjh.practice.utils.ValidateUtils;


@WebServlet("/ReviewNoticeServlet")
public class ReviewNoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ReviewNoticeServlet() {
        super();
    }
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//post���뷽ʽ
		request.setCharacterEncoding("UTF-8");
		String Id = request.getParameter("Id");
		//����У�飬����зǷ��ַ�����ô����ת��404
		if (ValidateUtils.validate(Id) || Id == null) {
			//��ת��404ҳ��,����ӡ������Ϣ
			String errorMessage = "����ʱ�����Ƿ��ַ����ܾ����ʣ�";
			request.getSession().setAttribute("ErrorMessage", errorMessage);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
			return ;
		}
		try {
			Integer companyNoticeId = Integer.parseInt(Id);
			NoticeService noticeService = new NoticeServiceImpl();
			java.util.Date date = new java.util.Date();
			java.sql.Date sqlDate=new java.sql.Date(date.getTime());
			if (noticeService.reviewCompanyNotice(companyNoticeId, sqlDate)) {
				request.getRequestDispatcher("AdminLookNoticesServlet").forward(request, response);	
			}
			else {
				//��ת��404ҳ��,����ӡ������Ϣ
				String errorMessage = "�������ݿ�����쳣�������޷����";
				request.getSession().setAttribute("ErrorMessage", errorMessage);
				response.sendRedirect(request.getContextPath() + "/404.jsp");
			}
		}catch(Exception e) {
			//��ת��404ҳ��,����ӡ������Ϣ
			String errorMessage = "�������ݿ�����쳣��";
			request.getSession().setAttribute("ErrorMessage", errorMessage);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
		}
	}

}
