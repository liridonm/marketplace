package com.patela.marketplace.repository;

import com.patela.marketplace.domain.entities.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Integer> {

    Optional<Attribute> findByNameAndCode(String name, String code);
}
