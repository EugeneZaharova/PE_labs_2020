package ru.eugene.backend;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import ru.eugene.backend.dto.LoadedFile;

import java.util.Collections;
import java.util.UUID;

@Profile("test")
@Configuration
public class TestConfiguration {
    @Bean
    @Primary
    public FileService getMockFileService() {
        FileService fileService = Mockito.mock(FileService.class);
        Mockito.when(fileService.download("filename")).thenReturn(createLoadedFile());
        return fileService;
    }

    @Bean
    @Primary
    public VoucherService getMockVoucherService() {
        VoucherService voucherService = Mockito.mock(VoucherService.class);
        Mockito.when(voucherService.getAllVouchers())
                .thenReturn(Collections.singletonList(
                        VoucherMapper.toModel(ObjectGenerator.generateVoucher())));

        Mockito.when(voucherService.getVoucher(Mockito.any(UUID.class)))
                .thenReturn(VoucherMapper.toModel(ObjectGenerator.generateVoucher()));

        return voucherService;
    }

    private LoadedFile createLoadedFile() {
        return new LoadedFile("filename", "text/plain", "Hello world".getBytes());
    }
}
