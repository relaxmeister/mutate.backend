package se.mutate.backend.service.impl;

import org.springframework.stereotype.Service;
import se.mutate.backend.model.jobdetail.JobDetail;
import se.mutate.backend.service.JobService;

import java.util.HashSet;
import java.util.Set;

@Service
public class JobServiceImpl implements JobService {

    public Set<JobDetail> getJobs(){
        Set<JobDetail> poop = new HashSet<>();
        return poop;
    }
}
