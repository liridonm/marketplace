package com.patela.marketplace.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tax")
@Getter
@Setter
@NoArgsConstructor
@Where(clause = "is_deleted=false")
public class Tax extends BaseEntity<Integer> {

    private String name;

    private String value;
}
