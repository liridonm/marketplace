package com.patela.marketplace.service;

import com.patela.marketplace.domain.dtos.InventoryDTO;
import com.patela.marketplace.domain.entities.Inventory;
import com.patela.marketplace.exception.ServiceException;

public interface InventoryService {

    InventoryDTO addInventoryOnStock(InventoryDTO inventoryDTO) throws ServiceException;

}
