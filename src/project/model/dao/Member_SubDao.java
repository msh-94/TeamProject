package project.model.dao;// 패키지명

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;
import java.sql.PreparedStatement;
import static project.controller.CompanyController.currentCno;

public class Member_SubDao extends Dao { // class start
    // 싱글톤
    private Member_SubDao(){}
    private static final Member_SubDao instance = new Member_SubDao();
    public static Member_SubDao getInstance(){ return instance; }

    // 서브사이트 회원번호 전역변수
    public static int currentSubMno;

    // [구독회사] 회원가입 (구독회사 테이블 만들어지고 구현)
    public int signUp(int mno,int mCategory,String mId, String mPwd, String mName, String mPhone,String mDate){
        try {
            // 1. SQL 작성한다.
            String sql = "INSERT INTO Member_sub (cno,mCategory,mId,mPwd,mPhone,mName,mDate) VALUES(?,?,?,?,?,?,?)";
            // 2. SQL 기재한다..
            PreparedStatement ps = conn.prepareStatement(sql);
            // 3. SQL 매개변수 대입
            ps.setInt(1,currentCno);
            ps.setInt(2,mCategory);
            ps.setString(3,mId);
            ps.setString(4,mPwd);
            ps.setString(5,mName);
            ps.setString(6,mPhone);
            // 4. SQL 실행  , insert/update/delete 은 .executeUpdate();
            int resultSignUp=ps.executeUpdate();
            // 5. sql 결과에 따른 로직/리턴/확인
            if(resultSignUp==1) return 1;
            return 2;
        }catch (Exception e){System.out.println(e);}//catch end
        return 3;
    }//signUp end
}// class end
