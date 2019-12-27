package com.noc.security;

import java.io.File;

public class TestActivity {

    public static void main(String[] args) {
        folderMethod2("C:\\Users\\40879\\Desktop\\新建文件夹\\01");
    }

    public static void folderMethod2(String path) {
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (null != files) {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        folderMethod2(file2.getAbsolutePath());
                    } else {
                        System.out.println("文件:" + file2.getAbsolutePath());
                        System.out.println("文件Name:" + file2.getName());
                        System.out.println("文件Path:" + file2.getParentFile().getPath());
                        String newPath = file2.getParentFile().getPath() + "\\" + "word_" + file2.getName();

                        File oldName = new File(file2.getAbsolutePath());
                        File newName = new File(newPath);
                        if (oldName.renameTo(newName)) {
                            System.out.println("文件已重命名！");
                        } else {
                            System.out.println("文件重命名:Error!!!!!");
                        }
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }

}
