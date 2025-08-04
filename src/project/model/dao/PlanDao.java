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
    public PlanDto planAdd(String pName, int pDate, int pMoney){
        PlanDto planDto = new PlanDto();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(db_url,db_user,db_password);
        } catch (Exception e) { System.out.println(e); }// try end
    }// func end

    //구독플랜수정
    public boolean  planEdit(PlanDto planDto){
        try {
            String sql = "UPDATE plan SET pName=?,pDate=?,pMoney=? WHERE pno=?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, planDto.getpName());
            ps.setInt(2,planDto.getpDate());
            ps.setInt(3,planDto.getpMoney());
            ps.setInt(4,planDto.getPno());
            int count= ps.executeUpdate();
            if(count==1) return true;
            return false;
        } catch (Exception e) {System.out.println(e);}//catch end
        return false;
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
            String sql = "select * from Member_Head where pName = ? and pDate = ? and pMoney = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,pName);
            ps.setInt(2,pDate);
            ps.setInt(3,pMoney);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                planDto.setPno(rs.getInt("pno"));
                planDto.setpName(rs.getString("pName"));
                planDto.setpDate(rs.getInt("pDate"));
                planDto.setpMoney(rs.getInt("pMoney"));
            }//while end
        }catch (SQLException e){
            System.out.println(e);
        }return planDto;
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

}// class end
