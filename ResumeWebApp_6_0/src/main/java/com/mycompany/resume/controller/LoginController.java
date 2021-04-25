package com.mycompany.resume.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.mycompany.bean.User;
import com.mycompany.resumeapp.Context;
import com.mycompany.resumeapp.dao.inter.UserDaoInter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.mycompany.resume.util.*;

@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    private  static BCrypt.Verifyer verifyer = BCrypt.verifyer();
    private UserDaoInter userDao =  Context.instanceUserDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            User user = userDao.getUserByEmail(email);

            if(user == null){
                throw new IllegalArgumentException("user does not exist!");
            }



            BCrypt.Result rs= verifyer.verify(password.toCharArray(), user.getPassword().toCharArray());
            System.out.println("hash:"+ user.getPassword());
            System.out.println("password: "+password);
            if(!rs.verified){
                throw new IllegalArgumentException("password is incorrect!!!");
            }

            request.getSession().setAttribute("loggedInUser", user);
            response.sendRedirect("users");
        }catch (Exception ex){
            UtilController.errorPage(response,ex);
        }
    }
}
