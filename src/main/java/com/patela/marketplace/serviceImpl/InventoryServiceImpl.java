package com.patela.marketplace.serviceImpl;

import com.patela.marketplace.domain.entities.Inventory;
import com.patela.marketplace.domain.entities.Product;
import com.patela.marketplace.exception.ServiceException;
import com.patela.marketplace.repository.InventoryRepository;
import com.patela.marketplace.service.InventoryService;
import com.patela.marketplace.util.MapperUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public Inventory updateInventoryStock(Integer productId, BigDecimal quantity) throws ServiceException {

        Inventory currentInventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new ServiceException(String.format("There is no inventory for product with id %s.", productId)));


        switch (currentInventory.getStatus()) {
            case OUT_OF_STOCK:
                throw new ServiceException(String.format("Product  with id %s is out of stock.", productId));
            case ON_DEMAND:
                break;
            case IN_STOCK:
                BigDecimal qty = currentInventory.getQuantity().add(quantity);

                if (qty.compareTo(BigDecimal.ZERO) < 0) {
                    throw new ServiceException(String.format("There aren't enough quantities for product with id %s", productId));
                }
                currentInventory.setQuantity(qty);
                inventoryRepository.save(currentInventory);
        }

        return currentInventory;

    }

    @Override
    public Inventory readByProduct(Product product) throws ServiceException {
        return inventoryRepository.findByProductId(product.getId())
                .orElseThrow(() -> new ServiceException("Inventory does not exists!"));
    }

}
