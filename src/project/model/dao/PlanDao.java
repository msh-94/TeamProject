package project.model.dao; // 패키지명

import project.model.dto.PlanDto;

import java.sql.*;

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
}// class end
