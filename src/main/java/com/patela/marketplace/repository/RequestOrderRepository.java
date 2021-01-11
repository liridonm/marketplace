package com.patela.marketplace.repository;

import com.patela.marketplace.domain.entities.RequestOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestOrderRepository extends JpaRepository<RequestOrder, Integer> {
}
