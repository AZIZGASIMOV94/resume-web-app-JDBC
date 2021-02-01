package com.mycompany.resumeapp;

import com.mycompany.bean.User;
import com.mycompany.resumeapp.dao.inter.CountryDaoInter;
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
        //System.out.println(obj.getById(1));
        
        CountryDaoInter cDao = Context.instanceCountryDao();
        System.out.println(cDao.getAllCountry());
    }
}
