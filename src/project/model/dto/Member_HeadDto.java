package project.model.dto;

public class Member_HeadDto {
    // 멤버변수              // Member_head(본사 회원 Table)
    private int mno;        // 본사회원번호(pk)
    private int mCategory;  // 회원유형(1.일반회원/2.택시기사/3.사업자)
    private String mId;     // 로그인 아이디
    private String mPwd;    // 로그인 비번
    private String mPhone;  // 휴대폰번호
    private String mName;   // 이름
    private String mDate;   // 회원가입일
    // 생성자
    public Member_HeadDto() {
    }
    public Member_HeadDto(int mno, int mCategory, String mId, String mPwd, String mPhone, String mName, String mDate) {
        this.mno = mno;
        this.mCategory = mCategory;
        this.mId = mId;
        this.mPwd = mPwd;
        this.mPhone = mPhone;
        this.mName = mName;
        this.mDate = mDate;
    }
    // getter/setter , toString()
    public int getMno() {
        return mno;
    }

    public void setMno(int mno) {
        this.mno = mno;
    }

    public int getmCategory() {
        return mCategory;
    }

    public void setmCategory(int mCategory) {
        this.mCategory = mCategory;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmPwd() {
        return mPwd;
    }

    public void setmPwd(String mPwd) {
        this.mPwd = mPwd;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmPhone() {
        return mPhone;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }
    @Override
    public String toString() {
        return "Member_HeadDto{" +
                "mno=" + mno +
                ", mCategory=" + mCategory +
                ", mId='" + mId + '\'' +
                ", mPwd='" + mPwd + '\'' +
                ", mPhone='" + mPhone + '\'' +
                ", mName='" + mName + '\'' +
                ", mDate='" + mDate + '\'' +
                '}';
    }
}// class end
