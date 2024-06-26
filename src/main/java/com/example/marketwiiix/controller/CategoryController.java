package com.example.marketwiiix.controller;

import com.example.marketwiiix.entity.Product;
import com.example.marketwiiix.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import static com.example.marketwiiix.utils.PageName.CATEGORY_PAGE;

/**
 * Контроллер для работы с категориями товаров.
 */
@Validated
@RestController
public class CategoryController {
    private final int NUMBER_ELEMENTS_ON_PAGE = 3;
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Отображает страницу категории товаров.
     * @param categoryId Идентификатор категории товаров.
     * @param page Номер текущей страницы.
     * @param size Размер страницы (количество элементов на странице).
     * @return Объект ModelAndView с соответствующим представлением и моделью.
     * @throws BadCredentialsException Если идентификатор категории не указан.
     */
    @GetMapping("/category")
    public ModelAndView showCategoryPage(
            @RequestParam Optional<Integer> categoryId,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(NUMBER_ELEMENTS_ON_PAGE);
        int id = categoryId.orElse(categoryId.orElseThrow(() -> new BadCredentialsException("Bad credentials.")));
        Page<Product> products = categoryService.findProductsByCategoryId(id,
                PageRequest.of(currentPage - 1, pageSize));

        ModelMap model = new ModelMap();
        model.addAttribute("products", products);

        int totalPages = products.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("categoryId", categoryId);
        }
        return new ModelAndView(CATEGORY_PAGE, model);
    }
}
