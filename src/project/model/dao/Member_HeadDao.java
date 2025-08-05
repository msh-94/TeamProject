package project.model.dao; // 패키지명

import project.model.dto.Member_HeadDto;

import java.sql.*;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;

public class Member_HeadDao extends Dao { // class start
    // 싱글톤
    private Member_HeadDao(){}
    private static final Member_HeadDao instance = new Member_HeadDao();
    public static Member_HeadDao getInstance(){ return instance; }

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
                dto.setmName(rs.getNString("mName"));
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
    public int updateProfile(Member_HeadDto dto){
        try{
            String sql = "update Member_head set mPwd = ? , mPhone = ? where mno = ? and mPwd = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,dto.getmName());
            ps.setString(2,dto.getmPhone());
            ps.setInt(3,dto.getMno());
            ps.setString(4,dto.getmPwd());
            int count = ps.executeUpdate();
            if (count == 1)return 1;
            return 2;
        } catch (SQLException e) { System.out.println(e); }
        return 3;
    }// func end

    // 구독중인 사람 조회 기능
    public ArrayList<Map<String,Object>> planUserList(){
        ArrayList<Map<String,Object>> maps = new ArrayList<>();
        try{
            String sql = "select m.mno , c.area , p.pName , m.mId , m.mName , m.mCategory , m.mPhone , MIN(l.addDate) as firstDate " +
                    ", MAX(l.endDate) as lastDate from plan p join Log l on p.pno  = l.pno\n" +
                    "join Member_head m on m.mno = l.mno\n" +
                    "join company c on m.mno = c.mno group by m.mno, c.area, p.pName, m.mId, m.mName, m.mCategory, m.mPhone having lastDate >= current_date() order by mno asc";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Map<String,Object> map = new HashMap<>();
                map.put("번호" , rs.getObject("mno"));
                map.put("지역",rs.getObject("area"));
                map.put("플랜이름" , rs.getObject("pName"));
                map.put("아이디" , rs.getObject("mId"));
                map.put("이름",rs.getObject("mName"));
                map.put("유형" , rs.getObject("mCategory"));
                map.put("핸드폰번호", rs.getObject("mPhone"));
                map.put("시작일" , rs.getObject("firstDate"));
                map.put("종료일",rs.getObject("lastDate"));
                maps.add(map);
            }// while end
        } catch (Exception e) { System.out.println(e); }
        return maps;
    }// func end

    // 구독만료된 사람 조회 기능
    public ArrayList<Map<String,Object>> planEndUserList(){
        ArrayList<Map<String,Object>> maps = new ArrayList<>();
        try{
            String sql = "select m.mno , c.area , p.pName , m.mId , m.mName , m.mCategory , m.mPhone , MIN(l.addDate) as firstDate " +
                    ", MAX(l.endDate) as lastDate from plan p join Log l on p.pno  = l.pno \n" +
                    " join Member_head m on m.mno = l.mno join company c on m.mno = c.mno" +
                    " group by m.mno, c.area, p.pName, m.mId, m.mName, m.mCategory, m.mPhone having lastDate < current_date() order by mno asc";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Map<String,Object> map = new HashMap<>();
                map.put("번호" , rs.getObject("mno"));
                map.put("지역",rs.getObject("area"));
                map.put("플랜이름" , rs.getObject("pName"));
                map.put("아이디" , rs.getObject("mId"));
                map.put("이름",rs.getObject("mName"));
                map.put("유형" , rs.getObject("mCategory"));
                map.put("핸드폰번호", rs.getObject("mPhone"));
                map.put("시작일" , rs.getObject("firstDate"));
                map.put("종료일",rs.getObject("lastDate"));
                maps.add(map);
            }// while end
        } catch (Exception e) { System.out.println(e); }
        return maps;
    }// func end

    //[본사] 회원가입
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
