package project.controller; // 패키지명

import project.model.dao.Member_HeadDao;
import project.model.dto.Member_HeadDto;

import java.util.ArrayList;
import java.util.Map;

public class Member_HeadController { // class start
    // 싱글톤
    private Member_HeadController(){}
    private static final Member_HeadController instance = new Member_HeadController();
    public static Member_HeadController getInstance(){ return instance; }

    // 전역변수 회원번호
    public static int currentMno;

    // dao 가져오기
    private Member_HeadDao memberHeadDao = Member_HeadDao.getInstance();

    // 로그인 기능
    public int logIn(String mId , String mPwd){
        Member_HeadDto result = memberHeadDao.logIn(mId, mPwd);
        currentMno = result.getMno();
        if (result.getmId().equals("admin")){
            return 1;
        }else if (result == null){
            return 3;
        }else return 2;
    }// func end

    // 회원탈퇴 기능
    public boolean withdrawUser(int mno){
        mno = currentMno;
        boolean result = memberHeadDao.withdrawUser(mno);
        return result;
    }// func end

    // 회원정보 수정 기능
    public boolean updateProfile(int mno , String mPwd , String mPhone){
        Member_HeadDto dto = new Member_HeadDto();
        dto.setmPwd(mPwd);  dto.setmPhone(mPhone); dto.setMno(currentMno);
        boolean result = memberHeadDao.updateProfile(dto);
        return result;
    }// func end

    // 구독자 조회
    public ArrayList<Map<String,Object>> planUserList(){
        ArrayList<Map<String,Object>> result = memberHeadDao.planUserList();
        return result;
    }// func end
}// class end
