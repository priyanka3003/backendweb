package CJV805.BackEnd.Controllers;

import CJV805.BackEnd.Models.Movies;
import CJV805.BackEnd.Models.TVs;
import CJV805.BackEnd.Services.TVsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class TVsController {

    @Autowired
    private TVsService service;

    @GetMapping("/tvs")
    public ResponseEntity getTVs(){
        return new ResponseEntity(service.getTVs(), HttpStatus.OK);
    }

    @GetMapping("/tv/{id}")
    public ResponseEntity getTV(@PathVariable("id") String id) {
        return new ResponseEntity(service.getTV(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMovies(@PathVariable("id") String id) {
        service.deletetv(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/tv/{title}")
    public ResponseEntity getmoviesByTitle(@RequestParam(value = "title") String t) throws Exception {
        return new ResponseEntity(service.gettvWithTitle(t), HttpStatus.OK);

    }

    @PutMapping("/tv/update/{id}")
    public ResponseEntity updateMovie(@PathVariable("id") String id, @RequestBody TVs tv) throws Exception {
        return new ResponseEntity(service.updatetv(id, tv), HttpStatus.OK);
    }

    @PostMapping(value = "/tvs", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity createMovie(@RequestBody TVs tv){
        if(service.addTV(tv)==null){
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        } else {
            return new ResponseEntity(HttpStatus.OK);
        }
    }

}
