package com.lucashelionel.dscommerce.controllers;

import com.lucashelionel.dscommerce.dto.ProductDTO;
import com.lucashelionel.dscommerce.entities.Product;
import com.lucashelionel.dscommerce.repositories.ProductRepository;
import com.lucashelionel.dscommerce.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    @Autowired
    private ProductService service;


    @GetMapping(value = "/{id}")
    public ResponseEntity <ProductDTO> findById(@PathVariable Long id){
        ProductDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);//customização de resposta onde o corpo vai ser o dto
    }


    // pageable serve para paginar o retorno dos dados sem que venham todos de uma vez
    //por padrão ele retorna uma lista com 20 elementos
    @GetMapping
    public ResponseEntity <Page<ProductDTO>> findAll(Pageable pageable)
    {
        Page<ProductDTO> dto = service.findAll(pageable);
         return ResponseEntity.ok(dto);

    }

    // @RequestBodypara indicar que é o corpo da requisição
    //@valid para iniciar a preparação e passar pelas validaçoes que estão no DTO
    @PostMapping
    public ResponseEntity <ProductDTO> insert(@Valid @RequestBody ProductDTO dto)
    {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity <ProductDTO> update(@PathVariable Long id, @Valid @RequestBody ProductDTO dto){
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity <Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
