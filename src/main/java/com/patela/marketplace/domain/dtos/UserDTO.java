package com.patela.marketplace.domain.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.patela.marketplace.domain.enums.UserRole;
import com.patela.marketplace.domain.enums.UserState;
import lombok.Data;
import lombok.Getter;

@Data
public class UserDTO {

    private Integer id;

    private String email;

    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private UserRole role;

    private Boolean isVerified;

    private UserState state;

    private Boolean isDeleted = false;
}
