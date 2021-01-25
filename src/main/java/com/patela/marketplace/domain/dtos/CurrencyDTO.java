package com.patela.marketplace.domain.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CurrencyDTO {

    private Integer id;

    private String name;

    private String symbol;

    private Boolean isDeleted = false;
}
