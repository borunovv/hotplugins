package com.borunovv.classfileparser.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author borunovv
 */
public class ConstantInteger extends ConstantInfo {

    private int value;

    public ConstantInteger() {
        super(ConstantType.Integer);
    }

    @Override
    public void fromStream(DataInputStream input) throws IOException {
        this.value = input.readInt();
    }

    public int getValue() {
        return value;
    }

    @Override
    protected String getReference() {
        return null;
    }

    @Override
    public String getResolvedValue(ConstantPool constantPool) {
        return "" + value;
    }

    @Override
    public String toString() {
        return "ConstantInteger{" +
                "value=" + value +
                "}";
    }
}
