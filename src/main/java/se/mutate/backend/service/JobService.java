package se.mutate.backend.service;

import se.mutate.backend.model.jobdetail.JobDetail;
import se.mutate.backend.model.jobspecifics.JobSpecifics;

import java.util.Set;

public interface JobService {

    Set<JobDetail> getJobs();
    JobSpecifics getJobSpecificsById(Long id);
    JobSpecifics findByJobDetail(Long id);
}
