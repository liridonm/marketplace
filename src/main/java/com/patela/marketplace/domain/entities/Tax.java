package com.patela.marketplace.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.patela.marketplace.annotations.MarketPlaceSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "tax")
@Getter
@Setter
@NoArgsConstructor
@Where(clause = "is_deleted=false")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tax extends BaseEntity<Integer> {

    private String name;

    //TODO: change to bigDecimal
    @Positive
    private String value;
}
