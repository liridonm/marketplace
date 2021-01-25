package com.patela.marketplace.domain.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.patela.marketplace.domain.enums.AttributeValueType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "product_attribute_value",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"value", "attribute_id"})
        })
@Getter
@Setter
@NoArgsConstructor
@Where(clause = "is_deleted=false")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AttributeValue extends BaseEntity<Integer> {

    private String label;

    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attribute_id")
    private Attribute attribute;

    @Enumerated(EnumType.STRING)
    private AttributeValueType attributeValueType;

}
