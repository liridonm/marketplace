package com.patela.marketplace.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.patela.marketplace.annotations.MarketPlaceSerializer;
import com.patela.marketplace.serializer.MarketPlaceCustomSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
@Where(clause = "is_deleted=false")
@JsonIgnoreProperties(ignoreUnknown = true)
//TODO: should we leave to create two category with the same that are linked with the same categoryType?
public class Category extends BaseEntity<Integer> {

    private String name;

    @Column(columnDefinition = "text")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private CategoryType type;
}
