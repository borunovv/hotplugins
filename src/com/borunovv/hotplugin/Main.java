package com.borunovv.hotplugin;

import com.borunovv.hotplugin.plugin.FileSystemPluginProvider;
import com.borunovv.hotplugin.plugin.PluginManager;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;

public class Main {

    private static final String PLUGIN_DIR = "./test_plugins/";

    public static void main(String[] args) throws Exception {
        FileSystemPluginProvider pluginProvider = new FileSystemPluginProvider(PLUGIN_DIR);
        PluginManager<IPlugin> pluginManager = new PluginManager<>(pluginProvider);

        List<String> pluginNames = pluginProvider.getAvailablePluginNames();

        String firstName = pluginNames.size() > 0 ?
                pluginNames.get(0) :
                "";

        if (firstName.isEmpty()) {
            throw new RuntimeException("No plugins found in dir: '" + PLUGIN_DIR + "'");
        }

        while (true) {
            Supplier<IPlugin> p = pluginManager.findPlugin(firstName);
            if (p.get() != null) {
                String result = p.get().process("{}");
                System.out.println("Plugin '" + firstName + "': " + result);
            } else {
                System.out.println("Plugin '" + firstName + "': not found.");
            }

            System.out.println("\nPress enter to reload plugin. Or type 'exit' or any other string to exit.");
            Scanner s = new Scanner(System.in);
            String input = s.nextLine();
            if (!input.isEmpty()) {
                break;
            }


            swap(PLUGIN_DIR, "ExternalPlugin.class", "ExternalPlugin.class_disabled");
            System.out.println("Swapped files: ExternalPlugin.class <-> ExternalPlugin.class_disabled");
            pluginManager.reloadPlugin(firstName);
        }
    }

    private static void swap(String pluginDir, String name1, String name2) throws IOException {
        File f1 = new File(pluginDir + name1);
        File f2 = new File(pluginDir + name2);
        File temp = new File(pluginDir + "temp");
        if (!f1.renameTo(temp)) {
            throw new IOException("Can't rename file '" + name1 + "' -> temp");
        }

        if (!f2.renameTo(f1)) {
            throw new IOException("Can't rename file '" + name2 + "' -> '" + name1);
        }

        if (!temp.renameTo(f2)) {
            throw new IOException("Can't rename file 'temp' -> '" + name2);
        }
    }
}

