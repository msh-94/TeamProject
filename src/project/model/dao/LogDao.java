package project.model.dao;

import project.model.dto.CompanyDto;
import project.model.dto.LogDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
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
        // 1) 최초 구독신청 : 본사 구독신청을 1번도 안한 경우
            // log 테이블 > mno(currentMno) 없는 경우 -->log 레코드 insert
            // #.case : (기존회원: 3)조나단 : fff_dd , 2025-06-10)
            // insert into log( pno , mno , addDate , endDate ) values( 1 , 12 , '2025-08-04' , '2025-09-04');
//            CompanyDto companyDto1 = new CompanyDto();
//            ArrayList<LogDto> logDtoList = new ArrayList<LogDto>();
//            try {
//                // 01. 로그 테이블 > mno 존재여부 순회( order by endDate )
//                    // 1.SQL 작성
//                    String sql =  "select * from log where mno = ? order by endDate";
//
//                    //  2. SQL 기재
//                    PreparedStatement ps = connectDB.getConn().prepareStatement(sql);
//
//                    // 3. SQL 매개변수 대입
//                    ps.setInt(1, mno);
//
//                    // 4. SQL 실행
//                    ResultSet rs = ps.executeQuery();
//
//                    // 5. SQL 실행결과 로직/리턴/확인
//                    while ( rs.next() ){
//                        int logno = rs.getInt("logno");
//                        pno = rs.getInt("pno");
//                        mno = rs.getInt("mno");
//                        String addDate = rs.getString("addDate");
//                        String endDate = rs.getString("endDate");
//                        LogDto logDto = new LogDto( logno, pno, mno, addDate, endDate ); // LogDto 객체에 SQL 속성값 대입
//                        logDtoList.add( logDto ); // Dto객체를 자바 리스트에 추가.
//                    } //while end
//                return true;
//            }catch ( Exception e ){System.out.println( e ); }
//             System.out.println(  logDtoList );

           // 02. 구독플랜 테이블 > pno 조회하여 해당하는 pDate 값 가져오기
           // 03. endDate + pDate 월수 더한 후 로그 테이블 insert


        // 2) 구독종료 후, 구독신청 : 베이직
        // log 테이블 > mno(currentMno) 있지만 레코드 내역 중 종료일(endDate)이 오늘 이전인 경우 -->log 레코드 insert
        // #.case : (기존회원: 3)조나단 : fff_dd , 2025-06-10)

        // 3) 같은플랜 구독연장 : 이미 베이직 상품 구독 중인데,추가 신청(기간 연장 + 6개월)
        // log 테이블 > mno(currentMno) 있지만 레코드 내역 중 마지막 종료일(endDate)에 해당신청 플랜 구독기간 추가
        // #.case : (기존회원: 2)강호동 : Adni ,2025-12-15)

        // 4) 다른 플랜 구독변경 :" 이미 베이직 상품 구독 중인데, 프리미엄 상품으로 갱신하는 경우
        // log 테이블 > mno(currentMno) 있지만 레코드 내역 중 마지막 종료일(endDate)에 해당신청 플랜 구독기간 추가
        // #.case : (기존회원: 8)김용만 : starfish , 2026-01-15)
      return false;
    }//func end


    // 1-2. 구독현황
    public LogDto subscribeState( int mno ){
        try {
        /* 01. 로그 테이블 > mno 존재여부 순회 */
            // 1.SQL 작성
            String sql =  "select * from log where mno = ? order by endDate desc limit 1";
            // 2.SQL 기재
            PreparedStatement ps = connectDB.getConn().prepareStatement(sql);
            // 3.SQL 매개변수 대입
            ps.setInt(1, mno);
            // 4.SQL 실행
            ResultSet rs = ps.executeQuery();
            // 5.SQL 실행결과 로직/리턴/확인
            while ( rs.next() ){
                int logno = rs.getInt("logno");
                int pno = rs.getInt("pno");
                mno = rs.getInt("mno");
                String addDate = rs.getString("addDate");
                String endDate = rs.getString("endDate");
                LogDto logDto = new LogDto( logno, pno, mno, addDate, endDate ); // LogDto 객체에 SQL 속성값 대입
                System.out.println( logDto );
                return logDto;
            } //while end
        }catch ( Exception e ){System.out.println( e ); }
        return null;
    }//func end

    // 1-3. 구독취소
    public boolean subscribeCancle( int mno ){
        try {
            /* 01. 로그 테이블 > mno 존재여부 순회 */
            // 1.SQL 작성
            String sql =  "delete from log where mno = ? order by endDate desc limit 1";
            // 2.SQL 기재
            PreparedStatement ps = connectDB.getConn().prepareStatement(sql);
            // 3.SQL 매개변수 대입
            ps.setInt(1, mno);
            // 4.SQL 실행
            int count = ps.executeUpdate(); // 레코드 삭제
            // 5.SQL 실행결과 로직/리턴/확인
            if( count == 1 ) return true;
            else return false;
        }catch ( Exception e ){System.out.println( e ); }
        return false;
    }//func end

    /* [2] 본사 > 관리자단 -----------------------------------------------------------------------------------*/
    // 2-1. 구독신청 내역조회
    public ArrayList<LogDto> subscribelList(){
        ArrayList<LogDto> logDtos = new ArrayList<>();
//        try {
//            /* 01. 로그 테이블 > mno 존재여부 순회 */
//            // 1.SQL 작성
//            String sql =  "select * from log order by logno";
//            // 2.SQL 기재
//            PreparedStatement ps = connectDB.getConn().prepareStatement(sql);
//            // 3.SQL 매개변수 대입
//            ps.setInt(1, mno);
//            // 4.SQL 실행
//            ResultSet rs = ps.executeQuery();
//            // 5.SQL 실행결과 로직/리턴/확인
//            while ( rs.next() ){
//                int logno = rs.getInt("logno");
//                int pno = rs.getInt("pno");
//                mno = rs.getInt("mno");
//                String addDate = rs.getString("addDate");
//                String endDate = rs.getString("endDate");
//                LogDto logDto = new LogDto( logno, pno, mno, addDate, endDate ); // LogDto 객체에 SQL 속성값 대입
//                System.out.println( logDto );
//                return logDto;
//            } //while end
//        }catch ( Exception e ){System.out.println( e ); }
        return null;
    }//func end

}//class end