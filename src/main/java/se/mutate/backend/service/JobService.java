package se.mutate.backend.service;

import se.mutate.backend.model.jobdetail.Job;

import java.util.Optional;
import java.util.Set;

public interface JobService {

    Set<Job> getJobs();
    Job getJobById(Long id);


    //JobSpecifics findByJobDetail(Long id);
    void deleteJobById(Long id);

    Job add(Job job);

    Job saveJobById(Long id, Job job);
}
