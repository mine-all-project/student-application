package org.example.fangwuzulin.form;

import org.example.fangwuzulin.config.groups.IsNotNull;
import org.example.fangwuzulin.entity.Houses;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class HousesForm {

    private String id;

    @NotBlank(message = "标题不能为空", groups = {IsNotNull.class})
    private String title;

    @NotBlank(message = "地址不能为空", groups = {IsNotNull.class})
    private String address;

    @NotNull(message = "价格不能为空", groups = {IsNotNull.class})
    private BigDecimal price;

    private String note;

    private String img_src;

    private String contract_id;

    private String province;

    private String city;

    private String area;

    public HousesForm() {
    }

    public Houses toEntity() {
        Houses entity = new Houses();
        entity.setId(this.id);
        entity.setTitle(this.title);
        entity.setAddress(this.address);
        entity.setPrice(this.price);
        entity.setNote(this.note);
        entity.setImg_src(this.img_src);
        entity.setProvince(this.province);
        entity.setCity(this.city);
        entity.setArea(this.area);
        entity.setContract_id(this.contract_id);
        return entity;
    }

    public String getId() {
        return this.id;
    }

    public @NotBlank(message = "标题不能为空", groups = {IsNotNull.class}) String getTitle() {
        return this.title;
    }

    public @NotBlank(message = "地址不能为空", groups = {IsNotNull.class}) String getAddress() {
        return this.address;
    }

    public @NotNull(message = "价格不能为空", groups = {IsNotNull.class}) BigDecimal getPrice() {
        return this.price;
    }

    public String getNote() {
        return this.note;
    }

    public String getImg_src() {
        return this.img_src;
    }

    public String getContract_id() {
        return this.contract_id;
    }

    public String getProvince() {
        return this.province;
    }

    public String getCity() {
        return this.city;
    }

    public String getArea() {
        return this.area;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(@NotBlank(message = "标题不能为空", groups = {IsNotNull.class}) String title) {
        this.title = title;
    }

    public void setAddress(@NotBlank(message = "地址不能为空", groups = {IsNotNull.class}) String address) {
        this.address = address;
    }

    public void setPrice(@NotNull(message = "价格不能为空", groups = {IsNotNull.class}) BigDecimal price) {
        this.price = price;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setImg_src(String img_src) {
        this.img_src = img_src;
    }

    public void setContract_id(String contract_id) {
        this.contract_id = contract_id;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof HousesForm)) return false;
        final HousesForm other = (HousesForm) o;
        if (!other.canEqual(this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$title = this.getTitle();
        final Object other$title = other.getTitle();
        if (this$title == null ? other$title != null : !this$title.equals(other$title)) return false;
        final Object this$address = this.getAddress();
        final Object other$address = other.getAddress();
        if (this$address == null ? other$address != null : !this$address.equals(other$address)) return false;
        final Object this$price = this.getPrice();
        final Object other$price = other.getPrice();
        if (this$price == null ? other$price != null : !this$price.equals(other$price)) return false;
        final Object this$note = this.getNote();
        final Object other$note = other.getNote();
        if (this$note == null ? other$note != null : !this$note.equals(other$note)) return false;
        final Object this$img_src = this.getImg_src();
        final Object other$img_src = other.getImg_src();
        if (this$img_src == null ? other$img_src != null : !this$img_src.equals(other$img_src)) return false;
        final Object this$contract_id = this.getContract_id();
        final Object other$contract_id = other.getContract_id();
        if (this$contract_id == null ? other$contract_id != null : !this$contract_id.equals(other$contract_id))
            return false;
        final Object this$province = this.getProvince();
        final Object other$province = other.getProvince();
        if (this$province == null ? other$province != null : !this$province.equals(other$province)) return false;
        final Object this$city = this.getCity();
        final Object other$city = other.getCity();
        if (this$city == null ? other$city != null : !this$city.equals(other$city)) return false;
        final Object this$area = this.getArea();
        final Object other$area = other.getArea();
        return this$area == null ? other$area == null : this$area.equals(other$area);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof HousesForm;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $title = this.getTitle();
        result = result * PRIME + ($title == null ? 43 : $title.hashCode());
        final Object $address = this.getAddress();
        result = result * PRIME + ($address == null ? 43 : $address.hashCode());
        final Object $price = this.getPrice();
        result = result * PRIME + ($price == null ? 43 : $price.hashCode());
        final Object $note = this.getNote();
        result = result * PRIME + ($note == null ? 43 : $note.hashCode());
        final Object $img_src = this.getImg_src();
        result = result * PRIME + ($img_src == null ? 43 : $img_src.hashCode());
        final Object $contract_id = this.getContract_id();
        result = result * PRIME + ($contract_id == null ? 43 : $contract_id.hashCode());
        final Object $province = this.getProvince();
        result = result * PRIME + ($province == null ? 43 : $province.hashCode());
        final Object $city = this.getCity();
        result = result * PRIME + ($city == null ? 43 : $city.hashCode());
        final Object $area = this.getArea();
        result = result * PRIME + ($area == null ? 43 : $area.hashCode());
        return result;
    }

    public String toString() {
        return "HousesForm(id=" + this.getId() + ", title=" + this.getTitle() + ", address=" + this.getAddress() + ", price=" + this.getPrice() + ", note=" + this.getNote() + ", img_src=" + this.getImg_src() + ", contract_id=" + this.getContract_id() + ", province=" + this.getProvince() + ", city=" + this.getCity() + ", area=" + this.getArea() + ")";
    }
}
