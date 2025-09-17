package com.example.CatalogoFilm.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.CatalogoFilm.model.CatalogoFilm;
import com.example.CatalogoFilm.model.Film;


@Service
public class CatalogoFilmService {
    
    private final CatalogoFilm catalogo = new CatalogoFilm();

    // ricerca tutti
    public List<Film> trovaTuttiFilm() {
        return catalogo.getAllFilm();
    }

    // ricerca per ID
    public Optional<Film> trovaConId(int id) {
        return catalogo.getById(id);
    }

    // aggiunge un film
    public Film aggiungiFilm(Film film) {
        catalogo.addFilm(film);
        return film; 
    }

    // aggiornare
    public Film aggiornaIlFilm(int id, Film film) {
        return trovaConId(id).map(f -> {
            f.setTitolo(film.getTitolo());
            f.setGenere(film.getGenere());
            f.setRegista(film.getRegista());
            f.setAnno(film.getAnno());
            return f;
        }).orElseThrow(() -> new NoSuchElementException("Aggiornamento non effettuato"));
    }

    // eliminare
    public boolean cancellaDaId(int id) {
        return catalogo.removeFilm(id);
    }
}