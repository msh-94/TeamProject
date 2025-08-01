package project.controller; // 패키지명

import project.model.dao.Member_HeadDao;
import project.model.dto.Member_HeadDto;

public class Member_HeadController { // class start
    // 싱글톤
    private Member_HeadController(){}
    private static final Member_HeadController instance = new Member_HeadController();
    public static Member_HeadController getInstance(){ return instance; }


    //싱글톤호출
    Member_HeadDao memberHeadDao = Member_HeadDao.getInstance();

    // 전역변수 회원번호
    public static int currentMno;
    public int signUp(int mno,int mCategory,String mId, String mPwd, String mName, String mPhone,String mDate){
        Member_HeadDto memberHeadDto = new Member_HeadDto(0,mCategory,mId,mPwd,mPhone,mName,null);
        int resultSignUp = Member_HeadDao.getInstance().signUp(0,mCategory,mId,mPwd,mName,mPhone,null);
        return resultSignUp;
    }

}// class end
