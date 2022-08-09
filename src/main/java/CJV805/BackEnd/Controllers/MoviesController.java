package CJV805.BackEnd.Controllers;

import CJV805.BackEnd.Models.Movies;
import CJV805.BackEnd.Services.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class MoviesController {

    @Autowired
    private MoviesService service;

    @GetMapping("/movies")
    public ResponseEntity getMovies() {
        return new ResponseEntity(service.getMovies(), HttpStatus.OK);
    }

    @GetMapping("/movie/{id}")
    public ResponseEntity getMovie(@PathVariable("id") String id) {
        return new ResponseEntity(service.getMovie(id), HttpStatus.OK); }

//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity deleteMovies(@PathVariable("id") String id) {
//        service.deleteMovie(id);
//        return new ResponseEntity(HttpStatus.OK);
//    }

    @GetMapping("/movie/{title}")
    public ResponseEntity getmoviesByTitle(@RequestParam(value = "title") String t) throws Exception {
            return new ResponseEntity(service.getMoviesWithTitle(t), HttpStatus.OK);

    }

    @PutMapping("/movies/update/{id}")
    public ResponseEntity updateMovie(@PathVariable("id") String id, @RequestBody Movies movies) throws Exception {
            return new ResponseEntity(service.updateMovie(id, movies), HttpStatus.OK);
    }

    @PostMapping(value = "/movies", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity createMovie(@RequestBody Movies movie){
        if(service.addMovie(movie)==null){
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        } else {
            return new ResponseEntity(HttpStatus.OK);
        }

    }

}
