package com.patela.marketplace.domain.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaxDTO {
    private Integer id;
    private String name;
    private String value;
    private Boolean isDeleted = false;
}
