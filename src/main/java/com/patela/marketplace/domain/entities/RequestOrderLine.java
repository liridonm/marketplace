package com.patela.marketplace.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.patela.marketplace.domain.enums.OrderState;
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
    private BigDecimal quantity;

    private BigDecimal price;

    @Column(name = "product_history", columnDefinition = "text")
    private String productHistory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @JsonBackReference("order-line")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "request_order_id")
    private RequestOrder requestOrder;

    @Enumerated(EnumType.STRING)
    private OrderState state;

    private BigDecimal total;


    @PrePersist
    @PreUpdate
    public void compute() {
        this.total = price.multiply(quantity);
    }
}
