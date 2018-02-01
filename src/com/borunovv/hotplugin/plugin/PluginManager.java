package com.borunovv.hotplugin.plugin;


import com.borunovv.hotplugin.util.AggressiveClassLoader;
import com.borunovv.hotplugin.util.IClassContentProvider;

import java.io.Closeable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

/**
 * @author borunovv
 */
public class PluginManager<T extends Closeable> {

    private ConcurrentHashMap<String, AtomicReference<T>> plugins = new ConcurrentHashMap<>();
    private IClassContentProvider pluginProvider;

    public PluginManager(IClassContentProvider pluginProvider) {
        this.pluginProvider = pluginProvider;
    }

    public Supplier<T> findPlugin(String name) {
        if (!plugins.containsKey(name)) {
            return reloadPlugin(name);
        } else {
            return plugins.get(name)::get;
        }
    }

    public Supplier<T> reloadPlugin(String name) {
        try {
            Class<?> clazz = reloadClass(name);
            T newInstance = (T) clazz.newInstance();
            AtomicReference<T> current = plugins.putIfAbsent(name, new AtomicReference<>(newInstance));
            if (current != null) {
                T prev = current.getAndSet(newInstance);
                prev.close();
                return current::get;
            } else {
                return plugins.get(name)::get;
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to reload plugin: " + name, e);
        }
    }

    private Class<?> reloadClass(String name) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        return new AggressiveClassLoader(pluginProvider).loadClass(name);
    }
}
