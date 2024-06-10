/*
package com.book.verse.books;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageService {

    private final String imageUrl="http:localhost:8080/api/v1/books";
    private final String desktopDirectory = System.getProperty("user.home") + "/Desktop";

    public String saveCoverImage(MultipartFile file, String bookTitle, Integer bookId) throws IOException {
        // Create the filename
        String filename = bookTitle + "_" + bookId + ".jpg"; // Assuming the file is an image
        Path filePath = Paths.get(desktopDirectory, filename);

        // Save the file
        Files.write(filePath, file.getBytes());
        return filePath.toString();
    }
    public Resource loadResourceAsResource(String coverImagePath) {
        try {
            Path filePath = Paths.get(coverImagePath);
            Resource resource = new FileSystemResource(filePath.toFile());
            if (resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("Cover image not found at path: " + coverImagePath);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Error loading cover image: " + ex.getMessage(), ex);
        }
    }
    public String getImageUrl(String filename) {
        return imageUrl + "/" + filename;
    }
}*/
