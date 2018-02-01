package com.borunovv.hotplugin;

/**
 * @author borunovv
 */
public class ExternalPlugin implements IPlugin {

    public ExternalPlugin() {
        System.out.println("Plugin v.1 (#" + hashCode() + "): c-tor");
    }

    @Override
    public String process(String str) {
        return "This is external Plugin v.1";
    }

    @Override
    public void close() {
        System.out.println("Plugin v.1 (#" + hashCode() + "): close()");
    }
}
