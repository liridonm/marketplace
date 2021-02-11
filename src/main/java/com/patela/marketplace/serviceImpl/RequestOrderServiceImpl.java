package com.patela.marketplace.serviceImpl;

import com.patela.marketplace.domain.dtos.RequestOrderDTO;
import com.patela.marketplace.domain.entities.RequestOrder;
import com.patela.marketplace.domain.entities.RequestOrderLine;
import com.patela.marketplace.domain.entities.User;
import com.patela.marketplace.domain.enums.OrderState;
import com.patela.marketplace.exception.ServiceException;
import com.patela.marketplace.repository.RequestOrderRepository;
import com.patela.marketplace.service.InventoryService;
import com.patela.marketplace.service.RequestOrderLineService;
import com.patela.marketplace.service.RequestOrderService;
import com.patela.marketplace.service.UserService;
import com.patela.marketplace.util.IgnoreNullsUtil;
import com.patela.marketplace.util.MapperUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RequestOrderServiceImpl implements RequestOrderService {

    private final RequestOrderRepository requestOrderRepository;

    private final MapperUtil mapperUtil;

    private final InventoryService inventoryService;

    private final RequestOrderLineService requestOrderLineService;

    private final UserService userService;

    private final IgnoreNullsUtil ignoreNullsUtil;

    public RequestOrderServiceImpl(RequestOrderRepository requestOrderRepository, MapperUtil mapperUtil, InventoryService inventoryService, RequestOrderLineService requestOrderLineService, UserService userService, IgnoreNullsUtil ignoreNullsUtil) {
        this.requestOrderRepository = requestOrderRepository;
        this.mapperUtil = mapperUtil;
        this.inventoryService = inventoryService;
        this.requestOrderLineService = requestOrderLineService;
        this.userService = userService;
        this.ignoreNullsUtil = ignoreNullsUtil;
    }

    @Override
    public List<RequestOrder> readByUserAndState(User user, OrderState state) {
        return requestOrderRepository.findByUserAndState(user, state);
    }


    @Override
    public List<RequestOrderDTO> readAll() {
        return requestOrderRepository.findAll().stream()
                .map(requestOrder -> mapperUtil.convert(requestOrder, new RequestOrderDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public RequestOrderDTO readById(Integer id) throws ServiceException {
        RequestOrder requestOrder = requestOrderRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Order does not exists!"));

        return mapperUtil.convert(requestOrder, new RequestOrderDTO());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = ServiceException.class)
    public RequestOrderDTO create(RequestOrderDTO requestOrderDTO) throws ServiceException {
        RequestOrder requestOrder = mapperUtil.convert(requestOrderDTO, new RequestOrder());

        if (requestOrder.getRequestOrderLines().isEmpty()) {
            throw new ServiceException("Order Item is required");
        }

        //check if order already exists!!
        List<RequestOrder> foundOrder = requestOrderRepository.findByUserAndState(requestOrder.getUser(), OrderState.DRAFT);
        if (!foundOrder.isEmpty()) {
            requestOrder.setId(foundOrder.get(0).getId());
        }

        createOrderLines(requestOrder, false);

        //TODO: need to build a pattern for invoice number!!!

        if (!foundOrder.isEmpty()) {
            requestOrder.getRequestOrderLines().addAll(foundOrder.get(0).getRequestOrderLines());
        }
        requestOrder.compute();

        RequestOrder order = requestOrderRepository.save(requestOrder);
        return mapperUtil.convert(order, new RequestOrderDTO());
    }

    @Override
    public RequestOrderDTO confirmOrder(RequestOrderDTO requestOrderDTO) {
        RequestOrder requestOrder = mapperUtil.convert(requestOrderDTO, new RequestOrder());
        requestOrder.setState(OrderState.CONFIRMED);

        createOrderLines(requestOrder, true);

        RequestOrder order = requestOrderRepository.save(requestOrder);
        return mapperUtil.convert(order, new RequestOrderDTO());
    }


    @Override
    public RequestOrderDTO update(RequestOrderDTO requestOrderDTO) throws ServiceException {
        RequestOrder requestOrder = mapperUtil.convert(requestOrderDTO, new RequestOrder());

        RequestOrder currentOrder = requestOrderRepository.findById(requestOrder.getId())
                .orElseThrow(() -> new ServiceException("Order does not exists!"));

        ignoreNullsUtil.myCopyProperties(requestOrder, currentOrder);

        requestOrderRepository.save(currentOrder);
        return requestOrderDTO;
    }

    @Override
    public void deleteById(Integer integer) throws ServiceException {

    }

    private void createOrderLines(RequestOrder requestOrder, Boolean confirmOrder) {
        requestOrder.getRequestOrderLines().forEach(requestOrderLine -> {
            try {
                //check if product already exists on cart!!!
                Optional<RequestOrderLine> line = requestOrderLineService.getLineByProductAndState(requestOrderLine.getProduct(), OrderState.DRAFT, requestOrder.getUser(), OrderState.DRAFT);
                line.ifPresent(orderLine -> requestOrderLine.setId(orderLine.getId()));

                if (confirmOrder) {
                    inventoryService.updateInventoryStock(requestOrderLine.getProduct().getId(), requestOrderLine.getQuantity().negate());
                    requestOrderLine.setState(OrderState.CONFIRMED);
                }
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        });
    }

}
