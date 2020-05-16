package org.example.fangwuzulin.entity;

import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.util.List;

public class Houses extends BaseEntity {

    private String title;
    private String address;
    private String user_id;
    private BigDecimal price;
    private String note;
    private String img_src;
    private String contract_id;
    private Contracts contracts;
    private String province;
    private String city;
    private String area;
    private SysUser user;
    private List<LeaveMessage> messages;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getImg_src() {
        return img_src;
    }

    public void setImg_src(String img_src) {
        this.img_src = img_src;
    }

    public String getContract_id() {
        return contract_id;
    }

    public void setContract_id(String contract_id) {
        this.contract_id = contract_id;
    }

    public Contracts getContracts() {
        return contracts;
    }

    public void setContracts(Contracts contracts) {
        this.contracts = contracts;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public List<LeaveMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<LeaveMessage> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
