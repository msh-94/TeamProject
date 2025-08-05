package project.view; // 패키지명

import project.controller.*;
import project.model.dto.CompanyDto;
import project.model.dto.Member_HeadDto;
import project.model.dto.PlanDto;

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
    // 본사 사용자별 View( 0.비회원/ 1.본사관리자/ 2.본사회원(구독X_구독O) / 그외_회원정보 찾을 수 없음 )
    public void index(){
        System.out.println("""


                        ╔═══════😊════════╗
                            🚨 MY CALL-TAXI SYSTEM 🚨
                             --------------------------
                             SaaS기반 월구독형 택시플랫폼
                        ╚═════════════════╝
                         호출 수수료 0원! 택시 수익률 100%!
                       모두가 만족하는 우리동네 콜택시 서비스
                                   ⓒ 2025.MJLK
                         
""");// 콘솔프로그램 최초 실행시, 나오는 1회성 인트로 홍보문구
        for(;;){
            try {
                //System.out.println( "currentMno : 로그인한 전역변수 회원번호(초기값 : 0) : " + currentMno );
                if( currentMno == 0 ){ //0.본사 사용자단(본사 비회원 전용메뉴): 메인 로그인전 공통화면
                    System.out.println("┌──────────<<🚖MY CALL-TAXI(월구독형 택시플랫폼)🚖>>──────────┐");
                    System.out.println("        1.회원가입   2.로그인   3.구독신청   4.데모체험   5.지역콜택시조회");
                    System.out.println("└──────────────────────────────────────────┘");
                    System.out.print("✔️메뉴선택 > ");
                    int choose = scan.nextInt();
                    if( choose == 1 ) uv.signUp();
                    else if( choose == 2 ) uv.logIn();
                    else if( choose == 3 ) uv.subscribeRequest();
                    else if( choose == 4 ) uv.siteManasers();
                    else if( choose == 5 ) uv.taxiList();
                    else System.out.println( "\n[경고] 해당하는 메뉴(숫자)를 입력하세요.\n" );
                }else if( currentMno == 1 ){ //1.본사 관리자단(본사 관리자 전용메뉴): admin 로그인화면
                    System.out.println("┌─────────────<<🛡️ MY CALL-TAXI(본사관리자)🛡️>>───────────┐");
                    System.out.println("        1.구독플랜등록  2.구독플랜조회    3.구독플랜수정     4.구독플랜삭제");
                    System.out.println("        5.회원목록조회  6.구독자목록조회  7.구독신청내역조회  8.로그아웃");
                    System.out.println("└──────────────────────────────────────────┘");
                    System.out.print("✔️메뉴선택 > ");
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
                    CompanyDto result = cc.siteManaser(currentMno);
                    boolean answer = mhc.checkMember();
                    if (result != null){ // 구독신청 사이트가 있을경우
                        if (answer){ existSite(); }
                        else {user();}
                    }else {  user(); }// if end   // 구독신청 사이트가 없을경우
                }else{ //★☆★☆ [본사]사용자단: 공통화면(로그인전/비회원/로그인 한 회원번호가 없는 경우_currentMno)
                    System.out.println("=================== MY_CALL (본사 ★ 지역콜택시 플랫폼) ===============");
                    System.out.println("1.회원가입 |  2.로그인  |  3.구독신청  |  4.데모체험  |  5.지역콜택시조회");
                    System.out.println("====================================================================");
                    System.out.print("선택 > ");
                    int choose = scan.nextInt();
                    if( choose == 1 ) uv.signUp();
                    else if( choose == 2 ) uv.logIn();
                    else if( choose == 3 ) uv.subscribeRequest();
                    else if( choose == 4 ) uv.siteManasers();
                    else if( choose == 5 ) uv.taxiList();
                    else System.out.println( "\n[경고] 올바른 메뉴(숫자)를 입력하세요.\n" );
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

    // ★☆★☆ [본사]사용자단: 회원(구독x , (구독 O / 사이트 x)) 로그인 화면
    public void user(){
        System.out.println("============== MY_CALL (본사 ★ 지역콜택시 플랫폼) =================");
        System.out.println("1.정보수정 | 2.로그아웃 | 3.구독신청 | 4.데모체험  |  5.지역콜택시조회");
        System.out.println("6.구독현황 | 7.회원탈퇴"); // | 8.구독취소
        System.out.println("================================================================");
        System.out.print("선택 > ");
        int choose = scan.nextInt();
        if( choose == 1 ) uv.updateProfile();
        else if( choose == 2 ) uv.signOut();
        else if( choose == 3 ) uv.subscribeRequest();
        else if( choose == 4 ) uv.siteManasers();
        else if( choose == 5 ) uv.taxiList();
        else if( choose == 6 ) uv.subscribeState();
        else if( choose == 7 ) uv.withdrawUser();
        else if( choose == 8 ) uv.subscribeCancle(); // 8.구독취소 : 구독중인 회원전용 메뉴
        else System.out.println("\n[경고] 올바른 메뉴(숫자)를 입력하세요.\n");
    }// func end

    //  ★☆★☆ [본사]사용자단: 회원(구독 O / 사이트 O) 로그인 화면
    public void existSite(){
        System.out.println("============== MY_CALL (본사 ★ 지역콜택시 플랫폼) =================");
        System.out.println("1.정보수정 | 2.로그아웃 | 3.구독신청 | 4.내사이트가기  |  5.지역콜택시조회");
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
    }// func end

}//class end
