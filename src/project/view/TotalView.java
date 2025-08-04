package project.view; // 패키지명

import project.controller.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import static project.controller.Member_HeadController.currentMno;

public class TotalView {
    // 싱글톤 생성
    private TotalView(){}
    private static final TotalView instance = new TotalView();
    public static TotalView getInstance(){ return instance; }

    // 싱글톤 호출
    private Member_HeadController mhc = Member_HeadController.getInstance();
    private Member_SubController msc = Member_SubController.getInstance();
    private PlanController pc = PlanController.getInstance();
    private LogController lc = LogController.getInstance();
    private CompanyController cc = CompanyController.getInstance();

    // 전역변수
    Scanner scan = new Scanner(System.in);

    /* ============================================ ★ Method(공통) ★ ================================================ */
    // 본사 > 사용자단 > 공통화면( 비회원/로그인 전 )
    public void index(){
        for(;;){
            try {
                if( currentMno == 1 ) { //★☆★☆ [본사]관리자단: admin(시스템관리자) 로그인 화면
                    System.out.println("=================== MY_CALL (본사 ★ 관리자) ========================");
                    System.out.println("1.구독플랜 등록 | 2.구독플랜 조회   | 3.구독플랜 수정    | 4.구독플랜 삭제");
                    System.out.println("5.회원목록 조회 | 6.구독자목록 조회 | 7.구독신청 내역조회 | 8. 로그아웃");
                    System.out.println("===================================================================");
                    System.out.print("선택 > ");
                    int choose = scan.nextInt();
                    if( choose == 1 ) planAdd();
                    else if( choose == 2 ) planList();
                    else if( choose == 3 ) planEdit();
                    else if( choose == 4 ) planDelete();
                    else if( choose == 5 ) userList();
                    else if( choose == 6 ) planUserList();
                    else if( choose == 7 ) subscribePrint();
                    else if( choose == 8 ) {signOut(); break;}
                    else System.out.println("\n[경고] 올바른 메뉴(숫자)를 입력하세요.\n");
                }else if( currentMno >= 2 ){ //★☆★☆ [본사]사용자단: 회원(구독X/구독O) 로그인 화면
                    System.out.println("============== MY_CALL (본사 ★ 지역콜택시 플랫폼) =================");
                    System.out.println("1.정보수정 | 2.로그아웃 | 3.구독신청 | 4.데모체험  |  5.지역콜택시조회");
                    System.out.println("6.구독현황 | 7.회원탈퇴"); // | 8.구독취소
                    System.out.println("================================================================");
                    System.out.print("선택 > ");
                    int choose = scan.nextInt();
                    if( choose == 1 ) updateProfile();
                    else if( choose == 2 ) {signOut(); break;}
                    else if( choose == 3 ) subscribeRequest();
                    else if( choose == 4 ) siteManaser();
                    else if( choose == 5 ) taxiList();
                    else if( choose == 6 ) subscribeState();
                    else if( choose == 7 ) withdrawUser();
                    else if( choose == 8 ) subscribeCancle(); // 8.구독취소 : 구독중인 회원전용 메뉴
                    else System.out.println("\n[경고] 올바른 메뉴(숫자)를 입력하세요.\n");
                }else{ //★☆★☆ [본사]사용자단: 공통화면(로그인전/비회원/로그인 한 회원번호가 없는 경우_currentMno)
                    System.out.println("=================== MY_CALL (본사 ★ 지역콜택시 플랫폼) ===============");
                    System.out.println("1.회원가입 |  2.로그인  |  3.구독신청  |  4.데모체험  |  5.지역콜택시조회");
                    System.out.println("====================================================================");
                    System.out.print("선택 > ");
                    int choose = scan.nextInt();
                    if( choose == 1 ) signUp();
                    else if( choose == 2 ) logIn();
                    else if( choose == 3 ) subscribeRequest();
                    else if( choose == 4 ) siteManaser();
                    else if( choose == 5 ) taxiList();
                    else System.out.println( "\n[경고] 올바른 메뉴(숫자)를 입력하세요.\n" );
                }//if end
            }catch ( InputMismatchException e ){
                System.out.println( "\n[경고] 타입불일치! 숫자를 입력하세요." + e + "\n" );
                scan = new Scanner( System.in );//입력객체 초기화
            }catch ( Exception e ){
                System.out.println( "\n[오류] 관리자 문의(000-0000)" + e + "\n" );
            }//catch end
        }//for(무한루프) end
    }//func end

    /* ======================================== ★ 사용자별 화면(view) ★ ============================================== */

    /* (1) [본사]사용자단: 공통화면(로그인전/비회원/로그인 한 회원번호가 없는 경우_currentMno) ---------------------------------*/
    // 1.1.회원가입
    public void signUp(){
        System.out.println("\n1.회원가입\n");
    }//func end

    // 1.2.로그인
    public void logIn(){
        System.out.println("\n2.로그인\n");
    }//func end

    // 1.3.구독신청
    public void subscribeRequest(){
        System.out.println("\n3.구독신청\n");
    }//func end

    // 1.4.데모체험
    public void siteManaser(){
        System.out.println("\n4.데모체험\n");
    }//func end

    // 1.5.지역콜택시조회
    public void taxiList(){
        System.out.println("\n5.지역콜택시조회\n");
    }//func end

    /* (2) [본사]사용자단: 회원(구독X/구독O) 로그인 화면 -------------------------------------------------------------------*/
    // 2.1.정보수정
    public void updateProfile(){
        System.out.println("\n1.정보수정n");
    }//func end

    // 2.2.로그아웃
    public void signOut(){
        System.out.println("\n2.로그아웃\n");
    }//func end

    // 2.6.구독현황
    public void subscribeState(){
        System.out.println("\n3.구독플랜 조회\n");
    }//func end

    // 2.7.회원탈퇴
    public void withdrawUser(){
        System.out.println("\n3.구독플랜 수정\n");
    }//func end

    // 2.8.구독취소
    public void subscribeCancle(){
        System.out.println("\n8.구독취소\n");
    }//func end

    /* (3) [본사]관리자단: admin(시스템관리자) 로그인 화면 -----------------------------------------------------------------*/
    // 3.1.구독플랜 등록
    public void planAdd(){
        System.out.println("\n1.구독플랜 등록\n");
    }//func end

    // 3.2.구독플랜 조회
    public void planList(){
        System.out.println("\n2.구독플랜 조회\n");
    }//func end

    // 3.3.구독플랜 수정
    public void planEdit(){
        System.out.println("\n3.구독플랜 수정\n");
    }//func end

    // 3.4.구독플랜 삭제
    public void planDelete(){
        System.out.println("\n4.구독플랜 삭제\n");
    }//func end

    // 3.5.회원목록 조회
    public void userList(){
        System.out.println("\n5.회원목록 조회\n");
    }//func end

    // 3.6.구독자목록 조회
    public void planUserList(){
        System.out.println("\n6.구독자목록 조회\n");
    }//func end

    // 3.7.구독신청 내역조회
    public void subscribePrint(){
        System.out.println("\n7.구독신청 내역조회\n");
    }//func end

}//class end
