package com.patela.marketplace.domain.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandDTO {
    private Integer id;
    private String name;
    private String barcode;
    private Boolean isDeleted = false;
}
