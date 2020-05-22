package se.mutate.backend.service.impl;

import org.springframework.stereotype.Service;
import se.mutate.backend.model.formdata.FormData;
import se.mutate.backend.repositories.ApplicationRepository;
import se.mutate.backend.service.ApplicationService;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
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
}
