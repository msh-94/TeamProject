package project.controller; // 패키지명

import project.model.dao.PlanDao;
import project.model.dto.PlanDto;

import java.util.ArrayList;

public class PlanController { // class start
    // 싱글톤
    private PlanController(){}
    private static final PlanController instance = new PlanController();
    public static PlanController getInstance(){ return instance; }

    // 전역변수 플랜번호

    // dao 가져오기
    private PlanDao planDao = PlanDao.getInstance();


    // 구독플랜 등록 기능
    public boolean planAdd(String pName, int pDate, int pMoney) {
       PlanDto planDto = new PlanDto(0,pName,pDate,pMoney);
       boolean result = planDao.planAdd(planDto);
       return result;
    }

    // 구독플랜 조회 기능
    public ArrayList<PlanDto> planList(){
        ArrayList<PlanDto> result = planDao.planList();
        return result;
    }
}// class end
