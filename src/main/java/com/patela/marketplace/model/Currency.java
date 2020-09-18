package com.patela.marketplace.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "currency",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"name", "symbol"})
        })
@Getter
@Setter
public class Currency extends Model<Integer> {

    private String name;

    private String symbol;
}
