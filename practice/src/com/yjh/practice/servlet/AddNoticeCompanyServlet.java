package com.yjh.practice.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yjh.practice.model.NoticeCompany;
import com.yjh.practice.service.NoticeService;
import com.yjh.practice.service.impl.NoticeServiceImpl;
import com.yjh.practice.utils.ValidateUtils;



@WebServlet("/AddNoticeCompanyServlet")
public class AddNoticeCompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddNoticeCompanyServlet() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		System.out.println(title + " " + content);
		if (ValidateUtils.validate(title) || ValidateUtils.validate(content)) {
			System.out.println("�п��ɲ���");
			//��ת��404ҳ��,����ӡ������Ϣ
			String errorMessage = "����ʱ�������ɲ������ܾ����ʣ�";
			request.getSession().setAttribute("ErrorMessage", errorMessage);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
			return;
		}
		NoticeService noticeService = new NoticeServiceImpl();
		NoticeCompany noticeCompany = new NoticeCompany();
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("account");
		// if (userName == null) {
		// userName = "sayHello";
		// }
		noticeCompany.setCompanyUsername(userName);
		noticeCompany.setTitle(title);
		noticeCompany.setContent(content);
		java.util.Date date = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		System.out.println(sqlDate);
		noticeCompany.setReleaseDate(sqlDate);
		try {
			noticeService.provideAnnouncement(noticeCompany);
			request.getRequestDispatcher("ShowNoticeListServlet").forward(request, response);
		} catch (Exception e) {
			//��ת��404ҳ��,����ӡ������Ϣ
			String errorMessage = "�������ݿ�����쳣��";
			request.getSession().setAttribute("ErrorMessage", errorMessage);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
		}
	}

}

