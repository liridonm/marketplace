package com.patela.marketplace.domain.dtos;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.patela.marketplace.annotations.MarketPlaceSerializer;
import com.patela.marketplace.domain.entities.BaseEntity;
import com.patela.marketplace.domain.enums.ProductState;
import com.patela.marketplace.domain.enums.ProductType;
import com.patela.marketplace.serializer.MarketPlaceCustomSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@MarketPlaceSerializer(fields = {"name", "barcode"})
public class ProductDTO extends BaseEntity<Integer> {

    public String name;

    private BigDecimal price;

    private String barcode;

    private ProductState state;

    private ProductType type;

    private String description;

    @JsonSerialize(using = MarketPlaceCustomSerializer.class)
    private UserDTO user;

    @JsonSerialize(using = MarketPlaceCustomSerializer.class)
    private UomDTO uom;

    @JsonSerialize(using = MarketPlaceCustomSerializer.class)
    private TaxDTO tax;

    @JsonSerialize(using = MarketPlaceCustomSerializer.class)
    private CurrencyDTO currency;

    @JsonSerialize(using = MarketPlaceCustomSerializer.class)
    private BrandDTO brand;

    @JsonSerialize(using = MarketPlaceCustomSerializer.class)
    private CategoryDTO category;

    private List<AttributeValueDTO> attributeValues;

    private InventoryDTO inventory;

}
