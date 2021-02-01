/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.resumeapp.dao.inter;

import com.mycompany.bean.Country;
import com.mycompany.bean.EmploymentHistory;
import java.util.List;

/**
 *
 * @author azizg
 */
public interface EmploymentHistoryDaoInter {
    public List<EmploymentHistory> getAllEmploymentHistoryByUserId(int userId);
}
