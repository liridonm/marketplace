package com.patela.marketplace.serviceImpl;

import com.patela.marketplace.domain.dtos.AttributeDTO;
import com.patela.marketplace.domain.entities.Attribute;
import com.patela.marketplace.exception.ServiceException;
import com.patela.marketplace.repository.AttributeRepository;
import com.patela.marketplace.service.AttributeService;
import com.patela.marketplace.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AttributeServiceImpl implements AttributeService {

    private final AttributeRepository attributeRepository;

    private final MapperUtil mapperUtil;

    public AttributeServiceImpl(AttributeRepository attributeRepository, MapperUtil mapperUtil) {
        this.attributeRepository = attributeRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<AttributeDTO> readAll() {
        return attributeRepository.findAll().stream()
                .map(attribute -> mapperUtil.convert(attribute, new AttributeDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public AttributeDTO readById(Integer id) throws ServiceException {
        Attribute attribute = attributeRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Attribute does not exists!"));
        return mapperUtil.convert(attribute, new AttributeDTO());
    }

    @Override
    public AttributeDTO create(AttributeDTO attributeDTO) throws ServiceException {

        Attribute convertedAttribute = mapperUtil.convert(attributeDTO, new Attribute());

        validateAttribute(attributeDTO);

        Attribute createdAttribute = attributeRepository.save(convertedAttribute);

        attributeDTO.setId(createdAttribute.getId());

        return attributeDTO;
    }

    @Override
    public AttributeDTO update(AttributeDTO attributeDTO) throws ServiceException {

        Attribute convertedAttribute = mapperUtil.convert(attributeDTO, new Attribute());
        readById(attributeDTO.getId());
        validateAttribute(attributeDTO);

        attributeRepository.save(convertedAttribute);

        return attributeDTO;
    }


    @Override
    public void deleteById(Integer id) throws ServiceException {
        Attribute attribute = attributeRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Attribute does not exists!"));

        //TODO: check if attribute is linked with attribute value.
        attribute.setIsDeleted(true);
        attributeRepository.save(attribute);
    }


    private void validateAttribute(AttributeDTO attributeDTO) throws ServiceException {
        Optional<Attribute> foundAttribute = attributeRepository.findByNameAndCode(attributeDTO.getName(),
                attributeDTO.getCode());

        if (foundAttribute.isPresent()) {
            throw new ServiceException("Attribute with name and code already exists");
        }
    }

}
