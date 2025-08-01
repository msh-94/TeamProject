package project.controller; // 패키지명

public class LogController { // class start
    // 싱글톤
    private LogController(){}
    private static final LogController instance = new LogController();
    public static LogController getInstance(){ return instance; }
}// class end
