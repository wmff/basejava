package ru.javawebinar.basejava;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class MainFile {
    public static void main(String[] args) {
        File dir = new File("./src");
        listDir(dir);
    }

    private static void listDir(File dir) {
        File[] list = dir.listFiles();
        if (list != null) {
            Arrays.sort(list, (o1, o2) -> {
                if (o1.isDirectory()) {
                    return o2.isDirectory() ? o1.compareTo(o2) : -1;
                } else if (o2.isDirectory()) {
                    return 1;
                } else {
                    return o1.compareTo(o2);
                }
            });

            for (File file : list) {
                if (file.isDirectory()) {
                    try {
                    System.out.println(file.getCanonicalFile());
                    } catch (IOException e) {
                        throw new RuntimeException("error" + e);
                    }
                    listDir(file);
                } else {
                    try {
                    System.out.println(file.getCanonicalFile());
                    } catch (IOException e) {
                        throw new RuntimeException("error" + e);
                    }
                }

            }
        }
    }

}
