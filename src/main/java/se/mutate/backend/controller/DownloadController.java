package se.mutate.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import se.mutate.backend.model.fileobject.FileObject;
import se.mutate.backend.service.DownloadService;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

@RestController
@RequestMapping(value = "")
public class DownloadController {

    private DownloadService downloadService;

    @Autowired
    public DownloadController(DownloadService downloadService) {
        this.downloadService = downloadService;
    }


    @CrossOrigin(origins="*")
    @GetMapping(value = "/download")
    public HttpEntity<byte[]> downloadMutate(@RequestParam("platform") String os) throws IOException {

        System.out.println("DOWNLOADDA!");

        //System.out.println(downloadService.getFileByRealOS("windoes").getSystem());
        //System.out.println(downloadService.getFileByRealOS("windoes").getId());


        byte[] documentBody = downloadService.getFileByOS(os).getFile();

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_PDF);
        //header.set(HttpHeaders.CONTENT_DISPOSITION,
        //        "attachment; filename=" + fileName.replace(" ", "_"));
        header.set(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=filename.pdf");

        header.setContentLength(documentBody.length);

        return new HttpEntity<byte[]>(documentBody, header);

    }
}
