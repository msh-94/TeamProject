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
    public Member_SubDto(int mno_pk, int cno_fk, int mCategory, String mId, String mPwd, String mNum, String mName, String mDate) {
        this.mno = mno_pk;
        this.cno = cno_fk;
        this.mCategory = mCategory;
        this.mId = mId;
        this.mPwd = mPwd;
        this.mPhone = mNum;
        this.mName = mName;
        this.mDate = mDate;
    }
    // getter/setter , toString()
    public int getMno_pk() {
        return mno;
    }

    public void setMno_pk(int mno_pk) {
        this.mno = mno_pk;
    }

    public int getCno_fk() {
        return cno;
    }

    public void setCno_fk(int cno_fk) {
        this.cno = cno_fk;
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
                "mno_pk=" + mno +
                ", cno_fk=" + cno +
                ", mCategory=" + mCategory +
                ", mId='" + mId + '\'' +
                ", mPwd='" + mPwd + '\'' +
                ", mNum='" + mPhone + '\'' +
                ", mName='" + mName + '\'' +
                ", mDate='" + mDate + '\'' +
                '}';
    }
}// class end
