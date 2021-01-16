package com.patela.marketplace.repository;

import com.patela.marketplace.domain.entities.Brand;
import com.patela.marketplace.domain.entities.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpecificationRepository extends JpaRepository<Specification, Integer> {
    Optional<Specification> findByName (String name);
}
