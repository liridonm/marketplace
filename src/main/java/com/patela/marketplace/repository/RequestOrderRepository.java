package com.patela.marketplace.repository;

import com.patela.marketplace.domain.entities.RequestOrder;
import com.patela.marketplace.domain.entities.User;
import com.patela.marketplace.domain.enums.OrderState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequestOrderRepository extends JpaRepository<RequestOrder, Integer> {

    List<RequestOrder> findByUserAndState(User user, OrderState state);
}
