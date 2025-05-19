package com.LiveScore.util;

import java.io.File;
import java.io.IOException;
import jakarta.servlet.http.Part;

/**
 * Utility class for handling image file uploads.
 * This class provides methods for extracting the file name from a {@link Part} object
 * and uploading the image file to a specified directory on the server.
 */
public class ImageUtil {

    /**
     * Extracts the file name from the given {@link Part} object based on the
     * "content-disposition" header.
     *
     * @param part the {@link Part} object representing the uploaded file.
     * @return the extracted file name. If no filename is found, returns a default name "download.png".
     */
    public String getImageNameFromPart(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        String imageName = null;

        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                imageName = s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }

        if (imageName == null || imageName.isEmpty()) {
            imageName = "download.png";  // Default name
        }

        return imageName;
    }

    /**
     * Uploads the image file from the given {@link Part} object to a specified
     * directory on the server.
     *
     * @param part the {@link Part} object representing the uploaded image file.
     * @param rootPath the root path for the server storage.
     * @param saveFolder the folder within the rootPath where the image will be saved.
     * @return {@code true} if the file was successfully uploaded, {@code false} otherwise.
     */
    public boolean uploadImage(Part part, String rootPath, String saveFolder) {
        String savePath = getSavePath(saveFolder);
        File fileSaveDir = new File(savePath);

        if (!fileSaveDir.exists()) {
            if (!fileSaveDir.mkdir()) {
                return false; // Failed to create the directory
            }
        }

        try {
            String imageName = getImageNameFromPart(part);
            String filePath = savePath + "/" + imageName;
            part.write(filePath);
            return true; // Upload successful
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Upload failed
        }
    }

    /**
     * Gets the save path for image storage.
     *
     * @param saveFolder the folder in which to save the image
     * @return the complete file path
     */
    public String getSavePath(String saveFolder) {
        String basePath = System.getProperty("user.dir") + "/webapp/resources/images/";
        return basePath + saveFolder + "/";
    }
}
