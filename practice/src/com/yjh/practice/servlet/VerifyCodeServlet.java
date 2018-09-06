package com.yjh.practice.servlet;

import java.awt.image.BufferedImage;  
import java.io.IOException;  
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;

import com.yjh.practice.utils.VerifyCodeUtil;  
  
  

@WebServlet("/VerifyCodeServlet") 
public class VerifyCodeServlet extends HttpServlet {  
  
    private static final long serialVersionUID = 1L;  
  
    public void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
        VerifyCodeUtil vc = new VerifyCodeUtil();  
        BufferedImage image = vc.getImage(85,23);//������֤��ͼƬ��С  
        request.getSession().setAttribute("vchidden", vc.getText());//����֤���ı����浽session��  
        VerifyCodeUtil.output(image, response.getOutputStream());  
    }  
}  

