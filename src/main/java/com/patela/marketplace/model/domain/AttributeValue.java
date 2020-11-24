package com.patela.marketplace.model.domain;

import com.patela.marketplace.model.annotation.IgnoreMapping;
import com.patela.marketplace.model.common.AttributeValueType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product_attribute_value")
@Getter
@Setter
public class AttributeValue extends Model<Integer> {

    private String label;

    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attribute_id")
    private Attribute attribute;

    @Enumerated(EnumType.STRING)
    private AttributeValueType attributeValueType;

    @ManyToMany(mappedBy = "attributeValues")
    @IgnoreMapping
    private List<Product> products;
}
