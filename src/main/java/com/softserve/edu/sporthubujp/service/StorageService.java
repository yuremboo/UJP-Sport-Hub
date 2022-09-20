package com.softserve.edu.sporthubujp.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface StorageService {
    public String uploadImage(MultipartFile multipartFile);
    public void deleteImage(String id);
    public void getImage(HttpServletResponse response, String id) throws IOException;

}
