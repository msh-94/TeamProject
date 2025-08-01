package project.model.dao; // 패키지명

import project.model.dto.Member_HeadDto;

import java.sql.*;
import java.util.ArrayList;
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

    // 로그인 회원 번호,아이디,비밀번호 반환 기능
    public Member_HeadDto logIn(String mId , String mPwd){
        Member_HeadDto dto = new Member_HeadDto();
        try {
            String sql = "select * from Member_Head where mId = ? and mPwd = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,mId);
            ps.setString(2,mPwd);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                dto.setMno(rs.getInt("mno"));
                dto.setmId(rs.getNString("mId"));
                dto.setmPwd(rs.getNString("mPwd"));
            }// while end
        } catch (SQLException e) { System.out.println(e); }
        return dto;
    }// func end

    // 회원탈퇴 기능
    public boolean withdrawUser(int mno){
        try{
            String sql = "delete from Member_head where mno = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,mno);
            int count = ps.executeUpdate();
            if (count == 1) return true;
        } catch (SQLException e) { System.out.println(e); } // try end
        return false;
    }// func end

    // 회원정보 수정 기능
    public boolean updateProfile(Member_HeadDto dto){
        try{
            String sql = "update Member_head set mPwd = ? , mPhone = ? where mno = ? and mPwd = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,dto.getmPwd());
            ps.setString(2,dto.getmPhone());
            ps.setInt(3,dto.getMno());
            ps.setString(4,dto.getmPwd());
            int count = ps.executeUpdate();
            if (count == 1)return true;
        } catch (SQLException e) { System.out.println(e); }
        return false;
    }// func end

    //// 구독자 조회 기능
    //public ArrayList<>
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
