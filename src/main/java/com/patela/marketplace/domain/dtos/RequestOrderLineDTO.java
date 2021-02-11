package com.patela.marketplace.domain.dtos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.patela.marketplace.domain.entities.BaseEntity;
import com.patela.marketplace.domain.entities.Product;
import com.patela.marketplace.domain.entities.RequestOrder;
import com.patela.marketplace.domain.enums.OrderState;
import com.patela.marketplace.serializer.MarketPlaceCustomSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class RequestOrderLineDTO extends BaseEntity<Integer> {

    private BigDecimal quantity;

    private BigDecimal price;

    private String productHistory;

    @JsonSerialize(using = MarketPlaceCustomSerializer.class)
    private ProductDTO product;

    @JsonBackReference("order-line")
    private RequestOrderDTO requestOrder;

    private OrderState state;

    private BigDecimal total;
}
