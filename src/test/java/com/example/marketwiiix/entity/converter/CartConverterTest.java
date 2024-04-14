package com.example.marketwiiix.entity.converter;

import com.example.marketwiiix.dto.CartDto;
import com.example.marketwiiix.dto.ProductDto;
import com.example.marketwiiix.entity.Cart;
import com.example.marketwiiix.entity.Product;
import com.example.marketwiiix.MarketWiiixApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = MarketWiiixApplication.class)
class CartConverterTest {

    /**
     * Класс для тестирования конвертера корзины.
     */
    @Autowired
    private CartConverter cartConverter;

    /**
     * Конвертирует корзину из сущности в DTO.
     */
    @Test
    void convertToDto() {
        // Создание тестовых данных для DTO продукта
        String nameDto = "product name test";
        String descriptionDto = "description test";
        double priceDto = 0.0;
        ProductDto productDto = ProductDto.builder()
                .name(nameDto)
                .description(descriptionDto)
                .price(priceDto)
                .build();
        List<ProductDto> productsDto = new ArrayList<>();
        productsDto.add(productDto);
        // Создание ожидаемого DTO корзины
        int quantity = 1;
        CartDto expected = CartDto.builder()
                .products(productsDto)
                .totalPrice(priceDto)
                .quantity(quantity)
                .build();

        // Создание тестовых данных для сущности продукта
        Integer productId = 0;
        String name = "product name test";
        String description = "description test";
        double price = 0.0;
        Product product = Product.builder()
                .productId(productId)
                .name(name)
                .description(description)
                .price(price)
                .build();
        List<Product> products = new ArrayList<>();
        products.add(product);
        // Создание корзины для конвертации
        Cart cart = Cart.builder()
                .products(products)
                .totalPrice(price)
                .quantity(quantity)
                .build();
        // Выполнение конвертации и проверка результата
        CartDto actual = cartConverter.convertToDto(cart);
        assertEquals(expected, actual);
    }
    /**
     * Конвертирует корзину из DTO в сущность.
     */
    @Test
    void convertToEntity() {
        // Создание тестовых данных для DTO продукта
        String nameDto = "product name test";
        String descriptionDto = "description test";
        double priceDto = 0.0;
        ProductDto productDto = ProductDto.builder()
                .name(nameDto)
                .description(descriptionDto)
                .price(priceDto)
                .build();
        List<ProductDto> productsDto = new ArrayList<>();
        productsDto.add(productDto);
        // Создание DTO корзины для конвертации
        CartDto cartDto = CartDto.builder()
                .products(productsDto)
                .totalPrice(priceDto)
                .quantity(productsDto.size())
                .build();
        // Создание ожидаемой сущности корзины
        Integer productId = null;
        String name = "product name test";
        String description = "description test";
        double price = 0.0;
        Product product = Product.builder()
                .productId(productId)
                .name(name)
                .description(description)
                .price(price)
                .build();
        List<Product> products = new ArrayList<>();
        products.add(product);
        Cart expected = Cart.builder()
                .products(products)
                .totalPrice(price)
                .quantity(products.size())
                .build();

        // Выполнение конвертации и проверка результата
        Cart actual = cartConverter.convertToEntity(cartDto);
        assertEquals(expected, actual);
    }

}