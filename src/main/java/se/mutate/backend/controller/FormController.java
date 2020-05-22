package se.mutate.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import se.mutate.backend.model.formdata.FormData;
import se.mutate.backend.service.ApplicationService;
import se.mutate.backend.service.SendMailService;

import javax.imageio.IIOException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
//import javax.validation.Validator;
import java.io.IOException;
import java.util.Set;

import org.springframework.validation.Validator;

@RestController
@RequestMapping(value = "/recruit")
public class FormController {
    private SendMailService sendMailService;
    private ApplicationService applicationService;

    @Autowired
    Validator validata;

    @Autowired
    public FormController(SendMailService sendMailService, ApplicationService applicationService) {
        this.sendMailService = sendMailService;
        this.applicationService = applicationService;
    }

    @CrossOrigin(origins="*")
    @PostMapping("/ph")//temporär placeholder
    @ResponseStatus(HttpStatus.OK)
    public FormData sendJobApplication(@RequestBody FormData formdata) {

        //sendMailService.sendFormAsEmail(formdata); //vill nu ha 2 filer
        return formdata;
    }

    @CrossOrigin(origins="*")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/phnewer", headers=("content-type=multipart/*"))
    public String woooo(@RequestParam("file1") MultipartFile resume,
                        @RequestParam("file2") MultipartFile coverLetter,
                        @RequestParam("obj") String obj
                        /*@RequestParam("lastname") String name2,
                        @RequestParam("email") String name3,
                        @RequestParam("phone") String name4,
                        @RequestParam("city") String name5,
                        @RequestParam(value = "reasoning", required = false) String name6*/) throws IOException {
        // TODO APPLICATION SKA ÄVEN HAMNA I


        ObjectMapper objectMapper = new ObjectMapper();
        try {
            FormData formdata = new ObjectMapper().readValue(obj, FormData.class);

            BindingResult errors = new BeanPropertyBindingResult(formdata, "data");
            validata.validate(formdata, errors); // populerar errors
            System.out.println("-------");
            System.out.println(errors.getAllErrors());
            System.out.println("-------");

            System.out.println("resumeSize: " + resume.getSize() + " byte");
            System.out.println("coverLetterSize: " + coverLetter.getSize() + " byte");

            //vad är 10mb? 10 000 bytes eller ?

            System.out.println(resume.getContentType());

            if (resume.getSize() > 10485760 || coverLetter.getSize() > 10485760) { // verkar redan finnas en inbyggd i tomcat
                throw new FileUploadBase.IOFileUploadException("Filerna överstiger 10mb");
            }

            if (errors.getAllErrors().size() > 0) {
                throw new IOException("Uppgifter inte korrekt inmatade");
            }
            sendMailService.sendFormAsEmail(formdata, resume, coverLetter); // lär vilja sätta specfiktjobb som en formdata-variabel
            applicationService.createNewApplication(formdata);
            return "SUCCESS!";
        } catch (Exception e) {
            // Faulty formData
            System.out.println("lol");
            System.out.println(e);
        }

        /*String hej = name+name2+name3+name4+name5+name6;
        FormData dataForm = new FormData();
        //bäst är onekligen att ta in ett helt objekt då fylls allt på en gång.. blir smådrygt annars.
        dataForm.setName(name);
        dataForm.setLastname(name2);
        dataForm.setReasoning(name6);*/
        return "SUPERFAIL";
    }

    @DeleteMapping(value = "/applications/{id}")
    public void deleteApplication(@PathVariable("id") Long id) {
        applicationService.deleteApplicationById(id);
    }
}
