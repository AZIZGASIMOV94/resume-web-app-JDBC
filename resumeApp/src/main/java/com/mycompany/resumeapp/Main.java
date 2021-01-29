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
        UserDaoInter obj = Context.instanceUserDao();
        User u = obj.getById(2);
        u.setName("aziz");
        obj.updateUser(u);
        System.out.println(obj.getById(2));
 
    }
}
