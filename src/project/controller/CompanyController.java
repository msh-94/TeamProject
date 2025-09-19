package project.controller; // 패키지명


import project.Container;
import project.model.dao.CompanyDao;
import project.model.dto.CompanyDto;


import java.util.ArrayList;

public class CompanyController {// class start
    //// 싱글톤
    //private CompanyController(){}
    //private static final CompanyController instance = new CompanyController();
    //public static CompanyController getInstance(){ return instance; }

    // dao가져오기
    private final CompanyDao companyDao = Container.getBean(CompanyDao.class);

    // 전역변수 회사번호
    public static int currentCno;

    // 로그인한 회원번호 일치하는 회사정보 반환 기능
    public CompanyDto siteManaser(int mno){
        CompanyDto result = companyDao.siteManaser(mno);
        return result;
    }// func end

    // 지역콜택시 조회 기능
    public ArrayList<CompanyDto> taxiList(){
        ArrayList<CompanyDto> result = companyDao.taxiList();
        return result;
    }
}// class end
