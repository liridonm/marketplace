package com.patela.marketplace.serviceImpl;

import com.patela.marketplace.domain.dtos.InventoryDTO;
import com.patela.marketplace.domain.entities.Inventory;
import com.patela.marketplace.domain.entities.Product;
import com.patela.marketplace.repository.InventoryRepository;
import com.patela.marketplace.service.InventoryService;
import com.patela.marketplace.util.MapperUtil;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    private final MapperUtil mapperUtil;

    public InventoryServiceImpl(InventoryRepository inventoryRepository, MapperUtil mapperUtil) {
        this.inventoryRepository = inventoryRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public InventoryDTO addInventoryOnStock(InventoryDTO inventoryDTO) throws ServiceException{
        Product convertProduct = mapperUtil.convert(inventoryDTO.getProduct(), new Product());

        Inventory inventory = inventoryRepository.findByProduct(convertProduct)
                .orElseThrow(() -> new ServiceException("Inventory does not exists!"));

        BigDecimal qty = inventory.getQuantity().add(inventoryDTO.getQuantity());

        inventory.setQuantity(qty);
        Inventory updatedInventory = inventoryRepository.save(inventory);

        return mapperUtil.convert(updatedInventory, new InventoryDTO());
    }
}
