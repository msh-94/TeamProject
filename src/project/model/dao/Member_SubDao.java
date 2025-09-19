package project.model.dao;// 패키지명

import project.model.dto.Member_SubDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.sql.PreparedStatement;
import static project.controller.CompanyController.currentCno;

public class Member_SubDao extends Dao { // class start
    //// 싱글톤
    //private Member_SubDao(){}
    //private static final Member_SubDao instance = new Member_SubDao();
    //public static Member_SubDao getInstance(){ return instance; }


    // [구독회사] 회원가입 (구독회사 테이블 만들어지고 구현)
    public int subSignUp(int mCategory,String mId, String mPwd, String mName, String mPhone){
        try {
            // 1. SQL 작성한다.
            String sql = "INSERT INTO Member_sub (cno,mCategory,mId,mPwd,mName,mPhone) VALUES(?,?,?,?,?,?)";
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
            int resultSignUp = ps.executeUpdate();
            // 5. sql 결과에 따른 로직/리턴/확인
            if(resultSignUp==1) return 1;
            return 2;
        }catch (Exception e){System.out.println(e);}//catch end
        return 3;
    }//signUp end

    // 현재회사의 회원목록
    public List<Member_SubDto> subUserList(){
        List<Member_SubDto> list = new ArrayList<>();
        try{
            String sql = "select * from Member_sub where cno = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,currentCno);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Member_SubDto dto = new Member_SubDto();
                dto.setMno(rs.getInt("mno"));
                dto.setCno(rs.getInt("cno"));
                dto.setmCategory(rs.getInt("mCategory"));
                dto.setmId(rs.getString("mId"));
                dto.setmPwd(rs.getString("mPwd"));
                dto.setmPhone(rs.getString("mPhone"));
                dto.setmName(rs.getString("mName"));
                dto.setmDate(rs.getString("mDate"));
                list.add(dto);
            }// while end
        } catch (Exception e) { System.out.println(e); }
        return list;
    }// func end

    // 로그인한 회원 정보반환
    public Member_SubDto subSignIn(String mId , String mPwd){
        Member_SubDto dto = new Member_SubDto();
        try{
            String sql = "select * from Member_sub where mId = ? and mPwd = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,mId);
            ps.setString(2,mPwd);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                dto.setMno(rs.getInt("mno"));
                dto.setCno(rs.getInt("cno"));
                dto.setmCategory(rs.getInt("mCategory"));
                dto.setmId(rs.getString("mId"));
                dto.setmPwd(rs.getString("mPwd"));
                dto.setmName(rs.getString("mName"));
                dto.setmPhone(rs.getString("mPhone"));
                dto.setmDate(rs.getString("mDate"));
            }// if end
        }catch (Exception e){ System.out.println(e); }
        return dto;
    }// func end
}// class end
