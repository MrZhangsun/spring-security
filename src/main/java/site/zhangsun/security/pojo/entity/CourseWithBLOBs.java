package site.zhangsun.security.pojo.entity;

public class CourseWithBLOBs extends Course {
    private String introduction;

    private String picture;

    private String signinQrcode;

    private String signoutQrcode;

    private String detail;

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public String getSigninQrcode() {
        return signinQrcode;
    }

    public void setSigninQrcode(String signinQrcode) {
        this.signinQrcode = signinQrcode == null ? null : signinQrcode.trim();
    }

    public String getSignoutQrcode() {
        return signoutQrcode;
    }

    public void setSignoutQrcode(String signoutQrcode) {
        this.signoutQrcode = signoutQrcode == null ? null : signoutQrcode.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }
}