package com.patela.marketplace.serviceImpl;

import com.patela.marketplace.domain.dtos.UomDTO;
import com.patela.marketplace.domain.entities.Uom;
import com.patela.marketplace.exception.ServiceException;
import com.patela.marketplace.repository.UomRepository;
import com.patela.marketplace.service.UomService;
import com.patela.marketplace.util.MapperUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UomServiceImpl implements UomService {

    private final UomRepository uomRepository;

    private final MapperUtil mapperUtil;

    public UomServiceImpl(UomRepository uomRepository, MapperUtil mapperUtil) {
        this.uomRepository = uomRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<UomDTO> readAll() {
        return uomRepository.findAll().stream()
                .map(uom -> mapperUtil.convertToDTO(uom, new UomDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public UomDTO readById(Integer id) throws ServiceException {
        Uom uom = uomRepository.findById(id)
                .orElseThrow(() -> new ServiceException("UOM does not exist"));
        return mapperUtil.convertToDTO(uom, new UomDTO());
    }

    @Override
    public UomDTO create(UomDTO uom) throws ServiceException {
        Uom convertedUom = mapperUtil.convertToEntity(uom, new Uom());
        validateUOM(convertedUom);
        Uom createdUom = uomRepository.save(convertedUom);

        return mapperUtil.convertToDTO(createdUom, new UomDTO());
    }


    @Override
    public UomDTO update(UomDTO uom) throws ServiceException {
        Uom convertedUom = mapperUtil.convertToEntity(uom, new Uom());
        readById(uom.getId());
        validateUOM(convertedUom);
        Uom updatedUom = uomRepository.save(convertedUom);

        return mapperUtil.convertToDTO(updatedUom, new UomDTO());
    }

    @Override
    public void deleteById(Integer id) throws ServiceException {
        Uom uom = uomRepository.findById(id)
                .orElseThrow(() -> new ServiceException("UOM does not exist"));
        //TODO: check if UOM is linked to a product!
        uom.setIsDeleted(true);

        uomRepository.save(uom);
    }

    private void validateUOM(Uom uom) throws ServiceException {
        Optional<Uom> foundUom = uomRepository.findByName(uom.getName());
        if (foundUom.isPresent()) {
            throw new ServiceException("This UOM already exist");
        }

        if (StringUtils.isEmpty(uom.getName())) {
            throw new ServiceException("Name can't be null");
        }
    }
}
