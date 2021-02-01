package com.mycompany.resumeapp.dao.impl;

import com.mycompany.bean.Skill;
import com.mycompany.resumeapp.dao.inter.AbstractDAO;
import com.mycompany.resumeapp.dao.inter.SkillDaoInter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author azizg
 */
public class SkillDaoImpl extends AbstractDAO implements SkillDaoInter{

    private Skill getSkill(ResultSet resultSet) throws Exception{
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        return new Skill(id,name);
    }

    @Override
    public List<Skill> getAllSkill() {
         List<Skill> skills = new ArrayList<Skill>();
        try( Connection con = dbConnect();) {//try with resource 
            Statement statement = con.createStatement();
            statement.execute("SELECT *FROM skill");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()){
               Skill country = getSkill(resultSet);
               skills.add(country);
            }
            //close connection
           // con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
        return skills;
    }
}
