/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.resumeapp.dao.impl;

import com.mycompany.bean.Skill;
import com.mycompany.bean.User;
import com.mycompany.resumeapp.dao.inter.AbstractDAO;
import com.mycompany.resumeapp.dao.inter.SkillDaoInter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author azizg
 */
public class SkillDaoImpl extends AbstractDAO implements SkillDaoInter{
    
    private Skill getSkill(ResultSet resultSet) throws Exception{
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        return new Skill(id, name);
    }

    @Override
    public List<Skill> getAllSkills() {
        List<Skill> skills = new ArrayList<Skill>();
         try( Connection con = dbConnect();) {//try with resource 
            Statement statement = con.createStatement();
            statement.execute("SELECT *FROM skill");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()){
               Skill skill = getSkill(resultSet);
               skills.add(skill);
            }
            //close connection
           // con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
        return skills;
    }
    
    
    
}
