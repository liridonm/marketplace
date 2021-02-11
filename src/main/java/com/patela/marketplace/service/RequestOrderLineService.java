package com.patela.marketplace.service;

import com.patela.marketplace.domain.dtos.RequestOrderLineDTO;
import com.patela.marketplace.domain.entities.Product;
import com.patela.marketplace.domain.entities.RequestOrderLine;
import com.patela.marketplace.domain.entities.User;
import com.patela.marketplace.domain.enums.OrderState;
import com.patela.marketplace.exception.ServiceException;

import java.util.Optional;

public interface RequestOrderLineService {

    RequestOrderLineDTO update(RequestOrderLineDTO requestOrderLineDTOS) throws ServiceException;

    Optional<RequestOrderLine> getLineByProductAndState(Product product, OrderState lineState, User user, OrderState state);

    void deleteById(Integer integer) throws ServiceException;
}
