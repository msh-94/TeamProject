package project.view; // 패키지명

public class AdminView {// class start
    // 싱글톤
    private AdminView(){}
    private static final AdminView instance = new AdminView();
    public static AdminView getInstance(){return instance;}

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
}// class end
