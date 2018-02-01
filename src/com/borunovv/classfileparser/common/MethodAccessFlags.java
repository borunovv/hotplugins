package com.borunovv.classfileparser.common;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author borunovv
 */
public enum MethodAccessFlags {
    ACC_PUBLIC (0x0001),
    ACC_PRIVATE (0x0002),
    ACC_PROTECTED (0x0004),
    ACC_STATIC (0x0008),
    ACC_FINAL (0x0010),
    ACC_SYNCHRONIZED (0x0020),
    ACC_BRIDGE (0x0040),
    ACC_VARARGS (0x0080),
    ACC_NATIVE (0x0100),
    ACC_ABSTRACT (0x0400),
    ACC_STRICT (0x0800),
    ACC_SYNTHETIC (0x1000)
    ;

    private int value;

    MethodAccessFlags(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Set<MethodAccessFlags> disassemble(int flags) {
        Set<MethodAccessFlags> result = new LinkedHashSet<>();
        for (MethodAccessFlags flag : values()) {
            if ((flags & flag.getValue()) != 0) {
                result.add(flag);
            }
        }
        return result;
    }

    public static int assemble(Set<MethodAccessFlags> flags) {
        int result = 0;
        for (MethodAccessFlags flag : flags) {
            result |= flag.getValue();
        }
        return result;
    }

    public static String toHexFormat(int flags) {
        return String.format("0x%04X", flags & 0xFFFF);
    }

    public static String toHexFormat(Set<MethodAccessFlags> flags) {
        return toHexFormat(MethodAccessFlags.assemble(flags));
    }

    public static String toString(int flags) {
        return toString(disassemble(flags));
    }

    public static String toString(Set<MethodAccessFlags> flags) {
        return toHexFormat(MethodAccessFlags.assemble(flags)) + Arrays.toString(flags.toArray());
    }
}
