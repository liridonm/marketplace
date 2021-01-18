package com.patela.marketplace.domain.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UomDTO {

    private Integer id;

    private String name;

    private String description;

    private Boolean isDeleted = false;
}
