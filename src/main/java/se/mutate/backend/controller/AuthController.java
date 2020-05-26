package se.mutate.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.mutate.backend.model.user.AppUser;
import se.mutate.backend.service.AuthService;

import java.util.Optional;

@RestController
@RequestMapping(value = "")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    //LOGIN
    @CrossOrigin(origins="*")
    @GetMapping(value = "/login")
    public ResponseEntity<AppUser> loginUser(@RequestParam(value = "email") String email,
                                             @RequestParam(value = "password") String password) {

        return Optional
                .ofNullable( authService.loginUser(email, password) )
                .map( e -> ResponseEntity.ok().body(e) )          //200 OK
                .orElseGet( () -> ResponseEntity.notFound().build() );  //404 Not found
    }

    //REGISTER
    @CrossOrigin(origins="*")
    @PostMapping(value = "/register")
    public ResponseEntity<AppUser> registerUser(@RequestBody AppUser user) {
        //iom retur av objekt borde det lösa direktinloggning.
        return Optional
                .ofNullable( authService.createNewUser(user) )
                .map( e -> ResponseEntity.ok().body(e) )          //200 OK
                .orElseGet( () -> ResponseEntity.notFound().build() );  //404 Not found
    }
}
