package com.patela.marketplace.model.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "category_type")
@Getter
@Setter
public class CategoryType extends Model<Integer>{

    private String name;

    @Column(columnDefinition = "text")
    private String description;
}
