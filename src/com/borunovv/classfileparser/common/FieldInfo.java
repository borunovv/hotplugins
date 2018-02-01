package com.borunovv.classfileparser.common;

import com.borunovv.classfileparser.constantpool.ConstantPool;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-4.html#jvms-4.5
 *
 * field_info {
 *   u2             access_flags;
 *   u2             name_index;
 *   u2             descriptor_index;
 *   u2             attributes_count;
 *   attribute_info attributes[attributes_count];
 * }
 *
 * @author borunovv
 */
public class FieldInfo {
    private String name;
    private String descriptor;
    private Set<FieldAccessFlags> accessFlags;
    private List<Attribute> attributes;

    public FieldInfo(DataInputStream input, ConstantPool constantPool) throws IOException {
        parse(input, constantPool);
    }

    private void parse(DataInputStream input, ConstantPool constantPool) throws IOException {
        int accessFlags = input.readUnsignedShort();
        this.accessFlags = FieldAccessFlags.disassemble(accessFlags);

        int nameIndex = input.readUnsignedShort();
        this.name = constantPool.getResolvedValue(nameIndex);

        int descriptorIndex = input.readUnsignedShort();
        this.descriptor = constantPool.getResolvedValue(descriptorIndex);

        int attributesCount = input.readUnsignedShort();
        this.attributes = new ArrayList<>(attributesCount);

        for (int i = 0; i < attributesCount; ++i) {
            Attribute attribute = new Attribute(input, constantPool);
            this.attributes.add(attribute);
        }
    }

    @Override
    public String toString() {
        return "FieldInfo{" +
                "name='" + name + '\'' +
                ", descriptor='" + descriptor + '\'' +
                ", accessFlags=" + FieldAccessFlags.toString(accessFlags) +
                ", attributes=" + Arrays.toString(attributes.toArray()) +
                '}';
    }
}
