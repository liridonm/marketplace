package com.patela.marketplace.serviceImpl;

import com.patela.marketplace.domain.dtos.ProductDTO;
import com.patela.marketplace.domain.dtos.UomDTO;
import com.patela.marketplace.domain.entities.Uom;
import com.patela.marketplace.exception.ServiceException;
import com.patela.marketplace.repository.UomRepository;
import com.patela.marketplace.service.ProductService;
import com.patela.marketplace.service.UomService;
import com.patela.marketplace.util.MapperUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UomServiceImpl implements UomService {

    private final UomRepository uomRepository;

    private final MapperUtil mapperUtil;

    private final ProductService productService;

    public UomServiceImpl(UomRepository uomRepository, MapperUtil mapperUtil, ProductService productService) {
        this.uomRepository = uomRepository;
        this.mapperUtil = mapperUtil;
        this.productService = productService;
    }

    @Override
    public List<UomDTO> readAll() {
        return uomRepository.findAll().stream()
                .map(uom -> mapperUtil.convert(uom, new UomDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public UomDTO readById(Integer id) throws ServiceException {
        Uom uom = uomRepository.findById(id)
                .orElseThrow(() -> new ServiceException("UOM does not exist"));
        return mapperUtil.convert(uom, new UomDTO());
    }

    @Transactional
    @Override
    public UomDTO create(UomDTO uom) throws ServiceException {
        Uom convertedUom = mapperUtil.convert(uom, new Uom());
        validateUOM(convertedUom);
        Uom createdUom = uomRepository.save(convertedUom);

        return mapperUtil.convert(createdUom, new UomDTO());
    }


    @Transactional
    @Override
    public UomDTO update(UomDTO uom) throws ServiceException {
        Uom convertedUom = mapperUtil.convert(uom, new Uom());
        readById(uom.getId());
        validateUOM(convertedUom);
        Uom updatedUom = uomRepository.save(convertedUom);

        return mapperUtil.convert(updatedUom, new UomDTO());
    }

    @Transactional
    @Override
    public void deleteById(Integer id) throws ServiceException {
        UomDTO uomDTO = readById(id);
        List<ProductDTO> productDTOS = productService.readByUom(uomDTO);

        if (productDTOS.size() > 0) {
            throw new ServiceException("Uom is linked at least by one product!");
        }

        Uom uom = mapperUtil.convert(uomDTO, new Uom());
        uom.setIsDeleted(true);

        uomRepository.save(uom);
    }

    private void validateUOM(Uom uom) throws ServiceException {
        Optional<Uom> foundUom = uomRepository.findByName(uom.getName());
        if (foundUom.isPresent()) {
            throw new ServiceException("This UOM already exist");
        }

        if (uom.getName().isEmpty()) {
            throw new ServiceException("Name can't be null");
        }
    }
}
