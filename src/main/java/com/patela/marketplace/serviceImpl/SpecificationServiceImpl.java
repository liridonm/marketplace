package com.patela.marketplace.serviceImpl;

import com.patela.marketplace.domain.dtos.SpecificationDTO;
import com.patela.marketplace.domain.entities.Specification;
import com.patela.marketplace.exception.ServiceException;
import com.patela.marketplace.repository.SpecificationRepository;
import com.patela.marketplace.service.SpecificationService;
import com.patela.marketplace.util.MapperUtil;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SpecificationServiceImpl implements SpecificationService {
    private final SpecificationRepository specificationRepository;
    private final MapperUtil mapperUtil;

    public SpecificationServiceImpl(SpecificationRepository specificationRepository, MapperUtil mapperUtil){
        this.specificationRepository = specificationRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<SpecificationDTO> readAll() {
        return specificationRepository.findAll().stream()
                .map(specification -> mapperUtil.convertToDTO(specification, new SpecificationDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public SpecificationDTO readById(Integer id) throws ServiceException {
        Specification specification = specificationRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Specification does not exist"));
        return mapperUtil.convertToDTO(specification, new SpecificationDTO());
    }

    @Override
    public SpecificationDTO create(SpecificationDTO specification) throws ServiceException {
        Specification convertSpecification = mapperUtil.convertToDTO(specification, new Specification());
        validateSpecification(convertSpecification);
        Specification createdSpecification = specificationRepository.save(convertSpecification);
        return mapperUtil.convertToDTO(createdSpecification, new SpecificationDTO());
    }

    @Override
    public SpecificationDTO update(SpecificationDTO specification) throws ServiceException {
        Specification convertSpecification = mapperUtil.convertToDTO(specification, new Specification());
        readById(specification.getId());
        validateSpecification(convertSpecification);
        Specification updatedBrand = specificationRepository.save(convertSpecification);

        return mapperUtil.convertToDTO(updatedBrand, new SpecificationDTO());
    }

    @Override
    public void deleteById(Integer id) throws ServiceException {
        SpecificationDTO foundSpecification = readById(id);
        foundSpecification.setIsDeleted(true);
        Specification convertedSpecification = mapperUtil.convertToEntity(foundSpecification, new Specification() );

        specificationRepository.save(convertedSpecification);
    }

    private void validateSpecification(Specification specification) throws ServiceException{
        Optional<Specification> foundSpecification = specificationRepository.findByName(specification.getName());
        if (foundSpecification.isPresent()) {
            throw new ServiceException("Specification already exists");
        }

        if (StringUtils.isEmpty(specification.getName())) {
            throw new ServiceException("Name cannot be null");
        }
    }
}
