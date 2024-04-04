package com.example.marketwiiix.controller;

import com.example.marketwiiix.entity.Product;
import com.example.marketwiiix.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.example.marketwiiix.utils.PageName.PRODUCT_PAGE;

/**
 * Контроллер для управления продуктами.
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Отображение страницы продукта по его идентификатору.
     * @param productId Идентификатор продукта.
     * @return Объект ModelAndView с данными о продукте.
     */
    @GetMapping("/{productId}")
    public ModelAndView showProductPage(@PathVariable("productId") Integer productId) {
        ModelMap model = new ModelMap();
        if (productId != null) {
            Optional<Product> product = productService.findById(productId);
            model.addAttribute("product", product.get());
        }
        return new ModelAndView(PRODUCT_PAGE, model);
    }

    /**
     * Поиск продуктов по ключевому слову в названии или описании.
     * @param searchKey Ключевое слово для поиска.
     * @return Объект ModelAndView с результатами поиска.
     */
    @GetMapping("/search")
    public ModelAndView advancedSearch(@RequestParam("searchKey") String searchKey) {
        ModelMap model = new ModelMap();
        Set<Product> products = productService.searchProductsByNameAndDescription(searchKey);
        model.addAttribute("products", products);
        return new ModelAndView(PRODUCT_PAGE, model);
    }

    /**
     * Отображение списка продуктов с пагинацией.
     * @param page Номер страницы.
     * @param size Размер страницы.
     * @return Объект ModelAndView с списком продуктов на странице.
     */
    @RequestMapping(value = "/listProducts", method = RequestMethod.GET)
    public ModelAndView listProducts(
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {
        ModelMap model = new ModelMap();
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(3);
        Page<Product> productPage = productService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("productPage", productPage);
        int totalPages = productPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return new ModelAndView(PRODUCT_PAGE, model);
    }
}