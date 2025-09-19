package project.view; // 패키지명

import project.Container;
import project.controller.*;
import project.model.dto.Member_HeadDto;
import project.model.dto.Member_SubDto;
import project.model.dto.PlanDto;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static project.controller.PlanController.currentPno;

public class AdminView {// class start
    // 싱글톤
    //private AdminView(){}
    //private static final AdminView instance = new AdminView();
    //public static AdminView getInstance(){return instance;}
    //// 싱글톤 호출
    //private Member_HeadController mhc = Member_HeadController.getInstance();
    //private Member_SubController msc = Member_SubController.getInstance();
    //private PlanController pc = PlanController.getInstance();
    //private LogController lc = LogController.getInstance();
    //private CompanyController cc = CompanyController.getInstance();
    private final Container container = Container.getInstance();

    /* (3) [본사]관리자단: admin(시스템관리자) 로그인 화면 -----------------------------------------------------------------*/
    // 3.1.구독플랜 등록
    public void planAdd(){
        System.out.println("\n1.구독플랜 등록");
        System.out.print("- 구독플랜명: ");      String pName = TotalView.scan.next();
        System.out.print("- 구독기간(월단위): ");  int pDate = TotalView.scan.nextInt();
        System.out.print("- 금액(VAT포함가): ");  int pMoney = TotalView.scan.nextInt();
        boolean result = container.getPc().planAdd(pName,pDate,pMoney);
        if(result){
            System.out.println("[안내] 구독플랜이 등록되었습니다.\n");
        }else {
            System.out.println("[경고] 이미 존재하는 구독플랜명이 있습니다.\n");

        }//if end
    }//func end

    // 3.2.구독플랜 조회
    public void planList(){
        ArrayList<PlanDto> result = container.getPc().planList();
        String 노출여부 = "활성화";
        DecimalFormat formatter = new DecimalFormat("#,###");
        System.out.println("--------------------------------------------------------------------------------------------- ");
        System.out.printf("%-8s %-11s %-9s %s\n","No","구독플랜명","구독기간","금액(원)");
        System.out.println("--------------------------------------------------------------------------------------------- ");
        for(PlanDto dto : result){
            if (currentPno.contains(dto.getPno()))노출여부 = "비활성화";
            if (!currentPno.contains(dto.getPno())) 노출여부 = "활성화";
            String moneyFormatted = formatter.format(dto.getpMoney());
        System.out.printf("%d\t   %6s\t    %6d개월\t    %6s\t \n", dto.getPno(), dto.getpName(), dto.getpDate(), moneyFormatted,노출여부);
        }//for e
        System.out.print("플랜 활성화/비활성화/뒤로가기(1/2/3) : ");  int choose = TotalView.scan.nextInt();
        if (choose == 1){
            System.out.print("활성화 하실 플랜번호 : ");     int pno = TotalView.scan.nextInt();
            boolean check = container.getPc().planRestart(pno);
            if (check){
                System.out.println("[안내] 입력하신 플랜상품이 활성화 되었습니다.");
            }else {
                System.out.println("[경고] 존재하지 않는 플랜번호 입니다. ");
            }// if end
        }else if (choose == 2){
            System.out.print("비활성화 하실 플랜번호 : ");     int pno = TotalView.scan.nextInt();
            boolean check = container.getPc().planStop(pno);
            if (check){
                System.out.println("[안내] 입력하신 플랜상품이 비활성화 되었습니다.\n");
            }else {
                System.out.println("[경고] 존재하지 않는 플랜번호 입니다.\n");
            }// if end
        }else if (choose == 3){
            return;
        }
        else {
            System.out.println("[경고] 존재하지 않는 번호 입니다.\n");
        }// if end
    }//func end

    // 3.3.구독플랜 수정
    public void planEdit(){
        System.out.println("\n3.구독플랜 수정");
        System.out.print("- 수정할구독플랜 번호 : "); int pno = TotalView.scan.nextInt();
        System.out.print("- 구독플랜명 : "); String pName = TotalView.scan.next();
        System.out.print("- 구독기간(월단위) : "); int pDate = TotalView.scan.nextInt();
        System.out.print("- 금액(VAT포함가) : "); int pMoney = TotalView.scan.nextInt();
        int result = container.getPc().planEdit(new PlanDto(pno,pName,pDate,pMoney));  //플랜수정 호출
        if(result==1){
            System.out.println("[안내] 구독플랜이 수정되었습니다.\n");
        }else if(result==2){
            System.out.println("[경고] 구독중인 구독자가 있는 구독플랜은 수정이 불가합니다.\n");
        }//if end
    }//func end

    // 3.4.구독플랜 삭제
    public void planDelete(){
        ArrayList<PlanDto> result = container.getPc().planList();
        System.out.println("--------------------------------------------------------------------------------------------- ");
        System.out.printf("%-8s %-11s %-9s %s\n","No","구독플랜명","구독기간","금액(원)");
        System.out.println("--------------------------------------------------------------------------------------------- ");
        for(PlanDto dto : result) {
            System.out.printf("%d\t   %6s\t    %6d개월\t    %6s\t \n",
                    dto.getPno(), dto.getpName(), dto.getpDate(), dto.getpMoney());
        }// for end
        System.out.print(" - 삭제할 플랜번호: "); int pno = TotalView.scan.nextInt();
        boolean resultDelete = container.getPc().planDelete(pno);
        if(resultDelete){
            System.out.println("[안내] 구독플랜이 삭제되었습니다.\n");
        }else{
            System.out.println("[오류] 개발팀 문의\n");
        }
    }//func end

