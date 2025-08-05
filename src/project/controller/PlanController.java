package project.controller; // 패키지명

import project.model.dao.PlanDao;
import project.model.dto.PlanDto;

import java.util.ArrayList;

import project.model.dao.PlanDao;
import project.model.dto.PlanDto;

public class PlanController { // class start
    // 싱글톤
    private PlanController(){}
    private static final PlanController instance = new PlanController();
    public static PlanController getInstance(){ return instance; }

    // 전역변수 플랜번호
    public static int currentPno;
    // dao 가져오기
    private PlanDao planDao = PlanDao.getInstance();


    // 구독플랜 등록 기능
    public boolean planAdd(String pName, int pDate, int pMoney) {
        PlanDto result = planDao.planAdd(pName, pDate, pMoney);
        if (result != null) {
            currentPno = result.getPno();
            return true;
        }else {
            return false;
        }
    }

    // 구독플랜 조회 기능
    public ArrayList<PlanDto> planList(){
        ArrayList<PlanDto> result = planDao.planList();
        return result;
    }

    //구독플랜수정
    public boolean planEdit(PlanDto planDto){
         boolean result= planDao.planEdit(planDto);
         return result;
    }

    //구독플랜삭제
    public boolean planDelete(int pno){
        boolean result = planDao.planDelete(pno);
        return result;
    }//func end
}// class end
