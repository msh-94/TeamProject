package project.model.dao; // 패키지명

import project.model.dto.CompanyDto;


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
}// class end
