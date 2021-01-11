package ru.eugene.backend;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.eugene.backend.dto.LoadedFile;

@Api(tags = "Files")
@RequestMapping("/files")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FileController {
    private final FileService filesService;

    @ApiOperation("Upload file")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @PostMapping("upload")
    public void upload(@RequestParam("file")  MultipartFile file) {
        filesService.upload(file);
    }

    @ApiOperation("Download file")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping("download/{filename}")
    public HttpEntity<byte[]> download(@PathVariable("filename") String filename) {
        LoadedFile loadedFile = filesService.download(filename);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(loadedFile.getContentType()));
        headers.set("HttpHeaders.CONTENT_DISPOSITION", "attachment; filename="
                + loadedFile.getName().replace(" ", "_"));
        headers.setContentLength(loadedFile.getContent().length);

        return new HttpEntity<>(loadedFile.getContent(), headers);
    }
}
