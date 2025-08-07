package project.controller; // 패키지명

import project.model.dao.Member_SubDao;
import project.model.dto.Member_SubDto;

import java.util.List;

public class Member_SubController { // class start
    // 싱글톤
    private Member_SubController(){}
    private static final Member_SubController instance = new Member_SubController();
    public static Member_SubController getInstance(){ return instance; }

    // 서브사이트 회원번호 전역변수
    public static int currentSubMno;

    // Member_SubDao 싱글톤 가져오기
    private Member_SubDao memberSubDao = Member_SubDao.getInstance();

    //  현재회사의 회원목록
    public List<Member_SubDto> subUserList(){
        List<Member_SubDto> result = memberSubDao.subUserList();
        return result;
    }// func end

    // 하위사이트 로그아웃
    public void subSignOut(){
        if (currentSubMno > 0){
            currentSubMno = 0;
        }// if end
    }// func end



    // 로그인 회원 정보 반환
    public Member_SubDto subSignIn(){
        Member_SubDto dto = memberSubDao.subSignIn();
        return dto;
    }// func end

}// class end
