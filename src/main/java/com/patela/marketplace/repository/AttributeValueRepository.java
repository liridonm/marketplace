package com.patela.marketplace.repository;

import com.patela.marketplace.domain.entities.AttributeValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttributeValueRepository extends JpaRepository<AttributeValue, Integer> {

    Optional<AttributeValue> findByValueAndAttributeId(String value, Integer attributeId);

    List<AttributeValue> findByAttributeId(Integer attributeId);
}
