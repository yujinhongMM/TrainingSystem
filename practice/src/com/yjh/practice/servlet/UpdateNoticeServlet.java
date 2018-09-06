package com.yjh.practice.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yjh.practice.model.NoticeCompany;
import com.yjh.practice.service.NoticeService;
import com.yjh.practice.service.impl.NoticeServiceImpl;
import com.yjh.practice.utils.ValidateUtils;



@WebServlet("/UpdateNoticeServlet")
public class UpdateNoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateNoticeServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//post��ʽ����
		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String id = request.getParameter("id");
		System.out.println(title+" "+content);
		if (ValidateUtils.validate(title) || ValidateUtils.validate(content)
				|| ValidateUtils.validate(id)) {
			System.out.println("�п��ɲ���");
			//��ת��404ҳ��,����ӡ������Ϣ
			String errorMessage = "���󸽴������ַ������ʱ��ܾ���";
			request.getSession().setAttribute("ErrorMessage", errorMessage);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
			return ;
		}
		NoticeService noticeService = new NoticeServiceImpl();
		try {
			//����Idȡ��֪ͨ�����ٽ��и�ֵ
			Integer companyNoticeId = Integer.parseInt(id);
			NoticeCompany noticeCompany = noticeService.queryNoticeById(companyNoticeId);
			java.util.Date date = new java.util.Date();
			java.sql.Date sqlDate=new java.sql.Date(date.getTime());
			//ֻ�ø�������
			noticeCompany.setTitle(title);
			noticeCompany.setContent(content);
			noticeCompany.setReleaseDate(sqlDate);
			//����֮������ù���Ա�������
			noticeCompany.setAuditDate(null);
			noticeService.updateCompanyNotice(noticeCompany);
			request.getRequestDispatcher("/ShowNoticeListServlet").forward(request, response);
		}catch(Exception e) {
			//��ת��404ҳ��,����ӡ������Ϣ
			String errorMessage = "�������ݿ�����쳣��";
			request.getSession().setAttribute("ErrorMessage", errorMessage);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
			return ;
		}
		
	}

}

