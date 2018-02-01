package com.borunovv.classfileparser.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author borunovv
 */
public class ConstantNameAndType extends ConstantInfo {

    private int nameIndex;
    private int descriptorIndex;

    public ConstantNameAndType() {
        super(ConstantType.NameAndType);
    }

    @Override
    public void fromStream(DataInputStream input) throws IOException {
        this.nameIndex = input.readUnsignedShort();
        this.descriptorIndex = input.readUnsignedShort();
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }


    @Override
    protected String getReference() {
        return "#" + nameIndex + ":#" + descriptorIndex;
    }

    @Override
    public String getResolvedValue(ConstantPool constantPool) {
        return constantPool.get(nameIndex).getResolvedValue(constantPool)
                + ":"
                + constantPool.get(descriptorIndex).getResolvedValue(constantPool);
    }

    @Override
    public String toString() {
        return "ConstantNameAndType{" +
                "nameIndex=" + nameIndex +
                ", descriptorIndex=" + descriptorIndex +
                "}";
    }
}
