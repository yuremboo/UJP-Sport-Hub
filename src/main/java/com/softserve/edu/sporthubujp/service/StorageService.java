package com.softserve.edu.sporthubujp.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface StorageService {
     String uploadImage(MultipartFile multipartFile, boolean isPhotoOfTheDay);
     void deleteImage(String id);
     void getImage(HttpServletResponse response, String id) throws IOException;

}
