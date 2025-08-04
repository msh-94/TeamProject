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
    private UserView uv = UserView.getInstance();
    private AdminView av = AdminView.getInstance();

    // 전역변수
    public static Scanner scan = new Scanner(System.in);

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
                    if( choose == 1 ) av.planAdd();
                    else if( choose == 2 ) av.planList();
                    else if( choose == 3 ) av.planEdit();
                    else if( choose == 4 ) av.planDelete();
                    else if( choose == 5 ) av.userList();
                    else if( choose == 6 ) av.planUserList();
                    else if( choose == 7 ) av.subscribePrint();
                    else if( choose == 8 ) uv.signOut();
                    else System.out.println("\n[경고] 올바른 메뉴(숫자)를 입력하세요.\n");
                }else if( currentMno >= 2 ){ //★☆★☆ [본사]사용자단: 회원(구독X/구독O) 로그인 화면
                    System.out.println("============== MY_CALL (본사 ★ 지역콜택시 플랫폼) =================");
                    System.out.println("1.정보수정 | 2.로그아웃 | 3.구독신청 | 4.데모체험  |  5.지역콜택시조회");
                    System.out.println("6.구독현황 | 7.회원탈퇴"); // | 8.구독취소
                    System.out.println("================================================================");
                    System.out.print("선택 > ");
                    int choose = scan.nextInt();
                    if( choose == 1 ) uv.updateProfile();
                    else if( choose == 2 ) uv.signOut();
                    else if( choose == 3 ) uv.subscribeRequest();
                    else if( choose == 4 ) uv.siteManaser();
                    else if( choose == 5 ) uv.taxiList();
                    else if( choose == 6 ) uv.subscribeState();
                    else if( choose == 7 ) uv.withdrawUser();
                    else if( choose == 8 ) uv.subscribeCancle(); // 8.구독취소 : 구독중인 회원전용 메뉴
                    else System.out.println("\n[경고] 올바른 메뉴(숫자)를 입력하세요.\n");
                }else{ //★☆★☆ [본사]사용자단: 공통화면(로그인전/비회원/로그인 한 회원번호가 없는 경우_currentMno)
                    System.out.println("=================== MY_CALL (본사 ★ 지역콜택시 플랫폼) ===============");
                    System.out.println("1.회원가입 |  2.로그인  |  3.구독신청  |  4.데모체험  |  5.지역콜택시조회");
                    System.out.println("====================================================================");
                    System.out.print("선택 > ");
                    int choose = scan.nextInt();
                    if( choose == 1 ) uv.signUp();
                    else if( choose == 2 ) uv.logIn();
                    else if( choose == 3 ) uv.subscribeRequest();
                    else if( choose == 4 ) uv.siteManaser();
                    else if( choose == 5 ) uv.taxiList();
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





}//class end