package com.nowbio.database.efn.modules.ums.dto;

import javax.validation.constraints.*;
import java.util.List;

/**
 * @author yun
 * @since 2021/5/7 10:53
 */
public class Parameter {
    @NotEmpty(message="姓名不能为空")
    private String name;

    @Min(value = 18, message = "年龄必须大于18岁")
    private int age;

    @NotEmpty(message="hobbies不能为空")
    private List<String> hobbies;

    @NotBlank(message="账号不能为空")
    private String account;

    @Size(min=5,max=10,message="密码的长度应该在5和10之间")
    private String password;

    @Email(message="邮箱格式错误")
    private String email;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public List<String> getHobbies() {
        return hobbies;
    }
    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }
    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
