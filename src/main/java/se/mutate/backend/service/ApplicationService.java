package se.mutate.backend.service;

import se.mutate.backend.model.formdata.FormData;

import java.util.Set;

public interface ApplicationService {

    Set<FormData> getAllApplications();
    FormData createNewApplication(FormData formdata);
    void deleteApplicationById(Long id);
    //Set<FormData> getApplicationsByJob(Long id);
}
