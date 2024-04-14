package com.example.marketwiiix.controller;

import com.example.marketwiiix.dto.CartDto;
import com.example.marketwiiix.service.CategoryService;
import com.example.marketwiiix.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import static com.example.marketwiiix.utils.PageName.ADMIN_PAGE;
import static com.example.marketwiiix.utils.PageName.CART_PAGE;
import static com.example.marketwiiix.utils.PageName.HOME_PAGE;
import static com.example.marketwiiix.utils.PageName.LOGIN_PAGE;
import static com.example.marketwiiix.utils.PageName.PROFILE_PAGE;
import static com.example.marketwiiix.utils.PageName.REGISTRATION_PAGE;

/**
 * Контроллер для перехода на различные страницы приложения.
 */
@RestController
@RequiredArgsConstructor
@SessionAttributes({"cartDto"})
public class GoToController {
    private final CategoryService categoryService;
    private final UserService userService;

    /**
     * Инициализация объекта корзины в сессии.
     * @return Объект корзины.
     */
    @ModelAttribute("cartDto")
    public CartDto initializeCartSessionObject() {
        return new CartDto();
    }

    /**
     * Отображение домашней страницы.
     * @return Объект ModelAndView с домашней страницей и списком категорий.
     */
    @GetMapping("/home")
    public ModelAndView showHomePage() {
        ModelMap model = new ModelMap();
        model.addAttribute("categories", categoryService.findAll());
        return new ModelAndView(HOME_PAGE, model);
    }

    /**
     * Отображение страницы администратора.
     * @return Объект ModelAndView с страницей администратора и списком пользователей.
     */
    @GetMapping("/admin")
    public ModelAndView showAdminPage() {
        ModelMap model = new ModelMap();
        model.addAttribute("users", userService.findAll());
        return new ModelAndView(ADMIN_PAGE, model);
    }

    /**
     * Отображение страницы входа.
     * @return Объект ModelAndView с страницей входа.
     */
    @GetMapping("/login")
    public ModelAndView showLoginPage() {
        return new ModelAndView(LOGIN_PAGE);
    }

    /**
     * Отображение страницы регистрации.
     * @return Объект ModelAndView с страницей регистрации.
     */
    @GetMapping("/registration")
    public ModelAndView showRegistrationPage() {
        return new ModelAndView(REGISTRATION_PAGE);
    }

    /**
     * Отображение страницы корзины.
     * @return Объект ModelAndView с страницей корзины.
     */
    @GetMapping("/cart")
    public ModelAndView showCartPage() {
        return new ModelAndView(CART_PAGE);
    }

    /**
     * Отображение страницы профиля пользователя.
     * @return Объект ModelAndView с страницей профиля пользователя.
     */
    @GetMapping("/profile")
    public ModelAndView showProfilePage() {
        return new ModelAndView(PROFILE_PAGE);
    }
}