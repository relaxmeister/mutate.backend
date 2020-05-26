package se.mutate.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.mutate.backend.service.JobService;
import se.mutate.backend.model.jobdetail.Job;

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
    public Set<Job> getAllJobs() {
        return jobService.getJobs();
    }

    @CrossOrigin(origins ="*")
    @GetMapping(value = "/recruit/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable("id") Long id) {

        return Optional
                .ofNullable( jobService.getJobById(id) )
                .map( user -> ResponseEntity.ok().body(user) )          //200 OK
                .orElseGet( () -> ResponseEntity.notFound().build() );  //404 Not found

    }

    @PostMapping(value = "/applications")
    public Job  createJob(@RequestBody Job job) {
        return jobService.add(job);
    }

    @PutMapping(value ="/applications/{id}")
    public Job updateJob(@PathVariable("id") Long id, @RequestBody Job job) {
        return jobService.saveJobById(id, job);
    }

    @CrossOrigin(origins="*")
    @DeleteMapping(value = "/{id}")
    public void deleteJob(@PathVariable("id") Long id) {
        jobService.deleteJobById(id);
    }
}
