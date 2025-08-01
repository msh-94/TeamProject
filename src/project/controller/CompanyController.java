package project.controller; // 패키지명


public class CompanyController {// class start
    // 싱글톤
    private CompanyController(){}
    private static final CompanyController instance = new CompanyController();
    public static CompanyController getInstance(){ return instance; }


}// class end
