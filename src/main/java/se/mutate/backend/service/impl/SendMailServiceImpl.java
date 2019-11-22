package se.mutate.backend.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import se.mutate.backend.model.formdata.FormData;
import se.mutate.backend.service.SendMailService;

@Service
public class SendMailServiceImpl implements SendMailService {

    public String sendFormAsEmail(FormData formdata) {
        return "HeJ";

    }
}
