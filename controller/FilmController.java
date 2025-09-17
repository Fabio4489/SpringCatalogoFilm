package com.example.CatalogoFilm.controller;

import com.example.CatalogoFilm.model.Film;
import com.example.CatalogoFilm.service.CatalogoFilmService;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.net.URI;

@RestController
@RequestMapping("/film")
    public class FilmController {

        private final CatalogoFilmService service;

        public FilmController(CatalogoFilmService service) {
            this.service = service;
        }


    // GET /studenti
    @GetMapping
    public List<Film> getAll() {
        return service.trovaTuttiFilm();
    }

    // GET id specifico
    @GetMapping("/{id}")
    public ResponseEntity<Film> getById(@PathVariable int id) {
        return service.trovaConId(id)
                      .map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
        }   

    // POST
    @PostMapping
    public ResponseEntity<Film> create(@RequestBody Film body) {
        Film risultatoInserimento = service.aggiungiFilm(body);
        if(risultatoInserimento == null){
            return ResponseEntity.badRequest().build();
        }else{
            return ResponseEntity.created(URI.create("/film/"+risultatoInserimento.getId()))
                                  .body(risultatoInserimento);
        }
    } 

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<Film> aggiornamentoFilm(@PathVariable int id, @RequestBody Film body) {
        Film filmAggiornato = service.aggiornaIlFilm(id, body);
        return ResponseEntity.ok().body(filmAggiornato);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancellaFilm(@PathVariable int id) {
        boolean risultatoInserimento = service.cancellaDaId(id);
        if(!risultatoInserimento){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.noContent().build();
        }
    }
}
