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

    //본사 회원가입
    public int signUp(int mno,int mCategory,String mId, String mPwd, String mName, String mPhone,String mDate){
        try {
            // 1. SQL 작성한다.
            String sql = "ISERT IN TO Member_head(mno,mCategory,mId,mPwd,mName,mPhone) values(?,?,?,?,?,?,?)";
            // 2. SQL 기재한다..
            PreparedStatement ps = conn.prepareStatement(sql);
            // 3. SQL 매개변수 대입
            ps.setInt(1,mno);
            ps.setInt(2,mCategory);
            ps.setString(3,mId);
            ps.setString(4,mPwd);
            ps.setString(5,mName);
            ps.setString(6,mPhone);
            ps.setString(7,mDate);
            // 4. SQL 실행  , insert/update/delete 은 .executeUpdate();
            int count = ps.executeUpdate();
            // 5. sql 결과에 따른 로직/리턴/확인
            return count;
        }catch (Exception e){System.out.println(e);}//catch end
        return 1;
    }//signUp end
}// class end
