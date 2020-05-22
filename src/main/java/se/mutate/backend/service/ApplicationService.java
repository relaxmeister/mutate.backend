package se.mutate.backend.service;

import se.mutate.backend.model.formdata.FormData;

public interface ApplicationService {
    FormData createNewApplication(FormData formdata);
    void deleteApplicationById(Long id);
}
