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
    public Member_HeadDto logIn(String mId , String mPwd){
        Member_HeadDto result = memberHeadDao.logIn(mId, mPwd);
        currentMno = result.getMno();
        return result;
    }// func end

    // 회원탈퇴 기능
    public boolean withdrawUser(){
        boolean result = memberHeadDao.withdrawUser(currentMno);
        return result;
    }// func end

    // 회원정보 수정 기능
    public int updateProfile(String mPwd , String mPhone , String mPwd1){
        Member_HeadDto dto = new Member_HeadDto();
        dto.setmPwd(mPwd);  dto.setmPhone(mPhone); dto.setMno(currentMno);
        dto.setmName(mPwd1);
        int result = memberHeadDao.updateProfile(dto);
        return result;
    }// func end

    // 구독자 조회
    public ArrayList<Map<String,Object>> planUserList(){
        ArrayList<Map<String,Object>> result = memberHeadDao.planUserList();
        return result;
    }// func end

    // 로그인한 회원 구독자목록에 존재하는지 확인
    public boolean checkMember(){
        ArrayList<Map<String,Object>> result = memberHeadDao.planUserList();
        for (int i = 0; i < result.size(); i++){
            Map<String,Object> map = result.get(i);
            if (map.get("번호").equals(currentMno)){
                return true;
            }// if end
        }// for end
        return false;
    }// func end

    //회원가입
    public int signUp(int mCategory,String mId, String mPwd, String mName, String mPhone){
        Member_HeadDto signDto = new Member_HeadDto (0,mCategory,mId,mPwd,mName,mPhone,null);
        int resultSignUp = memberHeadDao.signUp(0,mCategory,mId,mPwd,mName,mPhone,null);
        return resultSignUp;
    }// func end

    //회원목록조회
    public ArrayList<Member_HeadDto> userList() {
        ArrayList<Member_HeadDto> resultList = Member_HeadDao.getInstance().userList();
        return resultList;
    }//func end
}// class end
