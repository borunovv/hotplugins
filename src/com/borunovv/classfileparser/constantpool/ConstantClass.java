package com.borunovv.classfileparser.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author borunovv
 */
public class ConstantClass extends ConstantInfo {

    private int nameIndex;

    public ConstantClass() {
        super(ConstantType.Class);
    }

    @Override
    public void fromStream(DataInputStream input) throws IOException {
        this.nameIndex = input.readUnsignedShort();
    }

    public int getNameIndex() {
        return nameIndex;
    }

    @Override
    protected String getReference() {
        return "#" + nameIndex;
    }

    @Override
    public String getResolvedValue(ConstantPool constantPool) {
        return constantPool.get(nameIndex).getResolvedValue(constantPool);
    }


    @Override
    public String toString() {
        return "ConstantClass{" +
                "nameIndex=" + nameIndex +
                "}";
    }
}
