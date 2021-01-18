package com.patela.marketplace.domain.dtos;

import com.patela.marketplace.domain.entities.Product;
import com.patela.marketplace.domain.enums.StockStatus;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class InventoryDTO {

    private BigDecimal minQuantity;

    private BigDecimal maxQuantity;

    private BigDecimal quantity;

    private BigDecimal weight;

    private BigDecimal volume;

    private StockStatus status;

    private ProductDTO product;
}
