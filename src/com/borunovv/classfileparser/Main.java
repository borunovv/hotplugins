package com.borunovv.classfileparser;

import com.borunovv.common.util.FileUtils;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        String fileName = "TestClass.class";
        ClassFile cf = new ClassFile(FileUtils.readFileBytes(fileName));
        System.out.println(cf);
    }
}

