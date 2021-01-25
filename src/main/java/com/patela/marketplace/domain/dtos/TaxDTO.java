package com.patela.marketplace.domain.dtos;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaxDTO {
    private Integer id;
    private String name;
    private String value;
    private Boolean isDeleted = false;
}
