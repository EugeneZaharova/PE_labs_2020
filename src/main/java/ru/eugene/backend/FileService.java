package ru.eugene.backend;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.eugene.backend.dto.LoadedFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileService {
    private static final String accessKey = "<Your access key>";
    private static final String secretKey = "<Your secret key>";
    private static final String bucketName = "<Your bucket name>";

    private final AmazonS3 s3 = createS3Object();

    public void upload(MultipartFile file) {
        try {
            File tempFile = File.createTempFile("eugene-", "");
            tempFile.deleteOnExit();

            file.transferTo(tempFile);
            s3.putObject(bucketName, file.getOriginalFilename(), tempFile);
        } catch (IOException e) {
            throw new RuntimeException("Unable to upload file");
        }
    }

    public LoadedFile download(String filename) {
        try {
            S3Object object = s3.getObject(bucketName, filename);
            byte[] content = new byte[Long.valueOf(object.getObjectMetadata().getContentLength()).intValue()];
            object.getObjectContent().read(content);

            return new LoadedFile(
                    filename,
                    object.getObjectMetadata().getContentType(),
                    content);
        } catch (IOException ignored) {
            throw new RuntimeException("Unable to download file");
        }
    }

    private static AmazonS3 createS3Object() {
        AWSCredentials credentials = new MyCredentials();

        AwsClientBuilder.EndpointConfiguration endpointConfiguration =
                new AwsClientBuilder.EndpointConfiguration("http://hb.bizmrg.com", "eu-north-1");

        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withEndpointConfiguration(endpointConfiguration)
                .build();
    }

    private static class MyCredentials implements AWSCredentials {
        @Override
        public String getAWSAccessKeyId() {
            return accessKey;
        }

        @Override
        public String getAWSSecretKey() {
            return secretKey;
        }
    }
}
