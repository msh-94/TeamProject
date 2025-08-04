package project.view; // 패키지명

public class UserView { // class start
    // 싱글톤
    private UserView(){}
    private static final UserView instance = new UserView();
    public static UserView getInstance(){return instance;}

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
}// class end
