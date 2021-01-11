package com.patela.marketplace.domain.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.patela.marketplace.annotations.IgnoreMapping;
import com.patela.marketplace.domain.enums.AttributeValueType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product_attribute_value")
@Getter
@Setter
@NoArgsConstructor
@Where(clause = "is_deleted=false")
public class AttributeValue extends BaseEntity<Integer> {

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
