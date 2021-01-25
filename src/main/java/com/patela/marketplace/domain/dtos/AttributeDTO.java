package com.patela.marketplace.domain.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AttributeDTO {

    private Integer id;

    private String name;

    private String code;

    private String description;

    private Boolean isDeleted = false;
}
