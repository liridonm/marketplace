package com.patela.marketplace.repository;

import com.patela.marketplace.domain.entities.RequestOrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestOrderLineRepository extends JpaRepository<RequestOrderLine, Integer> {
}
