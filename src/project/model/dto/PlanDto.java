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
    public PlanDto(int pno, String pName, int pDate, int pMoney) {
        this.pno = pno;
        this.pName = pName;
        this.pDate = pDate;
        this.pMoney = pMoney;
    }
    // getter/setter , toString()

    public int getPno() {
        return pno;
    }

    public void setPno(int pno) {
        this.pno = pno;
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
                "pno=" + pno +
                ", pName='" + pName + '\'' +
                ", pDate=" + pDate +
                ", pMoney=" + pMoney +
                '}';
    }
}// class end
