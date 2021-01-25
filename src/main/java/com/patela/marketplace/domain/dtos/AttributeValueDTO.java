package com.patela.marketplace.domain.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.patela.marketplace.domain.entities.Attribute;
import com.patela.marketplace.domain.enums.AttributeValueType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AttributeValueDTO {

    private Integer id;

    private String label;

    private String value;

    private Attribute attribute;

    private AttributeValueType attributeValueType;

    private Boolean isDeleted = false;
}
