package com.wyl.cosystem.service;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class FileService {

    private final Path tempDir = Paths.get("temp");

    public String generateZipFile(List<Object> data) throws IOException {
        // Ensure the temp directory exists
        if (!Files.exists(tempDir)) {
            Files.createDirectory(tempDir);
        }

        // File path
        String zipFileName = "custInfo.zip";
        Path zipFilePath = tempDir.resolve(zipFileName);

        // Convert list to text file content
        StringBuilder sb = new StringBuilder();
        for (Object line : data) {
            sb.append(line).append("\n");
        }
        byte[] txtContent = sb.toString().getBytes();

        // Create a zip output stream
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFilePath.toFile()))) {
            // Create a text file entry in the zip
            ZipEntry zipEntry = new ZipEntry("custInfo.txt");
            zipOutputStream.putNextEntry(zipEntry);
            zipOutputStream.write(txtContent);
            zipOutputStream.closeEntry();
        }

        // Return the relative path to the file
        return "/download/" + zipFileName;
    }

    public FileSystemResource getFile(String fileName) {
        Path filePath = tempDir.resolve(fileName);
        return new FileSystemResource(filePath.toFile());
    }
}
