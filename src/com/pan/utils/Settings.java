package com.pan.utils;

import java.io.File;
import java.util.Scanner;

public class Settings {
    public String fileRoot = "~/.pan/files";
    public void SetFileRoot(String fileRoot) {
        this.fileRoot = fileRoot;
    }
    public void GetFileRoot() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Edit fileRoot : ");
        this.fileRoot = sc.nextLine();
        System.out.println("fileRoot switch to: " + this.fileRoot);
        sc.close();
    }
}
