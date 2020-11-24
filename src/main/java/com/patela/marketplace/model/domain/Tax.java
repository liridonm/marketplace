package com.patela.marketplace.model.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tax")
@Getter
@Setter
public class Tax extends Model<Integer> {

    private String name;

    private String value;
}
