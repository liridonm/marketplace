package com.patela.marketplace.domain.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class SearchDTO {

    private String fullTextSearch;
    private String brand;
    private String category;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private List<String> values = new ArrayList<>();
}
