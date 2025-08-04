package project.model.dao; // 패키지명

import java.sql.Connection;
import java.sql.DriverManager;

public class PlanDao extends Dao {// class start
    // 싱글톤
    private PlanDao(){}
    private static final PlanDao instance = new PlanDao();
    public static PlanDao getInstance(){ return instance; }


}// class end
