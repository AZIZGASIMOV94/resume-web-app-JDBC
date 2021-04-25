package com.mycompany.resumeapp.dao.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.mycompany.bean.Country;
import com.mycompany.bean.User;
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

public class UserDaoImpl extends AbstractDAO implements UserDaoInter{
    
    private User getUser(ResultSet resultSet) throws Exception{
        int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String profileDesc = resultSet.getString("profile_desc");
                String address = resultSet.getString("address");
                Date birthdate = resultSet.getDate("birthdate");
                int birthplace = resultSet.getInt("birthplace_id");
                int nationality = resultSet.getInt("nationality_id");
                String birthplaceStr = resultSet.getString("birthplace");
                String nationalityStr = resultSet.getString("nationality");
                return new User(id,name,surname,email,phone,profileDesc,address,birthdate,
                                new Country(birthplace,birthplaceStr,null),
                                new Country(nationality,null,nationalityStr));
    }

    private User getLoggedInUser(ResultSet resultSet) throws Exception{
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String surname = resultSet.getString("surname");
        String email = resultSet.getString("email");
        String phone = resultSet.getString("phone");
        String password = resultSet.getString("user_password");
        String profileDesc = resultSet.getString("profile_desc");
        String address = resultSet.getString("address");
        Date birthdate = resultSet.getDate("birthdate");
        int birthplace = resultSet.getInt("birthplace_id");
        int nationality = resultSet.getInt("nationality_id");

        User user = new User(id,name,surname,email,phone,profileDesc,address,birthdate,null,null);
        user.setPassword(password);
        return user;
    }

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
    public List<User> searchUsers(String name, String surname, Integer nationalityId) {
        List<User> res = new ArrayList<>();
        try( Connection con = dbConnect();) {
            
            String sql = "select "
                     + "u.*, "
                     + "n.nationality as nationality, "
                     + "c.name as birthplace "
                     + "FROM user_table u "
                     + "LEFT join country_table n on u.nationality_id = n.id "
                     + "LEFT JOIN country_table c on u.birthplace_id = c.id where 1=1 ";
            if(name != null && !name.trim().isEmpty()){
                sql += " and u.name=? ";
            }
            if(surname != null && !surname.trim().isEmpty()){
                
                sql += " and u.surname=? ";
            }
            if(nationalityId != null ){
                sql += " and u.nationality_id=? ";
            }
            PreparedStatement pstmt = con.prepareStatement(sql);
            
            int i=1;
            if(name != null && !name.trim().isEmpty()){
                pstmt.setString(i, name);
                i++;
            }
            if(surname != null &&!surname.trim().isEmpty()){
                pstmt.setString(i, surname);
                i++;
            }
            if(nationalityId != null ){
                pstmt.setInt(i, nationalityId);
     
            }
            pstmt.execute();
            ResultSet resultSet = pstmt.getResultSet();
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
    public User getUserByEmailAndPass(String email, String password) {
        User result = null;
        try(Connection con =dbConnect()){
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM user_table where email=? and user_password=?");
            pstmt.setString(1,email);
            pstmt.setString(2,password);
            ResultSet rs =  pstmt.executeQuery();
            while (rs.next()){
                result = getLoggedInUser(rs);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public User getUserByEmail(String email){
        User result = null;
        try(Connection con =dbConnect()){
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM user_table where email=?");
            pstmt.setString(1,email);
            ResultSet rs =  pstmt.executeQuery();
            while (rs.next()){
                result = getLoggedInUser(rs);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
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
        try(Connection con = dbConnect()) {
            PreparedStatement statement = con.prepareStatement("UPDATE user_table SET name=?,surname=?,email=?, phone=?, profile_desc=?,address=?,birthdate=?, birthplace_id=?, nationality_id=? WHERE id=?");
            statement.setString(1, u.getName());
            statement.setString(2, u.getSurname());
            statement.setString(3, u.getEmail());
            statement.setString(4, u.getPhone());
            statement.setString(5, u.getProfileDesc());
            statement.setString(6, u.getAddress());
            statement.setString(7, u.getBirthDate().toString());
            Country country = u.getBirthplace();
            Country nationality = u.getNationality();
            statement.setInt(8, country.getId());
            statement.setInt(9, nationality.getId());
            statement.setInt(10, u.getId());
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean deleteUser(int id) {
        try( Connection con = dbConnect();) {
            Statement statement = con.createStatement();
            statement.executeUpdate("DELETE FROM user_table WHERE id="+id);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }


    private BCrypt.Hasher crypt = BCrypt.withDefaults();

    @Override
    public boolean addUser(User u) {
        try (Connection con = dbConnect()){
            PreparedStatement statement = con.prepareStatement("INSERT INTO user_table(name,surname,email,user_password,phone) VALUES(?,?,?,?,?)");
            statement.setString(1, u.getName());
            statement.setString(2, u.getSurname());
            statement.setString(3, u.getEmail());
            statement.setString(4, crypt.hashToString(4, u.getPassword().toCharArray()));
            statement.setString(5, u.getPhone());
            statement.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        User u2 = new User(23, "test2", "testov2","test2@mail.ru", "123456", "+994555955858");
        new UserDaoImpl().addUser(u2);
        System.out.println("added");
    }
}
