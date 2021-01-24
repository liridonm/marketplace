package com.patela.marketplace.domain.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class AttributeDTO {

    private Integer id;

    private String name;

    private String code;

    private String description;

    private Boolean isDeleted = false;
}
