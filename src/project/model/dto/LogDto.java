package project.model.dto; // 패키지명

public class LogDto {// class start
    // 멤버변수
    private int logno;
    private int pno;
    private int mno;
    private String addDate;
    private String endDate;
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

    public int getPno() {return pno;}

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
                "logno_pk=" + logno +
                ", pno_fk=" + pno +
                ", mno_fk=" + mno +
                ", addDate='" + addDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}// class end
