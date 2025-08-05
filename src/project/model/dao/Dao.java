package project.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Dao {
    // DB연동 계정정보
    private String db_url = "jdbc:mysql://localhost:3306/본사";
    private String db_user = "root";
    private String db_password = "1234";
    public Connection conn;

    // 생성자
    public Dao(){
        connect();
    }

    // DB연동 함수
    public void connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection( db_url, db_user, db_password );
            //System.out.println("[안내] DB연동 성공!" );
        }catch( ClassNotFoundException e ){
            System.out.println("[경고] DB드라이버 연동 실패!" + e );
        }catch ( Exception e ){
            System.out.println("[경고] DB연동 실패!" + e );
        }//catch end
    }//func end
}// class end