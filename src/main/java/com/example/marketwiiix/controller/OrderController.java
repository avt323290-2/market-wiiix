package com.example.marketwiiix.controller;

import com.example.marketwiiix.entity.User;
import com.example.marketwiiix.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import static com.example.marketwiiix.utils.PageName.HOME_PAGE;
import static com.example.marketwiiix.utils.PageName.ORDER_HISTORY_PAGE;
import static com.example.marketwiiix.utils.PageName.ORDER_PAGE;

/**
 * Контроллер для управления заказами.
 */
@Controller
@SessionAttributes({"user"})
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Инициализация объекта пользователя в сессии.
     * @return Объект пользователя.
     */
    @ModelAttribute("user")
    public User User() {
        return new User();
    }

    /**
     * Отображение истории заказов пользователя.
     * @param userId Идентификатор пользователя.
     * @return Объект ModelAndView с страницей истории заказов.
     */
    @GetMapping("/read-history/{userId}")
    public ModelAndView showOrdersHistory(@PathVariable String userId) {
        ModelMap modelParams = new ModelMap();
        if (userId != null) {
            Integer id = Integer.parseInt(userId);
            modelParams.addAttribute("orders", orderService.readOrders(id));
        } else {
            return new ModelAndView(HOME_PAGE, modelParams);
        }
        return new ModelAndView(ORDER_HISTORY_PAGE, modelParams);
    }

    /**
     * Отображение информации о конкретном заказе.
     * @param orderId Идентификатор заказа.
     * @return Объект ModelAndView с страницей информации о заказе.
     */
    @GetMapping("/read/{orderId}")
    public ModelAndView showOrder(@PathVariable String orderId) {
        ModelMap modelParams = new ModelMap();
        if (orderId != null) {
            Integer id = Integer.parseInt(orderId);
            modelParams.addAttribute("order", orderService.read(id));
        } else {
            return new ModelAndView(HOME_PAGE, modelParams);
        }
        return new ModelAndView(ORDER_PAGE, modelParams);
    }
}