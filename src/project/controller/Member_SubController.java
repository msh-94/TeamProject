package project.controller; // 패키지명

public class Member_SubController { // class start
    // 싱글톤
    private Member_SubController(){}
    private static final Member_SubController instance = new Member_SubController();
    public static Member_SubController getInstance(){ return instance; }
}// class end
