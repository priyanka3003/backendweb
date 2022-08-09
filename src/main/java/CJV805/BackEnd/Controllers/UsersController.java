package CJV805.BackEnd.Controllers;

import CJV805.BackEnd.Models.UserModel;
import CJV805.BackEnd.Services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UsersController {

    @Autowired
    private UsersService service;

    @GetMapping("/users")
    public ResponseEntity getUsers(){
        return new ResponseEntity(service.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity getUser(@PathVariable("id") String id){
        return new ResponseEntity(service.getUser(id), HttpStatus.OK);
    }

    @PostMapping(value = "/users", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity createUser(@RequestBody UserModel user){
        String userEmail = user.getEmail();
        user.setUsername(userEmail);
        if(service.addUser(user)==null){
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        } else {
            return new ResponseEntity(HttpStatus.OK);
        }

    }
}


