package com.yjh.practice.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yjh.practice.model.Student;
import com.yjh.practice.service.StudentService;
import com.yjh.practice.service.impl.StudentServiceImpl;
import com.yjh.practice.utils.MdPwdUtil;



@WebServlet("/UpdateStudentServlet")
public class UpdateStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); //����POST�������
		response.setContentType("text/html;charset=UTF-8"); //������Ӧ��������
		
		HttpSession session = request.getSession();
		String account=(String)session.getAttribute("account");
		Boolean hasChange=false;
		
		
		//���ѧ�Ʊ���
		String background = request.getParameter("background");
		//���ѧϰ����
		String experience = request.getParameter("experience");
		//����о�����
		String direction = request.getParameter("direction");
		//�������ľ�����
		String oldpwd = request.getParameter("oldpwd");
		//��������������
		String newpwd = request.getParameter("newpwd");

		
		
		Student stu=null;
		StudentService ss=new StudentServiceImpl();
		stu=ss.findById(account);
		
		if(stu!=null){			
		
			if(background!=null){ 
				stu.setSubjectBackground(background);
				stu.setLearningExperience(experience);
				stu.setResearchDirection(direction);
				hasChange=true;
			}
			if(hasChange) //������޸ģ��͸������ݿ�
				ss.update(stu);
		}
		//�޸�����
		if(oldpwd!=null && newpwd!=null){
			if(oldpwd.trim()!=""){ 
				System.out.println("old:"+oldpwd);
				oldpwd=MdPwdUtil.MD5Password(oldpwd);//MD5����
				if(oldpwd.equals(stu.getPassword())){
					System.out.println("oldMd5:"+oldpwd);
					stu.setPassword(MdPwdUtil.MD5Password(newpwd));
					ss.update(stu);
					PrintWriter out = response.getWriter();
					out.print("<script> alert(\"�޸�����ɹ�!\"); </script>");
				}else{
					response.getWriter().print("<script> alert('���������벻��ȷ��');</script>");
				}
			}
		}
		request.setAttribute("student", stu);
		request.getRequestDispatcher("/jsp/studentInfo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
