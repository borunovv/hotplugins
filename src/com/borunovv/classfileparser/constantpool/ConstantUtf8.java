package com.borunovv.classfileparser.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author borunovv
 */
public class ConstantUtf8 extends ConstantInfo {

    private String value;

    public ConstantUtf8() {
        super(ConstantType.Utf8);
    }

    @Override
    public void fromStream(DataInputStream input) throws IOException {
        this.value = input.readUTF();
    }

    public String getValue() {
        return value;
    }

    @Override
    protected String getReference() {
        return null;
    }

    @Override
    public String getResolvedValue(ConstantPool constantPool) {
        return value;
    }

    @Override
    public String toString() {
        return "ConstantUtf8{" +
                "\"" + (value != null ? value : "[null]") + "\"" +
                "}";
    }
}
