package ru.shafikova.CompressionService.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
        String compressedFilePath = compressionService.compress(file);
        Path compressedFile = Path.of(compressedFilePath);

        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + compressedFile.getFileName())
                .body(new FileSystemResource(compressedFile));
    }

}
