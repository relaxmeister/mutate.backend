package se.mutate.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import se.mutate.backend.model.formdata.FormData;
import se.mutate.backend.service.SendMailService;

@RestController
@RequestMapping(value = "")
public class FormController {
    private SendMailService sendMailService;

    @Autowired
    public FormController(SendMailService sendMailService) {
        this.sendMailService = sendMailService;
    }

    @PostMapping("/recruit/ph")//temporär placeholder
    public String sendJobApplication(@RequestBody FormData formdata) {


        sendMailService.sendFormAsEmail(formdata);
        return "hi there!";
    }
}
