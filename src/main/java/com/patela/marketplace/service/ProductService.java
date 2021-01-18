package com.patela.marketplace.service;

import com.patela.marketplace.domain.dtos.CurrencyDTO;
import com.patela.marketplace.domain.dtos.ProductDTO;
import com.patela.marketplace.domain.dtos.UomDTO;
import com.patela.marketplace.exception.ServiceException;

import java.util.List;

public interface ProductService extends BaseService<ProductDTO, Integer> {

    ProductDTO suspendProduct(Integer productId) throws ServiceException;

    List<ProductDTO> readByCurrency(CurrencyDTO currencyDTO);

    List<ProductDTO> readByUom(UomDTO uomDTO);
}
