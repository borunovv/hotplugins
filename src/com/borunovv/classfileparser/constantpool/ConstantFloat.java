package com.borunovv.classfileparser.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author borunovv
 */
public class ConstantFloat extends ConstantInfo {

    private float value;

    public ConstantFloat() {
        super(ConstantType.Float);
    }

    @Override
    public void fromStream(DataInputStream input) throws IOException {
        this.value = input.readInt();
    }

    public float getValue() {
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
        return "ConstantFloat{" +
                "value=" + value +
                "}";
    }
}
