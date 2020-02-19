package com.itdr.pojo;

import java.util.Date;

public class Order {
    private Integer id;

    private Integer onumber;

    private Long oprice;

    private String oraddress;

    private String osaddress;

    private String orname;

    private String osname;

    private String express;

    private String ostate;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOnumber() {
        return onumber;
    }

    public void setOnumber(Integer onumber) {
        this.onumber = onumber;
    }

    public Long getOprice() {
        return oprice;
    }

    public void setOprice(Long oprice) {
        this.oprice = oprice;
    }

    public String getOraddress() {
        return oraddress;
    }

    public void setOraddress(String oraddress) {
        this.oraddress = oraddress == null ? null : oraddress.trim();
    }

    public String getOsaddress() {
        return osaddress;
    }

    public void setOsaddress(String osaddress) {
        this.osaddress = osaddress == null ? null : osaddress.trim();
    }

    public String getOrname() {
        return orname;
    }

    public void setOrname(String orname) {
        this.orname = orname == null ? null : orname.trim();
    }

    public String getOsname() {
        return osname;
    }

    public void setOsname(String osname) {
        this.osname = osname == null ? null : osname.trim();
    }

    public String getExpress() {
        return express;
    }

    public void setExpress(String express) {
        this.express = express == null ? null : express.trim();
    }

    public String getOstate() {
        return ostate;
    }

    public void setOstate(String ostate) {
        this.ostate = ostate == null ? null : ostate.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}