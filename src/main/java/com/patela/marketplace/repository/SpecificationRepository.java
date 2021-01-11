package com.patela.marketplace.repository;

import com.patela.marketplace.domain.entities.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecificationRepository extends JpaRepository<Specification, Integer> {
}
