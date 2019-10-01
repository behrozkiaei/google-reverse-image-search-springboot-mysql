package com.image.api.controller;

import com.image.api.config.ResourceNotFoundException;
import com.image.api.entity.Image;
import com.image.api.entity.Links;
import com.image.api.repository.ImageRepository;
import com.image.api.repository.linksRepository;
import com.image.api.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class ImageController {

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    linksRepository linksRepositoryC;

    @Autowired
    ImageService iamgeService;

    // Get All Notes
    @GetMapping("/images")
    public List<Image> getAllNotes() {
        return imageRepository.findAll();
    }

    // Create a new Note
    @PostMapping("/image")
    public Image createNote(@Valid @RequestBody Image i) throws IOException {


        String url = i.getImageName();
        Set<Links> l =   iamgeService.getImageByUrl(i.getImageName(),i);
        Set<Links> b =   iamgeService.getImageByUrlForYandex(i.getImageName(),i);
        l.addAll(b);
        i.setLinks(l);
        return imageRepository.save(i);


    }

    // Get a Single Note
    @GetMapping("/notes/{id}")
    public Image getNoteById(@PathVariable(value = "id") Long imageId) {
        return imageRepository.findById(imageId)
                .orElseThrow(() -> new ResourceNotFoundException("image", "id", imageId));
    }

    // Update a Note
    @PutMapping("/image/{id}")
    public Image updateNote(@PathVariable(value = "id") Long imageId,
                           @Valid @RequestBody Image imageDetails) {

        Image image = imageRepository.findById(imageId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", imageId));

        return  imageRepository.save(image);

    }
    // Delete a Note
    @DeleteMapping("/image/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable(value = "id") Long imageId) {
        Image image = imageRepository.findById(imageId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", imageId));

        imageRepository.delete(image);

        return ResponseEntity.ok().build();
    }



}
