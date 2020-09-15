package se.mutate.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.mutate.backend.model.user.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByEmailAndPassword(String email, String password);
    AppUser findByUsernameAndPassword(String username, String password);

    AppUser findByUsername(String username);

    AppUser findByEmail(String username);
    //@Query("SELECT u FROM AppUser u WHERE LOWER(u.email) = LOWER(:requestMail)") // är korrekt
    //AppUser findByEmailAndPassword(@Param("requestMail") String requestMail);
}
