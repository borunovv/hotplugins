package com.borunovv.classfileparser.common;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author borunovv
 */
public enum FieldAccessFlags {
    ACC_PUBLIC (0x0001),
    ACC_PRIVATE (0x0002),
    ACC_PROTECTED (0x0004),
    ACC_STATIC (0x0008),
    ACC_FINAL (0x0010),
    ACC_VOLATILE (0x0040),
    ACC_TRANSIENT(0x0080),
    ACC_SYNTHETIC (0x1000),
    ACC_ENUM (0x4000)
    ;

    private int value;

    FieldAccessFlags(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Set<FieldAccessFlags> disassemble(int flags) {
        Set<FieldAccessFlags> result = new LinkedHashSet<>();
        for (FieldAccessFlags flag : values()) {
            if ((flags & flag.getValue()) != 0) {
                result.add(flag);
            }
        }
        return result;
    }

    public static int assemble(Set<FieldAccessFlags> flags) {
        int result = 0;
        for (FieldAccessFlags flag : flags) {
            result |= flag.getValue();
        }
        return result;
    }

    public static String toHexFormat(int flags) {
        return String.format("0x%04X", flags & 0xFFFF);
    }

    public static String toHexFormat(Set<FieldAccessFlags> flags) {
        return toHexFormat(FieldAccessFlags.assemble(flags));
    }

    public static String toString(int flags) {
        return toString(disassemble(flags));
    }

    public static String toString(Set<FieldAccessFlags> flags) {
        return toHexFormat(FieldAccessFlags.assemble(flags)) + Arrays.toString(flags.toArray());
    }
}
