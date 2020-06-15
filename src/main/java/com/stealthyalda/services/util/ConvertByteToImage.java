package com.stealthyalda.services.util;

import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.services.db.JDBCConnection;
import com.vaadin.server.StreamResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Image;

import java.io.ByteArrayInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConvertByteToImage {
    private ConvertByteToImage() {
    }

    public static Image getImage(byte[] bytes) {
        Image imageRes;

        try {

            if (bytes == null) {
                ThemeResource unknownPic = new ThemeResource("images/Unknown.png");
                imageRes = new Image("", unknownPic);
                imageRes.setHeight("40px");
                imageRes.setWidth("40px");

                return imageRes;
            }
            StreamResource.StreamSource streamSource = () -> (bytes == null) ? null : new ByteArrayInputStream(
                    bytes);
            imageRes = new Image(
                    null, new StreamResource(
                    streamSource, "streamedSourceFromByteArray"));
            imageRes.setHeight("40px");
            imageRes.setWidth("40px");

            return imageRes;


        } catch (Exception e) {
            Logger.getLogger(ConvertByteToImage.class.getName()).log(Level.SEVERE, e.getMessage());
        } finally {
            try {
                JDBCConnection.getInstance().closeConnection();
            } catch (DatabaseException e) {
                Logger.getLogger(ConvertByteToImage.class.getName()).log(Level.SEVERE, e.getMessage());
            }
        }
        ThemeResource resource = new ThemeResource("img/RegisterStudent/Unknown.png");
        imageRes = new Image(null, resource);
        imageRes.setHeight("40px");
        imageRes.setWidth("40px");
        return imageRes;
    }
}

