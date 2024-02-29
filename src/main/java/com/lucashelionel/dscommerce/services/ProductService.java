package com.lucashelionel.dscommerce.services;

import com.lucashelionel.dscommerce.dto.ProductDTO;
import com.lucashelionel.dscommerce.entities.Product;
import com.lucashelionel.dscommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;
    @Transactional(readOnly = true)
    public ProductDTO findById(Long id){
        Optional<Product> result = repository.findById(id);
        Product product = result.get();
        ProductDTO dto = new ProductDTO(product);
        return dto;
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable){
     Page<Product> result = repository.findAll(pageable);
     return result.map(x -> new ProductDTO(x));
    }

    @Transactional //sem o read only pois é uma operação de salvar no banco
    public ProductDTO insert(ProductDTO dto){
        //esse metodo de salvamento segue essa ordem: instanciou, copiou e salvou
        Product entity = new Product();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ProductDTO(entity);
    }


    @Transactional
    public ProductDTO update(Long id, ProductDTO dto){
        // diferentes dos anteriores ele não vai diretamente no banco de dados
        //esse metodo de salvamento segue essa ordem: instanciou com a referência, copiou e salvou
        Product entity = repository.getReferenceById(id);
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ProductDTO(entity);
    }


    @Transactional
    public void delete(Long id){
        repository.deleteById(id);
    }

    private void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());
    }
}
