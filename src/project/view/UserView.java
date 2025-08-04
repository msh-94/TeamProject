package project.view; // 패키지명

import project.controller.*;
import project.model.dto.CompanyDto;
import project.model.dto.Member_HeadDto;

import java.util.Scanner;

import static project.controller.Member_HeadController.currentMno;

public class UserView { // class start
    // 싱글톤
    private UserView(){}
    private static final UserView instance = new UserView();
    public static UserView getInstance(){return instance;}

    //(*) 모든 메소드가 사용 가능하도록 메소드밖에 입력객체 생성
    private Scanner scan = new Scanner(System.in);

    // controller 불러오기
    private Member_HeadController mhc = Member_HeadController.getInstance();
    private Member_SubController msc = Member_SubController.getInstance();
    private PlanController pc = PlanController.getInstance();
    private LogController lc = LogController.getInstance();
    private CompanyController cc = CompanyController.getInstance();

    /* ======================================== ★ 사용자별 화면(view) ★ ============================================== */

    /* (1) [본사]사용자단: 공통화면(로그인전/비회원/로그인 한 회원번호가 없는 경우_currentMno) ---------------------------------*/
    // 1.1.회원가입
    public void signUp(){
        System.out.println("\n1.회원가입\n");
    }//func end

    // 1.2.로그인
    public void logIn(){
        System.out.print("아이디 : ");     String mId = TotalView.scan.next();
        System.out.print("비밀번호 : ");    String mPwd = TotalView.scan.next();
        Member_HeadDto result = mhc.logIn(mId,mPwd);
        System.out.printf("\n[안내] 반갑습니다 %s님\n",result.getmName());
    }//func end

    // 1.3.구독신청
    public void subscribeRequest(){
        System.out.println("\n3.구독신청\n");
    }//func end

    // 1.4.데모체험
    public void siteManaser(){
        CompanyDto result = cc.siteManaser(currentMno);
        if (result != null){
            System.out.println("------------------------------------------------------------------------------------------------------------");
            System.out.printf("< %s >    1.관리자단(ADMIN)         2. 사용자단(USER)         3. 본사바로가기\n" ,result.getcName());
            System.out.println("------------------------------------------------------------------------------------------------------------");
            System.out.print("선택 > ");
            int choose = TotalView.scan.nextInt();
        }else {
            System.out.println("------------------------------------------------------------------------------------------------------------");
            System.out.println("< 데모체험 >  1.관리자단(ADMIN)         2. 사용자단(USER)         3. 본사바로가기");
            System.out.println("------------------------------------------------------------------------------------------------------------");
            System.out.print("선택 > ");
            int choose = TotalView.scan.nextInt();
        }// if end
    }//func end

    // 1.5.지역콜택시조회
    public void taxiList(){
        System.out.println("\n5.지역콜택시조회\n");
    }//func end

    /* (2) [본사]사용자단: 회원(구독X/구독O) 로그인 화면 -------------------------------------------------------------------*/
    // 2.1.정보수정
    public void updateProfile(){
        System.out.print("비밀번호 : ");    String mPwd = TotalView.scan.next();
        System.out.print("수정할 비밀번호 : ");    String mPwd1 = TotalView.scan.next();
        System.out.print("수정할 전화번호 : ");    String mPhone = TotalView.scan.next();
        int result = mhc.updateProfile(mPwd,mPhone,mPwd1);
        if (result == 1){
            System.out.println("[안내] 정보수정이 완료되었습니다.");
        }else if (result == 2){
            System.out.println("[경고] 비밀번호가 불일치합니다. ");
        }else {
            System.out.println("[오류] 관리자 문의! (000-0000)");
        }// if end
    }//func end

    // 2.2.로그아웃
    public void signOut(){
        if(currentMno > 0){
            currentMno = 0;
            System.out.println("로그아웃 되었습니다.");
        }else {
            System.out.println("[오류] 관리자 문의(000-0000)");
        }
    }//func end

    // 2.6.구독현황
    public void subscribeState(){
        System.out.println("\n3.구독플랜 조회\n");
    }//func end

    // 2.7.회원탈퇴
    public void withdrawUser(){
        boolean result = mhc.withdrawUser();
        if (result){
            System.out.println("[안내] 회원탈퇴 되었습니다.");
        }else {
            System.out.println("[오류] 관리자 문의! (000-0000)");
        }// if end
    }//func end

    // 2.8.구독취소
    public void subscribeCancle(){
        System.out.println("\n8.구독취소\n");
    }//func end
}// class end