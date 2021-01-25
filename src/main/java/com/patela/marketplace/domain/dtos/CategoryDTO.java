package com.patela.marketplace.domain.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.patela.marketplace.domain.entities.CategoryType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDTO
{
    private Integer id;
    private String name;
    private String description;
    private CategoryType type;
    private Boolean isDeleted = false;
}
