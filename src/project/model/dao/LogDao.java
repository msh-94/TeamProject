package project.model.dao;
/* 구독신청(Log table) 관련 */
import project.model.dto.LogDto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
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
        try {// 0.Map 에서 mno 꺼내기
            int mno = (int) subscribeInfo.get("mno");
            int pno = (int) subscribeInfo.get("pno");
            int pDate = (int) subscribeInfo.get("pDate");
            String cName = (String) subscribeInfo.get("cName");
            String area = (String) subscribeInfo.get("area");
            String service = (String) subscribeInfo.get("service");
            LocalDate toDay = LocalDate.now(); // 오늘 날짜 객체
            
            // (1) 최초 구독신청 : 구독신청을 1번도 안한 본사회원의 경우
            String sql = "select * from log where mno = ? order by endDate limit 1";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, mno);
            ResultSet rs = ps.executeQuery();
            if ( ! rs.next() ) { // 구독기록 없음(X)_로그테이블에 mno 없음
                // ① Log 테이블 insert
                String sql_log = "insert into log( pno, mno, endDate ) values( ?,?,? )";
                PreparedStatement ps2 = conn.prepareStatement(sql_log);
                ps2.setInt(1, pno );
                ps2.setInt(2, mno );
                // 종료일 날짜계산 : 오늘날짜 + 해당 플랜 구독기간(월)
                String endDate = toDay.plusMonths( pDate ).toString();
                ps2.setString(3, endDate );
                int result_log = ps2.executeUpdate();
                // ② Company 테이블 insert
                String sql_company = "insert into company( mno, cName, area, service ) values( ?,?,?,? )";
                PreparedStatement ps3 = conn.prepareStatement(sql_company);
                ps3.setInt(1, mno );
                ps3.setString(2, cName );
                ps3.setString(3, area );
                ps3.setString(4, service );
                int result_company = ps3.executeUpdate(); // INSERT 실행
                ps.close(); rs.close(); ps2.close();
                if(result_log >= 1 && result_company >= 1 ) return true;
            }else{ // 구독 기록 있음(O)
                /*
                LogDto logDto = null;
                while ( rs.next() ){
                    int logno = rs.getInt("logno");
                    pno = rs.getInt("pno");
                    mno = rs.getInt("mno");
                    String addDate = rs.getString("addDate");
                    String endDate = rs.getString("endDate");
                    logDto = new LogDto( logno, pno, mno, addDate, endDate);
                } //while end
                if( logDto != null && !logDto.getEndDate().isEmpty() ){
                    // (2) 구독종료 후, 재구독신청
                    String sql_log = "insert into log( pno, mno, endDate ) values( ?,?,? )"; // 1.SQL 작성
                    PreparedStatement ps = conn.prepareStatement(sql_log); // 2.SQL 기재
                    ps.setInt(1, pno ); // 3.SQL 매개변수 대입
                    ps.setInt(2, mno );
                    String endDate = toDay.plusMonths( pDate ).toString(); // 종료일 날짜계산
                    ps.setString(3, endDate );
                    int result_log = ps.executeUpdate(); // 4.SQL 실행(INSERT)
                }

            // (3) 이미 구독중이지만, 구독 추가신청
                */
                return true;
            }
        }catch ( Exception e ){System.out.println( "예외발생" + e ); }
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