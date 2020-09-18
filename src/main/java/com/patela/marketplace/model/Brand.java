package com.patela.marketplace.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "brand")
@Getter
@Setter
public class Brand extends Model<Integer> {

    private String name;

    private String barcode;
}
