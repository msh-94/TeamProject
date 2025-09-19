package project; // 패키지명

import project.controller.*;
import project.model.dao.*;
import project.view.AdminView;
import project.view.TotalView;
import project.view.UserView;

public class Container { // class start
    // 싱글톤
    private static final Container instance = new Container();
    public static Container getInstance(){return instance;}

    // [*] 관리할 객체들
    // view
    private final AdminView adminView;
    private final TotalView totalView;
    private final UserView userView;
    // controller
    private final CompanyController cc;
    private final LogController lc;
    private final Member_HeadController mhc;
    private final Member_SubController msc;
    private final PlanController pc;
    // dao
    private final CompanyDao cdao;
    private final LogDao ldao;
    private final Member_HeadDao mhdao;
    private final Member_SubDao msdao;
    private final PlanDao pdao;

    // 생성자
    private Container(){
        // view
        this.adminView = new AdminView();
        this.totalView = new TotalView();
        this.userView = new UserView();
        // controller
        this.cc = new CompanyController();
        this.lc = new LogController();
        this.mhc = new Member_HeadController();
        this.msc = new Member_SubController();
        this.pc = new PlanController();
        // dao
        this.cdao = new CompanyDao();
        this.ldao = new LogDao();
        this.mhdao = new Member_HeadDao();
        this.msdao = new Member_SubDao();
        this.pdao = new PlanDao();
    } // end

    // [*] Getter
    // view
    public AdminView getAdminView() { return adminView; }
    public TotalView getTotalView() { return totalView; }
    public UserView getUserView() { return userView; }
    // controller
    public CompanyController getCc() { return cc; }
    public LogController getLc() { return lc; }
    public Member_HeadController getMhc() { return mhc; }
    public Member_SubController getMsc() { return msc; }
    public PlanController getPc() { return pc; }
    // dao
    public CompanyDao getCdao() { return cdao; }
    public LogDao getLdao() { return ldao; }
    public Member_HeadDao getMhdao() { return mhdao; }
    public Member_SubDao getMsdao() { return msdao; }
    public PlanDao getPdao() { return pdao; }


}// class end
