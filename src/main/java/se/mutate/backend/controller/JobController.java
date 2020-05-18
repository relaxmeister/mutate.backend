package se.mutate.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import se.mutate.backend.model.jobspecifics.JobSpecifics;
import se.mutate.backend.service.JobService;
import se.mutate.backend.model.jobdetail.JobDetail;

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
    public JobSpecifics getJobSpecificsById(@PathVariable("id") Long id) {

        //old - gamla funkade inte med bara long
        //System.out.println(jobService.findByJobDetail(1l));

        /*System.out.println(jobService.getJobSpecificsByJobDetail(2l));
        JobSpecifics hej = jobService.getJobSpecificsByJobDetail(id);
        if (hej == null) {
            System.out.println("ERROR");
        }
        System.out.println(hej.getDoing());*/
        //TODO
        return jobService.getJobSpecificsByJobDetail(id);

        //return jobService.getJobSpecificsByJobDetail(1);
    }
}
