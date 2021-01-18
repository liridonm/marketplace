package com.patela.marketplace.domain.dtos;

import com.patela.marketplace.domain.entities.CategoryType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO
{
    private Integer id;
    private String name;
    private String description;
    private CategoryType type;
    private Boolean isDeleted = false;
}
