package project.view; // 패키지명

public class TotalView { // class start
    // 싱글톤
    private TotalView(){}
    private static final TotalView instance = new TotalView();
    public static TotalView getInstance(){ return instance; }
}// class end
