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
import java.util.List;

@WebServlet(name = "UserController", urlPatterns = {"/users"})
public class UserController extends HttpServlet {
    private UserDaoInter userDao =  Context.instanceUserDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name =  request.getParameter("name");
        String surname = request.getParameter("surname");
        String natStr = request.getParameter("nationality");
        Integer natId = null;
        if(natStr != null && !natStr.trim().isEmpty()){
            natId = Integer.parseInt(request.getParameter("nationality"));
        }
        List<User> userList = userDao.searchUsers(name, surname, natId);//get all
        request.setAttribute("userList",userList);
        request.getRequestDispatcher("users.jsp").forward(request,response);
    }
}
