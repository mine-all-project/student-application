package cn.crabapples.tuole.entity;


import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
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

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
