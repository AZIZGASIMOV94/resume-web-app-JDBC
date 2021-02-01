/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.resumeapp;

import com.mycompany.resumeapp.dao.impl.CountryDaoImpl;
import com.mycompany.resumeapp.dao.impl.EmploymentHistoryDaoImpl;
import com.mycompany.resumeapp.dao.impl.SkillDaoImpl;
import com.mycompany.resumeapp.dao.impl.UserDaoImpl;
import com.mycompany.resumeapp.dao.impl.UserSkillDaoImpl;
import com.mycompany.resumeapp.dao.inter.CountryDaoInter;
import com.mycompany.resumeapp.dao.inter.EmploymentHistoryDaoInter;
import com.mycompany.resumeapp.dao.inter.SkillDaoInter;
import com.mycompany.resumeapp.dao.inter.UserDaoInter;
import com.mycompany.resumeapp.dao.inter.UserSkillDaoInter;

/**
 *
 * @author azizg
 */
public class Context {
    public static UserDaoInter instanceUserDao(){
        return new UserDaoImpl();
    }
    
    public static CountryDaoInter instanceCountryDao(){
        return new CountryDaoImpl();
    }
    
    public static UserSkillDaoInter instanceUserSkillDao(){
        return new UserSkillDaoImpl();
    }
    
    public static EmploymentHistoryDaoInter instanceEmploymentHistoryDao(){
        return new EmploymentHistoryDaoImpl();
    }
     
    public static SkillDaoInter instanceSkillDao(){
        return new SkillDaoImpl();
    }
}
