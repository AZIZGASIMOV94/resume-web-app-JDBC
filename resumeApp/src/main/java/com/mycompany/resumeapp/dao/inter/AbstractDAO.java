package com.mycompany.resumeapp.dao.inter;

import java.sql.Connection;
import java.sql.DriverManager;


public abstract class AbstractDAO { 

    public Connection dbConnect() throws Exception{
        //in new versions there is not need for this method
        //Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/resume_db";
        String username = "root";
        String password = "";
        Connection con = DriverManager.getConnection(url,username, password);
        return con;
    }
}
