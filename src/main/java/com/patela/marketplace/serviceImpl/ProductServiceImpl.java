package com.patela.marketplace.serviceImpl;

import com.patela.marketplace.domain.dtos.CurrencyDTO;
import com.patela.marketplace.domain.dtos.ProductDTO;
import com.patela.marketplace.domain.dtos.SearchDTO;
import com.patela.marketplace.domain.dtos.UomDTO;
import com.patela.marketplace.domain.entities.Currency;
import com.patela.marketplace.domain.entities.Product;
import com.patela.marketplace.domain.entities.Uom;
import com.patela.marketplace.domain.enums.ProductState;
import com.patela.marketplace.exception.ServiceException;
import com.patela.marketplace.repository.ProductRepository;
import com.patela.marketplace.service.ProductService;
import com.patela.marketplace.util.IgnoreNullsUtil;
import com.patela.marketplace.util.MapperUtil;
import com.vladmihalcea.hibernate.type.json.JsonNodeBinaryType;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ProductServiceImpl implements ProductService {

    private final EntityManager entityManager;

    private final ProductRepository productRepository;

    private final MapperUtil mapperUtil;

    private final IgnoreNullsUtil ignoreNullsUtil;

    public ProductServiceImpl(EntityManager entityManager, ProductRepository productRepository, MapperUtil mapperUtil, IgnoreNullsUtil ignoreNullsUtil) {
        this.entityManager = entityManager;
        this.productRepository = productRepository;
        this.mapperUtil = mapperUtil;
        this.ignoreNullsUtil = ignoreNullsUtil;
    }

    @Override
    public List<ProductDTO> readAll() {
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

        validateProduct(product);

        Product createdProduct = productRepository.save(product);

        return mapperUtil.convert(createdProduct, new ProductDTO());
    }

    @Override
    public ProductDTO update(ProductDTO productDTO) throws ServiceException {
        Product product = mapperUtil.convert(productDTO, new Product());

        Product foundProduct = productRepository.findById(productDTO.getId())
                .orElseThrow(() -> new ServiceException("Product does not exists!"));

        validateProduct(foundProduct);
        ignoreNullsUtil.myCopyProperties(product, foundProduct);
        Product updatedProduct = productRepository.save(foundProduct);

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

    @Override
    public List<ProductDTO> searchProduct(SearchDTO searchDTO) {
        StringBuilder query = new StringBuilder();

        query.append("SELECT row_to_json(products) AS result ")
                .append(" FROM ( SELECT p.id, p.name, p.price, ")
                .append(" json_build_object('id', u.id, 'name', u.name) AS uom,")
                .append(" json_build_object('id', c.id, 'name', c.name) AS category,")
                .append(" json_build_object('id', c2.id, 'name', c2.name, 'symbol', c2.symbol) AS currency,")
                .append(" json_build_object('id', b.id, 'name', b.name) AS brand,")
                .append(" json_build_object('id', i.id, 'quantity', i.quantity) AS inventory")
                .append(" FROM product p JOIN brand b on b.id = p.brand_id JOIN uom u on p.uom_id = u.id")
                .append(" JOIN category c on c.id = p.category_id JOIN currency c2 on c2.id = p.currency_id ")
                .append(" JOIN tax t on t.id = p.tax_id JOIN users u2 on p.user_id = u2.id")
                .append(" JOIN product_product_attribute_value_rel ppavr on p.id = ppavr.product_id")
                .append(" JOIN product_attribute_value pav on ppavr.attribute_value_id = pav.id")
                .append(" JOIN product_attribute pa on pav.attribute_id = pa.id JOIN inventory i on p.inventory_id = i.id")
                .append(" WHERE p.is_deleted IS NOT TRUE");


        //TODO: TBD what field should be included on full text search!!!
        if (ObjectUtils.isEmpty(searchDTO.getFullTextSearch())) {
            query.append(" AND p.name ILIKE %").append(searchDTO.getFullTextSearch()).append("% ");
        }

        if (ObjectUtils.isEmpty(searchDTO.getBrand())) {
            query.append(" AND b.name ILIKE %").append(searchDTO.getBrand()).append("% ");
        }

        if (ObjectUtils.isEmpty(searchDTO.getCategory())) {
            query.append(" AND c.name ILIKE %").append(searchDTO.getCategory()).append("% ");
        }

        if (searchDTO.getValues().size() > 0) {
            IntStream.range(0, searchDTO.getValues().size()).forEach(index -> {
                if (index == 0) {
                    query.append(" AND (");
                }

                query.append(" pav.value = '").append(searchDTO.getValues().get(index)).append("'");

                if (index < searchDTO.getValues().size() - 1) {
                    query.append(" OR");
                }

                if (index == searchDTO.getValues().size() - 1) {
                    query.append(")");
                }
            });
        }


        if ((searchDTO.getMinPrice() != null && !searchDTO.getMinPrice().equals(BigDecimal.ZERO)) ||
                (searchDTO.getMaxPrice() != null && !searchDTO.getMaxPrice().equals(BigDecimal.ZERO))) {
            query.append("  AND p.price BETWEEN ")
                    .append(searchDTO.getMinPrice())
                    .append(" AND ").append(searchDTO.getMaxPrice());
        }

        query.append("  GROUP BY p.id, u.id, c.id, c2.id, b.id, i.id ) products");

        return entityManager.createNativeQuery(query.toString())
                .unwrap(NativeQuery.class)
                .addScalar("result", JsonNodeBinaryType.INSTANCE)
                .getResultList();
    }


    private void validateProduct(Product product) throws ServiceException {
        if (product.getName() == null || product.getPrice() == null || product.getPrice().equals(BigDecimal.ZERO)
                || product.getType() == null || product.getState() == null) {
            throw new ServiceException("Name, Price, Type and State can't be null");
        }
    }

}
