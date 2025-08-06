package project.controller; // 패키지명


import project.model.dao.PlanDao;
import project.model.dto.PlanDto;
import java.util.ArrayList;

public class PlanController { // class start
    // 싱글톤
    private PlanController(){}
    private static final PlanController instance = new PlanController();
    public static PlanController getInstance(){ return instance; }

    // dao 가져오기
    private PlanDao planDao = PlanDao.getInstance();

    // 플랜번호리스트 전역변수
    public static ArrayList<Integer> currentPno = new ArrayList<>();



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

    //구독플랜 (로그 PNO 호출)
    public boolean planEditLog(int pno){
        boolean result=planDao.planEditLog(pno);
        return result;
    }

    //구독플랜 수정
    public int planEdit(PlanDto planDto){
        if(planDao.planEditLog(planDto.getPno())){
            return 4;
        }
        int result = planDao.planEdit(planDto);
        return result;
    }

    //구독플랜삭제
    public boolean planDelete(int pno){
        boolean result = planDao.planDelete(pno);
        return result;
    }//func end

    // 유효성검사 후 중지할 플랜번호 리스트에 추가
    public boolean planStop(int pno){
        ArrayList<PlanDto> list = planDao.planList();
        for (int i = 0; i < list.size(); i++){
            PlanDto dto = list.get(i);
            if (dto.getPno() == pno){ // view로 부터 들어오는 pno 와 같으면.
                if( !currentPno.contains( pno ) ){
                    // 구독플랜 중지가 아니면
                    currentPno.add( pno );
                }// func end
                return true;
            }// if end
        }// for end
        return false;
    }// func end

    // 플랜 중지 해제 기능
    public boolean planRestart(int pno){
        if (currentPno.contains(pno)){
            currentPno.remove(pno);
            return true;
        }// if end
        return false;
    }// func end

}// class end
