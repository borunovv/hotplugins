package com.borunovv.classfileparser.constantpool;

/**
 * @author borunovv
 */
public enum MethodReferenceKind {
    REF_getField(1),
    REF_getStatic(2),
    REF_puField(3),
    REF_putStatic(4),
    REF_invokeVirtual(5),
    REF_invokeStatic(6),
    REF_invokeSpecial(7),
    REF_newInvokeSpecial(8),
    REF_invokeInterface(9);


    private int kind;

    MethodReferenceKind(int kind) {
        this.kind = kind;
    }

    public static MethodReferenceKind fromKind(int kind) {
        for (MethodReferenceKind type : values()) {
            if (type.kind == kind) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unexpected kind value: " + kind);
    }

    @Override
    public java.lang.String toString() {
        return super.toString() + "(kind=" + kind + ")";
    }
}
