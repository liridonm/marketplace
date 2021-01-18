package com.patela.marketplace.domain.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryTypeDTO {
    private Integer id;
    private String name;
    private String description;
    private Boolean isDeleted;
}
