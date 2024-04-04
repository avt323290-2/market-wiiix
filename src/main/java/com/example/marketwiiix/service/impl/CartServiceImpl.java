package com.example.marketwiiix.service.impl;

import com.example.marketwiiix.dto.CartDto;
import com.example.marketwiiix.dto.ProductDto;
import com.example.marketwiiix.entity.Product;
import com.example.marketwiiix.entity.converter.ProductConverter;
import com.example.marketwiiix.repository.ProductRepository;
import com.example.marketwiiix.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Реализация сервиса корзины покупок.
 */
@Service
public class CartServiceImpl implements CartService {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    /**
     * Конструктор класса.
     * @param productRepository Репозиторий продуктов.
     * @param productConverter Конвертер продуктов.
     */
    @Autowired
    public CartServiceImpl(ProductRepository productRepository, ProductConverter productConverter) {
        this.productRepository = productRepository;
        this.productConverter = productConverter;
    }

    @Override
    public CartDto addProduct(Integer productId, CartDto cartDto) {
        List<ProductDto> products = new ArrayList<>();
        if (productId != null && cartDto != null) {
            Optional<Product> product = productRepository.findProductByProductId(productId);
            ProductDto productDto = productConverter.convertToDto(product.orElse(null));
            products = cartDto.getProducts();
            products.add(productDto);
            cartDto.setProducts(products);
        }
        return CartDto.builder()
                .products(products)
                .totalPrice(calculateTotalPrice(products))
                .quantity(products.size())
                .build();
    }

    @Override
    public CartDto removeProduct(Integer productId, CartDto cartDto) {
        List<ProductDto> products = cartDto.getProducts();
        products.removeIf(product -> product.getProductId().equals(productId));
        return CartDto.builder()
                .products(products)
                .totalPrice(calculateTotalPrice(products))
                .quantity(products.size())
                .build();
    }

    @Override
    public CartDto clear(CartDto cartDto) {
        List<ProductDto> productsDto = cartDto.getProducts();
        productsDto.clear();
        return CartDto.builder()
                .products(new ArrayList<>())
                .totalPrice(0.0)
                .quantity(0)
                .build();
    }

    @Override
    public double calculateTotalPrice(List<ProductDto> productsDto) {
        return productsDto.stream()
                .mapToDouble(ProductDto::getPrice)
                .sum();
    }
}