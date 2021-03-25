package com.mycompany.resumeapp;

import com.mycompany.bean.User;
import com.mycompany.resumeapp.dao.inter.EmploymentHistoryDaoInter;
import com.mycompany.resumeapp.dao.inter.SkillDaoInter;
import com.mycompany.resumeapp.dao.inter.UserDaoInter;
import com.mycompany.resumeapp.dao.inter.UserSkillDaoInter;
/**
 *
 * @author azizg
 * CRUD example
 */
public class Main {

    public static void main(String[] args) throws Exception {
        //loosly coupling 
        //UserSkillDaoInter obj = Context.instanceUserSkillDao();
        UserDaoInter userDao = Context.instanceUserDao();
        System.out.println(userDao.getById(1));
    }
}
