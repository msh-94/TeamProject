package project.model.dao; // 패키지명

import project.model.dto.CompanyDto;

import project.model.dto.CompanyDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CompanyDao extends Dao {// class start
    // 싱글톤
    private CompanyDao(){  }
    private static final CompanyDao instance = new CompanyDao();
    public static CompanyDao getInstance(){return instance; }


    // 로그인한 회원번호 일치하는 회사정보 반환 기능
    public CompanyDto siteManaser(int mno){
        CompanyDto dto = new CompanyDto();
        try{
            String sql = "select * from Company where mno = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,mno);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                dto.setMno(rs.getInt("mno"));
                dto.setArea(rs.getString("area"));
                dto.setCno(rs.getInt("cno"));
                dto.setcName(rs.getString("cName"));
                dto.setService(rs.getString("service"));
            }// while end
        } catch (Exception e) { System.out.println(e); } // try end
        return dto;
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
