package com.borunovv.classfileparser.common;

import com.borunovv.classfileparser.constantpool.ConstantPool;
import com.borunovv.common.Assert;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author borunovv
 */
public class Attribute {
    private String name;
    private byte[] info;

    public Attribute(DataInputStream input, ConstantPool constantPool) throws IOException {
        parse(input, constantPool);
    }

    private void parse(DataInputStream input, ConstantPool constantPool) throws IOException {
        int nameIndex = input.readUnsignedShort();
        this.name = constantPool.getResolvedValue(nameIndex);
        int infoSize = input.readInt();
        info = new byte[infoSize];
        int red = input.read(info);
        Assert.isTrue(red == infoSize, "Can't read whole attribute info array. Expected: " + infoSize + ", actual red: " + red);
    }

    public String getName() {
        return name;
    }

    public byte[] getInfo() {
        return info;
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "name='" + name + '\'' +
                ", info_size=" + (info != null ? info.length : 0) +
                '}';
    }
}
