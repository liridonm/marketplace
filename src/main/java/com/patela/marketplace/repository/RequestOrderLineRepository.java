package com.patela.marketplace.repository;

import com.patela.marketplace.domain.entities.Product;
import com.patela.marketplace.domain.entities.RequestOrderLine;
import com.patela.marketplace.domain.entities.User;
import com.patela.marketplace.domain.enums.OrderState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RequestOrderLineRepository extends JpaRepository<RequestOrderLine, Integer> {

    Optional<RequestOrderLine> findByProductAndStateAndRequestOrderUserAndRequestOrderState(Product product,
                                                                                            OrderState lineState,
                                                                                            User user,
                                                                                            OrderState orderState);
}
