package com.borunovv.hotplugin.util;

import com.borunovv.common.util.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author borunovv
 */
public class FileSystemClassContentProvider implements IClassContentProvider {

    public String directory;

    public FileSystemClassContentProvider(String directory) {
        this.directory = directory;
    }

    @Override
    public byte[] getClassContent(String name) {
        File fileName = new File(directory, name + ".class");
        String fullFilePath = fileName.getPath();

        try {
            return FileUtils.readFileBytes(fullFilePath);
        } catch (IOException e) {
            return null;
        }
    }
}
