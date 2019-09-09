package cn.crabapples.hxy.entity;


import com.google.gson.Gson;

import javax.persistence.*;

@Entity
public class MineInfo extends BaseEntity{

    @Column(length = 10)
    private double money;

    @Column(length = 20)
    private String city;

    @Column(length = 255)
    private String zhuanye;

    @Column(length = 255)
    private String zhengshu;

    @Column(length = 500)
    private String info;

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZhuanye() {
        return zhuanye;
    }

    public void setZhuanye(String zhuanye) {
        this.zhuanye = zhuanye;
    }

    public String getZhengshu() {
        return zhengshu;
    }

    public void setZhengshu(String zhengshu) {
        this.zhengshu = zhengshu;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
