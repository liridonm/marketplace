package com.patela.marketplace.domain.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpecificationDTO {
    private Integer id;
    private String name;
    private Boolean isDeleted = false;
}
