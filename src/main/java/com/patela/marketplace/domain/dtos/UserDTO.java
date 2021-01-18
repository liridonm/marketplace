package com.patela.marketplace.domain.dtos;

import com.patela.marketplace.domain.enums.UserRole;
import com.patela.marketplace.domain.enums.UserState;
import lombok.Getter;

@Getter
public class UserDTO {

    private Integer id;

    private String email;

    private String username;

    //    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private UserRole role;

    private Boolean isVerified;

    private UserState state;

    private Boolean isDeleted;
}