package project.model.dao; // 패키지명

import project.model.dto.CompanyDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CompanyDao {// class start
    // 싱글톤
    private CompanyDao(){ connect(); }
    private static final CompanyDao instance = new CompanyDao();
    public static CompanyDao getInstance(){return instance; }

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

    // 지역 콜택시 조회 - company 전체 반환 기능
    public ArrayList<CompanyDto> taxiList(){
        ArrayList<CompanyDto> list = new ArrayList<>();
        try{
            String sql = "select * from company";
            PreparedStatement cs = conn.prepareStatement(sql);
            ResultSet rs = cs.executeQuery();
            while (rs.next()){
                CompanyDto dto = new CompanyDto(
                        rs.getInt("cno"),
                        rs.getInt("mno"),
                        rs.getString("cName"),
                        rs.getString("area"),
                        rs.getString("service")
                );list.add(dto);
            }
        } catch (Exception e) {
            System.out.println(e);
        }return list;
    }
}// class end
