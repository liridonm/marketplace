package com.patela.marketplace.domain.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.patela.marketplace.serializer.MarketPlaceCustomSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "request_order_line")
@Getter
@Setter
@NoArgsConstructor
@Where(clause = "is_deleted=false")
public class RequestOrderLine extends BaseEntity<Integer> {
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
