package com.softserve.edu.sporthubujp.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    public String uploadImage(MultipartFile multipartImage) throws IOException;

    public Resource downloadImage(String imageId);
}
