package se.mutate.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.mutate.backend.model.jobdetail.Job;
import se.mutate.backend.repositories.JobRepository;
import se.mutate.backend.service.JobService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private final JobRepository jobRepository;


    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public Set<Job> getJobs(){
        Set<Job> jobs = new HashSet<>();
        jobRepository.findAll().forEach(jobs::add);
        return jobs;
    }

    @Override
    public Job getJobById(Long id){
        return jobRepository.findById(id).orElse(null);
    };



    @Override
    public void deleteJobById(Long id) {
        jobRepository.deleteById(id);
    }

    @Override
    public Job add(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public Job saveJobById(Long id, Job job) {
        Job updatedJob = job;
        updatedJob.setId(id);
        //om Id:t inte längre finns kommer det inte bli en override utan skapas en helt ny save med nytt unikt id

        return jobRepository.save(updatedJob);
    }

}
