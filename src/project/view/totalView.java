package project.view; // 패키지명

public class totalView { // class start
    // 싱글톤
    private totalView(){}
    private static final totalView instance = new totalView();
    public static totalView getInstance(){ return instance; }
}// class end
