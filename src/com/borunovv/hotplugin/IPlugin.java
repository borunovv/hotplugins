package com.borunovv.hotplugin;

import java.io.Closeable;

/**
 * @author borunovv
 */
public interface IPlugin extends Closeable {
    String process(String str);

    default void close() {
    }
}
