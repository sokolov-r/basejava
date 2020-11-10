package com.basejava.webapp;

import java.io.File;

public class MainFile {
    static int i = 1;
    static String n = "  ";

    public static void main(String[] args) {
        outputFileList(new File("."));
    }

    public static void outputFileList(File dir) {
        File[] list = dir.listFiles();
        if (list != null) {
            for (File file : list) {
                i++;
                for (int j = 0; j < i; j++) {
                    System.out.print(n);
                }
                System.out.println(file.getName());
                if (file.isDirectory()) {
                    outputFileList(file);
                }
                i--;
            }
        }
    }
}
