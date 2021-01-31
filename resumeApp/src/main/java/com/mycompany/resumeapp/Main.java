package com.mycompany.resumeapp;

import com.mycompany.bean.User;
import com.mycompany.resumeapp.dao.inter.UserDaoInter;
import lombok.SneakyThrows;
/**
 *
 * @author azizg
 * CRUD example
 */
public class Main {
    @SneakyThrows
    public static void main(String[] args)  {
        //loosly coupling 
        //add user created 
        UserDaoInter obj = Context.instanceUserDao();
        User u = new User("tural","genceli","tural@gmail.com","+994553555555");
        obj.addUser(u);
        System.out.println(obj.getAll());;
    }
}
