package project.controller; // 패키지명

public class Member_HeadController { // class start
    // 싱글톤
    private Member_HeadController(){}
    private static final Member_HeadController instance = new Member_HeadController();
    public static Member_HeadController getInstance(){ return instance; }
}// class end
