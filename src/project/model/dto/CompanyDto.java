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
    public CompanyDto(int cno_pk, int mno_fk, String cName, String area, String service) {
        this.cno = cno_pk;
        this.mno = mno_fk;
        this.cName = cName;
        this.area = area;
        this.service = service;
    }
    // getter/setter , toString()
    public int getCno_pk() {
        return cno;
    }

    public void setCno_pk(int cno_pk) {
        this.cno = cno_pk;
    }

    public int getMno_fk() {
        return mno;
    }

    public void setMno_fk(int mno_fk) {
        this.mno = mno_fk;
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
                "cno_pk=" + cno +
                ", mno_fk=" + mno +
                ", cName='" + cName + '\'' +
                ", area='" + area + '\'' +
                ", service='" + service + '\'' +
                '}';
    }
}// class end
