package project.model.dto;

public class LogDto {
    // 멤버변수              // Log(본사 구독로그 Table)
    private int logno;      // 구독로그번호(pk)
    private int pno;        // 구독플랜번호(fk)
    private int mno;        // 본사회원번호(fk)
    private String addDate; // 구독신청일
    private String endDate; // 구독종료일
    //생성자
    public LogDto() {
    }
    public LogDto(int logno, int pno, int mno, String addDate, String endDate) {
        this.logno = logno;
        this.pno = pno;
        this.mno = mno;
        this.addDate = addDate;
        this.endDate = endDate;
    }
    // getter/setter , toString()
    public int getLogno() {
        return logno;
    }

    public void setLogno(int logno) {
        this.logno = logno;
    }

    public int getPno() {
        return pno;
    }

    public void setPno(int pno) {
        this.pno = pno;
    }

    public int getMno() {
        return mno;
    }

    public void setMno(int mno) {
        this.mno = mno;
    }

    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "LogDto{" +
                "logno=" + logno +
                ", pno=" + pno +
                ", mno=" + mno +
                ", addDate='" + addDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}// class end
