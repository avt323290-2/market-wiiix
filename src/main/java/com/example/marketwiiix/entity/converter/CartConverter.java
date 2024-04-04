package com.example.marketwiiix.entity.converter;

import com.example.marketwiiix.dto.CartDto;
import com.example.marketwiiix.entity.Cart;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Конвертер для преобразования между объектами Cart и CartDto.
 */
@Component
public class CartConverter {
    private final ModelMapper modelMapper;

    @Autowired
    public CartConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * Преобразует сущность Cart в объект CartDto.
     *
     * @param cart Сущность Cart
     * @return Объект CartDto
     */
    public CartDto convertToDto(Cart cart) {
        return modelMapper.map(cart, CartDto.class);
    }

    /**
     * Преобразует объект CartDto в сущность Cart.
     *
     * @param cartDto Объект CartDto
     * @return Сущность Cart
     */
    public Cart convertToEntity(CartDto cartDto) {
        return modelMapper.map(cartDto, Cart.class);
    }
}