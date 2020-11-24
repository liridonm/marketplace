package com.patela.marketplace.model.domain;

import com.patela.marketplace.model.common.StockStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table
@Getter
@Setter
public class Inventory extends Model<Integer> {

    @Column(name = "min_quantity")
    private BigDecimal minQuantity;

    @Column(name = "max_quantity")
    private BigDecimal maxQuantity;

    private BigDecimal quantity;

    private BigDecimal weight;

    private BigDecimal volume;

    @Enumerated(EnumType.STRING)
    private StockStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}
