package project.model.dao; // 패키지명

import project.model.dto.PlanDto;
import project.model.dto.PlanDto;

import java.sql.*;

import java.sql.*;
import java.util.ArrayList;

public class PlanDao extends Dao {// class start
    // 싱글톤
    private PlanDao(){}
    private static final PlanDao instance = new PlanDao();
    public static PlanDao getInstance(){ return instance; }

    // 플랜 등록 - 플랜 번호, 구독플랜명, 구독기간, 금액 반환 기능
    public boolean planAdd(PlanDto planDto){
        try{
            String sql = "INSERT INTO plan (pName, pDate,pMoney) VALUES(?,?,?) ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,planDto.getpName());
            ps.setInt(2,planDto.getpDate());
            ps.setInt(3,planDto.getpMoney());
            int count = ps.executeUpdate();
            if(count>=1) return true;
            return false;
        }//while end
        catch (SQLException e){
            System.out.println(e);
        }return false;
    }//func end

    // 플랜 조회 - 플랜 전체 반환 기능
    public ArrayList<PlanDto> planList(){
        ArrayList<PlanDto> list = new ArrayList<>();
        try{
            String sql = "select * from plan";
            PreparedStatement ps1 = conn.prepareStatement(sql);
            ResultSet rs1 = ps1.executeQuery();
            while(rs1.next()){
                PlanDto dto = new PlanDto(
                        rs1.getInt("pno"),
                        rs1.getString("pName"),
                        rs1.getInt("pDate"),
                        rs1.getInt("pMoney")
                );list.add(dto);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }//func end

    //구독플수정(로그 PNO 호출)
    public boolean planEditLog(int pno){
        try{
            String sql= "select *from log where pno=?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, pno);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                return true;
            }//while end
            return false;
        } catch (Exception e) {System.out.println(e);}
        return false;
    }//func end

    //구독플랜수정
    public int planEdit(PlanDto planDto){
        try {
            String sql = "UPDATE plan SET pName=?,pMoney=?,pDate=? where pno=?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, planDto.getpName());
            ps.setInt(2, planDto.getpMoney());
            ps.setInt(3, planDto.getpDate());
            ps.setInt(4, planDto.getPno());
            int result = ps.executeUpdate();
            if(result==1) return 1;
            return 2;
        } catch (Exception e) {System.out.println(e);}
        return 3;
    }//func end

    //구독플랜삭제
    public boolean planDelete(int pno) {
        try {
            String sql = "DELETE FROM plan WHERE pno=?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,pno);
            int count = ps.executeUpdate();
            if(count==1) return true;
            return false;
        } catch (Exception e) {
            System.out.println("[경고] 구독중인 구독자가 있는 구독플랜은 삭제가 불가합니다.");
        }//catch end
        return false;
    }//func end

}// class end