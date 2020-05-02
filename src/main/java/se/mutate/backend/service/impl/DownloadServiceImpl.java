package se.mutate.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.mutate.backend.model.fileobject.FileObject;
import se.mutate.backend.repositories.DownloadReposity;
import se.mutate.backend.service.DownloadService;

import java.util.Optional;

@Service
public class DownloadServiceImpl implements DownloadService {

    @Autowired
    private final DownloadReposity downloadReposity;

    public DownloadServiceImpl(DownloadReposity downloadReposity) {
        this.downloadReposity = downloadReposity;
    }

    @Override
    public FileObject getFileByOS(String os) {
        FileObject obj;
        //obj = downloadReposity.findById(1l).orElse(null); //funkar bra annars
        obj = downloadReposity.findBySystem("windoes"); //hårdkodad för jag kmr inte ta hänsyn atm
        return obj;
    }

//    @Override
//    public FileObject getFileByRealOS(String os) {
//        //downloadReposity.findByLOLOL("windoes");
//        return downloadReposity.findBySystem("windoes");
//    }

}
