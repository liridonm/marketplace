package com.patela.marketplace.model;

import com.patela.marketplace.model.common.OrderState;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "request_order")
@Getter
@Setter
public class RequestOrder extends Model<Integer> {

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
