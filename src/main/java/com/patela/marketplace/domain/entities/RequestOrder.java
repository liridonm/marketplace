package com.patela.marketplace.domain.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.patela.marketplace.domain.enums.OrderState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "request_order")
@Getter
@Setter
@NoArgsConstructor
@Where(clause = "is_deleted=false")
public class RequestOrder extends BaseEntity<Integer> {

    @Column(name = "order_name")
    private String orderName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private BigDecimal amount;

    @Column(name = "amount_tax")
    private BigDecimal amountTax;

    @Column(name = "amount_total")
    private BigDecimal amountTotal;

    @Enumerated(EnumType.STRING)
    private OrderState state;


    @JsonManagedReference("order-line")
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "requestOrder")
    private List<RequestOrderLine> requestOrderLines;

    @PrePersist
    @PreUpdate
    public void compute() {

        this.amount = BigDecimal.ZERO;
        this.amountTax = BigDecimal.ZERO;
        this.amountTotal = BigDecimal.ZERO;

        requestOrderLines.forEach(requestOrderLine -> {
            requestOrderLine.compute();
            this.amountTotal = amountTotal.add(requestOrderLine.getTotal());
        });
    }
}
