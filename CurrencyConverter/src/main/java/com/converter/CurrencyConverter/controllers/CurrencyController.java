package com.converter.CurrencyConverter.controllers;

import com.converter.CurrencyConverter.models.Currency;
import com.converter.CurrencyConverter.services.CurrencyService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/currency")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Currency>> getAll(HttpServletResponse response){
        try{
            List<Currency> currencies = currencyService.getAll();
            return ResponseEntity.ok(currencies);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Currency> getById(@PathVariable Long id){
        try{
            Optional<Currency> c = currencyService.getById(id);
            return c.map(value -> ResponseEntity.ok().body(value)).orElseGet(() ->ResponseEntity.notFound().build());
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Currency> create(@RequestBody Currency dto){
        try {
            Currency createdCurrency = currencyService.create(dto);
            return new ResponseEntity<>(createdCurrency, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Currency> update(@PathVariable Long id, @RequestBody Currency dto){
        try{
            Currency updatedCurrency = currencyService.update(id, dto);
            if(updatedCurrency!=null) return new ResponseEntity<>(updatedCurrency, HttpStatus.OK);
            else return ResponseEntity.notFound().build();
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HashMap<String, String>> deleteUser(@PathVariable Long id){
        try{
            HashMap<String, String> response = currencyService.deleteById(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
