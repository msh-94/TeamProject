package project;

import project.model.dao.LogDao;
import project.view.TotalView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AppStart {
    public static void main(String[] args) {
        
        //LogDao.getInstance().subscribeState( 2 );// 구독현황(Log) 테스트(김진숙)_250804
        //LogDao.getInstance().subscribeCancle( 2 );// 구독취소(Log) 테스트(김진숙)_250804
        //LogDao.getInstance().subscribelList();
        Map<String, Object> testData = new HashMap<>();
        testData.put("mno", 12);
        testData.put("pno", 1);
        testData.put("pDate", 6);
        testData.put("cName", "콜콜콜");
        testData.put("area", "부삼");
        testData.put("service", "좋아요");
        boolean result = LogDao.getInstance().subscribeRequest( testData );
        System.out.println( result );




        TotalView.getInstance().index(); // 본사 사용자단 메인공통화면(view)


    }//main end
}//class end