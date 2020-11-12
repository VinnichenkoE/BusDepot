package com.vinnichenko.bdepot.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public class PhotoFileManager {
    private static final String UPLOAD_DIRECTORY = "C:\\uploads";
    private static final String FILE_EXTENSION = ".jpg";
    private static final Logger LOGGER = LogManager.getLogger();


    public Optional<String> add(Collection<Part> photoParts) {
        Path path = Paths.get(UPLOAD_DIRECTORY);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                LOGGER.log(Level.ERROR, "Error while creating directory {}", UPLOAD_DIRECTORY, e);
            }
        }
        Optional<String> photoName = Optional.empty();
        for (Part part : photoParts) {
            String fileName = part.getSubmittedFileName();
            if (fileName != null) {
                if (fileName.endsWith(FILE_EXTENSION)) {
                    try {
                        String newFileName = UUID.randomUUID().toString();
                        part.write(UPLOAD_DIRECTORY + File.separator + newFileName + FILE_EXTENSION);
                        LOGGER.log(Level.INFO, "Upload successful. File: {}", fileName);
                        photoName = Optional.of(newFileName);
                    } catch (IOException e) {
                        LOGGER.log(Level.ERROR, "Error while writing file {}", fileName, e);
                    }
                } else {
                    LOGGER.log(Level.ERROR, "Upload failed. File: {}", fileName);
                }
            }
        }
        return photoName;
    }

    /**
     * Delete photo.
     *
     * @param photoName the photo name
     */
    public void delete(String photoName) {
        Path path = Path.of(UPLOAD_DIRECTORY + File.separator + photoName + FILE_EXTENSION);
        try {
            Files.delete(path);
            LOGGER.log(Level.INFO, "Delete successful. File: {}", photoName);
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, "Error while deleting file {}", photoName, e);
        }
    }
}
