package com.mvp.question.services.files;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mvp.question.exceptions.BadRequestException;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import static net.logstash.logback.argument.StructuredArguments.kv;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    @Autowired
    private MinioClient minioClient;

    @Autowired
    private FileUtil fileUtil;

    @Value("${minio.bucket}")
    private String bucketName;

    private final String IMAGE_MEDIA_TYPE = "image";

    @Override
    public byte[] getFile(String fileName, String folder) {
        String objectName = folder + fileName;
        byte[] stream = null;
        GetObjectArgs minioRequest = GetObjectArgs.builder().bucket(bucketName).object(objectName).build();

        try {
            stream = minioClient.getObject(minioRequest).readAllBytes();
        } catch (InvalidKeyException | InsufficientDataException | InternalException | InvalidResponseException
                | NoSuchAlgorithmException | ServerException | XmlParserException | IOException
                | ErrorResponseException e) {
            System.out.printf("Minio error occurred with: {}, {}, {}",
                    kv("code", e.hashCode()), kv("message", e.getMessage()),
                    kv("trace", e.getStackTrace()));
        }
        return stream;
    }

    @Override
    public String uploadImage(MultipartFile file, String folder, boolean isResize) throws IOException {
        String fileExtension = fileUtil.getFileExtensionIfAcceptable(file, IMAGE_MEDIA_TYPE);
        String fileName = fileUtil.generateUniqueName(fileExtension);
        String objectName = folder + fileName;
        InputStream inputStream;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {

            throw new BadRequestException(e.getMessage());
        }

        if (isResize) {
            BufferedImage image;
            try {
                image = ImageIO.read(inputStream);
            } catch (IOException e) {
                throw new BadRequestException(e.getMessage());
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(fileUtil.resizeImage(image, image.getWidth(), image.getHeight()), fileExtension,
                    byteArrayOutputStream);
            inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        }

        try {
            minioClient.putObject(PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(
                    inputStream, inputStream.available(), -1)
                    .contentType(file.getContentType())
                    .build());
        } catch (InvalidKeyException | ErrorResponseException | InsufficientDataException | InternalException
                | InvalidResponseException | NoSuchAlgorithmException | ServerException | XmlParserException
                | IllegalArgumentException e) {
            throw new BadRequestException(e.getMessage());
        }
        return fileName;
    }

    public String uploadImg(InputStream inputStream, String folder) {
        String fileName = fileUtil.generateUniqueName("jpg");
        String objectName = folder + fileName;

        try {
            minioClient.putObject(PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(
                    inputStream, inputStream.available(), -1)
                    .contentType("image/jpg")
                    .build());
        } catch (InvalidKeyException | ErrorResponseException | InsufficientDataException | InternalException
                | InvalidResponseException | NoSuchAlgorithmException | ServerException | XmlParserException
                | IllegalArgumentException | IOException e) {
            throw new BadRequestException(e.getMessage());
        }
        return fileName;
    }

    @Override
    public void deleteFile(String fileName, String folder) {
        String objectName = folder + fileName;
        try {
            minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
        } catch (InvalidKeyException | ErrorResponseException | InsufficientDataException | InternalException
                | InvalidResponseException | NoSuchAlgorithmException | ServerException | XmlParserException
                | IllegalArgumentException | IOException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public String uploadInputStreamImage(InputStream file, String folder) {
        String fileName = fileUtil.generateUniqueName(fileUtil.getFileExtensionFromInputStream(file));
        String fileExtension = fileUtil.getFileExtensionIfAcceptable(fileName, IMAGE_MEDIA_TYPE);
        Path path = new File(fileName).toPath();
        String mimeType;
        try {
            mimeType = Files.probeContentType(path);
        } catch (IOException e) {
            throw new BadRequestException(e.getMessage());
        }
        fileName = fileUtil.generateUniqueName(fileExtension);
        String objectName = folder + fileName;

        try {
            minioClient.putObject(PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(
                    file, file.available(), -1)
                    .contentType(mimeType)
                    .build());
        } catch (InvalidKeyException | ErrorResponseException | InsufficientDataException | InternalException
                | InvalidResponseException | NoSuchAlgorithmException | ServerException | XmlParserException
                | IllegalArgumentException | IOException e) {
            throw new BadRequestException(e.getMessage());
        }
        return fileName;
    }

}
