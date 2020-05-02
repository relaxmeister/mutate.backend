package se.mutate.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import se.mutate.backend.model.jobdetail.JobDetail;

import java.util.Set;

public interface JobDetailRepository extends JpaRepository<JobDetail, Long> {
}
