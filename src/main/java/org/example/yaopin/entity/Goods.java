package org.example.yaopin.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.LocalDate;


@Getter
@Setter
/**
 * 药品信息
 */
@Entity
public class Goods extends BaseEntity {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCounts() {
        return counts;
    }

    public void setCounts(Long counts) {
        this.counts = counts;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getProducedTime() {
        return producedTime;
    }

    public void setProducedTime(LocalDate producedTime) {
        this.producedTime = producedTime;
    }

    public LocalDate getShelLife() {
        return shelLife;
    }

    public void setShelLife(LocalDate shelLife) {
        this.shelLife = shelLife;
    }

    public String getCharacte() {
        return characte;
    }

    public void setCharacte(String characte) {
        this.characte = characte;
    }

    public String getMajorFunction() {
        return majorFunction;
    }

    public void setMajorFunction(String majorFunction) {
        this.majorFunction = majorFunction;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getUsages() {
        return usages;
    }

    public void setUsages(String usages) {
        this.usages = usages;
    }

    public String getAdverseReactions() {
        return adverseReactions;
    }

    public void setAdverseReactions(String adverseReactions) {
        this.adverseReactions = adverseReactions;
    }

    public String getMattersNeeding() {
        return mattersNeeding;
    }

    public void setMattersNeeding(String mattersNeeding) {
        this.mattersNeeding = mattersNeeding;
    }

    public String getTaboo() {
        return taboo;
    }

    public void setTaboo(String taboo) {
        this.taboo = taboo;
    }

    public String getStorageEnvironment() {
        return storageEnvironment;
    }

    public void setStorageEnvironment(String storageEnvironment) {
        this.storageEnvironment = storageEnvironment;
    }

    @Column(columnDefinition = "varchar(64) comment '名称'")
    private String name;
    @Column(columnDefinition = "int(10) comment '数量'")
    private Long counts;

    @Column(columnDefinition = "varchar(64) comment '生产地址'")
    private String address;
    @Column(columnDefinition = "varchar(64) comment '产品批号'")
    private String number;
    @Column(columnDefinition = "timestamp comment '生产日期'")
    @JSONField(format = "yyyy-MM-dd")
    private LocalDate producedTime;
    @Column(columnDefinition = "timestamp comment '有效期'")
    @JSONField(format = "yyyy-MM-dd")
    private LocalDate shelLife;
    @Column(columnDefinition = "longtext comment '性状'")
    private String characte;
    @Column(columnDefinition = "longtext comment '功能主治'")
    private String majorFunction;
    @Column(columnDefinition = "longtext comment '规格'")
    private String specification;
    @Column(columnDefinition = "longtext comment '用法用量'")
    private String usages;
    @Column(columnDefinition = "longtext comment '不良反应'")
    private String adverseReactions;
    @Column(columnDefinition = "longtext comment '注意事项'")
    private String mattersNeeding;
    @Column(columnDefinition = "longtext comment '禁忌'")
    private String taboo;
    @Column(columnDefinition = "longtext comment '存储环境'")
    private String storageEnvironment;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
