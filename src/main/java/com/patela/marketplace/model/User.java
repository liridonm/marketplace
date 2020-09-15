package com.patela.marketplace.model;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "account_user")
@Getter
@Setter
//@Where(clause = "deleted = false")
public class User extends Model<Integer> {

    @NotNull
    private String username;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 6, message = "Password should contain minimum 6 characters.")
    private String password;

    private boolean isVerified = false;

    private boolean active = false;
}
