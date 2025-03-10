package org.iesvdm.videoclub.controller;

import org.iesvdm.videoclub.exception.CategoriaNotFoundExpection;
import org.iesvdm.videoclub.exception.PeliculaNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CategoriaNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(CategoriaNotFoundExpection.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String categoriaNotFoundHandler(CategoriaNotFoundExpection categoriaNotFoundExpection) {
        return categoriaNotFoundExpection.getMessage();
    }
}
