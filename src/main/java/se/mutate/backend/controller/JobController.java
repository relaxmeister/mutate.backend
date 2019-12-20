package se.mutate.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import se.mutate.backend.service.JobService;
import se.mutate.backend.model.jobdetail.JobDetail;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "")
public class JobController {
    //iom att den kommer kallas från start blir mapping "/"

    private JobService jobService;

    @Autowired
    Validator validata;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @CrossOrigin(origins="*")
    @GetMapping(value = "/")
    public Set<JobDetail> getAllJobs() {

        Set<JobDetail> poopers = new HashSet<>();

        JobDetail job1 = new JobDetail(1,"Senior UI/UX Designer", "Design");
        JobDetail job2 = new JobDetail(2,"Junior Frontend Developer", "Engineer");
        JobDetail job3 = new JobDetail(3,"Frontend Developer", "Engineer");

        poopers.add(job1);
        poopers.add(job2);
        poopers.add(job3);

        jobService.getJobs();

        return poopers;
    }
}
