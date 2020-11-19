package com.vinnichenko.bdepot.util;

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

/**
 * The type Photo file manager.
 */
public class PhotoFileManager {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String UPLOAD_DIRECTORY = "C:\\uploads";
    private static final String FILE_EXTENSION = ".jpg";

    /**
     * Add optional.
     *
     * @param photoParts the photo parts
     * @return the optional
     */
    public Optional<String> add(Collection<Part> photoParts) {
        Path path = Paths.get(UPLOAD_DIRECTORY);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                LOGGER.error("Error while creating directory {}", UPLOAD_DIRECTORY, e);
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
                        LOGGER.info("Upload successful. File: {}", fileName);
                        photoName = Optional.of(newFileName);
                    } catch (IOException e) {
                        LOGGER.error("Error while writing file {}", fileName, e);
                    }
                } else {
                    LOGGER.error("Upload failed. File: {}", fileName);
                }
            }
        }
        return photoName;
    }
}
