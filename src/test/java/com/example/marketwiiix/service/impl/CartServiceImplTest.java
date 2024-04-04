package com.example.marketwiiix.service.impl;

import com.example.marketwiiix.MarketWiiixApplication;
import com.example.marketwiiix.dto.ProductDto;
import com.example.marketwiiix.entity.Cart;
import com.example.marketwiiix.entity.Product;
import com.example.marketwiiix.service.CartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = MarketWiiixApplication.class)
class CartServiceImplTest {
    private final CartService cartService;

    @Autowired
    CartServiceImplTest(CartService cartService) {
        this.cartService = cartService;
    }

    @Test
    void testCalculateTotalPrice() {
        double price1 = 10.0;
        double price2 = 5.55;
        ProductDto productDto1 = ProductDto.builder().price(price1).build();
        ProductDto productDto2 = ProductDto.builder().price(price2).build();
        ArrayList<ProductDto> productsDto = new ArrayList<>();
        productsDto.add(productDto1);
        productsDto.add(productDto2);
        double actual = cartService.calculateTotalPrice(productsDto);
        double expected = 15.55;
        assertEquals(expected, actual);
    }

    @Test
    void testAddProduct() {
        Product product1 = Product.builder().productId(1).name("name1").price(10.5).build();
        Product product2 = Product.builder().productId(2).name("name2").price(5.0).build();
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        Cart expected = new Cart();
        expected.setProducts(products);

    }
}