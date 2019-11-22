package se.mutate.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.mutate.backend.service.SendMailService;

@RestController
@RequestMapping(value = "")
public class FormController {
    private SendMailService sendMailService;

    @Autowired
    public FormController(SendMailService sendMailService) {
        this.sendMailService = sendMailService;
    }
}
