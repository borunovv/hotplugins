package com.borunovv.classfileparser.common;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author borunovv
 */
public enum ClassAccessFlags {
    ACC_PUBLIC (0x0001),
    ACC_FINAL (0x0010),
    ACC_SUPER (0x0020),
    ACC_INTERFACE (0x0200),
    ACC_ABSTRACT (0x0400),
    ACC_SYNTHETIC (0x1000),
    ACC_ANNOTATION (0x2000),
    ACC_ENUM (0x4000)
    ;

    private int value;

    ClassAccessFlags(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Set<ClassAccessFlags> disassemble(int flags) {
        Set<ClassAccessFlags> result = new LinkedHashSet<>();
        for (ClassAccessFlags flag : values()) {
            if ((flags & flag.getValue()) != 0) {
                result.add(flag);
            }
        }
        return result;
    }

    public static int assemble(Set<ClassAccessFlags> flags) {
        int result = 0;
        for (ClassAccessFlags flag : flags) {
            result |= flag.getValue();
        }
        return result;
    }

    public static String toHexFormat(int flags) {
        return String.format("0x%04X", flags & 0xFFFF);
    }

    public static String toHexFormat(Set<ClassAccessFlags> flags) {
        return toHexFormat(ClassAccessFlags.assemble(flags));
    }

    public static String toString(int flags) {
        return toString(disassemble(flags));
    }

    public static String toString(Set<ClassAccessFlags> flags) {
        return toHexFormat(ClassAccessFlags.assemble(flags)) + Arrays.toString(flags.toArray());
    }
}
