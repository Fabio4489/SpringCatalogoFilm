package com.example.CatalogoFilm.controller;

import com.example.CatalogoFilm.model.Film;
import com.example.CatalogoFilm.service.CatalogoFilm;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.net.URI;

@RestController
@RequestMapping("/film")
    public class FilmController {

        private final CatalogoFilm service;

        public FilmController(CatalogoFilm service) {
            this.service = service;
        }


    // GET /studenti
    @GetMapping
    public List<Film> getAll() {
        return service.findAll();
    }

    // GET id specifico
    @GetMapping("/{id}")
    public ResponseEntity<Film> getById(@PathVariable int id) {
        return service.findById(id)
                      .map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
        }   

    // POST
    @PostMapping
        public ResponseEntity<Film> create(@RequestBody Film body) {
            Film filmCreato = service.create(body);

            return ResponseEntity.created(URI.create("/studenti"+filmCreato.getId()))
                                 .body(filmCreato);
    } 

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<Film> update(@PathVariable int id, @RequestBody Film body) {
        Film filmAggiornato = service.update(id, body);
        return ResponseEntity.ok().body(filmAggiornato); // 200 + il valore aggiornato
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    }

//     public static void main(String[] args) {
//         CatalogoFilm c = new CatalogoFilm();

//         Scanner scan = new Scanner(System.in);
//         int contatore = 1;
//         String scelta;


//         do{
//             menu();
//             scelta = scan.next();
//             switch (scelta) {
//                 case "1":
//                     // aggiunta
//                     if (aggiungiFilm(scan, contatore, c)){
//                         System.out.println("\n|-------------------------|");
//                         System.out.println("| Inserimento  completato |");
//                         System.out.println("|-------------------------|\n");
//                         contatore += 1;
//                     } else {
//                         System.out.println("\n| XXXXXXXX ERRORE XXXXXXX |");
//                         System.out.println("|     Nell'inserimento    |");
//                         System.out.println("| XXXXXXXX ERRORE XXXXXXX |\n");
//                     }
//                     break;
//                 case "2":
//                     // stampa
//                     System.out.println(stampa(c));
//                     break;
//                 case "3":
//                     // ricerca per genere
//                     System.out.println(ricerca(scan, c));
//                     break;
//                 case "4":
//                     // modifica
//                     if (modifica(scan, c)){
//                         System.out.println("\n|-------------------------|");
//                         System.out.println("|Aggiornamento  completato|");
//                         System.out.println("|-------------------------|\n");
//                     }else{
//                         System.out.println("\n| XXXXXXXX ERRORE XXXXXXX |");
//                         System.out.println("|    Nell'aggiornamento   |");
//                         System.out.println("| XXXXXXXX ERRORE XXXXXXX |\n");
//                     }
//                     break;
//                 case "5":
//                     // elimina
//                     if (elimina(scan, c)){
//                         System.out.println("\n|-------------------------|");
//                         System.out.println("| Eliminazione completata |");
//                         System.out.println("|-------------------------|\n");
//                     }else{
//                         System.out.println("\n| XXXXXXXX ERRORE XXXXXXX |");
//                         System.out.println("|     Id non presente     |");
//                         System.out.println("| XXXXXXXX ERRORE XXXXXXX |\n");

//                     }
//                     break;
//                 case "6":
//                         System.out.println("\n|-------------------------|");
//                         System.out.println("|  Grazie e arrivederci   |");
//                         System.out.println("|-------------------------|\n");
//                     break;
//                 default:
//                         System.out.println("\n| XXXXXXXX ERRORE XXXXXXX |");
//                         System.out.println("| Nell'inserimento numero |");
//                         System.out.println("| XXXXXXXX ERRORE XXXXXXX |\n");
//                     break;
//             }
            
//         }while (!scelta.equals("6"));
        
//     }
//     public static void menu(){
//         System.out.println("|--------- Menu ----------|");
//         System.out.println("| 1. Aggiungi Film        |");
//         System.out.println("| 2. Visualizza i Film    |");
//         System.out.println("| 3. Ricerca per genere   |");
//         System.out.println("| 4. Modifica un Film     |");
//         System.out.println("| 5. Elimina un Film      |");
//         System.out.println("| 6. Esci                 |");
//         System.out.print("|-------------------------|       --> ");
//     }


//     public static boolean aggiungiFilm(Scanner scan, int contatore, CatalogoFilm c){
//         scan.nextLine();
//         System.out.println("\n|-------------------------|");
//         System.out.println("| 1. Aggiungi Film        |");
//         System.out.println("|-------------------------|");
//         System.out.print("| Inserisci Titolo        | ");
//         String Titolo = scan.nextLine();
//         System.out.print("| Inserisci Genere        | ");
//         String Genere = scan.nextLine();
//         System.out.print("| Inserisci Regista       | ");
//         String Regista = scan.nextLine();
//         System.out.print("| Inserisci Anno uscita   | ");
//         String AnnoStringa = scan.next();
//         System.out.println("|-------------------------|       --> ");

//         int AnnoInt = Integer.parseInt(AnnoStringa);
//         Film f = new Film(contatore, Titolo, Regista, AnnoInt, Genere);
//         return c.addFilm(f);
//     }

//     public static String stampa(CatalogoFilm c){
//         System.out.println("\n|-------------------------|");
//         System.out.println("| 2. Lista Film           |");
//         System.out.println("|-------------------------|");
//         return c.printCatalogo();
//     }

//     public static boolean modifica(Scanner scan, CatalogoFilm c){
        
//         System.out.println("\n|-------------------------|");
//         System.out.println("| 4. Modifica un Film     |");
//         System.out.println("|-------------------------|");
//         System.out.print("| Inserisci id modifica   |");
//         String idStringa = scan.next();
        
//         scan.nextLine();
        
//         int idInt = Integer.parseInt(idStringa);
//         System.out.println("-- > INSERIRE (invio) PER NON MODIFICARE < --");
//         System.out.print("| Inserisci Titolo        |");
//         String Titolo = scan.nextLine();
//         System.out.print("| Inserisci Genere        |");
//         String Genere = scan.nextLine();
//         System.out.print("| Inserisci Regista       |");
//         String Regista = scan.nextLine();
//         System.out.println("-- > INSERIRE (0) PER NON MODIFICARE < --");
//         System.out.print("| Inserisci Anno uscita   |");
//         String AnnoStringa = scan.next();
//         System.out.println("|-------------------------|");

//         int AnnoInt = Integer.parseInt(AnnoStringa);
//         Film f = new Film(idInt, Titolo, Regista, AnnoInt, Genere);
//         return(c.modificaFilm(idInt, f));
//     }

//     public static boolean elimina(Scanner scan, CatalogoFilm c){

//         System.out.println("\n|-------------------------|");
//         System.out.println("| 5. Elimina un Film      |");
//         System.out.println("|-------------------------|");
//         System.out.print("|Inserisci id eliminazione|");
//         String idStringa = scan.next();
//         System.out.println("|-------------------------|");

//         int idInt = Integer.parseInt(idStringa);
//         return c.deleteFilm(idInt);
//     }

//     public static String ricerca(Scanner scan, CatalogoFilm c){
        
//         scan.nextLine();
        
//         System.out.println("\n|-------------------------|");
//         System.out.println("| 3. Lista Film           |");
//         System.out.println("|-------------------------|");
//         System.out.print("| Inserisci genere        |");
//         String Genere = scan.nextLine();
        
//         return c.findOneByGenere(Genere);
//     }



        // CatalogoFilm c = new CatalogoFilm();

        // Film f  = new Film(1, "The Godfather", "Francis Ford Coppola", 1972, "Crime");
        // Film f1 = new Film(2, "Inception", "Christopher Nolan", 2010, "Sci-Fi");
        // Film f2 = new Film(3, "Il Padrino - Parte II", "Francis Ford Coppola", 1974, "Crime");
        // Film f3 = new Film(4, "Pulp Fiction", "Quentin Tarantino", 1994, "Crime");
        // Film f4 = new Film(5, "The Shawshank Redemption", "Frank Darabont", 1994, "Drama");
        // Film f5 = new Film(6, "Il Signore degli Anelli: La Compagnia dell'Anello", "Peter Jackson", 2001, "Fantasy");
        // c.addFilm(f);
        // c.addFilm(f1);
        // c.addFilm(f2);
        // c.addFilm(f3);
        // c.addFilm(f4);
        // c.addFilm(f5);
        // c.printCatalogo();
        // c.deleteFilm(1);
        // c.addFilm(f);
        // c.printCatalogo();
        // System.out.println(c.modificaFilm(3, f1));
        // c.printCatalogo();
        // c.findOneByGenere("Crime");
    
