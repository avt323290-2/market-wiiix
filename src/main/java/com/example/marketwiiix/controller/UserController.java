package com.example.marketwiiix.controller;

import com.example.marketwiiix.dto.CartDto;
import com.example.marketwiiix.dto.RegistrationFormDto;
import com.example.marketwiiix.entity.User;
import com.example.marketwiiix.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import static com.example.marketwiiix.utils.PageName.LOGIN_PAGE;

/**
 * Контроллер для управления пользователями.
 */
@Controller
@SessionAttributes({"user", "cartDto"})
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Инициализация объекта пользователя в сессии.
     * @return Объект пользователя.
     */
    @ModelAttribute("user")
    public User initializeUserSessionObject() {
        return new User();
    }

    /**
     * Инициализация объекта корзины в сессии.
     * @return Объект корзины.
     */
    @ModelAttribute("cartDto")
    public CartDto initializeCartSessionObject() {
        return new CartDto();
    }

    /**
     * Обработка запроса на регистрацию нового пользователя.
     * @param registrationFormDto DTO с данными регистрации.
     * @param errors Результаты проверки валидации.
     * @param model Объект модели.
     * @return Объект ModelAndView с результатом обработки запроса.
     */
    @PostMapping("/registrate")
    public ModelAndView registrate(@ModelAttribute("registration_form_dto") @Valid RegistrationFormDto registrationFormDto,
                                   BindingResult errors,
                                   ModelAndView model) {
        model.addObject("registration_form_dto", registrationFormDto);
        userService.register(registrationFormDto);
        return new ModelAndView(LOGIN_PAGE);
    }
}