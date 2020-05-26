package se.mutate.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import se.mutate.backend.model.jobdetail.Job;

public interface JobRepository extends JpaRepository<Job, Long> {
}
