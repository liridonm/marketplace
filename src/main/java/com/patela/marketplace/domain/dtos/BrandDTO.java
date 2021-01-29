package com.patela.marketplace.domain.dtos;

import com.patela.marketplace.annotations.MarketPlaceSerializer;
import com.patela.marketplace.domain.entities.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@MarketPlaceSerializer(fields = {"name", "barcode"})
public class BrandDTO extends BaseEntity<Integer> {
    private String name;
    private String barcode;
}
