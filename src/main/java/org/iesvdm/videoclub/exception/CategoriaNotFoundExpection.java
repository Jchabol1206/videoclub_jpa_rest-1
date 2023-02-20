package org.iesvdm.videoclub.exception;

public class CategoriaNotFoundExpection extends RuntimeException{

    public CategoriaNotFoundExpection(Long id) {
        super("Not found Categoria with id: " + id);
    }
}
