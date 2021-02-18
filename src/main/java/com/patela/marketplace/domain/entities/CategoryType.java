package com.patela.marketplace.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.patela.marketplace.annotations.MarketPlaceSerializer;
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
@JsonIgnoreProperties(ignoreUnknown = true)
//TODO: should name be unique? - DONE
public class CategoryType extends BaseEntity<Integer> {
    @Column(unique = true)
    private String name;

    @Column(columnDefinition = "text")
    private String description;
}
