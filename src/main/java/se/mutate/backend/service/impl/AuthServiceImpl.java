package se.mutate.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.mutate.backend.model.user.AppUser;
import se.mutate.backend.repositories.AppUserRepository;
import se.mutate.backend.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private final AppUserRepository appUserRepository;

    public AuthServiceImpl(AppUserRepository appUserRepository) {
            this.appUserRepository = appUserRepository;
    }

    @Override
    public AppUser loginUser(String username, String password) {
        System.out.println("LOGINUSERIMPL: " + username + " " + password);
        //return appUserRepository.findByEmailAndPassword(email, password);
        return appUserRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public AppUser createNewUser(AppUser user) {
        user.setClaims("normal"); //med andra ord alla som skapas via API blir normal
        return appUserRepository.save(user);
    }
    //borde finnas en egen för specifikt adminroll?
}
