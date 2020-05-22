package se.mutate.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import se.mutate.backend.model.jobspecifics.JobSpecifics;
import se.mutate.backend.service.JobService;
import se.mutate.backend.model.jobdetail.JobDetail;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(value = "")
public class JobController {
    //iom att den kommer kallas från start blir mapping "/"

    private JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @CrossOrigin(origins="*")
    @GetMapping(value = "/")
    public Set<JobDetail> getAllJobs() {
        return jobService.getJobs();
    }

    @CrossOrigin(origins ="*")
    @GetMapping(value = "/recruit/{id}")
    public ResponseEntity<JobSpecifics> getJobSpecificsById(@PathVariable("id") Long id) {

        return Optional
                .ofNullable( jobService.getJobSpecificsByJobDetail(id) )
                .map( user -> ResponseEntity.ok().body(user) )          //200 OK
                .orElseGet( () -> ResponseEntity.notFound().build() );  //404 Not found

    }

    @DeleteMapping(value = "/{id}")
    public void deleteJob(@PathVariable("id") Long id) {
        jobService.deleteJobById(id);
    }
}
