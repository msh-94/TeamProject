package project.view; // 패키지명

import project.controller.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class totalView { // class start
    // 싱글톤
    private totalView(){}
    private static final totalView instance = new totalView();
    public static totalView getInstance(){ return instance; }

    // 모든 메소드가 사용 가능하도록 메소드밖에 입력객체 생성
    private Scanner scan = new Scanner(System.in);

    // 싱글톤 전체 불러오기
    //private LogController logController = LogController.getInstance();
    //private CompanyController companyController = CompanyController.getInstance();
    //private Member_HeadController memberHeadController = Member_HeadController.getInstance();
    //private Member_SubController memberSubController = Member_SubController.getInstance();
    //private PlanController planController = PlanController.getInstance();

    public void index(){
        for(;;){
            try{
                System.out.println("================ MY_CALL (본사 ★ 지역콜택시 플랫폼) ================");
                System.out.println("1. 회원가입  |  2. 로그인  |  3. 구독신청  |  4. 데모체험  |  5. 지역콜택시조회");
                System.out.println("선택 > ");
                int choose = scan.nextInt();
                if (choose ==1){}
                else if (choose == 2){}
                else if (choose == 3){}
                else if (choose == 4){}
                else if (choose == 5){}
                else{
                    System.out.println("[경고] 존재하지 않는 번호 입니다.");
                }
            }catch (Exception e){
                System.out.println("[오류] 관리자에게 문의");
            }
        }
    }




}// class end
