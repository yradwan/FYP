package com.packagename.myapp.backend.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.*;

@Entity
public class User extends AbstractEntity implements Cloneable {

    @NotNull
    @NotEmpty
    @Column(unique = true)
    private String userName = "";

    @NotNull
    @NotEmpty
    @Size(min=8)
    private String password = "";

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
