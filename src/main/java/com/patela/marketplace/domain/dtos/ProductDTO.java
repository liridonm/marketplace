package com.patela.marketplace.domain.dtos;

import com.patela.marketplace.domain.entities.*;
import com.patela.marketplace.domain.enums.ProductState;
import com.patela.marketplace.domain.enums.ProductType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {

    private Integer id;

    public String name;

    private BigDecimal price;

    private String barcode;

    private ProductState state;

    private ProductType type;

    private String description;

    private UserDTO user;

    private UomDTO uom;

    private Tax tax;

    private CurrencyDTO currency;

    private Brand brand;

    private Category category;

    private List<AttributeValue> attributeValues;

    private Boolean isDeleted = false;

    private InventoryDTO inventory;

}
