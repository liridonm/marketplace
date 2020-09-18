package com.patela.marketplace.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "uom")
@Getter
@Setter
public class Uom extends Model<Integer> {

    private String name;

    private String description;
}

