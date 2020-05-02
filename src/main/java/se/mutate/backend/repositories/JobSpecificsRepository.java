package se.mutate.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import se.mutate.backend.model.jobdetail.JobDetail;
import se.mutate.backend.model.jobspecifics.JobSpecifics;

public interface JobSpecificsRepository extends JpaRepository<JobSpecifics, Long> {

    //@Query("select p from Paper p where p.student.id = :student.id order by p.student.dateSubmitted")
    JobSpecifics findByjobdetail(JobDetail job);

    //@Query("SELECT u FROM JOB_SPECIFICS u WHERE u.status = 1")
    //@Query("SELECT * FROM JOB_SPECIFICS")
    //JobSpecifics findByJobDetail(Long id);


}
