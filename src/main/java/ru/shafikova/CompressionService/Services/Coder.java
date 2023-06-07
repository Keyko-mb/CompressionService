package ru.shafikova.CompressionService.Services;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Coder {
    public String compress(byte[] bytes) throws IOException {
        String line = new String(bytes, StandardCharsets.UTF_8);
        List<String> dictionary = new ArrayList<>();
        for (char c : line.toCharArray()) {
            if (!dictionary.contains(String.valueOf(c))) dictionary.add(String.valueOf(c));
        }
//        System.out.println(dictionary);

        StringBuilder codedLine = new StringBuilder();
        String previous = String.valueOf(line.charAt(0));
        int index;
        for (int i = 1; i < line.length(); i++) {
            String current = previous + line.charAt(i);

            if (!dictionary.contains(current)) {
                dictionary.add(current);
                index = dictionary.indexOf(previous);
                codedLine.append(index);
                previous = String.valueOf(line.charAt(i));
            } else {
                previous = current;
            }
        }
        index = dictionary.indexOf(previous);
        codedLine.append(index);
//        System.out.println(codedLine);

        return writeBinFile(codedLine.toString());
    }

    public String writeBinFile(String text) throws IOException {
        Path compressedFile = Files.createTempFile("compressedFile", ".bin");
        String filePath = String.valueOf(compressedFile.toAbsolutePath());

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(filePath))) {
            for (char c : text.toCharArray()) {
//                System.out.println("c " + c);
                int byteValue = Character.getNumericValue(c);
//                System.out.println("byteValue " + byteValue);
//                String byteString = Integer.toBinaryString(byteValue);
//                System.out.println("byteString " + byteString);
                dos.writeByte(byteValue);
            }
//            System.out.println("Двоичное число записано в файл.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filePath;
    }
}

