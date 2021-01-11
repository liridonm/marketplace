package com.patela.marketplace.domain.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CurrencyDTO {

    private Integer id;

    private String name;

    private String symbol;

    private Boolean isDeleted;
}
