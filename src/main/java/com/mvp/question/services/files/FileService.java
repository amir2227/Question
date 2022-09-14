package com.mvp.question.services.files;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    byte[] getFile(String fileName, String folder);
    
    String uploadImage(MultipartFile file, String folder, boolean isResize) throws IOException;

    void deleteFile(String fileName, String folder);
}
