package project;


import project.view.TotalView;

public class AppStart {
    public static void main(String[] args) {
        Container.find("project"); // 스캔 시작할 패키지명
        final TotalView totalView = Container.getBean(TotalView.class);
        totalView.index();
    } // 푸시용1
}//class end