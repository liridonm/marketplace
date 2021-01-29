package com.patela.marketplace.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.patela.marketplace.annotations.MarketPlaceSerializer;
import com.patela.marketplace.domain.entities.BaseEntity;
import com.patela.marketplace.domain.enums.UserRole;
import com.patela.marketplace.domain.enums.UserState;
import lombok.Data;

@Data
@MarketPlaceSerializer(fields = {"email", "username", "firstName", "lastName", "role"})
public class UserDTO extends BaseEntity<Integer> {

    private String email;

    private String username;

    private String firstName;

    private String lastName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private UserRole role;

    private Boolean isVerified;

    private UserState state;

}
