package project.controller; // 패키지명


import project.model.dao.CompanyDao;
import project.model.dto.CompanyDto;

import java.util.ArrayList;

public class CompanyController {// class start
    // 싱글톤
    private CompanyController(){}
    private static final CompanyController instance = new CompanyController();
    public static CompanyController getInstance(){ return instance; }

    // 전역변수 플랜번호
    public static int currentCno;

    //dao 가져오기
    private CompanyDao companyDao = CompanyDao.getInstance();

    // 지역콜택시 조회 기능
    public ArrayList<CompanyDto> taxiList(){
        ArrayList<CompanyDto> result = companyDao.taxiList();
        return result;
    }
}// class end
