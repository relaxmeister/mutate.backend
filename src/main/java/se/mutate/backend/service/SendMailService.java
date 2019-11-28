package se.mutate.backend.service;

import org.springframework.web.multipart.MultipartFile;
import se.mutate.backend.model.formdata.FormData;

import java.io.IOException;

public interface SendMailService {

    String sendFormAsEmail(FormData formdata, MultipartFile resume, MultipartFile coverLetter) throws IOException;
    String sendFormEasy(String email) throws IOException;
    //void uploadCommunityImage(UserDetails userInfo, long communityId, ImageType imageType, MultipartFile multipartFile)
    // throws UnauthorizedAccessException, NotFoundException, IOException, InternalException;
}
