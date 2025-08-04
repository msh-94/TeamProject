package project.model.dao;// 패키지명

import java.sql.Connection;
import java.sql.DriverManager;

public class LogDao extends Dao {// class start
    // 싱글톤
    private LogDao(){}
    private static final LogDao instance = new LogDao();
    public static LogDao getInstance(){ return instance; }


}// class end
