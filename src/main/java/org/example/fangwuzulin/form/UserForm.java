package org.example.fangwuzulin.form;

import org.example.fangwuzulin.entity.SysUser;

public class UserForm {

    private String id;
    private String username;
    private String password;
    private String newPassword;
    private String name;
    private String phone;
    private String mail;
    private String randomCode;

    public UserForm() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getRandomCode() {
        return randomCode;
    }

    public void setRandomCode(String randomCode) {
        this.randomCode = randomCode;
    }

    public SysUser toEntity() {
        SysUser entity = new SysUser();
        entity.setId(this.id);
        entity.setName(this.name);
        entity.setPhone(this.phone);
        entity.setMail(this.mail);
        entity.setUsername(this.username);
        entity.setPassword(this.password);
        entity.setRandomCode(this.randomCode);
        return entity;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof UserForm)) return false;
        final UserForm other = (UserForm) o;
        if (!other.canEqual(this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$username = this.getUsername();
        final Object other$username = other.getUsername();
        if (this$username == null ? other$username != null : !this$username.equals(other$username)) return false;
        final Object this$password = this.getPassword();
        final Object other$password = other.getPassword();
        if (this$password == null ? other$password != null : !this$password.equals(other$password)) return false;
        final Object this$newPassword = this.getNewPassword();
        final Object other$newPassword = other.getNewPassword();
        if (this$newPassword == null ? other$newPassword != null : !this$newPassword.equals(other$newPassword))
            return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$phone = this.getPhone();
        final Object other$phone = other.getPhone();
        if (this$phone == null ? other$phone != null : !this$phone.equals(other$phone)) return false;
        final Object this$mail = this.getMail();
        final Object other$mail = other.getMail();
        if (this$mail == null ? other$mail != null : !this$mail.equals(other$mail)) return false;
        final Object this$randomCode = this.getRandomCode();
        final Object other$randomCode = other.getRandomCode();
        return this$randomCode == null ? other$randomCode == null : this$randomCode.equals(other$randomCode);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UserForm;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $username = this.getUsername();
        result = result * PRIME + ($username == null ? 43 : $username.hashCode());
        final Object $password = this.getPassword();
        result = result * PRIME + ($password == null ? 43 : $password.hashCode());
        final Object $newPassword = this.getNewPassword();
        result = result * PRIME + ($newPassword == null ? 43 : $newPassword.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $phone = this.getPhone();
        result = result * PRIME + ($phone == null ? 43 : $phone.hashCode());
        final Object $mail = this.getMail();
        result = result * PRIME + ($mail == null ? 43 : $mail.hashCode());
        final Object $randomCode = this.getRandomCode();
        result = result * PRIME + ($randomCode == null ? 43 : $randomCode.hashCode());
        return result;
    }

    public String toString() {
        return "UserForm(id=" + this.getId() + ", username=" + this.getUsername() + ", password=" + this.getPassword() + ", newPassword=" + this.getNewPassword() + ", name=" + this.getName() + ", phone=" + this.getPhone() + ", mail=" + this.getMail() + ", randomCode=" + this.getRandomCode() + ")";
    }
}
