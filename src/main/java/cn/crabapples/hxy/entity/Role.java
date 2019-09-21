package cn.crabapples.hxy.entity;


import javax.persistence.*;
import java.util.List;

/**
 * TODO
 *
 * @author Mr.He
 * @date 2019/9/13 11:58
 * e-mail wishforyou.xia@gmail.com
 * qq 294046317
 * pc-name 29404
 */
@Entity
@Table
public class Role extends BaseEntity {
    @Column(columnDefinition = "varchar(20) default ''")
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> users;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }
    @Override
    public String toString() {
        return gson.toJson(this);
    }
}
