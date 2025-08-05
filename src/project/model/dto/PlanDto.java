package project.model.dto;

public class PlanDto {
    // 멤버변수              // Plan( 본사전용상품 > 구독플랜 Table)
    private int pno;        // 구독플랜번호(pk)
    private String pName;   // 구독플랜명
    private int pDate;      // 구독기간(월단위)
    private int pMoney;     // 구독금액(원)
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
