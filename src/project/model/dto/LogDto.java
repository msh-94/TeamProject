package project.model.dto; // 패키지명

public class LogDto {// class start
    // 멤버변수
    private int logno;
    private int pno_fk;
    private int mno_fk;
    private String addDate;
    private String endDate;
    //생성자
    public LogDto() {
    }
    public LogDto(int logno_pk, int pno_fk, int mno_fk, String addDate, String endDate) {
        this.logno = logno_pk;
        this.pno_fk = pno_fk;
        this.mno_fk = mno_fk;
        this.addDate = addDate;
        this.endDate = endDate;
    }
    // getter/setter , toString()
    public int getLogno_pk() {
        return logno;
    }

    public void setLogno_pk(int logno_pk) {
        this.logno = logno_pk;
    }

    public int getPno_fk() {
        return pno_fk;
    }

    public void setPno_fk(int pno_fk) {
        this.pno_fk = pno_fk;
    }

    public int getMno_fk() {
        return mno_fk;
    }

    public void setMno_fk(int mno_fk) {
        this.mno_fk = mno_fk;
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
                ", pno_fk=" + pno_fk +
                ", mno_fk=" + mno_fk +
                ", addDate='" + addDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}// class end
