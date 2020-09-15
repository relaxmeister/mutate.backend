package se.mutate.backend.service;

import se.mutate.backend.model.user.AppUser;

public interface AuthService {

    AppUser createNewUser(AppUser user);
    AppUser loginUser(String username, String password);
}
