package com.patela.marketplace.domain.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.patela.marketplace.domain.enums.StockStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "inventory")
@Getter
@Setter
@NoArgsConstructor
@Where(clause = "is_deleted=false")
public class Inventory extends BaseEntity<Integer> {

    @Column(name = "min_quantity")
    private BigDecimal minQuantity;

    @Column(name = "max_quantity")
    private BigDecimal maxQuantity;

    private BigDecimal quantity;

    private BigDecimal weight;

    private BigDecimal volume;

    @Enumerated(EnumType.STRING)
    private StockStatus status;

    @OneToOne(mappedBy = "inventory")
    private Product product;
}
