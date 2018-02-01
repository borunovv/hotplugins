package com.borunovv.hotplugin.util;

import com.borunovv.classfileparser.ClassFile;

import java.io.IOException;

/**
 * @author borunovv
 * @date 01-08-2017
 */
public final class ClassUtils {

    // По содержимому файла [simpleClassName].class вернет полное имя класса
    // вида: com.domain.a.b.c.[simpleClassName]
    public static String getFullClassName(byte[] classFileContent) {
        try {
            return new ClassFile(classFileContent)
                    .getFullClassNameWithSlashes()
                    .replaceAll("/", ".");
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to parse class file content", e);
        }
    }
}
