package project.view; // 패키지명

import project.controller.*;
import project.model.dto.*;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

import static project.controller.CompanyController.currentCno;
import static project.controller.Member_HeadController.currentMno;
import static project.controller.Member_SubController.currentSubMno;
import static project.controller.PlanController.currentPno;
import static project.model.dao.LogDao.formatter;
import static project.model.dao.LogDao.toDay;
import static project.view.TotalView.scan;

public class UserView { // class start
    // 싱글톤
    private UserView(){}
    private static final UserView instance = new UserView();
    public static UserView getInstance(){return instance;}

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
        System.out.println("\n1.회원가입");
        System.out.print(" - 회원유형(1.일반회원 2.택시기사 3.사업자) :  "); int mCategory = scan.nextInt();
        System.out.print(" - 아이디: "); String mId = scan.next();
        System.out.print(" - 비밀번호: "); String mPwd = scan.next();
        System.out.print(" - 이름: "); String mName = scan.next();
        System.out.print(" - 전화번호: "); String mPhone = scan.next();

        int result = mhc.signUp(mCategory,mId,mPwd,mName,mPhone);
        if(result==1){
            System.out.println("[안내] 회원가입이 완료되었습니다.\n");
        }else if(result==2){
            System.out.println("[경고] 이미 존재하는 아이디 입니다.");
        }else {
            System.out.println("[경고] 회원가입 실패. 다시 시도 해주세요.\n");
        } //if end
    }//func end

    // 1.2.로그인
    public void logIn(){
        System.out.println("\n2.로그인");
        System.out.print(" - 아이디 : ");     String mId = scan.next();
        System.out.print(" - 비밀번호 : ");    String mPwd = scan.next();
        Member_HeadDto result = mhc.logIn(mId,mPwd);
        CompanyDto dto = cc.siteManaser(result.getMno());
        currentCno = dto.getCno();
        if (result.getmId() == null){
            System.out.println("\n[경고] 존재하지 않는 계정입니다.\n");
        }else {
            System.out.printf("\n[안내] 반갑습니다 %s님\n",result.getmName());
        }// if end
    }//func end

    // 1.3.구독신청
    public void subscribeRequest(){

        ArrayList<PlanDto> list = pc.planList();
        boolean bool = !currentPno.isEmpty(); // 중지플랜이 존재하면

        if( currentMno != 0 ) { // 본사 로그인 이후, 구독신청 가능
            System.out.println("\n3.구독신청");
            /* 구독플랜조회 리스트 */ //planList();
            ArrayList<PlanDto> planDtos = pc.planList();
            System.out.println("----------------------------------");
            LogDto mLog = new LogDto();
            mLog = lc.subscribeState( currentMno ); // 구독로그가 있는 회원
            DecimalFormat formatter = new DecimalFormat("#,###");
            if( mLog.getEndDate() != null  ){
                for (PlanDto dto : planDtos) {
                    String moneyFormatted = formatter.format(dto.getpMoney());
                    if (dto.getPno() == 1) continue;
                    if (bool){ if( currentPno.contains( dto.getPno() ) ){ continue; }  }// if end
                    System.out.printf("  %d) %-6s (%d개월/%s원)\n", dto.getPno(), dto.getpName(), dto.getpDate(), moneyFormatted);
                }//for end
            }else{
                for (PlanDto dto : planDtos) {
                    String moneyFormatted = formatter.format(dto.getpMoney());
                    if (bool){ if( currentPno.contains( dto.getPno() ) ){ continue;  } }// if end
                    System.out.printf("  %d) %-6s (%d개월/%s원)\n", dto.getPno(), dto.getpName(), dto.getpDate(), moneyFormatted);
                }//for end
            }
            System.out.println("----------------------------------");
            System.out.print("✔️ 선택 > ");
            int choose = scan.nextInt();

            PlanDto selectPlan = null; // 회원이 선택한 구독플랜 정보 1개 가져오기
            for (PlanDto dto : planDtos) { // 사용자가 선택한 플랜번호 찾기
                if (dto.getPno() == choose) { selectPlan = dto; break; }
            }
            if (selectPlan != null) {
                System.out.printf(" - 신청하신 구독: %d)%s(%d개월/%d원)\n", selectPlan.getPno(), selectPlan.getpName(), selectPlan.getpDate(), selectPlan.getpMoney());
                //System.out.printf("구독종료일(예정):  ");
            }else{
                System.out.println("올바른 구독플랜 숫자를 선택하세요!");
            }
            String cName=""; String area=""; String service = "";
            if( mLog.getEndDate() == null  ) { // 구독로그가 없는 회원
                System.out.print("\n - 콜택시사이트명 : ");
                cName = scan.next();
                System.out.print(" - 서비스 지역 : ");
                area = scan.next();
                scan.nextLine();
                System.out.print(" - 서비스 내용 : ");
                service = scan.nextLine();
            }
            Map<String, Object> subscribeInfo = new HashMap<>();
            subscribeInfo.put("mno", currentMno);
            subscribeInfo.put("pno", selectPlan.getPno());
            subscribeInfo.put("pDate", selectPlan.getpDate());
            subscribeInfo.put("cName", cName);
            subscribeInfo.put("area", area);
            subscribeInfo.put("service", service);
            boolean result = lc.subscribeRequest(subscribeInfo);
            if( result ){ System.out.printf("\n[안내] %s 구독신청되었습니다.\n\n", selectPlan.getpName() );
            }else { System.out.println("\n[경고] 올바른 정보를 입력하세요.\n"); }// if end
        }else{ System.out.println("\n[안내] 로그인 이후, 구독신청 가능합니다.\n"); }// if end
    }//func end

    // 1.4.데모체험
    public void siteManasers(){
        for ( ; ; ){
            try {
                System.out.println("--------------------------------------------------------------------------");
                System.out.println("< 데모체험 >  1.관리자단(ADMIN)     2. 사용자단(USER)     3. 본사바로가기");
                System.out.println("--------------------------------------------------------------------------");
                System.out.print("✔️ 선택 > ");
                int choose = scan.nextInt();
                if (choose == 1) {
                    System.out.println("관리자단");
                } else if (choose == 2) {
                    System.out.println("사용자단");
                } else if (choose == 3) {
                    break;
                }else {
                    System.out.println("\n[경고] 존재하지 않는 번호입니다.\n");
                }// if end
            }catch (InputMismatchException e) {
                System.out.println("\n[경고] 입력타입 불일치! 숫자를 입력하세요." + e + "\n");
                scan = new Scanner(System.in);//입력객체 초기화
            } catch (Exception e) {
                System.out.println( "\n[오류] 개발팀 문의 " + e + "\n" );
            }// catch end
        }// for end
    }//func end

    // 1.5.지역콜택시조회
    public void taxiList(){
        System.out.println("\n5.지역콜택시조회");
        ArrayList<CompanyDto> result = cc.taxiList();
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("No     지역    콜택시사이트명          서비스내용");
        System.out.println("---------------------------------------------------------------------------");
        for(CompanyDto cdto : result){
            System.out.printf("%d\t  [%s]\t   %s\t     %s\t \n", cdto.getCno(), cdto.getArea(), cdto.getcName(), cdto.getService());
        }
        System.out.println("--------------------------------------------------------------------------\n");
    }//func end

    /* (2) [본사]사용자단: 회원(구독X/구독O) 로그인 화면 -------------------------------------------------------------------*/
    // 2.1.정보수정
    public void updateProfile(){
        System.out.println("\n1.정보수정");
        System.out.print(" - 비밀번호 : ");    String mPwd = scan.next();
        System.out.print(" - 수정할 비밀번호 : ");    String mPwd1 = scan.next();
        System.out.print(" - 수정할 전화번호 : ");    String mPhone = scan.next();
        int result = mhc.updateProfile(mPwd,mPhone,mPwd1);
        if (result == 1){
            System.out.println("[안내] 정보수정이 완료되었습니다.\n");
        }else if (result == 2){
            System.out.println("[경고] 비밀번호가 불일치합니다.\n");
        }else {
            System.out.println("[오류] 개발팀 문의");
        }// if end
    }//func end

    // 2.2.로그아웃
    public void signOut(){
        if(currentMno > 0){
            currentMno = 0;
            currentCno = 0;
            System.out.println("\n로그아웃 되었습니다.\n");
        }else {
            System.out.println("[오류] 개발팀 문의 ");
        }
    }//func end

    // 2.4 내사이트가기
    public void siteManaser(){
        CompanyDto result = cc.siteManaser(currentMno);
        for ( ; ;){
            try {
                System.out.println("--------------------------------------------------------------------------");
                System.out.printf("< %s >    1.관리자단(ADMIN)      2. 사용자단(USER)      3. 본사바로가기\n", result.getcName());
                System.out.println("--------------------------------------------------------------------------");
                System.out.print("선택 > ");
                int choose = scan.nextInt();
                if (choose == 1) {
                    TotalView.getInstance().subAdmin();
                } else if (choose == 2) {
                    TotalView.getInstance().subUser();
                } else if (choose == 3) {
                    currentCno = 0;
                    break;
                }else {
                    System.out.println("\n[경고] 존재하지 않는 번호입니다.\n");
                }// if end
            }catch (InputMismatchException e) {
                System.out.println("\n[경고] 입력타입 불일치! 숫자를 입력하세요." + e + "\n");
                scan = new Scanner(System.in);//입력객체 초기화
            } catch (Exception e) {
                System.out.println( "\n[오류] 개발팀 문의 " + e + "\n" );
            }// catch end
        }// for end
    }// func end

    // 2.6.구독현황
    public void subscribeState(){
        System.out.println("\n6.구독현황");

        LogDto result = lc.subscribeState( currentMno );

        if( result.getEndDate() != null  ){ // && result.getEndDate() == toDay
            ArrayList<PlanDto> planDtos = pc.planList();
            PlanDto selectPlan = null;
            for (PlanDto dto : planDtos) {
                if (result.getPno()== dto.getPno() ) {
                    selectPlan = dto;
                    break;
                }
            }
            if( selectPlan != null ){
                System.out.printf(" - 구독번호: %s\n", result.getLogno());
                System.out.printf(" - 구독플랜명: %s\n", selectPlan.getpName());
                System.out.printf(" - 구독기간: %s개월\n", selectPlan.getpDate());
                System.out.printf(" - 구독금액: %s원\n", selectPlan.getpMoney());
                System.out.printf(" - 구독신청일: %s\n", result.getAddDate());
                System.out.printf(" - 구독종료일: %s\n\n", result.getEndDate());
            }else { System.out.println(" 구독중인 플랜이 없습니다.\n"); }
        }else{
            System.out.println(" 구독중인 플랜이 없습니다.\n");
        }
    }//func end

    // 2.7.회원탈퇴
    public void withdrawUser(){
        System.out.println("\n7.회원탈퇴\n");
        boolean result = mhc.withdrawUser();
        if (result){
            System.out.println("[안내] 회원탈퇴 되었습니다.\n");
            currentMno = 0;
        }else {
            System.out.println("[오류] 개발팀 문의\n");
        }// if end
    }//func end


    // 2.8. 구독취소
    public void subscribeCancel(){
        System.out.println("\n8.구독취소\n");
        boolean result = lc.subscribeCancel( currentMno );
        if( result  ) {
            System.out.println("구독 취소되었습니다.\n");
        } else {
            System.out.println("[오류] 관리자 문의\n");
        }
    }//func end

    // 2.9.사용자 메뉴 변경(구독취소 메뉴)
    public String cancelMenu(){
        LogDto mLog = lc.subscribeState(currentMno);
        if (mLog != null && mLog.getEndDate() != null) {
            LocalDate endDate = LocalDate.parse(mLog.getEndDate(), formatter);
            if (toDay.isAfter(endDate)) {
                return "";  // 구독이 끝났다면 구독취소 메뉴 없음
            } else {
                return "8.구독취소";  // 구독중이면 메뉴 표시
            }
        } else {//구독로그 없거나 비회원
            return "";
        }
    }// func end

    // 2.10.사용자 메뉴 변경(구독취소 메뉴)
    public String cancelMenu2(){
        LogDto mLog  = lc.subscribeState(currentMno);
        // System.out.println( ": 구독취소 메뉴:" + mLog );
        if (mLog != null && mLog.getEndDate() != null) {
            LocalDate endDate = LocalDate.parse(mLog.getEndDate(), formatter);
            if (toDay.isAfter(endDate)) {
                return "4.데모체험";  // 구독이 끝났다면, 구독취소 메뉴 없음
            } else {
                return "4.내사이트가기";  // 구독중이면 메뉴 표시
            }
        } else {//구독로그 없거나 비회원
            return "4.데모체험";
        }
    }// func end

    // 하위사이트 회원가입
    public void subSignUp(){
        System.out.println("\n1.회원가입");
        System.out.print(" - 회원유형(1.일반회원 2.택시기사 3.사업자) :  "); int mCategory = scan.nextInt();
        System.out.print(" - 아이디: "); String mId = scan.next();
        System.out.print(" - 비밀번호: "); String mPwd = scan.next();
        System.out.print(" - 이름: "); String mName = scan.next();
        System.out.print(" - 전화번호: "); String mPhone = scan.next();
        int result = msc.subSignUp(mCategory,mId,mPwd,mName,mPhone);
        if(result==1){
            System.out.println("[안내] 회원가입이 완료되었습니다.\n");
        }else if(result==2){
            System.out.println("[경고] 이미 존재하는 아이디 입니다.");
        }else {
            System.out.println("[경고] 회원가입 실패. 다시 시도 해주세요.\n");
        } //if end
    }//func end

    // 하위사이트 로그인 기능
    public void subLogIn(){
        System.out.println("\n2.로그인");
        System.out.print(" - 아이디 : ");     String mId = scan.next();
        System.out.print(" - 비밀번호 : ");    String mPwd = scan.next();
        Member_SubDto result = msc.subSignIn(mId,mPwd);
        if (result.getmId() == null){
            System.out.println("\n[경고] 존재하지 않는 계정입니다.\n");
        }else {
            System.out.printf("\n[안내] 반갑습니다 %s님\n",result.getmName());
        }// if end
    }//func end

    // 하위사이트 로그아웃 기능
    // 2.2.로그아웃
    public void subSignOut(){
        if(currentSubMno > 0){
            currentSubMno = 0;
            System.out.println("\n로그아웃 되었습니다.\n");
        }else {
            System.out.println("[오류] 개발팀 문의 ");
        }// if end
    }//func end

}// class end