package ru.shafikova.CompressionService.Models;

public class FileMetadata {
    private String originalFileName;
    private String originalFormat;

    public FileMetadata(String originalFileName, String originalFormat) {
        this.originalFileName = originalFileName;
        this.originalFormat = originalFormat;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getOriginalFormat() {
        return originalFormat;
    }

    public void setOriginalFormat(String originalFormat) {
        this.originalFormat = originalFormat;
    }
}
