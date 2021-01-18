package com.patela.marketplace.domain.entities;

import com.patela.marketplace.domain.enums.OrderState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;

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
}
