package project.model.dao; // 패키지명

import project.model.dto.PlanDto;

import java.sql.*;
import java.util.ArrayList;

public class PlanDao {// class start
    // 싱글톤
    private PlanDao(){connect();}
    private static final PlanDao instance = new PlanDao();
    public static PlanDao getInstance(){ return instance; }

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

    // 플랜 등록 - 플랜 번호, 구독플랜명, 구독기간, 금액 반환 기능
    public PlanDto planAdd(String pName, int pDate, int pMoney){
        PlanDto planDto = new PlanDto();
        try{
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
