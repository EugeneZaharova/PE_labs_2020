package ru.eugene.backend;

import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class FileControllerTest {
    @Autowired
    FileController fileController;

    @Test
    public void testUpload() {
        MultipartFile file = Mockito.mock(MultipartFile.class);
        try {
            Mockito.doAnswer(i -> {
                File targetFile = (File) i.getArgument(0);
                BufferedWriter writer = new BufferedWriter(new FileWriter(targetFile));
                writer.write("Hello world");
                writer.close();
                return null;
            }).when(file).transferTo(Mockito.any(File.class));
        } catch (IOException e) {
            throw new RuntimeException("Mockito error while mocking file");
        }
        fileController.upload(file);
    }

    @Test
    public void testDownload() {
        HttpEntity<byte[]> response = fileController.download("filename");
        Assertions.assertEquals("text/plain", response.getHeaders().getContentType().toString());
        assertEquals("Hello world".getBytes(), response.getBody());
    }

    private void assertEquals(byte[] expected, byte[] actual) {
        Assertions.assertEquals(expected.length, actual.length);
        for (int i = 0; i < expected.length; i++) {
            Assertions.assertEquals(expected[i], actual[i]);
        }
    }
}
