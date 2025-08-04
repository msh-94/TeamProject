package project.model.dao;

import project.model.dto.CompanyDto;
import project.model.dto.LogDto;

import java.sql.PreparedStatement;
import java.util.ArrayList;

import static project.controller.Member_HeadController.currentMno;

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
    public boolean subscribeRequest( int pno , int mno, CompanyDto companyDto ){
//        try {
              // 1)구독 최초신청/무료체험 : log 테이블 > 로그인한 currentMno(mno)가 존재하지 않은 경우
              // --> 구독로그 insert

              // 2)구독종료 이후, 구독 재신청 : log 테이블 > 로그인한 currentMno(mno)가 이미 존재하지만,
              //   해당 mno의 구독레코드 중 마지막 구독종료일이 오늘날짜 기준 이전인 경우
              // --> 구독로그 insert

              // 3)같은 구독플랜 재신청(기간연장/갱신: 기존 구독기간에 + 구독기간 추가) : log 테이블 > 로그인한 currentMno(mno)가 이미 존재하고,
              //   플랜번호(pno)가 같은경우 현재 구독종료일이 오늘날짜 기준 이후이지만, 서비스 종료일이 다가와서 같은 플랜 구독 연장을 원하는 경우
              // --> 구독로그 insert

              // 4)다른 구독플랜으로 변경(기존 구독기간에 변경된 구독플랜 기간 추가) : log 테이블 > 로그인한 currentMno(mno)가 이미 존재하고,
              //   현재 구독종료일이 오늘날짜 기준 이후이지만 플랜번호(pno)가 다른경우
              // --> 구독로그 insert

//            // 1.SQL 작성

              //String sql = "insert into log( pno, mno, endDate ) values( ?,? ,? )"; // 로그 기록
//            // 2.SQL 기재
              //PreparedStatement ps = connectDB.getConn().prepareStatement( sql );
//            // 3.SQL 매개변수 대입
              //LogDto logDto = new LogDto();
              //ps.setString(1, logDto.getMno() );
//
//            // 4.SQL 실행
//            int count = ps.executeUpdate(); // 등록/수정/삭제 쿼리는 등록 횟수로 실행 결과값 판단.
//            // 5.SQL 실행결과에 따른 로직/리턴/반환
//            if( count >= 1 ) return true;
//            else return false;
//        }catch ( Exception e ){System.out.println( e ); }
        return false; // 예외 발생시 저장실패.
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