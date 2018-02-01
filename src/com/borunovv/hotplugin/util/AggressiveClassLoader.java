package com.borunovv.hotplugin.util;

import java.util.HashSet;
import java.util.Set;

/**
 * @author borunovv
 */
public class AggressiveClassLoader extends ClassLoader {

    private Set<String> loadedClasses = new HashSet<>();
    private Set<String> unavaiClasses = new HashSet<>();
    private ClassLoader parent = AggressiveClassLoader.class.getClassLoader();
    private IClassContentProvider classContentProvider;

    public AggressiveClassLoader(IClassContentProvider classContentProvider) {
        this.classContentProvider = classContentProvider;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (loadedClasses.contains(name) || unavaiClasses.contains(name)) {
            return super.loadClass(name); // Use default CL cache
        }

        byte[] newClassData = classContentProvider.getClassContent(name);
        if (newClassData != null) {
            loadedClasses.add(name);
            String fullClassName = ClassUtils.getFullClassName(newClassData);
            loadedClasses.add(fullClassName);
            return loadClass(newClassData, fullClassName);
        } else {
            unavaiClasses.add(name);
            return parent.loadClass(name);
        }
    }

    private Class<?> loadClass(byte[] classData, String fullClassName) {
        Class<?> clazz = defineClass(fullClassName, classData, 0, classData.length);
        if (clazz != null) {
            if (clazz.getPackage() == null) {
                definePackage(fullClassName.replaceAll("\\.\\w+$", ""), null, null, null, null, null, null, null);
            }
            resolveClass(clazz);
        }
        return clazz;
    }
}