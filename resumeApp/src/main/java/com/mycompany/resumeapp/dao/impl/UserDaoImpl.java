package com.mycompany.resumeapp.dao.impl;

import com.mycompany.bean.User;
import com.mycompany.resumeapp.dao.inter.AbstractDAO;
import com.mycompany.resumeapp.dao.inter.UserDaoInter;
import java.sql.Connection;
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

    @Override
    public List<User> getAll() {
        List<User> res = new ArrayList<>();
        try( Connection con = dbConnect();) {
            Statement statement = con.createStatement();
            statement.execute("SELECT * FROM user_table");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String st = resultSet.getString("name");
                String st1 = resultSet.getString("surname");
                String st2 = resultSet.getString("email");
                String st3 = resultSet.getString("phone");
                res.add(new User(id, st,st1,st2,st3));
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
    
    public User getById(int userId){
        User u = null;
        try(Connection con = dbConnect();) {
           Statement stmt = con.createStatement();
           stmt.execute("SELECT *FROM user_table WHERE id="+userId);
           ResultSet rs = stmt.getResultSet();
           while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                u = new User(id,name,surname,email,phone);
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
    
}
