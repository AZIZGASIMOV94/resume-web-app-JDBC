package com.mycompany.resumeapp.dao.impl;

import com.mycompany.bean.Skill;
import com.mycompany.bean.User;
import com.mycompany.bean.UserSkill;
import com.mycompany.resumeapp.dao.inter.AbstractDAO;
import com.mycompany.resumeapp.dao.inter.UserSkillDaoInter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author azizg
 */
public class UserSkillDaoImpl extends AbstractDAO implements UserSkillDaoInter{

    private UserSkill getUserSkill(ResultSet resultSet) throws Exception{
        int userId = resultSet.getInt("id");
        String userName = resultSet.getString("name");
        String userSurName = resultSet.getString("surname");
        int skillId = resultSet.getInt("skill_id");
        String skillName = resultSet.getString("skill_name");
        int skillLevel = resultSet.getInt("skill_level");
        return new UserSkill(null,new User(userId,userName,userSurName), new Skill(skillId,skillName), skillLevel);
    }
    
    @Override
    public List<UserSkill> getAllSkillByUserId(int userId) {
        List<UserSkill> uSkill = new ArrayList<UserSkill>();
     
        try( Connection con = dbConnect();) {
            PreparedStatement pstatement = con.prepareStatement("SELECT " +
                                        "	ut.*, " +
                                        "    usk.skill_id, " +
                                        "    sk.name as skill_name," +
                                        "    usk.skill_level as skill_level" +
                                        "    FROM user_table ut " +
                                        "    LEFT JOIN user_skill usk on ut.id = usk.user_id " +
                                        "    LEFT join skill sk on sk.id = usk.skill_id" +
                                        "    WHERE ut.id = ?");
            pstatement.setInt(1, userId);
            pstatement.execute();
            ResultSet resultSet = pstatement.getResultSet();
            while (resultSet.next()){
               UserSkill userSkill = getUserSkill(resultSet);
               uSkill.add(userSkill);
            }
            //close connection
           // con.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserSkillDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UserSkillDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uSkill;
    }

    
}
