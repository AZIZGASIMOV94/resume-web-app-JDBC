/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.resumeapp.dao.inter;

import com.mycompany.bean.Skill;
import java.util.List;

/**
 *
 * @author azizg
 */
public interface SkillDaoInter {
    //get all skills READ
    public List<Skill> getAllSkills();
    
    
}
