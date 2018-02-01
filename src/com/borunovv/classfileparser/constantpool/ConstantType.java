package com.borunovv.classfileparser.constantpool;

/**
 * @author borunovv
 */
public enum ConstantType {
    Class(7),
    Fieldref(9),
    MethodRef(10),
    InterfaceMethodRef(11),
    String(8),
    Integer(3),
    Float(4),
    Long(5),
    Double(6),
    NameAndType(12),
    Utf8(1),
    MethodHandle(15),
    MethodType(16),
    InvokeDynamic(18);

    private int tag;

    ConstantType(int tag) {
        this.tag = tag;
    }

    public static ConstantType fromTag(int tag) {
        for (ConstantType type : values()) {
            if (type.tag == tag) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unexpected tag value: " + tag);
    }

    public int getTag() {
        return tag;
    }
}
