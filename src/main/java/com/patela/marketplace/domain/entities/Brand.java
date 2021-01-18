package com.patela.marketplace.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "brand")
@Getter
@Setter
@NoArgsConstructor
@Where(clause = "is_deleted=false")
public class Brand extends BaseEntity<Integer> {

    private String name;

    private String barcode;
}
