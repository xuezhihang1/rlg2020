package com.itdr.pojo;

public class Wallet {
    private Integer id;

    private String uname;

    private Integer uphonenumber;

    private Integer starbean;

    private Integer coupon;

    private Integer giftcard;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }

    public Integer getUphonenumber() {
        return uphonenumber;
    }

    public void setUphonenumber(Integer uphonenumber) {
        this.uphonenumber = uphonenumber;
    }

    public Integer getStarbean() {
        return starbean;
    }

    public void setStarbean(Integer starbean) {
        this.starbean = starbean;
    }

    public Integer getCoupon() {
        return coupon;
    }

    public void setCoupon(Integer coupon) {
        this.coupon = coupon;
    }

    public Integer getGiftcard() {
        return giftcard;
    }

    public void setGiftcard(Integer giftcard) {
        this.giftcard = giftcard;
    }
}