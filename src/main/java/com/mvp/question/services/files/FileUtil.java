package com.mvp.question.services.files;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mvp.question.exceptions.BadRequestException;

import javax.validation.constraints.NotNull;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class FileUtil {

    @Value("${file.upload.acceptableImageExtensions}")
    private String[] acceptableImageExtensions;

    private boolean isExtensionAcceptable(String extension, String mediaType) {
        if (mediaType.equals("image")) {
            for (String s : acceptableImageExtensions) {
                if (s.equalsIgnoreCase(extension)) {
                    return true;
                }
            }
        }
        return false;
    }

    public String getFileExtensionIfAcceptable(@NotNull MultipartFile file, String mediaType) {
        String extension = file.getContentType().split("[/]")[1];
        if (isExtensionAcceptable(extension, mediaType)) {
            return extension;
        } else {
            throw new BadRequestException("extension not acceptable"+extension);
        }
    }

    public String getFileExtensionIfAcceptable(String fileName, String mediaType) {
        String extension = fileName.split("\\.")[1];
        if (isExtensionAcceptable(extension, mediaType)) {
            return extension;
        } else {
            throw new BadRequestException("extension not acceptable"+extension);
        }
    }

    public String generateUniqueName(String extension) {
        Date date = new Date();
        return RandomString.make(9)+"-"+date.getTime() + "." + extension;
    }

    public String getFileExtensionFromInputStream(InputStream inputStream) {
        String fileExtension = null;
        try {
            String mimeType = URLConnection.guessContentTypeFromStream(inputStream);
            String[] tokens = mimeType.split("[/]");
            fileExtension = tokens[1];
        } catch (IOException ioException) {
            System.out.printf("Error occurred: {}", ioException.getMessage());
        }
        return fileExtension;
    }

    public BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
        return outputImage;
    }

}