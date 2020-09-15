package se.mutate.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import se.mutate.backend.model.user.AppUser;
import se.mutate.backend.repositories.AppUserRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple user, hardcoded
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private final AppUserRepository appUserRepository;

    public MyUserDetailsService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("LOL");
        AppUser user = appUserRepository.findByUsername(username);
        //AppUser user = appUserRepository.findByEmail(email);
        //return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
        //return new User("foo@foo.foo", "foo", new ArrayList<>());
        if(user != null) {
            List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_" + user.getClaims());
            return new User(user.getUsername(), user.getPassword(), grantedAuthorities);

        } else {
            throw new UsernameNotFoundException("Username: " + username + " not found");
        }
    }
}
