package com.borunovv.classfileparser.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author borunovv
 */
public class ConstantMethodHandle extends ConstantInfo {

    private MethodReferenceKind referenceKind;
    private int referenceIndex;

    public ConstantMethodHandle() {
        super(ConstantType.MethodHandle);
    }

    @Override
    public void fromStream(DataInputStream input) throws IOException {
        int referenceKind = input.readUnsignedByte();
        this.referenceKind = MethodReferenceKind.fromKind(referenceKind);
        this.referenceIndex = input.readUnsignedShort();
    }

    public MethodReferenceKind getReferenceKind() {
        return referenceKind;
    }

    public int getReferenceIndex() {
        return referenceIndex;
    }

    @Override
    protected String getReference() {
        return "[" + referenceKind + "]#" + referenceIndex;
    }

    @Override
    public String getResolvedValue(ConstantPool constantPool) {
        return "[" + referenceKind + "] "
                + constantPool.get(referenceIndex).getResolvedValue(constantPool);
    }

    @Override
    public String toString() {
        return "ConstantMethodHandle{" +
                "referenceKind=" + referenceKind +
                ", referenceIndex=" + referenceIndex +
                "}";
    }
}
