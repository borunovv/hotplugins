package com.borunovv.classfileparser.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author borunovv
 */
public class ConstantMethodType extends ConstantInfo {

    private int descriptorIndex;

    public ConstantMethodType() {
        super(ConstantType.MethodType);
    }

    @Override
    public void fromStream(DataInputStream input) throws IOException {
        this.descriptorIndex = input.readUnsignedShort();
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }

    @Override
    protected String getReference() {
        return "#" + descriptorIndex;
    }

    @Override
    public String getResolvedValue(ConstantPool constantPool) {
        return constantPool.get(descriptorIndex).getResolvedValue(constantPool);
    }

    @Override
    public String toString() {
        return "ConstantMethodType{" +
                "descriptorIndex=" + descriptorIndex +
                "}";
    }
}
