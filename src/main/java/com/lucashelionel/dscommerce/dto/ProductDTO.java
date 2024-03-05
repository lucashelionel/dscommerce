package com.lucashelionel.dscommerce.dto;

import com.lucashelionel.dscommerce.entities.Product;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

//validações se colocam na classe DTO, o dto que coresponde ao JSON
public class ProductDTO {

    private Long id;
    @Size(min = 3, max = 80, message = "Nome precisa ter de 3 a 80 caracteres")
    @NotBlank(message = "Campo Requerido!!!")
    private String name;
    @Size(min = 10, message = "A descrição deve ter no mínimo 10 caracteres")
    @NotBlank(message = "Campo Requerido!!!")
    private String description;
    @Positive(message = "O preço deve ser positivo")
    private double price;
    private String imgUrl;

    public ProductDTO(Long id, String name, String description, double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }
    public ProductDTO(Product entity) {
        id = entity.getId();
        name = entity.getName();
        description = entity.getDescription();
        price = entity.getPrice();
        imgUrl = entity.getImgUrl();
    }

    //só tem os geterrs no DTO pois a intenção dele é só expor os dados sem alterações no banco
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
