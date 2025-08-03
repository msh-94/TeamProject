package project.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDB {
    // 싱글톤
    private ConnectDB(){}
    private static final ConnectDB instance = new ConnectDB();
    public static ConnectDB getInstance(){ return instance; }

    // DB연동 계정정보
    private String db_url = "jdbc:mysql://localhost:3306/본사";
    private String db_user = "root";
    private String db_password = "1234";
    private Connection conn; // DB 연동결과 조작 인터페이스(java.sql)

    // DB연동 함수
    public void connectDB(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection( db_url, db_user, db_password );
            System.out.println("[안내] DB연동 성공!" );
        }catch( ClassNotFoundException e ){
            System.out.println("[경고] DB드라이버 연동 실패!" + e );
        }catch ( Exception e ){
            System.out.println("[경고] DB연동 실패!" + e );
        }//catch end
    }//func end

}// class end
