package se.mutate.backend.service.impl;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;

import com.sendgrid.helpers.mail.objects.Attachments;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import se.mutate.backend.model.Beer;
import se.mutate.backend.model.formdata.FormData;
import se.mutate.backend.service.SendMailService;

import java.io.IOException;

@Service
public class SendMailServiceImpl implements SendMailService {

    @Autowired
    SendGrid sendGrid;

    Beer b = new Beer();


    /*
    * TIP
    * Message size limit: The total message size should not exceed 20MB.
    * This includes the message itself, headers,
    * and the combined size of any attachments.
    * */


    public String sendFormAsEmail(FormData formdata, MultipartFile resume, MultipartFile coverLetter) throws IOException {
        Email from = new Email("Tjohoo@example.com");

        String subject = "Job Application Mutate: "+ formdata.getJob() +" "+ formdata.getName() +" "+ formdata.getLastname();
        Email to = new Email("jojjethebest@hotmail.com");
        Content content = new Content("text/html",
                "<html><body>" +
                        "<p style=\"color: #000000; font-weight: bold\">Mail was sent by SendGrid!</p>" +
                        "<p style=\"color: #000000; font-weight: bold\">APPLICATION FOR "+formdata.getJob()+"</p>" +
                        "<p><span style=\"color: #000000; font-weight: bold\">Name: </span>"+formdata.getName()+" "+formdata.getLastname()+"</p>" +
                        "<p><span style=\"color: #000000; font-weight: bold\">City: </span>"+formdata.getCity()+"</p>" +
                        "<p><span style=\"color: #000000; font-weight: bold\">PhoneNr: </span>"+formdata.getPhone()+"</p>" +
                        "<p><span style=\"color: #000000; font-weight: bold\">Email: </span>"+formdata.getEmail()+"</p>" +
                        "<p style=\"color: #000000; font-weight: bold\">Optional Content: </p>" +
                        "<p>"+formdata.getReasoning()+"</p>" +
                        "</body></html>");


        Attachments attachments = new Attachments();
        Base64 x = new Base64();
        String imageDataString = x.encodeAsString(resume.getBytes());

        attachments.setContent(imageDataString);
        attachments.setType(resume.getContentType());
        attachments.setFilename(resume.getOriginalFilename());
        attachments.setDisposition("attachment");
        attachments.setContentId("Banner");

        Attachments attachment2 = new Attachments();
        imageDataString = x.encodeAsString(coverLetter.getBytes());

        attachment2.setContent(imageDataString);
        attachment2.setType(coverLetter.getContentType());
        attachment2.setFilename(coverLetter.getOriginalFilename());
        attachment2.setDisposition("attachment");
        attachment2.setContentId("Banner");

        Mail mail = new Mail(from, subject, to, content);

        mail.addAttachments(attachments);
        mail.addAttachments(attachment2);
        SendGrid sg = new SendGrid("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");
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
        return "sent!";

    }

    public String sendFormEasy(String email) throws IOException { // används inte längre
        Email from = new Email("test@gmail.com");
        String subject = "Let me give you muneee";
        //Email to = new Email("raseri90@gmail.com");
        Email to = new Email("jojjethebest@hotmail.com");
        Content content = new Content("text/plain", "Hi, i speek england good and me want u to have my Somalian munee");
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid("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");
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
        return "sent!";
    }

}
