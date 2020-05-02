package se.mutate.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import se.mutate.backend.model.jobspecifics.JobSpecifics;
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

//        Set<JobDetail> poopers = new HashSet<>();
//
//        JobDetail job1 = new JobDetail(1,"Senior UI/UX Designer", "Design");
//        JobDetail job2 = new JobDetail(2,"Junior Frontend Developer", "Engineering");
//        JobDetail job3 = new JobDetail(3,"Frontend Developer", "Engineering");
//        JobDetail job4 = new JobDetail(4,"Data Analytic", "Data");
//        JobDetail job5 = new JobDetail(5,"World of Data", "Data");
//
//        poopers.add(job1);
//        poopers.add(job2);
//        poopers.add(job3);
//        poopers.add(job4);
//        poopers.add(job5);
//
//        jobService.getJobs();

        return jobService.getJobs();
    }

    @CrossOrigin(origins ="*")
    @GetMapping(value = "/recruit/{id}")
    public JobSpecifics getJobSpecificsById(@PathVariable("id") Long id) {

        String[] jobDesc = {"You will develop a desktop client application based on Electron." +
                "You will be taking on a role as frontend lead where scalability is important to you," +
                "developing a codebase that can scale to a large number of developers." +
                "You will work with designers and together implement the visual and functional aspects of our client.",
                "Lead our Data Platform team to build a scalable data platform that powers features and informs teams. " +
                "Help us build scalable distributed systems like you'd " +
                "construct additional pylons in your base. You build those in your sleep, amirite?",
                "POOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOP"};

        String[] doing = new String[]{
                "Do everything",
                "Make me coffee",
                "Point small fingers",
                "Blame everyone except yourself",
                "Pretend to work",
                "Hide from the boss"};
        String[] shouldHave = {
                "3+ years of experience in developing frontend client/web applications. Working with Node.js and any modern framework such as Angular and React.js.",
                "Experience with common front-end development tools such as Webpack, NPM, etc.",
                "Able to sort of work independently as well as help the organization in improving workflows and processes.",
                "Strong understanding of user experience and interactions.",
                Long.toString(id)};
        String[] bonus = {
                "Experience with Electron, TypeScript and React.",
                "Experience with C-- and building native modules.",
                "Experience with CI/CD, static code analysis tools and Gitflow."};

        JobDetail job = new JobDetail();
        job.setId(2l);


        JobSpecifics js1 = new JobSpecifics(5l,job,
                "Frontend Developer", jobDesc, doing, shouldHave, bonus);

        System.out.println(jobService.getJobSpecificsById(2l));
        JobSpecifics hej = jobService.getJobSpecificsById(2l);
        System.out.println(hej.getDoing());
        return jobService.getJobSpecificsById(id);

        //return jobService.getJobSpecificsById(1);
    }
}
