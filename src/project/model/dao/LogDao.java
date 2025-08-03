package project.model.dao;

import project.model.dto.CompanyDto;
import project.model.dto.LogDto;
import java.util.ArrayList;

// 구독신청(Log table) 관련
public class LogDao {
    // 싱글톤
    private LogDao(){ connectDB.connectDB(); }
    private static final LogDao instance = new LogDao();
    public static LogDao getInstance(){ return instance; }

    // 싱글톤 호출
    private ConnectDB connectDB = ConnectDB.getInstance();

    /* ======================================== ★ 단위기능 ★ ============================================== */

    /* [1] 본사 > 사용자단 -----------------------------------------------------------------------------------*/
    // 1-1. 구독신청
    public boolean subscribeRequest( int mno , int pno , CompanyDto companyDto ){
        return true;
    }//func end

    // 1-2. 구독현황
    public LogDto subscribeState( int mno ){
        LogDto logDto = new LogDto();
        return logDto;
    }//func end

    // 1-3. 구독취소
    public boolean subscribeCancle( int mno ){
        return true;
    }//func end

    /* [2] 본사 > 관리자단 -----------------------------------------------------------------------------------*/
    // 2-1. 구독신청 내역조회
    public ArrayList<LogDto> subscribelList(){
        ArrayList<LogDto> logDtos = new ArrayList<>();
        return logDtos;
    }//func end

}//class end