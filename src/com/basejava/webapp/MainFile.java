package com.basejava.webapp;

import java.io.File;

public class MainFile {

    public static void main(String[] args) {
        outputFileList(new File("."), "  ");
    }

    public static void outputFileList(File dir, String offset) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    System.out.println(offset + file.getName());
                } else if (file.isDirectory()) {
                    System.out.println(offset + file.getName());
                    outputFileList(file, offset + "  ");
                }
            }
        }
    }
}
