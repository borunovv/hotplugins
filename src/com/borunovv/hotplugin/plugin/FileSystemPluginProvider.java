package com.borunovv.hotplugin.plugin;

import com.borunovv.hotplugin.util.FileSystemClassContentProvider;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author borunovv
 */
public class FileSystemPluginProvider extends FileSystemClassContentProvider {

    public FileSystemPluginProvider(String directory) {
        super(directory);
    }

    public List<String> getAvailablePluginNames() throws IOException {
        File dir = new File(directory);
        if (!dir.isDirectory()) {
            throw new IOException("Not a directory: " + directory);
        }
        String[] fileNames = dir.list();

        return fileNames != null ?
                Arrays.stream(fileNames)
                        .map(s -> s.split("\\.")[0])
                        .collect(Collectors.toList()) :
                Collections.emptyList();

    }
}
