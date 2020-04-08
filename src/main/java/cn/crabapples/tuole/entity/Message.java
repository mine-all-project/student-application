package cn.crabapples.tuole.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class Message extends BaseEntity{
    private Integer area;

    @ManyToOne
    private SysUser fromUser;

    @OneToMany
    List<Message> children;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
