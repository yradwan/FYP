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
    private String userName = "";

    @Email
    @NotNull
    @NotEmpty
    private String email = "";

    @NotNull
    @NotEmpty
    @Size(min=8)
    private String password = "";

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
