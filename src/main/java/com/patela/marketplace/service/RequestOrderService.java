package com.patela.marketplace.service;

import com.patela.marketplace.domain.dtos.RequestOrderDTO;
import com.patela.marketplace.domain.entities.RequestOrder;
import com.patela.marketplace.domain.entities.User;
import com.patela.marketplace.domain.enums.OrderState;

import java.util.List;

public interface RequestOrderService extends BaseService<RequestOrderDTO, Integer>{

    List<RequestOrder> readByUserAndState(User user, OrderState state);

    RequestOrderDTO confirmOrder(RequestOrderDTO requestOrderDTO);

}
