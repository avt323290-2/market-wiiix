package com.example.marketwiiix.entity.converter;

import com.example.marketwiiix.dto.ProductDto;
import com.example.marketwiiix.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Класс-конвертер для преобразования между сущностями Product и объектами ProductDto.
 */
@Component
public class ProductConverter {
    private final ModelMapper modelMapper;

    @Autowired
    public ProductConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * Преобразование сущности Product в объект ProductDto.
     * @param product Сущность Product для преобразования.
     * @return Объект ProductDto.
     */
    public ProductDto convertToDto(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }

    /**
     * Преобразование объекта ProductDto в сущность Product.
     * @param productDto Объект ProductDto для преобразования.
     * @return Сущность Product.
     */
    public Product convertToEntity(ProductDto productDto) {
        return modelMapper.map(productDto, Product.class);
    }
}