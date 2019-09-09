package cn.crabapples.hxy.entity;


import com.google.gson.Gson;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class User extends BaseEntity {

    @Column(length = 20)
    private String username;

    @Column(length = 20)
    private String password;

    @Column(length = 20)
    private String name;

    @Column(length = 3)
    private String sex;

    @Column(length = 1)
    private Integer type;

    @Column(length = 30)
    private String phone;

    @Column(length = 30)
    private String email;

    @Column(length = 10)
    private LocalDateTime birthday;

    @OneToOne
    @JoinTable(name="userToMineInfo",	//用来指定中间表的名称
            //用于指定本表在中间表的字段名称，以及中间表依赖的是本表的哪个字段
            joinColumns= {@JoinColumn(name="userId",referencedColumnName="id")},
            //用于指定对方表在中间表的字段名称，以及中间表依赖的是它的哪个字段
            inverseJoinColumns= {@JoinColumn(name="mineInfoId",referencedColumnName="id")}
    )
    MineInfo mineInfo;

    @OneToOne
    @JoinTable(name="userToOrgInfo",	//用来指定中间表的名称
            //用于指定本表在中间表的字段名称，以及中间表依赖的是本表的哪个字段
            joinColumns= {@JoinColumn(name="userId",referencedColumnName="id")},
            //用于指定对方表在中间表的字段名称，以及中间表依赖的是它的哪个字段
            inverseJoinColumns= {@JoinColumn(name="orgInfoId",referencedColumnName="id")}
    )
    OrgInfo orgInfo;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public MineInfo getMineInfo() {
        return mineInfo;
    }

    public void setMineInfo(MineInfo mineInfo) {
        this.mineInfo = mineInfo;
    }

    public OrgInfo getOrgInfo() {
        return orgInfo;
    }

    public void setOrgInfo(OrgInfo orgInfo) {
        this.orgInfo = orgInfo;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}