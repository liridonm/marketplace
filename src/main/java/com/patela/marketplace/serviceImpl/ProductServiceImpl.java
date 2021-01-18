package com.patela.marketplace.serviceImpl;

import com.patela.marketplace.domain.dtos.CurrencyDTO;
import com.patela.marketplace.domain.dtos.ProductDTO;
import com.patela.marketplace.domain.dtos.UomDTO;
import com.patela.marketplace.domain.entities.Currency;
import com.patela.marketplace.domain.entities.Product;
import com.patela.marketplace.domain.entities.Uom;
import com.patela.marketplace.domain.enums.ProductState;
import com.patela.marketplace.exception.ServiceException;
import com.patela.marketplace.repository.ProductRepository;
import com.patela.marketplace.service.ProductService;
import com.patela.marketplace.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final MapperUtil mapperUtil;

    public ProductServiceImpl(ProductRepository productRepository, MapperUtil mapperUtil) {
        this.productRepository = productRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<ProductDTO> readAll() {
        //TODO: need to written in native query because of performance!!!
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> mapperUtil.convert(product, new ProductDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO readById(Integer id) throws ServiceException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Product does not exists!"));

        return mapperUtil.convert(product, new ProductDTO());
    }

    @Override
    public ProductDTO create(ProductDTO productDTO) throws ServiceException {
        Product product = mapperUtil.convert(productDTO, new Product());

        Product createdProduct = validateAndSaveProduct(product);

        return mapperUtil.convert(createdProduct, new ProductDTO());
    }

    @Override
    public ProductDTO update(ProductDTO productDTO) throws ServiceException {
        Product product = mapperUtil.convert(productDTO, new Product());

        readById(product.getId());

        Product updatedProduct = validateAndSaveProduct(product);

        return mapperUtil.convert(updatedProduct, new ProductDTO());
    }


    @Override
    public void deleteById(Integer id) throws ServiceException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Product does not exists!"));

        product.setIsDeleted(true);
        product.setState(ProductState.DELETED);
        productRepository.save(product);
    }


    @Override
    public ProductDTO suspendProduct(Integer productId) throws ServiceException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ServiceException("Product does not exists!"));

        product.setState(ProductState.SUSPENDED);
        productRepository.save(product);
        return null;
    }

    @Override
    public List<ProductDTO> readByCurrency(CurrencyDTO currencyDTO) {
        Currency currency = mapperUtil.convert(currencyDTO, new Currency());
        return productRepository.findByCurrency(currency).stream()
                .map(product -> mapperUtil.convert(product, new ProductDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> readByUom(UomDTO uomDTO) {
        Uom uom = mapperUtil.convert(uomDTO, new Uom());
        return productRepository.findByUom(uom).stream()
                .map(product -> mapperUtil.convert(product, new ProductDTO()))
                .collect(Collectors.toList());
    }

    private Product validateAndSaveProduct(Product product) throws ServiceException {
        if (product.getName() == null || product.getPrice() == null || product.getPrice().equals(BigDecimal.ZERO)
                || product.getType() == null || product.getState() == null) {
            throw new ServiceException("Name, Price, Type and State can't be null");
        }

        return productRepository.save(product);
    }
}
