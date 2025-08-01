package project.model.dao; // 패키지명

import project.model.dto.Member_HeadDto;

import java.sql.*;

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

    // 로그인 정보 반환
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
}// class end
