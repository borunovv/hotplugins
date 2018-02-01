package com.borunovv.classfileparser.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author borunovv
 */
public class ConstantFieldRef extends ConstantInfo {

    private int classIndex;
    private int nameAndTypeIndex;

    public ConstantFieldRef() {
        super(ConstantType.Fieldref);
    }

    @Override
    public void fromStream(DataInputStream input) throws IOException {
        this.classIndex = input.readUnsignedShort();
        this.nameAndTypeIndex = input.readUnsignedShort();
    }

    public int getClassIndex() {
        return classIndex;
    }

    public int getNameAndTypeIndex() {
        return nameAndTypeIndex;
    }

    @Override
    protected String getReference() {
        return "#" + classIndex + ".#" + nameAndTypeIndex;
    }

    @Override
    public String getResolvedValue(ConstantPool constantPool) {
        return constantPool.get(classIndex).getResolvedValue(constantPool)
                + "."
                + constantPool.get(nameAndTypeIndex).getResolvedValue(constantPool);
    }

    @Override
    public String toString() {
        return "ConstantFieldRef{" +
                "classIndex=" + classIndex +
                ", nameAndTypeIndex=" + nameAndTypeIndex +
                "} ";
    }
}
