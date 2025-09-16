package com.example.CatalogoFilm.service;

import com.example.CatalogoFilm.model.Film;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class CatalogoFilm {

    List<Film> catalogo = new ArrayList<Film>();
    int contatore = 1;

    // ricerca tutti
    public List<Film> findAll() {
        return new ArrayList<>(catalogo);
    }

    // ricerca per ID
    public Optional<Film> findById(int id) {
        return catalogo.stream()
                         .filter(oggetto -> Objects.equals(oggetto.getId(),id))
                         .findFirst(); 
    }

    // aggiunge un film
    public Film create(Film film) {
        film.setId(contatore);
        contatore ++;
        catalogo.add(film);
        return film;
    }

    // aggiornare
    public Film update(int id, Film film) {
        return findById(id).map(f -> {
            f.setTitolo(film.getTitolo());
            f.setGenere(film.getGenere());
            f.setRegista(film.getRegista());
            f.setAnno(film.getAnno());
            return f;
        }).orElseThrow(() -> new NoSuchElementException("Aggiornamento non effettuato"));
    }

    // eliminare
    public void deleteById(int id) {
        boolean rimozioneEffettuata = catalogo.removeIf(f -> f.getId() == id);

        if(!rimozioneEffettuata) {
            throw new NoSuchElementException("Eliminazione non effettuata!");
        }
    }
}