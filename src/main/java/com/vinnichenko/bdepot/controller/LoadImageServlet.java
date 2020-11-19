package com.vinnichenko.bdepot.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * The type Load image servlet.
 */
@WebServlet(urlPatterns = "/images/*")
public class LoadImageServlet extends HttpServlet {
    private static final int BEGIN_INDEX = 1;
    private static final String UPLOAD_DIRECTORY = "C:\\uploads";
    private static final String CONTENT_TYPE = "image/jpeg";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String filename = request.getPathInfo().substring(BEGIN_INDEX);
        File file = new File(UPLOAD_DIRECTORY, filename);
        response.setContentType(CONTENT_TYPE);
        response.setContentLengthLong(file.length());
        Files.copy(file.toPath(), response.getOutputStream());
    }
}
