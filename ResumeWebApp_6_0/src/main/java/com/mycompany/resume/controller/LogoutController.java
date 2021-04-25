package com.mycompany.resume.controller;

import com.mycompany.bean.User;
import com.mycompany.resume.util.UtilController;
import com.mycompany.resumeapp.Context;
import com.mycompany.resumeapp.dao.inter.UserDaoInter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LogoutController", urlPatterns = {"/logout"})
public class LogoutController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       request.getSession().invalidate();
       response.sendRedirect("login");
    }
}
