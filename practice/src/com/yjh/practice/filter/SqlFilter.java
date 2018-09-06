package com.yjh.practice.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yjh.practice.utils.ValidateUtils;



/**
 * 
 * Description ��ֹsqlע��
 * @author YJH
 * @date 2018��6��2��  
 *
 */
//������������
@WebFilter(filterName="SqlFilter",urlPatterns={"/*"})
public class SqlFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		//����������������  
		Enumeration<String> params = req.getParameterNames();  
		String sql = "";  
		
		while (params.hasMoreElements()) {  
			//�õ�������  
			String name = params.nextElement().toString();  
			//�õ�������Ӧֵ  
			String[] value = req.getParameterValues(name);  
			for (int i = 0; i < value.length; i++) {  
				sql = sql + value[i];  
			}  
		}  
		//���÷�sqlע��ķ���
		if (ValidateUtils.validate(sql)) {  
			//��ת��404ҳ��,����ӡ������Ϣ
			String errorMessage = "���󸽴��Ƿ��ַ����ܾ����ʣ�";
			((HttpServletRequest) request).getSession().setAttribute("ErrorMessage", errorMessage);
			((HttpServletResponse) response).sendRedirect(((HttpServletRequest) request).getContextPath() + "/404.jsp");
		}else {  
			chain.doFilter(req, res);  
		}  
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
}
