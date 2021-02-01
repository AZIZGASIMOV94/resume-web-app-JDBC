package com.mycompany.resumeapp.dao.impl;

import com.mycompany.bean.Country;
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
                Date birthdate = resultSet.getDate("birthdate");
                int birthplace = resultSet.getInt("birthplace_id");
                int nationality = resultSet.getInt("nationality_id");
                String birthplaceStr = resultSet.getString("birthplace");
                String nationalityStr = resultSet.getString("nationality");
                return new User(id,name,surname,email,phone,birthdate,new Country(birthplace,birthplaceStr,null),new Country(nationality,nationalityStr,null));
    }
    
    @Override
    public List<User> getAll() {
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
            PreparedStatement statement = con.prepareStatement("UPDATE user_table SET name=?,surname=?,email=?, phone=? WHERE id=?");
            statement.setString(1, u.getName());
            statement.setString(2, u.getSurname());
            statement.setString(3, u.getEmail());
            statement.setString(4, u.getPhone());
            statement.setInt(5, u.getId());
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
            PreparedStatement statement = con.prepareStatement("INSERT INTO user_table(name,surname,email,phone) VALUES(?,?,?,?)");
            statement.setString(1, u.getName());
            statement.setString(2, u.getSurname());
            statement.setString(3, u.getEmail());
            statement.setString(4, u.getPhone());
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
        
        return uSkill;
    }
    
}
