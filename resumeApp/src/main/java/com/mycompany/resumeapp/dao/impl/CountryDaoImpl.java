/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.resumeapp.dao.impl;

import com.mycompany.bean.Country;
import com.mycompany.resumeapp.dao.inter.AbstractDAO;
import com.mycompany.resumeapp.dao.inter.CountryDaoInter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author azizg
 */
public class CountryDaoImpl extends AbstractDAO implements CountryDaoInter   {
    
      private Country getCountry(ResultSet resultSet) throws Exception{
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String nationality = resultSet.getNString("nationality");
        return new Country(id, name, nationality);
    }

    @Override
    public List<Country> getAllCountry() {
        List<Country> countries = new ArrayList<Country>();
        try( Connection con = dbConnect();) {//try with resource 
            Statement statement = con.createStatement();
            statement.execute("SELECT *FROM country_table");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()){
               Country country = getCountry(resultSet);
               countries.add(country);
            }
            //close connection
           // con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
        return countries;
    }
    
}
