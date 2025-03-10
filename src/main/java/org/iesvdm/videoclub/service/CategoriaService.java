package org.iesvdm.videoclub.service;

import org.iesvdm.videoclub.domain.Categoria;
import org.iesvdm.videoclub.domain.Pelicula;
import org.iesvdm.videoclub.exception.CategoriaNotFoundExpection;
import org.iesvdm.videoclub.exception.PeliculaNotFoundException;
import org.iesvdm.videoclub.repository.CategoriaRepository;
import org.iesvdm.videoclub.repository.PeliculaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.iesvdm.videoclub.DTO.CategoriaDTO;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> all() {
        return this.categoriaRepository.findAll();
    }




    public List<Categoria> allFiltro(Optional<String> buscarOptional, Optional<String> ordenarOptional) {

        List<Categoria> listaCategorias = new ArrayList<>();
        if(buscarOptional.isPresent() && ordenarOptional.isEmpty()) {
            listaCategorias = this.categoriaRepository.findByNombreContainingIgnoreCase(buscarOptional.get());
        } else if (buscarOptional.isPresent() && ordenarOptional.isPresent() && ordenarOptional.get().equals("asc")) {
            listaCategorias = this.categoriaRepository.findByNombreContainingIgnoreCaseOrderByNombreAsc(buscarOptional.get());
        } else if (buscarOptional.isPresent() && ordenarOptional.isPresent() && ordenarOptional.get().equals("desc")) {
            listaCategorias = this.categoriaRepository.findByNombreContainingIgnoreCaseOrderByNombreDesc(buscarOptional.get());
        } else if (buscarOptional.isEmpty() && ordenarOptional.isPresent() && ordenarOptional.get().equals("desc")) {
            listaCategorias = this.categoriaRepository.findAllByOrderByNombreDesc();
        } else if (buscarOptional.isEmpty() && ordenarOptional.isPresent() && ordenarOptional.get().equals("asc")) {
            listaCategorias = this.categoriaRepository.findAllByOrderByNombreAsc();
        } else {
            //listaCategorias = this.categoriaRepository.findAll();
        }
        return listaCategorias;
    }

    public Categoria save(Categoria categoria) {
        return this.categoriaRepository.save(categoria);
    }

    public Categoria one(Long id) {
        return this.categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNotFoundExpection(id));
    }

    public Categoria replace(Long id, Categoria categoria) {

        return this.categoriaRepository.findById(id).map( p -> (id.equals(categoria.getId())  ?
                        this.categoriaRepository.save(categoria) : null))
                .orElseThrow(() -> new CategoriaNotFoundExpection(id));

    }

    public void delete(Long id) {
        this.categoriaRepository.findById(id).map(p -> {this.categoriaRepository.delete(p);
                    return p;})
                .orElseThrow(() -> new CategoriaNotFoundExpection(id));
    }

    public List<CategoriaDTO> conteo() {
        List<Categoria> listaCategorias = this.categoriaRepository.findAll();
        List<Integer> conteoPeliculas = this.categoriaRepository.queryCategoriaConteoPeliculas();
        return IntStream.range(0, listaCategorias.size())
                .mapToObj(i -> new CategoriaDTO(
                        listaCategorias.get(i).getId(),
                        listaCategorias.get(i).getNombre(),
                        conteoPeliculas.get(i).intValue()
                ))
                .collect(Collectors.toList());
    }

}
