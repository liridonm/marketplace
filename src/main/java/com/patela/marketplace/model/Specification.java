package com.patela.marketplace.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "product_specification")
@Getter
@Setter
public class Specification extends Model<Integer> {
    private String name;
}
