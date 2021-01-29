package com.patela.marketplace.domain.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.patela.marketplace.serializer.MarketPlaceCustomSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "faq")
@Getter
@Setter
@NoArgsConstructor
@Where(clause = "is_deleted=false")
public class Faq extends BaseEntity<Integer> {

    @Column(columnDefinition = "text")
    private String question;

    @Column(columnDefinition = "text")
    private String answer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}
