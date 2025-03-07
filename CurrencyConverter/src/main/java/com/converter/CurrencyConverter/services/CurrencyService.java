package com.converter.CurrencyConverter.services;

import com.converter.CurrencyConverter.Repository.CurrencyRepository;
import com.converter.CurrencyConverter.models.Currency;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CurrencyService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyService.class);

    private final CurrencyRepository currencyRepository;

    @Transactional
    public Currency create(Currency dto){
        try{
            return currencyRepository.save(dto);
        }catch (Exception e){
         LOGGER.error("Error while creating currency {}", e.getMessage());
         throw new RuntimeException("Error creating currency");
        }
    }

    @Transactional
    public Currency update(Long id, Currency dto){
        try{
            Currency existingCurrency = currencyRepository.findById(id).orElse(null);
            if(existingCurrency!=null){
                existingCurrency.setName(dto.getName());
                existingCurrency.setValue(dto.getValue());
                return currencyRepository.save(existingCurrency);
            }else{
               throw new RuntimeException("Error updating currency");
            }
        }catch (Exception e){
            LOGGER.error("Error while updating currency: {}", e.getMessage());
            throw new RuntimeException("Error updating currency");
        }
    }

    @Transactional(readOnly = true)
    public Optional<Currency> getById(Long id){
        return currencyRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Currency> getAll(){
        return currencyRepository.findAll();
    }

    @Transactional
    public HashMap<String, String> deleteById(Long id){
        try{
            HashMap<String, String> response = new HashMap<>();
            response.put("message", "Currency deleted succesfully");
            currencyRepository.deleteById(id);
            return response;
        }catch (Exception e){
            LOGGER.error("Error while deleting currency: {}", e.getMessage());
            throw new RuntimeException("Error deleting currency");
        }
    }

}
