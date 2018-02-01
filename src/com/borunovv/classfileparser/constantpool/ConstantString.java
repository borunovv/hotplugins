package com.borunovv.classfileparser.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author borunovv
 */
public class ConstantString extends ConstantInfo {

    private int stringIndex;

    public ConstantString() {
        super(ConstantType.String);
    }

    @Override
    public void fromStream(DataInputStream input) throws IOException {
        this.stringIndex = input.readUnsignedShort();
    }

    public int getStringIndex() {
        return stringIndex;
    }

    @Override
    protected String getReference() {
        return "#" + stringIndex;
    }

    @Override
    public String getResolvedValue(ConstantPool constantPool) {
        return constantPool.get(stringIndex).getResolvedValue(constantPool);
    }

    @Override
    public String toString() {
        return "ConstantString{" +
                "stringIndex=" + stringIndex +
                "}";
    }
}
