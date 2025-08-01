package project.view; // 패키지명

import java.util.InputMismatchException;
import java.util.Scanner;

public class TotalView { // class start
    // 싱글톤
    private TotalView(){}
    private static final TotalView instance = new TotalView();
    public static TotalView getInstance(){ return instance; }

    // 전역변수
    Scanner scan = new Scanner(System.in);



    /* ★ Method(공통) ★ =================================================================================== */

    // * 본사 > 사용자단 > 공통화면( 비회원/로그인 전 )
    public void index(){
        try {
            for(;;){
                System.out.println("=================== MY_CALL (본사 ★ 지역콜택시 플랫폼) ==================");
                System.out.println("1.회원가입 |  2.로그인  |  3.구독신청  |  4.데모체험  |  5.지역콜택시조회");
                System.out.println("=========================================================================");
                System.out.print("선택 > ");
                int choose = scan.nextInt();
                if( choose == 1 ) signUp();
                else if( choose == 2 ) logIn();
                else if( choose == 3 ) subscribeRequest();
                else if( choose == 4 ) siteManaser();
                else if( choose == 5 ) taxiList();
                else System.out.println("[경고] 올바른 메뉴(숫자)를 입력하세요.");

            }//for end
        }catch ( InputMismatchException e ){ System.out.println("[경고] 타입불일치! 숫자를 입력하세요." + e ); }
         catch ( Exception e ){ System.out.println("[오류] 관리자 문의(000-0000)" + e );
        }//catch end

    }//func end

    /* ★ 단위기능 ★ ======================================================================================== */

    // 1.회원가입
    public void signUp(){
        System.out.println("\n1.회원가입\n");

    }//for end

    // 2.로그인
    public void logIn(){
        System.out.println("\n2.로그인\n");

    }//for end

    // 3.구독신청
    public void subscribeRequest(){
        System.out.println("\n3.구독신청\n");

    }//for end

    // 4.데모체험
    public void siteManaser(){
        System.out.println("\n4.데모체험\n");

    }//for end

    // 5.지역콜택시조회
    public void taxiList(){
        System.out.println("\n5.지역콜택시조회\n");

    }//for end

}// class end
