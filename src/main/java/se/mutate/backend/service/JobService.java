package se.mutate.backend.service;

import se.mutate.backend.model.jobdetail.JobDetail;
import java.util.Set;

public interface JobService {

    Set<JobDetail> getJobs();
}
