package com.mycompany.resume.controller;

import com.mycompany.bean.User;
import com.mycompany.resumeapp.Context;
import com.mycompany.resumeapp.dao.inter.UserDaoInter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ErrorController", urlPatterns = {"/error"})
public class ErrorController extends HttpServlet {
    private UserDaoInter userDao =  Context.instanceUserDao();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("error.jsp").forward(request,response);
    }
}
