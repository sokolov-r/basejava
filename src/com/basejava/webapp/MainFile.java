package com.basejava.webapp;

import java.io.File;

public class MainFile {

    public static void main(String[] args) {
        outputFileList(new File("."));
    }

    public static void outputFileList(File dir) {
        File[] list = dir.listFiles();
        if (list != null) {
            for (File file : list) {
                System.out.println(file.getName());
                if (file.isDirectory()) {
                    System.out.print("    ");
                    outputFileList(file);
                }
            }
        }
    }
}
