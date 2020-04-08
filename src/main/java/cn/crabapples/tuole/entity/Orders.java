package cn.crabapples.tuole.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.util.List;

/**
 * TODO 订单模块实体类
 *
 * @author Mr.He
 * 2020/4/7 22:56
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
@Getter
@Setter
@Entity
public class Orders extends BaseEntity {

    @CreatedDate
    private LocalDate orderTime;
    @OneToOne
    private SysUser sysUser;
    @OneToMany
    private List<Goods> goods;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
