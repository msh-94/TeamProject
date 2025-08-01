package project.model.dao;// 패키지명

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;
import java.sql.PreparedStatement;

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




    // 로그아웃 기능 구현

    public void signOut(){
        while(true){
            try{
                int input = new Scanner(System.in).nextInt();
                if(input == 2 ){
                    System.out.println("[안내] 로그아웃 되었습니다.");
                    break;
                }
            }catch (Exception e){
                System.out.println("[오류]관리자 문의(000-0000)");
            }
        }
    }

    // [구독회사] 회원가입 (구독회사 테이블 만들어지고 구현)
    public int signUp(int mno,int mCategory,String mId, String mPwd, String mName, String mPhone,String mDate){
        try {
            // 1. SQL 작성한다.
            String sql = "INSERT INTO Member_sub (cno,mCategory,mId,mPwd,mPhone,mName,mDate) VALUES(?,?,?,?,?,?)";
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
        return 3;
    }//signUp end
}// class end
