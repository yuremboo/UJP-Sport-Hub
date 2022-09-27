package com.softserve.edu.sporthubujp.service.impl;

import com.softserve.edu.sporthubujp.exception.ImageException;
import com.softserve.edu.sporthubujp.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import net.snowflake.client.jdbc.internal.apache.commons.io.IOUtils;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class StorageServiceImpl implements StorageService {
    public static final String UPLOAD_DIRECTORY = "src/main/resources/uploads/";
    public static final String PHOTO_OF_THE_DAY = "photo-of-the-day.jpg";

    @Override
    public String uploadImage(MultipartFile multipartFile, boolean isPhotoOfTheDay) {
        log.info(String.format("Service: uploading image with a name %s",
                multipartFile.getOriginalFilename()));

        if (!Objects.requireNonNull(multipartFile.getContentType()).startsWith("image")
                || multipartFile.isEmpty()) {
            throw new ServiceException("File is not an image or is empty");
        }

        String newImageName;

        if (isPhotoOfTheDay) {
            deleteImage(PHOTO_OF_THE_DAY);
            newImageName = PHOTO_OF_THE_DAY;
        } else {
            newImageName = UUID.randomUUID() + "." + "jpg";
        }

        String path = UPLOAD_DIRECTORY + newImageName;
        Path pathToFile = Paths.get(path);

        try {
            Files.createDirectories(pathToFile.getParent());
            Files.write(pathToFile, multipartFile.getBytes());
        } catch (IOException e) {
            log.error(String.format("Service: failed to upload image with id %s", newImageName));
            throw new ImageException(
                    String.format("Service: failed to upload image with id %s", newImageName));
        }
        return newImageName;
    }

    @Override
    public void deleteImage(String id) {
        log.info(String.format("Service: deleting image with an id %s", id));
        String path = UPLOAD_DIRECTORY + id;
        Path fileToDeletePath = Paths.get(path);
        try {
            Files.deleteIfExists(fileToDeletePath);
        } catch (IOException e) {
            log.error(String.format("Service: failed to delete image with id %s", id));
            throw new ImageException(String.format("Service: failed to delete image with id %s", id));
        }
    }

    @Override
    public void getImage(HttpServletResponse response, String id) {
        log.info(String.format("Service: getting image with an id %s", id));
        String sPath = UPLOAD_DIRECTORY + id;
        Path path = Paths.get(sPath);
        System.out.println(path);
        try (InputStream in = new FileInputStream(path.toFile())) {
            response.setContentType(Files.probeContentType(path));
            IOUtils.copy(in, response.getOutputStream());

        } catch (IOException e) {
            log.error(String.format("Service: failed to get image with id %s", id));
            throw new ImageException(String.format("Service: failed to get image with id %s", id));
        }
    }
}
