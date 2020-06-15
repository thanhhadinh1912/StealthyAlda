/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stealthyalda.services.util;

import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Image;
import com.vaadin.ui.Upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author WINDOWS
 */
public class ImageUploader implements Upload.Receiver, Upload.SucceededListener {
    static final Image image = new Image();
    static File file;

    public static File getFile() {
        return file;
    }

    public static Image getImage() {
        return image;
    }

    public OutputStream receiveUpload(String filename, String mimeType) {
        // Create and return a file output stream
        FileOutputStream fos;

        String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
        file = new File(basepath + "/image/" + filename);

        try {
            fos = new FileOutputStream(file);
        } catch (final IOException e) {
            Logger.getLogger(ImageUploader.class.getName()).log(Level.SEVERE, e.getMessage());
            return null;
        }
        return fos;
    }

    @Override
    public void uploadSucceeded(Upload.SucceededEvent event) {

        image.setSource(new FileResource(file));
    }
}


