package project.controller; // 패키지명

import project.model.dao.Member_SubDao;
import project.model.dto.Member_SubDto;

public class Member_SubController { // class start
    // 싱글톤
    private Member_SubController(){}
    private static final Member_SubController instance = new Member_SubController();
    public static Member_SubController getInstance(){ return instance; }


    // Member_SubDao 싱글톤 가져오기
    private Member_SubDao memberSubDao = Member_SubDao.getInstance();


}// class end
