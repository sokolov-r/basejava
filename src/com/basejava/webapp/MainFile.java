package com.basejava.webapp;

import java.io.File;

public class MainFile {

    public static void outputFileList(File dir) {
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
                File file = new File(dir.getPath() + "/" + name);
                if (file.isDirectory()) {
                    System.out.print("    ");
                    outputFileList(file);
                }
            }
        }
    }

    public static void main(String[] args) {
        outputFileList(new File("."));
    }
}
