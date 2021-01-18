package com.patela.marketplace.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "product_attribute")
@Getter
@Setter
@NoArgsConstructor
@Where(clause = "is_deleted=false")
public class Attribute extends BaseEntity<Integer> {

    private String name;

    private String code;

    @Column(columnDefinition = "text")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "specification_id")
    private Specification specification;
}