package ru.shafikova.CompressionService.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.shafikova.CompressionService.Models.FileMetadata;
import ru.shafikova.CompressionService.Services.CompressionService;

import java.io.IOException;
import java.nio.file.Path;

@RestController
@RequestMapping("/compress")
public class Controller {
    private final CompressionService compressionService;

    @Autowired
    public Controller(CompressionService compressionService) {
        this.compressionService = compressionService;
    }

    @PostMapping()
    public ResponseEntity<Object> put(@RequestParam("file") MultipartFile file) throws IOException {
        String originalFormat = file.getContentType();
        String originalFileName = file.getOriginalFilename();
        FileMetadata fileMetadata = new FileMetadata(originalFileName, originalFormat);

        String compressedFilePath = compressionService.compress(file, fileMetadata);
        Path compressedFile = Path.of(compressedFilePath);
        String compressedFileName = (originalFileName.split("\\.")[0] + "-compressed.bin").replace(" ", "_");
        System.out.println(compressedFileName);

        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + compressedFileName)
                .body(new FileSystemResource(compressedFile));
    }
}






