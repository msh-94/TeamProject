package project.model.dao; // 패키지명

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Member_HeadDao { // class start
    // 싱글톤
    private Member_HeadDao(){connect();}
    private static final Member_HeadDao instance = new Member_HeadDao();
    public static Member_HeadDao getInstance(){ return instance; }

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

    //[본사] 회원가입
    public int signUp(int mno,int mCategory,String mId, String mPwd, String mName, String mPhone,String mDate){
        try {
            // 1. SQL 작성한다.
            String sql = "INSERT INTO Member_head(mCategory , mId , mPwd , mPhone , mName) VALUES(?,?,?,?,?)";
            // 2. SQL 기재한다..
            PreparedStatement ps = conn.prepareStatement(sql);
            // 3. SQL 매개변수 대입
            //ps.setInt(1,mno);
            ps.setInt(1,mCategory);
            ps.setString(2,mId);
            ps.setString(3,mPwd);
            ps.setString(4,mName);
            ps.setString(5,mPhone);
            // 4. SQL 실행  , insert/update/delete 은 .executeUpdate();
           int resultSignUp=ps.executeUpdate();
            // 5. sql 결과에 따른 로직/리턴/확인
            if(resultSignUp==0) return 1;
            else if(resultSignUp==1) return 2;
            else if(resultSignUp==2) return 3;
        }catch (Exception e){System.out.println(e);}//catch end
        return 4;
    }//signUp end
}// class end
