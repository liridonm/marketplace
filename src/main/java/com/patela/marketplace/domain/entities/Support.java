package com.patela.marketplace.domain.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.patela.marketplace.serializer.MarketPlaceCustomSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "support")
@Getter
@Setter
@NoArgsConstructor
@Where(clause = "is_deleted=false")
public class Support extends BaseEntity<Integer> {

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}
