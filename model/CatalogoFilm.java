package com.example.CatalogoFilm.model;
import java.util.*;

// package com.example.CatalogoFilm.model;
public class CatalogoFilm {

    List<Film> catalogo = new ArrayList<>();
    int contatore = 1;

    public List<Film> getAllFilm() {
        return catalogo;
    }

    public Film addFilm(Film film){
        film.setId(contatore);
        contatore ++;
        catalogo.add(film);
        return film;
    }

    public boolean removeFilm(int id){
        return catalogo.removeIf(f -> f.getId() == id);
    }

    public Optional<Film> getById(int id) {
        return catalogo.stream().filter(f -> f.getId() == id)
                                .findFirst();
    }
}