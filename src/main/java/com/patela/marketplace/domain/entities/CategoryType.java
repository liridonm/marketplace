package com.patela.marketplace.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "category_type")
@Getter
@Setter
@NoArgsConstructor
@Where(clause = "is_deleted=false")
public class CategoryType extends BaseEntity<Integer> {

    private String name;

    @Column(columnDefinition = "text")
    private String description;
}
