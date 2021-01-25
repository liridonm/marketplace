package com.patela.marketplace.domain.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
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

    private TaxDTO tax;

    private CurrencyDTO currency;

    private BrandDTO brand;

    private CategoryDTO category;

    private List<AttributeValueDTO> attributeValues;

    private Boolean isDeleted = false;

    private InventoryDTO inventory;

}
