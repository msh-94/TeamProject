package project.controller; // 패키지명


import project.model.dao.PlanDao;
import project.model.dto.PlanDto;
import java.util.ArrayList;

public class PlanController { // class start
    // 싱글톤
    private PlanController(){}
    private static final PlanController instance = new PlanController();
    public static PlanController getInstance(){ return instance; }
    //싱글톤 호출
    public static PlanDao planDao = PlanDao.getInstance();

    //구독플랜 (로그 PNO 호출)
    public boolean planEditLog(int pno){
        boolean result=planDao.planEditLog(pno);
        return result;
    }

    //구독플랜 수정
    public int planEdit(PlanDto planDto){
        if(planDao.planEditLog(planDto.getPno())){
            return 2;
        }
        int result = planDao.planEdit(planDto);
        return result;
    }




    // 전역변수 플랜번호
    public static int currentPno;

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
    }//func end
}// class end