    // 3.5.회원목록 조회
    public void userList(){
            System.out.println("\n7.구독신청 내역조회");
            System.out.println("----------------------------------------------------------------------------------");
            System.out.printf("%s\t%10s\t%7s\t%9s\t%11s\t%9s \n","No","회원유형","아이디","이름","휴대폰번호","가입일");
            System.out.println("----------------------------------------------------------------------------------");
            ArrayList<Member_HeadDto> result = container.getMhc().userList();
            String memberType;
            for(Member_HeadDto dto : result) {
                int Category = dto.getmCategory();
                if (Category == 1) {
                    memberType = "일반회원";
                } else if (Category == 2) {
                    memberType = "택시기사";
                } else if (Category == 3) {
                    memberType = "사업자";
                } else {
                    memberType = "없는유형";
                }
                System.out.printf("%d\t%10s\t%10s\t%10s\t%10s\t%10s \n",
                        dto.getMno(), memberType, dto.getmId(),
                        dto.getmPhone(), dto.getmName(), dto.getmDate());
            }//for end
    }//func end

    // 3.6.현재구독중인 회원목록 조회
    public void planUserList(){
        ArrayList<Map<String,Object>> result = container.getMhc().planUserList();
        int no = 1;
        System.out.println("------------------------------------------- 현재 구독중인 회원  ------------------------------------------- ");
        System.out.println("---------------------------------------------------------------------------------------------------------- ");
        System.out.printf("%-3s %-6s %-12s %-10s %-8s %-12s %-13s %-11s %-13s\n",
                "No","지역","구독플랜명","아이디","구독자명","회원유형","휴대폰번호","최초구독일","종료일");
        System.out.println("---------------------------------------------------------------------------------------------------------- ");
        for (int i = 0; i < result.size(); i++){
            Map<String,Object> map = result.get(i);
            Object a = map.get("유형");
            if (a.equals(1)){ a = "일반회원"; }
            else if (a.equals(2)){ a = "택시기사"; }
            else if (a.equals(3)){ a = "사업자"; }
            System.out.printf("%-3d %-6s %-12s %-14s %-8s %-10s %-17s %-13s %-13s\n",no++,map.get("지역"),map.get("플랜이름"),
                    map.get("아이디"),map.get("이름"),a,map.get("핸드폰번호"),map.get("시작일"),map.get("종료일"));
        }// for end
    }//func end

    // 3.6.구독만료된 회원목록 조회
    public void planEndUserList(){
        ArrayList<Map<String,Object>> result = container.getMhc().planEndUserList();
        int no = 1;
        System.out.println("\n\n------------------------------------------- 구독 만료된 회원  ------------------------------------------- ");
        System.out.println("---------------------------------------------------------------------------------------------------------- ");
        System.out.printf("%-3s %-6s %-12s %-11s %-8s %-12s %-13s %-11s %-11s\n",
                "No","지역","구독플랜명","아이디","구독자명","회원유형","휴대폰번호","최초구독일","종료일");
        System.out.println("---------------------------------------------------------------------------------------------------------- ");
        for (int i = 0; i < result.size(); i++){
            Map<String,Object> map = result.get(i);
            Object a = map.get("유형");
            if (a.equals(1)){ a = "일반회원"; }
            else if (a.equals(2)){ a = "택시기사"; }
            else if (a.equals(3)){ a = "사업자"; }
            System.out.printf("%-3d %-6s %-12s %-14s %-8s %-10s %-17s %-13s %-13s\n",no++,map.get("지역"),map.get("플랜이름"),
                    map.get("아이디"),map.get("이름"),a,map.get("핸드폰번호"),map.get("시작일"),map.get("종료일"));
        }// for end
    }// func end

    // 3.7.구독신청 내역조회
    public void subscribeList() {
        System.out.println("\n7.구독신청 내역조회");
        ArrayList<LinkedHashMap<String, Object>> result = container.getLc().subscribeList();

        System.out.println("----------------------------------------------------------------------------");
        if (!result.isEmpty()) { // 헤더(key) 출력
            LinkedHashMap<String, Object> firstRow = result.get(0);
            for (String key : firstRow.keySet()) {
                System.out.printf("%-9s", key);
            }// for end
            System.out.println();
        }// if end
        System.out.println("----------------------------------------------------------------------------");
        for (LinkedHashMap<String, Object> logUser : result) { // 데이터(value) 출력
            for (Object value : logUser.values()) {
                String str = (value == null) ? "" : value.toString();
                System.out.printf("%-11s", str);
            }// for end
            System.out.println();
        }// for end
        System.out.println("----------------------------------------------------------------------------\n");
    }// func end

    // 하위사이트 회원목록조회
    public void subUserList(){
        List<Member_SubDto> result = container.getMsc().subUserList();
        String memberType;
        System.out.println("\n1.회원목록");
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("No        회원유형       아이디       이름       휴대폰번호       가입일");
        System.out.println("----------------------------------------------------------------------------");
        for(Member_SubDto dto : result) {
            int Category = dto.getmCategory();
            if (Category == 1) {
                memberType = "일반회원";
            } else if (Category == 2) {
                memberType = "택시기사";
            } else if (Category == 3) {
                memberType = "사업자";
            } else {
                memberType = "없는유형";
            }// if end
            System.out.printf("%d\t%7s\t%7s\t%7s\t%10s\t%10s \n",
                    dto.getMno(), memberType, dto.getmId(),
                    dto.getmName(), dto.getmPhone(), dto.getmDate());
        }// for end
    }// func end

}// class end