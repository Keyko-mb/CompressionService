package ru.shafikova.CompressionService.Services;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Coder {
    public List<String> compress(byte[] bytes) throws IOException {
        String line = new String(bytes, StandardCharsets.UTF_8);
        List<String> dictionary = new ArrayList<>();
        for (int i = 0; i <= 255; i++) dictionary.add(String.valueOf((char) i));
//        System.out.println(dictionary);

        List<String> codedLine = new ArrayList<>();
        String previous = String.valueOf(line.charAt(0));
        int index;
        for (int i = 1; i < line.length(); i++) {
            String current = previous + line.charAt(i);

            if (!dictionary.contains(current)) {
                dictionary.add(current);
                index = dictionary.indexOf(previous);
                codedLine.add(String.valueOf(index));
                previous = String.valueOf(line.charAt(i));
            } else {
                previous = current;
            }
        }
        index = dictionary.indexOf(previous);
        codedLine.add(String.valueOf(index));
        System.out.println(codedLine);

        return codedLine;
    }
}

