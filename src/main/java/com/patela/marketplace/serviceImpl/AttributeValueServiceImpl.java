package com.patela.marketplace.serviceImpl;

import com.patela.marketplace.domain.dtos.AttributeValueDTO;
import com.patela.marketplace.domain.entities.AttributeValue;
import com.patela.marketplace.exception.ServiceException;
import com.patela.marketplace.repository.AttributeValueRepository;
import com.patela.marketplace.service.AttributeValueService;
import com.patela.marketplace.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AttributeValueServiceImpl implements AttributeValueService {

    private final AttributeValueRepository attributeValueRepository;

    private final MapperUtil mapperUtil;

    public AttributeValueServiceImpl(AttributeValueRepository attributeValueRepository, MapperUtil mapperUtil) {
        this.attributeValueRepository = attributeValueRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<AttributeValueDTO> readAll() {
        return attributeValueRepository.findAll()
                .stream()
                .map(attributeValue -> mapperUtil.convert(attributeValue, new AttributeValueDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public AttributeValueDTO readById(Integer id) throws ServiceException {

        AttributeValue foundAttributeValue = attributeValueRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Value does not exists"));

        return mapperUtil.convert(foundAttributeValue, new AttributeValueDTO());
    }

    @Override
    public AttributeValueDTO create(AttributeValueDTO attributeValueDTO) throws ServiceException {
        AttributeValue convertedAttributeValue = mapperUtil.convert(attributeValueDTO, new AttributeValue());

        validateAttributeValue(attributeValueDTO);

        AttributeValue createdAttributeValue = attributeValueRepository.save(convertedAttributeValue);
        attributeValueDTO.setId(createdAttributeValue.getId());

        return attributeValueDTO;
    }

    @Override
    public AttributeValueDTO update(AttributeValueDTO attributeValueDTO) throws ServiceException {

        AttributeValue convertedAttributeValue = mapperUtil.convert(attributeValueDTO, new AttributeValue());
        readById(attributeValueDTO.getId());
        validateAttributeValue(attributeValueDTO);
        attributeValueRepository.save(convertedAttributeValue);

        return attributeValueDTO;
    }



    @Override
    public void deleteById(Integer id) throws ServiceException {
        AttributeValue foundAttributeValue = attributeValueRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Value does not exists"));

        foundAttributeValue.setIsDeleted(true);
        attributeValueRepository.save(foundAttributeValue);
    }

    private void validateAttributeValue(AttributeValueDTO attributeValueDTO) throws ServiceException {
        Optional<AttributeValue> foundAttributeValue = attributeValueRepository.findByValueAndAttributeId(attributeValueDTO.getValue(),
                attributeValueDTO.getAttribute().getId());

        if (foundAttributeValue.isPresent()) {
            throw new ServiceException("Attribute value already exists!");
        }
    }
}
