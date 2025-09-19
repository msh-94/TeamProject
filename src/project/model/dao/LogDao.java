package project.model.dao;
/* 구독신청(Log table) 관련 */
import project.model.dto.LogDto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class LogDao extends Dao{
    //// 싱글톤
    //private LogDao(){}
    //private static final LogDao instance = new LogDao();
    //public static LogDao getInstance(){ return instance; }

    // 날짜 객체
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); //날짜포맷
    public static  LocalDate toDay = LocalDate.now(); //오늘날짜 객체

    /* ======================================== ★ 단위기능 ★ ============================================== */
    // 1. 구독신청(본사 사용자단)
    public boolean subscribeRequest( Map<String, Object> subscribeInfo ){
        try {
            /* 매개변수 Map<> 에서 데이터 꺼내기 */
            int mno = (int) subscribeInfo.get("mno");
            int pno = (int) subscribeInfo.get("pno");
            int pDate = (int) subscribeInfo.get("pDate");
            String cName = (String) subscribeInfo.get("cName");
            String area = (String) subscribeInfo.get("area");
            String service = (String) subscribeInfo.get("service");

            /* Log 테이블(DB) > mno(fk) 마지막 구독기록 1건 조회 */
            String sql_log = "select * from log where mno = ? order by endDate desc limit 1";
            PreparedStatement ps = conn.prepareStatement(sql_log);
            ps.setInt(1, mno);
            ResultSet rs = ps.executeQuery();

            if ( ! rs.next() ){  // Log 테이블 > mno 없음( 구독기록 없음(X) )
                /* (1) 최초 구독신청 : 구독신청을 1번도 안한 경우 */
                /* 1_Log 테이블 insert */
                String sql = "insert into log( pno, mno, endDate ) values( ?,?,? )";
                PreparedStatement ps2 = conn.prepareStatement(sql);
                ps2.setInt(1, pno );
                ps2.setInt(2, mno );
                /* 종료일 날짜계산 : 오늘날짜 + 해당 플랜 구독기간(월) */
                String endDate = toDay.plusMonths( pDate ).toString();
                ps2.setString(3, endDate );
                int result_log = ps2.executeUpdate();
                /* 2_Company 테이블 insert */
                String sql_company = "insert into company( mno, cName, area, service ) values( ?,?,?,? )";
                PreparedStatement ps3 = conn.prepareStatement(sql_company);
                ps3.setInt(1, mno);
                ps3.setString(2, cName);
                ps3.setString(3, area);
                ps3.setString(4, service);
                int result_company = ps3.executeUpdate();
                if( result_log == 1 && result_company == 1) return true;
                else return false;
            }else{ // Log 테이블 > mno 있음( 구독기록 있음(O) )
                /* Log 테이블(DB) > mno(fk) 마지막 구독기록 1건 Data 변수에 담기! */
                String endDate = rs.getString("endDate");
                //System.out.println( "(1.신청이전)구독종료일 확인용(Test):" + endDate ); //(콘솔 테스트 확인용!)
                if( endDate != null && !endDate.isEmpty() ){
                    /* 날짜 문자열을 LocalDate 로 타입변환(*오늘날짜 기준, 구독종료일(구독상태)을 확인하기 위함) */
                    LocalDate endDateLog = LocalDate.parse(endDate, formatter);
                    if( endDateLog.isBefore(toDay) ) { // 1.구독종료일 오늘날짜 이전일 경우
                    /* (1) 구독종료 후, 재구독 신청 */
                        String sql = "insert into log( pno, mno, endDate ) values( ?,?,? )";
                        PreparedStatement ps2 = conn.prepareStatement(sql);
                        ps2.setInt(1, pno);
                        ps2.setInt(2, mno);
                        endDate = toDay.plusMonths(pDate).toString();// ****구독종료일 계산: 오늘날짜(신청일) + 해당플랜 구독기간(월)
                        ps2.setString(3, endDate);
                        int count = ps2.executeUpdate();
                        if (count == 1) return true;
                        else return false;
                    } else if( endDateLog.isAfter(toDay) ){ // 2.구독종료일 오늘날짜 이후일 경우
                    /* (2) 현재 구독중이지만, 구독연장/추가 신청 */
                        String sql = "insert into log( pno, mno, endDate ) values( ?,?,? )";
                        PreparedStatement ps2 = conn.prepareStatement(sql);
                        ps2.setInt(1, pno);
                        ps2.setInt(2, mno);
                        endDate = endDateLog.plusMonths(pDate).toString();// ****구독종료일 계산: 현재 구독중인 종료일 + 해당플랜 구독기간(월)
                        ps2.setString(3, endDate);
                        //System.out.println( "(2.신청이후)구독종료일 확인용(Test):" + endDate ); //(콘솔 테스트 확인용!)
                        int count = ps2.executeUpdate();
                        if (count == 1) return true;
                        else return false;
                    }// if end
                } else { return false; }// if end
            }//if end
        }catch ( Exception e ){ System.out.println( "예외발생" + e );  e.printStackTrace(); } // catch end
        return false;
    }//func end
    
    // 2. 구독현황(본사 사용자단)
    public LogDto subscribeState( int mno ){
        LogDto logDto = new LogDto();
        try {/* 로그 테이블 > mno 존재여부 순회 */
            String sql =  "select * from log where mno = ? order by endDate desc, logno desc limit 1";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, mno);
            ResultSet rs = ps.executeQuery();
            while ( rs.next() ){
                //logDto.setLogno(rs.getInt("logno"));
                logDto.setPno(rs.getInt("pno"));
                logDto.setMno(rs.getInt("mno"));
                logDto.setAddDate(rs.getString("addDate"));
                logDto.setEndDate(rs.getString("endDate"));
                //System.out.println( logDto );
            } //while end
        }catch ( Exception e ){System.out.println( "예외발생" + e ); }
        return logDto;
    }// func end

    // 3. 구독취소(본사 사용자단)_로그를 삭제 하지 않고 종료일을 구독취소일(당일)로 변경
    public boolean subscribeCancel( int mno ){
        try {
            //String sql =  "delete from log where mno = ? order by endDate desc limit 1";
            String sql = "update log set endDate = ? where mno = ? order by endDate desc , logno desc limit 1;";
            PreparedStatement ps = conn.prepareStatement(sql);
            String cancelDate = toDay.minusDays(1).toString(); // 구독취소일 - 1일로 수정

            ps.setString(1, cancelDate);
            ps.setInt(2, mno);
            //System.out.println("endDate = " + cancelDate);
            int count = ps.executeUpdate(); 
            if( count == 1 ) return true;
            else return false;
        }catch ( Exception e ){System.out.println( "예외발생" + e ); }
        return false;
    }//func end

    // 4. 구독신청 내역조회(본사 관리자)
    public ArrayList<LinkedHashMap<String,Object>> subscribeList(){
        ArrayList<LinkedHashMap<String,Object>> logUserList = new ArrayList<>();
        try {
            String sql = "select logno, pName, pMoney, mName, mId, addDate, endDate from plan left outer join log on plan.pno = log.pno left outer join Member_head on log.mno = Member_head.mno  WHERE logno IS NOT NULL order by logno desc";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while ( rs.next() ){
                LinkedHashMap<String,Object> logUser = new LinkedHashMap<>();
                logUser.put("No" , rs.getObject("logno"));
                logUser.put("구독플랜명",rs.getObject("pName"));
                logUser.put("구독가격(원)" , rs.getObject("pMoney"));
                logUser.put("이름" , rs.getObject("mName"));
                logUser.put("아이디",rs.getObject("mId"));
                logUser.put("구독시작일" , rs.getObject("addDate"));
                logUser.put("구독종료일", rs.getObject("endDate"));
                logUserList.add(logUser);
            } //while end
        }catch ( Exception e ){ System.out.println( "예외발생" + e ); }
        //System.out.println( logUserList );
        return logUserList;
    }//func end
}//class end