package com.patela.marketplace.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.patela.marketplace.domain.entities.BaseEntity;
import com.patela.marketplace.domain.enums.StockStatus;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class InventoryDTO extends BaseEntity<Integer> {


    private BigDecimal minQuantity;

    private BigDecimal maxQuantity;

    private BigDecimal quantity;

    private BigDecimal weight;

    private BigDecimal volume;

    private StockStatus status;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private ProductDTO product;

}
