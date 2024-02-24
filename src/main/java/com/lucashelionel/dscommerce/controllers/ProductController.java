package com.lucashelionel.dscommerce.controllers;

import com.lucashelionel.dscommerce.dto.ProductDTO;
import com.lucashelionel.dscommerce.entities.Product;
import com.lucashelionel.dscommerce.repositories.ProductRepository;
import com.lucashelionel.dscommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    @Autowired
    private ProductService service;
    @GetMapping(value = "/{id}")
    public ProductDTO findById(@PathVariable Long id){
        ProductDTO dto = service.findById(id);
        return dto;
        // ou simplemente: return service.findById(id);
    }
    // pageable serve para paginar o retorno dos dados sem que venham todos de uma vez
    //por padrão ele retorna uma lista com 20 elementos
    @GetMapping
    public Page<ProductDTO> findAll(Pageable pageable){
        return service.findAll(pageable);
    }

    @PostMapping
    public ProductDTO insert(@RequestBody ProductDTO dto) // para indicar que é o corpo da requisição
    {
        return service.insert(dto);
    }
}
