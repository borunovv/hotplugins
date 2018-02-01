package com.borunovv.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author borunovv
 */
public final class FileUtils {

    public static byte[] readFileBytes(String path) throws IOException {
        try (InputStream is = new FileInputStream(new File(path))) {
            // Get the size of the file
            long length = new File(path).length();

            if (length > Integer.MAX_VALUE) {
                throw new IOException("File is too large");
            }

            // Create the byte array to hold the data
            byte[] bytes = new byte[(int) length];

            // Read in the bytes
            int offset = 0;
            int numRead = 0;
            while (offset < bytes.length
                    && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
                offset += numRead;
            }

            // Ensure all the bytes have been read in
            if (offset < bytes.length) {
                throw new IOException("Could not completely read file " + path);
            }

            return bytes;
        }
    }
}
