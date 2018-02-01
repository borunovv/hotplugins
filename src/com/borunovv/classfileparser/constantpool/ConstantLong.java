package com.borunovv.classfileparser.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author borunovv
 */
public class ConstantLong extends ConstantInfo {

    private long value;

    public ConstantLong() {
        super(ConstantType.Long);
    }

    @Override
    public int howManyEntriesTakeInConstantPoolTable() {
        return 2;
    }

    @Override
    public void fromStream(DataInputStream input) throws IOException {
        this.value = input.readLong();
    }

    public long getValue() {
        return value;
    }

    @Override
    protected String getReference() {
        return null;
    }

    @Override
    public String getResolvedValue(ConstantPool constantPool) {
        return "" + value + "L";
    }

    @Override
    public String toString() {
        return "ConstantLong{" +
                "value=" + value +
                "}";
    }
}
