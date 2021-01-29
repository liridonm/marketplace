package com.patela.marketplace.domain.dtos;

import com.patela.marketplace.annotations.MarketPlaceSerializer;
import com.patela.marketplace.domain.entities.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MarketPlaceSerializer(fields = {"name"})
public class SpecificationDTO extends BaseEntity<Integer> {
    private String name;
}
