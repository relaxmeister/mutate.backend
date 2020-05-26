package se.mutate.backend.service.impl;

import org.springframework.stereotype.Service;
import se.mutate.backend.model.formdata.FormData;
import se.mutate.backend.model.jobdetail.Job;
import se.mutate.backend.repositories.ApplicationRepository;
import se.mutate.backend.repositories.JobRepository;
import se.mutate.backend.service.ApplicationService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository, JobRepository jobRepository) {
        this.applicationRepository = applicationRepository;
        this.jobRepository = jobRepository;
    }

    @Override
    public Set<FormData> getAllApplications() {
        Set<FormData> applications = new HashSet<>();
        applicationRepository.findAll().forEach(applications::add);
        return applications;
    }

    @Override
    public FormData createNewApplication(FormData formdata) {
        applicationRepository.save(formdata);
        return new FormData();
    };

    @Override
    public void deleteApplicationById(Long id) {
        applicationRepository.deleteById(id);
    }

//    @Override
//    public Set<FormData> getApplicationsByJob(Long id) {
//        Optional<Job> job = jobRepository.findById(id);
//
//        job.ifPresent(e -> {
//            System.out.println("hashmap av "+ e.getRole() +" = " + e.getFormdata());
//        });
//        return new HashSet<>();
//    }

}
