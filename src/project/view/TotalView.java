package project.view;

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
    // 본사 사용자별 View( 0.비회원/ 1.본사관리자/ 2.본사회원(구독X_구독O) / 그외_회원정보 찾을 수 없음 )
    public void index(){
System.out.println("""

                  ╔═════════════════════════════╗
                     🚨 MY CALL-TAXI SYSTEM 🚨
                     -------------------------
                      SaaS기반 월구독형 택시플랫폼
                  ╚═════════════════════════════╝
                   호출 수수료 0원! 택시 수익성 100%!
                  모두가 만족하는 우리동네 콜택시 서비스
                           ⓒ 2025.MJLK.
                         
""");// 콘솔프로그램 최초 실행시, 나오는 1회성 인트로 홍보문구
        for(;;){
            try {
                //System.out.println( "currentMno : 로그인한 전역변수 회원번호(초기값 : 0) : " + currentMno );
                if( currentMno == 0 ){ //0.본사 사용자단(본사 비회원 전용메뉴): 메인 로그인전 공통화면
                    System.out.println("┌─────────────<< 월구독형🚖 MY CALL-TAXI 🚖택시플랫폼 >>──────────────┐");
                    System.out.println("     1.회원가입   2.로그인   3.구독신청   4.데모체험   5.지역콜택시조회");
                    System.out.println("└──────────────────────────────────────────────────────────────────┘");
                    System.out.print("선택 > ");
                    int choose = scan.nextInt();
                    if( choose == 1 ) signUp();
                    else if( choose == 2 ) logIn();
                    else if( choose == 3 ) subscribeRequest();
                    else if( choose == 4 ) siteManaser();
                    else if( choose == 5 ) taxiList();
                    else System.out.println( "\n[경고] 해당하는 메뉴(숫자)를 입력하세요.\n" );
                }else if( currentMno == 1 ){ //1.본사 관리자단(본사 관리자 전용메뉴): admin 로그인화면
                    System.out.println("┌─────────────────<< 본사🛡️ MY CALL-TAXI 🛡️관리자 >>─────────────────┐");
                    System.out.println("    1.구독플랜등록  2.구독플랜조회    3.구독플랜수정     4.구독플랜삭제");
                    System.out.println("    5.회원목록조회  6.구독자목록조회  7.구독신청내역조회  8.로그아웃");
                    System.out.println("└─────────────────────────────────────────────────────────────────┘");
                    System.out.print("선택 > ");
                    int choose = scan.nextInt();
                    if( choose == 1 ) planAdd();
                    else if( choose == 2 ) planList();
                    else if( choose == 3 ) planEdit();
                    else if( choose == 4 ) planDelete();
                    else if( choose == 5 ) userList();
                    else if( choose == 6 ) planUserList();
                    else if( choose == 7 ) subscribePrint();
                    else if( choose == 8 ) signOut();
                    else System.out.println("\n[경고] 해당하는 메뉴(숫자)를 입력하세요.\n");
                }else if( currentMno >= 2 ){ //2.본사 사용자단(본사 회원 전용메뉴): 회원(구독X/구독O) 로그인화면
                    System.out.println("┌─────────────────<< 회원🧑‍ MY CALL-TAXI 👩전용>>──────────────────┐");
                    System.out.println("    1.정보수정  2.로그아웃  3.구독신청  4.데모체험  5.지역콜택시조회");
                    System.out.println("    6.구독현황  7.회원탈퇴"); // | 8.구독취소
                    System.out.println("└─────────────────────────────────────────────────────────────────┘");
                    System.out.print("선택 > ");
                    int choose = scan.nextInt();
                    if( choose == 1 ) updateProfile();
                    else if( choose == 2 ) signOut();
                    else if( choose == 3 ) subscribeRequest();
                    else if( choose == 4 ) siteManaser();
                    else if( choose == 5 ) taxiList();
                    else if( choose == 6 ) subscribeState();
                    else if( choose == 7 ) withdrawUser();
                    else if( choose == 8 ) subscribeCancle(); // 8.구독취소 : 구독중인(구독O) 회원전용 메뉴
                    else System.out.println("\n[경고] 해당하는 메뉴(숫자)를 입력하세요.\n");
                }else{ // 그외 : 오류 → currentMno(로그인 한 회원번호가 없는 경우)
                    System.out.println( "\n[경고] 회원정보를 찾을 수 없습니다.\n" );
                }//if end
            }catch ( InputMismatchException e ){
                System.out.println( "\n[경고] 입력타입 불일치! 숫자를 입력하세요." + e + "\n" );
                scan = new Scanner( System.in );//입력객체 초기화
            }catch ( Exception e ){
                System.out.println( "\n[오류] 개발팀 문의( root.kjs82@gmail.com )" + e + "\n" );
            }//catch end
        }//for(무한루프) end
    }//func end

    /* ======================================== ★ 사용자별 화면(view) ★ ============================================== */

    /* [1] 본사 > 사용자단 > 공통화면(로그인전/비회원/로그인 한 회원번호가 없는 경우_currentMno) --------------------------------*/
    // 1-1.회원가입
    public void signUp(){
        System.out.println("\n1.회원가입\n");
    }//func end

    // 1-2.로그인
    public void logIn(){
        System.out.println("\n2.로그인\n");
    }//func end

    // 1-3.구독신청
    public void subscribeRequest(){
        System.out.println("\n3.구독신청\n");
    }//func end

    // 1-4.데모체험
    public void siteManaser(){
        System.out.println("\n4.데모체험\n");
    }//func end

    // 1-5.지역콜택시조회
    public void taxiList(){
        System.out.println("\n5.지역콜택시조회\n");
    }//func end

    /* [2] 본사 > 사용자단 > 회원 로그인화면(구독X/구독O) ------------------------------------------------------------------*/
    // 2-1.정보수정
    public void updateProfile(){
        System.out.println("\n1.정보수정n");
    }//func end

    // 2-2/3-8.로그아웃
    public void signOut(){
        System.out.println("\n2.로그아웃\n"); //2. 본사 > 사용자단 로그아웃
        System.out.println("\n8.로그아웃\n"); //8. 본사 > 관리자단 로그아웃
    }//func end

    // 2-6.구독현황
    public void subscribeState(){
        System.out.println("\n6.구독현황\n");
    }//func end

    // 2-7.회원탈퇴
    public void withdrawUser(){
        System.out.println("\n7.회원탈퇴\n");
    }//func end

    // 2-8.구독취소 : 구독중인 회원전용 메뉴
    public void subscribeCancle(){
        System.out.println("\n8.구독취소\n");
    }//func end

    /* [3] 본사 > 관리자단 > 로그인화면(admin/시스템로그인) ----------------------------------------------------------------*/
    // 3-1.구독플랜 등록
    public void planAdd(){
        System.out.println("\n1.구독플랜 등록\n");
    }//func end

    // 3-2.구독플랜 조회
    public void planList(){
        System.out.println("\n2.구독플랜 조회\n");
    }//func end

    // 3-3.구독플랜 수정
    public void planEdit(){
        System.out.println("\n3.구독플랜 수정\n");
    }//func end

    // 3-4.구독플랜 삭제
    public void planDelete(){
        System.out.println("\n4.구독플랜 삭제\n");
    }//func end

    // 3-5.회원목록 조회
    public void userList(){
        System.out.println("\n5.회원목록 조회\n");
    }//func end

    // 3-6.구독자목록 조회
    public void planUserList(){
        System.out.println("\n6.구독자목록 조회\n");
    }//func end

    // 3-7.구독신청 내역조회
    public void subscribePrint(){
        System.out.println("\n7.구독신청 내역조회\n");
    }//func end

}//class end