package com.example.marketwiiix.controller;

import com.example.marketwiiix.dto.CartDto;
import com.example.marketwiiix.entity.Order;
import com.example.marketwiiix.entity.User;
import com.example.marketwiiix.service.CartService;
import com.example.marketwiiix.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import static com.example.marketwiiix.utils.PageName.CART_PAGE;
import static com.example.marketwiiix.utils.PageName.HOME_PAGE;

@Controller
@SessionAttributes({"cartDto", "user"})
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    private final OrderService orderService;

    @Autowired
    public CartController(CartService cartService, OrderService orderService) {
        this.cartService = cartService;
        this.orderService = orderService;
    }

    @ModelAttribute("cartDto")
    public CartDto initializeCartSessionObject() {
        return new CartDto();
    }

    @ModelAttribute("user")
    public User initializeUserSessionObject() {
        return new User();
    }

    /**
     * Добавляет товар в корзину.
     * @param productId Идентификатор добавляемого товара.
     * @param cartDto Объект передачи данных корзины.
     * @return Объект ModelAndView с соответствующим представлением.
     */
    @PostMapping("/add/{productId}")
    public ModelAndView addProductToCart(@PathVariable("productId") String productId,
                                         @Valid @ModelAttribute CartDto cartDto) {
        ModelMap modelParams = new ModelMap();
        if (productId != null) {
            Integer id = Integer.parseInt(productId);
            modelParams.addAttribute("cartDto", cartService.addProduct(id, cartDto));
            return new ModelAndView(CART_PAGE, modelParams);
        }
        return new ModelAndView(HOME_PAGE, modelParams);
    }

    // Другие методы контроллера...

    /**
     * Сохраняет заказ из корзины.
     * @param cartDto Объект передачи данных корзины.
     * @param userId Идентификатор пользователя, размещающего заказ.
     * @return Объект ModelAndView с соответствующим представлением.
     */
    @PostMapping("/order/{userId}")
    public ModelAndView saveOrder(@ModelAttribute("cartDto") CartDto cartDto,
                                  @PathVariable String userId) {
        ModelMap modelParams = new ModelMap();
        if (cartDto != null && userId != null) {
            Integer id = Integer.valueOf(userId);
            Order savedCart = orderService.save(cartDto, id);
            if (savedCart != null) {
                clearCart(cartDto, modelParams);
            }
        }
        return new ModelAndView(CART_PAGE, modelParams);
    }

    private void clearCart(CartDto cartDto, ModelMap modelParams) {
        modelParams.addAttribute("cartDto", cartService.clear(cartDto));
    }
}