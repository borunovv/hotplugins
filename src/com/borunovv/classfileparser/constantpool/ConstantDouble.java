package com.borunovv.classfileparser.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author borunovv
 */
public class ConstantDouble extends ConstantInfo {

    private double value;

    public ConstantDouble() {
        super(ConstantType.Double);
    }

    @Override
    public int howManyEntriesTakeInConstantPoolTable() {
        return 2;
    }

    @Override
    public void fromStream(DataInputStream input) throws IOException {
        this.value = input.readDouble();
    }

    public double getValue() {
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
        return "ConstantDouble{" +
                "value=" + value +
                "}";
    }
}
