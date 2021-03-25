package com.mycompany.resumeapp.dao.impl;

import com.mycompany.bean.Country;
import com.mycompany.bean.Skill;
import com.mycompany.bean.User;
import com.mycompany.bean.UserSkill;
import com.mycompany.resumeapp.dao.inter.AbstractDAO;
import com.mycompany.resumeapp.dao.inter.UserDaoInter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * profile_desc
 *
 * @author azizg
 */
public class UserDaoImpl extends AbstractDAO implements UserDaoInter{
    
    private User getUser(ResultSet resultSet) throws Exception{
        int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String profileDesc = resultSet.getString("profile_desc");
                Date birthdate = resultSet.getDate("birthdate");
                int birthplace = resultSet.getInt("birthplace_id");
                int nationality = resultSet.getInt("nationality_id");
                String birthplaceStr = resultSet.getString("birthplace");
                String nationalityStr = resultSet.getString("nationality");
                return new User(id,name,surname,email,phone,profileDesc,birthdate,
                                new Country(birthplace,birthplaceStr,null),
                                new Country(nationality,null,nationalityStr));// new Country(birthplace,birthplaceStr,null),
    }
    
    private UserSkill getUserSkill(ResultSet resultSet) throws Exception{
        int userId = resultSet.getInt("id");
        int skillId = resultSet.getInt("skill_id");
        String skillName = resultSet.getString("skill_name");
        int skillLevel = resultSet.getInt("skill_level");
        return new UserSkill(null,new User(userId), new Skill(skillId,skillName), skillLevel);
    }
    //getAll()
    @Override
    public List<User> getAllUsers() {
        List<User> res = new ArrayList<>();
        try( Connection con = dbConnect();) {
            Statement statement = con.createStatement();
            statement.execute("select "
                                + "u.*, "
                                + "n.nationality as nationality, "
                                + "c.name as birthplace "
                                + "FROM user_table u "
                                + "LEFT join country_table n on u.nationality_id = n.id "
                                + "LEFT JOIN country_table c on u.birthplace_id = c.id");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()){
               User u = getUser(resultSet);
               res.add(u);
            }
            //close connection
           // con.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    } 
    
    @Override
    public User getById(int userId){
        User u = null;
        try(Connection con = dbConnect();) {
           Statement stmt = con.createStatement();
           stmt.execute("select "
                            + "u.*, "
                            + "n.nationality as nationality, "
                            + "c.name as birthplace "
                            + "FROM user_table u "
                            + "LEFT join country_table n on u.nationality_id = n.id "
                            + "LEFT JOIN country_table c on u.birthplace_id = c.id where u.id="+userId);
           ResultSet resultSet = stmt.getResultSet();
           while(resultSet.next()){
                u = getUser(resultSet);
           }
         
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return u;
    }
    
    @Override
    public boolean updateUser(User u) {
        try(Connection con = dbConnect();) {
            PreparedStatement statement = con.prepareStatement("UPDATE user_table SET name=?,surname=?,email=?, phone=?, profile_desc=? WHERE id=?");
            statement.setString(1, u.getName());
            statement.setString(2, u.getSurname());
            statement.setString(3, u.getEmail());
            statement.setString(4, u.getPhone());
            statement.setString(5, u.getProfileDesc());
            statement.setInt(6, u.getId());
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (Exception ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    public boolean deleteUser(int id) {
        try( Connection con = dbConnect();) {
            Statement statement = con.createStatement();
            statement.executeUpdate("DELETE FROM user_table WHERE id=1");
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (Exception ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    public boolean addUser(User u) {
        try (Connection con = dbConnect()){
            PreparedStatement statement = con.prepareStatement("INSERT INTO user_table(name,surname,email,phone,profile_desc) VALUES(?,?,?,?,?)");
            statement.setString(1, u.getName());
            statement.setString(2, u.getSurname());
            statement.setString(3, u.getEmail());
            statement.setString(4, u.getPhone());
            statement.setString(5, u.getProfileDesc());
            statement.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
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
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uSkill;
    }
    
}
