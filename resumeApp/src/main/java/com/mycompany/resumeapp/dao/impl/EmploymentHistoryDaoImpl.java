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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author azizg
 */
public class EmploymentHistoryDaoImpl extends AbstractDAO implements EmploymentHistoryDaoInter{
    
     private EmploymentHistory getEmploymentHistory(ResultSet resultSet) throws Exception{
        int userId = resultSet.getInt("id");
        String header = resultSet.getString("header");
        String jobDesc = resultSet.getString("jobDescription");
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

}
