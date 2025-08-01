package project.model.dto; // 패키지명

public class Member_HeadDto { // class start
    // 멤버변수
    private int mno;
    private int mCategory;
    private String mId;
    private String mPwd;
    private String mPhone;
    private String mName;
    private String mDate;
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
                "mno_pk=" + mno +
                ", mCategory=" + mCategory +
                ", mId='" + mId + '\'' +
                ", mPwd='" + mPwd + '\'' +
                ", mPhone='" + mPhone + '\'' +
                ", mName='" + mName + '\'' +
                ", mDate='" + mDate + '\'' +
                '}';
    }
}// class end
