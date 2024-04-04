package com.example.marketwiiix.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import static com.example.marketwiiix.utils.PageName.ERROR_PAGE;

/**
 * Глобальный обработчик исключений для приложения.
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Обработчик исключения UserNotFoundException.
     * @param ex Исключение UserNotFoundException
     * @return Модель представления ошибки
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ModelAndView handleUserNotFoundException(UserNotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView(ERROR_PAGE);
        modelAndView.addObject("Пользователь не найден", ex.getMessage());
        log.error("Пользователь не найден", ex);
        return modelAndView;
    }
}




