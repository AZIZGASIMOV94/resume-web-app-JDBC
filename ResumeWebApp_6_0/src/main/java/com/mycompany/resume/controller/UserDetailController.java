package com.mycompany.resume.controller;

import com.mycompany.bean.User;
import com.mycompany.resumeapp.Context;
import com.mycompany.resumeapp.dao.inter.UserDaoInter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UserDetailController", urlPatterns = {"/userdetail"})
public class UserDetailController extends HttpServlet {
    private UserDaoInter userDao =  Context.instanceUserDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.valueOf(request.getParameter("id"));
        String action = request.getParameter("action");
        if(action.equals("update")) {
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");

            User user = userDao.getById(id);
            user.setName(name);
            user.setSurname(surname);

            userDao.updateUser(user);
        }else if(action.equals("delete")){
            userDao.deleteUser(id);
        }
        response.sendRedirect("users");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String userIdStr = request.getParameter("id");
            if (userIdStr == null || userIdStr.trim().isEmpty()) {
                throw new IllegalArgumentException("id is not specified");
            }
            Integer uid = Integer.parseInt(request.getParameter("id"));
            UserDaoInter userDao = Context.instanceUserDao();
            User user = userDao.getById(uid);
            if (user == null) {
                throw new IllegalArgumentException("There is no user with this id");
            }
            request.setAttribute("owner",true);
            request.setAttribute("user", user);
            request.getRequestDispatcher("userdetail.jsp").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error?msg="+e.getMessage());
        }
    }
}
