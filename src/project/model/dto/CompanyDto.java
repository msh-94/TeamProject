package project.model.dto; // 패키지명

public class CompanyDto {// class start
    // 멤버변수
    private int cno;
    private int mno;
    private String cName;
    private String area;
    private String service;
    // 생성자
    public CompanyDto() {
    }
    public CompanyDto(int cno, int mno, String cName, String area, String service) {
        this.cno = cno;
        this.mno = mno;
        this.cName = cName;
        this.area = area;
        this.service = service;
    }
    // getter/setter , toString()
    public int getCno() {
        return cno;
    }

    public void setCno(int cno) {
        this.cno = cno;
    }

    public int getMno() {
        return mno;
    }

    public void setMno(int mno) {
        this.mno = mno;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return "CompanyDto{" +
                "cno=" + cno +
                ", mno=" + mno +
                ", cName='" + cName + '\'' +
                ", area='" + area + '\'' +
                ", service='" + service + '\'' +
                '}';
    }
}// class end
