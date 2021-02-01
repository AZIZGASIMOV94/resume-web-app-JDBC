package com.mycompany.resumeapp;

import com.mycompany.resumeapp.dao.inter.EmploymentHistoryDaoInter;
import com.mycompany.resumeapp.dao.inter.SkillDaoInter;
import com.mycompany.resumeapp.dao.inter.UserSkillDaoInter;
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
        UserSkillDaoInter obj = Context.instanceUserSkillDao();
        //System.out.println(obj.getById(1));
        
       /* EmploymentHistoryDaoInter empHistDao = Context.instanceEmploymentHistoryDao();
        System.out.println(empHistDao.getAllEmploymentHistoryByUserId(23));*/
        
        SkillDaoInter skills = Context.instanceSkillDao();
        System.out.println(skills.getAllSkill());
    }
}
