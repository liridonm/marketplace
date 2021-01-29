package com.patela.marketplace.domain.dtos;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.patela.marketplace.annotations.MarketPlaceSerializer;
import com.patela.marketplace.domain.entities.BaseEntity;
import com.patela.marketplace.serializer.MarketPlaceCustomSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@MarketPlaceSerializer(fields = {"name", "code"})
public class AttributeDTO extends BaseEntity<Integer> {

    private String name;

    private String code;

    private String description;

    @JsonSerialize(using = MarketPlaceCustomSerializer.class)
    private SpecificationDTO specificationDTO;

}
