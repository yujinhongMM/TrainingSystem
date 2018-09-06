package com.yjh.practice.servlet;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yjh.practice.model.NoticeAdmin;
import com.yjh.practice.model.NoticeCompany;
import com.yjh.practice.service.NoticeService;
import com.yjh.practice.service.impl.NoticeServiceImpl;
import com.yjh.practice.utils.PageUtils;


/**
 * Servlet implementation class ShowNoticeListServlet
 */
@WebServlet("/MoreIndexNoticesListServlet")
public class MoreIndexNoticeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MoreIndexNoticeListServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("���������б�Servlet");
		String tole = request.getParameter("tole");
		NoticeService noticeService = new NoticeServiceImpl();
		String pageNows = request.getParameter("pageNow");
		if (pageNows == null) {
			pageNows = "1";
		}
		int pageNow = Integer.parseInt(pageNows);
		int pageSize = 15;
		HttpSession session = request.getSession();
		try {
			if (tole != null && !"".equals(tole)) {
				PageUtils pageUtils = null;
				//ѧԺ֪ͨ����
				if ("1".equals(tole)) {
					List<NoticeAdmin> list = noticeService.queryAllAdminNoticeOrderByDate(pageNow, pageSize);
					int count = noticeService.countAdminNotice();
					pageUtils = new PageUtils(pageNow, count);
					pageUtils.setPageSize(pageSize);
					session.setAttribute("allList", list);
				}
				else {
					List<NoticeCompany> list = noticeService.queryAllCompanyNoticeOrderByDate(pageNow, pageSize);
					int count = noticeService.countAllAuditCompanyNotice();
					pageUtils = new PageUtils(pageNow, count);
					pageUtils.setPageSize(pageSize);
					session.setAttribute("allList", list);
				}
				session.setAttribute("pager", pageUtils);
				session.setAttribute("tole", tole);
				request.getRequestDispatcher("new-lists.jsp").forward(request, response);
				return ;
			}
			else {
				//��ת��404ҳ��,����ӡ������Ϣ
				String errorMessage = "�����쳣��";
				request.getSession().setAttribute("ErrorMessage", errorMessage);
				response.sendRedirect(request.getContextPath() + "/404.jsp");
				return ;
			}
			
		}catch(Exception e) {
			//��ת��404ҳ��,����ӡ������Ϣ
			String errorMessage = "�������ݿ�����쳣��";
			request.getSession().setAttribute("ErrorMessage", errorMessage);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
		}
	}

}

