package com.patela.marketplace.serviceImpl;

import com.patela.marketplace.domain.dtos.TaxDTO;
import com.patela.marketplace.domain.entities.Tax;
import com.patela.marketplace.exception.ServiceException;
import com.patela.marketplace.repository.TaxRepository;
import com.patela.marketplace.service.TaxService;
import com.patela.marketplace.util.MapperUtil;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TaxServiceImpl implements TaxService {
    private final TaxRepository taxRepository;
    private final MapperUtil mapperUtil;

    public TaxServiceImpl(TaxRepository taxRepository, MapperUtil mapperUtil){
        this.taxRepository = taxRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<TaxDTO> readAll() {
        return taxRepository.findAll().stream()
                .map(tax -> mapperUtil.convertToDTO(tax, new TaxDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public TaxDTO readBy(Integer id) throws ServiceException {
        Tax tax = taxRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Tax doesn't exist"));
        return mapperUtil.convertToDTO(tax, new TaxDTO());
    }

    @Override
    public TaxDTO create(TaxDTO tax) throws ServiceException {
        Tax convertTax = mapperUtil.convertToDTO(tax, new Tax());
        validateTax(convertTax);
        Tax createdTax = taxRepository.save(convertTax);
        return mapperUtil.convertToDTO(createdTax,new TaxDTO());
    }

    @Override
    public TaxDTO update(TaxDTO tax) throws ServiceException {
        Tax convertTax = mapperUtil.convertToDTO(tax,  new Tax());
        readBy(tax.getId());
        validateTax(convertTax);
        Tax updatedTax = taxRepository.save(convertTax);

        return mapperUtil.convertToDTO(updatedTax, new TaxDTO());
    }

    @Override
    public void deleteById(Integer id) throws ServiceException {
        TaxDTO foundTax = readBy(id);
        foundTax.setIsDeleted(true);
        Tax convertedTax = mapperUtil.convertToEntity(foundTax, new Tax());

        taxRepository.save(convertedTax);
    }

    private void validateTax(Tax tax) throws ServiceException{
        Optional<Tax> foundTax = taxRepository.findByName(tax.getName());
        if(foundTax.isPresent()){
            throw new ServiceException("Tax already exists");
        }

        if(tax.getName().isEmpty()){
            throw new ServiceException("Name cannot be null");
        }
    }
}
