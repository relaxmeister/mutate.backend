package se.mutate.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import se.mutate.backend.model.formdata.FormData;

public interface ApplicationRepository extends JpaRepository<FormData, Long> {
}
