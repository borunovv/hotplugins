package com.borunovv.hotplugin.util;

/**
 * @author borunovv
 */
@FunctionalInterface
public interface IClassContentProvider {
    byte[] getClassContent(String name);
}
