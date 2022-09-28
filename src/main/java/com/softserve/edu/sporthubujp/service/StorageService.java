package com.softserve.edu.sporthubujp.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface StorageService {
     /**
      * Method for uploading an image to a local folder "uploads",
      * route - src/main/resources/uploads
      *
      * @param multipartFile is an actual image file (.png, .jpeg, .tiff, .gif)
      * @param isPhotoOfTheDay represents whether photo file is a photo of the day
      * @return new stored image name
      */
     String uploadImage(MultipartFile multipartFile, boolean isPhotoOfTheDay);

     /**
      * Method for deleting an image by its new generated name
      *
      * @param id - new image's generated name
      */
     void deleteImage(String id);

     /**
      * Method for getting an image by its new generated name
      *
      * @param response is for writing here an image itself
      * @param id - new image's generated name
      * @throws IOException
      */
     void getImage(HttpServletResponse response, String id) throws IOException;

}
