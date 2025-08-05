package project.view; // 패키지명

import project.controller.*;
import project.model.dto.PlanDto;

import java.util.ArrayList;
import java.util.Map;

public class AdminView {// class start
    // 싱글톤
    private AdminView(){}
    private static final AdminView instance = new AdminView();
    public static AdminView getInstance(){return instance;}
    // 싱글톤 호출
    private Member_HeadController mhc = Member_HeadController.getInstance();
    private Member_SubController msc = Member_SubController.getInstance();
    private PlanController pc = PlanController.getInstance();
    private LogController lc = LogController.getInstance();
    private CompanyController cc = CompanyController.getInstance();

    /* (3) [본사]관리자단: admin(시스템관리자) 로그인 화면 -----------------------------------------------------------------*/
    // 3.1.구독플랜 등록
    public void planAdd(){
        System.out.println("1.구독플랜 등록");
        System.out.println("- 구독플랜명:");      String pName = TotalView.scan.next();
        System.out.println("- 구독기간(월단위)");  int pDate = TotalView.scan.nextInt();
        System.out.println("- 금액(VAT포함가)");  int pMoney = TotalView.scan.nextInt();
        boolean result = pc.planAdd(pName,pDate,pMoney);
        if(result){
            System.out.println("[안내] 구독플랜이 등록되었습니다.");
        }else {
            System.out.println("[경고] 이미 존재하는 구독플랜명이 있습니다. ");
        }//if end
    }//func end

    // 3.2.구독플랜 조회
    public void planList(){
        ArrayList<PlanDto> result = pc.planList();
        System.out.println("--------------------------------------------------------------------------------------------- ");
        System.out.println("No     구독플랜명     구독기간     금액(원)");
        System.out.println("--------------------------------------------------------------------------------------------- ");

    }//func end

    // 3.3.구독플랜 수정
    public void planEdit(){
        System.out.println("\n4.구독플랜 수정\n");
    }//func end

    // 3.4.구독플랜 삭제
    public void planDelete(){
        System.out.println("\n4.구독플랜 삭제\n");
    }//func end

    // 3.5.회원목록 조회
    public void userList(){
        System.out.println("\n5.회원목록 조회\n");
    }//func end

    // 3.6.현재구독중인 회원목록 조회
    public void planUserList(){
        ArrayList<Map<String,Object>> result = mhc.planUserList();
        int no = 1;
        System.out.println("------------------------------------------- 현재 구독중인 회원  ------------------------------------------- ");
        System.out.println("---------------------------------------------------------------------------------------------------------- ");
        System.out.println("No 지역 구독플랜명   아이디  구독자명  회원유형       휴대폰번호       최초구독일       종료일");
        System.out.println("---------------------------------------------------------------------------------------------------------- ");
        for (int i = 0; i < result.size(); i++){
            Map<String,Object> map = result.get(i);
            Object a = (Integer)map.get("유형");
            if (a.equals(1)){ a = "일반회원"; }
            if (a.equals(2)){ a = "택시기사"; }
            if (a.equals(3)){ a = "사업자"; }
            System.out.printf("%d\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n",no++,map.get("지역"),map.get("플랜이름"),
                    map.get("아이디"),map.get("이름"),a,map.get("핸드폰번호"),map.get("시작일"),map.get("종료일"));
        }// for end
    }//func end

    // 3.6.구독만료된 회원목록 조회
    public void planEndUserList(){
        ArrayList<Map<String,Object>> result = mhc.planEndUserList();
        int no = 1;
        System.out.println("\n\n------------------------------------------- 구독 만료된 회원  ------------------------------------------- ");
        System.out.println("---------------------------------------------------------------------------------------------------------- ");
        System.out.println("No 지역 구독플랜명   아이디  구독자명  회원유형       휴대폰번호       최초구독일       종료일");
        System.out.println("---------------------------------------------------------------------------------------------------------- ");
        for (int i = 0; i < result.size(); i++){
            Map<String,Object> map = result.get(i);
            Object a = (Integer)map.get("유형");
            if (a.equals(1)){ a = "일반회원"; }
            if (a.equals(2)){ a = "택시기사"; }
            if (a.equals(3)){ a = "사업자"; }
            System.out.printf("%d\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n",no++,map.get("지역"),map.get("플랜이름"),
                    map.get("아이디"),map.get("이름"),a,map.get("핸드폰번호"),map.get("시작일"),map.get("종료일"));
        }// for end
    }// func end


    // 3.7.구독신청 내역조회
    public void subscribePrint(){
        System.out.println("\n7.구독신청 내역조회\n");
    }//func end
}// class end