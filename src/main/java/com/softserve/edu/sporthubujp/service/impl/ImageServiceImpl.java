package com.softserve.edu.sporthubujp.service.impl;

import com.softserve.edu.sporthubujp.entity.Image;
import com.softserve.edu.sporthubujp.repository.ImageRepository;
import com.softserve.edu.sporthubujp.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.impl.InvalidContentTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public String uploadImage(MultipartFile multipartImage) throws IOException {
        log.info(String.format("Service: uploading image with a name %s",
                multipartImage.getName()));

        if (!Objects.requireNonNull(multipartImage.getContentType()).startsWith("image/")) {
            log.error(String.format("Service: invalid file type: %s", multipartImage.getContentType()));
            throw new InvalidContentTypeException();
        }

        Image image = new Image();
        image.setName(multipartImage.getOriginalFilename());
        image.setContent(multipartImage.getBytes());

        return imageRepository.save(image)
                .getId();
    }

    @Override
    public Resource downloadImage(String imageId) {
        log.info(String.format("Service: downloading image with an id %s",
                imageId));

        byte[] image = imageRepository.findById(imageId)
                .orElseThrow(EntityNotFoundException::new)
                .getContent();

        return new ByteArrayResource(image);
    }
}
