package se.mutate.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import se.mutate.backend.model.user.AppUser;
import se.mutate.backend.model.userresponse.AppUserResponse;
import se.mutate.backend.service.AuthService;
import se.mutate.backend.service.impl.MyUserDetailsService;
import se.mutate.backend.util.JwtUtil;

import java.util.Optional;

//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    //LOGIN FÖRLEGAD
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/login")
    public ResponseEntity<AppUser> loginUser(@RequestParam(value = "email") String email,
                                             @RequestParam(value = "password") String password) {

        return Optional
                .ofNullable(authService.loginUser(email, password))
                .map(e -> ResponseEntity.ok().body(e))          //200 OK
                .orElseGet(() -> ResponseEntity.notFound().build());  //404 Not found
    }

    //REGISTER
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/register")
    public ResponseEntity<AppUser> registerUser(@RequestBody AppUser user) {
        //iom retur av objekt borde det lösa direktinloggning.
        return Optional
                .ofNullable(authService.createNewUser(user))
                .map(e -> ResponseEntity.ok().body(e))          //200 OK
                .orElseGet(() -> ResponseEntity.notFound().build());  //404 Not found
    }

    //AUTHENTICATE / LOGIN
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AppUser user) throws Exception {
        System.out.println(user.getUsername() + " " + user.getEmail() + " " + user.getPassword());
        //Bra/rimlig lösning på username -> email
        //https://stackoverflow.com/questions/50673400/how-to-login-by-email-instead-of-username-in-spring-security
        //problemet är att det userDetails returnerar kanske inte är tillräckligt med info?
        //standard token jnbc uses
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }


        AppUser DBUser = authService.loginUser(user.getUsername(), user.getPassword());
        //System.out.println(DBUser.getUsername() + DBUser.getClaims());
        if (DBUser != null) {
            System.out.println("FOUND");
            System.out.println(DBUser.getUsername() + DBUser.getClaims());
            final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
            final String jwt = jwtTokenUtil.generateToken(userDetails);

            return ResponseEntity.ok(new AppUserResponse(DBUser.getUsername(), DBUser.getClaims(), jwt));
        } else {
            return ResponseEntity.notFound().build();
        }


        //ANVÄND ANDRA SERVICEN


        //return ResponseEntity.notFound().build();
    }
}
