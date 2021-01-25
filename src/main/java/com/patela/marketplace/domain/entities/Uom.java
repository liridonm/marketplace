package com.patela.marketplace.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "uom")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "is_deleted = false ")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Uom extends BaseEntity<Integer> {

    @Column(unique = true)
    private String name;

    @Column(columnDefinition = "text")
    private String description;
}

