package project.model.dto;

public class CompanyDto {
    // 멤버변수              // Company(본사 구독자(회사) 부가정보 Table)
    private int cno;        // 구독회사(구독자) 번호(pk)
    private int mno;        // 본사회원번호(fk)
    private String cName;   // 사이트명
    private String area;    // 서비스지역
    private String service; // 서비스내용
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
