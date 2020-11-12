package com.vinnichenko.bdepot.controller;

import com.vinnichenko.bdepot.util.PhotoFileManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static com.vinnichenko.bdepot.controller.RequestParameter.*;

@WebServlet (urlPatterns = "/upload")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class UploadController extends HttpServlet {

    private static final PhotoFileManager photoFileManager = new PhotoFileManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Optional<String> photoName = photoFileManager.add(request.getParts());
        if (photoName.isPresent()) {
            request.setAttribute(IMAGE_NAME, photoName.get());
        } else {
            /*request.setAttribute(RequestParameter.INCORRECT_IMAGE_MESSAGE, true);*/
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/controller");
        dispatcher.forward(request, response);
    }
}
