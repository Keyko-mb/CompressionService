package ru.shafikova.CompressionService.Services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.shafikova.CompressionService.Models.FileMetadata;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class CompressionService {
    public String compress(MultipartFile file, FileMetadata metadata) throws IOException {
        byte[] bytes = file.getBytes();
        Coder coder = new Coder();
        List<String> codedLine = coder.compress(bytes);
        return writeBinFile(codedLine, metadata);
    }

    public static String writeBinFile(List<String> text, FileMetadata metadata) throws IOException {
        Path compressedFile = Files.createTempFile("compressedFile", ".bin");
        String filePath = String.valueOf(compressedFile.toAbsolutePath());

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(filePath))) {

            dos.writeShort(metadata.getOriginalFileName().length());
            dos.writeChars(metadata.getOriginalFileName());

            dos.writeShort(metadata.getOriginalFormat().length());
            dos.writeChars(metadata.getOriginalFormat());

            for (String s : text) {
                int intValue = Integer.parseInt(s);
                System.out.println("s " + s);
                System.out.println("intValue " + intValue);

//                byte msb = (byte) (intValue >> 8);
//                byte lsb = (byte) intValue;
//                System.out.println("msb " + msb);
//                System.out.println("lsb " +lsb);
//                dos.write(msb);
//                dos.write(lsb);

//                String byteString = Integer.toBinaryString(intValue);
//                System.out.println("byteString " + byteString);

                dos.writeShort(intValue);

            }
//            System.out.println("Двоичное число записано в файл.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filePath;
    }
}
