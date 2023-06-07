package ru.shafikova.CompressionService.Services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class CompressionService {
    public String compress(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        Coder coder = new Coder();
        return coder.compress(bytes);
    }
}
