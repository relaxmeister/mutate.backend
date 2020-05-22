package se.mutate.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.mutate.backend.model.jobdetail.JobDetail;
import se.mutate.backend.model.jobspecifics.JobSpecifics;
import se.mutate.backend.repositories.JobDetailRepository;
import se.mutate.backend.repositories.JobSpecificsRepository;
import se.mutate.backend.service.JobService;

import java.util.HashSet;
import java.util.Set;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private final JobDetailRepository jobDetailRepository;
    @Autowired
    private final JobSpecificsRepository jobSpecificsRepository;

    public JobServiceImpl(JobDetailRepository jobDetailRepository, JobSpecificsRepository jobSpecificsRepository) {
        this.jobDetailRepository = jobDetailRepository;
        this.jobSpecificsRepository = jobSpecificsRepository;
    }

    @Override
    public Set<JobDetail> getJobs(){
        Set<JobDetail> poop = new HashSet<>();
        jobDetailRepository.findAll().forEach(poop::add);
        return poop;
    }

    @Override
    public JobSpecifics getJobSpecificsByJobDetail(Long id) {
        JobDetail jd = new JobDetail();
        jd.setId(id);
        JobSpecifics js = jobSpecificsRepository.findByjobdetail(jd);
        return js;
    }

    @Override
    public void deleteJobById(Long id) {
        jobDetailRepository.deleteById(id);
    }
    // funkade inte bra
//    public JobSpecifics findByJobDetail(Long id) {
//        //TODO FUNKAR INTE findByJobDetailById
//        return jobSpecificsRepository.findByJobDetailById(4l);
//    }


}
