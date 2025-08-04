package project;

import project.model.dao.ConnectDB;
import project.view.TotalView;

public class AppStart {
    public static void main(String[] args) {

        //ConnectDB.getInstance().connectDB(); // DB연동 테스트(김진숙)_250803
        TotalView.getInstance().index(); // 본사 사용자단 메인공통화면(view)

    }
}//class end

