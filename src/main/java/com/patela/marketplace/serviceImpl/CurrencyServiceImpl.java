package com.patela.marketplace.serviceImpl;

import com.patela.marketplace.domain.dtos.CurrencyDTO;
import com.patela.marketplace.domain.entities.Currency;
import com.patela.marketplace.exception.ServiceException;
import com.patela.marketplace.repository.CurrencyRepository;
import com.patela.marketplace.service.CurrencyService;
import com.patela.marketplace.util.MapperUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;

    private final MapperUtil mapperUtil;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository, MapperUtil mapperUtil) {
        this.currencyRepository = currencyRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<CurrencyDTO> readAll() {
        return currencyRepository.findAll().stream()
                .map(currency -> mapperUtil.convertToDTO(currency, new CurrencyDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public CurrencyDTO readById(Integer id) throws ServiceException {
        Currency foundCurrency = currencyRepository.findById(id).orElseThrow(() -> new ServiceException("Currency does not exist"));
        return mapperUtil.convertToDTO(foundCurrency, new CurrencyDTO());
    }

    @Override
    public CurrencyDTO create(CurrencyDTO currency) throws ServiceException {
        Currency convertCurrency = mapperUtil.convertToEntity(currency, new Currency());
        validateCurrency(convertCurrency);
        Currency createdCurrency = currencyRepository.save(convertCurrency);

        return mapperUtil.convertToDTO(createdCurrency, new CurrencyDTO());
    }

    @Override
    public CurrencyDTO update(CurrencyDTO currency) throws ServiceException {
        Currency convertCurrency = mapperUtil.convertToEntity(currency, new Currency());
        readById(currency.getId());
        validateCurrency(convertCurrency);
        Currency updatedCurrency = currencyRepository.save(convertCurrency);

        return mapperUtil.convertToDTO(updatedCurrency, new CurrencyDTO());
    }


    @Override
    public void deleteById(Integer id) throws ServiceException {
        CurrencyDTO foundCurrency = readById(id);
        foundCurrency.setIsDeleted(true);
        Currency convertCurrency = mapperUtil.convertToEntity(foundCurrency, new Currency());
        //TODO: check if currency is linked by a product.
        currencyRepository.save(convertCurrency);
    }

    private void validateCurrency(Currency currency) throws ServiceException {
        Optional<Currency> foundCurrency = currencyRepository.findByNameAndSymbol(currency.getName(), currency.getSymbol());
        if (foundCurrency.isPresent()) {
            throw new ServiceException("Currency already exists!");
        }

        if (StringUtils.isEmpty(currency.getName()) || StringUtils.isEmpty(currency.getSymbol())) {
            throw new ServiceException("Name and Symbol can't be null!");
        }
    }
}
