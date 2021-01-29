package com.patela.marketplace.domain.dtos;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.patela.marketplace.domain.entities.BaseEntity;
import com.patela.marketplace.domain.enums.AttributeValueType;
import com.patela.marketplace.serializer.MarketPlaceCustomSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AttributeValueDTO extends BaseEntity<Integer> {

    private String label;

    private String value;

    @JsonSerialize(using = MarketPlaceCustomSerializer.class)
    private AttributeDTO attribute;

    private AttributeValueType attributeValueType;

}
