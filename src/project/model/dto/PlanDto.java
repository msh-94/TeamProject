package project.model.dto;// 패키지명

public class PlanDto {// class start
    // 멤버변수
    private int pno;
    private String pName;
    private int pDate;
    private int pMoney;
    // 생성자
    public PlanDto() {
    }
    public PlanDto(int pno_pk, String pName, int pDate, int pMoney) {
        this.pno = pno_pk;
        this.pName = pName;
        this.pDate = pDate;
        this.pMoney = pMoney;
    }
    // getter/setter , toString()

    public int getPno_pk() {
        return pno;
    }

    public void setPno_pk(int pno_pk) {
        this.pno = pno_pk;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public int getpDate() {
        return pDate;
    }

    public void setpDate(int pDate) {
        this.pDate = pDate;
    }

    public int getpMoney() {
        return pMoney;
    }

    public void setpMoney(int pMoney) {
        this.pMoney = pMoney;
    }

    @Override
    public String toString() {
        return "PlanDto{" +
                "pno_pk=" + pno +
                ", pName='" + pName + '\'' +
                ", pDate=" + pDate +
                ", pMoney=" + pMoney +
                '}';
    }
}// class end
