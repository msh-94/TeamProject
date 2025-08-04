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

    //회원가입
    public int signUp(int mno,int mCategory,String mId, String mPwd, String mName, String mPhone,String mDate){
        try {
            String sql = "INSERT INTO Member_head(mCategory , mId , mPwd , mPhone , mName) VALUES(?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,mCategory);
            ps.setString(2,mId);
            ps.setString(3,mPwd);
            ps.setString(4,mName);
            ps.setString(5,mPhone);
            int result=ps.executeUpdate();

            if (result == 1){return 1;} //반환값
            return 2;
        }catch (Exception e){System.out.println("[경고] 이미 등록된 아이디입니다. ");}//catch end
        return 0;
    }//func end

    //회원목록 조회
    public ArrayList<Member_HeadDto> userList(){
        ArrayList<Member_HeadDto> member_headDto = new ArrayList<>();
        try {
            String sql = "SELECT *FROM Member_head;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs =  ps.executeQuery();
            while(rs.next()) {
                int mno = rs.getInt("mno");
                int mCategory = rs.getInt("mCategory");
                String mId = rs.getString("mId");
                String mName = rs.getString("mName");
                String mPhone = rs.getString("mPhone");
                String mDate = rs.getString("mDate");

                Member_HeadDto memberHeadDto = new Member_HeadDto(mno,mCategory,mId,mName,mPhone,mDate,mDate);
                member_headDto.add(memberHeadDto);

            }//while end
        } catch (Exception e) {System.out.println(e);}
        return member_headDto;
    }//func end

}// class end
