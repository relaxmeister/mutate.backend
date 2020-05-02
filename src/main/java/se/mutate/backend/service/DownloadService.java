package se.mutate.backend.service;

import se.mutate.backend.model.fileobject.FileObject;

public interface DownloadService {
    FileObject getFileByOS(String os);
    //FileObject getFileByRealOS(String os);
}
