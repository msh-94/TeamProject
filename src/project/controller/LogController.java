package project.controller; // 패키지명

import project.model.dao.LogDao;
import project.model.dto.LogDto;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* 구독신청(Log table) 관련 */
public class LogController {
    // 싱글톤
    private LogController(){}
    private static final LogController instance = new LogController();
    public static LogController getInstance(){ return instance; }

    // 싱글톤 호출
    private LogDao logDao = LogDao.getInstance();

    /* ======================================== ★ 단위기능 ★ ============================================== */
    // 1. 구독신청(본사 사용자단)
    public boolean subscribeRequest( Map <String, Object> subscribeInfo ){
        boolean result = logDao.subscribeRequest( subscribeInfo );
        return result;
    }

    // 2. 구독현황(본사 사용자단)
    public LogDto subscribeState( int mno ){
        //LogDto result = new LogDto();
        LogDto result = logDao.subscribeState( mno );
        return result;
    }//func end

    // 3. 구독취소(본사 사용자단)_로그를 삭제 하지 않고 종료일을 구독취소일(당일)로 변경
    public boolean subscribeCancel( int mno ){
        boolean result = logDao.subscribeCancel( mno );
        return result;
    }//func end

    // 4. 구독신청 내역조회(본사 관리자)
    public ArrayList<LinkedHashMap<String,Object>> subscribeList(){
        ArrayList<LinkedHashMap<String,Object>> result = logDao.subscribeList( );
        return result;
    }//func end

}// class end
