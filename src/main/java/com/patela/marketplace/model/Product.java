package com.patela.marketplace.model;

import com.patela.marketplace.model.common.ProductState;
import com.patela.marketplace.model.common.ProductType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "product")
@Getter
@Setter
//@Where(clause = "deleted = false")
public class Product extends Model<Integer> {

    public String name;

    private BigDecimal price;

    private String barcode;

    @Enumerated(EnumType.STRING)
    private ProductState state;

    @Enumerated(EnumType.STRING)
    private ProductType type = ProductType.SIMPLE;

    @Column(columnDefinition = "text")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Product Attribute Values
     */
    @ManyToMany
    @JoinTable(
            name = "product_product_attribute_value_rel",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_value_id")
    )
    private List<AttributeValue> attributeValues;

}
