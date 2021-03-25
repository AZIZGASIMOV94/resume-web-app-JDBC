package com.mycompany.resumeapp.dao.impl;

import com.mycompany.bean.EmploymentHistory;
import com.mycompany.bean.User;
import com.mycompany.resumeapp.dao.inter.AbstractDAO;
import com.mycompany.resumeapp.dao.inter.EmploymentHistoryDaoInter;
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

public class EmploymentHistoryDaoImpl extends AbstractDAO implements EmploymentHistoryDaoInter{
    
    private EmploymentHistory getEmploymentHistory(ResultSet resultSet) throws Exception{
        int userId = resultSet.getInt("id");
        String header = resultSet.getString("header");
        String jobDesc = resultSet.getString("job_description");
        Date beginDate = resultSet.getDate("begin_date");
        Date endDate = resultSet.getDate("end_date");
        return new EmploymentHistory(null, header, beginDate, endDate, jobDesc, new User(userId));
    }
    
    @Override
    public List<EmploymentHistory> getAllEmploymentHistoryByUserId(int userId) {
        List<EmploymentHistory> empHistoryList = new ArrayList<EmploymentHistory>();
     
        try( Connection con = dbConnect();) {
            PreparedStatement pstatement = con.prepareStatement("SELECT *FROM employment_history where user_id=?");
            pstatement.setInt(1, userId);
            pstatement.execute();
            ResultSet resultSet = pstatement.getResultSet();
            while (resultSet.next()){
               EmploymentHistory empHistory = getEmploymentHistory(resultSet);
               empHistoryList.add(empHistory);
            }
            //close connection
           // con.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserSkillDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UserSkillDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return empHistoryList;
    }

    @Override
    public boolean updateEmploymentHistory(EmploymentHistory emphistory) {
        try(Connection con = dbConnect();) {
            PreparedStatement statement = con.prepareStatement("UPDATE employment_history SET header=?,job_description=?,begin_date=?,end_date=? WHERE id=?");
            statement.setString(1, emphistory.getHeader());
            statement.setString(2, emphistory.getJobDescription());
            statement.setString(3, emphistory.getBeginDate().toString());
            statement.setString(3, emphistory.getEndDate().toString());
            statement.setInt(4, emphistory.getId());
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }
    //not finished
    @Override
    public boolean addEmploymentHistory(EmploymentHistory emphistory) {
        try (Connection con = dbConnect()){
            PreparedStatement statement = con.prepareStatement("INSERT INTO employment_history(header,job_description,begin_date,end_date)VALUES(?,?,?,?)");
            statement.setString(1, emphistory.getHeader());
            statement.setString(2, emphistory.getJobDescription());
            statement.setString(3, emphistory.getBeginDate().toString());
            statement.setString(4, emphistory.getEndDate().toString());
            statement.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteEmploymentHistory(int id) {
         try( Connection con = dbConnect();) {
            Statement statement = con.createStatement();
            statement.executeUpdate("DELETE FROM employment_history WHERE id="+id);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }

}
