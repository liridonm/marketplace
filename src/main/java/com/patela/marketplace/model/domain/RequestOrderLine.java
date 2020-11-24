package com.patela.marketplace.model.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "request_order_line")
@Getter
@Setter
public class RequestOrderLine extends Model<Integer> {
    private Integer quantity;

    private BigDecimal price;

    @Column(name = "product_history", columnDefinition = "text")
    private String productHistory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_order_id")
    private RequestOrder requestOrder;
}
