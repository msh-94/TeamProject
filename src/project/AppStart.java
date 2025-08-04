package project;

import project.model.dao.ConnectDB;
import project.model.dao.LogDao;
import project.model.dto.LogDto;
import project.view.TotalView;

public class AppStart {
    public static void main(String[] args) {
        
        // LogDao.getInstance().subscribeState( 2 );// 구독현황(Log) 테스트(김진숙)_250804
        // LogDao.getInstance().subscribeCancle( 2 );// 구독취소(Log) 테스트(김진숙)_250804
        // ConnectDB.getInstance().connectDB(); // DB연동 테스트(김진숙)_250803
        TotalView.getInstance().index(); // 본사 사용자단 메인공통화면(view)

    }
}//class end