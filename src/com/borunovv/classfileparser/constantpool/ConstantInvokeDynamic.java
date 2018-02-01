package com.borunovv.classfileparser.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author borunovv
 */
public class ConstantInvokeDynamic extends ConstantInfo {

    private int bootstrapMethodAttrIndex;
    private int nameAndTypeIndex;

    public ConstantInvokeDynamic() {
        super(ConstantType.InvokeDynamic);
    }

    @Override
    public void fromStream(DataInputStream input) throws IOException {
        this.bootstrapMethodAttrIndex = input.readUnsignedShort();
        this.nameAndTypeIndex = input.readUnsignedShort();
    }

    public int getBootstrapMethodAttrIndex() {
        return bootstrapMethodAttrIndex;
    }

    public int getNameAndTypeIndex() {
        return nameAndTypeIndex;
    }

    @Override
    protected String getReference() {
        return "#" + bootstrapMethodAttrIndex + ".#" + nameAndTypeIndex;
    }

    @Override
    public String getResolvedValue(ConstantPool constantPool) {
        return constantPool.get(bootstrapMethodAttrIndex).getResolvedValue(constantPool)
                + "."
                + constantPool.get(nameAndTypeIndex).getResolvedValue(constantPool);
    }

    @Override
    public String toString() {
        return "ConstantInvokeDynamic{" +
                "bootstrapMethodAttrIndex=" + bootstrapMethodAttrIndex +
                ", nameAndTypeIndex=" + nameAndTypeIndex +
                "}";
    }
}
