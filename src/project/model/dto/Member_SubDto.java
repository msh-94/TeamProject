package project.model.dto; // 패키지명

public class Member_SubDto {// class start
    // 멤버변수
    private int mno;
    private int cno;
    private int mCategory;
    private String mId;
    private String mPwd;
    private String mPhone;
    private String mName;
    private String mDate;
    // 생성자
    public Member_SubDto() {
    }
    public Member_SubDto(int mno, int cno, int mCategory, String mId, String mPwd, String mNum, String mName, String mDate) {
        this.mno = mno;
        this.cno = cno;
        this.mCategory = mCategory;
        this.mId = mId;
        this.mPwd = mPwd;
        this.mPhone = mNum;
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

    public int getCno() {
        return cno;
    }

    public void setCno(int cno) {
        this.cno = cno;
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

    public String getmNum() {
        return mPhone;
    }

    public void setmNum(String mNum) {
        this.mPhone = mNum;
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
    @Override
    public String toString() {
        return "Member_SubDto{" +
                "mno=" + mno +
                ", cno=" + cno +
                ", mCategory=" + mCategory +
                ", mId='" + mId + '\'' +
                ", mPwd='" + mPwd + '\'' +
                ", mNum='" + mPhone + '\'' +
                ", mName='" + mName + '\'' +
                ", mDate='" + mDate + '\'' +
                '}';
    }
}// class end
