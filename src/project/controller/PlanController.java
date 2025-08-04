package project.controller; // 패키지명

import project.model.dao.PlanDao;
import project.model.dto.PlanDto;

public class PlanController { // class start
    // 싱글톤
    private PlanController(){}
    private static final PlanController instance = new PlanController();
    public static PlanController getInstance(){ return instance; }
    //싱글톤 호출
    public static PlanDao planDao = PlanDao.getInstance();
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
