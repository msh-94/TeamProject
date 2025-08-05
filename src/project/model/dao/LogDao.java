package project.model.dao;
/* 구독신청(Log table) 관련 */
import project.model.dto.LogDto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;

public class LogDao extends Dao{
    // 싱글톤
    private LogDao(){}
    private static final LogDao instance = new LogDao();
    public static LogDao getInstance(){ return instance; }
    
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

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); //날짜 포맷
            LocalDate toDay = LocalDate.now(); // 오늘 날짜 객체
            
            /* Log 테이블 > mno 최종 구독기록 1건 조회(*endDate 기준_마지막 구독종료일) */
            String sql_log = "select * from log where mno = ? order by endDate limit 1";
            PreparedStatement ps = conn.prepareStatement(sql_log);
            ps.setInt(1, mno);
            ResultSet rs = ps.executeQuery();

            /* 1. Log 테이블 > mno 없음( 구독기록 없음(X) ) */
            if ( ! rs.next() ) {
                //(1) 최초 구독신청 : 구독신청을 1번도 안한 경우
                //① Log 테이블 insert
                String sql = "insert into log( pno, mno, endDate ) values( ?,?,? )";
                PreparedStatement ps2 = conn.prepareStatement(sql);
                ps2.setInt(1, pno );
                ps2.setInt(2, mno );
                // 종료일 날짜계산 : 오늘날짜 + 해당 플랜 구독기간(월)
                String endDate = toDay.plusMonths( pDate ).toString();
                ps2.setString(3, endDate );
                int result_log = ps2.executeUpdate();

                //② Company 테이블 insert
                String sql_company = "insert into company( mno, cName, area, service ) values( ?,?,?,? )";
                PreparedStatement ps3 = conn.prepareStatement(sql_company);
                ps3.setInt(1, mno );
                ps3.setString(2, cName );
                ps3.setString(3, area );
                ps3.setString(4, service );
                int result_company = ps3.executeUpdate(); // INSERT 실행
                ps.close(); rs.close(); ps2.close();
                if(result_log >= 1 && result_company >= 1 ) return true;
            }else{
            /* 2. Log 테이블 > mno 있음( 구독기록 있음(O) ) */
                // mno(구독이력 있는 회원_fk) 최종 로그기록(1건) LogDto 객체 담기
                LogDto logDto = null;
                while ( rs.next() ){
                    int logno = rs.getInt("logno");
                    pno = rs.getInt("pno");
                    mno = rs.getInt("mno");
                    String addDate = rs.getString("addDate");
                    String endDate = rs.getString("endDate");
                    logDto = new LogDto( logno, pno, mno, addDate, endDate );
                    System.out.println( logDto );
                } //while end

                String endDate = logDto.getEndDate();
                System.out.println( endDate ); // 구독종료일 확인!
                
                if( endDate != null && !endDate.isEmpty() ){

                    // 날짜 문자열 → LocalDate 로 변환
                    LocalDate endDateLog = LocalDate.parse(endDate, formatter);
                    if( endDateLog.isBefore(toDay) ) { // ①구독종료일 오늘날짜 이전일 경우.
                    //(1) 구독종료 후, 재구독 신청
                        String sql = "insert into log( pno, mno, endDate ) values( ?,?,? )";
                        PreparedStatement ps2 = conn.prepareStatement(sql);
                        ps2.setInt(1, pno);
                        ps2.setInt(2, mno);
                        endDate = toDay.plusMonths(pDate).toString();//종료일 날짜계산: 오늘날짜 + 구독기간(월)
                        ps2.setString(3, endDate);
                        int count = ps2.executeUpdate();
                        if (count == 1) return true;
                        else return false;
                    } else if( endDateLog.isAfter(toDay) ){ // ② 구독종료일 오늘날짜 이후일 경우.
                    //(2) 현재 구독중이지만, 구독연장/추가 신청
                        String sql = "insert into log( pno, mno, endDate ) values( ?,?,? )";
                        PreparedStatement ps2 = conn.prepareStatement(sql);
                        ps2.setInt(1, pno);
                        ps2.setInt(2, mno);
                        endDate = endDateLog.plusMonths(pDate).toString();//종료일 날짜계산: 현재 구독종료일 + 구독기간(월)
                        ps2.setString(3, endDate);
                        int count = ps2.executeUpdate();
                        if (count == 1) return true;
                        else return false;
                    }// if end
                } else { return false; }// if end
            }//if end
        }catch ( Exception e ){ System.out.println( "예외발생" + e ); } // catch end
        return false;
    }//func end
    
    // 2. 구독현황(본사 사용자단)
    public LogDto subscribeState( int mno ){
        try {/* 로그 테이블 > mno 존재여부 순회 */
            String sql =  "select * from log where mno = ? order by endDate desc limit 1";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, mno);
            ResultSet rs = ps.executeQuery();
            while ( rs.next() ){
                int logno = rs.getInt("logno");
                int pno = rs.getInt("pno");
                mno = rs.getInt("mno");
                String addDate = rs.getString("addDate");
                String endDate = rs.getString("endDate");
                LogDto logDto = new LogDto( logno, pno, mno, addDate, endDate );
                System.out.println( logDto );
                return logDto;
            } //while end
        }catch ( Exception e ){System.out.println( "예외발생" + e ); }
        return null;
    }//func end

    // 3. 구독취소(본사 사용자단)
    public boolean subscribeCancle( int mno ){
        try {
            String sql =  "delete from log where mno = ? order by endDate desc limit 1";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, mno);
            int count = ps.executeUpdate(); 
            if( count == 1 ) return true;
            else return false;
        }catch ( Exception e ){System.out.println( "예외발생" + e ); }
        return false;
    }//func end

    // 4. 구독신청 내역조회(본사 관리자)
    public ArrayList<LogDto> subscribelList(){
        ArrayList<LogDto> logList = new ArrayList<>();
        try {
            String sql =  "select * from log";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while ( rs.next() ){
                int logno = rs.getInt("logno");
                int pno = rs.getInt("pno");
                int mno = rs.getInt("mno");
                String addDate = rs.getString("addDate");
                String endDate = rs.getString("endDate");
                LogDto logDto = new LogDto( logno, pno, mno, addDate, endDate ); // LogDto 객체에 SQL 속성값 대입
                logList.add( logDto );
            } //while end
        }catch ( Exception e ){ System.out.println( "예외발생" + e ); }
        //System.out.println( logList );
        return logList;
    }//func end
}//class end