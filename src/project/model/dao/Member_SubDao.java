package project.model.dao;// 패키지명

import java.sql.Connection;
import java.sql.DriverManager;

public class Member_SubDao { // class start
    // 싱글톤
    private Member_SubDao(){connect();}
    private static final Member_SubDao instance = new Member_SubDao();
    public static Member_SubDao getInstance(){ return instance; }

    // db 연동
    private String db_url = "jdbc:mysql://localhost:3306/본사";
    private String db_user = "root";
    private String db_password = "1234";
    private Connection conn;
    private void connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(db_url,db_user,db_password);
        } catch (Exception e) { System.out.println(e); }// try end
    }// func end
}// class end
