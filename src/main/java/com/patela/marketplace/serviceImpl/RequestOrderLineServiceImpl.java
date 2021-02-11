package com.patela.marketplace.serviceImpl;

import com.patela.marketplace.domain.dtos.RequestOrderLineDTO;
import com.patela.marketplace.domain.entities.Product;
import com.patela.marketplace.domain.entities.RequestOrderLine;
import com.patela.marketplace.domain.entities.User;
import com.patela.marketplace.domain.enums.OrderState;
import com.patela.marketplace.exception.ServiceException;
import com.patela.marketplace.repository.RequestOrderLineRepository;
import com.patela.marketplace.service.RequestOrderLineService;
import com.patela.marketplace.util.IgnoreNullsUtil;
import com.patela.marketplace.util.MapperUtil;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class RequestOrderLineServiceImpl implements RequestOrderLineService {

    private final RequestOrderLineRepository requestOrderLineRepository;

    private final MapperUtil mapperUtil;

    private final IgnoreNullsUtil ignoreNullsUtil;

    public RequestOrderLineServiceImpl(RequestOrderLineRepository requestOrderLineRepository, MapperUtil mapperUtil, IgnoreNullsUtil ignoreNullsUtil) {
        this.requestOrderLineRepository = requestOrderLineRepository;
        this.mapperUtil = mapperUtil;
        this.ignoreNullsUtil = ignoreNullsUtil;
    }


    @Override
    @Transactional
    public RequestOrderLineDTO update(RequestOrderLineDTO requestOrderLineDTO) throws ServiceException {
        RequestOrderLine orderLine = mapperUtil.convert(requestOrderLineDTO, new RequestOrderLine());

        RequestOrderLine foundLine = requestOrderLineRepository.findById(orderLine.getId())
                .orElseThrow(() -> new ServiceException("Order line does not exists!"));

        ignoreNullsUtil.myCopyProperties(orderLine, foundLine);
        requestOrderLineRepository.save(foundLine);
        return requestOrderLineDTO;
    }

    @Override
    public Optional<RequestOrderLine> getLineByProductAndState(Product product, OrderState lineState, User user, OrderState state) {
        return requestOrderLineRepository.findByProductAndStateAndRequestOrderUserAndRequestOrderState(product, lineState, user, state);
    }

    @Override
    public void deleteById(Integer id) throws ServiceException {
        RequestOrderLine orderLine = requestOrderLineRepository.findById(id).orElseThrow(() -> new ServiceException("Item does not exists!"));

        if (orderLine.getState() != OrderState.DRAFT) {
            throw new ServiceException("Cannot delete order line!");
        }

        orderLine.setIsDeleted(true);
        orderLine.setState(OrderState.DELETED);

        requestOrderLineRepository.save(orderLine);

    }
}
