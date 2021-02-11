package com.patela.marketplace.service;

import com.patela.marketplace.domain.entities.Inventory;
import com.patela.marketplace.domain.entities.Product;
import com.patela.marketplace.exception.ServiceException;

import java.math.BigDecimal;

public interface InventoryService {

    Inventory updateInventoryStock(Integer productId, BigDecimal quantity) throws ServiceException;

    Inventory readByProduct(Product product) throws ServiceException;
}
