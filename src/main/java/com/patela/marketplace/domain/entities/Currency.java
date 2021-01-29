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
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "currency",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"name", "symbol"})
        }
        )
@Getter
@Setter
@NoArgsConstructor
@Where(clause = "is_deleted=false")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Currency extends BaseEntity<Integer> {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String symbol;
}
