package project.model.dao; // 패키지명

import java.sql.Connection;
import java.sql.DriverManager;

public class Dao { // class start

    // da연동
    private String db_url = "jdbc:mysql://localhost:3306/본사";
    private String db_user = "root";
    private String db_password = "1234";

    public Connection conn;
    //생성자
    public Dao(){
        connect();
    }

    private void connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(db_url,db_user,db_password);
        } catch (Exception e) { System.out.println(e); }// try end
    }// func end
}// class end